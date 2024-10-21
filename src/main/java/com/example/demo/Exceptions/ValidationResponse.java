package com.example.demo.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationResponse {
    private LocalDateTime localDateTime;
    private int status;
    private Map<String, String> errors;
}
