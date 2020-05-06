package com.writingcode.www.community.dao;

import com.writingcode.www.community.entity.po.House;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
public interface HouseMapper extends BaseMapper<House> {

    /**
     * 根据用户id搜索房子
     * @param userId 用户id
     * @return House
     */
    House selectHouseByUserId(@Param("userId") Long userId);

}
