package com.writingcode.www.community.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
@Data
@Accessors(chain = true)
@TableName("staff_info")
public class StaffInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String ID_NUMBER = "id_number";
    public static final String PHONE = "phone";
    public static final String SEX = "sex";
    public static final String ADDRESS = "address";
    public static final String USER_ID = "user_id";
    public static final String SERVICE_ID = "service_id";

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String idNumber;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 性别 0-男 1-女
     */
    private Integer sex;

    private String address;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 工号
     */
    private Long serviceId;

}
