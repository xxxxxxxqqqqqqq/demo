package com.example.demo.util.othermethod;

public class TestCompiler {
    public static void main(String[] args) {
        String code = "import com.alibaba.fastjson.JSONObject;\n" +
                "\n" +
                "public class HelloWorld {\n" +
                "    public static void main(String[] args) {\n" +
                "        for (int i = 1; i < 11; i++) {\n" +
                "            System.out.println(\" Hello World \"+i);\n" +
                "        }\n" +
                "        String json = \"{\\\"name\\\":\\\"matthew\\\",\\\"sport\\\":\\\"football\\\"}\";\n" +
                "        JSONObject jsonObject = JSONObject.parseObject(json);\n" +
                "        String name = jsonObject.getString(\"name\");\n" +
                "        String sport = jsonObject.getString(\"sport\");\n" +
                "        System.out.println(name);\n" +
                "        System.out.println(sport);\n" +
                "    }\n" +
                "}";

        CustomStringJavaCompiler compiler = new CustomStringJavaCompiler(code);
        boolean res = compiler.compiler();
        if (res) {
            System.out.println("编译成功");
            System.out.println("compilerTakeTime：" + compiler.getCompilerTakeTime());
            try {
                compiler.runMainMethod();
                System.out.println("runTakeTime：" + compiler.getRunTakeTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(compiler.getRunResult());
            System.out.println("诊断信息：" + compiler.getCompilerMessage());
        } else {
            System.out.println("编译失败");
            System.out.println(compiler.getCompilerMessage());
        }

    }
}
