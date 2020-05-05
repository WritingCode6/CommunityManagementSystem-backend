package com.writingcode.www.community.service;

import com.writingcode.www.community.entity.po.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.writingcode.www.community.entity.vo.LoginVo;
import com.writingcode.www.community.entity.vo.UserDetailVo;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
public interface IUserService extends IService<User> {

    /**
     * 登录
     *
     * @param userName 账号
     * @param password 密码
     * @return 鉴权jwt
     */
    LoginVo login(String userName, String password);

    /**
     * 登出
     *
     * @param userId 用户id
     */
    void logout(Long userId);

    /**
     * 查看住户的个人信息
     * @param userId 用户id
     * @return UserDetailVo
     */
    UserDetailVo getUserInfo(Long userId);

}
