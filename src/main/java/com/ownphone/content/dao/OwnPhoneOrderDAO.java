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
	 *            belongtouseraccount field will be used to find.
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
	 * database, then order them by modified time.
	 * 
	 * @param belongToUseraccount
	 *            belongtouseraccount field will be used to find.
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
	public List<OwnPhoneOrder> findOwnPhoneOrdersOrderByModifiedTime(
			String belongToUseraccount, int starts, int ends)
			throws HibernateOperateException;

	/**
	 * Find OwnPhoneOrders by belongToUseraccount with conditions, including top
	 * starts-ends, ordertype, orderdirectory, keypad, phonecolor, phonestyle,
	 * emergency, price.
	 * 
	 * @param belongToUseraccount
	 *            belongtouseraccount field will be used to find.
	 * @param starts
	 *            starts index to be used to count.
	 * @param ends
	 *            ends index to be used to count.
	 * @param ordertype
	 *            order type condition.
	 * @param orderdirection
	 *            order direction condition.
	 * @param keypad
	 *            keypad field condition.
	 * @param phonecolor
	 *            phonecolor field condition.
	 * @param phonestyle
	 *            phonestyle field condition.
	 * @param emergency
	 *            emergency field condition.
	 * @param price
	 *            price field condition.
	 * @param ordertime
	 *            ordertime field condition.
	 * @return OwnPhoneOrders list whos belongtouseraccount field's value is
	 *         belongToUserAccount parameter and with the conditions parameter,
	 *         if no OwnPhoneOrder is belong to this useraccount and fulfilled
	 *         the conditions, it will return null.
	 * @throws HibernateOperateException
	 *             every hibernate operation exceptions will be wrapped in this
	 *             union exception.
	 */
	public List<OwnPhoneOrder> findOwnPhoneOrdersWithConditions(
			String belongToUseraccount, int starts, int ends, String ordertype,
			String orderdirection, String keypad, String phonecolor,
			String phonestyle, String emergency, String price, String ordertime)
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
	 *            belongtouseraccount field will be used to find.
	 * @return the size of OwnPhoneOrders belong to certain useraccount,
	 * @throws HibernateOperateException
	 *             every hibernate operation exceptions will be wrapped in this
	 *             union exception.
	 */
	public int sizeOfOwnPhoneOrdersByBelongto(String belongToUserAccount)
			throws HibernateOperateException;

	/**
	 * Find the size of OwnPhoneOrders by belongToUseraccount with conditions,
	 * including keypad, phonecolor, phonestyle, emergency, price.
	 * 
	 * @param belongToUseraccount
	 *            belongtouseraccount field will be used to find.
	 * @param keypad
	 *            keypad field condition.
	 * @param phonecolor
	 *            phonecolor field condition.
	 * @param phonestyle
	 *            phonestyle field condition.
	 * @param emergency
	 *            emergency field condition.
	 * @param price
	 *            price field condition.
	 * @param ordertime
	 *            ordertime field condition.
	 * @return the size of OwnPhoneOrders whos belongtouseraccount field value
	 *         is belongToUserAccount parameter and fulfilled the conditions
	 *         parameter.
	 * @throws HibernateOperateException
	 *             every hibernate operation exceptions will be wrapped in this
	 *             union exception.
	 */
	public int sizeOfOwnPhoneOrdersWithConditions(String belongToUseraccount,
			String keypad, String phonecolor, String phonestyle,
			String emergency, String price, String ordertime)
			throws HibernateOperateException;
}
