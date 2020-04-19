package com.example.demo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Description 上下文对象帮助类
 * @Author liz
 * @Date 16/04/2020 12:56 上午
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    /**
     * 获取上下文对象
     *
     * @return 当前上下文对象
     */
    public static ApplicationContext getApplicationContext() {
        return ApplicationContextUtil.applicationContext;
    }

    /**
     * 按照名称获取Bean对象
     *
     * @param name  名称
     * @param <T>   对象范型
     * @return  bean
     */
    public static  <T> T getBean(String name) {
        if (Objects.isNull(ApplicationContextUtil.applicationContext)) {
            return null;
        }
        return (T) ApplicationContextUtil.applicationContext.getBean(name);
    }

    /**
     * 按照类型和名称获取bean对象
     *
     * @param name 名称
     * @param tClass 对象类型
     * @param <T>   范型
     * @return  bean
     */
    public static  <T> T getBean(String name, Class<T> tClass) {
        if (Objects.isNull(ApplicationContextUtil.applicationContext)) {
            return null;
        }
        return ApplicationContextUtil.applicationContext.getBean(name, tClass);
    }


}
