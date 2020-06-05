package com.writingcode.www.community.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.writingcode.www.community.entity.po.DailyDuty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author Chavy
 * @date 2020/5/5 20:20
 */
@Data
@Accessors(chain = true)
public class DutyFromVo {

    /**
     * 主键id
     */
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

    /**
     * 值班人员真实姓名
     */
    private String name;

    public static DutyFromVo convert(DailyDuty dailyDuty){
        Assert.notNull(dailyDuty, "转换对象不能为空");
        DutyFromVo dutyFromVo = new DutyFromVo();
        BeanUtils.copyProperties(dailyDuty, dutyFromVo);
        return dutyFromVo;
    }
}
