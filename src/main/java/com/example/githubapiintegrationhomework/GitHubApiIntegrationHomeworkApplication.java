package com.example.githubapiintegrationhomework;

import com.example.githubapiintegrationhomework.GithubClient.BranchGithubResponseDto;
import com.example.githubapiintegrationhomework.model.DatabaseEntity;
import com.example.githubapiintegrationhomework.service.GitHubRepositoriesProxy;
import com.example.githubapiintegrationhomework.GithubClient.RepositoryGithubResponseDto;
import com.example.githubapiintegrationhomework.service.GitHubRepositoryService;
import com.example.githubapiintegrationhomework.service.RepositoryMapper;
import feign.FeignException;
import feign.RetryableException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpHeaders;

import java.util.*;

@SpringBootApplication
@EnableFeignClients
@Log4j2
public class GitHubApiIntegrationHomeworkApplication {

    @Autowired
    GitHubRepositoryService gitHubRepositoryClient;

    public static void main(String[] args) {
        SpringApplication.run(GitHubApiIntegrationHomeworkApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void run() {
        try {

            String requestUsername = "kalqa";

            DatabaseEntity databaseEntityByUsername = gitHubRepositoryClient.getDatabaseEntityByUsername(requestUsername);

            log.info(databaseEntityByUsername);

        } catch (FeignException.FeignClientException feignException) {
            log.error("client exception: " + feignException.status());
        } catch (FeignException.FeignServerException feignException) {
            System.out.println("server exception: " + feignException.status());
        } catch (RetryableException retryableException) {
            System.out.println("retryable exception " + retryableException.getMessage());
        } catch (FeignException feignException) {
            System.out.println(feignException.getMessage());
            System.out.println(feignException.status());
        }
    }

}
