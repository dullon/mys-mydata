package com.learn.chaptertest.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Services {
    private Services(){}
    //承载服务名称 和服务的 容器
    private static final Map<String,Privder> privders= new ConcurrentHashMap<>();
    //无定义时 默认此名称
    public static final String DEFAULT_P_NAME = "<def>";

    /**
     * 提供者注册API（默认）
     * @param p
     */
    public static void registerDefaultPrvider(Privder p){
        registerPrvider(DEFAULT_P_NAME,p);
    }

    /**
     * 提供者注册API
     * @param name
     * @param p
     */
    private static void registerPrvider(String name,Privder p) {
        privders.put(name,p);
    }

    /**
     * 服务访问API 默认
     * @return
     */
    public static Service newInstance(){
        return newInstance(DEFAULT_P_NAME);
    }
    /**
     * 服务访问API
     * @return
     */
    private static Service newInstance(String name) {
        Privder p = privders.get(name);
        if (p == null){
            throw new IllegalArgumentException("No privder registered with name:"+ name);

        }
        return p.getService();
    }
}
