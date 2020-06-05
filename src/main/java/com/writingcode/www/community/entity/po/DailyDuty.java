package com.writingcode.www.community.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
@Data
@Accessors(chain = true)
@TableName("daily_duty")
public class DailyDuty implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ID = "id";
    public static final String DATE = "date";
    public static final String EMPLOYEE_ID = "employee_id";
    public static final String TYPE = "type";
    public static final String PLACE = "place";

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 值班日期
     */
    @JsonFormat( pattern = "yyyy-MM-dd")
    private LocalDate date;

    /**
     * 用户id
     */
    private Long employeeId;

    /**
     * 值班类型  0清洁/1巡查/2安保
     */
    private Integer type;

    /**
     * 值班区域
     */
    private String place;

}
