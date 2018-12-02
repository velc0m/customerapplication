package com.vitali.cloud.jlong.customerapplication;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Aspect
public class LoggingAroundAspect {

    private Log log = LogFactory.getLog(getClass());

    @Around("execution(* com.vitali.cloud.jlong.customerapplication.CustomerService.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalDateTime start = LocalDateTime.now();

        Throwable toThrow = null;
        Object returnValue = null;

        try {
            returnValue = joinPoint.proceed();
        } catch (Throwable throwable) {
            toThrow = throwable;
        }

        LocalDateTime stop = LocalDateTime.now();

        log.info("starting @ " + start);
        log.info("finish @ " + stop + " with duration " + stop.minusNanos(start.getNano()));

        if (toThrow != null) {
            throw toThrow;
        }

        return returnValue;
    }
}
