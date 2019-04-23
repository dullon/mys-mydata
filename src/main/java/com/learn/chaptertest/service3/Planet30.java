package com.learn.chaptertest.service3;

/**
 * 30 用枚举（Enum）来代替int常量
 * 枚举 是指由一组固定的常量组成合法值得类型，int枚举和String枚举 不建议
 * 枚举的目的：
 *  通过公有的静态的final域 为每个枚举常量导出实例的类， 因为它没有访问的构造器，而且是final的，单例的泛型化。
 *  为了将数据和枚举常量关联起来，得声明实例域，并编写一个带有数据并将数据保存在域中的构造器 如下：
 *  劣势：与int常量相比，枚举在装载和初始化枚举时 会多一些 时间和空间成本。
 *  优势：枚举 代码优雅易读，安全性高，功能强大。（每个常量与属性的关联）（提供行为受属性影响的方法）
 *  如果多个常量共享相同的行为 可考虑策略枚举（枚举嵌套）如薪酬发放，各种加班行为的薪酬策略。
 *  应用方式：每当需要 一组 固定常量的时候，如 菜单的选项，操作代码，命令行标记，系统编码等等。
 */
public enum Planet30 {
    MERCURY(3.302e+23,2.439e6),
    VENUS(3.302e+23,2.439e6),
    EARTH(3.302e+23,2.439e6),
    MARS(3.302e+23,2.439e6),
    JUPITER(3.302e+23,2.439e6),
    SATURN(3.302e+23,2.439e6),
    URANUS(3.302e+23,2.439e6),
    NEPTUNE(3.302e+23,2.439e6);
    private final double mass;//值私有化 提供公有的提取方法。
    private final double radius;
    private final double surfaceGravity;
    private final double G = 6.67300E-11;

    Planet30(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        this.surfaceGravity = G * mass/(radius * radius);
    }

    public double mass() {
        return mass;
    }

    public double radius() {
        return radius;
    }

    public double surfaceGravity() {
        return surfaceGravity;
    }
    public double surfaceWeight(double mass){
        return mass * surfaceGravity;
    }

}

