package com.writingcode.www.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.writingcode.www.community.entity.po.Feedback;
import com.baomidou.mybatisplus.extension.service.IService;
import com.writingcode.www.community.entity.vo.FeedbackVo;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
public interface IFeedbackService extends IService<Feedback> {

    /**
     * 增加反馈
     * @param feedback 反馈
     * @return boolean
     */
    boolean addFeedback(Feedback feedback);

    /**
     * 更新反馈
     * @param feedback 反馈
     * @return boolean
     */
    boolean updateFeedback(Feedback feedback);

    /**
     * 分页获取反馈信息
     * @param page 分页信息
     * @return Page<FeedbackVo>
     */
    Page<FeedbackVo> getFeedback(Page<FeedbackVo> page);

}
