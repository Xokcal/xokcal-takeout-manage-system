package com.example.cangqiong.Controller;

import com.example.cangqiong.Common.Jwt.JwtUtil;
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

    @Autowired
    private JwtUtil jwtUtil;

    //员工登录
    @PostMapping("/employee/login")
    R employeeLoginHander(@RequestBody LoginGet loginGet){
        EmployeeLoginBody r = employee.employeeLoginBodyMain(loginGet);
        return new R().ok(r);
    }

    //新增员工
    @PostMapping("/employee")
    R addNewEmployee(@RequestBody AddEmployeeRequstBody addEmployeeRequstBody){
        Integer r = employee.addNewEmployeeMain(addEmployeeRequstBody);
        return new R().ok(r);
    }

    //分页查询员工信息
    @GetMapping("/employee/page")
    R selectEmployeePage(@RequestParam(value = "name" , required = false) String name
            , @RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize){
        EmployeePageResonseBody r = employee.selectEmployeePageMain(name, page, pageSize);
        return new R().ok(r);
    }

    //根据id查询员工
    @GetMapping("/employee/{id}")
    R selectEmployeeById(@PathVariable("id") long id){
        EmployeeBody target = employee.selectEmployeeByIdMain(id);
        return new R().ok(target);
    }

    //修改员工信息
    @PutMapping("/employee")
    R updateEmployee(@RequestBody AddEmployeeRequstBody addEmployeeRequstBody){
        Integer r = employee.updateEmployeeMain(addEmployeeRequstBody);
        return new R().ok(r);
    }

    //修改密码
    @PutMapping("/employee/editPassword")
    R updatePassword(@RequestHeader("token") String token ,
                     @RequestBody UpdatePasswordRquestBody updatePasswordRquestBody){
        Integer tokenId = jwtUtil.getTokenId(token);
        Integer row = employee.updatePasswordMain(tokenId,updatePasswordRquestBody);
        return new R().ok(row);
    }

    //修改员工状态
    @PostMapping("/employee/status/{status}")
    R updateEmployeeStatus(@PathVariable("status") Integer status , @RequestParam("id") Integer id){
        Integer r = employee.updateEmplloyeeStatusMain(status, id);
        return new R().ok(r);
    }

    //退出登录
    @PostMapping("/employee/logout")
    R employeeLogout(){
        log.info("退出登录！！");
        return new R().ok();
    }


}
