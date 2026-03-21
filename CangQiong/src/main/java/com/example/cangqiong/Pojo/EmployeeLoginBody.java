package com.example.cangqiong.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class EmployeeLoginBody {
    private Integer id;
    private String name;
    private String token;
    private String userName;

    public EmployeeLoginBody() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public EmployeeLoginBody(Integer id, String name, String token, String userName) {
        this.id = id;
        this.name = name;
        this.token = token;
        this.userName = userName;
    }
}
