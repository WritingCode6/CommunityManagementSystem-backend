package com.writingcode.www.community.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author Chavy
 * @date 2020/5/5 22:21
 */
@Data
@Accessors(chain = true)
public class HouseUserVo {

    private Long userId;

    private Integer roomNumber;

    private Integer buildingNumber;

    private String name;

    private String plateNumber;
}
