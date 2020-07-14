package com.example.demo.service.impl.beanstringservice;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.constant.GloabelContant;
import com.example.demo.service.ITestApi;
import com.example.demo.service.IbeanStringService;
import com.example.demo.util.ApplicationContextUtil;
import groovy.lang.GroovyClassLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author Administrator
 * @Date 14:09 2020/7/14
 **/
@Service
@Slf4j
public class BeanStringService implements IbeanStringService {

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public void generateBean(String beanName, String beanCode){
        // 获取bean工厂
        ConfigurableListableBeanFactory beanFactory = ((ConfigurableApplicationContext) applicationContext).getBeanFactory();
        DefaultListableBeanFactory listableBeanFactory = (DefaultListableBeanFactory) beanFactory;

        if (listableBeanFactory.containsBeanDefinition(beanName)) {
            listableBeanFactory.removeBeanDefinition(beanName);
        }

        // 利用classLoader加载java代码
        Class aClass = new GroovyClassLoader(this.getClass().getClassLoader()).parseClass(beanCode);

        // 定义bean属性
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(aClass);
        // 设置为外部的bean
        beanDefinition.setSynthetic(true);

        // 注册进IOC容器
        ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition(beanName, beanDefinition);
    }

    @Override
    public JSONObject test(JSONObject json) {
        ITestApi iTestApipi = ApplicationContextUtil.getBean(json.getString(GloabelContant.NAME), ITestApi.class);
        return iTestApipi.add(json);
    }

//    /**
//     * 重新加载平台实现
//     *
//     * @param platformCode 平台编码
//     */
////    @Override
//    public void reloadPlatformImpl(String platformCode) {
//        log.info("-------------重新加载平台实现"+platformCode);
//        try {
//            if(ApplicationContextUtil.getDefaultListableBeanFactory().containsBeanDefinition(platformCode)){
//                log.info("删除bean:"+platformCode);
//                ApplicationContextUtil.getDefaultListableBeanFactory().removeBeanDefinition( platformCode);
//            }
//            PlatformJavaCode platformJavaCode=PayPlatformImpl.queryPlatformJavaCode(platformCode);
//            Class objClazz = new GroovyClassLoader(this.getClass().getClassLoader()).parseClass(platformJavaCode.getJavaCode());
//            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
//            beanDefinition.setBeanClass(objClazz);
//            beanDefinition.setSynthetic(true);
//            ApplicationContextUtil.getDefaultListableBeanFactory().registerBeanDefinition(platformCode,beanDefinition);
//        }catch (Exception ex) {
//            log.error("重新加载支付平台失败",ex);
//        }
//
//    }

}
