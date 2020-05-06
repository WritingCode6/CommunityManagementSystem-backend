package com.writingcode.www.community.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.writingcode.www.community.entity.po.UserRole;
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
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 根据用户id获取角色id
     * @param userId 用户id
     * @return roleId
     */
    default Integer selectRoleIdByUserId(Long userId){
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(UserRole.USER_ID, userId);
        UserRole userRole = selectOne(queryWrapper);
        if(userRole == null){
            return null;
        }
        return userRole.getRoleId();
    }
}
