/**
 * 
 */
package com.ownphone.content.bean;

import java.io.Serializable;

/**
 * Form at modifyaccountinfo.jsp
 * 
 * @author Jiance Qin
 * 
 */
public class AccountInfoForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5936685889602223275L;

	private String nickname;
	private String email;
	private String mobilephone;
	private String realname;
	private String useraccount;

	/**
	 * 
	 */
	public AccountInfoForm() {

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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the useraccount
	 */
	public String getUseraccount() {
		return useraccount;
	}

	/**
	 * @param useraccount
	 *            the useraccount to set
	 */
	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}

}
