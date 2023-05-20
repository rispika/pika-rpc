package com.pika;

import com.pika.protocol.HttpServer;
import com.pika.register.LocalRegister;

public class ProviderStarter {

    public static void main(String[] args) {
        LocalRegister.register(DemoService.class.getName(), DemoServiceImpl.class);

        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost", 8080);
    }

}
