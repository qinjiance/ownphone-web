/**
 * 
 */
package com.ownphone.content.bean;

import java.io.Serializable;

/**
 * @author Jiance Qin
 * 
 */
public class CommonUserRegisterForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4910972896517451227L;

	private String useraccount;
	private String password;
	private String repeatpassword;
	private String nickname;
	private String email;
	private String mobilephone;
	private String realname;

	/**
	 * 
	 */
	public CommonUserRegisterForm() {

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

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the repeatpassword
	 */
	public String getRepeatpassword() {
		return repeatpassword;
	}

	/**
	 * @param repeatpassword
	 *            the repeatpassword to set
	 */
	public void setRepeatpassword(String repeatpassword) {
		this.repeatpassword = repeatpassword;
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

}
