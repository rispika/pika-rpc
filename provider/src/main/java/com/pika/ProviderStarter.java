package com.pika;

import com.pika.common.URL;
import com.pika.protocol.HttpServer;
import com.pika.register.LocalRegister;
import com.pika.register.RemoteRegister;

public class ProviderStarter {

    public static void main(String[] args) {
        // 注册接口
        LocalRegister.register(DemoService.class.getName(), DemoServiceImpl.class);
        // 注册中心服务注册
        URL url = new URL("localhost", 8080);
        RemoteRegister register = RemoteRegister.getInstance("192.168.6.128", 6379);
        register.setAuth("123456");
        register.regist(DemoService.class.getName(), url);
        HttpServer httpServer = new HttpServer();
        httpServer.start(url);
    }

}
