package com.example.cangqiong.Service.Redis;

import com.example.cangqiong.Common.Annotation.RedisOperate;
import com.example.cangqiong.Common.CheckIsValid.CheckIsValidUtil;
import com.example.cangqiong.Common.Exception.BusinessException;
import com.example.cangqiong.Common.Redis.RedisUtil;
import com.example.cangqiong.Pojo.EmployeePageRequestBody;
import com.example.cangqiong.Service.Constant.RedisEmployeeConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.Set;

@Slf4j
@Component
public class EmployeeRedis {

    @Autowired
    private RedisUtil redisUtil;

    //reids储存员工分页
    @RedisOperate(api = "员工分页" , summary = "reids储存员工分页")
    public void putRedisEmployeePage(Object data ,EmployeePageRequestBody employeePageRequestBody, Integer timeOut){
        String key = "employee_page:name="+employeePageRequestBody.getName()
                +"&page="+employeePageRequestBody.getPage()+"&pageSize="+employeePageRequestBody.getPageSize();
        log.info(RedisEmployeeConstant.REDIS_PUT_EMPLOYEE_PAGE_RESULT_SUCCESS,data);
        Integer randomTimeOut = timeOut + new Random().nextInt(4);
        redisUtil.put(key,data,randomTimeOut);
    }

    //redis得到员工分页
    @RedisOperate(api = "员工分页" , summary = "redis得到员工分页数据")
    public Object getRedisEmployeePage(EmployeePageRequestBody employeePageRequestBody){
        String key = "employee_page:name="+employeePageRequestBody.getName()
                +"&page="+employeePageRequestBody.getPage()+"&pageSize="+employeePageRequestBody.getPageSize();
        Object employeePageDataAndTotal = redisUtil.get(key);
        if (!CheckIsValidUtil.isValid(employeePageDataAndTotal)){
            log.warn(RedisEmployeeConstant.REDIS_GET_EMPLOYEE_PAGE_RESULT_ERROR);
            throw new BusinessException(RedisEmployeeConstant.REDIS_GET_EMPLOYEE_PAGE_RESULT_ERROR
                    , RedisEmployeeConstant.CODE_BEHIND);
        }
        log.info(RedisEmployeeConstant.REDIS_GET_EMPLOYEE_PAGE_RESULT_SUCCESS,employeePageDataAndTotal);
        return employeePageDataAndTotal;
    }

    //判断查询的员工分页是否存入redis
    @RedisOperate(api = "员工分页" , summary = "判断查询的员工分页是否存入redis")
    public boolean redisEmployeeIsExists(EmployeePageRequestBody employeePageRequestBody){
        String key = "employee_page:name="+employeePageRequestBody.getName()+"&page="
                +employeePageRequestBody.getPage()+"&pageSize="+employeePageRequestBody.getPageSize();
        Object employeePageData = redisUtil.get(key);
        return employeePageData == null ? false : true; //没存false，存了true
    }

    //删除所有员工缓存
    @RedisOperate(api = "员工" , summary = "删除所有员工缓存")
    public void deleteAllRedisEmployeePage(){
        String key = "employee_page:*";
        Set<String> keys = redisUtil.keys(key);
        if (keys != null && !keys.isEmpty()){
            redisUtil.delCollection(keys);
            log.info(RedisEmployeeConstant.REDIS_EMPLOYEE_DELETE_ALL_KEY_SUCCESS);
            return;
        }
        log.warn(RedisEmployeeConstant.REDIS_EMPLOYEE_DELETE_ALL_KEY_ERROR);
    }

}


