/**
 * 
 */
package com.ownphone.content.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ownphone.content.bean.AccountInfoForm;
import com.ownphone.content.bean.AccountPasswordForm;
import com.ownphone.content.bean.LoginUserForm;
import com.ownphone.content.dao.CommonUserDAO;
import com.ownphone.content.dao.HibernateOperateException;
import com.ownphone.content.po.CommonUser;
import com.ownphone.content.po.IUser;
import com.ownphone.util.HibernateUtil;

/**
 * @author Jiance Qin
 * 
 */
public class CommonUserDAOImpl implements CommonUserDAO {

	private Session session = null;

	/**
	 * 
	 */
	public CommonUserDAOImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xty.content.dao.CommonUserDAO#loginConfirm(com.xty.content.bean.
	 * LoginUserForm)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public IUser loginConfirm(LoginUserForm loginUserForm)
			throws HibernateOperateException {

		String hql = "from CommonUser u where u.useraccount=:useraccount and u.password=:password";

		List<IUser> list = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			Query query = session.createQuery(hql);
			query.setParameter("useraccount", loginUserForm.getUseraccount());
			query.setParameter("password", loginUserForm.getPassword());
			query.setMaxResults(1);

			list = (List<IUser>) query.list();
			session.getTransaction().commit();

			return (list == null || list.size() == 0) ? null : list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();

			throw new HibernateOperateException(e.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ownphone.content.dao.CommonUserDAO#updateAccountInfo(com.ownphone
	 * .content.bean.AccountInfoForm)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public CommonUser updateAccountInfo(AccountInfoForm accountInfo)
			throws HibernateOperateException {

		try {
			// Update CommonUser according to useraccount.
			String hql = "UPDATE CommonUser SET nickname=:nickname, email=:email, mobilephone=:mobilephone, realname=:realname where useraccount=:useraccount";

			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameter("nickname", accountInfo.getNickname());
			query.setParameter("email", accountInfo.getEmail());
			query.setParameter("mobilephone", accountInfo.getMobilephone());
			query.setParameter("realname", accountInfo.getRealname());
			query.setParameter("useraccount", accountInfo.getUseraccount());

			query.executeUpdate();
			session.getTransaction().commit();

			// Select the CommonUser updated above for returning.
			hql = "from CommonUser u where u.useraccount=:useraccount";
			List<CommonUser> list = null;

			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			query = session.createQuery(hql);
			query.setParameter("useraccount", accountInfo.getUseraccount());
			query.setMaxResults(1);

			list = (List<CommonUser>) query.list();
			session.getTransaction().commit();

			return (list == null || list.size() == 0) ? null : list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			throw new HibernateOperateException(e.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ownphone.content.dao.CommonUserDAO#updateAccountPassword(com.ownphone
	 * .content.bean.AccountPasswordForm)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public CommonUser updateAccountPassword(AccountPasswordForm accountPassword)
			throws HibernateOperateException {
		// Update CommonUser according to useraccount.
		String hql = "UPDATE CommonUser SET password=:password where useraccount=:useraccount";

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameter("password", accountPassword.getNewpassword());
			query.setParameter("useraccount", accountPassword.getUseraccount());

			query.executeUpdate();
			session.getTransaction().commit();

			// Select the CommonUser updated above for returning.
			hql = "from CommonUser u where u.useraccount=:useraccount";
			List<CommonUser> list = null;

			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			query = session.createQuery(hql);
			query.setParameter("useraccount", accountPassword.getUseraccount());
			query.setMaxResults(1);

			list = (List<CommonUser>) query.list();
			session.getTransaction().commit();

			return (list == null || list.size() == 0) ? null : list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			throw new HibernateOperateException(e.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ownphone.content.dao.CommonUserDAO#validatePasswordForUseraccount
	 * (java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean validatePasswordForUseraccount(String userAccount,
			String password) throws HibernateOperateException {

		String hql = "from CommonUser u where u.useraccount=:useraccount and u.password=:password";

		List<IUser> list = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			Query query = session.createQuery(hql);
			query.setParameter("useraccount", userAccount);
			query.setParameter("password", password);
			query.setMaxResults(1);

			list = (List<IUser>) query.list();
			session.getTransaction().commit();

			return (list == null || list.size() == 0) ? false : true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();

			throw new HibernateOperateException(e.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ownphone.content.dao.CommonUserDAO#addNewCommonUser(com.ownphone.
	 * content.po.CommonUser)
	 */
	@Override
	public boolean addNewCommonUser(CommonUser newCommonUser) {

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.save(newCommonUser);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ownphone.content.dao.CommonUserDAO#isUseraccountExist(java.lang.String
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean isUseraccountExist(String checkinguseraccount)
			throws HibernateOperateException {

		String hql = "from CommonUser o where o.useraccount=:useraccount";

		List<IUser> list = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameter("useraccount", checkinguseraccount);
			query.setMaxResults(1);

			list = (List<IUser>) query.list();
			session.getTransaction().commit();

			return (list == null || list.size() == 0) ? false : true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();

			throw new HibernateOperateException(e.getMessage());
		}

	}

}
