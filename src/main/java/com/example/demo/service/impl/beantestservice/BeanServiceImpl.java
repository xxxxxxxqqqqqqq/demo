package com.example.demo.service.impl.beantestservice;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.constant.GloabelContant;
import com.example.demo.service.IBeanService;
import com.example.demo.service.ITestApi;
import com.example.demo.util.ApplicationContextUtil;
import com.example.demo.util.BeanUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author liz
 * @Date 16/04/2020 1:35 上午
 */
@Service
public class BeanServiceImpl implements IBeanService {

    @Value("${beanFilepath}")
    private String beanFilepath;


    @Override
    public void generateBean() throws Exception {
        BeanUtil.inintMyBean(beanFilepath);
        System.out.println("==========>>>>>>>>>>加载本地对象仓库成功（^o^）");
    }

    @Override
    public JSONObject test(JSONObject json) {
        ITestApi iTestApipi = ApplicationContextUtil.getBean(json.getString(GloabelContant.NAME), ITestApi.class);
        return iTestApipi.add(json);
    }
}
