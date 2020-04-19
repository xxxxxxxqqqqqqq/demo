package com.example.demo.util.othermethod;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description TODO
 * @Author liz
 * @Date 16/04/2020 1:42 上午
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("liz");

        String s = "{\"user\":\"lizz\"}";
        JSONObject jsonObject = JSONObject.parseObject(s);

    }

}
