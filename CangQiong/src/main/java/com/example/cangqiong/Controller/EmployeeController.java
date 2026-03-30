package com.example.cangqiong.Controller;

import com.example.cangqiong.Common.Annotation.Operation;
import com.example.cangqiong.Common.CheckIsValid.CheckIsValidUtil;
import com.example.cangqiong.Common.Jwt.JwtUtil;
import com.example.cangqiong.Common.ThreadLocal.UserTokenStore;
import com.example.cangqiong.Pojo.*;
import com.example.cangqiong.Service.Implement.EmployeeImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin")
public class EmployeeController {

    @Autowired
    private EmployeeImpl employee;

    private static UserTokenStore userTokenStore;

    //员工登录
    @PostMapping("/employee/login")
    @Operation(summary = "登录" , description = "员工登录处理")
    R employeeLoginHander(@RequestBody LoginGet loginGet){
        EmployeeLoginBody r = employee.employeeLoginBodyMain(loginGet);
        return new R().ok(r);
    }

    //新增员工
    @PostMapping("/employee")
    @Operation(summary = "新增" , description = "新增员工")
    R addNewEmployee(@RequestBody AddEmployeeRequstBody addEmployeeRequstBody){
        Integer r = employee.addNewEmployeeMain(addEmployeeRequstBody);
        return new R().ok(r);
    }

    //分页查询员工信息
    @GetMapping("/employee/page")
    @Operation(summary = "分页查询" , description = "分页查询员工信息")
    R selectEmployeePage(EmployeePageRequestBody employeePageRequestBody){
        EmployeePageResonseBody r = employee.selectEmployeePageMain(employeePageRequestBody);
        return new R().ok(r);
    }

    //根据id查询员工
    @GetMapping("/employee/{id}")
    @Operation(summary = "根据id查询" , description = "根据id查询员工")
    R selectEmployeeById(@PathVariable("id") long id){
        EmployeeBody target = employee.selectEmployeeByIdMain(id);
        return new R().ok(target);
    }

    //修改员工信息
    @PutMapping("/employee")
    @Operation(summary = "修改" , description = "修改员工信息")
    R updateEmployee(@RequestBody AddEmployeeRequstBody addEmployeeRequstBody){
        Integer r = employee.updateEmployeeMain(addEmployeeRequstBody);
        return new R().ok(r);
    }

    //修改密码
    @PutMapping("/employee/editPassword")
    @Operation(summary = "修改" , description = "修改密码")
    R updatePassword(@RequestBody UpdatePasswordRquestBody updatePasswordRquestBody){
        Long id = userTokenStore.getUserId();
        Integer row = employee.updatePasswordMain(id,updatePasswordRquestBody);
        return new R().ok(row);
    }

    //修改员工状态
    @PostMapping("/employee/status/{status}")
    @Operation(summary = "修改" , description = "修改员工状态")
    R updateEmployeeStatus(@PathVariable("status") Integer status , @RequestParam("id") Integer id){
        Integer r = employee.updateEmplloyeeStatusMain(status, id);
        return new R().ok(r);
    }

    //退出登录
    @PostMapping("/employee/logout")
    @Operation(summary = "输出ok" , description = "退出登录")
    R employeeLogout(){
        log.info("退出登录！！");
        return new R().ok();
    }


}


