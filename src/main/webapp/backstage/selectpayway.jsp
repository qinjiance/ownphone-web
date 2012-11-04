<%@ page language="java" import="com.ownphone.content.po.IUser" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String location = "请选择支付方式";
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

						<c:when
							test="${sessionScope.loginAccount.privilege == 'common' 
										|| sessionScope.loginAccount.privilege == 'admin'}">

							<s:actionmessage cssClass="message" />

							<div id="paystep2img">
								<img src="<%=path%>/images/paystep2/paystep2.gif" alt="去支付" />
							</div>

							<div id="orderpriceinfo">
								<table>
									<thead>
										<tr>
											<th>订单号</th>
											<th>单价</th>
											<th>运费</th>
											<th>总价</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${requestScope.ownPhoneOrderToShow.ordernumber}</td>
											<td>￥${requestScope.ownPhoneOrderToShow.price}</td>
											<td>￥0</td>
											<td>￥${requestScope.ownPhoneOrderToShow.price+0}</td>
										</tr>
									</tbody>
								</table>
							</div>

							<div id="sureaccountinfo" class="paystep2">
								<h1>（注意：请确认是您的账户后再购买！）</h1>
								<h3>您的账户信息：</h3>
								<h4>
								<%
									IUser user = (IUser)session.getAttribute("loginAccount");
									String account = user.fetchUseraccount();
								%>
									用户名：<span style="font-weight: normal;"><%=account%></span>
								</h4>
								<h4>
									真实姓名：<span style="font-weight: normal;">${sessionScope.loginAccount.realname}</span>
								</h4>
								<h4>
									手机号码：<span style="font-weight: normal;">${sessionScope.loginAccount.mobilephone}</span>
								</h4>
								<h4>
									电子邮箱：<span style="font-weight: normal;">${sessionScope.loginAccount.email}</span>
								</h4>
							</div>

							<div id="expressaddress" class="paystep2">
								<h1>配送地址：</h1>
								<h4>姓名：</h4>
								<h4>地址：</h4>
								<h4>邮编：</h4>
								<h4>联系电话：</h4>
								<h4>送货的时间：</h4>
							</div>

							<div id="payway" class="paystep2">
								<h1>请选择支付方式</h1>
								<form name="selectpayway" action="" method="post">
									<table>
										<thead>
											<tr>
												<th colspan="4">支持以下银行和机构的在线支付：</th>
											</tr>
										</thead>
										<tbody>
											<%
												int totalWays = 14;
														int colPerRow = 4;
														int totalRows = totalWays / colPerRow;
														if (totalWays % colPerRow != 0) {
															totalRows++;
														}
														for (int row = 0; row < totalRows; row++) {
											%>
											<tr>
												<%
													for (int col = 1; col <= colPerRow; col++) {
																	int currentNumber = colPerRow * row + col;
																	if (currentNumber <= totalWays) {
												%>
												<td><input type="radio" name="payway"
													value="<%=currentNumber%>" /> <img
														src="<%=path%>/images/paystep2/bank<%=currentNumber%>.gif"
														alt="支付方式<%=currentNumber%>" /></td>
												<%
													} else {
												%>
												<td></td>
												<%
													}
																}
												%>
											</tr>
											<%
												}
											%>

										</tbody>
									</table>
								</form>
							</div>

							<div id="gotopay" class="paystep2">
								<p onclick=";">去支付</p>
							</div>

							<div class="clear"></div>

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
