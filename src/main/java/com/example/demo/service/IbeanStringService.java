package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description 第二种解决方案
 *      直接把String类型java类代码，加载到IOC容器
 * @Author Administrator
 * @Date 14:04 2020/7/14
 **/
public interface IbeanStringService {


    /**
     * IOC容器中构建bean
     *
     * @throws Exception 异常
     * @param beanName
     * @param beanCode
     */
    void generateBean(String beanName, String beanCode) throws Exception;

    /**
     * 测试策略类的方法
     *
     * @param json 必须有name key { "name":"" }
     * @return  测试结果
     */
    JSONObject test(JSONObject json);


}
