package com.writingcode.www.community.controller;

import com.writingcode.www.community.entity.vo.UserDetailVo;
import com.writingcode.www.community.result.CommonResult;
import com.writingcode.www.community.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Chavy
 * @date 2020/5/5 22:28
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private IUserService userService;

    /**
     * 查看住户的个人信息
     * @param userId 用户id
     * @return CommonResult<UserDetailVo>
     */
    @GetMapping("/getUserInfo")
    public CommonResult<UserDetailVo> getUserInfo(@RequestParam("userId") Long userId){
        return new CommonResult<UserDetailVo>().success(userService.getUserInfo(userId));
    }
}
