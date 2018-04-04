package cn.com.frame.services.common.timer;

import javax.servlet.ServletContextEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

public abstract class SystemTimerTask extends TimerTask {

	protected boolean isRunning;
	protected List runHistory;
	protected Date lastRunTime;
	protected boolean once;
	// servlet上下文
	protected ServletContextEvent sce;

	public SystemTimerTask(ServletContextEvent sce) {
		this.sce = sce;
		this.isRunning = false;
		this.runHistory = new ArrayList();
	}

	public SystemTimerTask(ServletContextEvent sce, boolean once) {
		this.sce = sce;
		this.isRunning = false;
		this.runHistory = new ArrayList();
		this.once = once;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public List getRunHistory() {
		return runHistory;
	}

	public void addHistory(Date date) {
		runHistory.add(date);
		lastRunTime = date;
	}

	public Date getLastRunTime() {
		return lastRunTime;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (this.isRunning == true) {
			return;
		}
		this.isRunning = true;
		// 写入一条日志
		
		// 详细运行参数
		try {
			this.taskRun();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.isRunning = false;
		this.addHistory(new Date());
		if(this.once) {
			this.cancel();
		}
	}

	// 运行任务
	public abstract void taskRun() throws Exception;

}
