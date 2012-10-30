<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/style1.css" />
<link rel="shortcut icon" href="<%=path%>/images/favicon.ico"
	type="image/x-icon" />
<link rel="icon" href="<%=path%>/images/animated_favicon1.gif"
	type="image/gif" />
<link rel="Bookmark" href="<%=path%>/images/favicon.ico" />
<!-- struts2 style, can set the s:xxx's label to italic, also set them to red when field error occurs-->
<s:head />

<%
	String message = (String) session.getAttribute("repeatLoginError");
	if (message != null) {
%>
<script language="javascript" type="text/javascript">
	location.href = "<%=path%>
	/logout.jsp";
</script>
<%
	}
%>
</head>

<body>
	<center>
		<div class="navigbg">

			<table class="navigtable1">
				<tr>
					<td class="tab1"><a href="<%=path%>/index.jsp">个性<br />手机
					</a></td>
				</tr>
			</table>

			<table class="navigtable2">
				<tr>
					<td class="tab2"><a href="<%=path%>/portal/whatisownphone.jsp">什么是<br />个性手机
					</a></td>
					<td class="tab2"><a
						href="<%=path%>/portal/whychooseownphone.jsp">为何选择<br />个性手机
					</a></td>
					<td class="tab2"><a href="<%=path%>/portal/howdoesitwork.jsp">个性手机<br />如何工作
					</a></td>
					<td class="tab2"><a href="<%=path%>/portal/whatdoesitcost.jsp">个性手机<br />价格如何
					</a></td>
					<td class="tab2"><a
						href="<%=path%>/portal/frequentlyaskedquestions.jsp">常见问题<br />&nbsp;&nbsp;/&nbsp;&nbsp;FAQ
					</a></td>
					<td class="tab2"><a
						href="<%=path%>/portal/designyourownphone.jsp">订制您的<br />个性手机
					</a></td>
				</tr>
			</table>

			<table class="navigtitle">
				<tr>
					<td><strong>${param.location}</strong></td>
				</tr>
			</table>

			<c:choose>
				<c:when test="${sessionScope.loginAccount == null}">
					<s:form name="loginForm" action="login" method="post">
						<table class="naviglogin">
							<tr>
								<td colspan="2"><s:fielderror fieldName="loginerror" /></td>
							</tr>
							<tr>
								<td>
									<table>
										<tr>
											<td><s:textfield cssStyle="width:100px;"
													name="loginUser.useraccount" maxlength="12" label="用户名" /></td>
										</tr>
									</table>
								</td>
								<td>
									<table>
										<tr>
											<td><s:password cssStyle="width:100px;"
													name="loginUser.password" maxlength="18" label="密码" /></td>
										</tr>
									</table>
								</td>
								<td><input type="image" src="<%=path%>/images/login.gif"
									alt="登陆" /></td>
								<td><img style="cursor: pointer;"
										src="<%=path%>/images/register.gif" alt="注册"
										onclick="window.location.href='<%=path%>/portal/register.jsp'" /></td>
							</tr>
						</table>
					</s:form>
				</c:when>
				<c:when test="${sessionScope.loginAccount.privilege == 'common'}">
					<table class="navigcommonuser">
						<tr>
							<td>${sessionScope.loginAccount.useraccount}<c:if
									test="${sessionScope.loginAccount.nickname != null}">
									(&nbsp;${sessionScope.loginAccount.nickname}&nbsp;)
								</c:if><font color="#000000">，您好！</font>
							</td>

							<td>||</td>

							<td class="navigloginfuction"><a
								href="<%=path%>/backstage/commonuser/modifyinfo.jsp">修改个人资料</a></td>

							<td>||</td>

							<td class="navigloginfuction"><a
								href="<%=path%>/order!showCommonUserOwnPhoneOrders?page=1">我的订单管理</a></td>

							<td>||</td>

							<td class="navigloginfuction"><a href="<%=path%>/logout.jsp">注销</a></td>
						</tr>
					</table>
				</c:when>
				<c:when test="${sessionScope.loginAccount.privilege == 'admin'}">
					<table class="navigadmin">
						<tr>
							<td><font color="#ff0000">（管理员）</font>
								${sessionScope.loginAccount.adminaccount}<c:if
									test="${sessionScope.loginAccount.nickname != null}">
									(&nbsp;${sessionScope.loginAccount.nickname}&nbsp;)
								</c:if><font color="#000000">，您好！</font></td>

							<td>||</td>

							<td class="navigloginfuction"><a
								href="<%=path%>/backstage/administrator/modifyinfo.jsp">修改个人资料</a></td>

							<td>||</td>

							<td class="navigloginfuction"><a
								href="<%=path%>/order!showAdministratorOwnPhoneOrders?page=1">我的订单管理</a></td>

							<td>||</td>

							<td class="navigloginfuction"><a
								href="<%=path%>/backstage/administrator/backstagemanagement.jsp">后台管理</a></td>

							<td>||</td>

							<td class="navigloginfuction"><a href="<%=path%>/logout.jsp">注销</a></td>
						</tr>
					</table>
				</c:when>
			</c:choose>

		</div>
	</center>
</body>

</html>
