package com.writingcode.www.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.writingcode.www.community.dao.StaffInfoMapper;
import com.writingcode.www.community.entity.po.DailyDuty;
import com.writingcode.www.community.dao.DailyDutyMapper;
import com.writingcode.www.community.entity.po.StaffInfo;
import com.writingcode.www.community.service.IDailyDutyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
@Service
public class DailyDutyServiceImpl extends ServiceImpl<DailyDutyMapper, DailyDuty> implements IDailyDutyService {

    @Resource
    private DailyDutyMapper dailyDutyMapper;

    @Resource
    private StaffInfoMapper staffInfoMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteDuty(List<Long> ids) {
        Assert.state(Objects.nonNull(ids), "删除主键id不能为空");
        Assert.state(dailyDutyMapper.deleteBatchIds(ids) == ids.size(), "删除失败");
        return true;
    }

    @Override
    public boolean addDutyForm(DailyDuty dailyDuty) {
        Assert.notNull(dailyDuty, "值班信息不能为空");
        Assert.notNull(dailyDuty.getEmployeeId(), "工作人员id不能为空");
        Assert.notNull(dailyDuty.getDate(), "值班时间不能为空");
        Assert.notNull(dailyDuty.getPlace(), "值班区域不能为空");

        Assert.state(staffInfoMapper.selectStaffExist(dailyDuty.getEmployeeId()), "用户不存在或不是工作人员");

        Assert.state(dailyDutyMapper.insert(dailyDuty) == 1, "新增失败");
        return true;
    }
}
