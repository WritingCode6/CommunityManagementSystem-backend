package com.writingcode.www.community.service;

import com.writingcode.www.community.entity.po.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.writingcode.www.community.entity.vo.LoginVo;
import com.writingcode.www.community.entity.vo.StaffVo;
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
     * @param user 账号密码
     * @return 鉴权jwt
     */
    LoginVo login(User user);

    /**
     * 查看住户的个人信息
     * @param userId 用户id
     * @return UserDetailVo
     */
    UserDetailVo getUserInfo(Long userId);

    /**
     * 更新用户信息
     * @param user 用户信息
     * @return boolean
     */
    boolean updateAccount(User user);

    /**
     * 添加工作人员
     * @param staffVo 工作人员信息
     * @return boolean
     */
    boolean addStaff(StaffVo staffVo);

    /**
     * 删除用户
     * @param userId 用户id
     * @return boolean
     */
    boolean deleteUser(Long userId);

}
