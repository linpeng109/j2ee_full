package com.cn.dwr;

//import org.directwebremoting.annotations.RemoteMethod;
//import org.directwebremoting.annotations.RemoteProxy;

//@RemoteProxy(name = "helloWorld")
public class DWRHelloWorld {
    public String helloWorld;

    public String getHelloWorld() {
        return helloWorld;
    }

    // @RemoteMethod
    public String hello(String name) {
        String result = name + ",  " + helloWorld + "!";
        return result;
    }

    public void setHelloWorld(String helloWorld) {
        this.helloWorld = helloWorld;
    }
}