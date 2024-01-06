package com.example.githubapiintegrationhomework;

import com.example.githubapiintegrationhomework.requestToGithubApi.service.GitHubRepositoryService;
import feign.FeignException;
import feign.RetryableException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableFeignClients
@Log4j2
public class GitHubApiIntegrationHomeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(GitHubApiIntegrationHomeworkApplication.class, args);
    }
}
