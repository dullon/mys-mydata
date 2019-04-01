package com.learn.chaptertest.service3;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 *  25 列表优于数组
 *  26 优先使用泛型
 *
 *  25：数组的缺点：因为数组是斜变的且可具体化的，所以向上转型不会报错，编译可能会出现数组类型错误，而泛型是不可变的
 *  且是可擦除的。
 *  所以两者不能很好的混合使用，经常会遇到编译错误或者警告，而列表却可以和泛型很好的混合使用，所以列表优于数组
 *
 *  26:如果某个类的功能可以进一步的更广泛的使用，我们优先考虑泛型来强化，即泛型化。
 *  引用泛型对象不能以基本类型引用，只能以引用类型来引用。否则会报错
 *
 *  List<E extends Map>  用extends 修饰符 可以让泛型 有类型限制，这叫做限制的类型参数
 *  List<?> 通配符类型参数 用？通配符，代替原生态类型。
 *  示例：
 */
/*
泛型前：
 */
public class Stacks {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INIT_CAPA = 16;

    public Stacks(){
        elements = new Object[DEFAULT_INIT_CAPA];
    }
    public void push(Object e){
        ensureCapacity();
        elements[size ++] = e;
    }
    public Object pop(){
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object result = elements[--size];
        elements[size] = null;
        return result;
    }
    public boolean isEmpty(){
        return size == 0 ;
    }
    private void ensureCapacity(){
        if (elements.length == size){
            elements = Arrays.copyOf(elements,2*size + 1);
        }
    }
}
/*
泛型化
 */
 class Stacks2<E> {
    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INIT_CAPA = 16;

    @SuppressWarnings("unchecked")
    public Stacks2(){
        elements = (E[]) new Object[DEFAULT_INIT_CAPA];
    }
    public void push(E e){
        ensureCapacity();
        elements[size ++] = e;
    }
    public E pop(){
        if (size == 0) {
            throw new EmptyStackException();
        }
        E result = elements[--size];
        elements[size] = null;
        return result;
    }
    public boolean isEmpty(){
        return size == 0 ;
    }
    private void ensureCapacity(){
        if (elements.length == size){
            elements = Arrays.copyOf(elements,2*size + 1);
        }
    }
}