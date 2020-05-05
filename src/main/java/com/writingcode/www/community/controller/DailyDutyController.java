package com.writingcode.www.community.controller;

import com.writingcode.www.community.entity.po.DailyDuty;
import com.writingcode.www.community.result.CommonResult;
import com.writingcode.www.community.service.IDailyDutyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author Chavy
 * @date 2020/5/5 16:55
 */
@RestController
@RequestMapping("/api/property")
public class DailyDutyController {

    @Resource
    private IDailyDutyService dailyDutyService;

    /**
     * 删除值班信息
     *
     * @param ids id列表
     * @return CommonResult<Void>
     */
    @PostMapping("/deleteDuty")
    public CommonResult<Void> deleteDuty(@RequestBody List<Long> ids){
        if(dailyDutyService.deleteDuty(ids)){
            return new CommonResult<Void>().success();
        }
        return new CommonResult<Void>().fail();
    }

    /**
     * 新增值班信息
     * @param dailyDuty 值班信息
     * @return CommonResult<Void>
     */
    @PostMapping("/addDutyForm")
    public CommonResult<Void> addDutyForm(@RequestBody DailyDuty dailyDuty){
        if(dailyDutyService.addDutyForm(dailyDuty)){
            return new CommonResult<Void>().success();
        }
        return new CommonResult<Void>().fail();
    }
}
