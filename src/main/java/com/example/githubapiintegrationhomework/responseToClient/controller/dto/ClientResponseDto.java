package com.example.githubapiintegrationhomework.responseToClient.controller.dto;

import com.example.githubapiintegrationhomework.responseToClient.controller.ClientResponseDtoInterface;

import java.util.List;

public record ClientResponseDto(List<RepositoryClientResponseDto> repositories) implements ClientResponseDtoInterface {
}
