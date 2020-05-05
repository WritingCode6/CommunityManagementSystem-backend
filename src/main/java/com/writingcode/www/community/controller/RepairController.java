package com.writingcode.www.community.controller;

import com.writingcode.www.community.entity.po.Repair;
import com.writingcode.www.community.result.CommonResult;
import com.writingcode.www.community.service.IRepairService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 * @author Chavy
 * @date 2020/5/5 20:40
 */
@RestController
@RequestMapping("/api/repair")
public class RepairController {

    @Resource
    private IRepairService repairService;

    /**
     * 新增保修单
     * @param repair 保修单
     * @return CommonResult<Void>
     */
    @PostMapping("/addRepair")
    public CommonResult<Void> addRepair(Repair repair){
        if(repairService.addRepair(repair)){
            return new CommonResult<Void>().success();
        }
        return new CommonResult<Void>().fail();
    }

    /**
     * 更新保修单
     * @param repair 保修单
     * @return CommonResult<Void>
     */
    @PostMapping("/updateRepair")
    public CommonResult<Void> updateRepair(Repair repair){
        if(repairService.updateRepair(repair)){
            return new CommonResult<Void>().success();
        }
        return new CommonResult<Void>().fail();
    }
}
