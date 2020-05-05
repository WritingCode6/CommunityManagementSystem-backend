package com.writingcode.www.community.service.impl;

import com.writingcode.www.community.entity.po.Activity;
import com.writingcode.www.community.dao.ActivityMapper;
import com.writingcode.www.community.service.IActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
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

    @Override
    public boolean deleteCommunityActivity(List<Integer> activityIds) {
        Assert.state(Objects.nonNull(activityIds), "id列表不能为空");

        int result = activityMapper.deleteBatchIds(activityIds);
        Assert.state(result == activityIds.size(), "删除失败");
        return true;
    }

    @Override
    public boolean updateCommunityActivity(Activity activity) {
        Assert.notNull(activity, "更新信息不能为空");
        Assert.notNull(activity.getId(), "主键不能为空");

        Assert.state(activityMapper.updateById(activity) == 1, "更新失败");
        return true;
    }
}
