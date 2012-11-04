/**
 * 
 */
package com.ownphone.content.dao.impl;

import java.util.Calendar;
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
			throw new HibernateOperateException(e.getMessage(), e);
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

			throw new HibernateOperateException(e.getMessage(), e);
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
	public List<OwnPhoneOrder> findOwnPhoneOrdersOrderByModifiedTime(
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

			throw new HibernateOperateException(e.getMessage(), e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ownphone.content.dao.OwnPhoneOrderDAO#findOwnPhoneOrdersWithConditions
	 * (java.lang.String, int, int, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OwnPhoneOrder> findOwnPhoneOrdersWithConditions(
			String belongToUseraccount, int starts, int ends, String ordertype,
			String orderdirection, String keypad, String phonecolor,
			String phonestyle, String emergency, String price, String status,
			String ordertime) throws HibernateOperateException {

		int items = ends - starts + 1;

		StringBuilder hql = new StringBuilder("from OwnPhoneOrder o");

		StringBuilder conditions = new StringBuilder();

		if (keypad != null && !keypad.isEmpty()) {
			conditions.append(" and o.keypad=:keypad");
		}

		if (phonecolor != null && !phonecolor.isEmpty()) {
			conditions.append(" and o.phonecolor=:phonecolor");
		}

		if (phonestyle != null && !phonestyle.isEmpty()) {
			conditions.append(" and o.phonestyle=:phonestyle");
		}

		if (emergency != null && !emergency.isEmpty()) {
			conditions.append(" and o.emergency=:emergency");
		}

		if (price != null && !price.isEmpty()) {
			conditions.append(" and o.price=:price");
		}

		if (status != null && !status.isEmpty()) {
			conditions.append(" and o.status=:status");
		}

		if (ordertime != null && !ordertime.isEmpty()) {
			if (ordertime.equals("latestthreemonthes")) {
				conditions.append(" and o.ordertimemillis>=:starttime");
			} else if (ordertime.equals("threemonthesago")) {
				conditions.append(" and o.ordertimemillis<:endtime");
			}
		}

		if (belongToUseraccount == null || belongToUseraccount.isEmpty()) {

			if (conditions.length() != 0) {
				hql.append(" where").append(conditions.substring(4));
			}
		} else {
			hql.append(" where o.belongtouseraccount=:belongtouseraccount")
					.append(conditions.toString());
		}

		if (ordertype == null || ordertype.isEmpty()) {
			hql.append(" order by o.modifytimemillis");
		} else if (ordertype.equals("modifiedtime")) {
			hql.append(" order by o.modifytimemillis");
		} else if (ordertype.equals("orderedtime")) {
			hql.append(" order by o.ordertimemillis");
		} else {
			hql.append(" order by o.modifytimemillis");
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

		List<OwnPhoneOrder> list = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(hql.toString());
			query.setFirstResult(starts);
			query.setMaxResults(items);

			if (belongToUseraccount != null && !belongToUseraccount.isEmpty()) {
				query.setParameter("belongtouseraccount", belongToUseraccount);
			}

			if (keypad != null && !keypad.isEmpty()) {
				query.setParameter("keypad", keypad);
			}

			if (phonecolor != null && !phonecolor.isEmpty()) {
				query.setParameter("phonecolor", phonecolor);
			}

			if (phonestyle != null && !phonestyle.isEmpty()) {
				query.setParameter("phonestyle", phonestyle);
			}

			if (emergency != null && !emergency.isEmpty()) {
				query.setParameter("emergency", emergency);
			}

			if (price != null && !price.isEmpty()) {
				query.setParameter("price", price);
			}

			if (status != null && !status.isEmpty()) {
				query.setParameter("status", status);
			}

			if (ordertime != null && !ordertime.isEmpty()) {

				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(System.currentTimeMillis());
				calendar.add(Calendar.MONTH, -3);
				Long time = Long.valueOf(calendar.getTimeInMillis());

				if (ordertime.equals("latestthreemonthes")) {
					query.setParameter("starttime", time);
				} else if (ordertime.equals("threemonthesago")) {
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

			throw new HibernateOperateException(e.getMessage(), e);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ownphone.content.dao.OwnPhoneOrderDAO#sizeOfOwnPhoneOrdersWithConditions
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int sizeOfOwnPhoneOrdersWithConditions(String belongToUseraccount,
			String keypad, String phonecolor, String phonestyle,
			String emergency, String price, String status, String ordertime)
			throws HibernateOperateException {

		StringBuilder hql = new StringBuilder("from OwnPhoneOrder o");

		StringBuilder conditions = new StringBuilder();

		if (keypad != null && !keypad.isEmpty()) {
			conditions.append(" and o.keypad=:keypad");
		}

		if (phonecolor != null && !phonecolor.isEmpty()) {
			conditions.append(" and o.phonecolor=:phonecolor");
		}

		if (phonestyle != null && !phonestyle.isEmpty()) {
			conditions.append(" and o.phonestyle=:phonestyle");
		}

		if (emergency != null && !emergency.isEmpty()) {
			conditions.append(" and o.emergency=:emergency");
		}

		if (price != null && !price.isEmpty()) {
			conditions.append(" and o.price=:price");
		}

		if (status != null && !status.isEmpty()) {
			conditions.append(" and o.status=:status");
		}

		if (ordertime != null && !ordertime.isEmpty()) {
			if (ordertime.equals("latestthreemonthes")) {
				conditions.append(" and o.ordertimemillis>=:starttime");
			} else if (ordertime.equals("threemonthesago")) {
				conditions.append(" and o.ordertimemillis<:endtime");
			}
		}

		if (belongToUseraccount == null || belongToUseraccount.isEmpty()) {

			if (conditions.length() != 0) {
				hql.append(" where").append(conditions.substring(4));
			}
		} else {
			hql.append(" where o.belongtouseraccount=:belongtouseraccount")
					.append(conditions.toString());
		}

		List<OwnPhoneOrder> list = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(hql.toString());

			if (belongToUseraccount != null && !belongToUseraccount.isEmpty()) {
				query.setParameter("belongtouseraccount", belongToUseraccount);
			}

			if (keypad != null && !keypad.isEmpty()) {
				query.setParameter("keypad", keypad);
			}

			if (phonecolor != null && !phonecolor.isEmpty()) {
				query.setParameter("phonecolor", phonecolor);
			}

			if (phonestyle != null && !phonestyle.isEmpty()) {
				query.setParameter("phonestyle", phonestyle);
			}

			if (emergency != null && !emergency.isEmpty()) {
				query.setParameter("emergency", emergency);
			}

			if (price != null && !price.isEmpty()) {
				query.setParameter("price", price);
			}

			if (status != null && !status.isEmpty()) {
				query.setParameter("status", status);
			}

			if (ordertime != null && !ordertime.isEmpty()) {

				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(System.currentTimeMillis());
				calendar.add(Calendar.MONTH, -3);
				Long time = Long.valueOf(calendar.getTimeInMillis());

				if (ordertime.equals("latestthreemonthes")) {
					query.setParameter("starttime", time);
				} else if (ordertime.equals("threemonthesago")) {
					query.setParameter("endtime", time);
				}
			}

			list = (List<OwnPhoneOrder>) query.list();

			session.getTransaction().commit();

			return list == null ? 0 : list.size();
		} catch (Exception e) {

			e.printStackTrace();
			session.getTransaction().rollback();

			throw new HibernateOperateException(e.getMessage(), e);
		}

	}

}
