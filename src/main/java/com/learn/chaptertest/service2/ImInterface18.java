package com.learn.chaptertest.service2;

/**
 * 18 接口优于抽象类
 */
public class ImInterface18 {

}
/*
* 现有的类很容易被更新，以实现新的接口
* 接口是定义mixin（混合类型）的理想选择
* 接口允许我们构造非层次结构的类型框架
*
* 通过导出的每个重要接口都提供一个抽象的骨架实现类，把接口和抽象类的优点结合起来。
* 集合的所有接口 都有自己的骨架实现类，所以定义重要接口的时候 最好也定义骨架类来完成通用方法的实现。
*
* 抽象类比接口容易演变，接口一旦定义，如果修改，会影响所有实现他的类。
* 而抽象类 扩展一个方法后 再定义一个合理的默认实现，就不会影响继承他的类。
* 即使接口和他的骨架类 同时更新了方法，依然还会有漏洞，因为可能会有部分类直接实现接口而没有通过骨架类。
* 所以定义接口一定要考虑完善 完成后就不要轻易修改，可以扩展子接口的方式。
*
* */
interface A{
    String get();
    void set(String a);
}

abstract class B implements A{
    @Override
    public String get() {
        return null;
    }

}

class C implements A{

    @Override
    public String get() {
        return null;
    }

    @Override
    public void set(String a) {
        System.out.println("324");
    }

    public String setA(){
        return new D().get();
    }
    class D extends B{

        @Override
        public void set(String a) {
            System.out.println("5345");
        }
    }
    //该方法时 静态工厂 利用B骨架类生产A的匿名实例（使用结束就销毁）。同时也是多重继承的一种方式。
    static A getA(final String s){
        if (s == null ){
            throw new NullPointerException();
        }
        return new B() {
            public String get(String string) {
                return s.replaceAll("-","") ;
            }

            @Override
            public void set(String a) {
                System.out.println(s);
            }
            public int size(){
                return s.length();
            }
        };
    }
}