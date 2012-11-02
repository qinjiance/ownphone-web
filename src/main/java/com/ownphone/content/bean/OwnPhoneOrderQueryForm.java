/**
 * 
 */
package com.ownphone.content.bean;

import java.io.Serializable;

/**
 * @author Jiance Qin
 * 
 */
public class OwnPhoneOrderQueryForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7504621878750524977L;

	private String ordertype;
	private String orderdirection;
	private String keypad;
	private String phonecolor;
	private String phonestyle;
	private String emergency;
	private String price;
	private String status;
	private String ordertime;

	/**
	 * 
	 */
	public OwnPhoneOrderQueryForm() {

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
	 * @return the keypad
	 */
	public String getKeypad() {
		return keypad;
	}

	/**
	 * @param keypad
	 *            the keypad to set
	 */
	public void setKeypad(String keypad) {
		this.keypad = keypad;
	}

	/**
	 * @return the phonecolor
	 */
	public String getPhonecolor() {
		return phonecolor;
	}

	/**
	 * @param phonecolor
	 *            the phonecolor to set
	 */
	public void setPhonecolor(String phonecolor) {
		this.phonecolor = phonecolor;
	}

	/**
	 * @return the phonestyle
	 */
	public String getPhonestyle() {
		return phonestyle;
	}

	/**
	 * @param phonestyle
	 *            the phonestyle to set
	 */
	public void setPhonestyle(String phonestyle) {
		this.phonestyle = phonestyle;
	}

	/**
	 * @return the emergency
	 */
	public String getEmergency() {
		return emergency;
	}

	/**
	 * @param emergency
	 *            the emergency to set
	 */
	public void setEmergency(String emergency) {
		this.emergency = emergency;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	 * @return the ordertime
	 */
	public String getOrdertime() {
		return ordertime;
	}

	/**
	 * @param ordertime
	 *            the ordertime to set
	 */
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
