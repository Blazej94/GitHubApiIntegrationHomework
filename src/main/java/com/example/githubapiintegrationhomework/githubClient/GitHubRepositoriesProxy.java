package com.example.githubapiintegrationhomework.githubClient;

import com.example.githubapiintegrationhomework.githubClient.dto.BranchGithubResponseDto;
import com.example.githubapiintegrationhomework.githubClient.dto.RepositoryGithubResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "github-client-list-repositories-for-a-user")
public interface GitHubRepositoriesProxy {

    @GetMapping(value = "/users/{username}/repos", headers = {"Authorization=${secret.token}"})
    RepositoryGithubResponseDto[] fetchListRepositoriesForAUser(@PathVariable String username);

    @GetMapping(value = "/repos/{owner}/{repo}/branches", headers = {"Authorization=${secret.token}"})
    BranchGithubResponseDto[] fetchListBranchesForARepository(@PathVariable String owner, @PathVariable String repo);
}
