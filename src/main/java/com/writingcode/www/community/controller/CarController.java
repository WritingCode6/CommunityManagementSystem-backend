package com.writingcode.www.community.controller;

import com.writingcode.www.community.entity.po.Car;
import com.writingcode.www.community.entity.po.ParkingSpace;
import com.writingcode.www.community.result.CommonResult;
import com.writingcode.www.community.service.ICarService;
import com.writingcode.www.community.service.IParkingSpaceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Laa
 */
@RestController
@RequestMapping("/api/car")
public class CarController {

    @Resource
    private ICarService carService;

    @Resource
    private IParkingSpaceService parkingSpaceService;

    /**
     * 增加车辆信息
     * @param car 车辆
     * @return CommonResult<Void>
     */
    @PostMapping("/addCarInfo")
    public CommonResult<Void> addCarInfo(@RequestBody Car car){
        if(carService.addCarInfo(car)){
            return new CommonResult<Void>().success();
        }else {
            return new CommonResult<Void>().fail();
        }
    }

    /**
     * 获取车位信息
     * @return CommonResult<List<ParkingSpace>>
     */
    @GetMapping("/getParkingInfo")
    public CommonResult<List<ParkingSpace>> getParkingInfo(){
        return new CommonResult<List<ParkingSpace>>().success(parkingSpaceService.getParkingInfo());
    }

    /**
     * 修改车辆信息
     * @param car 车辆
     * @return CommonResult<Void>
     */
    @PostMapping("/updateCarInfo")
    public CommonResult<Void> updateCarInfo(@RequestBody Car car){
        if(carService.updateCarInfo(car)){
            return new CommonResult<Void>().success();
        }else {
            return new CommonResult<Void>().fail();
        }
    }

    /**
     * 删除车辆
     * @param id id
     * @return CommonResult<Void>
     */
    @GetMapping("/deleteCarInfo")
    public CommonResult<Void> deleteCarInfo(@RequestParam("id") Long id){
        if(carService.deleteCarInfo(id)){
            return new CommonResult<Void>().success();
        }else {
            return new CommonResult<Void>().fail();
        }
    }
}
