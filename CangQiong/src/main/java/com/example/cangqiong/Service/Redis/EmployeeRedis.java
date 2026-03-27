package com.example.cangqiong.Service.Redis;

import com.example.cangqiong.Common.CheckIsValid.CheckIsValidUtil;
import com.example.cangqiong.Common.Exception.BusinessException;
import com.example.cangqiong.Common.Redis.RedisUtil;
import com.example.cangqiong.Mapper.EmployeeMapper;
import com.example.cangqiong.Pojo.EmployeeBody;
import com.example.cangqiong.Pojo.EmployeePageResonseBody;
import com.example.cangqiong.Service.RedisConstant.EmployeeRedisConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.Set;

@Slf4j
@Component
public class EmployeeRedis {

    @Autowired
    private RedisUtil redisUtil;

    //reids储存员工分页
    public void putRedisEmployeePage(Object data ,String name , Integer page , Integer pageSize , Integer timeOut){
        String key = "employee_page:name="+name+"&page="+page+"&pageSize="+pageSize;
        log.info(EmployeeRedisConstant.REDIS_PUT_EMPLOYEE_PAGE_RESULT_SUCCESS,data);
        Integer randomTimeOut = timeOut + new Random().nextInt(4);
        redisUtil.put(key,data,randomTimeOut);
    }

    //redis得到员工分页
    public Object getRedisEmployeePage(String name , Integer page , Integer pageSize){
        String key = "employee_page:name="+name+"&page="+page+"&pageSize="+pageSize;
        Object employeePageDataAndTotal = redisUtil.get(key);
        if (!CheckIsValidUtil.isValid(employeePageDataAndTotal)){
            log.warn(EmployeeRedisConstant.REDIS_GET_EMPLOYEE_PAGE_RESULT_ERROR);
            throw new BusinessException(EmployeeRedisConstant.REDIS_GET_EMPLOYEE_PAGE_RESULT_ERROR
                    , EmployeeRedisConstant.CODE_BEHIND);
        }
        log.info(EmployeeRedisConstant.REDIS_GET_EMPLOYEE_PAGE_RESULT_SUCCESS,employeePageDataAndTotal);
        return employeePageDataAndTotal;
    }

    //判断查询的员工分页是否存入redis
    public boolean redisEmployeeIsExists(String name , Integer page , Integer pageSize){
        String key = "employee_page:name="+name+"&page="+page+"&pageSize="+pageSize;
        Object employeePageData = redisUtil.get(key);
        return employeePageData == null ? false : true; //没存false，存了true
    }

    //删除所有员工缓存
    public void deleteAllRedisEmployeePage(){
        String key = "employee_page:*";
        Set<String> keys = redisUtil.keys(key);
        if (keys != null && !keys.isEmpty()){
            redisUtil.delCollection(keys);
            log.info(EmployeeRedisConstant.REDIS_EMPLOYEE_DELETE_ALL_KEY_SUCCESS);
            return;
        }
        log.warn(EmployeeRedisConstant.REDIS_EMPLOYEE_DELETE_ALL_KEY_ERROR);
    }

}


