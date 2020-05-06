package com.writingcode.www.community.service;

import com.writingcode.www.community.entity.po.StaffInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class IStaffInfoServiceTest {

    @Resource
    private IStaffInfoService staffInfoService;

    @Test
    void getStaffInfo() {
        Assertions.assertNotNull(staffInfoService.getStaffInfo(3L));
    }

    @Test
    @Rollback
    @Transactional
    void updateStaffInfo() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> staffInfoService.updateStaffInfo(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> staffInfoService.updateStaffInfo(new StaffInfo()));
        Assertions.assertThrows(IllegalStateException.class, () -> staffInfoService.updateStaffInfo(new StaffInfo().setId(1000L).setName("Ha")));
        Assertions.assertTrue(staffInfoService.updateStaffInfo(new StaffInfo().setId(1L).setAddress("湖北")));
    }
}