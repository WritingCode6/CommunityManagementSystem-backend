package com.writingcode.www.community.controller;

import com.writingcode.www.community.entity.vo.HouseHoldVo;
import com.writingcode.www.community.entity.vo.StaffVo;
import com.writingcode.www.community.result.CommonResult;
import com.writingcode.www.community.service.IHouseholdInfoService;
import com.writingcode.www.community.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Chavy
 * @date 2020/5/21
 */
@RestController
@RequestMapping("/api/user")
public class SystemController {

    @Resource
    private IUserService userService;

    @Resource
    private IHouseholdInfoService householdInfoService;


    /**
     * 新增工作人员
     * @param staffVo 工作人员信息
     * @return CommonResult<Void>
     */
    @PostMapping("/addStaff")
    public CommonResult<Void> addStaff(@RequestBody StaffVo staffVo){
        if(userService.addStaff(staffVo)){
            return new CommonResult<Void>().success();
        }
        return new CommonResult<Void>().fail();
    }

    /**
     * 删除用户
     * @param userId 用户id
     * @return CommonResult<Void>
     */
    @GetMapping("/deleteUser")
    public CommonResult<Void> deleteUser(@RequestParam("userId") Long userId){
        if(userService.deleteUser(userId)){
            return new CommonResult<Void>().success();
        }
        return new CommonResult<Void>().fail();
    }

    /**
     * 添加住户
     * @param houseHoldVo 住户信息
     * @return CommonResult<Void>
     */
    @PostMapping("/addUser")
    public CommonResult<Void> addUser(@RequestBody HouseHoldVo houseHoldVo){
        if(householdInfoService.addUser(houseHoldVo)){
            return new CommonResult<Void>().success();
        }
        return new CommonResult<Void>().fail();
    }
}
