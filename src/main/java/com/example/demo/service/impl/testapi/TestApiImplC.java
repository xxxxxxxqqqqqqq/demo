package com.example.demo.service.impl.testapi;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.ITestApi;

import java.io.Serializable;

/**
 * @Description 第三个策略类，不加service注解，动态加载到IOC容器
 * @Author liz
 * @Date 19/04/2020 12:35 上午
 */
public class TestApiImplC implements ITestApi, Serializable {

    private final static long serialVersionUID = 123456789L;

    @Override
    public JSONObject add(JSONObject json) {
        json.put("TestApiImplC", "C");
        return json;
    }
}
