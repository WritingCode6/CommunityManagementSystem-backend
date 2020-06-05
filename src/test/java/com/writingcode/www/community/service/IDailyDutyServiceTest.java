package com.writingcode.www.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.writingcode.www.community.entity.po.DailyDuty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class IDailyDutyServiceTest {

    @Resource
    private IDailyDutyService dailyDutyService;

    @Rollback
    @Transactional
    @Test
    void deleteDuty() {
        List<Long> ids = new LinkedList<>();
        ids.add(500L);
        Assertions.assertThrows(IllegalArgumentException.class, () -> dailyDutyService.deleteDuty(null));
        Assertions.assertThrows(IllegalStateException.class, () -> dailyDutyService.deleteDuty(new LinkedList<>()));
        Assertions.assertThrows(IllegalStateException.class, () -> dailyDutyService.deleteDuty(ids));
        ids.remove(0);
        ids.add(1L);
        Assertions.assertTrue(dailyDutyService.deleteDuty(ids));
    }

    @Rollback
    @Transactional
    @Test
    void addDutyForm() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dailyDutyService.addDutyForm(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> dailyDutyService.addDutyForm(new DailyDuty()));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> dailyDutyService.addDutyForm(new DailyDuty().setDate(LocalDate.now()).setPlace("撤唆")));
        Assertions.assertThrows(IllegalStateException.class,
                () -> dailyDutyService.addDutyForm(new DailyDuty().setEmployeeId(1L).setDate(LocalDate.now()).setPlace("撤唆")));
        Assertions.assertTrue(dailyDutyService.addDutyForm(new DailyDuty().setEmployeeId(3L).setDate(LocalDate.now()).setPlace("撤唆")));
    }

    @Test
    void getDutyInfo() {
        Assertions.assertNotNull(dailyDutyService.getDutyInfo(null, null, new Page<>(1, 10)));
        Assertions.assertNotNull(dailyDutyService.getDutyInfo(null, 1, new Page<>(1, 10)));
    }
}