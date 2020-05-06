package com.writingcode.www.community.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.writingcode.www.community.entity.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-05-02
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据登录信息搜索用户
     *
     * @param userName 用户名
     * @param password 密码
     * @return User
     */
    default User selectUserByLogin(String userName, String password){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(User.USER_NAME, userName);
        queryWrapper.eq(User.PASSWORD, password);
        return selectOne(queryWrapper);
    }

}
