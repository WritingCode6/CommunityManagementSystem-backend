package com.writingcode.www.community.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.writingcode.www.community.dao.HouseholdInfoMapper;
import com.writingcode.www.community.dao.StaffInfoMapper;
import com.writingcode.www.community.dao.UserRoleMapper;
import com.writingcode.www.community.entity.po.Feedback;
import com.writingcode.www.community.dao.FeedbackMapper;
import com.writingcode.www.community.entity.vo.FeedbackVo;
import com.writingcode.www.community.service.IFeedbackService;
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
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements IFeedbackService {

    @Resource
    private FeedbackMapper feedbackMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private StaffInfoMapper staffInfoMapper;

    @Resource
    private HouseholdInfoMapper householdInfoMapper;

    @Transactional(rollbackFor = Exception.class)
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

    @Transactional(rollbackFor = Exception.class)
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

    @Override
    public Page<FeedbackVo> getFeedback(Page<FeedbackVo> page) {
        Page<Feedback> feedbackPage = new Page<>(page.getCurrent(), page.getSize());
        feedbackPage = feedbackMapper.selectPage(feedbackPage, null);

        List<FeedbackVo> feedbackVos = new LinkedList<>();
        for(Feedback feedback : feedbackPage.getRecords()){
            FeedbackVo feedbackVo = FeedbackVo.convert(feedback);

            feedbackVo.setUserName(householdInfoMapper.selectByUserId(feedback.getUserId()).getName());

            if(feedback.getEmployeeId() != null){
                feedbackVo.setEmployeeName(staffInfoMapper.selectByUserId(feedback.getEmployeeId()).getName());
            }
            feedbackVos.add(feedbackVo);
        }

        page.setRecords(feedbackVos);
        page.setTotal(feedbackPage.getTotal());
        return page;
    }
}
