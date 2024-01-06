package com.example.githubapiintegrationhomework;

import com.example.githubapiintegrationhomework.githubClient.GitHubRepositoryService;
import com.example.githubapiintegrationhomework.server.ClientResponseDto;
import com.example.githubapiintegrationhomework.model.DatabaseEntity;
import com.example.githubapiintegrationhomework.service.ClientMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ClientResponseDto> getRepositoriesByUsername(@PathVariable String username){
        DatabaseEntity databaseEntityByUsername = gitHubRepositoryClient.getDatabaseEntityByUsername(username);
        ClientResponseDto response = mapper.mapFromDatabaseEntityToClientResponseDto(databaseEntityByUsername);
        log.info("Get all repositories for username: " + username);
        return ResponseEntity.ok(response);
    }

}
