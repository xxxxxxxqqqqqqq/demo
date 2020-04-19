package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.constant.GloabelContant;
import com.example.demo.service.IBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @Description 测试接口
 * @Author liz
 * @Date 16/04/2020 1:29 上午
 */
@RestController
public class BeanTestController {

    @Autowired
    private IBeanService iBeanService;

    // 注入bean
    @RequestMapping("/generate")
    public Object generateBean() throws Exception {
        iBeanService.generateBean();
        return GloabelContant.OK;
    }

    // 测试策略类  {"name":"A"}
    @RequestMapping(path = "/test", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public Object generateBean(@RequestBody JSONObject jsonObject) {
        if (Objects.isNull(jsonObject)) {
            return GloabelContant.ERROR;
        }
        System.out.println("请求参数：" + jsonObject);
        return iBeanService.test(jsonObject);
    }


}
