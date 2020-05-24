package com.writingcode.www.community.service;

import com.writingcode.www.community.entity.po.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ICarServiceTest {

    @Resource
    private ICarService carService;

    @Transactional
    @Rollback
    @Test
    void selectCarByUserId() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> carService.selectCarByUserId(null));
        Assertions.assertNotNull(carService.selectCarByUserId(1L));
    }

    @Transactional
    @Rollback
    @Test
    void addCarInfo() {
        Car car = new Car();
        Assertions.assertThrows(IllegalArgumentException.class, () -> carService.addCarInfo(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> carService.addCarInfo(car));
        Assertions.assertThrows(IllegalArgumentException.class, () -> carService.addCarInfo(car.setUserId(1L)));
        Assertions.assertTrue(carService.addCarInfo(car.setPlateNumber("123")));
    }

    @Transactional
    @Rollback
    @Test
    void updateCarInfo() {
        Car car = new Car();
        Assertions.assertThrows(IllegalArgumentException.class, () -> carService.updateCarInfo(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> carService.updateCarInfo(car));
        Assertions.assertTrue(carService.updateCarInfo(car.setId(1L).setBrand("不知道")));
    }

    @Transactional
    @Rollback
    @Test
    void deleteCarInfo() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> carService.deleteCarInfo(null));
        Assertions.assertTrue(carService.deleteCarInfo(1L));
    }
}