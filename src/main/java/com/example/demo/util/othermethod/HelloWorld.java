package com.example.demo.util.othermethod;

import com.alibaba.fastjson.JSONObject;

public class HelloWorld {
    public static void main(String[] args) {
        for (int i = 1; i < 11; i++) {
            System.out.println(" Hello World "+i);
        }
        String json = "{\"name\":\"matthew\",\"sport\":\"football\"}";
        JSONObject jsonObject = JSONObject.parseObject(json);
        String name = jsonObject.getString("name");
        String sport = jsonObject.getString("sport");
        System.out.println(name);
        System.out.println(sport);
    }
}
