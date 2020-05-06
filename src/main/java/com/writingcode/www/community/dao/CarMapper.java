package com.writingcode.www.community.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.writingcode.www.community.entity.po.Car;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.util.Assert;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-05-02
 */
@Mapper
public interface CarMapper extends BaseMapper<Car> {

    /**
     * 根据用户id搜索
     * @param userId 用户id
     * @return Car
     */
    default Car selectByUserId(Long userId){
        Assert.notNull(userId, "用户id不能为空");
        QueryWrapper<Car> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Car.USER_ID, userId);
        return selectOne(queryWrapper);
    }
}
