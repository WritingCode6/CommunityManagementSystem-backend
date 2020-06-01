package com.writingcode.www.community.service;

import com.writingcode.www.community.entity.po.User;
import com.writingcode.www.community.entity.vo.StaffVo;
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
public class IUserServiceTest {

    @Resource
    private IUserService userService;

    @Test
    void login() {
//        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.login(null));
//        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.login(new User().setUserName("root")));
//        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.login(new User().setPassword("123456")));
//        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.login(new User().setUserName("root").setPassword("1234567")));
//        Assertions.assertNotNull(userService.login(new User().setUserName("root").setPassword("123456")));
    }

    @Test
    void getUserInfo() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.getUserInfo(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.getUserInfo(1000L));
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.getUserInfo(1L));
        Assertions.assertNotNull(userService.getUserInfo(2L));
    }

    @Transactional
    @Rollback
    @Test
    void updateAccount() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.updateAccount(new User()));
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.updateAccount(null));
        Assertions.assertTrue(userService.updateAccount(new User().setId(1L).setPassword("1234567")));
    }

    @Transactional
    @Rollback
    @Test
    void addStaff(){
        StaffVo staffVo = new StaffVo();
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.addStaff(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.addStaff(staffVo));
        staffVo.setUserName("Test")
                .setPassword("123456")
                .setName("HaGa")
                .setIdNumber("111111222222223333")
                .setPhone("15345454545")
                .setSex(0)
                .setUserType(3)
                .setServiceId(123456L);
        Assertions.assertTrue(userService.addStaff(staffVo));
    }

    @Transactional
    @Rollback
    @Test
    void deleteUser(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.deleteUser(null));
        Assertions.assertTrue(userService.deleteUser(1L));
    }
}