package com.pika;

public class NiceServiceImpl implements NiceService{
    @Override
    public String nice(String msg) {
        return "nice " + msg;
    }
}
