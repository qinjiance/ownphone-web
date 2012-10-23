<%@ page language="java"
	import="com.ownphone.content.statistic.ApplicationConstants,com.ownphone.content.po.IUser,java.util.Date,com.ownphone.content.bean.LoginInfo"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String location = "服务器统计信息";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/style1.css" />
<title><%=location%></title>
</head>

<body>
	<center>
		<div class="pagebg">
			<div class="bodybg">
				<jsp:include page="/navigator.jsp">
					<jsp:param value="<%=location%>" name="location" />
				</jsp:include>

				<div id="mainlayout">

					<c:choose>
						<c:when test="${sessionScope.loginAccount == null}">
							<%
								response.sendRedirect(path + "/pleaselogin.jsp");
							%>
						</c:when>

						<c:when test="${sessionScope.loginAccount.privilege == 'admin'}">
							<table class="serverstatistictable">
								<thead>
									<tr>
										<th colspan="6"><strong>服务器启动时间：</strong> <fmt:formatDate
												value="<%=ApplicationConstants.SERVER_START_DATE%>"
												pattern="yyyy-MM-dd HH:mm:ss" /></th>
										<th colspan="5"><strong>历史访客数：</strong> <%=ApplicationConstants.TOTAL_HISTORY_COUNT%>人</th>
									</tr>
									<tr>
										<th colspan="6"><strong>最高在线访客数：</strong> <%=ApplicationConstants.MAX_ONLINE_COUNT%>人
										</th>
										<th colspan="5"><strong>发生在：</strong> <fmt:formatDate
												value="<%=ApplicationConstants.MAX_ONLINE_COUNT_DATE%>"
												pattern="yyyy-MM-dd HH:mm:ss" /></th>
									</tr>
									<tr>
										<td>序号</td>
										<td>在线用户<br />SessionID
										</td>
										<td>Session<br />建立时间
										</td>
										<td>登录<br />用户名
										</td>
										<td>登录用<br />户权限
										</td>
										<td>用户注<br />册日期
										</td>
										<td>用户登<br />录时间
										</td>
										<td>远程<br />主机名
										</td>
										<td>远程<br />IP地址
										</td>
										<td>在线<br />操作
										</td>
										<td>最后操<br />作时间
										</td>
									</tr>
								</thead>
								<tbody>
									<c:forEach
										items="<%=ApplicationConstants.OnlineSessionMap
								.values()%>"
										var="sess" varStatus="st">
										<tr>
											<td>${st.count}</td>
											<%
												HttpSession iteratingSession = (HttpSession) pageContext
																	.getAttribute("sess");
															String sessionId = iteratingSession.getId();
															int sessionIdLength = sessionId.length();
															String sessionIdPart1 = sessionId.substring(0,
																	sessionIdLength / 2);
															String sessionIdPart2 = sessionId
																	.substring(sessionIdLength / 2);
											%>
											<td><%=sessionIdPart1%><br /><%=sessionIdPart2%></td>
											<%
												long creationtime = iteratingSession.getCreationTime();
											%>
											<td><fmt:formatDate value="<%=new Date(creationtime)%>"
													pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<%
												IUser loginUser = (IUser) iteratingSession
																	.getAttribute("loginAccount");
															String userAccount = null;
															String privilege = null;
															long registerTimeMillis = 0;
															LoginInfo loginInfo = null;
															if (loginUser != null) {
																userAccount = loginUser.fetchUseraccount();
																privilege = loginUser.fetchPrivilege();
																registerTimeMillis = loginUser
																		.fetchRegisterTimeMillis();
																loginInfo = (LoginInfo) iteratingSession
																		.getAttribute("loginInfo");
															}
											%>
											<td><%=loginUser == null ? "未登录" : userAccount%></td>
											<td><%=loginUser == null ? "未登录" : privilege
								.equals("common") ? "普通用户" : privilege
								.equals("admin") ? "管理员" : "权限为空"%></td>
								<td>
											<%
												if (loginUser == null) {
											%>
											未登录
											<%
												} else {
											%>
											<fmt:formatDate
													value="<%=new Date(registerTimeMillis)%>"
													pattern="yyyy-MM-dd HH:mm:ss" />
											<%
												}
											%>
											</td>
											<td>
												<%
													if (loginUser == null) {
												%> 未登录 <%
													} else {
												%> <fmt:formatDate value="<%=loginInfo.getLogindatatime()%>"
													pattern="yyyy-MM-dd HH:mm:ss" /> <%
 	}
 %>
											</td>
											<td><%=iteratingSession.getAttribute("remoteHost")%></td>
											<td><%=iteratingSession.getAttribute("remoteIP")%></td>
											<td><%=iteratingSession.getAttribute("activeTimes")%>次</td>
											<%
												long lastaccessedtime = iteratingSession
																	.getLastAccessedTime();
											%>
											<td><fmt:formatDate
													value="<%=new Date(lastaccessedtime)%>"
													pattern="yyyy-MM-dd HH:mm:ss" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:when>

						<c:otherwise>
							<%
								response.sendRedirect(path + "/pleaselogin.jsp");
							%>
						</c:otherwise>
					</c:choose>

				</div>

				<jsp:include page="/tailer.jsp" />
			</div>
		</div>
	</center>
</body>

</html>
