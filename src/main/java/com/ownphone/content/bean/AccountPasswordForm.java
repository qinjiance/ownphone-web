/**
 * 
 */
package com.ownphone.content.bean;

import java.io.Serializable;

/**
 * @author Jiance Qin
 * 
 */
public class AccountPasswordForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 37572700729167121L;

	private String oldpassword;
	private String newpassword;
	private String repeatnewpassword;
	private String useraccount;

	/**
	 * 
	 */
	public AccountPasswordForm() {

	}

	/**
	 * @return the oldpassword
	 */
	public String getOldpassword() {
		return oldpassword;
	}

	/**
	 * @param oldpassword
	 *            the oldpassword to set
	 */
	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	/**
	 * @return the newpassword
	 */
	public String getNewpassword() {
		return newpassword;
	}

	/**
	 * @param newpassword
	 *            the newpassword to set
	 */
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	/**
	 * @return the repeatnewpassword
	 */
	public String getRepeatnewpassword() {
		return repeatnewpassword;
	}

	/**
	 * @param repeatnewpassword
	 *            the repeatnewpassword to set
	 */
	public void setRepeatnewpassword(String repeatnewpassword) {
		this.repeatnewpassword = repeatnewpassword;
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
