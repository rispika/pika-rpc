package com.pika.loadbalance;

import com.alibaba.fastjson2.JSON;
import com.pika.common.URL;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class LoadBalance {

    public static URL random(List<String> strUrls) {
        if (strUrls == null || strUrls.size() == 0) return null;
        Random random = new Random();
        int index = random.nextInt(strUrls.size());
        return JSON.parseObject(strUrls.get(index), URL.class);
    }

}
