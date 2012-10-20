/**
 * 
 */
package com.ownphone.content.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Bean used at LoginAction to store the login user's statistic.
 * 
 * @author Jiance Qin
 * 
 */
public class LoginInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8723956181833867505L;

	private String loginip;
	private Date logindatatime;
	private String loginhost;

	/**
	 * 
	 */
	public LoginInfo() {

	}

	/**
	 * @return the loginip
	 */
	public String getLoginip() {
		return loginip;
	}

	/**
	 * @param loginip
	 *            the loginip to set
	 */
	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}

	/**
	 * @return the logindatatime
	 */
	public Date getLogindatatime() {
		return logindatatime;
	}

	/**
	 * @param logindatatime
	 *            the logindatatime to set
	 */
	public void setLogindatatime(Date logindatatime) {
		this.logindatatime = logindatatime;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the loginhost
	 */
	public String getLoginhost() {
		return loginhost;
	}

	/**
	 * @param loginhost
	 *            the loginhost to set
	 */
	public void setLoginhost(String loginhost) {
		this.loginhost = loginhost;
	}

}
