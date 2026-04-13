package com.miniproject.amys.dto;

public class ErrorResDto<T> {
    private final T message;

    public ErrorResDto(T message) {
        this.message = message;
    }

    public T getMessage() {
        return message;
    }

}
