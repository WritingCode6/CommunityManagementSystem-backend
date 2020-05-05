package com.writingcode.www.community.service;

import com.writingcode.www.community.entity.po.HouseholdInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.writingcode.www.community.entity.vo.HouseUserVo;

import java.util.List;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
public interface IHouseholdInfoService extends IService<HouseholdInfo> {

    /**
     * 查询住户
     * @param name 姓名
     * @param buildingNum 栋数
     * @param roomNum 房间号
     * @return List<HouseUserVo>
     */
    List<HouseUserVo> searchUser(String name, Integer buildingNum, Integer roomNum);
}
