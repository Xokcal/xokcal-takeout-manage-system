package com.example.cangqiong.Common.Filter;

import com.example.cangqiong.Common.CheckIsValid.CheckIsValidUtil;
import com.example.cangqiong.Common.Exception.BusinessException;
import com.example.cangqiong.Common.Filter.Constant.FilterConstant;
import com.example.cangqiong.Common.Jwt.JwtUtil;
import com.example.cangqiong.Common.ThreadLocal.UserTokenStore;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.logging.LogRecord;

@Slf4j
public class MyFilter implements Filter {

    private JwtUtil jwtUtil;

    public MyFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        log.info(FilterConstant.DO_FILTER_URL_DESC, url);
        if (url.contains("/login") || url.contains("/register")){
            filterChain.doFilter(request , response);
            return;
        }
        String token = request.getHeader("token");
        if (token == null || token.trim().equals("")){
            filterChain.doFilter(request , response);
            return;
        }
        Long id;
        try {
            id = jwtUtil.getTokenId(token);
        }catch (BusinessException e){
            throw new BusinessException(FilterConstant.DO_FILTER_TOKEN_PARSE_ERROR
                    , FilterConstant.CODE_BEHIND);
        }
        UserTokenStore.setUserId(id);
        try {
            filterChain.doFilter(request , response);
        }finally {
            UserTokenStore.removeUserId();
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
