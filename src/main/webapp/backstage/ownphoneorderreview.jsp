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
	String location = "订单预览";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/style1.css" />
<title><%=location%></title>

<script type="text/javascript">
	function showPicture() {
		var keypadValue = document.getElementsByName("ownphonekeypad")[0].value;
		var phonecolorValue = document.getElementsByName("ownphonecolor")[0].value;
		var pnonestyleValue = document.getElementsByName("ownphonestyle")[0].value;

		// Change image
		var previewImg;
		var oldImgUrl;
		var imageUrl;

		// Change color
		var colorRgb = "#000000";

		if (keypadValue == null || keypadValue.length == 0) {
			return;
		} else {

			document.getElementById("keypad" + keypadValue).style.display = "block";

			previewImg = document
					.getElementById("keypad" + keypadValue + "img");
			oldImgUrl = previewImg.src;
			imageUrl = oldImgUrl.slice(0, oldImgUrl.lastIndexOf("/") + 1);

			imageUrl += keypadValue;

			if (phonecolorValue != null && phonecolorValue.length != 0) {

				switch (phonecolorValue) {

				case "粉色":
					phonecolorValue = "pink";
					colorRgb = "rgb(236,0,140)";
					break;
				case "蓝色":
					phonecolorValue = "blue";
					colorRgb = "rgb(32,114,181)";
					break;
				case "绿色":
					phonecolorValue = "green";
					colorRgb = "rgb(5,169,81)";
					break;
				case "橘色":
					phonecolorValue = "orange";
					colorRgb = "rgb(244,116,33)";
					break;
				case "红色":
					phonecolorValue = "red";
					colorRgb = "rgb(226,39,28)";
					break;
				case "黑色":
					phonecolorValue = "black";
					colorRgb = "rgb(54,49,48)";
					break;
				}

				imageUrl += phonecolorValue;

				if (pnonestyleValue != null && pnonestyleValue.length != 0) {

					switch (pnonestyleValue) {

					case "朴素":
						pnonestyleValue = "";
						break;
					case "图片":
						pnonestyleValue = "image";
						break;
					case "花纹":
						pnonestyleValue = "pattern";
						break;
					}

					imageUrl += pnonestyleValue;
				}
			}
		}

		imageUrl += ".gif";
		previewImg.src = imageUrl;

		for ( var k = 1; k <= keypadValue; k++) {
			var changeObj = document.getElementById("keypad" + keypadValue
					+ "_number" + k);

			if (changeObj != null) {
				changeObj.style.color = colorRgb;
			}
		}

		var changeObj = document.getElementById("warmlyprompt");
		if (changeObj != null) {
			changeObj.style.color = colorRgb;
		}
	}
</script>

</head>

<body onload="showPicture();">
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

							<s:form name="for_onload_js">
								<s:hidden name="ownphonekeypad"
									value="%{#request.ownPhoneOrderToShow.keypad}" />
								<s:hidden name="ownphonecolor"
									value="%{#request.ownPhoneOrderToShow.phonecolor}" />
								<s:hidden name="ownphonestyle"
									value="%{#request.ownPhoneOrderToShow.phonestyle}" />
							</s:form>

							<table class="designtable">
								<thead>
									<tr>
										<th colspan="2">订单号：${requestScope.ownPhoneOrderToShow.ordernumber}</th>
									</tr>
									<tr>
										<th colspan="2">所属用户：${requestScope.ownPhoneOrderToShow.belongtouseraccount}</th>
									</tr>
									<tr>
										<th colspan="2">手机价格：￥${requestScope.ownPhoneOrderToShow.price}</th>
									</tr>
									<%
										OwnPhoneOrder modifyingOwnPhoneOrder = (OwnPhoneOrder) request
														.getAttribute("ownPhoneOrderToShow");
												Date orderDate = new Date(modifyingOwnPhoneOrder
														.getOrdertimemillis().longValue());
												Date modifyDate = new Date(modifyingOwnPhoneOrder
														.getModifytimemillis().longValue());
									%>
									<tr>
										<th style="padding: 15px; font-family: 宋体; font-size: 15px">

											下单时间：<fmt:formatDate value="<%=orderDate%>"
												pattern="yyyy-MM-dd HH:mm:ss" />
										</th>
										<th style="padding: 15px; font-family: 宋体; font-size: 15px">

											最近修改：<fmt:formatDate value="<%=modifyDate%>"
												pattern="yyyy-MM-dd HH:mm:ss" />
										</th>
									</tr>
								</thead>
							</table>

							<h3 id="warmlyprompt">温馨提示：鼠标停留在手机按键上可查看该按键绑定的电话号码。</h3>

							<div id="keypad2" class="reviewdiv">
								<img id="keypad2img"
									src="<%=path%>/images/ownphonepreview/2blueimage.gif" />
								<p id="ownphonename">${requestScope.ownPhoneOrderToShow.name}</p>
								<h1 id="keypad2_number1"
									title="${requestScope.ownPhoneOrderToShow.keynumber1}">${requestScope.ownPhoneOrderToShow.keyname1}</h1>
								<h1 id="keypad2_number2"
									title="${requestScope.ownPhoneOrderToShow.keynumber2}">${requestScope.ownPhoneOrderToShow.keyname2}</h1>
							</div>

							<div id="keypad4" class="reviewdiv">
								<img id="keypad4img"
									src="<%=path%>/images/ownphonepreview/4blueimage.gif" />
								<p id="ownphonename">${requestScope.ownPhoneOrderToShow.name}</p>
								<h1 id="keypad4_number1"
									title="${requestScope.ownPhoneOrderToShow.keynumber1}">${requestScope.ownPhoneOrderToShow.keyname1}</h1>
								<h1 id="keypad4_number2"
									title="${requestScope.ownPhoneOrderToShow.keynumber2}">${requestScope.ownPhoneOrderToShow.keyname2}</h1>
								<h1 id="keypad4_number3"
									title="${requestScope.ownPhoneOrderToShow.keynumber3}">${requestScope.ownPhoneOrderToShow.keyname3}</h1>
								<h1 id="keypad4_number4"
									title="${requestScope.ownPhoneOrderToShow.keynumber4}">${requestScope.ownPhoneOrderToShow.keyname4}</h1>
							</div>

							<div id="keypad8" class="reviewdiv">
								<img id="keypad8img"
									src="<%=path%>/images/ownphonepreview/8blueimage.gif" />
								<p id="ownphonename">${requestScope.ownPhoneOrderToShow.name}</p>
								<h1 id="keypad8_number1"
									title="${requestScope.ownPhoneOrderToShow.keynumber1}">${requestScope.ownPhoneOrderToShow.keyname1}</h1>
								<h1 id="keypad8_number2"
									title="${requestScope.ownPhoneOrderToShow.keynumber2}">${requestScope.ownPhoneOrderToShow.keyname2}</h1>
								<h1 id="keypad8_number3"
									title="${requestScope.ownPhoneOrderToShow.keynumber3}">${requestScope.ownPhoneOrderToShow.keyname3}</h1>
								<h1 id="keypad8_number4"
									title="${requestScope.ownPhoneOrderToShow.keynumber4}">${requestScope.ownPhoneOrderToShow.keyname4}</h1>
								<h1 id="keypad8_number5"
									title="${requestScope.ownPhoneOrderToShow.keynumber5}">${requestScope.ownPhoneOrderToShow.keyname5}</h1>
								<h1 id="keypad8_number6"
									title="${requestScope.ownPhoneOrderToShow.keynumber6}">${requestScope.ownPhoneOrderToShow.keyname6}</h1>
								<h1 id="keypad8_number7"
									title="${requestScope.ownPhoneOrderToShow.keynumber7}">${requestScope.ownPhoneOrderToShow.keyname7}</h1>
								<h1 id="keypad8_number8"
									title="${requestScope.ownPhoneOrderToShow.keynumber8}">${requestScope.ownPhoneOrderToShow.keyname8}</h1>
							</div>

							<div id="keypad12" class="reviewdiv">
								<img id="keypad12img"
									src="<%=path%>/images/ownphonepreview/12blueimage.gif" />
								<p id="ownphonename">${requestScope.ownPhoneOrderToShow.name}</p>
								<h1 id="keypad12_number1"
									title="${requestScope.ownPhoneOrderToShow.keynumber1}">${requestScope.ownPhoneOrderToShow.keyname1}</h1>
								<h1 id="keypad12_number2"
									title="${requestScope.ownPhoneOrderToShow.keynumber2}">${requestScope.ownPhoneOrderToShow.keyname2}</h1>
								<h1 id="keypad12_number3"
									title="${requestScope.ownPhoneOrderToShow.keynumber3}">${requestScope.ownPhoneOrderToShow.keyname3}</h1>
								<h1 id="keypad12_number4"
									title="${requestScope.ownPhoneOrderToShow.keynumber4}">${requestScope.ownPhoneOrderToShow.keyname4}</h1>
								<h1 id="keypad12_number5"
									title="${requestScope.ownPhoneOrderToShow.keynumber5}">${requestScope.ownPhoneOrderToShow.keyname5}</h1>
								<h1 id="keypad12_number6"
									title="${requestScope.ownPhoneOrderToShow.keynumber6}">${requestScope.ownPhoneOrderToShow.keyname6}</h1>
								<h1 id="keypad12_number7"
									title="${requestScope.ownPhoneOrderToShow.keynumber7}">${requestScope.ownPhoneOrderToShow.keyname7}</h1>
								<h1 id="keypad12_number8"
									title="${requestScope.ownPhoneOrderToShow.keynumber8}">${requestScope.ownPhoneOrderToShow.keyname8}</h1>
								<h1 id="keypad12_number9"
									title="${requestScope.ownPhoneOrderToShow.keynumber9}">${requestScope.ownPhoneOrderToShow.keyname9}</h1>
								<h1 id="keypad12_number10"
									title="${requestScope.ownPhoneOrderToShow.keynumber10}">${requestScope.ownPhoneOrderToShow.keyname10}</h1>
								<h1 id="keypad12_number11"
									title="${requestScope.ownPhoneOrderToShow.keynumber11}">${requestScope.ownPhoneOrderToShow.keyname11}</h1>
								<h1 id="keypad12_number12"
									title="${requestScope.ownPhoneOrderToShow.keynumber12}">${requestScope.ownPhoneOrderToShow.keyname12}</h1>
							</div>

							<c:if test="${requestScope.modifyPermission != 'no'}">
								<ul id="modifyandpayul">
									<li id="modifylist"
										onclick="window.location.href='<%=path%>/order!showOwnPhoneOrderByOrderNumber?orderNumber=${requestScope.ownPhoneOrderToShow.ordernumber}';">
										<h3>修改订单</h3>
									</li>
									<li id="paylist"
										onclick="window.location.href='<%=path%>/order!selectPayway?orderNumber=${requestScope.ownPhoneOrderToShow.ordernumber}';">
										<h3>支付页面</h3>
									</li>
									<li class="clear"></li>
								</ul>
							</c:if>
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
