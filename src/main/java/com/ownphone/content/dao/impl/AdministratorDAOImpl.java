/**
 * 
 */
package com.ownphone.content.dao.impl;

import java.util.Calendar;
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

			throw new HibernateOperateException(e.getMessage(), e);
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
			throw new HibernateOperateException(e.getMessage(), e);
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
			throw new HibernateOperateException(e.getMessage(), e);
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

		List<Administrator> list = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			Query query = session.createQuery(hql);
			query.setParameter("adminaccount", adminAccount);
			query.setParameter("password", password);
			query.setMaxResults(1);

			list = (List<Administrator>) query.list();
			session.getTransaction().commit();

			return (list == null || list.size() == 0) ? false : true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();

			throw new HibernateOperateException(e.getMessage(), e);
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

		List<Administrator> list = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameter("adminaccount", checkingadminaccount);
			query.setMaxResults(1);

			list = (List<Administrator>) query.list();
			session.getTransaction().commit();

			return (list == null || list.size() == 0) ? false : true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();

			throw new HibernateOperateException(e.getMessage(), e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ownphone.content.dao.AdministratorDAO#findAdministratorByAdminaccount
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Administrator findAdministratorByAdminaccount(String adminaccount)
			throws HibernateOperateException {

		String hql = "from Administrator a where a.adminaccount=:adminaccount";

		List<Administrator> list = null;

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameter("adminaccount", adminaccount);
			query.setMaxResults(1);

			list = (List<Administrator>) query.list();
			session.getTransaction().commit();

			return (list == null || list.size() == 0) ? null : list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();

			throw new HibernateOperateException(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ownphone.content.dao.AdministratorDAO#findAdministratorsWithConditions
	 * (int, int, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Administrator> findAdministratorsWithConditions(int starts,
			int ends, String ordertype, String orderdirection, String nickname,
			String realname, String mobilephone, String email,
			String privilege, String registertime)
			throws HibernateOperateException {

		int items = ends - starts + 1;

		StringBuilder hql = new StringBuilder("from Administrator a");

		StringBuilder conditions = new StringBuilder();

		if (nickname != null && !nickname.isEmpty()) {
			conditions.append(" and a.nickname=:nickname");
		}

		if (realname != null && !realname.isEmpty()) {
			conditions.append(" and a.realname=:realname");
		}

		if (mobilephone != null && !mobilephone.isEmpty()) {
			conditions.append(" and a.mobilephone=:mobilephone");
		}

		if (email != null && !email.isEmpty()) {
			conditions.append(" and a.email=:email");
		}

		if (privilege != null && !privilege.isEmpty()) {
			conditions.append(" and a.privilege=:privilege");
		}

		if (registertime != null && !registertime.isEmpty()) {
			if (registertime.equals("latestthreemonthes")) {
				conditions.append(" and a.registertimemillis>=:starttime");
			} else if (registertime.equals("threemonthesago")) {
				conditions.append(" and a.registertimemillis<:endtime");
			}
		}

		if (conditions.length() != 0) {
			hql.append(" where").append(conditions.substring(4));
		}

		if (ordertype == null || ordertype.isEmpty()) {
			hql.append(" order by a.adminaccount");
		} else if (ordertype.equals("account")) {
			hql.append(" order by a.adminaccount");
		} else if (ordertype.equals("registertime")) {
			hql.append(" order by a.registertimemillis");
		} else {
			hql.append(" order by a.adminaccount");
		}

		if (orderdirection == null || orderdirection.isEmpty()) {
			hql.append(" desc");
		} else if (orderdirection.equals("descending")) {
			hql.append(" desc");
		} else if (orderdirection.equals("increasing")) {
			;
		} else {
			;
		}

		List<Administrator> list = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(hql.toString());
			query.setFirstResult(starts);
			query.setMaxResults(items);

			if (nickname != null && !nickname.isEmpty()) {
				query.setParameter("nickname", nickname);
			}

			if (realname != null && !realname.isEmpty()) {
				query.setParameter("realname", realname);
			}

			if (mobilephone != null && !mobilephone.isEmpty()) {
				query.setParameter("mobilephone", mobilephone);
			}

			if (email != null && !email.isEmpty()) {
				query.setParameter("email", email);
			}

			if (privilege != null && !privilege.isEmpty()) {
				query.setParameter("privilege", privilege);
			}

			if (registertime != null && !registertime.isEmpty()) {

				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(System.currentTimeMillis());
				calendar.add(Calendar.MONTH, -3);
				Long time = Long.valueOf(calendar.getTimeInMillis());

				if (registertime.equals("latestthreemonthes")) {
					query.setParameter("starttime", time);
				} else if (registertime.equals("threemonthesago")) {
					query.setParameter("endtime", time);
				}
			}

			list = query.list(); // Do not use forcing cast, it will use
									// ArrayList<> so that order result is
									// invalid.

			session.getTransaction().commit();

			return (list == null || list.size() == 0) ? null : list;
		} catch (Exception e) {

			e.printStackTrace();
			session.getTransaction().rollback();

			throw new HibernateOperateException(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ownphone.content.dao.AdministratorDAO#sizeOfAdministratorsWithConditions
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int sizeOfAdministratorsWithConditions(String nickname,
			String realname, String mobilephone, String email,
			String privilege, String registertime)
			throws HibernateOperateException {

		StringBuilder hql = new StringBuilder("from Administrator a");

		StringBuilder conditions = new StringBuilder();

		if (nickname != null && !nickname.isEmpty()) {
			conditions.append(" and a.nickname=:nickname");
		}

		if (realname != null && !realname.isEmpty()) {
			conditions.append(" and a.realname=:realname");
		}

		if (mobilephone != null && !mobilephone.isEmpty()) {
			conditions.append(" and a.mobilephone=:mobilephone");
		}

		if (email != null && !email.isEmpty()) {
			conditions.append(" and a.email=:email");
		}

		if (privilege != null && !privilege.isEmpty()) {
			conditions.append(" and a.privilege=:privilege");
		}

		if (registertime != null && !registertime.isEmpty()) {
			if (registertime.equals("latestthreemonthes")) {
				conditions.append(" and a.registertimemillis>=:starttime");
			} else if (registertime.equals("threemonthesago")) {
				conditions.append(" and a.registertimemillis<:endtime");
			}
		}

		if (conditions.length() != 0) {
			hql.append(" where").append(conditions.substring(4));
		}

		List<Administrator> list = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(hql.toString());

			if (nickname != null && !nickname.isEmpty()) {
				query.setParameter("nickname", nickname);
			}

			if (realname != null && !realname.isEmpty()) {
				query.setParameter("realname", realname);
			}

			if (mobilephone != null && !mobilephone.isEmpty()) {
				query.setParameter("mobilephone", mobilephone);
			}

			if (email != null && !email.isEmpty()) {
				query.setParameter("email", email);
			}

			if (privilege != null && !privilege.isEmpty()) {
				query.setParameter("privilege", privilege);
			}

			if (registertime != null && !registertime.isEmpty()) {

				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(System.currentTimeMillis());
				calendar.add(Calendar.MONTH, -3);
				Long time = Long.valueOf(calendar.getTimeInMillis());

				if (registertime.equals("latestthreemonthes")) {
					query.setParameter("starttime", time);
				} else if (registertime.equals("threemonthesago")) {
					query.setParameter("endtime", time);
				}
			}

			list = (List<Administrator>) query.list();

			session.getTransaction().commit();

			return list == null ? 0 : list.size();
		} catch (Exception e) {

			e.printStackTrace();
			session.getTransaction().rollback();

			throw new HibernateOperateException(e.getMessage(), e);
		}
	}

}
