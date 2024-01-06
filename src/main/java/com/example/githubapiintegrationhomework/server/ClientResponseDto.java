package com.example.githubapiintegrationhomework.server;

import java.util.List;

public record ClientResponseDto(List<RepositoryClientResponseDto> repositories) {
}
