package com.joebrooks.mapshotserver.infra.sns.util;

import com.joebrooks.mapshotserver.global.dto.ErrorMessage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@Component
public class SlackMessageUtil {

    private JSONObject getHeader(String title){
        JSONObject block = new JSONObject();
        block.put("type", "plain_text");
        block.put("text", title);

        JSONObject header = new JSONObject();
        header.put("type", "header");
        header.put("text", block);

        return header;
    }

    private JSONObject getSection(Map<String, Object> map){
        JSONArray jArr = new JSONArray();

        map.forEach((key, value) -> {
            JSONObject json = new JSONObject();
            json.put("type", "mrkdwn");
            json.put("text", key + ": " + value);

            jArr.add(json);
        });

        JSONObject json = new JSONObject();
        json.put("fields", jArr);
        json.put("type", "section");

        return json;
    }

    private JSONObject getBlock(JSONObject ... jsons){
        JSONArray arr = new JSONArray();

        for(var i : jsons){
            arr.add(i);
        }

        JSONObject json = new JSONObject();
        json.put("blocks", arr);

        return json;
    }

    public String makeErrorMessage(ErrorMessage message) {
        JSONObject headerJson = getHeader("Failed");
        Map<String, Object> map = new HashMap<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd a HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));

        map.put("시간", simpleDateFormat.format(new Date()));

        JSONObject sectionOneJson = getSection(map);
        map.clear();

        map.put("원인", message.getName());
        JSONObject sectionTwoJson = getSection(map);
        map.clear();

        map.put("에러", message.getMessage());
        JSONObject sectionThreeJson = getSection(map);

        return getBlock(headerJson, sectionOneJson, sectionTwoJson, sectionThreeJson).toJSONString();
    }

//    @Override
//    public String makeSuccessMessage(UserInfo param) {
//        JSONObject headerJson = getHeader("Success");
//        Map<String, Object> map = new HashMap<>();
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd a HH:mm:ss");
//        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
//
//        map.put("사용 시간", simpleDateFormat.format(new Date()));
//
//        JSONObject sectionOneJson = getSection(map);
//        map.clear();
//
//        map.put("사용 횟수", param.getUsingCount());
//        map.put("사용한 기능", param.getUsedFunc());
//
//        JSONObject sectionTwoJson = getSection(map);
//
//        return getBlock(headerJson, sectionOneJson, sectionTwoJson).toJSONString();
//    }
//
//    @Override
//    public String makeErrorMessage(UserInfo param) {
//        JSONObject headerJson = getHeader("Failed");
//        Map<String, Object> map = new HashMap<>();
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd a HH:mm:ss");
//        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
//
//        map.put("사용 시간", simpleDateFormat.format(new Date()));
//
//        JSONObject sectionOneJson = getSection(map);
//        map.clear();
//
//        map.put("에러명", param.getCode());
//        map.put("메세지", param.getMessage());
//
//        JSONObject sectionTwoJson = getSection(map);
//        map.clear();
//
//        map.put("사용 횟수", param.getUsingCount());
//        map.put("사용한 기능", param.getUsedFunc());
//
//        JSONObject sectionThreeJson = getSection(map);
//
//        return getBlock(headerJson, sectionOneJson, sectionTwoJson, sectionThreeJson).toJSONString();
//    }

//    @Override
//    public String makeDailyMessage(DailyInfo param) {
//        JSONObject headerJson = getHeader("Daily Report");
//        Map<String, Object> map = new HashMap<>();
//        map.put("사용한 유저", userNumber);
//
//        JSONObject sectionOneJson = getSection(map);
//
//        map.clear();
//        map.put("호출 성공 횟수", successCount);
//        map.put("호출 실패 횟수", failedCount);
//
//
//        JSONObject sectionTwoJson = getSection(map);
//
//        return getBlock(headerJson, sectionOneJson, sectionTwoJson).toJSONString();
//    }
}
