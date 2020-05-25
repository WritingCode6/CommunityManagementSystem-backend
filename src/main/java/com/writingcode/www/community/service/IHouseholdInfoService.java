package com.writingcode.www.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.writingcode.www.community.entity.po.HouseholdInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.writingcode.www.community.entity.vo.HouseHoldVo;
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
     * @param page 分页bean
     * @return Page<HouseUserVo>
     */
    Page<HouseUserVo> searchUser(String name, Integer buildingNum, Integer roomNum, Page<HouseUserVo> page);

    /**
     * 更新住户信息
     * @param householdInfo 住户信息
     * @return boolean
     */
    boolean updateUserInfo(HouseholdInfo householdInfo);

    /**
     * 增加住户
     * @param houseHoldVo 住户信息
     * @return boolean
     */
    boolean addUser(HouseHoldVo houseHoldVo);
}
