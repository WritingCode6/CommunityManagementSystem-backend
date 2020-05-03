package com.writingcode.www.community.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.writingcode.www.community.entity.po.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-05-02
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 根据用户id获取角色id
     * @param userId 用户id
     * @return roleId
     */
    default Integer selectRoleIdByUserId(Long userId){
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(UserRole.USER_ID, userId);
        return selectOne(queryWrapper).getRoleId();
    }
}
