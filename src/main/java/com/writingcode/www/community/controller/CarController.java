package com.writingcode.www.community.controller;

import com.writingcode.www.community.entity.po.Car;
import com.writingcode.www.community.entity.vo.LoginVo;
import com.writingcode.www.community.result.CommonResult;
import com.writingcode.www.community.service.ICarService;
import jdk.nashorn.internal.objects.annotations.Getter;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/parking")
public class CarController
{
    @Resource
    private ICarService carService;

    @GetMapping("/getCarInfor")
    public CommonResult<Car> selectCarByUserId(Long userid){
        return new CommonResult<Car>().success(carService.selectCarByUserId(userid));
    }
}
