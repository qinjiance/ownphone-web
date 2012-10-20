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
	String location = "修改订单";
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
							<s:form name="modifyOwnPhoneOrder"
								action="order!modifyOwnPhoneOrder" method="post">
								<table class="designtable">
									<thead>
										<tr>
											<th colspan="2">订单号：${ownPhoneOrderToShow.ordernumber}</th>
										</tr>
										<%
											OwnPhoneOrder ownPhoneOrder = (OwnPhoneOrder) request
																.getAttribute("ownPhoneOrderToShow");
														long orderTimeMillis = ownPhoneOrder
																.getOrdertimemillis().longValue();
														Date orderDate = new Date(orderTimeMillis);
														long modifyTimeMillis = ownPhoneOrder
																.getModifytimemillis().longValue();
														Date modifyDate = new Date(modifyTimeMillis);
										%>
										<tr>
											<th
												style="padding: 15px; color: #00aaaa; font-family: 宋体; font-size: 15px">下单时间：<fmt:formatDate
													value="<%=orderDate%>" pattern="yyyy-MM-dd HH:mm:ss" /></th>
											<th
												style="padding: 15px; color: #00aaaa; font-family: 宋体; font-size: 15px">最近修改：<fmt:formatDate
													value="<%=modifyDate%>" pattern="yyyy-MM-dd HH:mm:ss" /></th>
										</tr>
										<tr>
											<td><s:hidden name="modifyingOwnPhoneOrder.ordernumber"
													value="%{#request.ownPhoneOrderToShow.ordernumber}" /></td>
										</tr>
									</thead>
								</table>
								<table class="designtable">
									<thead>
										<tr>
											<th colspan="2">修改您的个性手机参数</th>
										</tr>
									</thead>
									<tr>
										<td><s:radio name="modifyingOwnPhoneOrder.keypad"
												value="%{#request.ownPhoneOrderToShow.keypad}"
												label="选择您的按键数量"
												list="#{'2':'2个名字','4':'4个名字','8':'8个名字','12':'12个名字'}" /></td>
									</tr>
									<tr>
										<td><s:radio name="modifyingOwnPhoneOrder.phonecolor"
												value="%{#request.ownPhoneOrderToShow.phonecolor}"
												label="选择您的机身颜色"
												list="#{'粉色':'粉色','蓝色':'蓝色','绿色':'绿色','红色':'红色','橘色':'橘色','黑色':'黑色'}" />
										</td>
									</tr>
									<tr>
										<td><s:radio name="modifyingOwnPhoneOrder.phonestyle"
												value="%{#request.ownPhoneOrderToShow.phonestyle}"
												label="选择您的机身皮肤" list="#{'朴素':'朴素','图片':'图片','花纹':'花纹'}" /></td>
									</tr>
								</table>

								<table class="designtable">
									<thead>
										<tr>
											<th colspan="2">填写您的个人资料</th>
										</tr>
									</thead>
									<tr>
										<td><s:textfield name="modifyingOwnPhoneOrder.name"
												value="%{#request.ownPhoneOrderToShow.name}" size="30"
												label="请输入您的姓名" /></td>
									</tr>
									<tr>
										<td><s:radio name="modifyingOwnPhoneOrder.emergency"
												value="%{#request.ownPhoneOrderToShow.emergency}"
												label="是否有紧急呼叫按键" list="#{'有':'有','无':'无'}" /></td>
									</tr>
								</table>

								<table class="designtable">
									<thead>
										<tr>
											<th colspan="2">资费套餐</th>
										</tr>
									</thead>
									<tr>
										<td><s:radio name="modifyingOwnPhoneOrder.price"
												value="%{#request.ownPhoneOrderToShow.price}"
												label="请选择通话资费套餐"
												list="#{'套餐50':'套餐50','套餐100':'套餐100','套餐500':'套餐500'}" /></td>
									</tr>
								</table>
								<table class="designtable">
									<thead>
										<tr>
											<th colspan="2">直接借记卡资料</th>
										</tr>
									</thead>
									<tr>
										<td><s:textfield name="modifyingOwnPhoneOrder.debithost"
												value="%{#request.ownPhoneOrderToShow.debithost}" size="30"
												label="卡主姓名" /></td>
									</tr>
									<tr>
										<td><s:textfield name="modifyingOwnPhoneOrder.bank"
												value="%{#request.ownPhoneOrderToShow.bank}" size="30"
												label="开卡银行" /></td>
									</tr>
									<tr>
										<td><s:textfield name="modifyingOwnPhoneOrder.branch"
												value="%{#request.ownPhoneOrderToShow.branch}" size="30"
												label="银行分行" /></td>
									</tr>
									<tr>
										<td><s:textfield name="modifyingOwnPhoneOrder.account"
												value="%{#request.ownPhoneOrderToShow.account}" size="30"
												label="借记卡账号" /></td>
									</tr>
								</table>
								<table style="margin: 20px;">
									<tr>
										<td><s:submit value="修改订单" /></td>
										<td><s:reset value="恢复订单" /></td>
									</tr>
								</table>
							</s:form>
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
