/**
 * 
 */
package com.ownphone.content.action;

import com.opensymphony.xwork2.ActionSupport;
import com.ownphone.content.bean.CommonUserRegisterForm;
import com.ownphone.content.dao.AdministratorDAO;
import com.ownphone.content.dao.CommonUserDAO;
import com.ownphone.content.dao.impl.AdministratorDAOImpl;
import com.ownphone.content.dao.impl.CommonUserDAOImpl;
import com.ownphone.content.po.CommonUser;
import com.ownphone.util.FormValidator;

/**
 * @author Jiance Qin
 * 
 */
public class RegisterAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3175269986341356751L;

	/**
	 * a form bean instance submitted by register.jsp
	 */
	private CommonUserRegisterForm commonUserRegister;

	private CommonUserDAO commonUserDAO = new CommonUserDAOImpl();

	private AdministratorDAO administratorDAO = new AdministratorDAOImpl();

	/**
	 * 
	 */
	public RegisterAction() {

	}

	/**
	 * @return the commonUserRegister
	 */
	public CommonUserRegisterForm getCommonUserRegister() {
		return commonUserRegister;
	}

	/**
	 * @param commonUserRegister
	 *            the commonUserRegister to set
	 */
	public void setCommonUserRegister(CommonUserRegisterForm commonUserRegister) {
		this.commonUserRegister = commonUserRegister;
	}

	/**
	 * @return the commonUserDAO
	 */
	public CommonUserDAO getCommonUserDAO() {
		return commonUserDAO;
	}

	/**
	 * @param commonUserDAO
	 *            the commonUserDAO to set
	 */
	public void setCommonUserDAO(CommonUserDAO commonUserDAO) {
		this.commonUserDAO = commonUserDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {

		try {
			// Check if the new useraccount is exist in CommonUser and
			// Administrator table in database
			if (commonUserDAO.isUseraccountExist(commonUserRegister
					.getUseraccount())
					|| administratorDAO.isAdminaccountExist(commonUserRegister
							.getUseraccount())) {
				this.addFieldError("commonUserRegister.useraccount",
						"用户名已存在，请使用其他用户名！");
				return "input";
			}

		} catch (Exception e) {
			this.addActionMessage("操作失败，请重新注册！");
			return "registerfailed";
		}

		// Set new CommonUser instantce
		CommonUser newCommonUser = new CommonUser();
		newCommonUser.setUseraccount(commonUserRegister.getUseraccount());
		newCommonUser.setPassword(commonUserRegister.getPassword());
		newCommonUser.setNickname(commonUserRegister.getNickname());
		newCommonUser.setEmail(commonUserRegister.getEmail());
		newCommonUser.setMobilephone(commonUserRegister.getMobilephone());
		newCommonUser.setRealname(commonUserRegister.getRealname());
		newCommonUser.setPrivilege("common");
		newCommonUser.setRegistertimemillis(Long.valueOf(System
				.currentTimeMillis()));

		if (!commonUserDAO.addNewCommonUser(newCommonUser)) {
			this.addActionMessage("操作失败，请重新注册！");
			return "registerfailed";
		} else {
			this.addActionMessage("恭喜您，注册成功！请登录!");
			return "registersuccess";
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

		String formBeanName = "commonUserRegister";

		// Validate the user account field
		if (!FormValidator.validateFieldUsingRegex(this, formBeanName,
				commonUserRegister, "useraccount",
				FormValidator.CHECK_USERACCOUNT_REGEX, false, "请输入用户名！",
				"用户名只能由1-12位的英文、数字和下划线组成，且只能以下划线或英文开头！")) {
			checkPassed = false;
		}

		// Validate the password field
		if (!FormValidator.validateFieldUsingRegex(this, formBeanName,
				commonUserRegister, "password",
				FormValidator.CHECK_PASSWORD_REGEX, false, "请输入密码！",
				"密码只能由6-18位的英文、数字和下划线组成！")) {
			checkPassed = false;
		}

		// Validate the repeatpassword field
		if (!commonUserRegister.getPassword().equals(
				commonUserRegister.getRepeatpassword())) {
			checkPassed = false;
			this.addFieldError(formBeanName + ".repeatpassword", "重复密码与密码不一致！");
		}

		// Validate the nickname field
		if (!FormValidator.validateFieldUsingRegex(this, formBeanName,
				commonUserRegister, "nickname",
				FormValidator.CHECK_NICKNAME_REGEX, true, null,
				"昵称只能由1到10个字，包括中文、英文、数字、下划线和它们中间的空格组成！")) {
			checkPassed = false;
		}

		// Validate the email field
		if (!FormValidator.validateFieldUsingRegex(this, formBeanName,
				commonUserRegister, "email", FormValidator.CHECK_EMAIL_REGEX,
				true, null, "电子邮箱地址格式有误！")) {
			checkPassed = false;
		}

		// Validate the mobilephone field
		if (!FormValidator.validateFieldUsingRegex(this, formBeanName,
				commonUserRegister, "mobilephone",
				FormValidator.CHECK_MOBILEPHONE_REGEX, true, null,
				"手机号码只能由以1开头的11个数字组成！")) {
			checkPassed = false;
		}

		// Validate the realname field
		if (!FormValidator.validateFieldUsingRegex(this, formBeanName,
				commonUserRegister, "realname",
				FormValidator.CHECK_REALNAME_REGEX, true, null,
				"中文姓名只能由中文组成，英文名只能由英文和它们中间的空格组成！")) {
			checkPassed = false;
		}

		// Check the checking result above
		if (checkPassed) {
			super.validate();
		} else {
			return;
		}
	}

}
