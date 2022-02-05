package com.joebrooks.mapshotserver.domain.map.kakao.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.text.DecimalFormat;

//@Aspect
//@Component
//@Slf4j
//public class LogAspect {
//
//    @Around("execution(* com.joebrooks.mapshotserver.domain.map.kakao.service.*Service.*(..))")
//    public Object printMemoryLog(ProceedingJoinPoint joinPoint) throws Throwable{
//        String name = joinPoint.getSignature().getDeclaringTypeName();
//        String totalHeapSize = byteCalculation(getTotalHeapSize());
//        String usedHeapSize = byteCalculation(getTotalHeapSize() - getFreeHeapSize());
//
//        log.debug(name + "." + joinPoint.getSignature().getName() + "()'");
//        log.debug("총 힙 사이즈: " +  totalHeapSize);
//        log.debug("사용중인 힙 사이즈: " + usedHeapSize);
//        log.debug("메타스페이스 사이즈: " + byteCalculation(getMetaSpaceMemory()));
//
//        return joinPoint.proceed();
//    }
//
//    private long getTotalHeapSize(){
//        return Runtime.getRuntime().totalMemory();
//    }
//
//    private long getFreeHeapSize(){
//        return Runtime.getRuntime().freeMemory();
//    }
//
//
//    private long getMetaSpaceMemory(){
//        for (MemoryPoolMXBean memoryMXBean : ManagementFactory.getMemoryPoolMXBeans()) {
//            if ("Metaspace".equals(memoryMXBean.getName())) {
//                return memoryMXBean.getUsage().getUsed();
//            }
//        }
//
//        return 0;
//    }
//
//    private String byteCalculation(long bytes) {
//        String retFormat = "0";
//        Long size = bytes;
//
//        String[] s = {"bytes", "KB", "MB", "GB", "TB", "PB"};
//
//
//        if (bytes != 0) {
//            int idx = (int) Math.floor(Math.log(size) / Math.log(1024));
//            DecimalFormat df = new DecimalFormat("#,###.##");
//            double ret = ((size / Math.pow(1024, Math.floor(idx))));
//            retFormat = df.format(ret) + " " + s[idx];
//        } else {
//            retFormat += " " + s[0];
//        }
//
//        return retFormat;
//    }
//
//
//}
