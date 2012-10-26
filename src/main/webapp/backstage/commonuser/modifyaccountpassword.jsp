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
	String location = "修改用户密码";
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
					<script language="javascript" type="text/javascript">
						window.top.location.href = "<%=path%>/pleaselogin.jsp";
					</script>
				</c:when>

				<c:when test="${sessionScope.loginAccount.privilege == 'common'}">
					<s:form name="modifyaccountpassword"
						action="commonuser!modifyAccountPassword" method="post">
						<table class="modifyaccountpasswordtable">
							<thead>
								<tr>
									<th colspan="2">在修改之前，请先输入旧密码，请务必记住您的新密码</th>
								</tr>
								<tr>
									<td colspan="2"><s:fielderror>
											<s:param>modifyaccountpassworderror</s:param>
											<s:param>modifyaccountpasswordsuccess</s:param>
										</s:fielderror></td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><s:password name="accountPassword.oldpassword"
											value="" size="30" label="请输入原密码" /></td>
								</tr>
								<tr>
									<td><s:password name="accountPassword.newpassword"
											value="" size="30" label="请输入新密码" /></td>
								</tr>
								<tr>
									<td><s:password name="accountPassword.repeatnewpassword"
											value="" size="30" label="请重复新密码" /></td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td><s:submit value="修改" /></td>
									<td><s:reset value="重置" /></td>
								</tr>
							</tfoot>
						</table>
					</s:form>
				</c:when>

				<c:otherwise>
					<script language="javascript" type="text/javascript">
						window.top.location.href = "<%=path%>/pleaselogin.jsp";
					</script>
				</c:otherwise>
			</c:choose>

		</div>
	</center>
</body>

</html>
