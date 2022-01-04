package com.joebrooks.mapshotserver.infra.sns.util;

public interface ISnsMessageUtil<T> {
    String makeMessage(T param);
//    String makeSuccessMessage(T param);
//    String makeErrorMessage(T param);
//    String makeDailyMessage(T param);
}
