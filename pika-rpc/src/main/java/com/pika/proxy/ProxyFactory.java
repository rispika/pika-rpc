package com.pika.proxy;

import com.pika.common.Invocation;
import com.pika.common.URL;
import com.pika.loadbalance.LoadBalance;
import com.pika.protocol.HttpClient;
import com.pika.register.RemoteRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
                        // 服务发现
                        RemoteRegister remoteRegister = RemoteRegister.getInstance("192.168.6.128", 6379);
                        remoteRegister.setAuth("123456");
                        List<String> urls = remoteRegister.get(interfaceClass.getName());
                        // 发送失败充实
                        String result = null;
                        int cnt = 3;
                        while (cnt > 0) {
                            try {
                                // 负载均衡
                                URL url = LoadBalance.random(urls);
                                result = httpClient.send(url, invocation);
                                break; // 跳出循环
                            } catch (Exception e){
                                cnt--; // cnt减1继续循环
                                TimeUnit.SECONDS.sleep(3);
                            }
                        }
                        return result;
                    }
                });
        return (T) instance;
    }

}
