package com.joebrooks.mapshotserver.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class DailyInfo {
    private static AtomicInteger userCount = new AtomicInteger(0);
    private static AtomicInteger successCount = new AtomicInteger(0);
    private static AtomicInteger failedCount = new AtomicInteger(0);

    public static int getSuccessCount() {
        return successCount.get();
    }

    public static void setSuccessCount(int successCount) {
        DailyInfo.successCount.set(successCount);
    }

    public static int getFailedCount() {
        return failedCount.get();
    }

    public static void setFailedCount(int failedCount) {
        DailyInfo.failedCount.set(failedCount);
    }

    public static int getUserCount(){
        return userCount.get();
    }

    public static void setUserCount(int userCount){
        DailyInfo.userCount.set(userCount);
    }

    public static void initData(){
        DailyInfo.userCount.set(0);
        DailyInfo.failedCount.set(0);
        DailyInfo.successCount.set(0);
    }


}
