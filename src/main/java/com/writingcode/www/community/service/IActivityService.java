package com.writingcode.www.community.service;

import com.writingcode.www.community.entity.po.Activity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
public interface IActivityService extends IService<Activity> {

    /**
     * 批量删除社区活动
     *
     * @param activityIds 社区活动ids
     * @return boolean
     */
    boolean deleteCommunityActivity(List<Integer> activityIds);

    /**
     * 更新活动信息
     *
     * @param activity 活动
     * @return boolean
     */
    boolean updateCommunityActivity(Activity activity);

    /**
     * 增加社区活动
     *
     * @param activity 活动
     * @return boolean
     */
    boolean addCommunityActivity(Activity activity);

}
