package com.example.githubapiintegrationhomework.githubClient;

import com.example.githubapiintegrationhomework.githubClient.dto.BranchGithubResponseDto;
import com.example.githubapiintegrationhomework.githubClient.dto.RepositoryGithubResponseDto;
import com.example.githubapiintegrationhomework.model.Branch;
import com.example.githubapiintegrationhomework.model.Repository;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class RepositoryMapper {

    public Branch mapFromBranchGithubResponseDtoToBranch(BranchGithubResponseDto branchGithubResponseDto) {
        return new Branch(branchGithubResponseDto.name(), branchGithubResponseDto.commit().sha());
    }

    public List<Branch> mapFromListBranchGithubResponseDtoListToBranchList(List<BranchGithubResponseDto> branchDtos) {
        List<Branch> branches = new ArrayList<>();
        branchDtos.forEach(
                branchDto -> branches.add(mapFromBranchGithubResponseDtoToBranch(branchDto))
        );
        return branches;
    }

    public Repository mapFromRepositoryGithubResponseDtoToRepository(RepositoryGithubResponseDto repositoryGithubResponseDto, List<BranchGithubResponseDto> branchDto) {
        return new Repository(repositoryGithubResponseDto.name(), repositoryGithubResponseDto.owner().login(), mapFromListBranchGithubResponseDtoListToBranchList(branchDto));
    }

}
