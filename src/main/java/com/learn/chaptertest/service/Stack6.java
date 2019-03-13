package com.learn.chaptertest.service;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 消除过期的对象引用
 */
public class Stack6 {
    private Object[] elements;//如果该成员是final类型 则clone时会报错
    private int size = 0;
    private static final int DEFAULT_INITIAL = 16;

    public Stack6(){
        elements = new Object[DEFAULT_INITIAL];
    }
    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }
    //弹栈的时候 出来的对象 及时没有被引用 也不会被垃圾回收 ，因为 栈内部会对这些对象维护的过期引用存在内存泄露问题。
    //2 处理方法 清空被弹出的对象
    // 但是 方法依然不是最好的处理方法，最好的方法是调用该引用的对象或变量 结束生命周期，本例就是Stack6这个类结束生命周期时 自然会清空所有栈内对象。
    public Object pop(){
        if (size == 0){
            throw new EmptyStackException();
        }
        //return  elements[--size];
        //2
        Object result = elements[--size];
        elements[size] = null;//**看似简单的方式 却是解决内存泄露的重点。
        return result;
    }

    //扩容时 产生新的数组，引用指向新的 数组，但是 旧的对象依然存在没有只是没有被引用
    private void ensureCapacity(){
        if (elements.length == size){
            elements = Arrays.copyOf(elements,2*size + 1);//扩容把旧的数组迁移到新数组内
        }
    }

    @Override
    public Stack6 clone() {
        try {
            Stack6 result = (Stack6)super.clone();//这属于表层复制，把目标对象基本类型复制过来，如果有复杂的子域则需要二次复制
            result.elements = elements.clone();//克隆时 需要把被克隆对象的子域 同时复制过来，尤其是容器类型。
            return result;
        }catch (CloneNotSupportedException ce){
            throw new AssertionError(":"+this.getClass());
        }
    }
}
