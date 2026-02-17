package com.miniproject.amys.constant;

public enum Message {
    CREATE("Create Successfully"),
    UPDATE("Update Successfully"),
    DELETE("Delete Successfully");

    private String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
