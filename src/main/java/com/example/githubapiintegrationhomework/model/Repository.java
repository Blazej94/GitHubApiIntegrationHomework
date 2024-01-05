package com.example.githubapiintegrationhomework.model;

import java.util.List;

public record Repository(String name, String ownerLogin, List<Branch> branches) {
}
