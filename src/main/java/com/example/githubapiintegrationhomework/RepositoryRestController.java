package com.example.githubapiintegrationhomework;

import com.example.githubapiintegrationhomework.GithubClient.GitHubRepositoryService;
import com.example.githubapiintegrationhomework.Server.ClientResponseDto;
import com.example.githubapiintegrationhomework.model.DatabaseEntity;
import com.example.githubapiintegrationhomework.service.ClientMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping()
public class RepositoryRestController {

    @Autowired
    private GitHubRepositoryService gitHubRepositoryClient;

    @Autowired
    ClientMapper mapper;

    @GetMapping("/{username}")
    public ClientResponseDto getRepositoriesByUsername(@RequestHeader(required = false) String accept, @PathVariable String username) {
        DatabaseEntity databaseEntityByUsername = gitHubRepositoryClient.getDatabaseEntityByUsername(username);
        return mapper.mapFromDatabaseEntityToClientResponseDto(databaseEntityByUsername);
    }

}
