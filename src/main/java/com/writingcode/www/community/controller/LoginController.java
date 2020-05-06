package com.writingcode.www.community.controller;

import com.writingcode.www.community.auth.source.IDataStore;
import com.writingcode.www.community.entity.vo.LoginVo;
import com.writingcode.www.community.result.CommonResult;
import com.writingcode.www.community.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 * @author Chavy
 * @date 2020/5/3
 */
@RestController
@RequestMapping("/api/user")
public class LoginController {

    @Resource
    private IUserService userService;

    @Resource
    private IDataStore dataStore;

    /**
     * 用户登录方法
     * @param userName 用户名
     * @param password 密码
     * @return CommonResult<LoginVo>
     */
    @PostMapping("/login")
    public CommonResult<LoginVo> login(String userName, String password){
        return new CommonResult<LoginVo>().success(userService.login(userName, password));
    }

    @GetMapping("/logout")
    public CommonResult<Void> logout(Long userId){
        if(dataStore.remove(String.valueOf(userId))){
            return new CommonResult<Void>().success();
        }
        return new CommonResult<Void>().fail();
    }
}
