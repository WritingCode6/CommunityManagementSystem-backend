package com.writingcode.www.community.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author Chavy
 * @date 2020/5/3
 */
@Data
@Accessors(chain = true)
public class LoginVo {

    private Long userId;

    private Integer roleId;

    private String token;
}
