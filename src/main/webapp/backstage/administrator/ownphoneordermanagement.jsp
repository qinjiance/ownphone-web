<%@ page language="java" 
	import="com.ownphone.content.po.IUser,java.util.Date" 
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
	String location = "我的订单管理";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/style1.css" />
<script type="text/javascript" src="<%=path%>/js/Iframe.js"></script>
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
							<table class="ownphoneorderlisttable">
								<thead>
									<tr>
										<th colspan="8">账户列表</th>
									</tr>
									<s:form name="userQuery" action="administrator!showAllUsers" 
											method="post" theme="simple" >
									<tr>
										<td id="ownphoneorderquerytd" colspan="8">
											<table class="ownphoneorderquerytable">
												<tr>
													<td>排序类型<br /><s:select name="userQuery.ordertype" 
														value="%{#request.userQuery.ordertype}"
														list="#{'account':'用户名','registertime':'注册时间'}" /></td>
													<td>排序方向<br /><s:select name="userQuery.orderdirection" 
														value="%{#request.userQuery.orderdirection}"
														list="#{'increasing':'升序','descending':'降序'}" /></td>
													<td>用户名<br /><s:textfield name="accountquery" size="9"
														 maxlength="12" value="%{#request.accountquery}" /></td>
													<td>昵称<br /><s:textfield name="userQuery.nickname" size="7"
														 maxlength="10" value="%{#request.userQuery.nickname}" /></td>
													<td>真实姓名<br /><s:textfield name="userQuery.realname" size="6"
														 value="%{#request.userQuery.realname}" /></td>
													<td>手机号码<br /><s:textfield name="userQuery.mobilephone" size="8"
														 maxlength="11" value="%{#request.userQuery.mobilephone}" /></td>
													<td>E-mail<br /><s:textfield name="userQuery.email" size="12"
														 value="%{#request.userQuery.email}" /></td>
													<td>账户权限<br /><s:select name="userQuery.privilege" 
														value="%{#request.userQuery.privilege}"
														list="#{'':'所有','common':'普通用户','admin':'管理员'}" /></td>
													<td>注册时间<br /><s:select name="userQuery.registertime" 
														value="%{#request.userQuery.registertime}"
														list="#{'':'所有','latestthreemonthes':'三个月内','threemonthesago':'三个月前'}" /></td>	
														
													<td><s:hidden id="pagehiddenfield" name="page" value="1"/>
														<input type="image" alt="搜索" src="<%=path%>/images/search.gif" /></td>
												</tr>
											</table>
										</td>
									</tr>
									</s:form>
									<tr>
										<td>序号</td>
										<td>用户名</td>
										<td>昵称
										</td>
										<td>真实姓名
										</td>
										<td>手机号码
										</td>
										<td>E-mail
										</td>
										<td>账户权限
										</td>
										<td>注册时间
										</td>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${request.userListToShow}"
										var="user" varStatus="status">
										<tr>
											<td>${status.index+1}</td>
											<%
												IUser iterateuser = (IUser)pageContext.getAttribute("user");
												String account = iterateuser.fetchUseraccount();
											%>
											<td id="modifylink"><a title="点击查看该用户的订单列表"
												href="<%=path%>/order!showAdministratorOwnPhoneOrders?account=<%=account%>"
												target="ownphoneorderlist_iframe">
													<%=account%></a></td>
											<td>${user.nickname}</td>
											<td>${user.realname}</td>
											<td>${user.mobilephone}</td>
											<td>${user.email}</td>
											<%
												String showPrivilege;
												String userPrivilege = iterateuser.fetchPrivilege();
												if (userPrivilege.equals("common")) {
													showPrivilege = "普通用户";
												} else if(userPrivilege.equals("admin")) {
													showPrivilege = "管理员";
												} else {
													showPrivilege = "权限为空";
												}
											%>
											<td><%=showPrivilege%></td>
											<% 
												Date registerDate = new Date(iterateuser.fetchRegisterTimeMillis());
											%>
											<td class="ownphoneorderlisttimetd"><fmt:formatDate value="<%=registerDate%>" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<td colspan="2" id="modifylink"><a title="点击查看所有用户的订单列表"
												href="<%=path%>/order!showAdministratorOwnPhoneOrders"
												target="ownphoneorderlist_iframe">
													查看所有订单</a></td>
										<td colspan="6">
											<span>总账户数：${requestScope.userSize}</span>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											分页：
											&nbsp;&nbsp;
											<%
												int pageSize = ((Integer) request.getAttribute("pageSize")).intValue();
												int currentPage = ((Integer) request.getAttribute("currentPage")).intValue();
											
												if (pageSize <= 7) {
													for (int i = 1; i <= pageSize; i++) {
														if (i == currentPage) {
											%>
															<span id="separatedpage">
											<% 					
														}
											%> 			
														<a href="" onclick="document.getElementById('pagehiddenfield').value='<%=i%>'; 
															document.all.userQuery.submit();return false;">
															<%=i%>
														</a>
											<% 
														if (i == currentPage) {
											%>
															</span>
											<% 					
														}
											%>
														|
											<%		} 
												} else {
														// 第1页
														if (currentPage-3 >= 1) {
											%>
															<a href="" onclick="document.getElementById('pagehiddenfield').value='1'; 
																	document.all.userQuery.submit();return false;">
																1
															</a>
															| 
											<%
															if (currentPage-3 > 1) {
											%>
																...
																|				
											<%				
															}
														}
														
														// 第currentPage-2 ~ currentPage+2页
														for (int i = currentPage-2; i<=currentPage+2; i++) {
															if(i>=1 && i<=pageSize) {
																if (i == currentPage) {
											%>
																	<span id="separatedpage">
											<% 					
																}
											%>
																<a href="" onclick="document.getElementById('pagehiddenfield').value='<%=i%>'; 
																	document.all.userQuery.submit();return false;">
																	<%=i%>
																</a>
											<% 
																if (i == currentPage) {
											%>
																	</span>
											<% 					
																}
											%>
																|
											<%
															}
														}
														
														// 第pageSize页
														if (currentPage+3 <= pageSize) {
															if(currentPage+3 < pageSize) {
											%>					
																...
																|
											<%					
															}
											%>				
															<a href="" onclick="document.getElementById('pagehiddenfield').value='<%=pageSize%>'; 
																document.all.userQuery.submit();return false;">
																<%=pageSize%>
															</a>
															|
											<%				
														}
												}
											%>
											
											<input id="gotopage" type="text" name="goToPage" value="<%=currentPage%>" 
												size="5" maxlength="5" />
												
											<img style="cursor: pointer;" src="<%=path%>/images/go.gif" alt="跳转" 
												onclick="document.getElementById('pagehiddenfield').value=document.getElementById('gotopage').value; 
												document.all.userQuery.submit();return false;" />
										</td>
									</tr>
								</tfoot>
							</table>
							
							<div class="horizatal"></div>
							
							<div>
								<iframe id="ownphoneorderlistiframe" name="ownphoneorderlist_iframe"
									scrolling="no" frameborder="0"
									src="<%=path%>/backstage/administrator/ownphoneorderlist.jsp"
									onload="Javascript:SetIframeHeight(this)"></iframe>
							</div>
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
