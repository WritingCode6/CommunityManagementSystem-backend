package com.writingcode.www.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.writingcode.www.community.dao.UserMapper;
import com.writingcode.www.community.entity.po.StaffInfo;
import com.writingcode.www.community.dao.StaffInfoMapper;
import com.writingcode.www.community.service.IStaffInfoService;
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
public class StaffInfoServiceImpl extends ServiceImpl<StaffInfoMapper, StaffInfo> implements IStaffInfoService {

    @Resource
    private StaffInfoMapper staffInfoMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public StaffInfo getStaffInfo(Long userId) {
        Assert.notNull(userId, "用户id不能为空");
        Assert.notNull(userMapper.selectById(userId), "该用户不存在");

        QueryWrapper<StaffInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StaffInfo.USER_ID, userId);
        StaffInfo staffInfo = staffInfoMapper.selectOne(queryWrapper);

        Assert.notNull(staffInfo, "该用户不是工作人员");
        return staffInfo;
    }

    @Override
    public boolean updateStaffInfo(StaffInfo staffInfo) {
        Assert.notNull(staffInfo, "更新内容不能为空");
        Assert.notNull(staffInfo.getId(), "id不能为空");

        Assert.state(staffInfoMapper.updateById(staffInfo) == 1, "更新失败");
        return true;
    }
}
