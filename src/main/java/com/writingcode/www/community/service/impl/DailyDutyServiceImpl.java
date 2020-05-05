package com.writingcode.www.community.service.impl;

import com.writingcode.www.community.entity.po.DailyDuty;
import com.writingcode.www.community.dao.DailyDutyMapper;
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteDuty(List<Long> ids) {
        Assert.state(Objects.nonNull(ids), "删除主键id不能为空");
        Assert.state(dailyDutyMapper.deleteBatchIds(ids) == ids.size(), "删除失败");
        return true;
    }
}
