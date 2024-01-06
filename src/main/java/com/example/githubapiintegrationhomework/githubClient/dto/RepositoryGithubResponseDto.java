package com.example.githubapiintegrationhomework.githubClient.dto;

import com.example.githubapiintegrationhomework.githubClient.dto.OwnerGithubResponceDto;

public record RepositoryGithubResponseDto(String name, OwnerGithubResponceDto owner) {
}
