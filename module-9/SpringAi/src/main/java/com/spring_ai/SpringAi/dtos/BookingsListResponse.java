package com.spring_ai.SpringAi.dtos;

import java.util.List;

public record BookingsListResponse(List<BookingResponse> bookings, String message) {}
