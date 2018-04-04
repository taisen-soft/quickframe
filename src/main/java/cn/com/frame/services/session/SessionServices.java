/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.services.session;


import cn.com.frame.common.builder.SystemBuilder;
import cn.com.frame.common.struct.session.SessionModel;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Date;

/**
 * Session监听服务
 * 
 * @author 张奇(Sirius Zhang)
 * 
 *         Date : 2012-3-19
 */
public class SessionServices implements HttpSessionListener,
        ServletRequestListener {

	// 访问的request
	private HttpServletRequest request;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http
	 * .HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSession().getAttribute("SYS_USER_UUID") == null) {
			SessionModel session = new SessionModel();
			session.setUuid(arg0.getSession().getId());
			session.setUseruuid("未登录");
			session.setUsername("用户未登录");
			session.setIp(request.getRemoteAddr());
			session.setEnterURI(request.getRequestURL().toString());
			session.setTime(SystemBuilder.getBuilder().getToolsFactory()
					.getConverter().formatForceToString(new Date()));
			SessionManager.intoMap(arg0.getSession().getId(), session);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet
	 * .http.HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		if (SessionManager.releaseMap(arg0.getSession().getId()) != null) {
			SessionManager.removeMap(arg0.getSession().getId());
		}
		if (arg0.getSession().getAttribute("SYS_USER_UUID") != null) {
			String userid = (String) arg0.getSession().getAttribute(
					"SYS_USER_UUID");
			if (SessionManager.releaseMap(userid) != null) {
				SessionManager.removeMap(userid);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequestListener#requestDestroyed(javax.servlet.
	 * ServletRequestEvent)
	 */
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.ServletRequestListener#requestInitialized(javax.servlet
	 * .ServletRequestEvent)
	 */
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		request = (HttpServletRequest) sre.getServletRequest();
	}

}
