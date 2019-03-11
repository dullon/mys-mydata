package com.learn.chaptertest.service;

/**
 * 覆盖equals时请遵守通用约定
 */
public class Person8 {

    //此方式是防止被意外调用
    @Override
    public boolean equals(Object obj) {

        throw new AssertionError();
        //return super.equals(obj);
    }
    //从写equals方式5大原则
    /*
    1, 自反性 z.equals(z) == true
    2，对称性 z.equals(y) == true 则  y.equals(z) == true
    3，一致性  z.equals(y) == true 如果两者不进行修改 则一直为true
    4，传递性  z.equals(y) == true  y.equals(x) == true 则  z.equals(x) == true
    5，非空性  z.equals(null) == false

    方式：
        1）使用 == 来检查 是否是这个对象的引用
        2）使用 instanceof 来检查 是否是正确的类型
        3）转换参数为正确的类型
        4）对于该类中的 关键域（成员变量为对象） 检查参数中的域是否与该对象中应对的域相匹配
        经典用法Objects内（(a == b) || (a != null && a.equals(b))）
        5）保持 对称性 传递性 一致性

    注： 覆盖equals方法时 一般都覆盖hashCode方法
        不要将equals声明中的boj对象改为其他类型，那样并不是覆盖而是重载，应该用@Override避免发生
     */
}
