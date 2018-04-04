/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.services.session;


import cn.com.frame.common.struct.session.SessionModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 张奇(Sirius Zhang)
 * 
 *         Date : 2012-8-3
 */
public class SessionManager {

    // 获取Session值
    private static Map<String, SessionModel> sessionMap = new HashMap<String, SessionModel>();

    private SessionManager() {

    }
    /**
     * 重写put方法
     * @param key
     * @param value
     */
    public static synchronized void intoMap(String key, SessionModel value) {
        sessionMap.put(key, value);
    }
    
    /**
     * 重写get方法
     * @param key
     * @return
     */
    public static synchronized SessionModel releaseMap(String key){
        return sessionMap.get(key);
    }
    
    /**
     * 重写remove方法
     * @param key
     */
    public static synchronized void removeMap(String key){
        sessionMap.remove(key);
    }
    
    /**
     * 重写keySet
     * @return
     */
    public static synchronized Set<String> keySet(){
        return sessionMap.keySet();
    }

}
