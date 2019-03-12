package com.learn.chaptertest.service;

import java.util.Objects;

/**
 * 覆盖equals时 总要覆盖hashCode
 */
public class PhoneNumber9 {
    private final short areaCode;
    private final short prefix;
    private final short lineNumber;

    public PhoneNumber9(short areaCode, short prefix, short lineNumber) {
        rangCheck(areaCode,999,"area code");
        rangCheck(prefix,999,"prefix");
        rangCheck(lineNumber,9999,"line number");
        this.areaCode = areaCode;
        this.prefix = prefix;
        this.lineNumber = lineNumber;
    }
    public static void rangCheck(int arg,int max,String name){
        if (arg < 0||arg > max){
            throw new IllegalArgumentException(name + ":" + arg );
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber9 that = (PhoneNumber9) o;
        return areaCode == that.areaCode &&
                prefix == that.prefix &&
                lineNumber == that.lineNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(areaCode, prefix, lineNumber);
    }
    /*
    hashCode的三个约定：
        1. 在程序执行期间，只要对象的equals所用的的信息没有修改，那么对这个同一个对象多次调用，hashCode方法必须始终如一的返回同一个整数，在同一个应用多次执行的过程中，每次执行所hashCode返回的整数可以不一致
        2. 如果两个对象根据equals方法比较是相等的，那么调用这两个对象中任意一个对象的hashCode方法都必须产生同样的整数结果。
        3. 如果两个对象根据equals方法比较是不相等的，那么调用这两个对象中任意一个对象的hashCode方法，则不一定要产生不同的整数结果。
    技巧：
        boolean ==>（f？1:0）
        byte、short、char、int ==> （int）f
        long   ==> (int)(f^(f>>>32))
        float  ==> Float.floatToIntBits(f)
        double ==> Double.doubleToLongBits(f)
        object ==> object != null ? object.hashCode() : object
        object [] ==> Arrays.hashCode(object)
     */
}
