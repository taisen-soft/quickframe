/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.services.config.module.common;

import cn.com.frame.services.common.once.AbstractThreads;
import cn.com.frame.services.config.service.ConfigServices;

import java.util.Map;



/**
 * @author 张奇(Sirius Zhang)
 * 
 *         Date : 2012-9-18
 */
public abstract class ConfigServicesRunning extends AbstractThreads {

	// 配置文件路径
	protected String configPath;

	// 配置标记
	protected String configKey;

	/**
	 * 唯一构造函数
	 * 
	 * @param configPath
	 * @param configKey
	 */
	public ConfigServicesRunning(String configPath, String configKey) {
		super(null);
		this.configKey = configKey;
		this.configPath = configPath;
	}

	/**
	 * 读取配置文件
	 * 
	 * @return
	 */
	public abstract Map readConfig();

	/**
	 * 写入配置文件
	 * 
	 * @param config
	 * @return
	 */
	public abstract boolean writeConfig(Map config);

	/**
	 * 同步本配置
	 * 
	 * @param config
	 */
	public void syncConfig(Object config) {
		// TODO Auto-generated method stub
		ConfigServices.intoConfig(this.configKey, config);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.system.services.common.AbstractServices#servicesStart(java.util.Map)
	 */
	@Override
	public void servicesStart() {
		// TODO Auto-generated method stub
		if (RUNNING_PARAMS == null) {
			this.readConfig();
		} else if (RUNNING_PARAMS.get("type") != null ? ((String) RUNNING_PARAMS
				.get("type")).equals("write") : false) {
			if (RUNNING_PARAMS.get("config") != null) {
				this.writeConfig((Map) RUNNING_PARAMS.get("config"));
			} else {
				this.writeConfig(null);
			}
		}

	}
}
