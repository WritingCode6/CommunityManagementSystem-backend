package com.writingcode.www.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.writingcode.www.community.entity.po.Repair;
import com.baomidou.mybatisplus.extension.service.IService;
import com.writingcode.www.community.entity.vo.RepairVo;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
public interface IRepairService extends IService<Repair> {

    /**
     * 添加保修单
     * @param repair 保修单
     * @return boolean
     */
    boolean addRepair(Repair repair);

    /**
     * 更新保修单
     * @param repair 保修单
     * @return boolean
     */
    boolean updateRepair(Repair repair);

    /**
     * 分页查询保修单
     * @param userId 用户id
     * @param page 分页bean
     * @return Page<RepairVo>
     */
    Page<RepairVo> getRepair(Long userId, Page<RepairVo> page);

}
