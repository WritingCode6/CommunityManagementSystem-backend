package com.writingcode.www.community.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * mybatis plus 时间自动填充处理器
 * 时间类型需要为 LocalDateTime 创建时间命名需要 createTime  更新时间命名需要 updateTime
 * 配合注解 	@TableField(fill = FieldFill.INSERT) @TableField(fill = FieldFill.UPDATE)等使用
 * @author Chavy
 * @date 2020/4/14
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 起始版本 3.3.0(推荐使用)
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }
}