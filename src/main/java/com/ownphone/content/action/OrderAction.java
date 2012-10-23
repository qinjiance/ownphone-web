/**
 * 
 */
package com.ownphone.content.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ownphone.content.bean.OwnPhoneOrderQueryForm;
import com.ownphone.content.dao.HibernateOperateException;
import com.ownphone.content.dao.OwnPhoneOrderDAO;
import com.ownphone.content.dao.impl.OwnPhoneOrderDAOImpl;
import com.ownphone.content.po.Administrator;
import com.ownphone.content.po.CommonUser;
import com.ownphone.content.po.IUser;
import com.ownphone.content.po.OwnPhoneOrder;
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
				return "findcommonuserownphoneordersuccess";
			} else if (privilege.equals("admin")) {
				return "findadminownphoneordersuccess";
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

		int requestPage = Integer.valueOf(
				ServletActionContext.getRequest().getParameter("page"))
				.intValue();

		requestPage = requestPage <= 1 ? 1 : requestPage;

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

		int requestPage = Integer.valueOf(
				ServletActionContext.getRequest().getParameter("page"))
				.intValue();

		requestPage = requestPage <= 1 ? 1 : requestPage;

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

		if (!validateLogin()) {
			return "validateloginfailed";
		}

		if (!validateOwnPhoneOrder(ownPhoneOrder, "ownPhoneOrder")) {
			return "validateaddingorderfailed";
		}

		ownPhoneOrder.setBelongtouseraccount(((IUser) (session
				.get("loginAccount"))).fetchUseraccount());

		Long systemCurrentTimeMillis = Long.valueOf(System.currentTimeMillis());
		ownPhoneOrder.setOrdertimemillis(systemCurrentTimeMillis);
		ownPhoneOrder.setModifytimemillis(systemCurrentTimeMillis);

		OwnPhoneOrder returnOwnPhone;

		try {
			returnOwnPhone = ownPhoneOrderDAO.addOwnPhoneOrder(ownPhoneOrder);
		} catch (HibernateOperateException e) {
			this.addActionMessage("操作失败，请重试，或联系管理员。");
			return "feedback";
		}

		if (returnOwnPhone != null) {
			this.addActionMessage("订单提交成功！");
		} else {
			this.addActionMessage("订单提交失败，请重下订单！");
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

		request.put("ownPhoneOrderToShow", foundOwnPhoneOrder);

		if (!validateOwnPhoneOrder(modifyingOwnPhoneOrder,
				"modifyingOwnPhoneOrder")) {
			String privilege = ((IUser) session.get("loginAccount"))
					.fetchPrivilege();

			if (privilege.equals("common")) {
				return "validatecommonusermodifyingorderfailed";
			} else if (privilege.equals("admin")) {
				return "validateadminmodifyingorderfailed";
			} else {
				this.addActionMessage("用户权限错误，请重试，或联系管理员。");
				return "privilegeerror";
			}
		}

		foundOwnPhoneOrder.setKeypad(modifyingOwnPhoneOrder.getKeypad());
		foundOwnPhoneOrder
				.setPhonecolor(modifyingOwnPhoneOrder.getPhonecolor());
		foundOwnPhoneOrder
				.setPhonestyle(modifyingOwnPhoneOrder.getPhonestyle());
		foundOwnPhoneOrder.setName(modifyingOwnPhoneOrder.getName());
		foundOwnPhoneOrder.setEmergency(modifyingOwnPhoneOrder.getEmergency());
		foundOwnPhoneOrder.setPrice(modifyingOwnPhoneOrder.getPrice());
		foundOwnPhoneOrder.setDebithost(modifyingOwnPhoneOrder.getDebithost());
		foundOwnPhoneOrder.setBank(modifyingOwnPhoneOrder.getBank());
		foundOwnPhoneOrder.setBranch(modifyingOwnPhoneOrder.getBranch());
		foundOwnPhoneOrder.setAccount(modifyingOwnPhoneOrder.getAccount());

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

		// Validates the name field.
		if (!FormValidator.validateFieldUsingRegex(this, formBeanName,
				ownPhoneOrderForm, "name", FormValidator.CHECK_REALNAME_REGEX,
				false, "姓名不可为空！", "中文姓名只能由中文组成，英文名只能由英文和它们中间的空格组成！")) {
			checkPassed = false;
		}

		// Validates the debithost field
		if (!FormValidator.validateFieldUsingRegex(this, formBeanName,
				ownPhoneOrderForm, "debithost",
				FormValidator.CHECK_REALNAME_REGEX, false, "卡主姓名不可为空！",
				"中文姓名只能由中文组成，英文名只能由英文和它们中间的空格组成！")) {
			checkPassed = false;
		}

		// Validates the bank field
		if (!FormValidator.validateFieldUsingRegex(this, formBeanName,
				ownPhoneOrderForm, "bank", FormValidator.CHECK_BANKNAME_REGEX,
				false, "银行不可为空！", "银行只能由中文、英文、数字和它们中间的空格组成！")) {
			checkPassed = false;
		}

		// Validates the branch field
		if (!FormValidator.validateFieldUsingRegex(this, formBeanName,
				ownPhoneOrderForm, "branch",
				FormValidator.CHECK_BANKNAME_REGEX, false, "分行不可为空！",
				"分行只能由中文、英文、数字和它们中间的空格组成！")) {
			checkPassed = false;
		}

		// Validates the branch field
		if (!FormValidator.validateFieldUsingRegex(this, formBeanName,
				ownPhoneOrderForm, "account",
				FormValidator.CHECK_BANKACCOUNT_REGEX, false, "银行账号不可为空！",
				"银行账号只能由数字和它们中间的空格组成！")) {
			checkPassed = false;
		}

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

		// Validates the emergency field
		if (!FormValidator.validateRadio(this, formBeanName, ownPhoneOrderForm,
				"emergency", false, "请选择是否需要紧急呼叫！")) {
			checkPassed = false;
		}

		// Validates the price field
		if (!FormValidator.validateRadio(this, formBeanName, ownPhoneOrderForm,
				"price", false, "请选择资费套餐！")) {
			checkPassed = false;
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
