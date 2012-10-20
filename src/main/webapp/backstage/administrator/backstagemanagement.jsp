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
	String location = "后台管理";
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
							<div style="height: 250px;"></div>

							<table>
								<tr>
									<td class="navigloginfuction"><a
										href="<%=path%>/backstage/administrator/statisticpage.jsp">服务器统计信息</a>
									</td>
								</tr>
							</table>

							<div style="height: 250px;"></div>
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
