package com.example.githubapiintegrationhomework.responseToClient.repository;

import com.example.githubapiintegrationhomework.model.DatabaseEntity;
import com.example.githubapiintegrationhomework.model.RepositoryGithubEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Repository
public class RepositoryDatabase {

    private final Map<String, List<RepositoryGithubEntity>> repositoriesDatabase = new HashMap<>();

    public DatabaseEntity saveToDatabase(String userName, List<RepositoryGithubEntity> repositories) {
        DatabaseEntity databaseEntity = new DatabaseEntity(userName, repositories);
        repositoriesDatabase.put(userName, repositories);
        return databaseEntity;
    }

}
