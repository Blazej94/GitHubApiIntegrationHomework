package com.example.githubapiintegrationhomework.server;

import org.springframework.http.HttpStatus;

public record ErrorClientResponseDto(HttpStatus status, String Message) {
}
