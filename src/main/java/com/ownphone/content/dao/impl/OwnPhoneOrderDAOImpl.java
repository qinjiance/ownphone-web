/**
 * 
 */
package com.ownphone.content.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ownphone.content.dao.HibernateOperateException;
import com.ownphone.content.dao.OwnPhoneOrderDAO;
import com.ownphone.content.po.OwnPhoneOrder;
import com.ownphone.util.HibernateUtil;

/**
 * @author Jiance Qin
 * 
 */
public class OwnPhoneOrderDAOImpl implements OwnPhoneOrderDAO {

	private Session session = null;

	/**
	 * 
	 */
	public OwnPhoneOrderDAOImpl() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xty.content.dao.OwnPhoneOrderDAO#addOwnPhoneOrder(com.xty.content
	 * .po.OwnPhoneOrder)
	 */
	@Override
	public OwnPhoneOrder addOwnPhoneOrder(OwnPhoneOrder newOwnPhoneOrder)
			throws HibernateOperateException {
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.save(newOwnPhoneOrder);
			session.getTransaction().commit();

			return newOwnPhoneOrder;
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
	 * com.ownphone.content.dao.OwnPhoneOrderDAO#findOwnPhoneOrdersByBelongto
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OwnPhoneOrder> findOwnPhoneOrdersByBelongto(
			String belongToUserAccount) throws HibernateOperateException {

		String hql = "from OwnPhoneOrder o where o.belongtouseraccount=:belongtouseraccount order by o.modifytimemillis desc";

		List<OwnPhoneOrder> list = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameter("belongtouseraccount", belongToUserAccount);

			list = query.list(); // Do not use forcing cast, it will use
									// ArrayList<> so that order result is
									// invalid.
			session.getTransaction().commit();

			return (list == null || list.size() == 0) ? null : list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();

			throw new HibernateOperateException(e.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ownphone.content.dao.OwnPhoneOrderDAO#
	 * findStartsToEndsOwnPhoneOrdersByBelongto(java.lang.String, int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OwnPhoneOrder> findStartsToEndsOwnPhoneOrdersByBelongto(
			String belongToUseraccount, int starts, int ends)
			throws HibernateOperateException {

		int items = ends - starts + 1;

		String hql = "from OwnPhoneOrder o where o.belongtouseraccount=:belongtouseraccount order by o.modifytimemillis desc";

		List<OwnPhoneOrder> list = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameter("belongtouseraccount", belongToUseraccount);
			query.setFirstResult(starts);
			query.setMaxResults(items);

			list = query.list(); // Do not use forcing cast, it will use
									// ArrayList<> so that order result is
									// invalid.
			session.getTransaction().commit();

			return (list == null || list.size() == 0) ? null : list;
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
	 * com.ownphone.content.dao.OwnPhoneOrderDAO#findOwnPhoneOrderByOrderNumber
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public OwnPhoneOrder findOwnPhoneOrderByOrderNumber(Long requestOrderNumber)
			throws HibernateOperateException {

		String hql = "from OwnPhoneOrder o where o.ordernumber=:ordernumber";

		List<OwnPhoneOrder> list = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameter("ordernumber", requestOrderNumber);
			query.setMaxResults(1);

			list = (List<OwnPhoneOrder>) query.list();
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
	 * com.ownphone.content.dao.OwnPhoneOrderDAO#updateOwnPhoneOrder(com.ownphone
	 * .content.po.OwnPhoneOrder)
	 */
	@Override
	public boolean updateOwnPhoneOrder(OwnPhoneOrder updatingOwnPhoneOrder) {

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.update(updatingOwnPhoneOrder);
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
	 * com.ownphone.content.dao.OwnPhoneOrderDAO#sizeOfOwnPhoneOrdersByBelongto
	 * (java.lang.String)
	 */
	@Override
	public int sizeOfOwnPhoneOrdersByBelongto(String belongToUserAccount)
			throws HibernateOperateException {

		List<OwnPhoneOrder> list = findOwnPhoneOrdersByBelongto(belongToUserAccount);

		return list == null ? 0 : list.size();

	}

}
