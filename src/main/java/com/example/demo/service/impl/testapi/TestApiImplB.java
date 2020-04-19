package com.example.demo.service.impl.testapi;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.ITestApi;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author liz
 * @Date 18/04/2020 11:12 下午
 */
@Service("B")
public class TestApiImplB implements ITestApi {
    @Override
    public JSONObject add(JSONObject json) {
        json.put("TestApiImpl", "B");
        return json;
    }
}
