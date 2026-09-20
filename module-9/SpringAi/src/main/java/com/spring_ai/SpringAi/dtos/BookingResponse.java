package com.spring_ai.SpringAi.dtos;



import com.spring_ai.SpringAi.entity.BookingStatus;

import java.time.Instant;

public record BookingResponse(Long id, String destination, Instant departureTime, BookingStatus status) {}