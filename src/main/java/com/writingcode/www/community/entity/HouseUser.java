package com.writingcode.www.community.entity;

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

    /**
     * 房屋id
     */
    private Integer houseId;

    /**
     * 住户id
     */
    private Long userId;

}
