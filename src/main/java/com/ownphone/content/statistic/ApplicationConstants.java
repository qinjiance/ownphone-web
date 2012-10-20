/**
 * 
 */
package com.ownphone.content.statistic;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Jiance Qin
 * 
 */
public class ApplicationConstants {

	// 在线的session集合
	public static Map<String, Object> OnlineSessionMap = new LinkedHashMap<String, Object>();

	// 当前登录的用户数
	public static int CURRENT_LOGIN_COUNT = 0;

	// 历时访客数
	public static int TOTAL_HISTORY_COUNT = 0;

	// 服务器启动时间
	public static Date SERVER_START_DATE = null;

	// 最高在线人数出现的时间
	public static Date MAX_ONLINE_COUNT_DATE = null;

	// 最高在线人数
	public static int MAX_ONLINE_COUNT = 0;

}
