package com.writingcode.www.community.service;

import com.writingcode.www.community.entity.po.StaffInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
public interface IStaffInfoService extends IService<StaffInfo> {

    /**
     * 获取工作人员详细信息
     * @param userId 用户id
     * @return StaffInfo
     */
    List<StaffInfo> getStaffInfo(Long userId);

    /**
     * 更新员工信息
     * @param staffInfo 员工信息
     * @return boolean
     */
    boolean updateStaffInfo(StaffInfo staffInfo);
}
