package com.writingcode.www.community.service.impl;

import com.writingcode.www.community.entity.po.Car;
import com.writingcode.www.community.dao.CarMapper;
import com.writingcode.www.community.service.ICarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Objects;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements ICarService {

    @Resource
    private CarMapper carMapper;

    @Override
    public Car selectCarByUserId(Long userId) {
        Assert.notNull(userId, "用户id不能为空");
        return carMapper.selectByUserId(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addCarInfo(Car car) {
        Assert.notNull(car, "车辆不能为空");
        Assert.notNull(car.getUserId(), "用户id不能为空");
        Assert.notNull(car.getPlateNumber(), "车牌号不能为空");
        carMapper.insert(car);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateCarInfo(Car car) {
        Assert.notNull(car, "车辆信息不能为空");
        Assert.notNull(car.getId(), "车辆主键不能为空");
        carMapper.updateById(car);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteCarInfo(Long id) {
        Assert.notNull(id, "id不能为空");
        Assert.state(carMapper.deleteById(id) == 1, "该车辆不存在");
        return true;
    }
}
