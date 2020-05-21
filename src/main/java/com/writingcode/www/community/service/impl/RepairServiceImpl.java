package com.writingcode.www.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.writingcode.www.community.dao.*;
import com.writingcode.www.community.entity.po.HouseholdInfo;
import com.writingcode.www.community.entity.po.Repair;
import com.writingcode.www.community.entity.po.StaffInfo;
import com.writingcode.www.community.entity.po.UserRole;
import com.writingcode.www.community.entity.vo.RepairVo;
import com.writingcode.www.community.service.IRepairService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
@Service
public class RepairServiceImpl extends ServiceImpl<RepairMapper, Repair> implements IRepairService {

    @Resource
    private RepairMapper repairMapper;

    @Resource
    private HouseholdInfoMapper householdInfoMapper;

    @Resource
    private StaffInfoMapper staffInfoMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addRepair(Repair repair) {
        Assert.notNull(repair, "保修单不能为空");
        Assert.notNull(repair.getReason(), "保修内容不能为空");
        Assert.notNull(repair.getUserId(), "保修用户id不能为空");
        Assert.notNull(repair.getPlace(), "保修地不能为空");

        Assert.notNull(userMapper.selectById(repair.getUserId()), "该用户不存在");
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(UserRole.USER_ID, repair.getUserId());
        Assert.state(userRoleMapper.selectOne(queryWrapper).getRoleId() == 2, "该用户不是住户");

        Assert.state(repairMapper.insert(repair) == 1, "新增失败");
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateRepair(Repair repair) {
        Assert.notNull(repair, "更新内容不能为空");
        Assert.notNull(repair.getId(), "id不能为空");

        Assert.state(repairMapper.updateById(repair) == 1, "更新失败");

        return true;
    }

    @Override
    public Page<RepairVo> getRepair(Long userId, Page<RepairVo> page) {
        QueryWrapper<Repair> queryWrapper = new QueryWrapper<>();
        if(userId != null) {
            queryWrapper.eq(Repair.USER_ID, userId);
        }

        Page<Repair> repairPage = new Page<>(page.getCurrent(), page.getSize());
        repairPage = repairMapper.selectPage(repairPage, queryWrapper);

        QueryWrapper<HouseholdInfo> householdInfoQueryWrapper;
        QueryWrapper<StaffInfo> staffInfoQueryWrapper;
        List<RepairVo> repairVos = new LinkedList<>();
        if(repairPage.getRecords() != null){
            for(Repair repair : repairPage.getRecords()){

                RepairVo repairVo = RepairVo.convert(repair);

                householdInfoQueryWrapper = new QueryWrapper<>();
                householdInfoQueryWrapper.eq(HouseholdInfo.USER_ID, repair.getUserId());
                HouseholdInfo householdInfo = householdInfoMapper.selectOne(householdInfoQueryWrapper);
                repairVo.setUserName(householdInfo.getName());

                if(repair.getEmployeeId() != null){
                    staffInfoQueryWrapper = new QueryWrapper<>();
                    staffInfoQueryWrapper.eq(StaffInfo.USER_ID, repair.getEmployeeId());
                    StaffInfo staffInfo = staffInfoMapper.selectOne(staffInfoQueryWrapper);

                    repairVo.setEmployeeName(staffInfo.getName()).setEmployeePhone(staffInfo.getPhone());
                }
                repairVos.add(repairVo);
            }
        }

        page.setTotal(repairPage.getTotal());
        page.setRecords(repairVos);
        return page;
    }
}
