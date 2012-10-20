/**
 * 
 */
package com.ownphone.content.listener;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.ownphone.content.statistic.ApplicationConstants;

/**
 * @author Jiance Qin
 * 
 */
public class StatisticListener implements HttpSessionAttributeListener,
		ServletContextListener, HttpSessionListener, ServletRequestListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet
	 * .ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		// 记录服务器启动时间
		ApplicationConstants.SERVER_START_DATE = new Date();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
	 * ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

		// 清空结果
		ApplicationConstants.SERVER_START_DATE = null;

		ApplicationConstants.MAX_ONLINE_COUNT_DATE = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.ServletRequestListener#requestInitialized(javax.servlet
	 * .ServletRequestEvent)
	 */
	@Override
	public void requestInitialized(ServletRequestEvent arg0) {

		HttpServletRequest request = (HttpServletRequest) arg0
				.getServletRequest();

		HttpSession session = request.getSession(true);

		// 记录远程IP地址，主机名
		session.setAttribute("remoteIP", request.getRemoteAddr());
		session.setAttribute("remoteHost", request.getRemoteHost());

		// 获取访问次数
		Integer activeTimes = (Integer) session.getAttribute("activeTimes");
		if (activeTimes == null) {
			// 初始化为0
			activeTimes = Integer.valueOf(0);
		} 
		
		activeTimes = Integer.valueOf(activeTimes.intValue() + 1);

		// 更新访问次数
		session.setAttribute("activeTimes", activeTimes);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequestListener#requestDestroyed(javax.servlet.
	 * ServletRequestEvent)
	 */
	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http
	 * .HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {

		// 新创建的session
		HttpSession newsession = arg0.getSession();
		
		// 存入在线集合
		ApplicationConstants.OnlineSessionMap.put(newsession.getId(),
				newsession);

		// 总访问人数+1
		ApplicationConstants.TOTAL_HISTORY_COUNT++;

		// 更新最高访问量
		if (ApplicationConstants.OnlineSessionMap.size() > ApplicationConstants.MAX_ONLINE_COUNT) {

			ApplicationConstants.MAX_ONLINE_COUNT = ApplicationConstants.OnlineSessionMap
					.size();

			// 更新最高访问量出现的时间
			ApplicationConstants.MAX_ONLINE_COUNT_DATE = new Date();
		}

		System.out.println("创建session，在线session数："
				+ ApplicationConstants.OnlineSessionMap.size());

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

		// 获取注销session
		HttpSession oldsession = arg0.getSession();

		// 从在线集合中移除
		ApplicationConstants.OnlineSessionMap.remove(oldsession.getId());

		System.out.println("移除session，剩余在线session数："
				+ ApplicationConstants.OnlineSessionMap.size());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpSessionAttributeListener#attributeAdded(javax.
	 * servlet.http.HttpSessionBindingEvent)
	 */
	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {

		// 如果为loginAccount，则登录数++
		if (arg0.getName().equals("loginAccount")) {

			ApplicationConstants.CURRENT_LOGIN_COUNT++;

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpSessionAttributeListener#attributeRemoved(javax
	 * .servlet.http.HttpSessionBindingEvent)
	 */
	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {

		// 如果为loginAccount，则登录数--
		if (arg0.getName().equals("loginAccount")) {

			ApplicationConstants.CURRENT_LOGIN_COUNT--;

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpSessionAttributeListener#attributeReplaced(javax
	 * .servlet.http.HttpSessionBindingEvent)
	 */
	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {

	}

}
