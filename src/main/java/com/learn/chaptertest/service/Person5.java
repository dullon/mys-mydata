package com.learn.chaptertest.service;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 避免创建不必要的对象 反例&正例
 */
public class Person5 {
    //private final Date birthDate = ;

    //反例
    //该例中Calendar，TimeZone Date Date 每次调用都会创建上面4个实例 这是低效的。
    public boolean isBabyBoomer(Date birthDate){
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946,Calendar.JANUARY,1,0,0,0);
        Date boomerStart = gmtCal.getTime();
        gmtCal.set(1965,Calendar.JANUARY,1,0,0,0);
        Date boomerEnd = gmtCal.getTime();
        return birthDate.compareTo(boomerStart) >= 0 && birthDate.compareTo(boomerEnd) <= 0;
    }

    //正例
    private static final Date BOOMER_START ;
    private static final Date BOOMER_END ;
    private boolean isBabyBoomer2(Date birthDate){
        return birthDate.compareTo(BOOMER_START) >= 0 && birthDate.compareTo(BOOMER_END) <= 0;
    }
    //一些固定的参数可以放到 静态块中执行 这样值实例一次（注意内存泄露问题，不适合大容器类）
    //进一步优化，如果 isBabyBoomer2 方法不调用 可以无需先加载该块，可以用延迟加载方式（但除非对性能特殊要求一般情况下不这么做）
    static {
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946,Calendar.JANUARY,1,0,0,0);
        BOOMER_START = gmtCal.getTime();
        gmtCal.set(1965,Calendar.JANUARY,1,0,0,0);
        BOOMER_END = gmtCal.getTime();
    }
}
