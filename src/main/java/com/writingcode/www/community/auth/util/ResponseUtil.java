package com.writingcode.www.community.auth.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.writingcode.www.community.auth.constant.SecurityResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Chavy
 */
public class ResponseUtil {

    public static <T> void printCode(HttpServletResponse response, SecurityResult<T> result, Integer statusCode) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(statusCode);
        PrintWriter writer = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(result);
        writer.print(json);
        writer.close();
        response.flushBuffer();
    }
}
