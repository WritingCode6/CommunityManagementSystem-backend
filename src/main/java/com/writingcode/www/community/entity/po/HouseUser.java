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
@TableName("house_user")
public class HouseUser implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String HOUSE_ID = "house_id";
    public static final String USER_ID = "user_id";

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 房屋id
     */
    private Integer houseId;

    /**
     * 住户id
     */
    private Long userId;

}
