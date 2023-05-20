package com.pika.proxy;

import com.pika.common.Invocation;
import com.pika.protocol.HttpClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * pika
 * 代理类 -> 通过代理模式,使接口调用变得和本地调用一样
 * 主要功能 -> 借助java的Proxy类代理接口类, 在invoke中实现构建Invocation类,以及发送http请求的操作
 */
public class ProxyFactory {
    public static <T> T getProxy(Class<T> interfaceClass) {
        Object instance = Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Invocation invocation = new Invocation(interfaceClass.getName(),
                                method.getName(),
                                method.getParameterTypes(),
                                args);
                        HttpClient httpClient = new HttpClient();
                        return httpClient.send("localhost", 8080, invocation);
                    }
                });
        return (T) instance;
    }

}
