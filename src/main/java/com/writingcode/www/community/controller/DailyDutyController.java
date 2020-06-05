package com.writingcode.www.community.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.writingcode.www.community.entity.po.DailyDuty;
import com.writingcode.www.community.entity.vo.DutyFromVo;
import com.writingcode.www.community.result.CommonResult;
import com.writingcode.www.community.service.IDailyDutyService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.sql.Date;
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

    /**
     * 分页查询值班表
     * @param date 时间
     * @param type 类型
     * @param current 页码 默认1
     * @param size 页面大小 默认10
     * @return CommonResult<Page<DutyFromVo>>
     */
    @GetMapping("/getDutyInfo")
    public CommonResult<Page<DutyFromVo>> getDutyInfo(@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(value = "date", required = false) LocalDate date,
                                                     @RequestParam(value = "type", required = false) Integer type,
                                                     @RequestParam(value = "current", defaultValue = "1") int current,
                                                     @RequestParam(value = "size", defaultValue = "10") int size){
        return new CommonResult<Page<DutyFromVo>>().success(dailyDutyService.getDutyInfo(date, type, new Page<>(current, size)));
    }
}
