package com.joebrooks.mapshotserver.service;

import com.joebrooks.mapshotserver.domain.OnFailed;
import com.joebrooks.mapshotserver.domain.OnSuccess;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@Service
public class MakeMessageService {


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

    public String makeSuccessMessage(OnSuccess success){
        JSONObject headerJson = getHeader("Success");
        Map<String, Object> map = new HashMap<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd a HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));

        map.put("사용 시간", simpleDateFormat.format(new Date()));

        JSONObject sectionOneJson = getSection(map);
        map.clear();

        map.put("사용 횟수", success.getUsingCount());
        map.put("사용한 기능", success.getUsedFunc());

        JSONObject sectionTwoJson = getSection(map);
        
        return getBlock(headerJson, sectionOneJson, sectionTwoJson).toJSONString();
    }

    public String makeFailedMessage(OnFailed failed){
        JSONObject headerJson = getHeader("Failed");
        Map<String, Object> map = new HashMap<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd a HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));

        map.put("사용 시간", simpleDateFormat.format(new Date()));

        JSONObject sectionOneJson = getSection(map);
        map.clear();

        map.put("에러명", failed.getTitle());
        map.put("메세지", failed.getMessage());

        JSONObject sectionTwoJson = getSection(map);
        map.clear();

        map.put("사용 횟수", failed.getUsingCount());
        map.put("사용한 기능", failed.getUsedFunc());

        JSONObject sectionThreeJson = getSection(map);

        return getBlock(headerJson, sectionOneJson, sectionTwoJson, sectionThreeJson).toJSONString();
    }

    public String makeDailyMessage(int userNumber, int successCount, int failedCount){
        JSONObject headerJson = getHeader("Daily Report");
        Map<String, Object> map = new HashMap<>();
        map.put("사용한 유저", userNumber);

        JSONObject sectionOneJson = getSection(map);

        map.clear();
        map.put("호출 성공 횟수", successCount);
        map.put("호출 실패 횟수", failedCount);


        JSONObject sectionTwoJson = getSection(map);

        return getBlock(headerJson, sectionOneJson, sectionTwoJson).toJSONString();
    }

}
