package com.writingcode.www.community.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.writingcode.www.community.entity.po.Repair;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

/**
 *
 * @author Chavy
 * @date 2020/5/5 21:04
 */
@Data
@Accessors(chain = true)
public class RepairVo {


    /**
     * 主键id
     */
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
     * 用户真实姓名
     */
    private String userName;

    /**
     * 保修时间
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
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
     * 工作人员真实姓名
     */
    private String employeeName;

    /**
     * 工作人员手机号
     */
    private String employeePhone;

    /**
     * 处理时间
     */
    private LocalDateTime handleTime;

    /**
     * 处理结果
     */
    private String result;

    public static RepairVo convert(Repair repair){
        Assert.notNull(repair, "转换对象不能为空");
        RepairVo repairVo = new RepairVo();
        BeanUtils.copyProperties(repair, repairVo);
        return repairVo;
    }
}
