package com.writingcode.www.community.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.writingcode.www.community.entity.po.Feedback;
import com.writingcode.www.community.entity.vo.FeedbackVo;
import com.writingcode.www.community.result.CommonResult;
import com.writingcode.www.community.service.IFeedbackService;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 更新反馈
     * @param feedback 反馈
     * @return CommonResult<Void>
     */
    @PostMapping("/updateFeedback")
    public CommonResult<Void> updateFeedback(@RequestBody Feedback feedback){
        if(feedbackService.updateFeedback(feedback)){
            return new CommonResult<Void>().success();
        }
        return new CommonResult<Void>().fail();
    }

    /**
     * 分页获取反馈信息
     * @param current 页码
     * @param size 页面大小
     * @return CommonResult<Page<FeedbackVo>>
     */
    @GetMapping("/getFeedback")
    public CommonResult<Page<FeedbackVo>> getFeedback(@RequestParam(value = "current", defaultValue = "1") int current,
                                                      @RequestParam(value = "size", defaultValue = "10") int size){
        return new CommonResult<Page<FeedbackVo>>().success(feedbackService.getFeedback(new Page<>(current, size)));
    }
}
