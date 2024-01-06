package com.example.githubapiintegrationhomework.model;

import java.util.List;

public record RepositoryGithubEntity(String name, String ownerLogin, List<BranchEntity> branchesEntity) {
}
