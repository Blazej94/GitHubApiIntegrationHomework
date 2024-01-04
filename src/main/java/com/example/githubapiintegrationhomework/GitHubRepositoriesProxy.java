package com.example.githubapiintegrationhomework;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "github-client-list-repositories-for-a-user")
public interface GitHubRepositoriesProxy {

    @GetMapping(value = "/{username}/repos", headers = {"Accept: application/json"})
    RepositoryDto[] fetchListRepositoriesForAUser(@PathVariable String username);

}
