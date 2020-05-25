package com.writingcode.www.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.writingcode.www.community.entity.po.HouseholdInfo;
import com.writingcode.www.community.entity.vo.HouseHoldVo;
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
public class IHouseholdInfoServiceTest {

    @Resource
    private IHouseholdInfoService householdInfoService;

    @Test
    void searchUser() {
        Assertions.assertNotNull(householdInfoService.searchUser(null, null, null, new Page<>(1, 10)));
        Assertions.assertNotNull(householdInfoService.searchUser("周深", null, null, new Page<>(1, 10)));
        Assertions.assertNotNull(householdInfoService.searchUser(null, 1, 101, new Page<>(1, 10)));
    }

    @Transactional
    @Rollback
    @Test
    void updateUserInfo() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> householdInfoService.updateUserInfo(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> householdInfoService.updateUserInfo(new HouseholdInfo()));
        Assertions.assertTrue(householdInfoService.updateUserInfo(new HouseholdInfo().setId(1L).setResidenceAddress("湖南省")));
    }

    @Transactional
    @Rollback
    @Test
    void addUser(){
        HouseHoldVo houseHoldVo = new HouseHoldVo();
        Assertions.assertThrows(IllegalArgumentException.class, () -> householdInfoService.addUser(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> householdInfoService.addUser(houseHoldVo));
        houseHoldVo.setUserName("Test10086")
                .setPassword("123456")
                .setAncestralHome("bzd")
                .setBuildingNumber(1)
                .setRoomNumber(101)
                .setSex(0)
                .setName("12")
                .setResidenceAddress("bzd")
                .setIdNumber("123");
        Assertions.assertTrue(householdInfoService.addUser(houseHoldVo));
    }
}