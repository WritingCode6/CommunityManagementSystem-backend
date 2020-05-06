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
@TableName("repair")
public class Repair implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ID = "id";
    public static final String FACILITY = "facility";
    public static final String PLACE = "place";
    public static final String REASON = "reason";
    public static final String USER_ID = "user_id";
    public static final String CREATE_TIME = "create_time";
    public static final String IS_RECEIVED = "is_received";
    public static final String EMPLOYEE_ID = "employee_id";
    public static final String HANDLE_TIME = "handle_time";
    public static final String RESULT = "result";

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
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
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
