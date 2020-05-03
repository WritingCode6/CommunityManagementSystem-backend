package com.writingcode.www.community.auth.source.impl;

import com.writingcode.www.community.auth.source.IDataStore;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 用 Map 模拟 Redis 进行鉴权信息缓存
 *
 * @author Chavy
 * @date 2020/5/3
 */
@Component
public class MapDataStore implements IDataStore {

    private static final Map<String, String> CACHE_MAP = new HashMap<>();

    @Override
    public boolean put(String key, String value, long expire) {
        CACHE_MAP.put(key, value);
        return true;
    }

    @Override
    public boolean remove(String key) {
        return CACHE_MAP.remove(key) != null;
    }

    @Override
    public boolean refresh(String key, long expire) {
        return false;
    }

    @Override
    public String get(String key) {
        return CACHE_MAP.get(key);
    }
}
