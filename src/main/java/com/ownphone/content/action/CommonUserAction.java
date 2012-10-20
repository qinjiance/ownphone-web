/**
 * 
 */
package com.ownphone.content.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ownphone.content.bean.AccountInfoForm;
import com.ownphone.content.bean.AccountPasswordForm;
import com.ownphone.content.dao.CommonUserDAO;
import com.ownphone.content.dao.HibernateOperateException;
import com.ownphone.content.dao.impl.CommonUserDAOImpl;
import com.ownphone.content.po.CommonUser;
import com.ownphone.content.po.IUser;
import com.ownphone.util.FormValidator;

/**
 * @author Jiance Qin
 * 
 */
public class CommonUserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2009405450348536757L;

	private Map<String, Object> session = ActionContext.getContext()
			.getSession();

	/**
	 * a form bean instance submitted by modifyaccountinfo.jsp
	 */
	private AccountInfoForm accountInfo;

	/**
	 * a form bean instance submitted by modifyaccountpassword.jsp
	 */
	private AccountPasswordForm accountPassword;

	private CommonUserDAO commonUserDAO = new CommonUserDAOImpl();

	/**
	 * 
	 */
	public CommonUserAction() {

	}

	/**
	 * @return the accountInfo
	 */
	public AccountInfoForm getAccountInfo() {
		return accountInfo;
	}

	/**
	 * @param accountInfo
	 *            the accountInfo to set
	 */
	public void setAccountInfo(AccountInfoForm accountInfo) {
		this.accountInfo = accountInfo;
	}

	/**
	 * @return the accountPassword
	 */
	public AccountPasswordForm getAccountPassword() {
		return accountPassword;
	}

	/**
	 * @param accountPassword
	 *            the accountPassword to set
	 */
	public void setAccountPassword(AccountPasswordForm accountPassword) {
		this.accountPassword = accountPassword;
	}

	/**
	 * Modify account info in database using the input from
	 * modifyaccountinfo.jsp
	 * 
	 * @return a string which point to the result page on struts.xml
	 */
	public String modifyAccountInfo() {

		if (!validateCommonUserLogin()) {
			return "validateloginfailed";
		}

		if (!validateAccountInfoForm(accountInfo)) {
			return "validateaccountinfofailed";
		}

		accountInfo.setUseraccount(((CommonUser) (session.get("loginAccount")))
				.getUseraccount());

		CommonUser updatedCommonUser = null;
		try {
			updatedCommonUser = commonUserDAO.updateAccountInfo(accountInfo);
		} catch (HibernateOperateException e) {
			this.addFieldError("modifyaccountinfoerror", "操作失败，请重试，或联系管理员。");
			return "modifyaccountinfofailed";
		}

		if (updatedCommonUser == null) {
			this.addFieldError("modifyaccountinfosuccess", "操作已成功！请重新登录以获得更新。");
			return "modifyaccountinfosuccess";
		} else {
			this.addFieldError("modifyaccountinfosuccess", "操作已成功！");

			// Remove the user password for security
			updatedCommonUser.setPassword(null);

			session.put("loginAccount", (IUser) updatedCommonUser);
			return "modifyaccountinfosuccess";
		}
	}

	/**
	 * Modify account password in database using the input from
	 * modifyaccountpassword.jsp
	 * 
	 * @return a string which point to the result page on struts.xml
	 */
	public String modifyAccountPassword() {

		if (!validateCommonUserLogin()) {
			return "validateloginfailed";
		}

		if (!validateAccountPasswordForm(accountPassword)) {
			return "validateaccountpasswordfailed";
		}

		String userAccount = ((CommonUser) (session.get("loginAccount")))
				.getUseraccount();

		try {
			if (!commonUserDAO.validatePasswordForUseraccount(userAccount,
					accountPassword.getOldpassword())) {
				this.addFieldError("modifyaccountpassworderror", "原密码输入错误！");
				return "validateoldpasswordfailed";
			}
		} catch (HibernateOperateException e) {
			this.addFieldError("modifyaccountpassworderror", "操作失败，请重试，或联系管理员。");
			return "validateoldpasswordfailed";
		}

		accountPassword.setUseraccount(userAccount);

		CommonUser updatedCommonUser = null;
		try {
			updatedCommonUser = commonUserDAO
					.updateAccountPassword(accountPassword);
		} catch (HibernateOperateException e) {
			this.addFieldError("modifyaccountpassworderror", "操作失败，请重试，或联系管理员。");
			return "modifyaccountpasswordfailed";
		}

		if (updatedCommonUser == null) {
			this.addFieldError("modifyaccountpasswordsuccess",
					"操作已成功！请重新登录以获得更新。");
			return "modifyaccountpasswordsuccess";
		} else {
			this.addFieldError("modifyaccountpasswordsuccess", "操作已成功！");

			// Remove the user password for security
			updatedCommonUser.setPassword(null);

			session.put("loginAccount", (IUser) updatedCommonUser);
			return "modifyaccountpasswordsuccess";
		}
	}

	/**
	 * Validate AccountInfoForm from modifyaccountinfo.jsp
	 * 
	 * @param accountInfoForm
	 *            AccountInfoForm will be validated
	 * 
	 * @return true if validation passed, otherwise false
	 */
	private boolean validateAccountInfoForm(AccountInfoForm accountInfoForm) {

		boolean checkPassed = true;
		String formBeanName = "accountInfo";

		// Validates the nickname field.
		if (!FormValidator.validateFieldUsingRegex(this, formBeanName,
				accountInfoForm, "nickname",
				FormValidator.CHECK_NICKNAME_REGEX, true, null,
				"昵称只能由1到10个字，包括中文、英文、数字、下划线和它们中间的空格组成！")) {
			checkPassed = false;
		}

		// Validates the email field.
		if (!FormValidator.validateFieldUsingRegex(this, formBeanName,
				accountInfoForm, "email", FormValidator.CHECK_EMAIL_REGEX,
				true, null, "电子邮箱地址格式有误！")) {
			checkPassed = false;
		}

		// Validates the mobilephone field.
		if (!FormValidator.validateFieldUsingRegex(this, formBeanName,
				accountInfoForm, "mobilephone",
				FormValidator.CHECK_MOBILEPHONE_REGEX, true, null,
				"手机号码只能由以1开头的11个数字组成！")) {
			checkPassed = false;
		}

		// Validates the realname field.
		if (!FormValidator.validateFieldUsingRegex(this, formBeanName,
				accountInfoForm, "realname",
				FormValidator.CHECK_REALNAME_REGEX, true, null,
				"中文姓名只能由中文组成，英文名只能由英文和它们中间的空格组成！")) {
			checkPassed = false;
		}

		return checkPassed;
	}

	/**
	 * Validate AccountPasswordForm from modifyaccountpassword.jsp
	 * 
	 * @param accountPasswordForm
	 *            AccountPasswordForm will be validated
	 * 
	 * @return true if validation passed, otherwise false
	 */
	private boolean validateAccountPasswordForm(
			AccountPasswordForm accountPasswordForm) {

		boolean checkPassed = true;
		String formBeanName = "accountPassword";

		// Validates the oldpassword field.
		if (!FormValidator.validateFieldUsingRegex(this, formBeanName,
				accountPasswordForm, "oldpassword",
				FormValidator.CHECK_PASSWORD_REGEX, false, "请输入原密码！",
				"密码只能由6-18位的英文、数字和下划线组成！")) {
			checkPassed = false;
		}

		// Validates the newpassword field.
		if (!FormValidator.validateFieldUsingRegex(this, formBeanName,
				accountPasswordForm, "newpassword",
				FormValidator.CHECK_PASSWORD_REGEX, false, "请输入新密码！",
				"密码只能由6-18位的英文、数字和下划线组成！")) {
			checkPassed = false;
		}

		// Validates the repeatnewpassword field.
		if (!accountPasswordForm.getRepeatnewpassword().equals(
				accountPasswordForm.getNewpassword())) {
			checkPassed = false;
			this.addFieldError(formBeanName + ".repeatnewpassword",
					"重复新密码与新密码不一致！");
		}

		return checkPassed;
	}

	/**
	 * Validate if common user is login
	 * 
	 * @return
	 */
	public boolean validateCommonUserLogin() {

		// Validate if common user is login
		if (session.get("loginAccount") == null
				|| !((IUser) (session.get("loginAccount"))).fetchPrivilege()
						.equals("common")) {
			return false;
		}

		return true;
	}

}
