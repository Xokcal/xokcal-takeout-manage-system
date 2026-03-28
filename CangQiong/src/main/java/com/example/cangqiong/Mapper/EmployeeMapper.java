package com.example.cangqiong.Mapper;

import com.example.cangqiong.Pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    //查询用户
    @Select("select id , name , username from employee where username = #{username} and password = #{password}")
    EmployeeLoginBody getEmployeeLoginBody(@Param("username") String username , @Param("password") String password);

    //新增员工
    @Insert("insert into employee (id_number , name , phone , sex , username)" +
            " values (#{idNumber} , #{name} , #{phone} , #{sex} , #{username})")
    Integer addNewEmployee(AddEmployeeRequstBody addEmployeeRequstBody);

    //分页查询员工
    List<EmployeeBody> selectEmployeePage(@Param("start") Integer start
            ,@Param("p") EmployeePageRequestBody employeePageRequestBody);

    //员工总数
    @Select("select count(*) from employee")
    long selectTotal();

    //根据id查询员工
    @Select("select * from employee where id = #{id}")
    EmployeeBody selectEmployeeById(long id);

    //更新员工信息
    @Update("update employee set id_number = #{idNumber},name = #{name}" +
            ",phone = #{phone},sex = #{sex}, username = #{username} where id = #{id}")
    Integer updateEmployee(AddEmployeeRequstBody addEmployeeRequstBody);

    //修改密码
    @Update("update employee set password = #{password} where id = #{id}")
    Integer updatePassword(@Param("password") String password , @Param("id") Integer id);

    //得到原始密码
    @Select("select password from employee where id = #{id}")
    String selectOldPassword(Integer id);

    //更改员工状态
    @Update("update employee set status = #{status} where id = #{id}")
    Integer updateEmployeeStatus(@Param("status") Integer status , @Param("id") Integer id);
}
