/**
 * 
 */
package com.ownphone.content.po;

/**
 * @author Jiance Qin
 * 
 */
public interface IUser {

	/**
	 * Fetch the user's privilege to distinct different user instance.
	 * 
	 * @return a string represents user's privilege
	 */
	public String fetchPrivilege();

	/**
	 * Fetch the user's useraccount.
	 * 
	 * @return a string represents user's useraccount
	 */
	public String fetchUseraccount();

	/**
	 * Fetch the user's nickname.
	 * 
	 * @return a string represents user's nickname
	 */
	public String fetchNickname();

	/**
	 * Fetch the user's realname.
	 * 
	 * @return a string represents user's realname
	 */
	public String fetchRealname();

	/**
	 * Fetch the user's mobilephone.
	 * 
	 * @return a string represents user's mobilephone
	 */
	public String fetchMobilephone();

	/**
	 * Fetch the user's email.
	 * 
	 * @return a string represents user's email
	 */
	public String fetchEmail();

	/**
	 * Fetch the user's registertimemillis.
	 * 
	 * @return a long value represents user's registertimemillis
	 */
	public long fetchRegisterTimeMillis();

}
