package com.writingcode.www.community.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.writingcode.www.community.entity.dto.HouseholdPageDto;
import com.writingcode.www.community.entity.po.HouseholdInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.writingcode.www.community.entity.vo.HouseUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.Assert;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-05-02
 */
@Mapper
public interface HouseholdInfoMapper extends BaseMapper<HouseholdInfo> {


    /**
     * 根据用户id搜索信息
     * @param userId 用户id
     * @return HouseholdInfo
     */
    default HouseholdInfo selectByUserId(Long userId){
        Assert.notNull(userId, "用户id不能为空");
        QueryWrapper<HouseholdInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(HouseholdInfo.USER_ID, userId);
        return selectOne(queryWrapper);
    }

    /**
     * 分页查询住户
     * @param householdPageDto 条件
     * @return List<HouseUserVo>
     */
    List<HouseUserVo> selectHouseUser(@Param("householdPageDto") HouseholdPageDto householdPageDto);

    /**
     * 查询总数
     * @param householdPageDto 条件
     * @return 总数
     */
    Integer selectHouseUserCount(@Param("householdPageDto") HouseholdPageDto householdPageDto);
}
