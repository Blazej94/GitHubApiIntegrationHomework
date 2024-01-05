package com.example.githubapiintegrationhomework.Server;

import com.example.githubapiintegrationhomework.Server.RepositoryClientResponseDto;

import java.util.List;

public record ClientResponseDto(List<RepositoryClientResponseDto> repositories) {
}
