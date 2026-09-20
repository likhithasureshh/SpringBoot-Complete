package com.spring_ai.SpringAi.dtos;

import lombok.Data;

@Data
public class JokeDto {
    private String text;
    private String category;
    private Double laughingScor;
    private Boolean isNSFW;

}
