/**
 * 
 */
package com.ownphone.content.bean;

import java.io.Serializable;

/**
 * @author Jiance Qin
 * 
 */
public class UserQueryForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3451566840002851145L;

	private String ordertype;
	private String orderdirection;
	private String nickname;
	private String realname;
	private String mobilephone;
	private String email;
	private String privilege;
	private String registertime;

	/**
	 * 
	 */
	public UserQueryForm() {

	}

	/**
	 * @return the ordertype
	 */
	public String getOrdertype() {
		return ordertype;
	}

	/**
	 * @param ordertype
	 *            the ordertype to set
	 */
	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}

	/**
	 * @return the orderdirection
	 */
	public String getOrderdirection() {
		return orderdirection;
	}

	/**
	 * @param orderdirection
	 *            the orderdirection to set
	 */
	public void setOrderdirection(String orderdirection) {
		this.orderdirection = orderdirection;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname
	 *            the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the realname
	 */
	public String getRealname() {
		return realname;
	}

	/**
	 * @param realname
	 *            the realname to set
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}

	/**
	 * @return the mobilephone
	 */
	public String getMobilephone() {
		return mobilephone;
	}

	/**
	 * @param mobilephone
	 *            the mobilephone to set
	 */
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the privilege
	 */
	public String getPrivilege() {
		return privilege;
	}

	/**
	 * @param privilege
	 *            the privilege to set
	 */
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	/**
	 * @return the registertime
	 */
	public String getRegistertime() {
		return registertime;
	}

	/**
	 * @param registertime
	 *            the registertime to set
	 */
	public void setRegistertime(String registertime) {
		this.registertime = registertime;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
