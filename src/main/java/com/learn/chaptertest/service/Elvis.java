package com.learn.chaptertest.service;

import java.io.Serializable;

/**
 * 用私有的构造器或者枚举类型强化单例模式（Singleton）属性
 */
public class Elvis implements Serializable {

    private static final long serialVersionUID = 1L;
    // 1 公有成员变量 public static final Elvis INSTANCE = new Elvis();
    //2
    private static final Elvis INSTANCE = new Elvis();

    private Elvis(){}
    //2
    public static Elvis getInstance(){
        return INSTANCE;
    }
    //防止反序列列化时 每次创建新的对象，这个方法可以阻止创建，并调用已经创建的实例
    private Object readResolve(){
        return INSTANCE;
    }
    //缺点，反射方法能调用私有的构造方法，可以通过修改构造器，创建第二个实例时报错即可

    //3 单元素枚举类型就是一种单例。而且不怕反射调用。序列化也无序添加readResolve方法
    public enum Elvis2{
        INSTANCE2;
    }

}
