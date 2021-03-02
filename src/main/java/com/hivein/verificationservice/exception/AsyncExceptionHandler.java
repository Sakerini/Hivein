package com.hivein.verificationservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

@Slf4j
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
        log.error("Exception caught: " + throwable.getMessage() + "Method: " + method.getName());
        for (Object param : objects) {
            log.info("Parameter value - " + param);
        }
    }
}
