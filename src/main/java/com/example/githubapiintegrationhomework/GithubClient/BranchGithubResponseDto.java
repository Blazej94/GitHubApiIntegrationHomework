package com.example.githubapiintegrationhomework.GithubClient;

import com.example.githubapiintegrationhomework.GithubClient.CommitGithubResponseDto;

public record BranchGithubResponseDto(String name, CommitGithubResponseDto commit) {
}
