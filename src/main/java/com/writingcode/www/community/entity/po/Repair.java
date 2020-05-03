package com.writingcode.www.community.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
@Data
@Accessors(chain = true)
@TableName("repair")
public class Repair implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 设施
     */
    private String facility;

    /**
     * 所在地
     */
    private String place;

    /**
     * 保修原因
     */
    private String reason;

    /**
     * 保修的用户id
     */
    private Long userId;

    /**
     * 保修时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 是否被受理 0-否 1-是
     */
    private Integer isReceived;

    /**
     * 工作人员的用户id
     */
    private Long employeeId;

    /**
     * 处理时间
     */
    private LocalDateTime handleTime;

    /**
     * 处理结果
     */
    private String result;

}
