package com.example.githubapiintegrationhomework.GithubClient;

import com.example.githubapiintegrationhomework.GithubClient.BranchGithubResponseDto;
import com.example.githubapiintegrationhomework.GithubClient.RepositoryGithubResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "github-client-list-repositories-for-a-user")
public interface GitHubRepositoriesProxy {

    @GetMapping(value = "/users/{username}/repos", headers = {"Accept: application/json"})
    RepositoryGithubResponseDto[] fetchListRepositoriesForAUser(@PathVariable String username);

    @GetMapping(value = "/repos/{owner}/{repo}/branches", headers = {"Accept: application/json"})
    BranchGithubResponseDto[] fetchListBranchesForARepository(@PathVariable String owner, @PathVariable String repo);
}
