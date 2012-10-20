<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

						<c:when test="${sessionScope.loginAccount.privilege == 'common'}">
							<table class="ownphoneorderlisttable">
								<thead>
									<tr>
										<th colspan="12">我的订单列表</th>
									</tr>
									<tr>
										<td>序号</td>
										<td>订单号</td>
										<td>按键<br />数量
										</td>
										<td>机身<br />颜色
										</td>
										<td>机身<br />皮肤
										</td>
										<td>用户<br />姓名
										</td>
										<td>紧急呼叫<br />按键
										</td>
										<td>资费<br />套餐
										</td>
										<td>借记卡<br />卡主
										</td>
										<td>开卡<br />银行
										</td>
										<td>银行<br />分行
										</td>
										<td>借记卡<br />账号
										</td>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${request.ownPhoneOrderListToShow}"
										var="ownPhoneOrder" varStatus="status">
										<tr>
											<td>${status.index+1}</td>
											<td id="modifylink"><a
												href="<%=path%>/order!showOwnPhoneOrderByOrderNumber?orderNumber=${ownPhoneOrder.ordernumber}">
													${ownPhoneOrder.ordernumber} </a></td>
											<td>${ownPhoneOrder.keypad}个</td>
											<td>${ownPhoneOrder.phonecolor}</td>
											<td>${ownPhoneOrder.phonestyle}</td>
											<td>${ownPhoneOrder.name}</td>
											<td>${ownPhoneOrder.emergency}</td>
											<td>${ownPhoneOrder.price}</td>
											<td>${ownPhoneOrder.debithost}</td>
											<td>${ownPhoneOrder.bank}</td>
											<td>${ownPhoneOrder.branch}</td>
											<td>${ownPhoneOrder.account}</td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<td colspan="12">
											<span>总订单数：${requestScope.orderSize}</span>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											分页：
											&nbsp;&nbsp;
											<%
												int orderSize = ((Integer) request.getAttribute("orderSize")).intValue();
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
														<a href="<%=path%>/order!showCommonUserOwnPhoneOrders?page=<%=i%>"><%=i%></a>
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
															<a href="<%=path%>/order!showCommonUserOwnPhoneOrders?page=1">1</a>
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
																<a href="<%=path%>/order!showCommonUserOwnPhoneOrders?page=<%=i%>"><%=i%></a>
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
															<a href="<%=path%>/order!showCommonUserOwnPhoneOrders?page=<%=pageSize%>"><%=pageSize%></a>
															|
											<%				
														}
												}
											%>
										</td>
									</tr>
								</tfoot>
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
