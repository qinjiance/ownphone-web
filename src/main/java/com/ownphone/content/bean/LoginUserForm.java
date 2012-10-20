/**
 * 
 */
package com.ownphone.content.bean;

import java.io.Serializable;

/**
 * Form at navigator.jsp
 * 
 * @author Jiance Qin
 * 
 */
public class LoginUserForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7906898816899538338L;

	private String useraccount;
	private String password;

	/**
	 * 
	 */
	public LoginUserForm() {
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
