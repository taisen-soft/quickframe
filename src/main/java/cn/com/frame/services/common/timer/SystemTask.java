package cn.com.frame.services.common.timer;


import cn.com.frame.common.builder.SystemBuilder;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 可监控的可控制的TASK
 * 
 * @author Sirius
 * 
 */
public class SystemTask extends Timer {

	// uuid唯一标识码
	private String uuid;
	private String taskname;
	// 间隔时间
	private long instance;
	// 任务
	private SystemTimerTask task;
	// 开始时间
	private Date beginTime;

	/**
	 * 创建带有时间间隔的服务
	 * 
	 * @param instance
	 */
	public SystemTask(String taskname, long instance, SystemTimerTask task) {
		super(true);
		this.taskname = taskname;
		this.uuid = SystemBuilder.getBuilder().getToolsFactory().getConverter()
				.getUUID();
		this.instance = instance;
		this.task = task;
		this.beginTime = new Date();
		// 将现有服务注册到TASK_MAP中
		ServicesUtil.TASK_MAP.put(this.uuid, this);
	}

	/**
	 * 创建带有时间间隔和开始时间的服务
	 * 
	 * @param instance
	 * @param firsttime
	 * @param task
	 */
	public SystemTask(String taskname, long instance, Date firsttime,
			SystemTimerTask task) {
		this(taskname, instance, task);
		this.beginTime = firsttime;
	}

	public long getInstance() {
		return instance;
	}

	public void setInstance(long instance) {
		this.instance = instance;
	}

	public SystemTimerTask getTask() {
		return task;
	}

	public void setTask(SystemTimerTask task) {
		this.task = task;
	}

	public String getUuid() {
		return uuid;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	@Override
	public void cancel() {
		super.cancel();
		try {
			Field field = TimerTask.class.getDeclaredField("state");
			field.setAccessible(true);
			field.set(task, 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
