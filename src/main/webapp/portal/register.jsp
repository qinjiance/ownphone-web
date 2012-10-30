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
	String location = "注册用户";
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

					<s:form name="registerForm" theme="simple" action="register"
						method="post">
						<table class="registertable">
							<thead>
								<tr>
									<th colspan="3">请输入注册信息</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td></td>
									<th colspan="2" align="left"><s:fielderror
											fieldName="commonUserRegister.useraccount" /></th>
								</tr>
								<tr>
									<td class="inputlabel">用户名：</td>
									<td class="inputfield"><s:textfield
											name="commonUserRegister.useraccount" size="30" label="用 户 名" /></td>
									<td class="inputprompt">*（用户名只能由1-12位的英文、数字和下划线组成，且只能以下划线或英文开头）</td>
								</tr>
								<tr>
									<td></td>
									<th colspan="2" align="left"><s:fielderror
											fieldName="commonUserRegister.password" /></th>
								</tr>
								<tr>
									<td class="inputlabel">密码：</td>
									<td class="inputfield"><s:password
											name="commonUserRegister.password" value="" size="30"
											label="输入密码" /></td>
									<td class="inputprompt">*（密码只能由6-18位的英文、数字和下划线组成）</td>
								</tr>
								<tr>
									<td></td>
									<th colspan="2" align="left"><s:fielderror
											fieldName="commonUserRegister.repeatpassword" /></th>
								</tr>
								<tr>
									<td class="inputlabel">重复密码：</td>
									<td class="inputfield"><s:password
											name="commonUserRegister.repeatpassword" value="" size="30"
											label="重复密码" /></td>
									<td class="inputprompt">*（重复密码要与密码一致）</td>
								</tr>
								<tr>
									<td></td>
									<th colspan="2" align="left"><s:fielderror
											fieldName="commonUserRegister.nickname" /></th>
								</tr>
								<tr>
									<td class="inputlabel">昵称：</td>
									<td class="inputfield"><s:textfield
											name="commonUserRegister.nickname" size="30" label="用户昵称" /></td>
									<td class="inputprompt">（昵称只能由1到10个字，包括中文、英文、数字、下划线和它们中间的空格组成）</td>
								</tr>
								<tr>
									<td></td>
									<th colspan="2" align="left"><s:fielderror
											fieldName="commonUserRegister.email" /></th>
								</tr>
								<tr>
									<td class="inputlabel">电子邮箱：</td>
									<td class="inputfield"><s:textfield
											name="commonUserRegister.email" size="30" label="电子邮箱" /></td>
									<td class="inputprompt">（电子邮箱邮箱格式要正确）</td>
								</tr>
								<tr>
									<td></td>
									<th colspan="2" align="left"><s:fielderror
											fieldName="commonUserRegister.mobilephone" /></th>
								</tr>
								<tr>
									<td class="inputlabel">手机号码：</td>
									<td class="inputfield"><s:textfield
											name="commonUserRegister.mobilephone" size="30" label="手机号码" /></td>
									<td class="inputprompt">（手机号码只能由以1开头的11个数字组成）</td>
								</tr>
								<tr>
									<td></td>
									<th colspan="2" align="left"><s:fielderror
											fieldName="commonUserRegister.realname" /></th>
								</tr>
								<tr>
									<td class="inputlabel">真实姓名：</td>
									<td class="inputfield"><s:textfield
											name="commonUserRegister.realname" size="30" label="真实姓名" /></td>
									<td class="inputprompt">（中文姓名只能由中文组成，英文名只能由英文和它们中间的空格组成）</td>
								</tr>
								<tr>
									<td></td>
									<th colspan="2" align="left"><s:fielderror
											fieldName="captchatext" /></th>
								</tr>
								<tr>
									<td class="inputlabel">验证码：</td>
									<td class="inputfield"><s:textfield name="captchatext"
											size="10" label="验证码" /></td>
									<td class="inputprompt"><img id="captchaImage"
										src="<%=path%>/register!getCaptchaImage" alt="验证码" /> 
										<a href="" onclick="document.getElementById('captchaImage').src='<%=path%>/register!getCaptchaImage?id=' + Math.random(); return false;">（看不清换一张？）</a>
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td style="text-align: center; padding:10px;" colspan="2"><input type="image" src="<%=path%>/images/registernow.gif" alt="注册" /></td>
									<td style="text-align: left; padding:10px;"><s:reset value="重置" /></td>
								</tr>
							</tfoot>
						</table>
					</s:form>

				</div>

				<jsp:include page="/tailer.jsp" />
			</div>
		</div>
	</center>
</body>

</html>
