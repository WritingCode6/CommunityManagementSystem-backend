package com.writingcode.www.community.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.writingcode.www.community.entity.po.StaffInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-05-02
 */
public interface StaffInfoMapper extends BaseMapper<StaffInfo> {

    /**
     * 判断工作人员是否存在
     * @param userId 用户id
     * @return boolean
     */
    default boolean selectStaffExist(Long userId){
        QueryWrapper<StaffInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StaffInfo.USER_ID, userId);
        return selectOne(queryWrapper) != null;
    }

}
