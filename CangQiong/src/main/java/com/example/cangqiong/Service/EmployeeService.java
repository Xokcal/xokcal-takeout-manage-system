package com.example.cangqiong.Service;

import com.example.cangqiong.Pojo.*;

public interface EmployeeService {
    public EmployeeLoginBody employeeLoginBodyMain(LoginGet loginGet);
    public Integer addNewEmployeeMain(AddEmployeeRequstBody addEmployeeRequstBody);
    public EmployeePageResonseBody selectEmployeePageMain(String name , Integer page , Integer pageSize);
    public EmployeeBody selectEmployeeByIdMain(long id);
    public Integer updateEmployeeMain(AddEmployeeRequstBody addEmployeeRequstBody);
    public Integer updatePasswordMain(Integer id , UpdatePasswordRquestBody updatePasswordRquestBody);
    public Integer updateEmplloyeeStatusMain(Integer status , Integer id);
}
