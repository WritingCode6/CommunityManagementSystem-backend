package com.writingcode.www.community.controller;

import com.writingcode.www.community.entity.po.HouseholdInfo;
import com.writingcode.www.community.entity.po.StaffInfo;
import com.writingcode.www.community.entity.po.User;
import com.writingcode.www.community.entity.vo.UserDetailVo;
import com.writingcode.www.community.result.CommonResult;
import com.writingcode.www.community.service.IHouseholdInfoService;
import com.writingcode.www.community.service.IStaffInfoService;
import com.writingcode.www.community.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Chavy
 * @date 2020/5/5 22:28
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private IUserService userService;

    @Resource
    private IStaffInfoService staffInfoService;

    @Resource
    private IHouseholdInfoService householdInfoService;

    /**
     * 查看住户的个人信息
     * @param userId 用户id
     * @return CommonResult<UserDetailVo>
     */
    @GetMapping("/getUserInfo")
    public CommonResult<UserDetailVo> getUserInfo(@RequestParam("userId") Long userId){
        return new CommonResult<UserDetailVo>().success(userService.getUserInfo(userId));
    }

    /**
     * 更新用户账号信息【管理员，用户】
     * @param user 用户
     * @return CommonResult<Void>
     */
    @PostMapping("/updateAccount")
    public CommonResult<Void> updateAccount(@RequestBody User user){
        if(userService.updateAccount(user)){
            return new CommonResult<Void>().success();
        }
        return new CommonResult<Void>().fail();
    }

    /**
     * 获取工作人员详细信息
     * @param userId 用户id
     * @return CommonResult<StaffInfo>
     */
    @GetMapping("/getStaffInfo")
    public CommonResult<List<StaffInfo>> getStaffInfo(@RequestParam(value = "userId", required = false) Long userId){
        return new CommonResult<List<StaffInfo>>().success(staffInfoService.getStaffInfo(userId));
    }

    /**
     * 更新住户信息
     * @param householdInfo 住户信息
     * @return CommonResult<Void>
     */
    @PostMapping("/updateUserInfo")
    public CommonResult<Void> updateUserInfo(@RequestBody HouseholdInfo householdInfo){
        if(householdInfoService.updateUserInfo(householdInfo)){
            return new CommonResult<Void>().success();
        }
        return new CommonResult<Void>().fail();
    }

    /**
     * 更新工作人员信息
     * @param staffInfo 工作人员信息
     * @return CommonResult<Void>
     */
    @PostMapping("/updateStaffInfo")
    public CommonResult<Void> updateStaffInfo(@RequestBody StaffInfo staffInfo){
        if(staffInfoService.updateStaffInfo(staffInfo)){
            return new CommonResult<Void>().success();
        }
        return new CommonResult<Void>().fail();
    }
}
