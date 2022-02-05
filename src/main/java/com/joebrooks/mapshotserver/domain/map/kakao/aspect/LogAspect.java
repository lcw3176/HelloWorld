package com.joebrooks.mapshotserver.domain.map.kakao.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Around("execution(* com.joebrooks.mapshotserver.domain.map.kakao.service.*Service.*(..))")
    public Object printMemoryLog(ProceedingJoinPoint joinPoint) throws Throwable{
        String heapSize = byteCalculation(Long.toString(Runtime.getRuntime().totalMemory()));
        String heapMaxSize = byteCalculation(Long.toString(Runtime.getRuntime().maxMemory()));
        String heapFreeSize = byteCalculation(Long.toString(Runtime.getRuntime().freeMemory()));

        String name = joinPoint.getSignature().getDeclaringTypeName();
        log.debug(name + "." + joinPoint.getSignature().getName() + "()'");
        log.debug("총 힙 사이즈: " + heapMaxSize  + "  현재 힙 사이즈: " + heapSize + "  잔여 힙 사이즈: " + heapFreeSize);

        return joinPoint.proceed();
    }

    private String byteCalculation(String bytes) {
        String retFormat = "0";
        Double size = Double.parseDouble(bytes);

        String[] s = {"bytes", "KB", "MB", "GB", "TB", "PB"};


        if (bytes != "0") {
            int idx = (int) Math.floor(Math.log(size) / Math.log(1024));
            DecimalFormat df = new DecimalFormat("#,###.##");
            double ret = ((size / Math.pow(1024, Math.floor(idx))));
            retFormat = df.format(ret) + " " + s[idx];
        } else {
            retFormat += " " + s[0];
        }

        return retFormat;
    }
}
