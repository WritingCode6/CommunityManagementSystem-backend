package com.writingcode.www.community.service;

import com.writingcode.www.community.entity.po.User;
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
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.login(null, null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.login("root", null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.login(null, "123456"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.login("not", "123456"));
        Assertions.assertNotNull(userService.login("root", "123456"));
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
}