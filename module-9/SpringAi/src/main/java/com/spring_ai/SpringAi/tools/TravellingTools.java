package com.spring_ai.SpringAi.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class TravellingTools {


    @Tool(
            name = "weather_fetching_tool",
            description = "Used to fetch the weather in a city")
    public String getWeather(String city)
    {
        switch (city) {
            case "Delhi" -> {
                return "Sunny with 26 degree";
            }
            case "London" -> {
                return "cloudy,with 2 degree";
            }
            default -> {
                return "invalid city namme";
            }
        }
    }
}
