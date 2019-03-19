package com.learn.chaptertest.service3;

import java.io.Serializable;
import java.util.Comparator;

/**
 * 21 用函数对象表示策略
 */
public class StringLengthComparator21 {
    private StringLengthComparator21() {
    }

    public static final StringLengthComparator21 INSTANCE = new StringLengthComparator21();

    public int compare(String str1,String str2){
        return str1.length() - str2.length();
    }
    /*
    * 策略模式：通过传递不同的比较器（行为），就可以获得不同的排序（结果）。
    * */
    //以上内容用静态单例模式 执行策略 灵活性低。所以设计策略模式时，还要定义一个策略接口。而实现时往往采用匿名内部类来实现。


}
//已Comparator为策略接口 静态内部类为实现，可以用于多实现 ，如序列化
class Host{
    private static class StrLenCmp implements Comparator<String>, Serializable{
        @Override
        public int compare(String o1, String o2) {
            return o1.length() - o2.length();
        }
    }

    public static final Comparator<String> STRING_COMPARATOR_LENGTH= new StrLenCmp();
}
