package com.learn.chaptertest.service5;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  43 : 返回0长度的数组或者集合，而不用null
 * 在这个级别上担心性能问题是不明智的，除非分析表明这个方法正是造成性能问题的真正源头。
 * 1 对于这个问题，逻辑出错比性能下降造成的后果更严重，除非有足够多的证据证明确实是在这里造成的性能问题；
 * 2 零长度的数组，其实并不比null占用太多的额外开销；
 * 3 如果真的返回次数太多，其实我们可以使用同一个零长度的数组。
 *
 *  44 ： 为所有导出的API元素编写文档注释
 *
 *  文档注释三部分：
 *  1.简述。文档中，对于属性和方法都是先有一个列表，然后才在后面一个一个的详细的说明。
 *  2.详细说明。该部分对属性或者方法进行详细的说明，在格式上没有什么特殊的要求，可以包含若干个点号。
 *  3.特殊说明。这部分包括版本说明、参数说明、返回值说明等。
 *
 *  添加文档注释规范：
 * 一、为了正确地编写API文档，必须在每个被导出的类、接口、构造器、方法和域声明之前增加一个文档注释。
 * 二、方法的文档注释应该简洁的描述出它和客户端之间的约定。这个约定应该说明这个方法做了什么，而不是说明它是如何完成这项工作的。文档注释应该列举如下内容：
 * 1.前提条件（precondition） 前提条件是指为了使客户能够调用这个方法，而必须满足的条件；
 * 2.后置条件（postcondition）  所谓后置条件是指在调用成功完成之后，哪些条件必须要满足；
 * 3.副作用（side effect） 副作用是指系统状态中可以观察到的变化，它不是为了获得后置条件而明确要求的变化；
 * 4.类或者方法的线程安全性（thread safety）（详见70条） 当一个类的实例或者静态方法被并发使用时，这个类行为的并发情况。
 *
     类的导出API有两个容易被人忽略的特征：
     1.类是否是线程安全的应该在文档中对它的线程安全级别进行说明。（如70条所述）
     2.如果类是可序列化的，就应该在文档中说明它的序列化形式。（如第75条所述）
 */
public class ReturnAorC43 {

    /**
     * 当为泛型或者方法编写文档时，确保要在文档中说明所有的类型参数。
     * @author
     * @version 1.0
     * @param <K> the type of keys maintained by the map
     * @param <V> the type of mapped values
     */
    public interface Map<K, V> {
        //dosomething
    }
    /**
     * 当为枚举类型编写文档时，要确保在文档中说明常量，以及类型，还有任何公有的方法。
     * three primary colours
     * @author
     * @version 1.0
     * return enum
     */
    public enum Color {
        /** Red, the color of blood. */
        RED,
        /** Green, the color of grass.  */
        GREEN,
        /** Blue, the color of sea. */
        BLUE;
    }
    /**
     * 为注解类型编写文档时，要确保在文档中说明所有成员，以及本身类型。
     * Indicates that the annotated method is a test method that
     * must throw the designated exception to succeed.
     * @author
     * @version 1.0
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface ExceptionTest {
        /**The exception that the annotated test method must throw
         * in order to pass. (The test is permitted to throw any
         * subtype of the type described by this class object.) */
        Class<? extends Exception> value();
    }

}
