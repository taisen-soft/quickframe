package cn.com.frame.services.runnableserver;

import cn.com.frame.common.builder.Sys;
import cn.com.frame.common.util.ShareData;
import cn.com.frame.services.common.timer.SystemTimerTask;

import javax.servlet.ServletContextEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统监控服务
 * 
 * @author Sirius
 * 
 */
public class RunnableServer extends SystemTimerTask {

	private Map params;

	public RunnableServer(ServletContextEvent sce, Map params) {
		super(sce);
		// TODO Auto-generated constructor stub
		this.params = params;
	}

	@Override
	public void taskRun() throws Exception {
		if (ShareData.SHARE_DISPLAY_LIST.get("__SYSTEM") == null) {
			ShareData.SHARE_DISPLAY_LIST.put("__SYSTEM", new HashMap());
		}
		Map tempMap = (Map) ShareData.SHARE_DISPLAY_LIST.get("__SYSTEM");
		double max = ((Runtime.getRuntime().maxMemory()) / (1024.0 * 1024));
		double total = ((Runtime.getRuntime().totalMemory()) / (1024.0 * 1024));
		double free = ((Runtime.getRuntime().freeMemory()) / (1024.0 * 1024));

		tempMap.put("__jvm_max_memory", max + "MB");
		tempMap.put("__jvm_this_memory", total + "MB");
		tempMap.put("__jvm_free_memory", free + "MB");
		tempMap.put("__jvm_avl_memory", (max - total + free) + "MB");
		tempMap.put("__updatetime", Sys.getConverter().formatForceToString(new Date()));

		ShareData.SHARE_DISPLAY_LIST.put("__SYSTEM", tempMap);

	}
}
