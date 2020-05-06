package com.writingcode.www.community.service.impl;

import com.writingcode.www.community.dao.StaffInfoMapper;
import com.writingcode.www.community.dao.UserRoleMapper;
import com.writingcode.www.community.entity.po.Feedback;
import com.writingcode.www.community.dao.FeedbackMapper;
import com.writingcode.www.community.service.IFeedbackService;
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
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements IFeedbackService {

    @Resource
    private FeedbackMapper feedbackMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private StaffInfoMapper staffInfoMapper;

    @Override
    public boolean addFeedback(Feedback feedback) {
        Assert.notNull(feedback, "反馈内容不能为空");
        Assert.notNull(feedback.getUserId(), "用户id不能为空");
        Assert.notNull(feedback.getType(), "反馈类型不能为空");
        Assert.notNull(feedback.getDetails(), "反馈内容不能为空");

        Integer role = userRoleMapper.selectRoleIdByUserId(feedback.getUserId());
        Assert.state(role != null && role == 2, "该用户不存在或不是住户");

        Assert.state(feedbackMapper.insert(feedback) == 1, "增加失败");
        return true;
    }

    @Override
    public boolean updateFeedback(Feedback feedback) {
        Assert.notNull(feedback, "反馈内容不能为空");
        Assert.notNull(feedback.getId(), "主键id不能为空");

        if(feedback.getEmployeeId() != null) {
            Assert.state(staffInfoMapper.selectStaffExist(feedback.getEmployeeId()), "该用户不存在或不是工作人员");
        }

        Assert.state(feedbackMapper.updateById(feedback) == 1, "更新失败");
        return true;
    }
}
