package com.shahian.basedomains.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderEvent {
    private String message;
    private String status;
    private OrderDTO order;
}
