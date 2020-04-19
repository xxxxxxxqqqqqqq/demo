package com.example.demo.util;

import com.example.demo.constant.GloabelContant;
import com.example.demo.service.impl.testapi.TestApiImplC;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import java.io.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Description 处理bean
 * @Author liz
 * @Date 18/04/2020 10:58 下午
 */
public class BeanUtil {

    /** 记录存储过的bean名称 */
    private static Set<String> MY_IOC_NAME_SET = new HashSet<>();

    /**
     * 校验动态注入的bean
     *
     * @param name  bean名称
     * @return 是否注入过
     */
    public static boolean verifyMyBean(String name) {
        return MY_IOC_NAME_SET.contains(name);
    }

    /**
     * 动态载入bean
     *
     * @param name  bean名称
     * @param object 对象
     * @throws Exception    异常
     */
    public synchronized static void addBean(String name, Object object) throws Exception {
        if (verifyMyBean(name)) {
            throw new Exception("bean重复");
        }
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) ApplicationContextUtil.getApplicationContext().getAutowireCapableBeanFactory();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(object.getClass());
        defaultListableBeanFactory.registerBeanDefinition(name, beanDefinitionBuilder.getBeanDefinition());
        MY_IOC_NAME_SET.add(name);
        System.out.println(MY_IOC_NAME_SET);
    }

    /**
     * 动态移除bean
     *
     * @param beanName Bean名称
     */
    public static void removeBean(String beanName) {
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) ApplicationContextUtil.getApplicationContext().getAutowireCapableBeanFactory();
        defaultListableBeanFactory.removeBeanDefinition(beanName);
    }

    /**
     * 持久化对象
     *
     * @param object  对象
     * @param path  保存地址
     * @param beanName 容器内的bean名称
     */
    public static void saveObjToFile(Object object, String path, String beanName) {
        ObjectOutputStream oos = null;
        try {
            // 创建对象输出流  path + object.getClass().getName() + "-" + beanName
            oos = new ObjectOutputStream(new FileOutputStream(new StringBuilder().append(path)
                                                                        .append(object.getClass().getName())
                                                                        .append(GloabelContant.SHORT_LINE)
                                                                        .append(beanName).toString()));
            // 写出对象文件
            oos.writeObject(object);
            // 关闭流
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(oos)) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        // 需要新的拓展类，只需要在此生成序列化对象文件，上传至本地对象仓库即可
        BeanUtil.saveObjToFile(new TestApiImplC(), "/Users/liz/Documents/test/", "C");
    }

    /**
     * 从文件中反序列化成对象
     *
     * @param objFilePath 文件绝对地址
     * @param tClass    对象类型
     * @param <T>   对象范型
     * @return  反序列化出来的对象
     */
    public static <T> T getObjFromFile(String objFilePath, Class<T> tClass){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(objFilePath));
            T person= (T) ois.readObject();
            return person;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 初始化本地仓库
     *
     * @param path
     */
    public static void inintMyBean(String path) throws Exception {
        try {
            File file = new File(path);
            for (File f : file.listFiles()) {
                // 跳过隐藏文件
                if (f.isHidden()){
                    continue;
                }
                if (f.isDirectory()) {
                    continue;
                }
                if (f.isFile()) {
                    String[] nameArr = f.getName().split("-"); // 类名-bean名称
                    Class<?> aClass = Class.forName(nameArr[0]);
                    Object o = BeanUtil.getObjFromFile(path + f.getName(), aClass);
                    BeanUtil.addBean(nameArr[1], o);
                }
            }
        } catch (ClassNotFoundException e) {
            // 可以考虑持久化错误日志
            System.err.println("本地对象仓库异常，请检查本地对象仓库！！！" + e.getMessage());
            throw new Exception("本地对象仓库异常，请检查本地对象仓库！！！");
        } catch (Exception e) {
            System.err.println("初始化本地对象仓库异常！！！" + e.getMessage());
            throw new Exception("初始化本地对象仓库异常！！！");
        }
    }

}
