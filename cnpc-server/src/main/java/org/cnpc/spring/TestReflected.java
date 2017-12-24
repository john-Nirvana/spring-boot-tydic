package org.cnpc.spring;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author baoxingzheng
 */
public class TestReflected {

    public List<Class> getClasssFromJarFile(String jarPath, String filePath) {
        List<Class> clazzs = new ArrayList<Class>();

        JarFile jarFile = null;
        try {
            jarFile = new JarFile(jarPath);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        List<JarEntry> jarEntryList = new ArrayList<JarEntry>();

        Enumeration<JarEntry> ee = jarFile.entries();
        while (ee.hasMoreElements()) {
            JarEntry entry = (JarEntry) ee.nextElement();
            // 过滤我们出满足我们需求的东西
            if (entry.getName().startsWith(filePath) && entry.getName().endsWith(".class")) {
                try {
                    /**
                     * 加载各个中心服务接口路径下的方法
                     */
                    Class<?> test = Class.forName("com.auth0.jwt.interfaces.Clock");
                    Method[] methods = test.getMethods();
                    for (Method method : methods) {
                        String methodName = method.getName();
                        System.out.println("方法名称:" + methodName);
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        for (Class<?> clas : parameterTypes) {
                            String parameterName = clas.getName();
                            System.out.println("参数名称:" + parameterName);
                        }
                        System.out.println("*****************************");
                    }
                } catch (Exception e) {
                    System.out.print(e.toString());
                }


                jarEntryList.add(entry);
            }
        }
        for (JarEntry entry : jarEntryList) {
            String className = entry.getName().replace('/', '.');
            className = className.substring(0, className.length() - 6);

            try {
                clazzs.add(Thread.currentThread().getContextClassLoader().loadClass(className));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return clazzs;
    }

    public static void main(String[] args) {
        TestReflected test = new TestReflected();
        /**
         * 循环加载各个中心api 需要加载各个中心用户组信息  用户组信息可以通过xml获取
         * 但为了加载效率 暂定为硬代码 后续如果需要扩展中心 需要重新加入
         */
        test.getClasssFromJarFile("D:\\java-jwt-3.3.0.jar", "com/auth0/jwt/interfaces");
    }

}
