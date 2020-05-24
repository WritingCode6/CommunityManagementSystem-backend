package com.writingcode.www.community.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class IParkingSpaceServiceTest {

    @Resource
    private IParkingSpaceService parkingSpaceService;

    @Test
    void getParkingInfo() {
        Assertions.assertNotNull(parkingSpaceService.getParkingInfo());
    }

    @Transactional
    @Rollback
    @Test
    void addParking() {
        List<Integer> numbers = new LinkedList<>();
        Assertions.assertThrows(IllegalArgumentException.class, () -> parkingSpaceService.addParking(null));
        Assertions.assertThrows(IllegalStateException.class, () -> parkingSpaceService.addParking(numbers));
        numbers.add(1);
        parkingSpaceService.addParking(numbers);
    }

    @Transactional
    @Rollback
    @Test
    void deleteParking() {
        List<Integer> numbers = new LinkedList<>();
        Assertions.assertThrows(IllegalArgumentException.class, () -> parkingSpaceService.deleteParking(null));
        Assertions.assertThrows(IllegalStateException.class, () -> parkingSpaceService.deleteParking(numbers));
        numbers.add(1);
        parkingSpaceService.deleteParking(numbers);
    }
}