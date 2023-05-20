package com.pika.register;

import java.util.HashMap;
import java.util.Map;

/**
 * pika
 * 主要维护interfaceMap
 * interfaceMap -> 用于保存Invocation对象传递的信息, 以及其接口的实现类信息
 * 方便获取其实现类来调用方法
 */
public class LocalRegister {

    private static final Map<String, Class<?>> interfaceMap = new HashMap<>();

    public static void register(String interfaceName, String version, Class<?> implClass) {
        interfaceMap.put(interfaceName + version, implClass);
    }
    public static void register(String interfaceName, Class<?> implClass) {
        register(interfaceName, "1.0", implClass);
    }

    public static Class<?> get(String interfaceName, String version) {
        return interfaceMap.get(interfaceName + version);
    }
    public static Class<?> get(String interfaceName) {
        return get(interfaceName, "1.0");
    }

}
