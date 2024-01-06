package com.example.githubapiintegrationhomework.responseToClient.controller.error;

import com.example.githubapiintegrationhomework.responseToClient.controller.ClientResponseDtoInterface;
import org.springframework.http.HttpStatus;

public record ErrorClientResponseDto(HttpStatus status, String Message) implements ClientResponseDtoInterface {
}
