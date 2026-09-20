package com.spring_ai.SpringAi.service;

import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AIServiceTest {

    @Autowired
    private AIService aiService;

    @Test
    void testCrackJoke()
    {
        String response = aiService.crackJoke("Likitha");
        System.out.println(response);
    }

    @Test
    void testEmbedding()
    {
        var res = aiService.embedding("Hi,This is likitha!");
        System.out.println(res.length);
        for(float f : res)
        {
            System.out.print(f+" ");
        }
    }

    @Test
    void injestData()
    {
        aiService.ingestData();
    }
    @Test
    void similaritySearch()
    {
        List<Document> documentList = aiService.similaritySearch("Give me the result with category spring boot");
        for(Document document : documentList)
        {
            System.out.println(document);
        }

    }
}