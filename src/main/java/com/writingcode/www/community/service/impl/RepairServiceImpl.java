package com.writingcode.www.community.service.impl;

import com.writingcode.www.community.entity.po.Repair;
import com.writingcode.www.community.dao.RepairMapper;
import com.writingcode.www.community.service.IRepairService;
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
public class RepairServiceImpl extends ServiceImpl<RepairMapper, Repair> implements IRepairService {

    @Resource
    private RepairMapper repairMapper;

    @Override
    public boolean addRepair(Repair repair) {
        Assert.notNull(repair, "保修单不能为空");
        Assert.notNull(repair.getReason(), "保修内容不能为空");
        Assert.notNull(repair.getUserId(), "保修用户id不能为空");
        Assert.notNull(repair.getPlace(), "保修地不能为空");

        Assert.state(repairMapper.insert(repair) == 1, "新增失败");
        return true;
    }

    @Override
    public boolean updateRepair(Repair repair) {
        Assert.notNull(repair, "更新内容不能为空");
        Assert.notNull(repair.getId(), "id不能为空");

        Assert.state(repairMapper.updateById(repair) == 1, "更新失败");

        return true;
    }
}
