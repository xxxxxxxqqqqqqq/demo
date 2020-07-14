package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description 第一种解决方案
 *      将对象序列化成为文件加载到IOC容器
 * @Author liz
 * @Date 16/04/2020 1:33 上午
 */
public interface IBeanSerializationService {

    /**
     * IOC容器中构建bean
     *
     * @throws Exception 异常
     */
    void generateBean() throws Exception;

    /**
     * 测试策略类的方法
     *
     * @param json 必须有name key { "name":"" }
     * @return  测试结果
     */
    JSONObject test(JSONObject json);

}
