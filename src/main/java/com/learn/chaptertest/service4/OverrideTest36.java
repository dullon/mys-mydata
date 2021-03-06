package com.learn.chaptertest.service4;

import java.io.Serializable;

/**
 * 36 : 坚持使用Override注解
 *
 *应该在想要覆盖超类声明的每个方法声明中使用Override注解，这样当想要覆盖的方法没有被覆盖时，编译器能提示错误，大大减少方法覆盖失败的可能性。
 * 37 : 用标记接口定义类型
 *
 *标记接口的优点：
 * 1标记接口定义的类型是由北标记类的实例实现的；标记接口则没有定义的类型；
 * 2标记接口可以更加精确的进行锁定。
 *
 * 标记注解的优点:
 * 1标记注解可以通过默认的方式添加一个或者多个注解类型元素 , 给已被实用的注解类型添加更多地信息 。
 * 2标记注解是更大注解机制的一部分 , 这意味这它在那些支持注解作为编程元素之一的框架中同样具有一致性。
 *
 * 标记注解和标记接口的选择:
 *  如果标记是应用到程序元素而不是类或者接口，就必须用注解；
 *  如果标记只应用给类和接口，那么就标记接口优先于标记注解；
 *  如果要定义一个任何新方法都不会与之关联的类型，标记接口是最好的选择；
 *  如果想要标记程序元素而非类和接口，考虑到未来可能要给标记添加更多的信息忙活着标记更适合于已经广泛使用了注解类型的框架，那么就该使用标记注解。
 *
 * 总结：从某种意义上说，本条目与第19条中“如果不想定义类型就不要使用接口”的说法相反。本条目接近的意思是说：如果想要定义类型，一定要使用接口。
 *
 */
public class OverrideTest36 implements Serializable {

}
