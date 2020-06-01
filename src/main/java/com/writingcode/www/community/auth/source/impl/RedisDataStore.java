package com.writingcode.www.community.auth.source.impl;

import com.writingcode.www.community.auth.source.IDataStore;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author Chavy
 * @date 2020/4/13
 */
@Component
public class RedisDataStore implements IDataStore {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean put(String key, String value, long expire) {
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.MILLISECONDS);
        return true;
    }

    @Override
    public boolean remove(String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    @Override
    public boolean refresh(String key, long expire) {
        return Boolean.TRUE.equals(redisTemplate.expire(key, expire, TimeUnit.MILLISECONDS));
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
