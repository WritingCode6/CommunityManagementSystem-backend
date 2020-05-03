package com.writingcode.www.community.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
    private Long user_id;

    /**
     * 内容
     */
    private String details;

    /**
     * 创建时间
     */
    private LocalDateTime create_time;

    /**
     * 是否被受理 0-否 1-是
     */
    private Integer is_received;

    /**
     * 工作人员id
     */
    private Long employee_id;

    /**
     * 处理结果
     */
    private String result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }
    public Integer getIs_received() {
        return is_received;
    }

    public void setIs_received(Integer is_received) {
        this.is_received = is_received;
    }
    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Feedback{" +
            "id=" + id +
            ", type=" + type +
            ", user_id=" + user_id +
            ", details=" + details +
            ", create_time=" + create_time +
            ", is_received=" + is_received +
            ", employee_id=" + employee_id +
            ", result=" + result +
        "}";
    }
}
