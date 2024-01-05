package com.example.githubapiintegrationhomework.service;

import com.example.githubapiintegrationhomework.Server.BranchClientResponseDto;
import com.example.githubapiintegrationhomework.Server.ClientResponseDto;
import com.example.githubapiintegrationhomework.Server.RepositoryClientResponseDto;
import com.example.githubapiintegrationhomework.model.Branch;
import com.example.githubapiintegrationhomework.model.DatabaseEntity;
import com.example.githubapiintegrationhomework.model.Repository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientMapper {

    public ClientResponseDto mapFromDatabaseEntityToClientResponseDto(DatabaseEntity databaseEntityByUsername) {
        return new ClientResponseDto(mapFromListRepositoryToListRepositoryClientResponseDto(databaseEntityByUsername.repositories()));
    }

    private List<RepositoryClientResponseDto> mapFromListRepositoryToListRepositoryClientResponseDto(List<Repository> repositories) {
        List<RepositoryClientResponseDto> repositoriesClientResponseDto = new ArrayList<>();
        repositories.forEach(
                repositoryDto -> repositoriesClientResponseDto.add(mapFromRepositoryToRepositoryClientResponseDto(repositoryDto))
        );
        return repositoriesClientResponseDto;
    }

    private RepositoryClientResponseDto mapFromRepositoryToRepositoryClientResponseDto(Repository repository) {
        return new RepositoryClientResponseDto(repository.name(), repository.ownerLogin(), mapFromListBranchToListBranchClientResponseDto(repository.branches()));
    }

    private List<BranchClientResponseDto> mapFromListBranchToListBranchClientResponseDto(List<Branch> branches) {
        List<BranchClientResponseDto> branchClientResponseDtos = new ArrayList<>();
        branches.forEach(
                branch -> branchClientResponseDtos.add(mapFromBranchToBranchClientResponseDto(branch))
        );
        return branchClientResponseDtos;
    }

    private BranchClientResponseDto mapFromBranchToBranchClientResponseDto(Branch branch) {
        return new BranchClientResponseDto(branch.name(), branch.commit());
    }

}
