package com.example.githubapiintegrationhomework.requestToGithubApi.service;

import com.example.githubapiintegrationhomework.repository.RepositoryDatabase;
import com.example.githubapiintegrationhomework.requestToGithubApi.dto.BranchGithubResponseDto;
import com.example.githubapiintegrationhomework.requestToGithubApi.dto.RepositoryGithubResponseDto;
import com.example.githubapiintegrationhomework.model.DatabaseEntity;
import com.example.githubapiintegrationhomework.model.RepositoryGithubEntity;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Log4j2
public class GitHubRepositoryService {

    private final GitHubRepositoriesProxy gitHubRepositoriesClient;
    private final RepositoryDatabase repositoryDatabase;
    private final RepositoryMapper repositoryMapper;

    private GitHubRepositoryService(GitHubRepositoriesProxy gitHubRepositoriesClient, RepositoryDatabase repositoryDatabase, RepositoryMapper repositoryMapper) {
        this.gitHubRepositoriesClient = gitHubRepositoriesClient;
        this.repositoryDatabase = repositoryDatabase;
        this.repositoryMapper = repositoryMapper;
    }

    public DatabaseEntity getDatabaseEntityByUsername(String username) {
        List<RepositoryGithubResponseDto> repositoryDtos = getListRepositoryGithubResponseDtoByUsername(username);
        List<RepositoryGithubEntity> repositories = new ArrayList<>();
        repositoryDtos.forEach(
                repositoryDto -> repositories.add(
                        repositoryMapper.mapFromRepositoryGithubResponseDtoToRepository(
                                repositoryDto, getListBranchGithubResponseDtoByUsername(username, repositoryDto.name())))
        );
        return repositoryDatabase.saveToDatabase(username, repositories);
    }

    private List<RepositoryGithubResponseDto> getListRepositoryGithubResponseDtoByUsername(String username) {
        try {
            return Arrays.stream(gitHubRepositoriesClient.fetchListRepositoriesForAUser(username)).toList();
        } catch (FeignException.NotFound e) {
            log.error("Client given not existing github user: " + username + " " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private List<BranchGithubResponseDto> getListBranchGithubResponseDtoByUsername(String username, String repositoryName) {
        return Arrays.stream(gitHubRepositoriesClient.fetchListBranchesForARepository(username, repositoryName)).toList();
    }

}
