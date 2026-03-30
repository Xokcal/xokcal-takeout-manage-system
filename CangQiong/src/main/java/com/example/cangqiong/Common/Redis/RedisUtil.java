package com.example.cangqiong.Common.Redis;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Resource
    RedisTemplate<String , Object> redisTemplate;

    //储存（永久）
    public void put(String key , Object value , Integer timeOut){
        redisTemplate.opsForValue().set(key , value , timeOut , TimeUnit.MINUTES);
    }

    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public void del(String key){
        redisTemplate.delete(key);
    }

    public Set<String> keys(String key){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate.keys(key);
    }

    public void delCollection(Set<String> keys){
        redisTemplate.delete(keys);
    }
}
