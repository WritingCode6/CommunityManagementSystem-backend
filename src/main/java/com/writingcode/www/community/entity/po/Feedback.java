package com.writingcode.www.community.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@TableName("feedback")
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 反馈类型 0-建议 1-投诉
     */
    private Integer type;

    /**
     * 反馈的用户id
     */
    private Long userId;

    /**
     * 内容
     */
    private String details;

    /**
     * 创建时间
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 是否被受理 0-否 1-是
     */
    private Integer isReceived;

    /**
     * 工作人员id
     */
    private Long employeeId;

    /**
     * 处理结果
     */
    private String result;

}
