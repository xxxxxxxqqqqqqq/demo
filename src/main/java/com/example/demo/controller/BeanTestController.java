package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.constant.GloabelContant;
import com.example.demo.service.IBeanSerializationService;
import com.example.demo.service.IbeanStringService;
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
    private IBeanSerializationService iBeanSerializationService;
    @Autowired
    private IbeanStringService ibeanStringService;

    // 注入bean
    @RequestMapping("/generate1")
    public Object generateBean() throws Exception {
        iBeanSerializationService.generateBean();
        return GloabelContant.OK;
    }

    // 测试策略类  {"name":"A"}
    @RequestMapping(path = "/test1", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public Object test(@RequestBody JSONObject jsonObject) {
        if (Objects.isNull(jsonObject)) {
            return GloabelContant.ERROR;
        }
        System.out.println("请求参数：" + jsonObject);
        return iBeanSerializationService.test(jsonObject);
    }

    // 注入bean
    // 用下面main方法打印出来的测试C策略类
    @RequestMapping("/generate2")
    public Object generateBean2(@RequestBody JSONObject jsonObject) throws Exception {
        ibeanStringService.generateBean(jsonObject.getString(GloabelContant.beanName), jsonObject.getString(GloabelContant.beanCode));
        return GloabelContant.OK;
    }

    // 测试策略类  {"name":"A"}

    @RequestMapping(path = "/test2", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public Object test2(@RequestBody JSONObject jsonObject) {
        if (Objects.isNull(jsonObject)) {
            return GloabelContant.ERROR;
        }
        System.out.println("请求参数：" + jsonObject);
        return ibeanStringService.test(jsonObject);
    }




    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(GloabelContant.beanName, "C");
        jsonObject.put(GloabelContant.beanCode, "package com.example.demo.service.impl.testapi;\n" +
                "\n" +
                "import com.alibaba.fastjson.JSONObject;\n" +
                "import com.example.demo.service.ITestApi;\n" +
                "\n" +
                "import java.io.Serializable;\n" +
                "\n" +
                "/**\n" +
                " * @Description 第三个策略类，不加service注解，动态加载到IOC容器\n" +
                " * @Author liz\n" +
                " * @Date 19/04/2020 12:35 上午\n" +
                " */\n" +
                "public class TestApiImplC implements ITestApi, Serializable {\n" +
                "\n" +
                "    private final static long serialVersionUID = 123456789L;\n" +
                "\n" +
                "    @Override\n" +
                "    public JSONObject add(JSONObject json) {\n" +
                "        json.put(\"TestApiImplC\", \"C\");\n" +
                "        return json;\n" +
                "    }\n" +
                "}\n");
        System.out.println(jsonObject.toString());
    }
}
