package com.writingcode.www.community.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.writingcode.www.community.entity.po.Repair;
import com.writingcode.www.community.entity.vo.RepairVo;
import com.writingcode.www.community.result.CommonResult;
import com.writingcode.www.community.service.IRepairService;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

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
    public CommonResult<Void> addRepair(@RequestBody Repair repair){
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
    public CommonResult<Void> updateRepair(@RequestBody Repair repair){
        if(repairService.updateRepair(repair)){
            return new CommonResult<Void>().success();
        }
        return new CommonResult<Void>().fail();
    }

    /**
     * 分页查询保修单
     * @param userId 用户id
     * @param current 页码 默认1
     * @param size 页面大小 默认10
     * @return CommonResult<Page<RepairVo>>
     */
    @GetMapping("/getRepair")
    public CommonResult<Page<RepairVo>> getRepair(@RequestParam(value = "userId", required = false) Long userId,
                                                  @RequestParam(value = "current", defaultValue = "1") int current,
                                                  @RequestParam(value = "size", defaultValue = "10") int size){
        return new CommonResult<Page<RepairVo>>().success(repairService.getRepair(userId, new Page<>(current, size)));
    }
}
