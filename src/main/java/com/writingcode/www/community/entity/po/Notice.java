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
@TableName("notice")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 创建时间
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 发布的工作人员id
     */
    private Long employeeId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;
}
