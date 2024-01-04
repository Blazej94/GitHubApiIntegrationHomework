package com.example.githubapiintegrationhomework;

import feign.FeignException;
import feign.RetryableException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
@Log4j2
public class GitHubApiIntegrationHomeworkApplication {

    @Autowired
    GitHubRepositoriesProxy gitHubRepositoriesClient;

    public static void main(String[] args) {
        SpringApplication.run(GitHubApiIntegrationHomeworkApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void run() {
        try {

            List<RepositoryDto> repositories = Arrays.stream(gitHubRepositoriesClient.fetchListRepositoriesForAUser("kalqa")).toList();
            ArrayList<String> repositoriesNames = new ArrayList<>();
            repositories.forEach(
                    repositoryDto ->
                            repositoriesNames.add(repositoryDto.name())
            );

            repositoriesNames.forEach(
                    log::info
            );

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
