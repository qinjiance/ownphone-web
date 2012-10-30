/**
 * 
 */
package com.ownphone.util;

import java.util.Random;

import com.ownphone.content.bean.LoginUserForm;
import com.ownphone.content.dao.AdministratorDAO;
import com.ownphone.content.dao.CommonUserDAO;
import com.ownphone.content.dao.HibernateOperateException;
import com.ownphone.content.dao.impl.AdministratorDAOImpl;
import com.ownphone.content.dao.impl.CommonUserDAOImpl;
import com.ownphone.content.po.CommonUser;

/**
 * @author Jiance Qin
 * 
 */
public class CommonUserAccountUtil {

	public static final String RANDOM_ACCOUNT_RANGE = "abcdefghijklmnopqrstuvwxyz";

	public static final int RANDOM_ACCOUNT_LENGTH = 6;

	public static final int RANDOM_PASSWORD_LENGTH = 6;

	/**
	 * 
	 */
	private CommonUserAccountUtil() {

	}

	/**
	 * Create a CommonUser in database, using random account at 10 length
	 * string, and the password is initialed to last 6 characters of account.
	 * 
	 * @return new CommonUser object if creating is successful, otherwise,
	 *         return null.
	 * 
	 * @throws HibernateOperateException
	 *             every hibernate operation exceptions will be wrapped in this
	 *             union exception.
	 */
	public static CommonUser createRandomCommonUser()
			throws HibernateOperateException {

		CommonUserDAO commonUserDAO = new CommonUserDAOImpl();
		AdministratorDAO administratorDAO = new AdministratorDAOImpl();

		String useraccount = null;
		String userpassword = null;

		do {
			useraccount = createRandomString(RANDOM_ACCOUNT_RANGE,
					RANDOM_ACCOUNT_LENGTH);

		} while (useraccount == null || useraccount.isEmpty()
				|| commonUserDAO.isUseraccountExist(useraccount)
				|| administratorDAO.isAdminaccountExist(useraccount));

		int startsAt = RANDOM_ACCOUNT_LENGTH - RANDOM_PASSWORD_LENGTH;
		userpassword = useraccount.substring(startsAt);

		CommonUser newCommonUser = new CommonUser();
		newCommonUser.setUseraccount(useraccount);
		newCommonUser.setPassword(userpassword);
		newCommonUser.setPrivilege("common");
		newCommonUser.setRegistertimemillis(Long.valueOf(System
				.currentTimeMillis()));

		boolean addResult = commonUserDAO.addNewCommonUser(newCommonUser);

		if (addResult) {
			LoginUserForm loginUserForm = new LoginUserForm();
			loginUserForm.setUseraccount(useraccount);
			loginUserForm.setPassword(userpassword);
			return (CommonUser) commonUserDAO.loginConfirm(loginUserForm);

		} else {

			return null;
		}
	}

	/**
	 * Create a random string range in range parameter and length with length
	 * parameter.
	 * 
	 * @param range
	 *            character range in the random string
	 * @param length
	 *            string length
	 * @return random string, but, if range is null or empty or length is 0,
	 *         return null.
	 */
	public static String createRandomString(String range, int length) {

		if (range == null || range.isEmpty() || length == 0) {
			return null;
		}

		Random random = new Random();
		StringBuilder randomString = new StringBuilder();

		for (int i = 0; i < length; i++) {
			randomString.append(range.charAt(random.nextInt(range.length())));
		}

		return randomString.toString();
	}

}
