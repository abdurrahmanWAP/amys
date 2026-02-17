package com.miniproject.amys.constant;

public enum Message {
    CREATE("Create Succes"),UPDATE("Update Succes"),DELETE("Delete Succes");

    private String message;

     Message(String message){
        this.message=message;
    }

    public String getMessage(){
         return message;
    }


}
