package com.writingcode.www.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.writingcode.www.community.entity.po.Activity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class IActivityServiceTest {

    @Resource
    private IActivityService activityService;

    @Rollback
    @Transactional
    @Test
    void deleteCommunityActivity() {
        List<Integer> ids = new LinkedList<>();
        ids.add(1000);
        Assertions.assertThrows(IllegalArgumentException.class, () -> activityService.deleteCommunityActivity(null));
        Assertions.assertThrows(IllegalStateException.class, () -> activityService.deleteCommunityActivity(new LinkedList<>()));
        Assertions.assertThrows(IllegalStateException.class, () -> activityService.deleteCommunityActivity(ids));
        ids.remove(0);
        ids.add(1);
        Assertions.assertTrue(activityService.deleteCommunityActivity(ids));
    }

    @Rollback
    @Transactional
    @Test
    void updateCommunityActivity() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> activityService.updateCommunityActivity(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> activityService.updateCommunityActivity(new Activity()));
        Assertions.assertThrows(IllegalStateException.class, () -> activityService.updateCommunityActivity(new Activity().setId(1000).setContent("HAHA")));
        Assertions.assertTrue(activityService.updateCommunityActivity(new Activity().setId(1).setContent("改一下内容")));
    }

    @Rollback
    @Transactional
    @Test
    void addCommunityActivity() {
        Activity activity = new Activity();
        Assertions.assertThrows(IllegalArgumentException.class, () -> activityService.addCommunityActivity(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> activityService.addCommunityActivity(activity));
        Assertions.assertThrows(IllegalArgumentException.class, () -> activityService.addCommunityActivity(activity.setContent("HA")));
        Assertions.assertThrows(IllegalArgumentException.class, () -> activityService.addCommunityActivity(activity.setActivityName("ha")));
        Assertions.assertThrows(IllegalArgumentException.class, () -> activityService.addCommunityActivity(activity.setHost("是我")));
        Assertions.assertThrows(IllegalArgumentException.class, () -> activityService.addCommunityActivity(activity.setPrincipal("不是我")));
        Assertions.assertTrue(activityService.addCommunityActivity(activity.setTelNumber("11111111111")));
    }

    @Test
    void getCommunityActivity() {
        Assertions.assertNotNull(activityService.getCommunityActivity(new Page<>(1, 10)));
    }
}