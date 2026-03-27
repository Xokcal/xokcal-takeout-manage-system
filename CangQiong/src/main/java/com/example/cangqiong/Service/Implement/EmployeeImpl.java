package com.example.cangqiong.Service.Implement;

import com.example.cangqiong.Common.CheckIsValid.CheckIsValidUtil;
import com.example.cangqiong.Common.Jwt.JwtUtil;
import com.example.cangqiong.Common.Exception.BusinessException;
import com.example.cangqiong.Common.Redis.RedisUtil;
import com.example.cangqiong.Mapper.EmployeeMapper;
import com.example.cangqiong.Pojo.*;
import com.example.cangqiong.Service.Constant.EmployeeConstant;
import com.example.cangqiong.Service.EmployeeService;
import com.example.cangqiong.Service.Redis.EmployeeRedis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class EmployeeImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeRedis employeeRedis;

    @Autowired
    private JwtUtil jwtUtil;

    //员工登录
    @Override
    public EmployeeLoginBody employeeLoginBodyMain(LoginGet loginGet) {
        if (!CheckIsValidUtil.isValid(loginGet)) {
            log.warn(EmployeeConstant.EMPLOYEE_LOGIN_PARAM_ERROR);
            throw new BusinessException(EmployeeConstant.EMPLOYEE_LOGIN_PARAM_ERROR
                    , EmployeeConstant.CODE_FRONT);
        }
        EmployeeLoginBody r = employeeMapper.getEmployeeLoginBody(loginGet.getUsername(), loginGet.getPassword());
        r.setToken(jwtUtil.generateToken(r.getId()));
        if (!CheckIsValidUtil.isValid(r)) {
            log.warn(EmployeeConstant.EMPLOYEE_LOGIN_RESULT_ERROR);
            throw new BusinessException(EmployeeConstant.EMPLOYEE_LOGIN_RESULT_ERROR
                    , EmployeeConstant.CODE_BEHIND);
        }
        return r;
    }

    //新增员工
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addNewEmployeeMain(AddEmployeeRequstBody addEmployeeRequstBody) {
        if (!CheckIsValidUtil.isValid(addEmployeeRequstBody)) {
            log.warn(EmployeeConstant.ADD_EMPLOYEE_PARAM_ERROR);
            throw new BusinessException(EmployeeConstant.ADD_EMPLOYEE_PARAM_ERROR
                    , EmployeeConstant.CODE_FRONT);
        }
        Integer r = employeeMapper.addNewEmployee(addEmployeeRequstBody);
        return r;
    }

    //分页查询员工
    @Override
    public EmployeePageResonseBody selectEmployeePageMain(String name, Integer page, Integer pageSize) {
        if (!CheckIsValidUtil.isValid(page) || !CheckIsValidUtil.isValid(pageSize)) {
            log.warn(EmployeeConstant.SELECT_PAGE_PARAM_ERROR);
            throw new BusinessException(EmployeeConstant.SELECT_PAGE_PARAM_ERROR
                    , EmployeeConstant.CODE_FRONT);
        }
        if (employeeRedis.redisEmployeeIsExists(name , page , pageSize)){
            return (EmployeePageResonseBody) employeeRedis.getRedisEmployeePage(name, page, pageSize);
        }
        return getEmployeePageResonseBody(name, page, pageSize);
    }

    //分页查询：没用缓存情况
    private EmployeePageResonseBody getEmployeePageResonseBody(String name, Integer page, Integer pageSize) {
        Integer start = EmployeeConstant.startPage(page, pageSize);
        List<EmployeeBody> employeeBodies = employeeMapper.selectEmployeePage(name, start, pageSize);
        if (!CheckIsValidUtil.isValid(employeeBodies)) {
            log.warn(EmployeeConstant.SELECT_PAGE_RESULT_ERROR);
            throw new BusinessException(EmployeeConstant.SELECT_PAGE_RESULT_ERROR
                    , EmployeeConstant.CODE_BEHIND);
        }
        EmployeePageResonseBody employeePageResonseBody = new EmployeePageResonseBody(selectTotal(), employeeBodies);
        employeeRedis.putRedisEmployeePage(employeePageResonseBody
                , name, page, pageSize, 10);
        return employeePageResonseBody;
    }

    //根据id查询员工
    @Override
    public EmployeeBody selectEmployeeByIdMain(long id) {
        if (!CheckIsValidUtil.isValid(id)) {
            log.warn(EmployeeConstant.QUERY_EMPLOYEE_BY_ID_PARAM_ERROR);
            throw new BusinessException(EmployeeConstant.QUERY_EMPLOYEE_BY_ID_PARAM_ERROR
                    , EmployeeConstant.CODE_FRONT);
        }
        EmployeeBody target = employeeMapper.selectEmployeeById(id);
        if (!CheckIsValidUtil.isValid(target)) {
            log.warn(EmployeeConstant.QUERY_EMPLOYEE_BY_ID_RESULT_ERROR);
            throw new BusinessException(EmployeeConstant.QUERY_EMPLOYEE_BY_ID_RESULT_ERROR
                    , EmployeeConstant.CODE_BEHIND);
        }
        return target;
    }

    //修改员工信息
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateEmployeeMain(AddEmployeeRequstBody addEmployeeRequstBody) {
        if (!CheckIsValidUtil.isValid(addEmployeeRequstBody)) {
            log.warn(EmployeeConstant.UPDATE_EMPOYEE_PARAM_ERROR);
            throw new BusinessException(EmployeeConstant.UPDATE_EMPOYEE_PARAM_ERROR
                    , EmployeeConstant.CODE_FRONT);
        }
        employeeRedis.deleteAllRedisEmployeePage();
        Integer row = employeeMapper.updateEmployee(addEmployeeRequstBody);
        return row;
    }

    //修改密码
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updatePasswordMain(Integer id, UpdatePasswordRquestBody updatePasswordRquestBody) {
        if (!CheckIsValidUtil.isValid(updatePasswordRquestBody)
                || !CheckIsValidUtil.isValid(id)) {
            log.warn(EmployeeConstant.UPDATE_PASSWORD_PARAM_ERROR);
            throw new BusinessException(EmployeeConstant.UPDATE_PASSWORD_PARAM_ERROR
                    , EmployeeConstant.CODE_FRONT);
        }
        if (!selectOldPasswordMain(id).equals(updatePasswordRquestBody.getOldPassword())) {
            log.warn(EmployeeConstant.UPDATE_PASSWORD_MATCH_ERROR);
            throw new BusinessException(EmployeeConstant.UPDATE_PASSWORD_MATCH_ERROR
                    , EmployeeConstant.CODE_FRONT);
        }
        Integer row = employeeMapper.updatePassword(updatePasswordRquestBody.getNewPassword(), id);
        return row;
    }

    //修改员工状态
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateEmplloyeeStatusMain(Integer status, Integer id) {
        if (!CheckIsValidUtil.isValid(status) || !CheckIsValidUtil.isValid(id)) {
            log.warn(EmployeeConstant.UPDATE_EMPLOYEE_STATUS_PARAM_ERROR);
            throw new BusinessException(EmployeeConstant.UPDATE_EMPLOYEE_STATUS_PARAM_ERROR
                    , EmployeeConstant.CODE_FRONT);
        }
        employeeRedis.deleteAllRedisEmployeePage();
        Integer integer = employeeMapper.updateEmployeeStatus(status, id);
        return integer;
    }

    //得到原始密码
    private String selectOldPasswordMain(Integer id) {
        selectOldPasswordMainValidParam(id);
        String password = employeeMapper.selectOldPassword(id);
        if (!CheckIsValidUtil.isValid(password)) {
            log.warn(EmployeeConstant.QUERY_OLD_PASSWORD_RESULT_ERROR);
            throw new BusinessException(EmployeeConstant.QUERY_OLD_PASSWORD_RESULT_ERROR
                    , EmployeeConstant.CODE_BEHIND);
        }
        return password;
    }

    //参数校验
    private static void selectOldPasswordMainValidParam(Integer id) {
        if (!CheckIsValidUtil.isValid(id)) {
            log.warn(EmployeeConstant.QUERY_OLD_PASSWORD_PARAM_ERROR);
            throw new BusinessException(EmployeeConstant.QUERY_OLD_PASSWORD_PARAM_ERROR
                    , EmployeeConstant.CODE_FRONT);
        }
    }

    //查询总数
    private long selectTotal() {
        long total = employeeMapper.selectTotal();
        if (!CheckIsValidUtil.isValid(total)) {
            log.warn(EmployeeConstant.SELECT_TOTAL_ERROR);
            throw new BusinessException(EmployeeConstant.SELECT_TOTAL_ERROR
                    , EmployeeConstant.CODE_BEHIND);
        }
        return total;
    }


}
