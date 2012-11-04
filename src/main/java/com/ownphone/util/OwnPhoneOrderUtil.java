/**
 * 
 */
package com.ownphone.util;

/**
 * @author Jiance Qin
 * 
 */
public class OwnPhoneOrderUtil {

	/**
	 * Order status represents a order is submitted but have not pay.
	 */
	public static final String ORDER_STATUS_NOPAY = "未付款";

	/**
	 * Order status represents a order is payed but have not deliver.
	 */
	public static final String ORDER_STATUS_PAYED = "已付款";

	/**
	 * Order status represents a order is delivering.
	 */
	public static final String ORDER_STATUS_DELIVER = "出货中";

	/**
	 * Order status represents a order is delivered but consumer have not
	 * receive.
	 */
	public static final String ORDER_STATUS_WAITRECEIVE = "待客户签收";

	/**
	 * Order status represents a order is received by consumer.
	 */
	public static final String ORDER_STATUS_DONE = "交易完成";

	/**
	 * 
	 */
	public OwnPhoneOrderUtil() {
	}

}
