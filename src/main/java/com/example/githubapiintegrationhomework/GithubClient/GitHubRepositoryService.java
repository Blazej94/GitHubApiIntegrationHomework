package com.example.githubapiintegrationhomework.GithubClient;

import com.example.githubapiintegrationhomework.RepositoryRepository;
import com.example.githubapiintegrationhomework.model.DatabaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GitHubRepositoryService {

    @Autowired
    GitHubRepositoriesProxy gitHubRepositoriesClient;

    @Autowired
    RepositoryRepository repositoryDatabase;

    @Autowired
    RepositoryMapper repositoryMapper;

    public DatabaseEntity getDatabaseEntityByUsername(String username) {
        List<RepositoryGithubResponseDto> repositories = Arrays.stream(gitHubRepositoriesClient.fetchListRepositoriesForAUser(username)).toList();
        DatabaseEntity database = repositoryDatabase.saveToDatabase(username, repositoryMapper.mapFromRepositoryGithubResponseDtoListToRepositoryList(repositories));
        return database;
    }
}
