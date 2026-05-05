package com.nttoan.handmadeshop.application.interceptor;

import java.util.UUID;

import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestLoggerInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(RequestLoggerInterceptor.class);
    private static final String REQUEST_ID = "REQUEST_ID";
    private static final String START_TIME = "START_TIME";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String requestId = UUID.randomUUID().toString();
        long startTime = System.currentTimeMillis();

        request.setAttribute(REQUEST_ID, requestId);
        request.setAttribute(START_TIME, startTime);

        log.info("[{}] -> {} {} - ", requestId, request.getMethod(), request.getRequestURI());
        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            @Nullable Exception ex) throws Exception {
        String requestId = (String) request.getAttribute(REQUEST_ID);
        long startTime = (long) request.getAttribute(START_TIME);
        long duration = System.currentTimeMillis() - startTime;
        String body = getBody(request);

        log.info("[{}] ← {} {} ({} ms) status={}, body: {}",
                requestId,
                request.getMethod(),
                request.getRequestURI(),
                duration,
                response.getStatus(),
                body);

        if (ex != null) {
            log.error("[{}] ERROR: {}", requestId, ex.getMessage(), ex);
        }
    }

    private String getBody(HttpServletRequest request) {
        if (request instanceof ContentCachingRequestWrapper wrapper) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                return new String(buf);
            }
        }
        return "";
    }
}
