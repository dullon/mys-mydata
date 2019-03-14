package com.learn.chaptertest.service2;

import java.util.Collection;
import java.util.Set;

/**
 * 16 复合化优先于继承
 *
 * 这个类是包装类 就是增强具体功能的实现
 * @param <E>
 */
public class InstrumentedSet16<E> extends ForwarddingSet16<E> {
    private int count = 0;

    public InstrumentedSet16(Set<E> set){
        super(set);
    }

    @Override
    public boolean add(E e) {
        count ++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        count += c.size() ;
        return super.addAll(c);
    }

    public int getCount() {
        return count;
    }
    /*
    * 普通的具体类进行跨包边界的继承时非常危险的，因为继承打破了类的封装性。
    * 此种优化方式用到了复合设计，在新的类中增加一个私有域，他引用现有类的一个实例。
    * 新类中的所有方法都可以调用被包含现有类实例中对应的方法，并返回他的结果，这被称为转发。
    *
    * 此种模式就是装饰者模式（Decorator）
    * 他指的是在不必改变原类文件和使用继承的情况下，动态地扩展一个对象的功能。它是通过创建一个包装对象，也就是装饰来包裹真实的对象。
    * （1） 装饰对象和真实对象有相同的接口。这样客户端对象就能以和真实对象相同的方式和装饰对象交互。
      （2） 装饰对象包含一个真实对象的引用（reference）
      （3） 装饰对象接受所有来自客户端的请求。它把这些请求转发给真实的对象。
      （4） 装饰对象可以在转发这些请求以前或以后增加一些附加功能。这样就确保了在运行时，不用修改给定对象的结构就可以在外部增加附加的功能。在面向对象的设计中，通常是通过继承来实现对给定类的功能扩展。
      适用性
        以下情况使用Decorator模式
        1. 需要扩展一个类的功能，或给一个类添加附加职责。
        2. 需要动态的给一个对象添加功能，这些功能可以再动态的撤销。
        3. 需要增加由一些基本功能的排列组合而产生的非常大量的功能，从而使继承关系变的不现实。
        4. 当不能采用生成子类的方法进行扩充时。一种情况是，可能有大量独立的扩展，为支持每一种组合将产生大量的子类，使得子类数目呈          爆炸性增长。另一种情况可能是因为类定义被隐藏，或类定义不能用于生成子类。

        他响应了开闭原则：对扩展开放，对修改关闭。

    * */
}
