package com.pika;

public class DemoServiceImpl implements DemoService {


    @Override
    public String hello(String name) {
        return "hello: " + name;
    }

    @Override
    public String goodbye(String name) {
        return "goodbye: " + name;
    }
}
