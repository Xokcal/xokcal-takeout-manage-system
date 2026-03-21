package com.example.cangqiong.Pojo;

import lombok.Data;

@Data
public class AddEmployeeRequstBody {
    private Long id;
    private String idNumber;
    private String name;
    private String phone;
    private String sex;
    private String username;

    public AddEmployeeRequstBody() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AddEmployeeRequstBody(Long id, String idNumber, String name, String phone, String sex, String username) {
        this.id = id;
        this.idNumber = idNumber;
        this.name = name;
        this.phone = phone;
        this.sex = sex;
        this.username = username;
    }
}
