package cn.com.frame.services.common.services;


import cn.com.frame.services.common.once.AbstractThreads;
import cn.com.frame.services.common.once.ThreadManager;
import cn.com.frame.services.common.timer.SystemTimerTask;

import javax.servlet.ServletContextEvent;

public class ThreadRunningServer extends SystemTimerTask {

	public ThreadRunningServer(ServletContextEvent sce) {
		super(sce);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void taskRun() throws Exception {
		// TODO Auto-generated method stub
		AbstractThreads thread = ThreadManager.deleteFront();
		if (thread == null) {
			return;
		}
		new Thread(thread).start();
	}

}
