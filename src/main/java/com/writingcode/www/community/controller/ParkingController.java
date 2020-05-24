package com.writingcode.www.community.controller;

import com.writingcode.www.community.entity.po.Car;
import com.writingcode.www.community.result.CommonResult;
import com.writingcode.www.community.service.ICarService;
import com.writingcode.www.community.service.IParkingSpaceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author Chavy
 */
@RestController
@RequestMapping("/api/parking")
public class ParkingController {

    @Resource
    private ICarService carService;

    @Resource
    private IParkingSpaceService parkingSpaceService;

    /**
     * 查看车辆资料
     * @param userId 用户id
     * @return CommonResult<Car>
     */
    @GetMapping("/getCarInfor")
    public CommonResult<Car> getCarInfor(@RequestParam("userId") Long userId){
        return new CommonResult<Car>().success(carService.selectCarByUserId(userId));
    }

    /**
     * 批量增加车位
     * @param spaceNumber 车位号
     * @return CommonResult<Void>
     */
    @PostMapping("/addParking")
    public CommonResult<Void> addParking(@RequestBody List<Integer> spaceNumber){
        parkingSpaceService.addParking(spaceNumber);
        return new CommonResult<Void>().success();
    }

    /**
     * 批量删除车位
     * @param ids 车位号
     * @return CommonResult<Void>
     */
    @PostMapping("/deleteParking")
    public CommonResult<Void> deleteParking(@RequestBody List<Integer> ids){
        parkingSpaceService.deleteParking(ids);
        return new CommonResult<Void>().success();
    }
}
