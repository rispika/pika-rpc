package com.pika;

import com.pika.common.Invocation;
import com.pika.protocol.HttpClient;
import com.pika.proxy.ProxyFactory;

public class ConsumerStarter {

    public static void main(String[] args) {
        DemoService proxy = ProxyFactory.getProxy(DemoService.class);
        String pika = proxy.hello("pika");
        System.out.println(pika);

    }

}
