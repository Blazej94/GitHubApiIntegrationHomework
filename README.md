## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
This project use GitHub Api https://api.github.com. 

Api consumer make HTTP query GET on endpoint {server.port}/username/{username} with GitHub username as path variable and header contains “Accept: application/json”
Client receive list all user GitHub repositories, which are not forks.

Information, which is in the response:
Repository Name
Owner Login
For each branch it’s name and last commit sha.

Endpoint:
{server.port}/username/{username}

## Technologies
Project is created with:
* Spring Boot: 3.1.0
* Open Feign: 8.18.0
* Swagger: 2.0.2

## Setup
To run this project complete secret.token in application.properties by your GitHub API key.