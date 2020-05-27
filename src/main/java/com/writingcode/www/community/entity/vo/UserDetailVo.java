package com.writingcode.www.community.entity.vo;

import com.writingcode.www.community.entity.po.Car;
import com.writingcode.www.community.entity.po.House;
import com.writingcode.www.community.entity.po.HouseholdInfo;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Chavy
 * @date 2020/5/5 22:30
 */
@Data
@Accessors(chain = true)
public class UserDetailVo {

    private House houseInfo;

    private Car carInfo;

    private UserVo userInfo;
}
