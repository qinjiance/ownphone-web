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
public class CommonUser implements IUser, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1353729455634827565L;

	private int id;
	private String useraccount;
	private String password;
	private String nickname;
	private String email;
	private String mobilephone;
	private String realname;
	private String privilege;
	private Long registertimemillis;

	/**
	 * 
	 */
	public CommonUser() {
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the registertimemillis
	 */
	public Long getRegistertimemillis() {
		return registertimemillis;
	}

	/**
	 * @param registertimemillis
	 *            the registertimemillis to set
	 */
	public void setRegistertimemillis(Long registertimemillis) {
		this.registertimemillis = registertimemillis;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ownphone.content.po.IUser#fetchPrivilege()
	 */
	@Override
	public String fetchPrivilege() {
		return this.getPrivilege();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ownphone.content.po.IUser#fetchUseraccount()
	 */
	@Override
	public String fetchUseraccount() {
		return this.getUseraccount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ownphone.content.po.IUser#fetchRegisterTimeMillis()
	 */
	@Override
	public long fetchRegisterTimeMillis() {
		return this.getRegistertimemillis().longValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ownphone.content.po.IUser#fetchNickname()
	 */
	@Override
	public String fetchNickname() {
		return this.getNickname();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ownphone.content.po.IUser#fetchRealname()
	 */
	@Override
	public String fetchRealname() {
		return this.getRealname();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ownphone.content.po.IUser#fetchMobilephone()
	 */
	@Override
	public String fetchMobilephone() {
		return this.getMobilephone();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ownphone.content.po.IUser#fetchEmail()
	 */
	@Override
	public String fetchEmail() {
		return this.getEmail();
	}

}
