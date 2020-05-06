package com.writingcode.www.community.controller;

import com.writingcode.www.community.entity.po.Feedback;
import com.writingcode.www.community.result.CommonResult;
import com.writingcode.www.community.service.IFeedbackService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Chavy
 * @date 2020/5/6
 */
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Resource
    private IFeedbackService feedbackService;

    /**
     * 添加反馈
     * @param feedback 反馈
     * @return CommonResult<Void>
     */
    @PostMapping("/addFeedback")
    public CommonResult<Void> addFeedback(@RequestBody Feedback feedback){
        if(feedbackService.addFeedback(feedback)){
            return new CommonResult<Void>().success();
        }
        return new CommonResult<Void>().fail();
    }
}
