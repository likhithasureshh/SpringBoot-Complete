package com.spring_ai.SpringAi.service;

import com.spring_ai.SpringAi.dtos.JokeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AIService {

    private final ChatClient chatClient;
    private final EmbeddingModel embeddingModel;
    private final VectorStore vectorStore;

    public String crackJoke(String topic)
    {
//        return chatClient.prompt()
//                .user("Crack a joke on topic: "+topic)
//                .call()
//                .content();

        String system = """
                You are a sarcastic joker
                Give me the jokes in the form of poem total 4 lines
                on the topic : {topic}
                Dont include the jokes on politics
                """;

        PromptTemplate promptTemplate = new PromptTemplate(system);
        String rendered = promptTemplate.render(Map.of("topic",topic));

        var response = chatClient.prompt()
                .user(rendered)
                .advisors(
                        new SimpleLoggerAdvisor()
                )
                .call()
                .entity(JokeDto.class);
        return response.getText();
    }

    public float[] embedding(String text)
    {
        return embeddingModel.embed(text);
    }

    public void ingestData()
    {
        List<Document> documents = List.of(

                new Document(
                        """
                        Spring Boot is a Java framework used to create production-ready
                        applications. It provides auto-configuration, dependency injection,
                        embedded servers, and many other features.
                        """,
                        Map.of(
                                "category", "spring-boot",
                                "topic", "basics",
                                "author", "Spring Team"
                        )
                ),

                new Document(
                        """
                        Spring AI provides abstractions for integrating AI models into
                        Spring applications. It supports chat models, embedding models,
                        vector stores, prompt templates, and RAG.
                        """,
                        Map.of(
                                "category", "spring-ai",
                                "topic", "overview",
                                "author", "Spring Team"
                        )
                ),

                new Document(
                        """
                        pgvector is a PostgreSQL extension that allows PostgreSQL to store
                        vector embeddings and perform similarity searches.
                        """,
                        Map.of(
                                "category", "database",
                                "topic", "pgvector",
                                "database", "PostgreSQL"
                        )
                ),

                new Document(
                        """
                        Retrieval Augmented Generation, or RAG, retrieves relevant documents
                        from a vector database and provides them as context to a language model
                        before generating an answer.
                        """,
                        Map.of(
                                "category", "artificial-intelligence",
                                "topic", "rag",
                                "technology", "Spring AI"
                        )
                ),

                new Document(
                        """
                        Embeddings convert text into numerical vectors that represent the
                        semantic meaning of the text. Similar pieces of text generally have
                        similar vector representations.
                        """,
                        Map.of(
                                "category", "artificial-intelligence",
                                "topic", "embeddings",
                                "technology", "Spring AI"
                        )
                ),

                new Document(
                        """
                        ChatClient in Spring AI provides a fluent API for interacting with
                        chat models. It allows applications to provide system instructions,
                        user prompts, advisors, and other configuration.
                        """,
                        Map.of(
                                "category", "spring-ai",
                                "topic", "chat-client",
                                "class", "ChatClient"
                        )
                ),

                new Document(
                        """
                        Prompt templates allow applications to create reusable prompts
                        containing variables. These variables can be replaced with actual
                        values before sending the prompt to the language model.
                        """,
                        Map.of(
                                "category", "spring-ai",
                                "topic", "prompt-template",
                                "class", "PromptTemplate"
                        )
                )
        );
        vectorStore.add(documents);
    }

    public List<Document> similaritySearch(String text)
    {
        return vectorStore.similaritySearch(
                SearchRequest.builder()
                        .topK(2)
                        .similarityThreshold(0.4)
                        .query(text)
                        .build()
        );
    }
}
