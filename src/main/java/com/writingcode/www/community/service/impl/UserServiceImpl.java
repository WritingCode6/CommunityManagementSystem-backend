package com.writingcode.www.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.writingcode.www.community.auth.source.IDataStore;
import com.writingcode.www.community.auth.util.SecurityUtil;
import com.writingcode.www.community.dao.*;
import com.writingcode.www.community.entity.po.*;
import com.writingcode.www.community.entity.vo.*;
import com.writingcode.www.community.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private HouseMapper houseMapper;

    @Resource
    private HouseholdInfoMapper householdInfoMapper;

    @Resource
    private StaffInfoMapper staffInfoMapper;

    @Resource
    private CarMapper carMapper;

    @Override
    public LoginVo login(User user) {
        Assert.notNull(user, "不能为空");
        Assert.notNull(user.getUserName(), "用户名不能为空");
        Assert.notNull(user.getPassword(), "密码不能为空");

        User tempUser = userMapper.selectUserByLogin(user.getUserName(), user.getPassword());
        Assert.notNull(tempUser, "该用户不存在");

        Integer roleId = userRoleMapper.selectRoleIdByUserId(tempUser.getId());
        Assert.notNull(roleId, "系统错误");

        Role role = roleMapper.selectById(roleId);
        Assert.notNull(role, "系统错误");

        String token = securityUtil.login(String.valueOf(tempUser.getId()), null, null);

        return new LoginVo().setRoleId(roleId).setUserId(tempUser.getId()).setToken(token);
    }

    @Override
    public UserDetailVo getUserInfo(Long userId) {
        Assert.notNull(userId, "用户id不能为空");

        User user = userMapper.selectById(userId);

        Assert.notNull(user, "该用户不存在");

        UserDetailVo userDetailVo = new UserDetailVo();

        HouseholdInfo householdInfo = householdInfoMapper.selectByUserId(userId);

        Assert.notNull(householdInfo, "该用户不是住户");

        House house = houseMapper.selectHouseByUserId(userId);

        Car car = carMapper.selectByUserId(userId);

        UserVo userVo = new UserVo();
        userVo.setId(userId)
                .setIdNumber(householdInfo.getIdNumber())
                .setResidenceAddress(householdInfo.getResidenceAddress())
                .setAncestralHome(householdInfo.getAncestralHome())
                .setName(householdInfo.getName())
                .setSex(householdInfo.getSex())
                .setUserName(user.getUserName());
        userDetailVo.setCarInfo(car).setHouseInfo(house).setUserInfo(userVo);
        return userDetailVo;
    }

    @Transactional(rollbackFor = Exception.class)
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addStaff(StaffVo staffVo) {
        Assert.notNull(staffVo, "传入信息不能为null");
        staffVo.isNotNull();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(User.USER_NAME, staffVo.getUserName());
        Assert.state(userMapper.selectOne(queryWrapper) == null, "该用户名已存在");
        Assert.state(userMapper.insert(new User().setUserName(staffVo.getUserName()).setPassword(staffVo.getPassword())) == 1,
                "添加用户失败");

        User user = userMapper.selectOne(queryWrapper);
        userRoleMapper.insert(new UserRole().setUserId(user.getId()).setRoleId(staffVo.getUserType()));

        staffInfoMapper.insert(StaffVo.convert(staffVo).setUserId(user.getId()));
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteUser(Long userId) {
        Assert.notNull(userId, "用户id不能为空");
        userMapper.deleteById(userId);
        return true;
    }
}
