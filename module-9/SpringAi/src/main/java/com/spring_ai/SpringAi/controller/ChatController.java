package com.spring_ai.SpringAi.controller;

import com.spring_ai.SpringAi.tools.FlightBookingTool;
import com.spring_ai.SpringAi.tools.TravellingTools;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ChatController {

    private final ChatClient chatClient;
    private final ChatMemory chatMemory;
    private final TravellingTools travellingTools;
    private final FlightBookingTool flightBookingTool;

    @PostMapping(path = "/chat")
    public String chat(@RequestBody  String prompt, @RequestParam  String userId)
    {
        String system = """
                You are a friendly Flight Booking assistant,
                Help the users to create,view and update their bookings carefully
                You can perform this operations for the user and there user id is %s
                Please consider your actions and perform them only by using the existing tools
                added here.
                """+userId;

        return chatClient.prompt()
                .system(system)
                .user(prompt)
                .advisors(
                        a->a.param(ChatMemory.CONVERSATION_ID,userId)
                                .advisors(
                                        MessageChatMemoryAdvisor.builder(chatMemory)
                                                .build()
                                )
                )
                .tools(travellingTools,flightBookingTool)
                .call()
                .content();
    }
}
