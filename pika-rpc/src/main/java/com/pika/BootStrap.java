package com.pika;

import com.pika.common.URL;
import com.pika.protocol.HttpServer;
import com.pika.register.LocalRegister;
import com.pika.register.RemoteRegister;

public class BootStrap {

    private RemoteRegister register;
    private URL url;
    private String registerHostname; // "192.168.6.128"
    private Integer registerPort;
    private String registerPwd;
    public BootStrap addInterface(Class<?> interfaceClass, Class<?> implClass) {
        // 注册接口
        LocalRegister.register(interfaceClass.getName(), implClass);
        register.regist(interfaceClass.getName(), url);
        return this;
    }

    public BootStrap setRegister(RemoteRegister register) {
        this.register = register;
        return this;
    }

    public BootStrap setUrl(URL url) {
        this.url = url;
        return this;
    }

    public BootStrap setPort(Integer port) {
        this.url.setPort(port);
        return this;
    }

    public BootStrap setRegisterHostname(String registerHostname) {
        this.registerHostname = registerHostname;
        return this;
    }

    public BootStrap setRegisterPort(Integer registerPort) {
        this.registerPort = registerPort;
        return this;
    }

    public BootStrap setRegisterPwd(String registerPwd) {
        this.registerPwd = registerPwd;
        return this;
    }

    public BootStrap build() {
        // 注册中心服务注册
        register = RemoteRegister.getInstance(registerHostname, registerPort);
        if (registerPwd != null) {
            register.setAuth(registerPwd);
        }
        return this;
    }

    public BootStrap run() {
        HttpServer httpServer = new HttpServer();
        httpServer.start(url);
        return this;
    }

    public static BootStrap boot() {
        BootStrap bootStrap = new BootStrap();
        bootStrap.url = new URL("localhost", 2333);
        bootStrap.registerHostname = "localhost";
        bootStrap.registerPort = 6379;
        bootStrap.registerPwd = null;
        return bootStrap;

    }

}
