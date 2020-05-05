package com.writingcode.www.community.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.writingcode.www.community.entity.po.Activity;
import com.writingcode.www.community.entity.po.Notice;
import com.writingcode.www.community.result.CommonResult;
import com.writingcode.www.community.service.IActivityService;
import com.writingcode.www.community.service.INoticeService;
import org.springframework.web.bind.annotation.*;

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

    @Resource
    private INoticeService noticeService;

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

    /**
     * 增加社区活动
     * @param activity 活动
     * @return CommonResult<Void>
     */
    @PostMapping("/addCommunityActivity")
    public CommonResult<Void> addCommunityActivity(@RequestBody Activity activity){
        if(activityService.addCommunityActivity(activity)){
            return new CommonResult<Void>().success();
        }
        return new CommonResult<Void>().fail();
    }

    /**
     * 分页查询社区活动
     * @param current 页码 默认1
     * @param size 页面大小 默认10
     * @return CommonResult<Page<Activity>>
     */
    @GetMapping("/getCommunityActivity")
    public CommonResult<Page<Activity>> getCommunityActivity(@RequestParam(value = "current", defaultValue = "1") int current,
                                                             @RequestParam(value = "size", defaultValue = "10") int size){
        return new CommonResult<Page<Activity>>().success(activityService.getCommunityActivity(new Page<>(current, size)));
    }

    /**
     * 更新社区通知
     * @param notice 通知
     * @return CommonResult<Void>
     */
    @PostMapping("/updateCommunityNotice")
    public CommonResult<Void> updateCommunityNotice(@RequestBody Notice notice){
        if(noticeService.updateCommunityNotice(notice)){
            return new CommonResult<Void>().success();
        }
        return new CommonResult<Void>().fail();
    }

    /**
     * 新增社区通知
     * @param notice 通知
     * @return CommonResult<Void>
     */
    @PostMapping("/addCommunityNotice")
    public CommonResult<Void> addCommunityNotice(@RequestBody Notice notice){
        if(noticeService.addCommunityNotice(notice)){
            return new CommonResult<Void>().success();
        }
        return new CommonResult<Void>().fail();
    }

    /**
     * 分页获取社区通知
     * @param current 页码 默认1
     * @param size 页面大小 默认10
     * @return CommonResult<Page<Notice>>
     */
    @GetMapping("/getCommunityNotice")
    public CommonResult<Page<Notice>> getCommunityNotice(@RequestParam(value = "current", defaultValue = "1") int current,
                                                 @RequestParam(value = "size", defaultValue = "10") int size){
        return new CommonResult<Page<Notice>>().success(noticeService.getCommunityNotice(new Page<>(current, size)));
    }
}
