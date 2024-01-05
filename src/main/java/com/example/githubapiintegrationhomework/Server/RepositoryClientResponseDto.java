package com.example.githubapiintegrationhomework.Server;

import java.util.List;

public record RepositoryClientResponseDto(String repositoryName, String login, List<BranchClientResponseDto> branches) {
}
