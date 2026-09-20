package com.spring_ai.SpringAi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RAGServiceTest {

    @Autowired
    private RAGService ragService;

    @Test
    void ingestData()
    {
        ragService.ingestData();
    }

    @Test
    void testAskAi()
    {
        //String res = ragService.askAI("Why am i facing issues with discord and not able to connect to it");
        String res = ragService.askAI("Where is America");
        System.out.println(res);
    }
}