package com.writingcode.www.community.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.writingcode.www.community.entity.po.Activity;
import com.writingcode.www.community.dao.ActivityMapper;
import com.writingcode.www.community.service.IActivityService;
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
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityService {

    @Resource
    private ActivityMapper activityMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteCommunityActivity(List<Integer> activityIds) {
        Assert.notNull(activityIds, "id列表不能为空");
        Assert.state(!activityIds.isEmpty(), "id列表不能为空");

        Assert.state(activityMapper.deleteBatchIds(activityIds) == activityIds.size(), "删除失败");
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateCommunityActivity(Activity activity) {
        Assert.notNull(activity, "更新信息不能为空");
        Assert.notNull(activity.getId(), "主键不能为空");

        Assert.state(activityMapper.updateById(activity) == 1, "更新失败");
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addCommunityActivity(Activity activity) {
        Assert.notNull(activity, "活动不能为空");
        Assert.notNull(activity.getActivityName(), "活动名不能为空");
        Assert.notNull(activity.getContent(), "活动内容不能为空");
        Assert.notNull(activity.getPrincipal(), "负责人不能为空");
        Assert.notNull(activity.getHost(), "主办方不能为空");
        Assert.notNull(activity.getTelNumber(), "咨询电话不能为空");

        Assert.state(activityMapper.insert(activity) == 1, "增加失败");
        return true;
    }

    @Override
    public Page<Activity> getCommunityActivity(Page<Activity> page) {
        page = activityMapper.selectPage(page, null);
        return page;
    }
}
