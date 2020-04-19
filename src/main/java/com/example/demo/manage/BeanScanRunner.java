package com.example.demo.manage;

import com.example.demo.service.IBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Description 项目启动时加载本地对象仓库到IOC容器中
 * @Author liz
 * @Date 19/04/2020 9:22 下午
 */
@Component
public class BeanScanRunner implements ApplicationRunner {

    @Autowired
    private IBeanService iBeanService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        iBeanService.generateBean();
        System.out.println("==========>>>>>>>>>>初始化本地对象仓库成功（^o^）");
    }
}
