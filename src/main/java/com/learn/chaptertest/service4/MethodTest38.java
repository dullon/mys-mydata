package com.learn.chaptertest.service4;

import java.math.BigInteger;
import java.util.AbstractList;
import java.util.List;

/**
 * 38 : 检查参数的有效性
 *
 * 一般在方法执行之前先检查参数的有效性，如果参数值无效，那么很快它就会失败，并且清楚的抛出合适的异常。
 *
 * 如果这个方法没有检查参数的异常，那么可能在方法处理中出现令人费解的异常。更糟糕的有可能是，方法可以正常返回，但是却使得某个对象处于被破坏的状态。
 *
 * 并非对参数的任何限制都是好事，一般来说要尽可能的通用， 符合实际的需要。假如方法对它能接受的参数都能完成合理的计算，那么对于参数的限制其实是越少越好的。因此，鼓励开发者把限制写到文档中，并在方法的开头显式的检查参数的有效性。
 */
public  class MethodTest38 {

    /**此处为BigInteger源码。
     * 对于公有的方法，要用Javadoc的@throws标签 在文档中说明违反参数值限制时会抛出的异常。通常这样的异常为IllegalArgumentException，
     *
     * IndexOutOfBoundsException。
     * Returns a BigInteger whose value is {@code (this mod m}).  This method
     * differs from {@code remainder} in that it always returns a
     * <i>non-negative</i> BigInteger.
     *
     * @param  m the modulus.
     * @return {@code this mod m}
     * @throws ArithmeticException {@code m} &le; 0
     * @see    #remainder
     */
    public BigInteger mod(BigInteger m) {
        if (m.signum <= 0)
            throw new ArithmeticException("BigInteger: modulus not positive");

        BigInteger result = this.remainder(m);
        return (result.signum >= 0 ? result : result.add(m));
    }
        //对于未被导出的方法，作为包的创建者，你可以控制这个方法在哪些情况下可以被调用，因此你可以，也应该确保只将有效的参数传递进去。因此，非公有方法通常应该使用断言（assert）来检查它们的参数
    //有一些参数暂时没有直接用到，只是保存起来供以后使用，这种参数的有效性检查也是尤其重要
    private static void sort(long a[], int offset, int length) {
        assert a != null;
        assert offset >= 0 && offset <= a.length;
        assert length >= 0 && length <= a.length - offset;
    }

    //传入的是一个int数组，返回的是它的list视图。如果传入的是null，返回了一个新建的list，然后错误的null在后面的程序中可能会非常难以定位。因此，构造器检查参数的有效性是非常重要的，必须保证构造出来的对象是有效的。
    static List<Integer> intArrayAsList(final int[] a) {
        if (a == null)//它的存在就是为了检查传入数组是否为空。如果为空可以准确的定位调用方法。
            throw new NullPointerException();

        return new AbstractList<Integer>() {
            public Integer get(int i) {
                return a[i];  // Autoboxing (Item 5)
            }

            @Override
            public Integer set(int i, Integer val) {
                int oldVal = a[i];
                a[i] = val;     // Auto-unboxing
                return oldVal;  // Autoboxing
            }

            public int size() {
                return a.length;
            }
        };
    }

}
