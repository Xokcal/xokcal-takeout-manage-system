package com.example.cangqiong.Pojo;

import lombok.Data;

@Data
public class LoginGet {
    private String username;
    private String password;

    public LoginGet() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginGet(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
