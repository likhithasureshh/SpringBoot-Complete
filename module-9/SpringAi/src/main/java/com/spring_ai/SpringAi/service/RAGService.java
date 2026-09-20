package com.spring_ai.SpringAi.service;

import com.spring_ai.SpringAi.advisors.TokenUsageAdvisor;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.VectorStoreChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RAGService {
    private final ChatClient chatClient;
    private final EmbeddingModel embeddingModel;
    private final VectorStore vectorStore;
    private final ChatMemory chatMemory;

    @Value("classpath:faq.pdf")
    private Resource resource;

    public String askAiWithAdvisors(String prompt,String userId)
    {
        return chatClient.prompt()
                .system("""
                You are an AI assistance with name Cody,
                Always greet the user with ur name and their name if u know
                Reply in the friendly conversational tone
                """)
                .user(prompt)
                .advisors(
                        a-> a.param(ChatMemory.CONVERSATION_ID,userId)
                                .advisors(
                                        //new SafeGuardAdvisor(List.of("Politics","Gaming")),
                                        VectorStoreChatMemoryAdvisor.builder(vectorStore)
                                                .build(),
                                        MessageChatMemoryAdvisor.builder(chatMemory)
                                             .build()
                                                ,
                                        QuestionAnswerAdvisor.builder(vectorStore)
                                                .searchRequest(
                                                        SearchRequest
                                                                .builder()
                                                                .topK(4)
                                                                .similarityThreshold(0.5)
                                                                .filterExpression("file_name == 'faq.pdf'")
                                                                .query(prompt)
                                                                .build()
                                                )
                                                .build(),
                                        new TokenUsageAdvisor()
                                )
                )
                .call()
                .content();
    }

    public String askAI(String prompt)
    {
        String sysPrompt = """
                You are an AI Assistant helping a developer
                Rules:
                1.Give the results based on the context only
                2. Do not introduce your own knowledge or facts
                3. if you think there are more than one ans,the combine the results
                4.You can rephrase,summarize and explain in natural language
                5.if u dont have the result in the context just say,'I dont Know'
                the context is : {context}
                Reply in the friendly Conversational Tone.
                """;
        List<Document> documentList = vectorStore.similaritySearch(
                SearchRequest
                        .builder()
                        .query(prompt)
                        .topK(1)
                        .similarityThreshold(0.8)
                        .filterExpression("file_name == 'faq.pdf'")
                        .build()
        );
        String context = documentList.stream()
                .map(Document::getText)
                .collect(Collectors.joining("\n\n"));

        PromptTemplate promptTemplate = new PromptTemplate(sysPrompt);
        String rendered = promptTemplate.render(Map.of("context",context));
        return chatClient.prompt()
                .user(rendered)
                .advisors(
                        new SimpleLoggerAdvisor()
                )
                .call()
                .content();
    }

    public void ingestData()
    {
        PagePdfDocumentReader reader = new PagePdfDocumentReader(resource);
        List<Document> documentsList = reader.get();

        TokenTextSplitter tokenTextSplitter = TokenTextSplitter.builder()
                .withChunkSize(200)
                .build();

        List<Document> chunks = tokenTextSplitter.apply(documentsList);
        vectorStore.add(chunks);
    }


}
