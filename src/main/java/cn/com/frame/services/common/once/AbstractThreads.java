/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.services.common.once;


import cn.com.frame.common.builder.Sys;

import java.util.Date;
import java.util.Map;

/**
 * @author 张奇(Sirius Zhang)
 * 
 *         Date : 2017-8-14
 */
public abstract class AbstractThreads implements Runnable {

	protected String RUNNING_SERVICES_KEY;
	protected ThreadDescripter RUNNING_SERVICES_PARAMS;
	public Map RUNNING_PARAMS;

	public AbstractThreads(Map params) {
		super();
		this.RUNNING_PARAMS = params;
		RUNNING_SERVICES_KEY = Sys.getConverter().getUUID();
		RUNNING_SERVICES_PARAMS = new ThreadDescripter();
		if (params == null) {
			return;
		}
		if (params.get("__USERNAME") != null) {
			RUNNING_SERVICES_PARAMS.setUsername((String) params.get("__USERNAME"));
		}
		if (params.get("__USERUUID") != null) {
			RUNNING_SERVICES_PARAMS.setUseruuid((String) params.get("__USERUUID"));
		}
		if (params.get("__SERVICENAME") != null) {
			RUNNING_SERVICES_PARAMS.setServiceName((String) params.get("__SERVICENAME"));
		}
		if (params.get("__SERVICEROOT") != null) {
			RUNNING_SERVICES_PARAMS.setServiceRoot((String) params.get("__SERVICEROOT"));
		}

	}

	/**
	 * 标准启动服务方法
	 * 
	 * @param
	 */
	public abstract void servicesStart() throws Exception;

	/**
	 * 运行服务时操作
	 */
	public void run() {
		if (RUNNING_SERVICES_KEY != null && RUNNING_SERVICES_PARAMS != null) {
			RUNNING_SERVICES_PARAMS.setStartDate(new Date().getTime());
			ThreadManager.joinServices(this.RUNNING_SERVICES_KEY, this.RUNNING_SERVICES_PARAMS);
		}
		try {
			servicesStart();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		if (RUNNING_SERVICES_KEY != null && RUNNING_SERVICES_PARAMS != null) {
			ThreadManager.removeServices(this.RUNNING_SERVICES_KEY);
		}
	}

	/**
	 * 获取线程描述符
	 * @return
	 */
	public ThreadDescripter getThreadInformation() {
		return this.RUNNING_SERVICES_PARAMS;
	}
	/**
	 *获取线程UUID
	 * /
	 * 
	 */
	public String getThreadUUID(){
		return RUNNING_SERVICES_KEY;
	}
}
