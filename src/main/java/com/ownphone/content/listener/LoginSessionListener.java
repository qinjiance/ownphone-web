/**
 * 
 */
package com.ownphone.content.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.ownphone.content.bean.LoginInfo;
import com.ownphone.content.po.IUser;

/**
 * @author Jiance
 * 
 */
public class LoginSessionListener implements HttpSessionAttributeListener {

	// String命名规则为"useraccount"，Object为已登陆用户的session
	private static Map<String, Object> LoginSessionGroup = new HashMap<String, Object>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpSessionAttributeListener#attributeAdded(javax.
	 * servlet.http.HttpSessionBindingEvent)
	 */
	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {

		// 取出将加入的属性名
		String attributename = arg0.getName();

		String loginAttributeName = "loginAccount";
		String loginInfoAttributeName = "loginInfo";

		// 如果是loginAccount，则表示有账号登陆
		if (attributename.equals(loginAttributeName)) {
			// 取出将加入的loginAccount用户
			String loginUseraccount = ((IUser) arg0.getValue())
					.fetchUseraccount();

			HttpSession oldLoginSession = (HttpSession) LoginSessionGroup
					.get(loginUseraccount);

			HttpSession newLoginSession = arg0.getSession();
			// 如果存在，表明为重复登陆账号
			if (oldLoginSession != null) {
				// 移除原登陆session内的登陆信息
				oldLoginSession.removeAttribute(loginAttributeName);
				oldLoginSession.removeAttribute(loginInfoAttributeName);

				// 取出loginInfo
				LoginInfo loginInfo = (LoginInfo) newLoginSession
						.getAttribute(loginInfoAttributeName);

				// 弹框给原登陆浏览器以作提示
				oldLoginSession
						.setAttribute("repeatLoginError", "您的账号已经在其他机器上登陆，主机名："
								+ loginInfo.getLoginhost() + "；IP地址："
								+ loginInfo.getLoginip() + "；您被迫下线！");

			} else {
				;
			}

			// 将新登录的session加入map中
			LoginSessionGroup.put(loginUseraccount, newLoginSession);

			System.out.println("LoginSessionGroup MAP 中的用户Session数为："
					+ LoginSessionGroup.size());
		} else {
			;
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

		// 取出将删除的属性名
		String attributename = arg0.getName();

		String loginAttributeName = "loginAccount";

		// 如果是loginAccount，则表示有账号将注销
		if (attributename.equals(loginAttributeName)) {
			// 取出将删除的loginAccount属性
			String loginUseraccount = ((IUser) arg0.getValue())
					.fetchUseraccount();

			// 移除map中的将注销的session
			LoginSessionGroup.remove(loginUseraccount);

			System.out.println("LoginSessionGroup MAP 中的用户Session数为："
					+ LoginSessionGroup.size());
		} else {
			;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionAttributeL
	 * istener#attributeReplaced(javax.servlet.http.HttpSessionBindingEvent)
	 */
	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {

		// 取出将要修改的属性名
		String attributename = arg0.getName();

		String loginAttributeName = "loginAccount";
		String loginInfoAttributeName = "loginInfo";

		// 如果是loginAccount，则表示在本页面没有注销的情况下，用另一账号登陆
		if (attributename.equals(loginAttributeName)) {
			// 取出将删除的loginAccount属性
			IUser oldLoginUser = (IUser) arg0.getValue();
			// 移除map中的将注销的session
			LoginSessionGroup.remove(oldLoginUser.fetchUseraccount());

			HttpSession newLoginSession = arg0.getSession();

			// 取出将加入的loginAccount属性
			IUser newLoginUser = (IUser) newLoginSession
					.getAttribute(loginAttributeName);

			// 取出将加入的useraccount
			String newUseraccount = newLoginUser.fetchUseraccount();

			// 尝试取出该账号已登录的session
			HttpSession oldLoginSession = (HttpSession) LoginSessionGroup
					.get(newUseraccount);
			// 如果存在，表明为重复登陆账号
			if (oldLoginSession != null) {
				// 移除原登陆session内的登陆信息
				oldLoginSession.removeAttribute(loginAttributeName);
				oldLoginSession.removeAttribute(loginInfoAttributeName);

				// 取出loginInfo
				LoginInfo loginInfo = (LoginInfo) newLoginSession
						.getAttribute(loginInfoAttributeName);

				// 弹框给原登陆浏览器以作提示
				oldLoginSession
						.setAttribute("repeatLoginError", "您的账号已经在其他机器上登陆，主机名："
								+ loginInfo.getLoginhost() + "；IP地址："
								+ loginInfo.getLoginip() + "；您被迫下线！");

			} else {
				;
			}

			// 将新登录的session加入map中
			LoginSessionGroup.put(newUseraccount, newLoginSession);

			System.out.println("LoginSessionGroup MAP 中的用户Session数为："
					+ LoginSessionGroup.size());
		} else {
			;
		}

	}

}
