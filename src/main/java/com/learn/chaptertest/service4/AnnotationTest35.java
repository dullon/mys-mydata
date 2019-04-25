package com.learn.chaptertest.service4;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 35：注解优先于命名模式
 * 命名模式缺点：
 *      1.文字拼写错误导致失败，测试方法没有执行，也没有报错
 *      2.无法确保它们只用于相应的程序元素上，如希望一个类的所有方法被测试，把类命名为test开头，但JUnit不支持类级的测试，只在test开头的方法中生效
 *      3.没有提供将参数值与程序元素关联起来的好方法。
 *      注解能解决命名模式存在的问题，下面定义一个注解类型指定简单的测试，它们自动运行，并在抛出异常时失败（注意，下面的Test注解是自定义的，不是JUnit的实现）
 */
public class AnnotationTest35 {
    public static void main(String[] args) throws ClassNotFoundException {
        int tests = 0;
        int passed = 0;
        Class testClass = Class.forName("com.learn.chaptertest.service4.Sample");
        for(Method m : testClass.getDeclaredMethods()) {
            if(m.isAnnotationPresent(Test.class)) {
                tests++;
                try {
                    m.invoke(null);
                    passed++;
                } catch(InvocationTargetException wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    System.out.println(m + " failed: " + exc);
                } catch(Exception e) {
                    System.out.println("INVALID @Test: " + m);
                }

            }
        }
        System.out.printf("Passed: %d, Failed: %d%n", passed, tests - passed);
        System.out.println("--------------------------------");
        tests = 0;
        passed = 0;
        Class testClass2 = Class.forName("com.learn.chaptertest.service4.Sample2");
        for(Method m : testClass2.getDeclaredMethods()) {
            if(m.isAnnotationPresent(ExceptionTest.class)) {//判断是否有注解
                tests++;
                try {
                    m.invoke(null);
                   // passed++;
                    System.out.printf("Test %s failed : no exception%n",m);
                } catch(InvocationTargetException wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    Class<? extends Exception> exType = m.getAnnotation(ExceptionTest.class).value();
                    if (exType.isInstance(exc) ) {
                        passed ++;
                    }else {
                        System.out.println("Test:"+ m + " failed: expected:" + exType.getName() + "got" + exc);
                    }
                } catch(Exception e) {
                    System.out.println("INVALID @Test: " + m);
                }

            }
        }
        System.out.printf("Passed: %d, Failed: %d%n", passed, tests - passed);
    }
}
//简单的测试接口
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
 @interface Test {
}

//调用测试接口的类
class Sample {
    @Test public static void m1() {

    }
    public static void m2() {

    }
    @Test public static void m3() {
        throw new RuntimeException("Boom");
    }
    public static void m4() {

    }
    @Test public void m5() {

    }
    public static void m6() {

    }
    @Test public static void m7() {
        throw new RuntimeException("Crash");
    }
    public static void m8() {

    }

}
//针对只有在抛出特殊异常才成功的注解
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ExceptionTest {
    Class<? extends Exception> value();
}
//
class Sample2 {
    @ExceptionTest(ArithmeticException.class)
    static void m1 (){
        int i = 0 ;
        i = i/i;
    }
    @ExceptionTest(ArithmeticException.class)
    static void m2 (){
        int [] a = new int[0];
        int i = a[1];
    }
    @ExceptionTest(ArithmeticException.class)
    static void m3 (){
    }
}
