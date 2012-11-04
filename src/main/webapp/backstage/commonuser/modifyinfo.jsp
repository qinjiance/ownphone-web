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
	String location = "修改个人资料";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/style1.css" />
<script type="text/javascript" src="<%=path%>/js/Iframe.js"></script>
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
							<div>
								<ul class="modifyinfolist">
									<li><a
										href="<%=path%>/backstage/commonuser/modifyaccountinfo.jsp"
										target="modifyinfo_iframe">修改用户信息</a></li>
									<li><a
										href="<%=path%>/backstage/commonuser/modifyaccountpassword.jsp"
										target="modifyinfo_iframe">修改用户密码</a></li>
								</ul>
							</div>

							<div>
								<iframe id="modifyinfoiframe" name="modifyinfo_iframe"
									scrolling="no" frameborder="0"
									src="<%=path%>/backstage/commonuser/modifyaccountinfo.jsp"
									onload="Javascript:SetIframeHeight(this)"></iframe>
							</div>
						</c:when>

						<c:otherwise>
							<%
								response.sendRedirect(path + "/pleaselogin.jsp");
							%>
						</c:otherwise>
					</c:choose>

					<div class="clear"></div>

				</div>

				<jsp:include page="/tailer.jsp" />
			</div>
		</div>
	</center>
</body>

</html>
