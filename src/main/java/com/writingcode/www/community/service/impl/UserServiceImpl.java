package com.writingcode.www.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.writingcode.www.community.auth.source.IDataStore;
import com.writingcode.www.community.auth.util.SecurityUtil;
import com.writingcode.www.community.dao.*;
import com.writingcode.www.community.entity.po.*;
import com.writingcode.www.community.entity.vo.LoginVo;
import com.writingcode.www.community.entity.vo.UserDetailVo;
import com.writingcode.www.community.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private SecurityUtil securityUtil;

    @Resource
    private IDataStore dataStore;

    @Resource
    private HouseMapper houseMapper;

    @Resource
    private HouseholdInfoMapper householdInfoMapper;

    @Resource
    private CarMapper carMapper;

    @Override
    public LoginVo login(String userName, String password) {
        Assert.notNull(userName, "用户名不能为空");
        Assert.notNull(password, "密码不能为空");

        User user = userMapper.selectUserByLogin(userName, password);
        Assert.notNull(user, "该用户不存在");

        Integer roleId = userRoleMapper.selectRoleIdByUserId(user.getId());
        Assert.notNull(roleId, "系统错误");

        Role role = roleMapper.selectById(roleId);
        Assert.notNull(role, "系统错误");

        String token = securityUtil.login(String.valueOf(user.getId()), null, null);

        return new LoginVo().setRoleId(roleId).setUserId(user.getId()).setToken(token);
    }

    @Override
    public void logout(Long userId) {
        Assert.notNull(userId, "用户id不能为空");
        dataStore.remove(String.valueOf(userId));
    }

    @Override
    public UserDetailVo getUserInfo(Long userId) {
        Assert.notNull(userId, "用户id不能为空");

        Assert.notNull(userMapper.selectById(userId), "该用户不存在");

        UserDetailVo userDetailVo = new UserDetailVo();

        HouseholdInfo householdInfo = householdInfoMapper.selectByUserId(userId);

        Assert.notNull(householdInfo, "该用户不是住户");

        House house = houseMapper.selectHouseByUserId(userId);

        Car car = carMapper.selectByUserId(userId);

        userDetailVo.setCarInfo(car).setHouseInfo(house).setUserInfo(householdInfo);
        return userDetailVo;
    }

    @Override
    public boolean updateAccount(User user) {
        Assert.notNull(user, "更新内容不能为空");
        Assert.notNull(user.getId(), "用户id不能为空");

        if(user.getUserName() != null){
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(User.USER_NAME, user.getUserName());
            Assert.state(userMapper.selectOne(queryWrapper) == null, "用户名已存在");
        }

        Assert.state(userMapper.updateById(user) == 1, "更新失败");
        return true;
    }
}
