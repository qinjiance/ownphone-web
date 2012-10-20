/**
 * 
 */
package com.ownphone.content.dao;

import java.util.List;

import com.ownphone.content.po.OwnPhoneOrder;

/**
 * @author Jiance Qin
 * 
 */
public interface OwnPhoneOrderDAO {

	/**
	 * Add a OwnPhone order to database.
	 * 
	 * @param newOwnPhoneOrder
	 *            a new OwnPhoneOrder will be added
	 * 
	 * @return if adding success, the entity refers to the OwnPhoneOrder which
	 *         has been added. Otherwise, will return null.
	 * @throws HibernateOperateException
	 *             every hibernate operation exceptions will be wrapped in this
	 *             union exception.
	 */
	public OwnPhoneOrder addOwnPhoneOrder(OwnPhoneOrder newOwnPhoneOrder)
			throws HibernateOperateException;

	/**
	 * Find OwnPhoneOrders by belongtouseraccount field in database.
	 * 
	 * @param belongToUserAccount
	 *            belongtouseraccount filed will be used to find.
	 * 
	 * @return the OwnPhoneOrder list whos belongtouseraccount field's value is
	 *         belongToUserAccount parameter, if no OwnPhoneOrder is belong to
	 *         this useraccount, it will return null.
	 * @throws HibernateOperateException
	 *             every hibernate operation exceptions will be wrapped in this
	 *             union exception.
	 */
	public List<OwnPhoneOrder> findOwnPhoneOrdersByBelongto(
			String belongToUserAccount) throws HibernateOperateException;

	/**
	 * Find top starts-ends OwnPhoneOrders by belongtouseraccount field in
	 * database.
	 * 
	 * @param belongToUseraccount
	 *            belongtouseraccount filed will be used to find.
	 * @param starts
	 *            starts index to be used to count.
	 * @param ends
	 *            ends index to be used to count.
	 * @return top starts-ends OwnPhoneOrders list whos belongtouseraccount
	 *         field's value is belongToUserAccount parameter, if no
	 *         OwnPhoneOrder is belong to this useraccount, it will return null.
	 * @throws HibernateOperateException
	 *             every hibernate operation exceptions will be wrapped in this
	 *             union exception.
	 */
	public List<OwnPhoneOrder> findStartsToEndsOwnPhoneOrdersByBelongto(
			String belongToUseraccount, int starts, int ends)
			throws HibernateOperateException;

	/**
	 * Find OwnPhoneOrder by ordernumber field in database.
	 * 
	 * @param requestOrderNumber
	 *            ordernumber field will be used to find.
	 * 
	 * @return the OwnPhoneOrder whos ordernumber field's value is
	 *         requestOrderNumber parameter, if no OwnPhoneOrder's ordernumber
	 *         is requestOrderNumber, it will return null.
	 * @throws HibernateOperateException
	 *             every hibernate operation exceptions will be wrapped in this
	 *             union exception.
	 */
	public OwnPhoneOrder findOwnPhoneOrderByOrderNumber(Long requestOrderNumber)
			throws HibernateOperateException;

	/**
	 * Update OwnPhoneOrder with updatingOwnPhoneOrder parameter.
	 * 
	 * @param updatingOwnPhoneOrder
	 *            OwnPhoneOrder to be used to updated
	 * @return true if updating is successful, otherwise, return false.
	 */
	public boolean updateOwnPhoneOrder(OwnPhoneOrder updatingOwnPhoneOrder);

	/**
	 * Find the size of OwnPhoneOrders belong to certain useraccount.
	 * 
	 * @param belongToUserAccount
	 *            belongtouseraccount filed will be used to find.
	 * @return the size of OwnPhoneOrders belong to certain useraccount,
	 * @throws HibernateOperateException
	 *             every hibernate operation exceptions will be wrapped in this
	 *             union exception.
	 */
	public int sizeOfOwnPhoneOrdersByBelongto(String belongToUserAccount)
			throws HibernateOperateException;
}
