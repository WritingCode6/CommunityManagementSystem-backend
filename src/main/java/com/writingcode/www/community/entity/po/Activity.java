package com.writingcode.www.community.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("activity")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 主办方
     */
    private String host;

    /**
     * 负责人名字
     */
    private String principal;

    /**
     * 咨询电话
     */
    private String telNumber;

    /**
     * 内容
     */
    private String content;

    /**
     * 开始时间
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

}
