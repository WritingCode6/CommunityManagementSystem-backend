package com.writingcode.www.community.service;

import com.writingcode.www.community.entity.po.HouseholdInfo;
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
        Assertions.assertNotNull(householdInfoService.searchUser(null, null, null));
        Assertions.assertNotNull(householdInfoService.searchUser("周深", null, null));
        Assertions.assertNotNull(householdInfoService.searchUser(null, 1, 101));
    }

    @Transactional
    @Rollback
    @Test
    void updateUserInfo() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> householdInfoService.updateUserInfo(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> householdInfoService.updateUserInfo(new HouseholdInfo()));
        Assertions.assertTrue(householdInfoService.updateUserInfo(new HouseholdInfo().setId(1L).setResidenceAddress("湖南省")));

    }
}