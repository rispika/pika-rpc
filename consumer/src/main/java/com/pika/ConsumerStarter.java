package com.pika;

import com.pika.common.Invocation;
import com.pika.protocol.HttpClient;
import com.pika.proxy.ProxyFactory;

public class ConsumerStarter {

    public static void main(String[] args) {
        DemoService proxy = ProxyFactory.getProxy(DemoService.class);
        String pika = proxy.hello("pika");
        String pika1 = proxy.goodbye("pika");
        System.out.println(pika);
        System.out.println(pika1);
        NiceService niceService = ProxyFactory.getProxy(NiceService.class);
        System.out.println(niceService.nice("boy~~"));

    }

}
