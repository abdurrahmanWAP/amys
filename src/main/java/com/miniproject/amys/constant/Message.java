package com.miniproject.amys.constant;

public enum Message {
    CREATE("Created successfully"),
    UPDATE("Updated successfully"),
    DELETE("Deleted successfully");

    private String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
