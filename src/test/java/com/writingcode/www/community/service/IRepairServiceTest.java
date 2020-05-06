package com.writingcode.www.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.writingcode.www.community.entity.po.Repair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.time.LocalDateTime;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class IRepairServiceTest {

    @Resource
    private IRepairService repairService;

    @Rollback
    @Transactional
    @Test
    void addRepair() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> repairService.addRepair(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> repairService.addRepair(
                new Repair().setUserId(1000L).setReason("Ha").setPlace("撤唆")));
        Assertions.assertThrows(IllegalStateException.class, () -> repairService.addRepair(
                new Repair().setUserId(3L).setReason("Ha").setPlace("撤唆")));
        Assertions.assertTrue(repairService.addRepair(new Repair().setUserId(2L).setReason("Ha").setPlace("撤唆")));
    }

    @Rollback
    @Transactional
    @Test
    void updateRepair() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> repairService.updateRepair(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> repairService.updateRepair(new Repair()));
        Assertions.assertTrue(repairService.updateRepair(new Repair().setId(2L).setIsReceived(1).setHandleTime(LocalDateTime.now()).setEmployeeId(3L)));
    }

    @Test
    void getRepair() {
        Assertions.assertNotNull(repairService.getRepair(null, new Page<>(1,10)));
        Assertions.assertNotNull(repairService.getRepair(2L, new Page<>(1,10)));
    }
}