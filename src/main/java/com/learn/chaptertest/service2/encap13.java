package com.learn.chaptertest.service2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 13 使类和成员的可访问性最小化
 */
public class encap13 {
    //说白了 就是一种封装化 Java三大特性 封装 继承 多态
    /*
    封装 或者说 信息隐藏 他的优点：
    1.解耦合，使之模块化， 便于独立开发，测试，优化，使用，理解和修改
    2.加快开发速度，因为封装后以模块为单位，所以可以并行开发
    3.可以有效的调节性能，因为模块化，所以便于定位那些模块影响系统性能，再做定向优化，而且不影响其他模块。
    4.提高了复用性，拼装式组合。复用到其他地方也不影响当前调用
    5.降低了大型系统的风险，部分模块出现问题 ，不影响其他模块正常工作。

    规则：
    尽可能使每个类或者成员不被外界访问，实例域不能是公有的，因为包含公有的可变域的类型不是线程安全的

    注意：
    长度非0的数组总是可变的，所以，类具有公有的静态的final数组域，或返回这种域的方法，一般情况下都是错误的。
     */
    //public static final String[] Values = {}; 这是错误的方式

    //不太懂，字面翻译unmodifiableList方法：返回指定列表的不可修改视图。这种方法允许模块为用户提供对内部的“只读”访问列表。        //返回的列表上的查询操作“通读”到指定列表，并尝试修改返回的列表irect或通过其迭代器生成
    private static final String[] AAA_VALUES = {};
    public static final List<String> VALUES = Collections.unmodifiableList(Arrays.asList(AAA_VALUES));

    //该方式为常用方式 通过公有的方法调用 克隆样本。
    private static final String[] BBB_VALUES = {};
    public static final String[] values(){
        return BBB_VALUES.clone();
    }

}
