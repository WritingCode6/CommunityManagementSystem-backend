package com.writingcode.www.community.controller;

import com.writingcode.www.community.auth.source.IDataStore;
import com.writingcode.www.community.entity.po.User;
import com.writingcode.www.community.entity.vo.LoginVo;
import com.writingcode.www.community.result.CommonResult;
import com.writingcode.www.community.service.IUserService;
import org.springframework.web.bind.annotation.*;

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
     * @param user 用户名和密码
     * @return CommonResult<LoginVo>
     */
    @PostMapping("/login")
    public CommonResult<LoginVo> login(@RequestBody User user){
        return new CommonResult<LoginVo>().success(userService.login(user));
    }

    @GetMapping("/logout")
    public CommonResult<Void> logout(@RequestParam("userId") Long userId){
        if(dataStore.remove(String.valueOf(userId))){
            return new CommonResult<Void>().success();
        }
        return new CommonResult<Void>().fail();
    }
}
