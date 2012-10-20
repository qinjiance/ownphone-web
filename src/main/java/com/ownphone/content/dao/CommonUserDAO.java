/**
 * 
 */
package com.ownphone.content.dao;

import com.ownphone.content.bean.AccountInfoForm;
import com.ownphone.content.bean.AccountPasswordForm;
import com.ownphone.content.bean.LoginUserForm;
import com.ownphone.content.po.CommonUser;
import com.ownphone.content.po.IUser;

/**
 * @author Jiance Qin
 * 
 */
public interface CommonUserDAO {

	/**
	 * Confirm CommonUser in the database using loginUserForm submit from
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
	 * Update common user's account info except for password and privilege,
	 * using AccountInfoForm.
	 * 
	 * @param accountInfo
	 *            AccountInfoForm to provide update info.
	 * 
	 * @return the CommonUser which is updated successfully
	 * 
	 * @throws HibernateOperateException
	 *             every hibernate operation exceptions will be wrapped in this
	 *             union exception.
	 */
	public CommonUser updateAccountInfo(AccountInfoForm accountInfo)
			throws HibernateOperateException;

	/**
	 * Update common user's account password using AccountPasswordForm.
	 * 
	 * @param accountPassword
	 *            AccountPasswordForm to provide new password.
	 * 
	 * @return the CommonUser which is updated successfully
	 * @throws HibernateOperateException
	 *             every hibernate operation exceptions will be wrapped in this
	 *             union exception.
	 */
	public CommonUser updateAccountPassword(AccountPasswordForm accountPassword)
			throws HibernateOperateException;

	/**
	 * Validate the password for the given useraccount.
	 * 
	 * @param userAccount
	 *            given useraccount
	 * @param password
	 *            input password
	 * 
	 * @return true if password is correct for the useraccount, otherwise,
	 *         return false.
	 * @throws HibernateOperateException
	 *             every hibernate operation exceptions will be wrapped in this
	 *             union exception.
	 */
	public boolean validatePasswordForUseraccount(String userAccount,
			String password) throws HibernateOperateException;

	/**
	 * Add new CommonUser into database.
	 * 
	 * @param newCommonUser
	 *            new CommonUser to be added
	 * 
	 * @return true if adding is successful, otherwise, return false
	 */
	public boolean addNewCommonUser(CommonUser newCommonUser);

	/**
	 * Check whether CommonUser is existed whos useraccount's value is
	 * checkinguseraccount.
	 * 
	 * @param checkinguseraccount
	 *            useraccount field will be used to check
	 * @return true if CommonUser is existed whos useraccount's value is
	 *         checkinguseraccount,otherwise,return false.
	 * @throws HibernateOperateException
	 *             every hibernate operation exceptions will be wrapped in this
	 *             union exception.
	 */
	public boolean isUseraccountExist(String checkinguseraccount)
			throws HibernateOperateException;

}
