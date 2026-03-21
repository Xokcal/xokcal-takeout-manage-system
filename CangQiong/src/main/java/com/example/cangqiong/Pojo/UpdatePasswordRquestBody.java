package com.example.cangqiong.Pojo;

import lombok.Data;


@Data
public class UpdatePasswordRquestBody {
    private Integer empId;
    private String newPassword;
    private String oldPassword;

    public UpdatePasswordRquestBody() {
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public UpdatePasswordRquestBody(Integer empId, String newPassword, String oldPassword) {
        this.empId = empId;
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }
}
