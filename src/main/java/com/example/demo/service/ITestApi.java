package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description 测试策略类
 * @Author liz
 * @Date 18/04/2020 11:10 下午
 */
public interface ITestApi {

    /**
     * 测试策略方法
     *
     * @param json
     * @return
     */
    JSONObject add(JSONObject json);

}
