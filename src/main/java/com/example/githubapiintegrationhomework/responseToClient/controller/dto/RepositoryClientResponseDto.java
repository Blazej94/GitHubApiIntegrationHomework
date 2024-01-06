package com.example.githubapiintegrationhomework.responseToClient.controller.dto;

import com.example.githubapiintegrationhomework.responseToClient.controller.dto.BranchClientResponseDto;

import java.util.List;

public record RepositoryClientResponseDto(String repositoryName, String login, List<BranchClientResponseDto> branches) {
}
