package com.pika;

public class DemoServiceImpl implements DemoService {


    @Override
    public String hello(String name) {
        return "hello " + name;
    }
}
