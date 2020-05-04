package com.writingcode.www.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.writingcode.www.community.entity.po.House;
import com.baomidou.mybatisplus.extension.service.IService;
import com.writingcode.www.community.entity.vo.HouseInfoVo;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
public interface IHouseService extends IService<House> {

    /**
     * 查询房屋信息
     *
     * @param userId 用户id
     * @param buildingNum 栋数
     * @param page 分页bean
     * @return Page<HouseInfoVo>
     */
    Page<HouseInfoVo> getHouseInfo(Long userId, Integer buildingNum, Page<HouseInfoVo> page);

}
