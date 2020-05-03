package com.writingcode.www.community.service.impl;

import com.writingcode.www.community.entity.po.Feedback;
import com.writingcode.www.community.dao.FeedbackMapper;
import com.writingcode.www.community.service.IFeedbackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements IFeedbackService {

}
