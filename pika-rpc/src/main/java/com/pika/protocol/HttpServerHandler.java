package com.pika.protocol;

import com.pika.common.Invocation;
import com.pika.register.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * 处理逻辑
 */
public class HttpServerHandler {

    public static void handler(HttpServletRequest req, HttpServletResponse res) {
        try {
            Invocation invocation = (Invocation) new ObjectInputStream(req.getInputStream()).readObject();
            String interfaceName = invocation.getInterfaceName();
            Class<?> classImpl = LocalRegister.get(interfaceName);
            Method method = classImpl.getMethod(invocation.getMethodName(), invocation.getParameterTypes());
            Object result = method.invoke(classImpl.newInstance(), invocation.getParameters());
            IOUtils.write((String) result, res.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

}
