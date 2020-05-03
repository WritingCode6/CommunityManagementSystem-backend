package com.writingcode.www.community.auth.source;

/**
 * @author Chavy
 * @date 2020/4/13
 */
public interface IDataStore {

    /**
     * 存储数据
     *
     * @param key    数据Key
     * @param value  数据内容
     * @param expire 过期时间（毫秒单位）
     * @return 存储是否成功
     */
    boolean put(String key, String value, long expire);

    /**
     * 删除数据
     *
     * @param key 数据Key
     * @return 删除是否成功
     */
    boolean remove(String key);

    /**
     * 更新数据时间
     *
     * @param key    数据Key
     * @param expire 过期时间（毫秒单位）
     * @return 更新是否成功
     */
    boolean refresh(String key, long expire);

    /**
     * 读取数据
     *
     * @param key 数据Key
     * @return 读取结果
     */
    String get(String key);
}
