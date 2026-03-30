package com.example.cangqiong.Service.Redis;

import com.example.cangqiong.Common.Annotation.RedisOperate;
import com.example.cangqiong.Common.CheckIsValid.CheckIsValidUtil;
import com.example.cangqiong.Common.Exception.BusinessException;
import com.example.cangqiong.Common.Redis.RedisUtil;
import com.example.cangqiong.Pojo.Category.CategoryPageRequestBody;
import com.example.cangqiong.Pojo.Category.CategoryPageResonseBody;
import com.example.cangqiong.Service.Constant.RedisCategoryConstant;
import com.example.cangqiong.Service.Constant.RedisEmployeeConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.Set;

@Slf4j
@Component
public class CategoryRedis {

    @Autowired
    private RedisUtil redisUtil;

    //分类分页查询储存
    @RedisOperate(api = "分类分页" , summary = "分类分页数据储存到redis")
    public void putCategoryPageToRedis(CategoryPageResonseBody data
            ,CategoryPageRequestBody categoryPageRequestBody , Integer timeOut){
        String key = "category_page:name="+categoryPageRequestBody.getName()
                +"&page="+categoryPageRequestBody.getPage()+"&pageSize="
                +categoryPageRequestBody.getPageSize()+"&type="+categoryPageRequestBody.getType();
        log.info(RedisCategoryConstant.REDIS_PUT_CATEGORY_PAGE_RESULT_SUCCESS,data);
        Integer randomTimeOut = timeOut + new Random().nextInt(4);
        redisUtil.put(key,data,randomTimeOut);
    }

    //从数据库查询分类分页数据
    @RedisOperate(api = "分类分页" , summary = "redis得到分类分页数据")
    public Object getCategoryPageFromRedis(CategoryPageRequestBody categoryPageRequestBody){
        String key = "category_page:name="+categoryPageRequestBody.getName()
                +"&page="+categoryPageRequestBody.getPage()+"&pageSize="
                +categoryPageRequestBody.getPageSize()+"&type="+categoryPageRequestBody.getType();
        Object categoryPageDataAndTotal = redisUtil.get(key);
        if (!CheckIsValidUtil.isValid(categoryPageDataAndTotal)){
            log.warn(RedisCategoryConstant.REDIS_GET_CATEGORY_PAGE_RESULT_ERROR);
            throw new BusinessException(RedisCategoryConstant.REDIS_GET_CATEGORY_PAGE_RESULT_ERROR
                    , RedisCategoryConstant.CODE_BEHIND);
        }
        log.info(RedisCategoryConstant.REDIS_GET_CATEGORY_PAGE_RESULT_SUCCESS,categoryPageDataAndTotal);
        return categoryPageDataAndTotal;
    }

    //判断分类分页是否已经缓存在redis中
    @RedisOperate(api = "分类分页" , summary = "判断分类分页是否已经缓存在redis中")
    public boolean categoryPageIsExist(CategoryPageRequestBody categoryPageRequestBody){
        String key = "category_page:name="+categoryPageRequestBody.getName()
                +"&page="+categoryPageRequestBody.getPage()+"&pageSize="
                +categoryPageRequestBody.getPageSize()+"&type="+categoryPageRequestBody.getType();
        Object employeePageData = redisUtil.get(key);
        return employeePageData == null ? false : true;
    }

    //删除所有分类缓存
    @RedisOperate(api = "分类" , summary = "删除所有分类缓存")
    public void deleteAllRedisCategoryPage(){
        String key = "category_page:*";
        Set<String> keys = redisUtil.keys(key);
        if (keys != null && !keys.isEmpty()){
            redisUtil.delCollection(keys);
            log.info(RedisCategoryConstant.REDIS_DELETE_ALL_CATEGORY_PAGE_SUCCESS);
            return;
        }
        log.warn(RedisCategoryConstant.REDIS_DELETE_ALL_CATEGORY_KEY_ERROR);
    }


}
