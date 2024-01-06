package com.example.githubapiintegrationhomework.responseToClient.service;

import com.example.githubapiintegrationhomework.responseToClient.controller.dto.BranchClientResponseDto;
import com.example.githubapiintegrationhomework.responseToClient.controller.dto.ClientResponseDto;
import com.example.githubapiintegrationhomework.responseToClient.controller.dto.RepositoryClientResponseDto;
import com.example.githubapiintegrationhomework.model.BranchEntity;
import com.example.githubapiintegrationhomework.model.DatabaseEntity;
import com.example.githubapiintegrationhomework.model.RepositoryGithubEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientMapper {

    public ClientResponseDto mapFromDatabaseEntityToClientResponseDto(DatabaseEntity databaseEntityByUsername) {
        return new ClientResponseDto(mapFromListRepositoryToListRepositoryClientResponseDto(databaseEntityByUsername.repositories()));
    }

    private List<RepositoryClientResponseDto> mapFromListRepositoryToListRepositoryClientResponseDto(List<RepositoryGithubEntity> repositories) {
        List<RepositoryClientResponseDto> repositoriesClientResponseDto = new ArrayList<>();
        repositories.forEach(
                repositoryDto -> repositoriesClientResponseDto.add(mapFromRepositoryToRepositoryClientResponseDto(repositoryDto))
        );
        return repositoriesClientResponseDto;
    }

    private RepositoryClientResponseDto mapFromRepositoryToRepositoryClientResponseDto(RepositoryGithubEntity repository) {
        return new RepositoryClientResponseDto(repository.name(), repository.ownerLogin(), mapFromListBranchToListBranchClientResponseDto(repository.branchesEntity()));
    }

    private List<BranchClientResponseDto> mapFromListBranchToListBranchClientResponseDto(List<BranchEntity> branchesEntity) {
        List<BranchClientResponseDto> branchClientResponseDtos = new ArrayList<>();
        branchesEntity.forEach(
                branchEntity -> branchClientResponseDtos.add(mapFromBranchToBranchClientResponseDto(branchEntity))
        );
        return branchClientResponseDtos;
    }

    private BranchClientResponseDto mapFromBranchToBranchClientResponseDto(BranchEntity branchEntity) {
        return new BranchClientResponseDto(branchEntity.name(), branchEntity.commit());
    }

}
