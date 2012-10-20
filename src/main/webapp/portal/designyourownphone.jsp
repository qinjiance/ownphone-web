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
	String location = "订制您的个性手机";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/style1.css" />
<title><%=location%></title>
<!-- struts2 style, can set the s:xxx's label to italic, also set them to red when field error occurs-->
<s:head />
</head>

<body>
	<center>
		<div class="pagebg">
			<div class="bodybg">
				<jsp:include page="/navigator.jsp">
					<jsp:param value="<%=location%>" name="location" />
				</jsp:include>

				<div id="mainlayout">
					<s:form name="addOwnPhoneOrder" action="order!addOwnPhoneOrder"
						method="post">
						<table class="designtable">
							<thead>
								<tr>
									<th colspan="2">选择您的个性手机参数</th>
								</tr>
							</thead>
							<tr>
								<td><s:radio name="ownPhoneOrder.keypad" label="选择您的按键数量"
										list="#{'2':'2个名字','4':'4个名字','8':'8个名字','12':'12个名字'}" /></td>
							</tr>
							<tr>
								<td><s:radio name="ownPhoneOrder.phonecolor"
										label="选择您的机身颜色"
										list="#{'粉色':'粉色','蓝色':'蓝色','绿色':'绿色','红色':'红色','橘色':'橘色','黑色':'黑色'}" />
								</td>
							</tr>
							<tr>
								<td><s:radio name="ownPhoneOrder.phonestyle"
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
								<td><s:textfield name="ownPhoneOrder.name" size="30"
										label="请输入您的姓名" /></td>
							</tr>
							<tr>
								<td><s:radio name="ownPhoneOrder.emergency"
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
								<td><s:radio name="ownPhoneOrder.price" label="请选择通话资费套餐"
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
								<td><s:textfield name="ownPhoneOrder.debithost" size="30"
										label="卡主姓名" /></td>
							</tr>
							<tr>
								<td><s:textfield name="ownPhoneOrder.bank" size="30"
										label="开卡银行" /></td>
							</tr>
							<tr>
								<td><s:textfield name="ownPhoneOrder.branch" size="30"
										label="银行分行" /></td>
							</tr>
							<tr>
								<td><s:textfield name="ownPhoneOrder.account" size="30"
										label="借记卡账号" /></td>
							</tr>
						</table>
						<c:if test="${sessionScope.loginAccount!=null}">
							<table style="margin: 20px;">
								<tr>
									<td><s:submit value="提交订单" /></td>
									<td><s:reset value="重置订单" /></td>
								</tr>
							</table>
						</c:if>
					</s:form>
				</div>

				<jsp:include page="/tailer.jsp" />
			</div>
		</div>
	</center>
</body>

</html>
