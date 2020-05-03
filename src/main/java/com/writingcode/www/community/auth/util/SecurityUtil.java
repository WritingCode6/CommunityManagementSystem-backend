package com.writingcode.www.community.auth.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.writingcode.www.community.auth.constant.SecurityConstant;
import com.writingcode.www.community.auth.properties.SecurityProperties;
import com.writingcode.www.community.auth.source.IDataStore;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

/**
 * @author Chavy
 * @date 2020/4/13
 */
@Slf4j
@Component
@AllArgsConstructor
public class SecurityUtil {

    public JwtUtil jwtUtil;

    private IDataStore dataStore;

    private SecurityProperties securityProperties;

    public static String getUserId() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static <T> T getUserDetail(Class<T> tClass) {
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (details instanceof String) {
            return JSONObject.parseObject((String) details, tClass);
        } else {
            return null;
        }
    }

    public String login(HttpServletRequest request, List<String> userId, List<String> permissionList, Object userDetail) {
        String userIdString = Base64.getEncoder().encodeToString(JSONObject.toJSONString(userId).getBytes());
        return login(request, userIdString, permissionList, userDetail);
    }

    public String login(HttpServletRequest request, String userId, List<String> permissionList, Object userDetail) {
        String token = login(userId, permissionList, userDetail);
        if (securityProperties.isEnableSession()) {
            request.getSession().setAttribute(SecurityConstant.AUTHORIZATION_HEADER, token);
        }
        return token;
    }

    public String login(List<String> userId, List<String> permissionList, Object userDetail) {
        String userIdString = Base64.getEncoder().encodeToString(JSONObject.toJSONString(userId).getBytes());
        return login(userIdString, permissionList, userDetail);
    }

    public String login(String userId, List<String> permissionList, Object userDetail) {
        String uuid = UUID.randomUUID().toString() + UUID.randomUUID().toString();
        String token = jwtUtil.createJwt(uuid, securityProperties.getExpire());

        boolean result = dataStore.put(SecurityConstant.TOKEN_PREFIX + uuid,
                userId,
                securityProperties.getExpire());

        result = result && dataStore.put(SecurityConstant.PERMISSION_PREFIX + userId,
                JSONArray.toJSONString(permissionList),
                securityProperties.getExpire());

        result = result && dataStore.put(SecurityConstant.INFORMATION_PREFIX + userId,
                JSONObject.toJSONString(userDetail),
                securityProperties.getExpire());

        if (result) {
            return SecurityConstant.STATE_PREFIX + token;
        } else {
            return SecurityConstant.SIGN_FAIL;
        }
    }
}
