package com.writingcode.www.community.service.impl;

import com.writingcode.www.community.entity.po.Car;
import com.writingcode.www.community.dao.CarMapper;
import com.writingcode.www.community.service.ICarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements ICarService {
    @Resource
    private CarMapper carMapper;

    public Car selectCarByUserId(Long userId)
    {
        Car car=carMapper.selectCarByUserId(userId);
        Assert.notNull(car, "该用户暂无车辆信息");
        return car;
    }

    public void updateCarById(Long id)
    {
        Car car=carMapper.selectCarByCarId(id);
    }
}
