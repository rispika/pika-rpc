package com.pika;

import java.util.TreeSet;

public class ProviderStarter {

    public static void main(String[] args) {
        BootStrap.boot()
                // 配置信息
                .setPort(2333)
                .setRegisterHostname("192.168.6.128")
                .setRegisterPort(6379)
                .setRegisterPwd("123456")
                .build()
                // 添加接口
                .addInterface(DemoService.class, DemoServiceImpl.class)
                .addInterface(NiceService.class, NiceServiceImpl.class)
                .run();
    }

}
