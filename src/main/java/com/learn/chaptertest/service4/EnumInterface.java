package com.learn.chaptertest.service4;

/**
 * 34：用接口模拟可伸缩的枚举
 * 枚举类型是不可扩展的，但是接口类型是可扩展的。使用接口，可以模拟可伸缩的枚举。
 */
public class EnumInterface {
    private static <T extends Enum<T> & InterfaceDemo> void test(Class<T> opSet, double x, double y) {

        for(InterfaceDemo op : opSet.getEnumConstants())
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
    }

    public static void main(String[] args) {
        double x = 2.0;
        double y = 4.0;
        test(ExtendedOperation.class, x, y);
    }
}
//接口提取参数
interface InterfaceDemo {
    double apply(double x, double y);
}
//原始计算器本来枚举已定型
enum BasicOperation implements InterfaceDemo {
    PLUS("+") {
        public double apply(double x, double y) {return x + y;}
    },
    MINUS("-") {
        public double apply(double x, double y) {return x - y;}
    },
    TIMES("*") {
        public double apply(double x, double y) {return x - y;}
    },
    DIVIDE("/") {
        public double apply(double x, double y) {return x - y;}
    };
    private final String symbol;
    BasicOperation(String symbol) {
        this.symbol = symbol;
    }
    public String toString() {
        return symbol;
    }

}
//扩展功能 同样实现 参数接口 达到扩展目的， 依赖倒转原则，细节依赖抽象。
enum ExtendedOperation implements InterfaceDemo {
    EXP("^") {
        public double apply(double x, double y) {return Math.pow(x, y);}
    },
    REMAINDER("%") {
        public double apply(double x, double y) {return x % y;}
    };

    private final String symbol;
    private ExtendedOperation(String symbol) {
        this.symbol = symbol;
    }
    public String toString() {
        return symbol;
    }
}