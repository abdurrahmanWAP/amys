package com.miniproject.amys.pojo;

public class MailPojo {
    private String email;
    private String assignCode;

    public String getEmail() {
        return email;
    }

    public MailPojo(String email, String assignCode) {
        this.email = email;
        this.assignCode = assignCode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAssignCode() {
        return assignCode;
    }

    public void setAssignCode(String assignCode) {
        this.assignCode = assignCode;
    }
}
