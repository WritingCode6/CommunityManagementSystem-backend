package com.writingcode.www.community.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.writingcode.www.community.entity.po.House;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

/**
 * @author Chavy
 * @date 2020/5/4
 */
@Data
@Accessors(chain = true)
public class HouseInfoVo {


    /**
     * 主键id
     */
    private Integer id;

    /**
     * 户型
     */
    private Integer unitType;

    /**
     * 面积
     */
    private Float area;

    /**
     * 栋数
     */
    private Integer buildingNumber;

    /**
     * 房间号
     */
    private Integer roomNumber;

    /**
     * 是否空闲 0-是 1-不是
     */
    private Integer isIdle;

    /**
     * 入住时间
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkInTime;

    /**
     * 用户id 户主
     */
    private Long userId;

    /**
     * 真实姓名
     */
    private String name;

    public static HouseInfoVo convert(House house){
        Assert.notNull(house, "house对象不能为空");
        HouseInfoVo houseInfoVo = new HouseInfoVo();
        BeanUtils.copyProperties(house, houseInfoVo);
        return houseInfoVo;
    }
}
