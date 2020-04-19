package com.example.demo.service.impl.testapi;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.ITestApi;
import org.springframework.stereotype.Service;

/**
 * @Description 策略类A
 * @Author liz
 * @Date 18/04/2020 11:12 下午
 */
@Service("A")
public class TestApiImplA implements ITestApi {
    @Override
    public JSONObject add(JSONObject json) {
        json.put("TestApiImplA", "A");
        return json;
    }
}
