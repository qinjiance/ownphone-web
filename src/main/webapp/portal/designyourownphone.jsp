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
<script type="text/javascript" src="<%=path%>/js/ownphoneordersubmit.js"></script>

<title><%=location%></title>
<!-- struts2 style, can set the s:xxx's label to italic, also set them to red when field error occurs-->
<s:head />
</head>

<body onload="checkRadio();feedBack();autoFixedElement('previewownphoneimg');">
	<center>
		<div class="pagebg">
			<div class="bodybg">
				<jsp:include page="/navigator.jsp">
					<jsp:param value="<%=location%>" name="location" />
				</jsp:include>

				<div id="mainlayout">

					<div id="designownphonelist">
						<h1>设计并订制您的个性手机非常简单--只需要完成下列三步即可！</h1>
						<ul>
							<li class="openlist1"
								onclick="showAndHide('keypad','phonecolor','phonestyle');">
								<h2>
									点击选择您的按键数量<span id="keypadsymbol" class="listcollapse">-</span>
								</h2>
							</li>

							<li id="keypad">
								<h3>选择您需要订制的号码数量</h3>
								
								<img class="keypadimg" title="2个号码"
									onclick="submitKeypad('2','ownPhoneOrder.keypad');"
									src="<%=path%>/images/ownphoneselect/2keypads.gif" alt="2个号码" />

								<img class="keypadimg" title="4个号码"
									onclick="submitKeypad('4','ownPhoneOrder.keypad');"
									src="<%=path%>/images/ownphoneselect/4keypads.gif" alt="4个号码" />

								<img class="keypadimg" title="8个号码"
									onclick="submitKeypad('8','ownPhoneOrder.keypad');"
									src="<%=path%>/images/ownphoneselect/8keypads.gif" alt="8个号码" />

								<img class="keypadimg" title="12个号码"
									onclick="submitKeypad('12','ownPhoneOrder.keypad');"
									src="<%=path%>/images/ownphoneselect/12keypads.gif" alt="12个号码" />
							</li>

							<li class="openlist2" onclick="showPhoneColorList();">
								<h2>
									点击选择您的机身颜色<span id="phonecolorsymbol" class="listcollapse">+</span>
								</h2>
							</li>

							<li id="phonecolor">
								<h3>选择您需要订制的手机颜色</h3>
								<ul id="phonecolorlist">
									<li id="phonecolorpink" title="粉色"
										onclick="submitInput('粉色','ownPhoneOrder.phonecolor')" />

									<li id="phonecolorblue" title="蓝色"
										onclick="submitInput('蓝色','ownPhoneOrder.phonecolor')" />

									<li id="phonecolorgreen" title="绿色"
										onclick="submitInput('绿色','ownPhoneOrder.phonecolor')" />

									<li id="phonecolororange" title="橘色"
										onclick="submitInput('橘色','ownPhoneOrder.phonecolor')" />

									<li id="phonecolorred" title="红色"
										onclick="submitInput('红色','ownPhoneOrder.phonecolor')" />

									<li id="phonecolorblack" title="黑色"
										onclick="submitInput('黑色','ownPhoneOrder.phonecolor')" />
								</ul>
							</li>

							<li class="clear"></li>

							<li class="openlist3" onclick="showPhoneStyleList();">
								<h2>
									点击选择您的机身皮肤<span id="phonestylesymbol" class="listcollapse">+</span>
								</h2>
							</li>

							<li id="phonestyle">
								<h3>选择您需要订制的手机皮肤</h3>
								
								<img class="phonestyleimg" title="朴素"
									onclick="submitInput('朴素','ownPhoneOrder.phonestyle');showElement('orderButton');"
									src="<%=path%>/images/ownphoneselect/plain.jpg" alt="朴素" />
									
								<img class="phonestyleimg" title="图片"
									onclick="submitInput('图片','ownPhoneOrder.phonestyle');showElement('orderButton');"
									src="<%=path%>/images/ownphoneselect/image.jpg" alt="图片" />
								
								<img class="phonestyleimg" title="花纹"
									onclick="submitInput('花纹','ownPhoneOrder.phonestyle');showElement('orderButton');"
									src="<%=path%>/images/ownphoneselect/pattern.jpg" alt="花纹" />
							</li>

							<li id="orderButton"
								onclick="showThenHide('ownphoneorderform','designownphonelist');">
								<h3>填写订单</h3>
							</li>
						</ul>
					</div>

					<s:form id="ownphoneorderform" name="addOwnPhoneOrder"
						action="order!addOwnPhoneOrder" method="post">
						<table>
							<tr>
								<td>
									<s:hidden name="ownPhoneOrder.keypad" />
								
									<s:hidden name="ownPhoneOrder.phonecolor" />
									
									<s:hidden name="ownPhoneOrder.phonestyle" />
								</td>
							</tr>
						</table>

						<ul id="changecolortitle1" class="ownphoneorderformlist1">
							<li>个性化</li>

							<li>
								<ul class="ownphoneorderformlist2">
									<li>您希望给您的个性手机起一个什么拉风的名字？</li>
									<li>这个名字将被印在手机的正面上。</li>
									<li>若不需要名字，请留空。</li>
									<li>
										<table>
											<tr>
												<td><s:textfield name="ownPhoneOrder.name" size="20"
														label="请输入个性手机的名字" /></td>
											</tr>
										</table>
									</li>
								</ul>
							</li>
						</ul>

						<ul id="changecolortitle2" class="ownphoneorderformlist1">
							<li>紧急呼叫按键</li>

							<li>
								<ul class="ownphoneorderformlist2">
									<li>您希望给您的个性手机设置一个紧急呼叫号码吗？</li>
									<li>注意：紧急呼叫按键会占用个性手机的最后一个按键。</li>
									<li>请选择：</li>
									<li><table>
											<tr>
												<td><s:radio cssStyle="margin-left: 40px;" name="ownPhoneOrder.emergency"
														label="是否有紧急呼叫按键" list="#{'有':'有','无':'无'}"
														onclick="inputEmergency();" /></td>
											</tr>
										</table></li>
								</ul>
							</li>
						</ul>

						<ul id="changecolortitle3" class="ownphoneorderformlist1">
							<li>按键与号码设置</li>

							<li>
								<ul class="ownphoneorderformlist2">
									<li><img id="namesandnumbersimg"
											src="<%=path%>/images/ownphoneselect/2keypads.gif"
											alt="预览图" /></li>
									<li>请设置您的个性手机按键与号码。</li>
									<li>每个名字对应一个电话号码，名字将被印在按键上面。</li>
									<li>注意：请务必填写正确的电话号码</li>
									<li>为了通用性，网站将不检查您的输入的有效性</li>
									<li>
										<table class="namesandnumberstb">
											<thead>
												<tr>
													<th></th>
													<th>名字</th>
													<th>电话号码</th>
												</tr>
											</thead>
											<tbody>
												<%
													for (int l = 1; l <= 12; l++) {
															request.setAttribute("keyname", "ownPhoneOrder.keyname" + l);
															request.setAttribute("keynumber", "ownPhoneOrder.keynumber" + l);
												%>
												<tr id="namesandnumbersinput<%=l%>"
													class="namesandnumbersinput">
													<th><%=l%></th>
													<td>
														<table>
															<tr>
																<td><s:textfield name="%{#request.keyname}"
																		size="20" /></td>
															</tr>
														</table>
													</td>
													<td>
														<table>
															<tr>
																<td><s:textfield name="%{#request.keynumber}"
																		size="20" /></td>
															</tr>
														</table>
													</td>
												</tr>
												<%
														if (l % 2 == 0) {
												%>
												<tr id="namesandnumbersline<%=l%>"
													class="namesandnumbersinput">
													<td colspan="3">
														<hr />
													</td>
												</tr>
												<%
														}
													}
												%>
											</tbody>
										</table>
									</li>
								</ul>
							</li>
						</ul>

						<ul id="changecolortitle4" class="ownphoneorderformlist1">
							<li>您的个性手机价格</li>

							<li>
								<ul class="ownphoneorderformlist2">
									<li>个性手机的定价标准为：</li>
									<li>2个按键为200RMB，每增加2个按键加价50RMB</li>
									<li>根据你的订制，您的个性手机价格为：</li>
									<li><span id="ownphoneorderprice">200</span>RMB</li>
									<li><s:hidden name="ownPhoneOrder.price" /></li>
								</ul>
							</li>
						</ul>

						<c:if test="${sessionScope.loginAccount!=null}">
							<ul id="backandsubmitul">
								<li id="gobacklist"
									onclick="showThenHide('designownphonelist','ownphoneorderform')">更改外观</li>
								<li id="submitlist"
									onclick="document.getElementById('ownphoneorderform').submit();">提交订单</li>
								<li class="clear"></li>
							</ul>
						</c:if>
					</s:form>

					<div id="previewownphoneimg">
						<h1>您的个性手机</h1>
						
						<img id="previewimg" alt="预览图" 
							src="<%=path%>/images/ownphonepreview/default_phone.gif" />
					</div>

					<div class="clear"></div>

				</div>

				<jsp:include page="/tailer.jsp" />
			</div>
		</div>
	</center>
</body>

</html>
