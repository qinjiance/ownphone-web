/**
 * 
 */
package com.ownphone.content.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Jiance Qin
 * 
 */

@Entity
public class OwnPhoneOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3935241202829946487L;

	private long ordernumber;
	private String belongtouseraccount;
	private String keypad;
	private String phonecolor;
	private String phonestyle;
	private String name;
	private String emergency;
	private String price;
	private String debithost;
	private String bank;
	private String branch;
	private String account;
	private Long ordertimemillis;
	private Long modifytimemillis;

	/**
	 * 
	 */
	public OwnPhoneOrder() {

	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the ordernumber
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getOrdernumber() {
		return ordernumber;
	}

	/**
	 * @param ordernumber
	 *            the ordernumber to set
	 */
	public void setOrdernumber(long ordernumber) {
		this.ordernumber = ordernumber;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the debithost
	 */
	public String getDebithost() {
		return debithost;
	}

	/**
	 * @param debithoder
	 *            the debithost to set
	 */
	public void setDebithost(String debithost) {
		this.debithost = debithost;
	}

	/**
	 * @return the bank
	 */
	public String getBank() {
		return bank;
	}

	/**
	 * @param bank
	 *            the bank to set
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}

	/**
	 * @return the branch
	 */
	public String getBranch() {
		return branch;
	}

	/**
	 * @param branch
	 *            the branch to set
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the belongtouseraccount
	 */
	public String getBelongtouseraccount() {
		return belongtouseraccount;
	}

	/**
	 * @param belongtouseraccount
	 *            the belongtouseraccount to set
	 */
	public void setBelongtouseraccount(String belongtouseraccount) {
		this.belongtouseraccount = belongtouseraccount;
	}

	/**
	 * @return the ordertimemillis
	 */
	public Long getOrdertimemillis() {
		return ordertimemillis;
	}

	/**
	 * @param ordertimemillis
	 *            the ordertimemillis to set
	 */
	public void setOrdertimemillis(Long ordertimemillis) {
		this.ordertimemillis = ordertimemillis;
	}

	/**
	 * @return the modifytimemillis
	 */
	public Long getModifytimemillis() {
		return modifytimemillis;
	}

	/**
	 * @param modifytimemillis
	 *            the modifytimemillis to set
	 */
	public void setModifytimemillis(Long modifytimemillis) {
		this.modifytimemillis = modifytimemillis;
	}

}
