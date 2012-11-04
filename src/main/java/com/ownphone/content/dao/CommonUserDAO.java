/**
 * 
 */
package com.ownphone.content.dao;

import java.util.List;

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

	/**
	 * @param useraccount
	 *            useraccount field will be used to find
	 * @return CommonUser with useraccount matched. Otherwise, return null.
	 * @throws HibernateOperateException
	 *             every hibernate operation exceptions will be wrapped in this
	 *             union exception.
	 */
	public CommonUser findCommonUserByUseraccount(String useraccount)
			throws HibernateOperateException;

	/**
	 * Find CommonUsers with conditions, including top starts-ends, ordertype,
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
	 * @return CommonUsers list fulfilled the conditions parameter, if no
	 *         CommonUser fulfilled the conditions, it will return null.
	 * @throws HibernateOperateException
	 *             every hibernate operation exceptions will be wrapped in this
	 *             union exception.
	 */
	public List<CommonUser> findCommonUsersWithConditions(int starts, int ends,
			String ordertype, String orderdirection, String nickname,
			String realname, String mobilephone, String email,
			String privilege, String registertime)
			throws HibernateOperateException;

	/**
	 * Find the size of CommonUsers with conditions, including nickname,
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
	 * @return the size of CommonUsers fulfilled the condition parameters.
	 * @throws HibernateOperateException
	 *             every hibernate operation exceptions will be wrapped in this
	 *             union exception.
	 */
	public int sizeOfCommonUsersWithConditions(String nickname,
			String realname, String mobilephone, String email,
			String privilege, String registertime)
			throws HibernateOperateException;
}
