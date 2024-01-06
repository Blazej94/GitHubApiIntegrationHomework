package com.example.githubapiintegrationhomework.model;

import java.util.List;

public record DatabaseEntity(String userName, List<Repository> repositories) {
}
