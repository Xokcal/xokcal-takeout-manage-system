package com.example.cangqiong.Common.GlobalExceptionHander;

import com.example.cangqiong.Common.Exception.BusinessException;
import com.example.cangqiong.Common.GlobalExceptionHander.Constant.GlobalExceptionHanderConstant;
import com.example.cangqiong.Pojo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHander {

    //businessException报错处理
    @ExceptionHandler(BusinessException.class)
    public R businessException(BusinessException e){
        log.warn(GlobalExceptionHanderConstant.BUSINESS_EXCEPTION_ERROR + e);
        return new R().error(e);
    }
}
