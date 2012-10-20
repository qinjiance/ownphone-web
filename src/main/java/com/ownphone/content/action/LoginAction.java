package com.ownphone.content.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ownphone.content.bean.LoginInfo;
import com.ownphone.content.bean.LoginUserForm;
import com.ownphone.content.dao.AdministratorDAO;
import com.ownphone.content.dao.CommonUserDAO;
import com.ownphone.content.dao.HibernateOperateException;
import com.ownphone.content.dao.impl.AdministratorDAOImpl;
import com.ownphone.content.dao.impl.CommonUserDAOImpl;
import com.ownphone.content.po.Administrator;
import com.ownphone.content.po.CommonUser;
import com.ownphone.content.po.IUser;

/**
 * @author Jiance Qin
 * 
 */
public class LoginAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 237414239021046238L;

	/**
	 * a form bean instance submitted by navigator.jsp
	 */
	private LoginUserForm loginUser;

	/**
	 * @return the loginUser
	 */
	public LoginUserForm getLoginUser() {
		return loginUser;
	}

	/**
	 * @param loginUser
	 *            the loginUser to set
	 */
	public void setLoginUser(LoginUserForm loginUser) {
		this.loginUser = loginUser;
	}

	// DAO instance
	private CommonUserDAO commonUserDAO = new CommonUserDAOImpl();

	private AdministratorDAO administratorDAO = new AdministratorDAOImpl();

	private Map<String, Object> session = ActionContext.getContext()
			.getSession();

	private HttpServletRequest request = ServletActionContext.getRequest();

	@Override
	public String execute() throws Exception {

		session.clear();

		// Confirm common user
		IUser confirmUser = null;
		try {
			confirmUser = commonUserDAO.loginConfirm(loginUser);
		} catch (HibernateOperateException e) {
			this.addFieldError("loginerror", "操作失败，请重试，或联系管理官！");
			return "loginfailed";
		}

		// If not a common user, then confirm administrator
		if (confirmUser == null) {

			// Confirm administrator
			try {
				confirmUser = administratorDAO.loginConfirm(loginUser);
			} catch (HibernateOperateException e) {
				this.addFieldError("loginerror", "操作失败，请重试，或联系管理官！");
				return "loginfailed";
			}

			if (confirmUser == null) {
				this.addFieldError("loginerror", "您输入的用户名或密码不正确！");
				return "loginfailed";
			}
		}

		String privilege = confirmUser.fetchPrivilege();

		if (privilege == null || "".equals(privilege)) {
			this.addFieldError("loginerror", "您的账户无任何权限，登录失败，请联系管理员");
			return "loginfailed";
		} else {
			// Check account privilege
			if (privilege.equals("common")) {

				// Store the user login info
				LoginInfo loginInfo = new LoginInfo();
				loginInfo.setLoginip(request.getRemoteAddr());
				loginInfo.setLoginhost(request.getRemoteHost());
				loginInfo.setLogindatatime(new Date());

				// Remove the user password for security
				((CommonUser) confirmUser).setPassword(null);

				// loginInfo must be added in front of loginAccount, because
				// LoginSessionListener will use loginInfo first.
				session.put("loginInfo", loginInfo);
				session.put("loginAccount", confirmUser);
				return "commonuserloginsuccess";

			} else if (privilege.equals("admin")) {

				// Store the admin login info.
				LoginInfo loginInfo = new LoginInfo();
				loginInfo.setLoginip(request.getRemoteAddr());
				loginInfo.setLoginhost(request.getRemoteHost());
				loginInfo.setLogindatatime(new Date());

				// Remove the user password for security
				((Administrator) confirmUser).setPassword(null);

				session.put("loginInfo", loginInfo);
				session.put("loginAccount", confirmUser);
				return "adminloginsuccess";

			} else {
				this.addFieldError("loginerror", "您的账户存在权限异常，登录失败，请联系管理员");
				return "loginfailed";
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	@Override
	public void validate() {

		boolean checkPassed = true;

		// Validates the useraccount field.
		if (loginUser.getUseraccount() == null
				|| "".equals(loginUser.getUseraccount().trim())) {
			this.addFieldError("loginerror", "请输入用户名");
			checkPassed = false;
		}

		// Validates the useraccount field.
		if (loginUser.getPassword() == null
				|| "".equals(loginUser.getPassword().trim())) {

			// Get the previous fielderror
			List<String> fieldErrorList = this.getFieldErrors().get(
					"loginerror");
			if (fieldErrorList == null) {
				this.addFieldError("loginerror", "请输入密码");
			} else {
				// Combine error message to one new error message.
				fieldErrorList.set(0, fieldErrorList.get(0) + "和密码");

				// Put the List above into fieldErrors map to make "fiederror"
				// only contain one messager so that the struts2 only show one
				// error row
				this.getFieldErrors().put("loginerror", fieldErrorList);
				checkPassed = false;
			}
		}

		// Check the checking result above
		if (checkPassed) {
			super.validate();
		} else {
			return;
		}
	}
}
