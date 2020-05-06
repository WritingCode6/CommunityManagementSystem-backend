package com.writingcode.www.community.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.writingcode.www.community.entity.po.Feedback;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

/**
 *
 * @author Chavy
 * @date 2020/5/6
 */
@Data
@Accessors(chain = true)
public class FeedbackVo {

    /**
     * 主键id
     */
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
     * 用户真实姓名
     */
    private String userName;

    /**
     * 内容
     */
    private String details;

    /**
     * 创建时间
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
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
     * 工作人员真实姓名
     */
    private String employeeName;

    /**
     * 处理结果
     */
    private String result;

    public static FeedbackVo convert(Feedback feedback){
        Assert.notNull(feedback, "反馈信息不能为空");
        FeedbackVo feedbackVo = new FeedbackVo();
        BeanUtils.copyProperties(feedback, feedbackVo);
        return feedbackVo;
    }
}
