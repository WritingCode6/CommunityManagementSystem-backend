package com.writingcode.www.community.controller;

import com.writingcode.www.community.entity.vo.StaffVo;
import com.writingcode.www.community.result.CommonResult;
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
}
