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
import com.ownphone.content.dao.AdministratorDAO;
import com.ownphone.content.dao.HibernateOperateException;
import com.ownphone.content.po.Administrator;
import com.ownphone.content.po.IUser;
import com.ownphone.util.HibernateUtil;

/**
 * @author Jiance Qin
 * 
 */
public class AdministratorDAOImpl implements AdministratorDAO {

	private Session session = null;

	/**
	 * 
	 */
	public AdministratorDAOImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ownphone.content.dao.AdministratorDAO#loginConfirm(com.ownphone.content
	 * .bean.LoginUserForm)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public IUser loginConfirm(LoginUserForm loginUserForm)
			throws HibernateOperateException {

		String hql = "from Administrator a where a.adminaccount=:adminaccount and a.password=:password";

		List<IUser> list = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			Query query = session.createQuery(hql);
			query.setParameter("adminaccount", loginUserForm.getUseraccount());
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
	 * com.ownphone.content.dao.AdministratorDAO#updateAccountInfo(com.ownphone
	 * .content.bean.AccountInfoForm)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Administrator updateAccountInfo(AccountInfoForm accountInfo)
			throws HibernateOperateException {

		try {
			// Update Administrator according to adminaccount.
			String hql = "UPDATE Administrator SET nickname=:nickname, email=:email, mobilephone=:mobilephone, realname=:realname where adminaccount=:adminaccount";

			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameter("nickname", accountInfo.getNickname());
			query.setParameter("email", accountInfo.getEmail());
			query.setParameter("mobilephone", accountInfo.getMobilephone());
			query.setParameter("realname", accountInfo.getRealname());
			query.setParameter("adminaccount", accountInfo.getUseraccount());

			query.executeUpdate();
			session.getTransaction().commit();

			// Select the Administrator updated above for returning.
			hql = "from Administrator a where a.adminaccount=:adminaccount";
			List<Administrator> list = null;

			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			query = session.createQuery(hql);
			query.setParameter("adminaccount", accountInfo.getUseraccount());
			query.setMaxResults(1);

			list = (List<Administrator>) query.list();
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
	 * com.ownphone.content.dao.AdministratorDAO#updateAccountPassword(com.ownphone
	 * .content.bean.AccountPasswordForm)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Administrator updateAccountPassword(
			AccountPasswordForm accountPassword)
			throws HibernateOperateException {
		// Update Administrator according to adminaccount.
		String hql = "UPDATE Administrator SET password=:password where adminaccount=:adminaccount";

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameter("password", accountPassword.getNewpassword());
			query.setParameter("adminaccount", accountPassword.getUseraccount());

			query.executeUpdate();
			session.getTransaction().commit();

			// Select the Administrator updated above for returning.
			hql = "from Administrator a where a.adminaccount=:adminaccount";
			List<Administrator> list = null;

			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			query = session.createQuery(hql);
			query.setParameter("adminaccount", accountPassword.getUseraccount());
			query.setMaxResults(1);

			list = (List<Administrator>) query.list();
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
	 * com.ownphone.content.dao.AdministratorDAO#validatePasswordForAdminaccount
	 * (java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean validatePasswordForAdminaccount(String adminAccount,
			String password) throws HibernateOperateException {

		String hql = "from Administrator a where a.adminaccount=:adminaccount and a.password=:password";

		List<IUser> list = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			Query query = session.createQuery(hql);
			query.setParameter("adminaccount", adminAccount);
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
	 * com.ownphone.content.dao.AdministratorDAO#isAdminaccountExist(java.lang
	 * .String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean isAdminaccountExist(String checkingadminaccount)
			throws HibernateOperateException {

		String hql = "from Administrator a where a.adminaccount=:adminaccount";

		List<IUser> list = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameter("adminaccount", checkingadminaccount);
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
