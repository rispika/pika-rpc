package com.pika.common;

import java.io.Serializable;

/**
 * pika
 * Invocation
 * 用于传递接口的信息
 * 包括(接口名称, 方法名称, 方法参数类型, 方法参数)
 * 用于以接口名称为Key存入Map中, value为实现类
 */
public class Invocation implements Serializable {

    private String interfaceName;
    private String methodName;
    private Class[] parameterTypes;
    private Object[] parameters;

    public Invocation() {
    }

    public Invocation(String interfaceName, String methodName,
                      Class<?>[] parameterTypes, Object[] parameters) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.parameters = parameters;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
