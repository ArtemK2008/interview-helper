package com.project.green.exeption;

public class ErrorMessage {
    private final Long createdAt;
    private final String status;
    private final String message;

    public ErrorMessage(Long createdAt, String status, String message) {
        this.createdAt = createdAt;
        this.status = status;
        this.message = message;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
