package com.writingcode.www.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.writingcode.www.community.dao.StaffInfoMapper;
import com.writingcode.www.community.entity.po.DailyDuty;
import com.writingcode.www.community.dao.DailyDutyMapper;
import com.writingcode.www.community.entity.po.StaffInfo;
import com.writingcode.www.community.entity.vo.DutyFromVo;
import com.writingcode.www.community.entity.vo.HouseInfoVo;
import com.writingcode.www.community.service.IDailyDutyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
        Assert.notNull(ids, "删除主键id不能为空");
        Assert.state(!ids.isEmpty(), "删除主键id不能为空");
        Assert.state(dailyDutyMapper.deleteBatchIds(ids) == ids.size(), "删除失败");
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
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

    @Override
    public Page<DutyFromVo> getDutyInfo(LocalDate date, Integer type, Page<DutyFromVo> page) {
        QueryWrapper<DailyDuty> dailyDutyQueryWrapper = new QueryWrapper<>();
        if(date != null){
            dailyDutyQueryWrapper.eq(DailyDuty.DATE, date);
        }
        if(type != null){
            dailyDutyQueryWrapper.eq(DailyDuty.TYPE, type);
        }
        Page<DailyDuty> dailyDutyPage = new Page<>(page.getCurrent(), page.getSize());
        dailyDutyPage = dailyDutyMapper.selectPage(dailyDutyPage, dailyDutyQueryWrapper);

        //获取用户id
        List<Long> userId = dailyDutyPage.getRecords().stream().map(DailyDuty::getEmployeeId).collect(Collectors.toList());

        if(!userId.isEmpty()) {
            QueryWrapper<StaffInfo> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.in(StaffInfo.USER_ID, userId);
            Map<Long, String> userNames = staffInfoMapper.selectList(queryWrapper1).stream().collect(Collectors.toMap(StaffInfo::getUserId, StaffInfo::getName));

            //设置name
            page.setRecords(dailyDutyPage.getRecords().stream().map(dailyDuty -> {
                DutyFromVo dutyFromVo = DutyFromVo.convert(dailyDuty);
                if (dailyDuty.getEmployeeId() != null) {
                    dutyFromVo.setName(userNames.get(dailyDuty.getEmployeeId()));
                }
                return dutyFromVo;
            }).collect(Collectors.toList()));
            page.setTotal(dailyDutyPage.getTotal());
        }
        return page;
    }
}
