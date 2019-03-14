package com.learn.chaptertest.service2;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.ComplexType;

import java.util.Map;

/**
 * 15 使可变性 最小化
 */
//也可以 不用 final 修饰表达 则需要把构造方法私有化，并提供静态工厂 创建对象
public  class Complex15 {
    /*
    *不可变类的优势：易于设计，实现和使用，不易出错，安全性高
    * 如：String，基本类型的包装类，BigInteger和BigDecimal等。
    *
    * 设计原则 1. 不要提供任何会修改对象状态的方法
    *         2. 保证类不会被扩展
    *         3. 使用的所有域都是final的
    *         4. 使用的所有域都成为私有的
    *         5. 确保对于任何可变组件的互斥访问。 不太理解
    *
    * 不可变对象的本质是线程安全的，所以他们不要求同步。不可变的对象也可以共享他们的内部信息，
    * 不可变的对象为其他对象提供了大量的构建
    *
    * 缺点，每个不同的值 都需要一个单独的对象，当值过多时，尤其出现循环生成不同的值时 极占内存 影响性能 甚至出现内存泄漏问题。
    * */
    private final double re;
    private final double im;


    private static final Complex15 ZERO = new Complex15(0,0);
    private static final Complex15 ONE = new Complex15(1,0);
    private static final Complex15 I = new Complex15(0,1);

    //私有化的构造方法 不能被外部调用
    private Complex15(double re, double im) {
        this.re = re;
        this.im = im;
    }
    //相当于get方法
    public double realPart(){
        return re;
    }
    public double imaginaryPart(){
        return im;
    }

    public Complex15 add(Complex15 c){
        return new Complex15(re + c.re , im + c.im);
    }

    public Complex15 subtract(Complex15 c){
        return new Complex15(re - c.re , im - c.im);
    }

    public Complex15 multiply(Complex15 c){
        return new Complex15(re * c.re - im * c.im, re * c.im + im * c.re);
    }

    public Complex15 divide(Complex15 c){
        double tmp = c.re * c.re + c.im * c.im;
        return new Complex15((re * c.re + im * c.im) / tmp ,(im * c.re - re * c.im) / tmp);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        //if (!(o instanceof Complex15)) return false; 上下两种方式均可。
        Complex15 complex15 = (Complex15) o;

        if (Double.compare(complex15.re, re) != 0) return false;
        return Double.compare(complex15.im, im) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(re);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(im);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Complex15{" +
                "re=" + re +
                ", im=" + im +
                '}';
    }

    //上一个方式 是 类名final化 确保 不能被继承。提供公有的静态工厂方法 用于生产对象。
    public static Complex15 valueOf(double re, double im){
        return  new Complex15(re,im);
    }

}
