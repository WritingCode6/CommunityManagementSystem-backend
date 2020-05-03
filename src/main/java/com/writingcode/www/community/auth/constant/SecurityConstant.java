package com.writingcode.www.community.auth.constant;

/**
 * @author Chavy
 * @date 2020/4/13
 */
public class SecurityConstant {


    public static final SecurityResult<String> ACCESS_DENY =
            new SecurityResult<String>().setSuccess(false).setMessage("无权限访问此接口").setCode(401);

    public static final String SIGN_FAIL = "签发Token失败";

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String STATE_PREFIX = "Bearer ";

    public static final String TOKEN_PREFIX = "Token-";
    public static final String INFORMATION_PREFIX = "Information-";
    public static final String PERMISSION_PREFIX = "Permission-";
}
