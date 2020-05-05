package com.writingcode.www.community.service;

import com.writingcode.www.community.entity.po.Repair;
import com.baomidou.mybatisplus.extension.service.IService;

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

}
