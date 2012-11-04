/**
 * 
 */
package com.ownphone.content.dao;

import java.util.List;

import com.ownphone.content.bean.AccountInfoForm;
import com.ownphone.content.bean.AccountPasswordForm;
import com.ownphone.content.bean.LoginUserForm;
import com.ownphone.content.po.Administrator;
import com.ownphone.content.po.IUser;

/**
 * @author Jiance Qin
 * 
 */
public interface AdministratorDAO {

	/**
	 * Confirm Administrator in the database using loginUserForm submit from
	 * LoginAction.
	 * 
	 * @param loginUserForm
	 *            LoginUserForm to be confirmed
	 * 
	 * @return the IUser if it can be found in database, otherwise, will return
	 *         null
	 * @throws HibernateOperateException
	 *             every hibernate operation exceptions will be wrapped in this
	 *             union exception.
	 */
	public IUser loginConfirm(LoginUserForm loginUserForm)
			throws HibernateOperateException;

	/**
	 * Update administrator's account info except for password and privilege,
	 * using AccountInfoForm.
	 * 
	 * @param accountInfo
	 *            AccountInfoForm to provide update info.
	 * 
	 * @return the Administrator which is updated successfully
	 * 
	 * @throws HibernateOperateException
	 *             every hibernate operation exceptions will be wrapped in this
	 *             union exception.
	 */
	public Administrator updateAccountInfo(AccountInfoForm accountInfo)
			throws HibernateOperateException;

	/**
	 * Update administrator's account password using AccountPasswordForm.
	 * 
	 * @param accountPassword
	 *            AccountPasswordForm to provide new password.
	 * 
	 * @return the Administrator which is updated successfully
	 * @throws HibernateOperateException
	 *             every hibernate operation exceptions will be wrapped in this
	 *             union exception.
	 */
	public Administrator updateAccountPassword(
			AccountPasswordForm accountPassword)
			throws HibernateOperateException;

	/**
	 * Validate the password for the given adminaccount.
	 * 
	 * @param adminAccount
	 *            given adminaccount
	 * @param password
	 *            input password
	 * 
	 * @return true if password is correct for the adminaccount, otherwise,
	 *         return false.
	 * @throws HibernateOperateException
	 *             every hibernate operation exceptions will be wrapped in this
	 *             union exception.
	 */
	public boolean validatePasswordForAdminaccount(String adminAccount,
			String password) throws HibernateOperateException;

	/**
	 * Check whether Administrator is existed whos adminaccount's value is
	 * checkingadminaccount.
	 * 
	 * @param checkingadminaccount
	 *            adminaccount field will be used to check
	 * @return true if Administrator is existed whos adminaccount's value is
	 *         checkingadminaccount,otherwise,return false.
	 * @throws HibernateOperateException
	 *             every hibernate operation exceptions will be wrapped in this
	 *             union exception.
	 */
	public boolean isAdminaccountExist(String checkingadminaccount)
			throws HibernateOperateException;

	/**
	 * @param adminaccount
	 *            adminaccount field will be used to find
	 * @return Administrator with adminaccount matched. Otherwise, return null.
	 * @throws HibernateOperateException
	 *             every hibernate operation exceptions will be wrapped in this
	 *             union exception.
	 */
	public Administrator findAdministratorByAdminaccount(String adminaccount)
			throws HibernateOperateException;
	
	/**
	 * Find Administrators with conditions, including top starts-ends, ordertype,
	 * orderdirectory, nickname, realname, mobilephone, email,
	 * privilege,registertime.
	 * 
	 * @param nickname
	 *            nickname field condition.
	 * @param realname
	 *            realname field condition.
	 * @param mobilephone
	 *            mobilephone field condition.
	 * @param email
	 *            email field condition.
	 * @param privilege
	 *            privilege field condition.
	 * @param registertime
	 *            registertime field condition.
	 * @return Administrators list fulfilled the conditions parameter, if no
	 *         Administrator fulfilled the conditions, it will return null.
	 * @throws HibernateOperateException
	 *             every hibernate operation exceptions will be wrapped in this
	 *             union exception.
	 */
	public List<Administrator> findAdministratorsWithConditions(int starts, int ends,
			String ordertype, String orderdirection, String nickname,
			String realname, String mobilephone, String email,
			String privilege, String registertime)
			throws HibernateOperateException;

	/**
	 * Find the size of Administrators with conditions, including nickname,
	 * realname, mobilephone, email, privilege,registertime.
	 * 
	 * @param nickname
	 *            nickname field condition.
	 * @param realname
	 *            realname field condition.
	 * @param mobilephone
	 *            mobilephone field condition.
	 * @param email
	 *            email field condition.
	 * @param privilege
	 *            privilege field condition.
	 * @param registertime
	 *            registertime field condition.
	 * @return the size of Administrators fulfilled the condition parameters.
	 * @throws HibernateOperateException
	 *             every hibernate operation exceptions will be wrapped in this
	 *             union exception.
	 */
	public int sizeOfAdministratorsWithConditions(String nickname,
			String realname, String mobilephone, String email,
			String privilege, String registertime)
			throws HibernateOperateException;
}
