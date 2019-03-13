package com.learn.chaptertest.service2;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 *  12 考虑实现接口Comparable接口
 */
public class WordList12 implements Comparable<WordList12>{
    String s = "1";
    public static void main(String [] args){
        String [] s1 = {"c","d","a"};
        Set<String> s = new TreeSet<>();
        Collections.addAll(s,s1);
        System.out.println(s);
    }

    @Override
    public int compareTo(WordList12 o) {
        return String.CASE_INSENSITIVE_ORDER.compare(s,o.s);
    }
    /*
    Comparable接口只有一个方法 compareTo ,该方法的熟成约定 就是 a与b 比较（同类型） 都有3个结果 大于 小于 等于，返回的值为int类型的 正数 负数 0 ，如果a 与 b  无法比较 则 抛出异常 ClassCastException 。
    重写compareTo方法必须满足：
        1.相对对称性 a.compareTo(b) == -b.compareTo(a)
        2.传递性 a.compareTo(b) > 0 且 b.compareTo(c) > 0 则 a.compareTo(c) > 0
        3.一致性 a.compareTo(b) == 0 则 a.compareTo(c) == b.compareTo(c)
        4.非必须，但建议 (a.compareTo(b) == 0) == (a.equals(b)) 如果特殊要求，需要注释：该类具有内在排序功能但与equals不一定一致。
     */
}
