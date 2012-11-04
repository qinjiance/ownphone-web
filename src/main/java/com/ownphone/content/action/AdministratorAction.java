/**
 * 
 */
package com.ownphone.content.action;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ownphone.content.bean.AccountInfoForm;
import com.ownphone.content.bean.AccountPasswordForm;
import com.ownphone.content.bean.UserQueryForm;
import com.ownphone.content.dao.AdministratorDAO;
import com.ownphone.content.dao.CommonUserDAO;
import com.ownphone.content.dao.HibernateOperateException;
import com.ownphone.content.dao.impl.AdministratorDAOImpl;
import com.ownphone.content.dao.impl.CommonUserDAOImpl;
import com.ownphone.content.po.Administrator;
import com.ownphone.content.po.CommonUser;
import com.ownphone.content.po.IUser;
import com.ownphone.util.FormValidator;

/**
 * @author Jiance Qin
 * 
 */
public class AdministratorAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2009405450348536757L;

	private Map<String, Object> session = ActionContext.getContext()
			.getSession();

	@SuppressWarnings("unchecked")
	private Map<String, Object> request = (Map<String, Object>) ActionContext
			.getContext().get("request");

	/**
	 * a form bean instance submitted by modifyaccountinfo.jsp
	 */
	private AccountInfoForm accountInfo;

	/**
	 * a form bean instance submitted by modifyaccountpassword.jsp
	 */
	private AccountPasswordForm accountPassword;

	private AdministratorDAO administratorDAO = new AdministratorDAOImpl();

	private CommonUserDAO commonUserDAO = new CommonUserDAOImpl();

	private UserQueryForm userQuery;

	/**
	 * @return the userQuery
	 */
	public UserQueryForm getUserQuery() {
		return userQuery;
	}

	/**
	 * @param userQuery
	 *            the userQuery to set
	 */
	public void setUserQuery(UserQueryForm userQuery) {
		this.userQuery = userQuery;
	}

	/**
	 * 
	 */
	public AdministratorAction() {

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
	 * Find all users including administrators.
	 * 
	 * @return a string which point to the result page on struts.xml
	 */
	public String showAllUsers() {

		if (!validateAdminLogin()) {
			return "validateloginfailed";
		}

		final int ITEMS = 5;

		int requestPage = 1;

		String requestPageStr = ServletActionContext.getRequest().getParameter(
				"page");

		if (requestPageStr != null && !requestPageStr.isEmpty()) {
			if (requestPageStr.matches("^\\d+$")) {
				requestPage = Integer.valueOf(requestPageStr).intValue();
			}
		}

		int starts = ITEMS * (requestPage - 1);
		int ends = ITEMS * requestPage - 1;

		String accountquery = null;
		List<IUser> userList = null;
		Integer userSize = null;
		try {

			// Query ordernumber
			accountquery = ServletActionContext.getRequest().getParameter(
					"accountquery");

			if (accountquery != null && !accountquery.trim().isEmpty()) {

				IUser user = commonUserDAO
						.findCommonUserByUseraccount(accountquery);
				if (user != null) {

					userSize = Integer.valueOf(1);

					userList = new LinkedList<IUser>();

					userList.add(user);
				} else {
					user = administratorDAO
							.findAdministratorByAdminaccount(accountquery);
					if (user != null) {
						userSize = Integer.valueOf(1);

						userList = new LinkedList<IUser>();

						userList.add(user);
					} else {
						userSize = Integer.valueOf(0);
					}
				}
			} else {

				if (userQuery == null) {

					userQuery = new UserQueryForm();
					userQuery.setOrdertype("account");
					userQuery.setOrderdirection("increasing");
				}

				// Query users size
				int commonUserSize = commonUserDAO
						.sizeOfCommonUsersWithConditions(
								userQuery.getNickname(),
								userQuery.getRealname(),
								userQuery.getMobilephone(),
								userQuery.getEmail(), userQuery.getPrivilege(),
								userQuery.getRegistertime());
				int adminSize = administratorDAO
						.sizeOfAdministratorsWithConditions(
								userQuery.getNickname(),
								userQuery.getRealname(),
								userQuery.getMobilephone(),
								userQuery.getEmail(), userQuery.getPrivilege(),
								userQuery.getRegistertime());

				userSize = Integer.valueOf(commonUserSize + adminSize);

				// Query order list
				userList = new LinkedList<IUser>();
				List<CommonUser> commonUsersList = commonUserDAO
						.findCommonUsersWithConditions(starts, ends,
								userQuery.getOrdertype(),
								userQuery.getOrderdirection(),
								userQuery.getNickname(),
								userQuery.getRealname(),
								userQuery.getMobilephone(),
								userQuery.getEmail(), userQuery.getPrivilege(),
								userQuery.getRegistertime());

				int commonUsersListSize = commonUsersList == null ? 0
						: commonUsersList.size();

				if (commonUsersList == null || commonUsersListSize < ITEMS) {

					int commonUserTotalPages = (int) Math
							.ceil((double) commonUserSize / ITEMS);
					int commonUserLastPageItems = commonUserSize % ITEMS;
					if (commonUserLastPageItems == 0) {
						commonUserTotalPages++;
					}

					int adminStarts = ITEMS
							* (requestPage - commonUserTotalPages)
							- commonUserLastPageItems;
					adminStarts = adminStarts <= 0 ? 0 : adminStarts;

					int adminEnds = ITEMS
							* (requestPage - commonUserTotalPages + 1) - 1
							- commonUserLastPageItems;

					List<Administrator> adminList = administratorDAO
							.findAdministratorsWithConditions(adminStarts,
									adminEnds, userQuery.getOrdertype(),
									userQuery.getOrderdirection(),
									userQuery.getNickname(),
									userQuery.getRealname(),
									userQuery.getMobilephone(),
									userQuery.getEmail(),
									userQuery.getPrivilege(),
									userQuery.getRegistertime());
					if (commonUsersList != null) {
						userList.addAll(commonUsersList);
					}

					if (adminList != null) {
						userList.addAll(adminList);
					}
				} else {
					userList.addAll(commonUsersList);
				}
			}
		} catch (HibernateOperateException e) {
			this.addActionMessage("操作失败，请重试，或联系管理员。");
			return "finduserserror";
		}

		Integer pageSize = Integer.valueOf((int) Math.ceil((double) userSize
				.intValue() / ITEMS));

		request.put("userSize", userSize);
		request.put("pageSize", pageSize);
		request.put("currentPage", Integer.valueOf(requestPage));
		request.put("accountquery", accountquery);
		request.put("userQuery", userQuery);
		request.put("userListToShow", userList);

		return "finduserssuccess";
	}

	/**
	 * Modify account info in database using the input from
	 * modifyaccountinfo.jsp
	 * 
	 * @return a string which point to the result page on struts.xml
	 */
	public String modifyAccountInfo() {

		if (!validateAdminLogin()) {
			return "validateloginfailed";
		}

		if (!validateAccountInfoForm(accountInfo)) {
			return "validateaccountinfofailed";
		}

		accountInfo.setUseraccount(((Administrator) (session
				.get("loginAccount"))).getAdminaccount());

		Administrator updatedAdministrator = null;
		try {
			updatedAdministrator = administratorDAO
					.updateAccountInfo(accountInfo);
		} catch (HibernateOperateException e) {
			this.addFieldError("modifyaccountinfoerror", "操作失败，请重试，或联系管理员。");
			return "modifyaccountinfofailed";
		}

		if (updatedAdministrator == null) {
			this.addFieldError("modifyaccountinfosuccess", "操作已成功！请重新登录以获得更新。");
			return "modifyaccountinfosuccess";
		} else {
			this.addFieldError("modifyaccountinfosuccess", "操作已成功！");

			// Remove the user password for security
			updatedAdministrator.setPassword(null);

			session.put("loginAccount", (IUser) updatedAdministrator);
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

		if (!validateAdminLogin()) {
			return "validateloginfailed";
		}

		if (!validateAccountPasswordForm(accountPassword)) {
			return "validateaccountpasswordfailed";
		}

		String adminAccount = ((Administrator) (session.get("loginAccount")))
				.getAdminaccount();

		try {
			if (!administratorDAO.validatePasswordForAdminaccount(adminAccount,
					accountPassword.getOldpassword())) {
				this.addFieldError("modifyaccountpassworderror", "原密码输入错误！");
				return "validateoldpasswordfailed";
			}
		} catch (HibernateOperateException e) {
			this.addFieldError("modifyaccountpassworderror", "操作失败，请重试，或联系管理员。");
			return "validateoldpasswordfailed";
		}

		accountPassword.setUseraccount(adminAccount);

		Administrator updatedAdministrator = null;
		try {
			updatedAdministrator = administratorDAO
					.updateAccountPassword(accountPassword);
		} catch (HibernateOperateException e) {
			this.addFieldError("modifyaccountpassworderror", "操作失败，请重试，或联系管理员。");
			return "modifyaccountpasswordfailed";
		}

		if (updatedAdministrator == null) {
			this.addFieldError("modifyaccountpasswordsuccess",
					"操作已成功！请重新登录以获得更新。");
			return "modifyaccountpasswordsuccess";
		} else {
			this.addFieldError("modifyaccountpasswordsuccess", "操作已成功！");

			// Remove the user password for security
			updatedAdministrator.setPassword(null);

			session.put("loginAccount", (IUser) updatedAdministrator);
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
	 * Validate if administrator is login
	 * 
	 * @return
	 */
	public boolean validateAdminLogin() {

		// Validate if administrator is login
		if (session.get("loginAccount") == null
				|| !((IUser) (session.get("loginAccount"))).fetchPrivilege()
						.equals("admin")) {
			return false;
		}

		return true;
	}

}
