package com.writingcode.www.community.entity.vo;

import com.writingcode.www.community.entity.po.StaffInfo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

/**
 * @author Chavy
 * @date 2020/5/21
 */
@Data
@Accessors(chain = true)
public class StaffVo {

    private String userName;

    private String password;

    private String name;

    private String idNumber;

    private String phone;

    private int sex;

    private String address;

    private Long serviceId;

    private int userType;

    public boolean isNotNull(){
        Assert.notNull(userName, "用户名不能为空");
        Assert.notNull(password, "密码不能为空");
        Assert.notNull(name, "真实姓名不能为空");
        Assert.notNull(idNumber, "身份证号不能为空");
        Assert.notNull(phone, "手机号不能为空");
        Assert.notNull(serviceId, "工号不能为空");
        Assert.state(sex == 0 || sex == 1, "性别错误");
        Assert.state(userType == 3 || userType == 4 || userType == 5 || userType == 6, "身份错误");
        return true;
    }

    public static StaffInfo convert(StaffVo staffVo){
        Assert.notNull(staffVo, "传入对象不能为空");
        StaffInfo staffInfo = new StaffInfo();
        BeanUtils.copyProperties(staffVo, staffInfo);
        return staffInfo;
    }
}
