package com.writingcode.www.community.entity.vo;

import com.writingcode.www.community.entity.po.HouseholdInfo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * @author Chavy
 * @date 2020/5/21
 */
@Data
@Accessors(chain = true)
public class HouseHoldVo {

    private Long id;

    private String userName;

    private String password;

    private String name;

    private String idNumber;

    private int sex;

    private String ancestralHome;

    private String residenceAddress;

    private Integer buildingNumber;

    private Integer roomNumber;

    public boolean isNotNull(){
        Assert.notNull(userName, "用户名不能为空");
        Assert.notNull(password, "密码不能为空");
        Assert.notNull(name, "真实姓名不能为空");
        Assert.notNull(idNumber, "身份证号不能为空");
        Assert.notNull(ancestralHome, "籍贯不能为空");
        Assert.notNull(residenceAddress, "户口所在地不能为空");
        Assert.notNull(buildingNumber, "栋数不能为空");
        Assert.notNull(roomNumber, "房间号不能为空");
        Assert.state(sex == 0 || sex == 1, "性别错误");
        return true;
    }

    public static HouseholdInfo convert(HouseHoldVo houseHoldVo){
        Assert.notNull(houseHoldVo, "传入对象不能为空");
        HouseholdInfo householdInfo = new HouseholdInfo();
        BeanUtils.copyProperties(houseHoldVo, householdInfo);
        return householdInfo;
    }

}
