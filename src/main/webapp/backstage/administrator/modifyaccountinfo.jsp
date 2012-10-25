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
	String location = "修改用户信息";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/style1.css" />
<title><%=location%></title>

<s:head />
</head>

<body>
	<center>
		<div id="mainlayout">
			<c:choose>
				<c:when test="${sessionScope.loginAccount == null}">
					<%
						response.sendRedirect(path + "/pleaselogin_iframe.jsp");
					%>
				</c:when>

				<c:when test="${sessionScope.loginAccount.privilege == 'admin'}">
					<s:form name="modifyaccountinfo"
						action="administrator!modifyAccountInfo" method="post">
						<table class="modifyaccountinfotable">
							<thead>
								<tr>
									<th colspan="2">请认真填写您的用户信息，以保证我们可以联系到您</th>
								</tr>
								<tr>
									<td colspan="2"><s:fielderror>
											<s:param>modifyaccountinfoerror</s:param>
											<s:param>modifyaccountinfosuccess</s:param>
										</s:fielderror></td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><s:textfield name="accountInfo.nickname"
											value="%{#session.loginAccount.nickname}" size="30"
											label="昵称" /></td>
								</tr>
								<tr>
									<td><s:textfield name="accountInfo.email"
											value="%{#session.loginAccount.email}" size="30" label="电子邮箱" /></td>
								</tr>
								<tr>
									<td><s:textfield name="accountInfo.mobilephone"
											value="%{#session.loginAccount.mobilephone}" size="30"
											label="手机号码" /></td>
								</tr>
								<tr>
									<td><s:textfield name="accountInfo.realname"
											value="%{#session.loginAccount.realname}" size="30"
											label="真实姓名" /></td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td><s:submit value="修改" /></td>
									<td><s:reset value="恢复" /></td>
								</tr>
							</tfoot>
						</table>
					</s:form>
				</c:when>

				<c:otherwise>
					<%
						response.sendRedirect(path + "/pleaselogin_iframe.jsp");
					%>
				</c:otherwise>
			</c:choose>

		</div>
	</center>
</body>

</html>
