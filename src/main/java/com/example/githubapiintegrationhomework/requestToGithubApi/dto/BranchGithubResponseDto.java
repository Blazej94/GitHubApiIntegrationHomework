package com.example.githubapiintegrationhomework.requestToGithubApi.dto;

public record BranchGithubResponseDto(String name, CommitGithubResponseDto commit) {
}
