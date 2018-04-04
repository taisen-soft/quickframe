package cn.com.frame.services.writepage.service;

import cn.com.frame.services.common.timer.SystemTimerTask;

import java.util.Map;

import javax.servlet.ServletContextEvent;


/**
 * 数据检查服务
 * 
 * @author Sirius
 * 
 */
public class DataCheckServer extends SystemTimerTask {

	public static boolean NEED_RUN = false;

	private Map params;

	public DataCheckServer(ServletContextEvent sce, Map params) {
		super(sce);
		// TODO Auto-generated constructor stub
		this.params = params;
	}

	@Override
	public void taskRun() {
		// TODO Auto-generated method stub
		try {
			// 需要运行
			if (NEED_RUN == true) {
				NEED_RUN = false;
				// 检查丢失的数据
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
