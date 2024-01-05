package com.example.githubapiintegrationhomework.model;

import com.example.githubapiintegrationhomework.model.Repository;

import java.util.List;

public record DatabaseEntity(String userName, List<Repository> repositories) {
}
