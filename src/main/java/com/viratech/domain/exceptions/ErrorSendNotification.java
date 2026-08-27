package com.viratech.domain.exceptions;

public class ErrorSendNotification extends RuntimeException {
    public ErrorSendNotification(String message) {
        super(message);
    }
}
