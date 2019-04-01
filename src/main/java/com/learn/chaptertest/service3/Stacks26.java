package com.learn.chaptertest.service3;

import java.util.*;

/**
 *  25 列表优于数组
 *  26 优先使用泛型
 *  27 优先考虑泛型方法
 *  28 利用有限制通配符来提升API的灵活性
 *  29 优先考虑安全的异构容器
 *
 *  25：数组的缺点：因为数组是斜变的且可具体化的，所以向上转型不会报错，编译可能会出现数组类型错误，而泛型是不可变的
 *  且是可擦除的。
 *  所以两者不能很好的混合使用，经常会遇到编译错误或者警告，而列表却可以和泛型很好的混合使用，所以列表优于数组
 *
 *  26:如果某个类的功能可以进一步的更广泛的使用，我们优先考虑泛型来强化，即泛型化。
 *  引用泛型对象不能以基本类型引用，只能以引用类型来引用。否则会报错
 *
 *  List<E extends Map>  用extends 修饰符 可以让泛型 有类型限制，这叫做限制的类型参数
 *  List<?> 通配符类型参数 用？通配符，代替原生态类型。
 *
 *  27：泛型方法特性： 无需明确指定类型参数的值，不像调用泛型构造器的时候必须是指定的。
 *  类型参数出现变量声明两侧比较冗余，可以编写一个泛型的静态工厂方法
 *
 *  28: 因为泛型的参数化类型为不可变且可擦除的，所以为了提高灵活性，引入了限制的通配符模式。
 *  <? extends E>
 *      为了获得大限度的灵活性，要在表示生产者或者消费者的输入输出参数上使用通配符类型。如果同时是生产者和消费者，
 *      则无需使用 通配符类型。
 *      通配符类型因为可擦除的因素 有一定的安全风险。
 *
 *      PECS 表示 ：生产者 producer-extends ，消费者 consumer-super
 *
 *  29:
 *  示例：
 */
/*
泛型前：
 */
public class Stacks26 {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INIT_CAPA = 16;

    public Stacks26(){
        elements = new Object[DEFAULT_INIT_CAPA];
    }
    public void push(Object e){
        ensureCapacity();
        elements[size ++] = e;
    }
    public Object pop(){
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object result = elements[--size];
        elements[size] = null;
        return result;
    }
    public boolean isEmpty(){
        return size == 0 ;
    }
    private void ensureCapacity(){
        if (elements.length == size){
            elements = Arrays.copyOf(elements,2*size + 1);
        }
    }

    /*
     27可以设置泛型方法 来灵活配置相关参数调用
     */
    private static <K,V> HashMap<K,V> newHashMap(){
        return new HashMap<K,V>();
    }
    private HashMap<String , List<String>> hashMap = newHashMap();

    //28 限制通配符类型的安全风险
    private static <E> Set<E> union(Set<? extends E> s1,Set<? extends E> s2){
        Set<E> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }
    Set<Integer> integers = new HashSet<>();
    Set<Double> doubles = new HashSet<>();
    //该方法调用并没有出现问题，但运行时会报错。
    Set<Number> numbers = union(integers,doubles);


}
/*
泛型化
 */
 class Stacks2<E> {
    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INIT_CAPA = 16;

    @SuppressWarnings("unchecked")
    public Stacks2(){
        elements = (E[]) new Object[DEFAULT_INIT_CAPA];
    }
    public void push(E e){
        ensureCapacity();
        elements[size ++] = e;
    }
    public E pop(){
        if (size == 0) {
            throw new EmptyStackException();
        }
        E result = elements[--size];
        elements[size] = null;
        return result;
    }
    public boolean isEmpty(){
        return size == 0 ;
    }
    private void ensureCapacity(){
        if (elements.length == size){
            elements = Arrays.copyOf(elements,2*size + 1);
        }
    }

    // 29 示例

    public static void main(String [] args){
        Favorites f = new Favorites();
        f.putFavorite(String.class,"Java");
        f.putFavorite(Integer.class,0xafebae);
        f.putFavorite(Class.class,Favorites.class);
        String favoriteString = f.getFavorite(String.class);
        int favoriteInt = f.getFavorite(Integer.class);
        Class<?> favoriteClass = f.getFavorite(Class.class);
        System.out.printf("%s %x %s%n",favoriteString,favoriteInt,favoriteClass.getName());
    }//Java afebae com.learn.chaptertest.service3.Favorites

}
// 29 它是类型安全的；当你请求String时 ，返回String， 当你请求Integer时 返回Integer
// 同时它也是异构的：他可以存储不同的类型，他所有的键都是不同类型的。所以它称为 类型安全的异构容器
// 它的键类型为通配符类型 可以支持不同的类型保证异构能力。值 却是 Object类型 ，说明Map不能保证每个值与键的类型相同。
class Favorites {
    private Map<Class<?>,Object> favorites = new HashMap<>();
    public <T> void putFavorite(Class<T> type,T instance){
        if (type == null) {
            throw new NullPointerException();
        }
        favorites.put(type,instance);
    }
    //cast 方法时Java的cast操作符的动态模拟。它只检验它的参数是否为Class对象所表示的类型的实例。如果是就返回参数，不是则抛出异常ClassCastException。
    public <T> T getFavorite(Class<T> type){
        return type.cast(favorites.get(type));
    }
}