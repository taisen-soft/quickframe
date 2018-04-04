/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.services.common.once;

/**
 * @author 张奇(Sirius Zhang)
 * 
 *         Date : 2012-9-29
 */
public final class ThreadDescripter {

	// 线程名称
	private String serviceName;
	// 线程路径
	private String serviceRoot;
	// 线程开始时间
	private long startDate;
	// 线程启动用户
	private String username;
	// 线程用户uuid
	private String useruuid;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceRoot() {
		return serviceRoot;
	}

	public void setServiceRoot(String serviceRoot) {
		this.serviceRoot = serviceRoot;
	}

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUseruuid() {
		return useruuid;
	}

	public void setUseruuid(String useruuid) {
		this.useruuid = useruuid;
	}

}
