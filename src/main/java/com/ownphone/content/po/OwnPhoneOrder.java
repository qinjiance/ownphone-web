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
	private String keyname1;
	private String keynumber1;
	private String keyname2;
	private String keynumber2;
	private String keyname3;
	private String keynumber3;
	private String keyname4;
	private String keynumber4;
	private String keyname5;
	private String keynumber5;
	private String keyname6;
	private String keynumber6;
	private String keyname7;
	private String keynumber7;
	private String keyname8;
	private String keynumber8;
	private String keyname9;
	private String keynumber9;
	private String keyname10;
	private String keynumber10;
	private String keyname11;
	private String keynumber11;
	private String keyname12;
	private String keynumber12;
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
	 * @return the keyname1
	 */
	public String getKeyname1() {
		return keyname1;
	}

	/**
	 * @param keyname1
	 *            the keyname1 to set
	 */
	public void setKeyname1(String keyname1) {
		this.keyname1 = keyname1;
	}

	/**
	 * @return the keynumber1
	 */
	public String getKeynumber1() {
		return keynumber1;
	}

	/**
	 * @param keynumber1
	 *            the keynumber1 to set
	 */
	public void setKeynumber1(String keynumber1) {
		this.keynumber1 = keynumber1;
	}

	/**
	 * @return the keyname2
	 */
	public String getKeyname2() {
		return keyname2;
	}

	/**
	 * @param keyname2
	 *            the keyname2 to set
	 */
	public void setKeyname2(String keyname2) {
		this.keyname2 = keyname2;
	}

	/**
	 * @return the keynumber2
	 */
	public String getKeynumber2() {
		return keynumber2;
	}

	/**
	 * @param keynumber2
	 *            the keynumber2 to set
	 */
	public void setKeynumber2(String keynumber2) {
		this.keynumber2 = keynumber2;
	}

	/**
	 * @return the keyname3
	 */
	public String getKeyname3() {
		return keyname3;
	}

	/**
	 * @param keyname3
	 *            the keyname3 to set
	 */
	public void setKeyname3(String keyname3) {
		this.keyname3 = keyname3;
	}

	/**
	 * @return the keynumber3
	 */
	public String getKeynumber3() {
		return keynumber3;
	}

	/**
	 * @param keynumber3
	 *            the keynumber3 to set
	 */
	public void setKeynumber3(String keynumber3) {
		this.keynumber3 = keynumber3;
	}

	/**
	 * @return the keyname4
	 */
	public String getKeyname4() {
		return keyname4;
	}

	/**
	 * @param keyname4
	 *            the keyname4 to set
	 */
	public void setKeyname4(String keyname4) {
		this.keyname4 = keyname4;
	}

	/**
	 * @return the keynumber4
	 */
	public String getKeynumber4() {
		return keynumber4;
	}

	/**
	 * @param keynumber4
	 *            the keynumber4 to set
	 */
	public void setKeynumber4(String keynumber4) {
		this.keynumber4 = keynumber4;
	}

	/**
	 * @return the keyname5
	 */
	public String getKeyname5() {
		return keyname5;
	}

	/**
	 * @param keyname5
	 *            the keyname5 to set
	 */
	public void setKeyname5(String keyname5) {
		this.keyname5 = keyname5;
	}

	/**
	 * @return the keynumber5
	 */
	public String getKeynumber5() {
		return keynumber5;
	}

	/**
	 * @param keynumber5
	 *            the keynumber5 to set
	 */
	public void setKeynumber5(String keynumber5) {
		this.keynumber5 = keynumber5;
	}

	/**
	 * @return the keyname6
	 */
	public String getKeyname6() {
		return keyname6;
	}

	/**
	 * @param keyname6
	 *            the keyname6 to set
	 */
	public void setKeyname6(String keyname6) {
		this.keyname6 = keyname6;
	}

	/**
	 * @return the keynumber6
	 */
	public String getKeynumber6() {
		return keynumber6;
	}

	/**
	 * @param keynumber6
	 *            the keynumber6 to set
	 */
	public void setKeynumber6(String keynumber6) {
		this.keynumber6 = keynumber6;
	}

	/**
	 * @return the keyname7
	 */
	public String getKeyname7() {
		return keyname7;
	}

	/**
	 * @param keyname7
	 *            the keyname7 to set
	 */
	public void setKeyname7(String keyname7) {
		this.keyname7 = keyname7;
	}

	/**
	 * @return the keynumber7
	 */
	public String getKeynumber7() {
		return keynumber7;
	}

	/**
	 * @param keynumber7
	 *            the keynumber7 to set
	 */
	public void setKeynumber7(String keynumber7) {
		this.keynumber7 = keynumber7;
	}

	/**
	 * @return the keyname8
	 */
	public String getKeyname8() {
		return keyname8;
	}

	/**
	 * @param keyname8
	 *            the keyname8 to set
	 */
	public void setKeyname8(String keyname8) {
		this.keyname8 = keyname8;
	}

	/**
	 * @return the keynumber8
	 */
	public String getKeynumber8() {
		return keynumber8;
	}

	/**
	 * @param keynumber8
	 *            the keynumber8 to set
	 */
	public void setKeynumber8(String keynumber8) {
		this.keynumber8 = keynumber8;
	}

	/**
	 * @return the keyname9
	 */
	public String getKeyname9() {
		return keyname9;
	}

	/**
	 * @param keyname9
	 *            the keyname9 to set
	 */
	public void setKeyname9(String keyname9) {
		this.keyname9 = keyname9;
	}

	/**
	 * @return the keynumber9
	 */
	public String getKeynumber9() {
		return keynumber9;
	}

	/**
	 * @param keynumber9
	 *            the keynumber9 to set
	 */
	public void setKeynumber9(String keynumber9) {
		this.keynumber9 = keynumber9;
	}

	/**
	 * @return the keyname10
	 */
	public String getKeyname10() {
		return keyname10;
	}

	/**
	 * @param keyname10
	 *            the keyname10 to set
	 */
	public void setKeyname10(String keyname10) {
		this.keyname10 = keyname10;
	}

	/**
	 * @return the keynumber10
	 */
	public String getKeynumber10() {
		return keynumber10;
	}

	/**
	 * @param keynumber10
	 *            the keynumber10 to set
	 */
	public void setKeynumber10(String keynumber10) {
		this.keynumber10 = keynumber10;
	}

	/**
	 * @return the keyname11
	 */
	public String getKeyname11() {
		return keyname11;
	}

	/**
	 * @param keyname11
	 *            the keyname11 to set
	 */
	public void setKeyname11(String keyname11) {
		this.keyname11 = keyname11;
	}

	/**
	 * @return the keynumber11
	 */
	public String getKeynumber11() {
		return keynumber11;
	}

	/**
	 * @param keynumber11
	 *            the keynumber11 to set
	 */
	public void setKeynumber11(String keynumber11) {
		this.keynumber11 = keynumber11;
	}

	/**
	 * @return the keyname12
	 */
	public String getKeyname12() {
		return keyname12;
	}

	/**
	 * @param keyname12
	 *            the keyname12 to set
	 */
	public void setKeyname12(String keyname12) {
		this.keyname12 = keyname12;
	}

	/**
	 * @return the keynumber12
	 */
	public String getKeynumber12() {
		return keynumber12;
	}

	/**
	 * @param keynumber12
	 *            the keynumber12 to set
	 */
	public void setKeynumber12(String keynumber12) {
		this.keynumber12 = keynumber12;
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
