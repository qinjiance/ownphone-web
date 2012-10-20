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

}
