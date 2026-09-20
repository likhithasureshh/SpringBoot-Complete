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
    void testAskAiWithAdvisors()
    {
        //String res = ragService.askAI("Why am i facing issues with discord and not able to connect to it");
        String res = ragService.askAiWithAdvisors("what is your views on Gaming?","liki");
        System.out.println(res);
    }
}