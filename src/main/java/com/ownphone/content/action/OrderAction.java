/**
 * 
 */
package com.ownphone.content.action;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ownphone.content.bean.LoginInfo;
import com.ownphone.content.bean.OwnPhoneOrderQueryForm;
import com.ownphone.content.dao.HibernateOperateException;
import com.ownphone.content.dao.OwnPhoneOrderDAO;
import com.ownphone.content.dao.impl.OwnPhoneOrderDAOImpl;
import com.ownphone.content.po.Administrator;
import com.ownphone.content.po.CommonUser;
import com.ownphone.content.po.IUser;
import com.ownphone.content.po.OwnPhoneOrder;
import com.ownphone.util.CommonUserAccountUtil;
import com.ownphone.util.FormValidator;

/**
 * @author Jiance Qin
 * 
 */
public class OrderAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4440347288771914007L;

	private Map<String, Object> session = ActionContext.getContext()
			.getSession();

	@SuppressWarnings("unchecked")
	private Map<String, Object> request = (Map<String, Object>) ActionContext
			.getContext().get("request");

	/**
	 * a form bean instance submitted by designyourownphonorer.jsp
	 */
	private OwnPhoneOrder ownPhoneOrder;

	/**
	 * a form bean instance submitted by modifyownphoneorder.jsp
	 */
	private OwnPhoneOrder modifyingOwnPhoneOrder;

	/**
	 * a form bean instance submitted by ownphoneordermanagement.jsp
	 */
	private OwnPhoneOrderQueryForm ownPhoneOrderQuery;

	/**
	 * OwnPhoneOrderDAOImpl instance
	 */
	private OwnPhoneOrderDAO ownPhoneOrderDAO = new OwnPhoneOrderDAOImpl();

	/**
	 * @return the ownPhoneOrder
	 */
	public OwnPhoneOrder getOwnPhoneOrder() {
		return ownPhoneOrder;
	}

	/**
	 * @param ownPhoneOrder
	 *            the ownPhoneOrder to set
	 */
	public void setOwnPhoneOrder(OwnPhoneOrder ownPhoneOrder) {
		this.ownPhoneOrder = ownPhoneOrder;
	}

	/**
	 * @return the modifyingOwnPhoneOrder
	 */
	public OwnPhoneOrder getModifyingOwnPhoneOrder() {
		return modifyingOwnPhoneOrder;
	}

	/**
	 * @param modifyingOwnPhoneOrder
	 *            the modifyingOwnPhoneOrder to set
	 */
	public void setModifyingOwnPhoneOrder(OwnPhoneOrder modifyingOwnPhoneOrder) {
		this.modifyingOwnPhoneOrder = modifyingOwnPhoneOrder;
	}

	/**
	 * @return the ownPhoneOrderQuery
	 */
	public OwnPhoneOrderQueryForm getOwnPhoneOrderQuery() {
		return ownPhoneOrderQuery;
	}

	/**
	 * @param ownPhoneOrderQuery
	 *            the ownPhoneOrderQuery to set
	 */
	public void setOwnPhoneOrderQuery(OwnPhoneOrderQueryForm ownPhoneOrderQuery) {
		this.ownPhoneOrderQuery = ownPhoneOrderQuery;
	}

	/**
	 * Find OwnPhoneOrder by login ordernumber.
	 * 
	 * @return a string which point to the result page on struts.xml
	 */
	public String showOwnPhoneOrderByOrderNumber() {

		if (!validateLogin()) {
			return "validateloginfailed";
		}

		String requestOrderNumber = ServletActionContext.getRequest()
				.getParameter("orderNumber");

		if (requestOrderNumber == null || "".equals(requestOrderNumber)) {
			this.addActionMessage("请求失败，请重新选择，或联系管理员");
			return "requesterror";
		}

		OwnPhoneOrder foundOwnPhoneOrder;
		try {
			foundOwnPhoneOrder = ownPhoneOrderDAO
					.findOwnPhoneOrderByOrderNumber(Long
							.valueOf(requestOrderNumber));
		} catch (HibernateOperateException e) {
			this.addActionMessage("操作失败，请重新选择，或联系管理员");
			return "findownphoneordererror";
		}

		if (foundOwnPhoneOrder == null) {
			this.addActionMessage("该订单不存在，请重新选择，或联系管理员");
			return "ownphoneordernotexisted";
		} else {
			request.put("ownPhoneOrderToShow", foundOwnPhoneOrder);

			String privilege = ((IUser) session.get("loginAccount"))
					.fetchPrivilege();

			if (privilege.equals("common")) {
				return "findownphoneordersuccess";
			} else if (privilege.equals("admin")) {
				return "findownphoneordersuccess";
			} else {
				this.addActionMessage("用户权限错误，请重试，或联系管理员。");
				return "privilegeerror";
			}
		}

	}

	/**
	 * Find OwnPhoneOrders by login useraccount.
	 * 
	 * @return a string which point to the result page on struts.xml
	 */
	public String showCommonUserOwnPhoneOrders() {

		if (!validateCommonUserLogin()) {
			return "validateloginfailed";
		}

		String commonUseraccount = ((CommonUser) (session.get("loginAccount")))
				.getUseraccount();

		final int ITEMS = 10;

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

		String ordernumberquery = null;
		List<OwnPhoneOrder> ownPhoneOrderList = null;
		Integer orderSize = null;
		try {

			// Query ordernumber
			ordernumberquery = ServletActionContext.getRequest().getParameter(
					"ordernumberquery");

			if (ordernumberquery != null && !ordernumberquery.trim().isEmpty()) {

				if (FormValidator.validateStringUsingRegex(ordernumberquery,
						"^\\d+$")) {

					OwnPhoneOrder ownPhoneOrder = ownPhoneOrderDAO
							.findOwnPhoneOrderByOrderNumber(Long
									.valueOf(ordernumberquery));
					if (ownPhoneOrder != null) {

						orderSize = Integer.valueOf(1);

						ownPhoneOrderList = new ArrayList<OwnPhoneOrder>();

						ownPhoneOrderList.add(ownPhoneOrder);
					} else {
						orderSize = Integer.valueOf(0);
					}
				} else {
					orderSize = Integer.valueOf(0);
				}
			} else {

				if (ownPhoneOrderQuery == null) {

					ownPhoneOrderQuery = new OwnPhoneOrderQueryForm();
					ownPhoneOrderQuery.setOrdertype("modifiedtime");
					ownPhoneOrderQuery.setOrderdirection("descending");
				}

				// Query orders size
				orderSize = Integer.valueOf(ownPhoneOrderDAO
						.sizeOfOwnPhoneOrdersWithConditions(commonUseraccount,
								ownPhoneOrderQuery.getKeypad(),
								ownPhoneOrderQuery.getPhonecolor(),
								ownPhoneOrderQuery.getPhonestyle(),
								ownPhoneOrderQuery.getEmergency(),
								ownPhoneOrderQuery.getPrice(),
								ownPhoneOrderQuery.getOrdertime()));

				// Query order list
				ownPhoneOrderList = ownPhoneOrderDAO
						.findOwnPhoneOrdersWithConditions(commonUseraccount,
								starts, ends,
								ownPhoneOrderQuery.getOrdertype(),
								ownPhoneOrderQuery.getOrderdirection(),
								ownPhoneOrderQuery.getKeypad(),
								ownPhoneOrderQuery.getPhonecolor(),
								ownPhoneOrderQuery.getPhonestyle(),
								ownPhoneOrderQuery.getEmergency(),
								ownPhoneOrderQuery.getPrice(),
								ownPhoneOrderQuery.getOrdertime());
			}
		} catch (HibernateOperateException e) {
			this.addActionMessage("操作失败，请重试，或联系管理员。");
			return "findownphoneorderserror";
		}

		Integer pageSize = Integer.valueOf((int) Math.ceil((double) orderSize
				.intValue() / ITEMS));

		request.put("orderSize", orderSize);
		request.put("pageSize", pageSize);
		request.put("currentPage", Integer.valueOf(requestPage));
		request.put("ordernumberquery", ordernumberquery);
		request.put("ownPhoneOrderQuery", ownPhoneOrderQuery);
		request.put("ownPhoneOrderListToShow", ownPhoneOrderList);

		return "findcommonuserownphoneorderssuccess";
	}

	/**
	 * Find OwnPhoneOrders by login adminaccount.
	 * 
	 * @return a string which point to the result page on struts.xml
	 */
	public String showAdministratorOwnPhoneOrders() {

		if (!validateAdministratorLogin()) {
			return "validateloginfailed";
		}

		String adminaccount = ((Administrator) (session.get("loginAccount")))
				.getAdminaccount();

		final int ITEMS = 10;

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

		String ordernumberquery = null;
		List<OwnPhoneOrder> ownPhoneOrderList = null;
		Integer orderSize = null;
		try {

			// Query ordernumber
			ordernumberquery = ServletActionContext.getRequest().getParameter(
					"ordernumberquery");

			if (ordernumberquery != null && !ordernumberquery.trim().isEmpty()) {

				if (FormValidator.validateStringUsingRegex(ordernumberquery,
						"^\\d+$")) {

					OwnPhoneOrder ownPhoneOrder = ownPhoneOrderDAO
							.findOwnPhoneOrderByOrderNumber(Long
									.valueOf(ordernumberquery));
					if (ownPhoneOrder != null) {

						orderSize = Integer.valueOf(1);

						ownPhoneOrderList = new ArrayList<OwnPhoneOrder>();

						ownPhoneOrderList.add(ownPhoneOrder);
					} else {
						orderSize = Integer.valueOf(0);
					}
				} else {
					orderSize = Integer.valueOf(0);
				}
			} else {

				if (ownPhoneOrderQuery == null) {

					ownPhoneOrderQuery = new OwnPhoneOrderQueryForm();
					ownPhoneOrderQuery.setOrdertype("modifiedtime");
					ownPhoneOrderQuery.setOrderdirection("descending");
				}

				// Query orders size
				orderSize = Integer.valueOf(ownPhoneOrderDAO
						.sizeOfOwnPhoneOrdersWithConditions(adminaccount,
								ownPhoneOrderQuery.getKeypad(),
								ownPhoneOrderQuery.getPhonecolor(),
								ownPhoneOrderQuery.getPhonestyle(),
								ownPhoneOrderQuery.getEmergency(),
								ownPhoneOrderQuery.getPrice(),
								ownPhoneOrderQuery.getOrdertime()));

				// Query order list
				ownPhoneOrderList = ownPhoneOrderDAO
						.findOwnPhoneOrdersWithConditions(adminaccount, starts,
								ends, ownPhoneOrderQuery.getOrdertype(),
								ownPhoneOrderQuery.getOrderdirection(),
								ownPhoneOrderQuery.getKeypad(),
								ownPhoneOrderQuery.getPhonecolor(),
								ownPhoneOrderQuery.getPhonestyle(),
								ownPhoneOrderQuery.getEmergency(),
								ownPhoneOrderQuery.getPrice(),
								ownPhoneOrderQuery.getOrdertime());
			}
		} catch (HibernateOperateException e) {
			this.addActionMessage("操作失败，请重试，或联系管理员。");
			return "findownphoneorderserror";
		}

		Integer pageSize = Integer.valueOf((int) Math.ceil((double) orderSize
				.intValue() / ITEMS));

		request.put("orderSize", orderSize);
		request.put("pageSize", pageSize);
		request.put("currentPage", Integer.valueOf(requestPage));
		request.put("ordernumberquery", ordernumberquery);
		request.put("ownPhoneOrderQuery", ownPhoneOrderQuery);
		request.put("ownPhoneOrderListToShow", ownPhoneOrderList);

		return "findadminownphoneorderssuccess";
	}

	/**
	 * Add a OwnPhoneOrder to the database, then dispatch request to the result
	 * page.
	 * 
	 * @return a string which point to the result page on struts.xml
	 */
	public String addOwnPhoneOrder() {

		if (!validateOwnPhoneOrder(ownPhoneOrder, "ownPhoneOrder")) {
			return "validateaddingorderfailed";
		}

		CommonUser newCommonUser = null;

		if (!validateLogin()) {
			// Auto register is non-login
			try {
				newCommonUser = CommonUserAccountUtil.createRandomCommonUser();
			} catch (HibernateOperateException e) {
				this.addActionMessage("操作失败，请重试，或联系管理员。");
				return "addfeedback";
			}

			if (newCommonUser != null) {
				HttpServletRequest requestObj = ServletActionContext
						.getRequest();

				// Store the user login info
				LoginInfo loginInfo = new LoginInfo();
				loginInfo.setLoginip(requestObj.getRemoteAddr());
				loginInfo.setLoginhost(requestObj.getRemoteHost());
				loginInfo.setLogindatatime(new Date());

				// Remove the user password for security
				newCommonUser.setPassword(null);

				// loginInfo must be added in front of loginAccount, because
				// LoginSessionListener will use loginInfo first.
				session.put("loginInfo", loginInfo);
				session.put("loginAccount", newCommonUser);
			}
		}

		ownPhoneOrder.setBelongtouseraccount(((IUser) (session
				.get("loginAccount"))).fetchUseraccount());

		Long systemCurrentTimeMillis = Long.valueOf(System.currentTimeMillis());
		ownPhoneOrder.setOrdertimemillis(systemCurrentTimeMillis);
		ownPhoneOrder.setModifytimemillis(systemCurrentTimeMillis);

		OwnPhoneOrder returnOwnPhone;

		try {
			returnOwnPhone = ownPhoneOrderDAO.addOwnPhoneOrder(ownPhoneOrder);

			if (returnOwnPhone != null) {
				this.addActionMessage("订单提交成功！");
			} else {
				this.addActionMessage("订单提交失败，请重下订单！");
			}
		} catch (HibernateOperateException e) {
			this.addActionMessage("操作失败，请重试，或联系管理员。");
		}

		if (newCommonUser != null) {
			this.addActionMessage("\n");
			this.addActionMessage("系统已自动为您创建一个新用户！");
			this.addActionMessage("您的订单已自动归入该用户所属。");
			this.addActionMessage("用户名：" + newCommonUser.getUseraccount());
			this.addActionMessage("密码与用户名相同，请及时修改密码！");
		}

		return "addfeedback";
	}

	/**
	 * Modify a OwnPhoneOrder existed in database, then dispatch request to the
	 * result page.
	 * 
	 * @return a string which point to the result page on struts.xml
	 */
	public String modifyOwnPhoneOrder() {

		if (!validateLogin()) {
			return "validateloginfailed";
		}

		OwnPhoneOrder foundOwnPhoneOrder;
		try {
			foundOwnPhoneOrder = ownPhoneOrderDAO
					.findOwnPhoneOrderByOrderNumber(Long
							.valueOf(modifyingOwnPhoneOrder.getOrdernumber()));
		} catch (HibernateOperateException e) {
			this.addActionMessage("操作失败，请重新选择，或联系管理员");
			return "findownphoneordererror";
		}

		if (foundOwnPhoneOrder == null) {
			this.addActionMessage("该订单不存在，请重新选择，或联系管理员。");
			return "ownphoneordernotexisted";
		}

		foundOwnPhoneOrder.setKeypad(modifyingOwnPhoneOrder.getKeypad());
		foundOwnPhoneOrder
				.setPhonecolor(modifyingOwnPhoneOrder.getPhonecolor());
		foundOwnPhoneOrder
				.setPhonestyle(modifyingOwnPhoneOrder.getPhonestyle());
		foundOwnPhoneOrder.setName(modifyingOwnPhoneOrder.getName());
		foundOwnPhoneOrder.setEmergency(modifyingOwnPhoneOrder.getEmergency());
		foundOwnPhoneOrder.setPrice(modifyingOwnPhoneOrder.getPrice());

		foundOwnPhoneOrder.setKeyname1(modifyingOwnPhoneOrder.getKeyname1());
		foundOwnPhoneOrder
				.setKeynumber1(modifyingOwnPhoneOrder.getKeynumber1());

		foundOwnPhoneOrder.setKeyname2(modifyingOwnPhoneOrder.getKeyname2());
		foundOwnPhoneOrder
				.setKeynumber2(modifyingOwnPhoneOrder.getKeynumber2());

		foundOwnPhoneOrder.setKeyname3(modifyingOwnPhoneOrder.getKeyname3());
		foundOwnPhoneOrder
				.setKeynumber3(modifyingOwnPhoneOrder.getKeynumber3());

		foundOwnPhoneOrder.setKeyname4(modifyingOwnPhoneOrder.getKeyname4());
		foundOwnPhoneOrder
				.setKeynumber4(modifyingOwnPhoneOrder.getKeynumber4());

		foundOwnPhoneOrder.setKeyname5(modifyingOwnPhoneOrder.getKeyname5());
		foundOwnPhoneOrder
				.setKeynumber5(modifyingOwnPhoneOrder.getKeynumber5());

		foundOwnPhoneOrder.setKeyname6(modifyingOwnPhoneOrder.getKeyname6());
		foundOwnPhoneOrder
				.setKeynumber6(modifyingOwnPhoneOrder.getKeynumber6());

		foundOwnPhoneOrder.setKeyname7(modifyingOwnPhoneOrder.getKeyname7());
		foundOwnPhoneOrder
				.setKeynumber7(modifyingOwnPhoneOrder.getKeynumber7());

		foundOwnPhoneOrder.setKeyname8(modifyingOwnPhoneOrder.getKeyname8());
		foundOwnPhoneOrder
				.setKeynumber8(modifyingOwnPhoneOrder.getKeynumber8());

		foundOwnPhoneOrder.setKeyname9(modifyingOwnPhoneOrder.getKeyname9());
		foundOwnPhoneOrder
				.setKeynumber9(modifyingOwnPhoneOrder.getKeynumber9());

		foundOwnPhoneOrder.setKeyname10(modifyingOwnPhoneOrder.getKeyname10());
		foundOwnPhoneOrder.setKeynumber10(modifyingOwnPhoneOrder
				.getKeynumber10());

		foundOwnPhoneOrder.setKeyname11(modifyingOwnPhoneOrder.getKeyname11());
		foundOwnPhoneOrder.setKeynumber11(modifyingOwnPhoneOrder
				.getKeynumber11());

		foundOwnPhoneOrder.setKeyname12(modifyingOwnPhoneOrder.getKeyname12());
		foundOwnPhoneOrder.setKeynumber12(modifyingOwnPhoneOrder
				.getKeynumber12());

		request.put("ownPhoneOrderToShow", foundOwnPhoneOrder);

		if (!validateOwnPhoneOrder(modifyingOwnPhoneOrder,
				"modifyingOwnPhoneOrder")) {
			String privilege = ((IUser) session.get("loginAccount"))
					.fetchPrivilege();

			if (privilege.equals("common")) {
				return "validatemodifyingorderfailed";
			} else if (privilege.equals("admin")) {
				return "validatemodifyingorderfailed";
			} else {
				this.addActionMessage("用户权限错误，请重试，或联系管理员。");
				return "privilegeerror";
			}
		}

		Long systemCurrentTimeMillis = Long.valueOf(System.currentTimeMillis());
		foundOwnPhoneOrder.setModifytimemillis(systemCurrentTimeMillis);

		if (ownPhoneOrderDAO.updateOwnPhoneOrder(foundOwnPhoneOrder)) {
			this.addActionMessage("订单修改成功！");
		} else {
			this.addActionMessage("操作失败，请重新操作，或联系管理员");
		}

		return "modifyfeedback";
	}

	/**
	 * Validate OwnPhoneOrder form.
	 * 
	 * @param ownPhoneOrderForm
	 *            OwnPhoneOrderForm will be validated
	 * @param formBeanName
	 *            a string represent the OwnPhoneOrder form name.
	 * @return true if validation passed, otherwise false
	 */
	private boolean validateOwnPhoneOrder(OwnPhoneOrder ownPhoneOrderForm,
			String formBeanName) {

		boolean checkPassed = true;

		// Validates the keypad field
		if (!FormValidator.validateRadio(this, formBeanName, ownPhoneOrderForm,
				"keypad", false, "请选择按键数量！")) {
			checkPassed = false;
		}

		// Validates the phonecolor field
		if (!FormValidator.validateRadio(this, formBeanName, ownPhoneOrderForm,
				"phonecolor", false, "请选择手机颜色！")) {
			checkPassed = false;
		}

		// Validates the phonestyle field
		if (!FormValidator.validateRadio(this, formBeanName, ownPhoneOrderForm,
				"phonestyle", false, "请选择手机样式！")) {
			checkPassed = false;
		}

		// Validates the name field.
		if (!FormValidator.validateFieldUsingRegex(this, formBeanName,
				ownPhoneOrderForm, "name", FormValidator.CHECK_PHONENAME_REGEX,
				true, null, "手机名字只能由1到10个字，包括中文、英文、数字、下划线和它们中间的空格组成！")) {
			checkPassed = false;
		}

		// Validates the emergency field
		if (!FormValidator.validateRadio(this, formBeanName, ownPhoneOrderForm,
				"emergency", false, "请选择是否需要紧急呼叫！")) {
			checkPassed = false;
		}

		// Validates the price field
		if (!FormValidator.validateFieldUsingRegex(this, formBeanName,
				ownPhoneOrderForm, "price", FormValidator.CHECK_DIGIT_REGEX,
				false, "价格不可为空！", "价格只能由数字组成！")) {
			checkPassed = false;
		}

		// Validates the keyname and keynumber fields
		int keypad = Integer.valueOf(ownPhoneOrderForm.getKeypad()).intValue();

		for (int k = 1; k <= 12; k++) {

			String keyName = "keyname" + k;
			String keyNumber = "keynumber" + k;

			// Input fields
			if (k <= keypad) {

				// Validates the keyname field
				if (!FormValidator.validateFieldUsingRegex(this, formBeanName,
						ownPhoneOrderForm, keyName,
						FormValidator.CHECK_PHONENAME_REGEX, false, "名字不可为空！",
						"名字只能由1到10个字，包括中文、英文、数字、下划线和它们中间的空格组成！")) {
					checkPassed = false;
				}

				// Validates the keynumber field
				if (!FormValidator.validateFieldUsingRegex(this, formBeanName,
						ownPhoneOrderForm, keyNumber,
						FormValidator.CHECK_DIGIT_REGEX, false, "电话号码不可为空！",
						"电话号码只能由数字组成！")) {
					checkPassed = false;
				}

			} else { // Not valid fields
				// Get class
				Class<? extends Object> formClass = ownPhoneOrderForm
						.getClass();

				// Get private field reflect object
				Field keynameField;
				Field keynumberField;

				try {
					keynameField = formClass.getDeclaredField(keyName);
					keynumberField = formClass.getDeclaredField(keyNumber);
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
					return false;
				} catch (SecurityException e) {
					e.printStackTrace();
					return false;
				}

				// Open access
				keynameField.setAccessible(true);
				keynumberField.setAccessible(true);

				// Set to null
				try {
					keynameField.set(ownPhoneOrderForm, null);
					keynumberField.set(ownPhoneOrderForm, null);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					return false;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					return false;
				}
			}
		}

		return checkPassed;
	}

	/**
	 * Validate if user is login, including common user and administrator.
	 * 
	 * @return true if user is login, otherwise, return false
	 */
	public boolean validateLogin() {

		// Validate if user is login
		if (session.get("loginAccount") == null) {
			return false;
		}

		return true;
	}

	/**
	 * Validate if common user is login
	 * 
	 * @return true if common user is login, otherwise, return false
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

	/**
	 * Validate if administrator is login
	 * 
	 * @return true if administrator is login, otherwise, return false
	 */
	public boolean validateAdministratorLogin() {

		// Validate if administrator is login
		if (session.get("loginAccount") == null
				|| !((IUser) (session.get("loginAccount"))).fetchPrivilege()
						.equals("admin")) {
			return false;
		}

		return true;
	}

}
