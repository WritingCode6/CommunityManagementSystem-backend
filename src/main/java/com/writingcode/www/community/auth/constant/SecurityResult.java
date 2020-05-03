package com.writingcode.www.community.auth.constant;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Chavy
 * @date 2020/4/13
 */
@Data
@Accessors(chain = true)
public class SecurityResult <T>{

    private int code;

    private String message;

    private boolean isSuccess;

    private T data;

    public SecurityResult<T> success(){
        return setSuccess(true).setMessage("Operate Success").setCode(200);
    }

    public SecurityResult<T> success(T data){
        return setSuccess(true).setMessage("Operate Success").setCode(200).setData(data);
    }

    public SecurityResult<T> success(String message){
        return setSuccess(true).setMessage(message).setCode(200);
    }

    public SecurityResult<T> fail(String message){
        return setSuccess(true).setMessage(message).setCode(401);
    }
}
