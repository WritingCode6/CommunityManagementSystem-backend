package com.writingcode.www.community.service;

import com.writingcode.www.community.entity.po.ParkingSpace;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
public interface IParkingSpaceService extends IService<ParkingSpace> {


    /**
     * 查看车位信息
     * @return List<ParkingSpace>
     */
    List<ParkingSpace> getParkingInfo();

    /**
     * 批量增加车位
     * @param spaceNumber 车位号
     */
    void addParking(List<Integer> spaceNumber);

    /**
     * 批量删除车位
     * @param ids id
     */
    void deleteParking(List<Integer> ids);
}
