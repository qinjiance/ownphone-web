<%@ page language="java" 
	import="com.ownphone.content.po.OwnPhoneOrder,java.util.Date" 
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
										<th colspan="10">我的订单列表</th>
									</tr>
									<s:form name="ownPhoneOrderQuery" action="order!showAdministratorOwnPhoneOrders" 
											method="post" theme="simple" >
									<tr>
										<td id="ownphoneorderquerytd" colspan="10">
											<table class="ownphoneorderquerytable">
												<tr>
													<td>排序类型：<br /><s:select name="ownPhoneOrderQuery.ordertype" 
														value="%{#request.ownPhoneOrderQuery.ordertype}"
														list="#{'orderedtime':'下单时间','modifiedtime':'修改时间'}" /></td>
													<td>排序方向：<br /><s:select name="ownPhoneOrderQuery.orderdirection" 
														value="%{#request.ownPhoneOrderQuery.orderdirection}"
														list="#{'increasing':'升序','descending':'降序'}" /></td>
													<td>按键数量：<br /><s:select name="ownPhoneOrderQuery.keypad" 
														value="%{#request.ownPhoneOrderQuery.keypad}"
														list="#{'':'所有','2':'2个','4':'4个','8':'8个','12':'12个'}" /></td>
													<td>机身颜色：<br /><s:select name="ownPhoneOrderQuery.phonecolor" 
														value="%{#request.ownPhoneOrderQuery.phonecolor}"
														list="#{'':'所有','粉色':'粉色','蓝色':'蓝色','绿色':'绿色','红色':'红色','橘色':'橘色','黑色':'黑色'}" /></td>
													<td>机身皮肤：<br /><s:select name="ownPhoneOrderQuery.phonestyle" 
														value="%{#request.ownPhoneOrderQuery.phonestyle}"
														list="#{'':'所有','朴素':'朴素','图片':'图片','花纹':'花纹'}" /></td>
													<td>紧急呼叫按键：<br /><s:select name="ownPhoneOrderQuery.emergency" 
														value="%{#request.ownPhoneOrderQuery.emergency}"
														list="#{'':'所有','有':'有','无':'无'}" /></td>
													<td>手机价格：<br /><s:select name="ownPhoneOrderQuery.price" 
														value="%{#request.ownPhoneOrderQuery.price}"
														list="#{'':'所有','200':'200RMB','250':'250RMB','350':'350RMB','450':'450RMB'}" /></td>
													<td>下单时间：<br /><s:select name="ownPhoneOrderQuery.ordertime" 
														value="%{#request.ownPhoneOrderQuery.ordertime}"
														list="#{'':'所有','latestthreemonthes':'三个月内','threemonthesago':'三个月前'}" /></td>
													<td>订单号查询：<br /><s:textfield name="ordernumberquery" size="11"
														 maxlength="12" value="%{#request.ordernumberquery}" /></td>	
														
													<td><s:hidden id="pagehiddenfield" name="page" value="1"/>
														<s:submit value="查询"/></td>
												</tr>
											</table>
										</td>
									</tr>
									</s:form>
									<tr>
										<td>序号</td>
										<td>订单号</td>
										<td>按键<br />数量
										</td>
										<td>机身<br />颜色
										</td>
										<td>机身<br />皮肤
										</td>
										<td>手机<br />名字
										</td>
										<td>紧急呼叫<br />按键
										</td>
										<td>手机价格
										</td>
										<td>下单时间
										</td>
										<td>最近修改
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
											<td>${ownPhoneOrder.price}RMB</td>
											<% 
												OwnPhoneOrder ownPhoneOrder = (OwnPhoneOrder)pageContext.getAttribute("ownPhoneOrder");
												Date orderDate = new Date(ownPhoneOrder.getOrdertimemillis().longValue());
												Date modifyDate = new Date(ownPhoneOrder.getModifytimemillis().longValue());;
											%>
											<td><fmt:formatDate value="<%=orderDate%>" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td><fmt:formatDate value="<%=modifyDate%>" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<td colspan="10">
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
														<a href="<%=path%>/order!showAdministratorOwnPhoneOrders?page=<%=i%>" 
															onclick="document.getElementById('pagehiddenfield').value='<%=i%>'; 
															document.all.ownPhoneOrderQuery.submit();return false;">
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
															<a href="<%=path%>/order!showAdministratorOwnPhoneOrders?page=1" 
																	onclick="document.getElementById('pagehiddenfield').value='1'; 
																	document.all.ownPhoneOrderQuery.submit();return false;">
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
																<a href="<%=path%>/order!showAdministratorOwnPhoneOrders?page=<%=i%>" 
																	onclick="document.getElementById('pagehiddenfield').value='<%=i%>'; 
																	document.all.ownPhoneOrderQuery.submit();return false;">
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
															<a href="<%=path%>/order!showAdministratorOwnPhoneOrders?page=<%=pageSize%>" 
																onclick="document.getElementById('pagehiddenfield').value='<%=pageSize%>'; 
																document.all.ownPhoneOrderQuery.submit();return false;">
																<%=pageSize%>
															</a>
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
