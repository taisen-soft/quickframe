package cn.com.frame.services.common.timer;


import cn.com.frame.services.common.services.ThreadRunningServer;
import cn.com.frame.services.runnableserver.RunnableServer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.*;

/**
 * 系统服务运行入口
 * 
 * @author Sirius
 * 
 */
public class ServicesManager implements ServletContextListener {

	private static int DAY_TIME = 24 * 60 * 60 * 1000;

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		for (Iterator it = ServicesUtil.TASK_MAP.keySet().iterator(); it.hasNext();) {
			String timerkey = (String) it.next();
			SystemTask timer = (SystemTask) ServicesUtil.TASK_MAP.get(timerkey);
			try {
				timer.cancel();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		// 当天半夜时间23点33分15秒
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int date = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		int send = calendar.get(Calendar.SECOND);

		// calendar.set(year, month, date, 23, 33, 15);
		calendar.set(year, month, date, hour, minute, send);
		Date firstrunning = calendar.getTime();

		Calendar sign = Calendar.getInstance();
		sign.set(year, month, date, 20, 15, 0);
		Calendar signup = Calendar.getInstance();
		signup.set(year, month, date, 6, 0, 0);
		signup.add(Calendar.DAY_OF_YEAR, 1);

		Map upparams = new HashMap();
		upparams.put("key", "up");
		Map downparams = new HashMap();
		downparams.put("key", "down");
		
		System.out.println("系统垃圾回收服务启动，运行时间6小时间隔。");
//		new SystemTask("垃圾回收服务", 6 * 60 * 60 * 1000, firstrunning, new DeleteServer(sce, null));
		new SystemTask("线程队列服务", 5000, firstrunning, new ThreadRunningServer(sce));
		new SystemTask("监控系统JVM服务", 60000, firstrunning, new RunnableServer(sce, null));
		// new SystemTask("Sign服务", 24 * 60 * 60 * 1000, sign.getTime(), new SignServer(sce, null));
//		new SystemTask("Sign服务下午", 24 * 60 * 60 * 1000, sign.getTime(), new SignServer(sce, downparams));
//		new SystemTask("Sign服务上午", 24 * 60 * 60 * 1000, signup.getTime(), new SignServer(sce, upparams));

		for (Iterator it = ServicesUtil.TASK_MAP.keySet().iterator(); it.hasNext();) {
			String timerkey = (String) it.next();
			System.out.println("启动" + timerkey);
			SystemTask timer = (SystemTask) ServicesUtil.TASK_MAP.get(timerkey);
			timer.schedule(timer.getTask(), timer.getBeginTime(), timer.getInstance());
			System.out.println(timer.getBeginTime() + "第一次运行");
		}
	}
}
