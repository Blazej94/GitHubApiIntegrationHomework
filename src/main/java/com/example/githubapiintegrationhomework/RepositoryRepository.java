package com.example.githubapiintegrationhomework;

import com.example.githubapiintegrationhomework.GithubClient.RepositoryGithubResponseDto;
import com.example.githubapiintegrationhomework.model.DatabaseEntity;
import com.example.githubapiintegrationhomework.model.Repository;
import com.example.githubapiintegrationhomework.service.GitHubRepositoriesProxy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Repository
public class RepositoryRepository {

    Map<String, List<Repository>> repositoriesDatabase = new HashMap<>();

    public DatabaseEntity saveToDatabase(String userName, List<Repository> repositories) {
        DatabaseEntity databaseEntity = new DatabaseEntity(userName, repositories);
        repositoriesDatabase.put(userName, repositories);
        return databaseEntity;
    }


}
