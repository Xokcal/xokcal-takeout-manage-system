package com.example.cangqiong.Common.Jwt;

import com.example.cangqiong.Common.CheckIsValid.CheckIsValidUtil;
import com.example.cangqiong.Common.Jwt.Constant.JwtConstant;
import com.example.cangqiong.Common.Exception.BusinessException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

/**
 * JWT工具类：生成、验证、解析token
 */
@Slf4j
@Component
public class JwtUtil {

    // 从配置文件读取密钥（建议至少32位）
    @Value("${jwt.secret:abcdefghijklmnopqrstuvwxyz1234567890abcdef}")
    private String secret;

    // token过期时间（单位：毫秒，这里设为2小时）
    @Value("${jwt.expire:7200000}")
    private long expire;

    /**
     * 生成JWT令牌
     * @param userId 员工ID（自定义载荷，可加更多信息）
     * @return JWT令牌
     */
    public String generateToken(int userId) {
        // 1. 创建密钥（必须足够长，否则报错）
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        // 2. 生成token
        return Jwts.builder()
                // 载荷：存储自定义信息（如用户ID）
                .claim("id", userId)
                // 签名算法+密钥
                .signWith(key)
                // 构建token
                .compact();
    }

    /**
     * 验证token是否合法（过期/签名错误都会抛异常）
     * @param token JWT令牌
     * @return 合法返回true，否则false
     */
    public boolean validateToken(String token) {
        try {
            // 解析token（自动验证签名和过期时间）
            parseToken(token);
            return true;
        } catch (Exception e) {
            // 验证失败（过期/签名错误/格式错误）
            return false;
        }
    }

    /**
     * 解析token，获取载荷中的用户ID
     * @param token JWT令牌
     * @return 员工ID
     */
    public Long getUserIdFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.get("userId", Long.class);
    }

    /**
     * 解析token，获取所有载荷信息
     */
    private Claims parseToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //得到id
    public Long getTokenId(String token){
        if (!CheckIsValidUtil.isValid(token)){
            log.warn(JwtConstant.JWT_GET_ID_PARAM_ERROR);
            throw new BusinessException(JwtConstant.JWT_GET_ID_PARAM_ERROR , 400);
        }
        Claims claims = parseToken(token);
        Integer id = (Integer) claims.get("id");
        Long idL = Long.valueOf(String.valueOf(id));
        if (!CheckIsValidUtil.isValid(idL)){
            log.warn(JwtConstant.JWT_GET_ID_RESULT_ERROR);
            throw new BusinessException(JwtConstant.JWT_GET_ID_RESULT_ERROR , 500);
        }
        return idL;
    }

    public JwtUtil(){}
}
