package com.writingcode.www.community.controller;

import com.writingcode.www.community.entity.po.Activity;
import com.writingcode.www.community.result.CommonResult;
import com.writingcode.www.community.service.IActivityService;
import com.writingcode.www.community.service.impl.ActivityServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author Chavy
 * @date 2020/5/5
 */
@RestController
@RequestMapping("/api/community")
public class CommunityController {

    @Resource
    private IActivityService activityService;

    /**
     * 批量删除活动
     * @param ids id列表
     * @return CommonResult<Void>
     */
    @PostMapping("/deleteCommunityActivity")
    public CommonResult<Void> deleteCommunityActivity(@RequestBody List<Integer> ids){
        if(activityService.deleteCommunityActivity(ids)){
            return new CommonResult<Void>().success();
        }
        return new CommonResult<Void>().fail();
    }

    /**
     * 更新活动信息
     * @param activity 活动
     * @return CommonResult<Void>
     */
    @PostMapping("/updateCommunityActivity")
    public CommonResult<Void> updateCommunityActivity(@RequestBody Activity activity){
        if(activityService.updateCommunityActivity(activity)){
            return new CommonResult<Void>().success();
        }
        return new CommonResult<Void>().fail();
    }
}
