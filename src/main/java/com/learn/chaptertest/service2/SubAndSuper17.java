package com.learn.chaptertest.service2;

import java.util.Date;

/**
 * 17 要么为继承而设计，并提供文档说明，要么就禁止继承
 */
public class SubAndSuper17 extends Super{
    private final Date date;

    public SubAndSuper17() {
        this.date = new Date();
    }

    @Override
    public void overrideMe() {
        System.out.println(date);
    }

    public static void main(String [] args){
        SubAndSuper17 ss = new SubAndSuper17();
        /*返回结果
        null
        Fri Mar 15 18:40:55 CST 2019
        */
        ss.overrideMe();
    }
}
/*
* 为了能编写有效的子类，父类必须通过某种形式提供适当的钩子（hook），一遍能够进入到它的内部工作流中，可以是受保护的方法或者受保护的域
*
* 对于为了继承而设计的类，唯一的测试方法就是编写子类，而且必须全面测试，最好有3个子类进行
*
* 为了允许继承，构造器决不能调用可以覆盖的方法。注：这块经常出现面试题，本示例就是典型的构造器内调用可以覆盖的方法
*
* 如果基类实现了Cloneable或者Serializable，注意，clone方法 和 readObject方法都类似于构造器，所以他们不可以调用可以被覆盖的方法，不管是直接调用还是间接调用。
*
* 对于那些并非废了安全地进行子类化而设计和编写文档的类，要禁止子类化。方式 定义为final类，或者 私有化所有构造器并提供静态工厂代替构造器（静态方法返回新建的本类）
* */
class Super{
    public Super() {
        overrideMe();
    }
    public void overrideMe(){

    }
}
