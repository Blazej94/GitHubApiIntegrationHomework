package com.example.githubapiintegrationhomework.server;

import java.util.List;

public record RepositoryClientResponseDto(String repositoryName, String login, List<BranchClientResponseDto> branches) {
}
