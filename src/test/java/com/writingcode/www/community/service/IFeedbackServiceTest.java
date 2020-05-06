package com.writingcode.www.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.writingcode.www.community.entity.po.Feedback;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class IFeedbackServiceTest {

    @Resource
    private IFeedbackService feedbackService;

    @Rollback
    @Transactional
    @Test
    void addFeedback() {
        Feedback feedback = new Feedback();
        Assertions.assertThrows(IllegalArgumentException.class, () -> feedbackService.addFeedback(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> feedbackService.addFeedback(feedback));
        Assertions.assertThrows(IllegalArgumentException.class, () -> feedbackService.addFeedback(feedback.setUserId(1L)));
        Assertions.assertThrows(IllegalArgumentException.class, () -> feedbackService.addFeedback(feedback.setType(0)));
        Assertions.assertThrows(IllegalStateException.class, () -> feedbackService.addFeedback(feedback.setDetails("其实没有什么")));
        Assertions.assertTrue(feedbackService.addFeedback(feedback.setUserId(2L)));
    }

    @Rollback
    @Transactional
    @Test
    void updateFeedback() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> feedbackService.updateFeedback(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> feedbackService.updateFeedback(new Feedback()));
        Assertions.assertThrows(IllegalStateException.class, () -> feedbackService.updateFeedback(new Feedback().setId(1L).setEmployeeId(1L)));
        Assertions.assertTrue(feedbackService.updateFeedback(new Feedback().setId(1L).setEmployeeId(3L)));
    }

    @Test
    void getFeedback() {
        Assertions.assertNotNull(feedbackService.getFeedback(new Page<>(1, 10)));
    }
}