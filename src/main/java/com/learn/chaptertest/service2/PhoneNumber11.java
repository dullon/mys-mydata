package com.learn.chaptertest.service2;

import java.util.Objects;

/**
 * 10 始终要覆盖toString
 * 11 谨慎的覆盖clone
 */
public class PhoneNumber11 implements Cloneable{
    private final short areaCode;
    private final short prefix;
    private final short lineNumber;

    public PhoneNumber11(short areaCode, short prefix, short lineNumber) {
        rangCheck(areaCode,999,"area code");
        rangCheck(prefix,999,"prefix");
        rangCheck(lineNumber,9999,"line number");
        this.areaCode = areaCode;
        this.prefix = prefix;
        this.lineNumber = lineNumber;
    }
    public static void rangCheck(int arg,int max,String name){
        if (arg < 0||arg > max){
            throw new IllegalArgumentException(name + ":" + arg );
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber11 that = (PhoneNumber11) o;
        return areaCode == that.areaCode &&
                prefix == that.prefix &&
                lineNumber == that.lineNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(areaCode, prefix, lineNumber);
    }

    @Override
    public String toString() {
        String format = String.format("(%03d) %03d-%04d", areaCode, prefix, lineNumber);
        return "PhoneNumber11{" +
                "areaCode=" + areaCode +
                ", prefix=" + prefix +
                ", lineNumber=" + lineNumber +
                '}';
    }

    @Override
    public Object clone(){
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // will never happen
            throw new InternalError(e);
        }
    }
    /*
    覆盖toString方法注意两条:
        1. 可以设置指定格式的方法，但是 必须声明改格式的相关注释  如：正则表达式 表达的电话号码，注释中给出相应格式
        2. 如果不设置指定的格式，那么 也应该声明相关的注释，用于免责。
     */

    /*
    谨慎的覆盖clone
    值得一提的是，覆盖clone方法 有个约定，必须实现Cloneable接口，但该接口没有任何方法，他的作用是什么呢

    他的作用 就是用于声明 该方法 可以使用Object的受保护的clone方法，因此当被保护的方法被使用覆盖，所以在该类或者其实现类中最好有方法调用失败时捕获异常的功能

    应知应会：实现了Cloneable接口后应该有一个公有的方法覆盖clone，该方法首先调用 super.clone，然后修正任何需要修正的域（数组，容器，对象等）。

    clone 覆盖约束条件太多，经常行将就错，所以一般 都会应用拷贝构造器和拷贝工厂，属于一种类型转换功能。而这洽签是 clone不能实现的，所以除非必要，不要实现Cloneable接口。来增加拷贝功能。

    通则：永远不要让客户去做任何类库能替客户完成的事情，能用到super方法 就不要自己写方法。


    */
}
