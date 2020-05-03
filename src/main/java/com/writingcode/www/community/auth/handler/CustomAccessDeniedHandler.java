package com.writingcode.www.community.auth.handler;

import com.writingcode.www.community.auth.constant.SecurityConstant;
import com.writingcode.www.community.auth.util.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Chavy
 * @date 2020/4/13
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResponseUtil.printCode(httpServletResponse, SecurityConstant.ACCESS_DENY, 401);
    }
}
