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
@TableName("car")
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ID = "id";
    public static final String USER_ID = "user_id";
    public static final String PLATE_NUMBER = "plate_number";
    public static final String BRAND = "brand";
    public static final String MODEL = "model";
    public static final String COLOR = "color";

    /**
     * 车主用户id
     */
    private Long userId;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 车牌号
     */
    private String plateNumber;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 型号
     */
    private String model;

    /**
     * 颜色
     */
    private String color;

}
