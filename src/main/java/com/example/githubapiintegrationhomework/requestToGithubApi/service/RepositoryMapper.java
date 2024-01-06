package com.example.githubapiintegrationhomework.requestToGithubApi.service;

import com.example.githubapiintegrationhomework.requestToGithubApi.dto.BranchGithubResponseDto;
import com.example.githubapiintegrationhomework.requestToGithubApi.dto.RepositoryGithubResponseDto;
import com.example.githubapiintegrationhomework.model.BranchEntity;
import com.example.githubapiintegrationhomework.model.RepositoryGithubEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
    class RepositoryMapper {

    RepositoryGithubEntity mapFromRepositoryGithubResponseDtoToRepository(RepositoryGithubResponseDto repositoryGithubResponseDto, List<BranchGithubResponseDto> branchDto) {
        return new RepositoryGithubEntity(repositoryGithubResponseDto.name(), repositoryGithubResponseDto.owner().login(), mapFromListBranchGithubResponseDtoListToBranchList(branchDto));
    }

    private BranchEntity mapFromBranchGithubResponseDtoToBranch(BranchGithubResponseDto branchGithubResponseDto) {
        return new BranchEntity(branchGithubResponseDto.name(), branchGithubResponseDto.commit().sha());
    }

    private List<BranchEntity> mapFromListBranchGithubResponseDtoListToBranchList(List<BranchGithubResponseDto> branchDtos) {
        List<BranchEntity> branchEntities = new ArrayList<>();
        branchDtos.forEach(
                branchDto -> branchEntities.add(mapFromBranchGithubResponseDtoToBranch(branchDto))
        );
        return branchEntities;
    }

}
