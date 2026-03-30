package com.example.cangqiong.Service;

import com.example.cangqiong.Pojo.*;

public interface EmployeeService {
    public EmployeeLoginBody employeeLoginBodyMain(LoginGet loginGet);
    public Integer addNewEmployeeMain(AddEmployeeRequstBody addEmployeeRequstBody);
    public EmployeePageResonseBody selectEmployeePageMain(EmployeePageRequestBody employeePageRequestBody);
    public EmployeeBody selectEmployeeByIdMain(long id);
    public Integer updateEmployeeMain(AddEmployeeRequstBody addEmployeeRequstBody);
    public Integer updatePasswordMain(Long id, UpdatePasswordRquestBody updatePasswordRquestBody);
    public Integer updateEmplloyeeStatusMain(Integer status , Integer id);
}
