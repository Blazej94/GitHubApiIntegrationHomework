package com.example.githubapiintegrationhomework.GithubClient;

import com.example.githubapiintegrationhomework.model.Branch;
import com.example.githubapiintegrationhomework.model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class RepositoryMapper {

    @Autowired
    GitHubRepositoriesProxy gitHubRepositoriesClient;

    public Branch mapFromBranchGithubResponseDtoToBranch(BranchGithubResponseDto branchGithubResponseDto) {
        return new Branch(branchGithubResponseDto.name(), branchGithubResponseDto.commit().sha());
    }

    public List<Branch> mapFromBranchGithubResponseDtoListToBranchList(List<BranchGithubResponseDto> branchDtos) {
        List<Branch> branches = new ArrayList<>();
        branchDtos.forEach(
                branchDto -> branches.add(mapFromBranchGithubResponseDtoToBranch(branchDto))
        );
        return branches;
    }

    public Repository mapFromRepositoryGithubResponseDtoToRepository(RepositoryGithubResponseDto repositoryGithubResponseDto, List<Branch> branches) {
        return new Repository(repositoryGithubResponseDto.name(), repositoryGithubResponseDto.owner().login(), branches);
    }

    public List<Repository> mapFromRepositoryGithubResponseDtoListToRepositoryList(List<RepositoryGithubResponseDto> repositoriesDtos) {
        List<Repository> repositories = new ArrayList<>();
        repositoriesDtos.forEach(
                repositoryDto -> repositories.add(mapFromRepositoryGithubResponseDtoToRepository(
                        repositoryDto,
                        mapFromBranchGithubResponseDtoListToBranchList(Arrays.stream(gitHubRepositoriesClient.fetchListBranchesForARepository(repositoryDto.owner().login(), repositoryDto.name())).toList())
                        ))
        );
        return repositories;
    }

}
