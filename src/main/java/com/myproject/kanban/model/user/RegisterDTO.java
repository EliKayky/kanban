package com.myproject.kanban.model.user;

public record RegisterDTO(String login, String password, UserRole role) {
}
