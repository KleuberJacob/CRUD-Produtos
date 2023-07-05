package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDTO {

    private String message;

    public ErrorDTO(String message) {
        this.message = message;
    }

}
