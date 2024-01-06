package com.example.githubapiintegrationhomework.githubClient;

import com.example.githubapiintegrationhomework.RepositoryRepository;
import com.example.githubapiintegrationhomework.githubClient.dto.BranchGithubResponseDto;
import com.example.githubapiintegrationhomework.githubClient.dto.RepositoryGithubResponseDto;
import com.example.githubapiintegrationhomework.model.DatabaseEntity;
import com.example.githubapiintegrationhomework.model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<RepositoryGithubResponseDto> repositoryDtos = getListRepositoryGithubResponseDtoByUsername(username);
        List<Repository> repositories = new ArrayList<>();

        repositoryDtos.forEach(
                repositoryDto -> repositories.add(
                        repositoryMapper.mapFromRepositoryGithubResponseDtoToRepository(
                                repositoryDto, getListBranchGithubResponseDtoByUsername(username,repositoryDto.name())))
        );
        return repositoryDatabase.saveToDatabase(username, repositories);
    }

    public List<RepositoryGithubResponseDto> getListRepositoryGithubResponseDtoByUsername(String username) {
        List<RepositoryGithubResponseDto> repositoriesDto = Arrays.stream(gitHubRepositoriesClient.fetchListRepositoriesForAUser(username)).toList();
        return repositoriesDto;
    }

    public List<BranchGithubResponseDto> getListBranchGithubResponseDtoByUsername(String username, String repositoryName) {
        List<BranchGithubResponseDto> branchesDto = Arrays.stream(gitHubRepositoriesClient.fetchListBranchesForARepository(username, repositoryName)).toList();
        return branchesDto;
    }

}
