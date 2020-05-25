package com.writingcode.www.community.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Chavy
 * @date 2020/5/25
 */
@Data
@Accessors(chain = true)
public class HouseholdPageDto {

    private long size;

    private long offset;

    private Integer buildingNum;

    private Integer roomNum;

    private String name;
}
