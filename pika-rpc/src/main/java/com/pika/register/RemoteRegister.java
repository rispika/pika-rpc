package com.pika.register;

import com.alibaba.fastjson2.JSON;
import com.pika.common.URL;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * 远程注册中心
 */
public class RemoteRegister {

    private static volatile RemoteRegister instance;
    private Jedis jedis;

    private RemoteRegister() {
    }

    public void setAuth(String pwd) {
        jedis.auth(pwd);
    }

    private RemoteRegister(String hostname, Integer port) {
        this.jedis = new Jedis(hostname, port);
    }

    public static RemoteRegister getInstance(String hostname, Integer port) {
        if (instance == null) {
            synchronized (RemoteRegister.class) {
                if (instance == null) {
                    instance = new RemoteRegister(hostname, port);
                }
            }
        }
        return instance;
    }

    private final Map<String, List<String>> remoteRegisterCache = new HashMap<>();

    public void regist(String interfaceName, URL url) {
        String urlJson = JSON.toJSONString(url);
        // 存入redis注册中心
        jedis.lpush(interfaceName, urlJson);
        // 存入缓存
        List<String> urls = remoteRegisterCache.get(interfaceName);
        if (urls == null) {
            urls = new ArrayList<>();
        }
        urls.add(urlJson);
        remoteRegisterCache.put(interfaceName, urls);
    }

    public List<String> get(String interfaceName) {
        List<String> urls = remoteRegisterCache.get(interfaceName);
        if (urls == null) {
            urls = jedis.lrange(interfaceName, 0, -1);
        }
        return urls;
    }

}
