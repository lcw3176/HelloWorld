package com.joebrooks.mapshotserver.global.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@Component
public class QueryGenerator {

    public MultiValueMap<String, String> getMaps(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<Map<String,String>> typeRef = new TypeReference<Map<String,String >>(){};

        Map<String, String> result = mapper.convertValue(obj, typeRef);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.setAll(result);

        return params;
    }

}
