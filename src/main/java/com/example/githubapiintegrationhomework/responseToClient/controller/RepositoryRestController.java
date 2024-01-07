package com.example.githubapiintegrationhomework.responseToClient.controller;

import com.example.githubapiintegrationhomework.requestToGithubApi.service.GitHubRepositoryService;
import com.example.githubapiintegrationhomework.responseToClient.controller.dto.ClientResponseDto;
import com.example.githubapiintegrationhomework.model.DatabaseEntity;
import com.example.githubapiintegrationhomework.responseToClient.controller.error.ErrorClientResponseDto;
import com.example.githubapiintegrationhomework.responseToClient.service.ClientMapper;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@Log4j2
@RequestMapping()
 public class RepositoryRestController {

    private final GitHubRepositoryService gitHubRepositoryClient;
    private final ClientMapper mapper;

    private RepositoryRestController(GitHubRepositoryService gitHubRepositoryClient, ClientMapper mapper) {
        this.gitHubRepositoryClient = gitHubRepositoryClient;
        this.mapper = mapper;
    }

    @GetMapping(value = "/username/{username}")
    private ResponseEntity<ClientResponseDtoInterface> getRepositoriesByUsername(
            @RequestHeader(HttpHeaders.ACCEPT) @Valid String accept,
            @PathVariable String username) {
        if (accept != null &&!accept.equals("application/json")) {
            log.warn("Client did not give header contains Accept: application/json");
            ErrorClientResponseDto errorResponse = new ErrorClientResponseDto(HttpStatus.NOT_ACCEPTABLE.value(), "Accept: application/json require in header");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
        }
        DatabaseEntity databaseEntityByUsername = gitHubRepositoryClient.getDatabaseEntityByUsername(username);
        ClientResponseDto response = mapper.mapFromDatabaseEntityToClientResponseDto(databaseEntityByUsername);
        ClientResponseDto emptyClientResponseDto = new ClientResponseDto(Collections.emptyList());
        if(response.equals(emptyClientResponseDto)) {
            ErrorClientResponseDto errorResponse = new ErrorClientResponseDto(HttpStatus.NOT_FOUND.value(), "given not existing github user");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
        log.info("Get all repositories for username: " + username);
        return ResponseEntity.ok(response);
    }
}
