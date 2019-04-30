package com.learn.chaptertest.service4;

import java.util.Date;

/**
 * 39 ：必要时进行保护性拷贝
 * 假设类的客户端会尽其所能来破坏这个类的约束条件，因此你必须保护性的设计程序。
 *
 * 如果类具有从客户端得到或者返回到客户端的可变组件，类就必须保护性的拷贝这些组件。如果拷贝的成本受到限制，并且类信任他的客户端不会进行修改，或者恰当的修改，那么就需要在文档中指明客户端调用者的责任（不的修改或者如何有效修改）。
 * 特别是当你的可变组件的生命周期很长，或者会多层传递时，隐藏的问题往往暴漏出来就很可怕。
 * 基于这种形式，最好使用构造器或者静态工厂模式
 */
public final class Period {
    private final Date start;
    private final Date end;
    public Period(Date start, Date end) {
        //没有进行复制保护
        /*if(start.compareTo(end) > 0){
            throw new IllegalArgumentException(start + " after " + end);
        }
        this.start = start;
        this.end = end;*/
        //Tue Apr 30 16:11:09 CST 2019
        //Sun Apr 30 16:11:09 CST 1978
        //------------------------------------------
        //进行了复制保护
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
        if(start.compareTo(end) > 0){
            throw new IllegalArgumentException(start + " after " + end);
        }
        //Tue Apr 30 16:14:44 CST 2019
        //Tue Apr 30 16:14:44 CST 2019
    }

    public Date start(){
        //return start; 二次攻击时 对方生成不可信实例，访问本方法达到篡改意图
        return new Date(start.getTime());
    }

    public Date end(){
        //return end; 二次攻击时 对方生成不可信实例，访问本方法达到篡改意图
        return new Date(end.getTime());
    }
    //这里修改了final的值 并没有报错因为Date类本身是可变的。
    public static void main(String []args){
        Date start = new Date();
        Date end = new Date();
        Period period = new Period(start, end);
        System.out.println(period.end());
        //end.setYear(78);
        period.end().setYear(78);//二次攻击 通过访问参数方法达到修改目的
        System.out.println(period.end());

    }
}
