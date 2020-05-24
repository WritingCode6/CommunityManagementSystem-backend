package com.writingcode.www.community.service;

import com.writingcode.www.community.entity.po.Car;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
public interface ICarService extends IService<Car> {

    /**
     * 查询车辆信息
     * @param userId 用户id
     * @return Car
     */
    Car selectCarByUserId(Long userId);

    /**
     * 添加车辆资料
     * @param car 车辆
     * @return boolean
     */
    boolean addCarInfo(Car car);

    /**
     * 修改车辆信息
     * @param car 车辆资料
     * @return boolean
     */
    boolean updateCarInfo(Car car);

    /**
     * 删除车辆
     * @param id 主键id
     * @return boolean
     */
    boolean deleteCarInfo(Long id);
}
