/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.services.config.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 读取配置文件的方法
 * 
 * @author 张奇(Sirius Zhang)
 * 
 *         Date : 2012-9-18
 */
public final class ConfigServices {

	// 角色权限配置表
	public final static String CONFIG_ROLE_PERMISSION = "__SYS_ROLE_PERMISSIONS";
	public final static String XML_ROLE_PERMISSION = "config\\permission\\sys_permission.xml";
	public final static String XML_ROLE_PERMISSION_GLOBLE = "sys_permission_all.xml";
	public final static String XML_ROLE_PERMISSION_PATH = "config\\permission";

	// 权限列表配置表
	public final static String CONFIG_PERMISSION_LIST = "__SYS_PERMISSION_LIST";
	public final static String XML_PERMISSION_LIST = "config\\permission\\sys_permission_list.xml";

	// 系统配置表
	public final static String CONFIG_SYS_CONFIG = "__SYS_CONFIG";
	public final static String XML_SYS_CONFIG = "config\\sysloader\\sys_config.xml";

	// 反射配置表
	public final static String CONFIG_SYS_REFLECT = "__SYS_REFLECT";
	public final static String XML_SYS_REFLECT = "config\\sysloader\\sys_reflect.xml";

	private static Map<String, Object> configMap = new HashMap<String, Object>();

	private ConfigServices() {

	}

	/**
	 * 获取配置
	 * 
	 * @param key
	 * @return
	 */
	public synchronized static Object findConfigByKey(String key) {
		return configMap.get(key);
	}

	/**
	 * 设置配置
	 * 
	 * @param key
	 * @param config
	 */
	public synchronized static void intoConfig(String key, Object config) {
		configMap.put(key, config);
	}

	/**
	 * 获取所有的配置key
	 * 
	 * @return
	 */
	public synchronized static List findConfigKey() {
		List result = new ArrayList();
		for (Iterator<String> it = configMap.keySet().iterator(); it.hasNext();) {
			result.add(it.next());
		}
		return result;
	}
}
