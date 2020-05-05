package com.writingcode.www.community.service;

import com.writingcode.www.community.entity.po.DailyDuty;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
public interface IDailyDutyService extends IService<DailyDuty> {

    /**
     * 批量删除值班信息
     * @param ids id列表
     * @return boolean
     */
    boolean deleteDuty(List<Long> ids);

    /**
     * 新增值班信息
     * @param dailyDuty 值班信息
     * @return boolean
     */
    boolean addDutyForm(DailyDuty dailyDuty);

}
