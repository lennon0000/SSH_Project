<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'add_input.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="../css/style.css">

	</head>

	<body>

		<form action="system/UserServlet" method="post">
			<input type="hidden" name="m" value="update">
			<input type="hidden" name="id" value="${user.id}">
			<table width="95%" border="1" align="center" cellpadding="1"
				cellspacing="0" bordercolor="#0099cc"
				style="line-height: 1.4; border-collapse: collapse"
				bgcolor="#ffffcc">
				<tr>
					<td align=right width=20%>
						登录用户名：
					</td>
					<td align=left width=30%>
						<input name="username" value="${user.username}">
					</td>
					<td align=right width=20%>
						用户姓名：
					</td>
					<td align=left width=30%>
						<input name="name" value="${user.name}">
					</td>
				</tr>
				<tr>
					<td align=right width=20%>
						所属工商机关：
					</td>
					<td align=left width=30%>

						<select name="admcode">
							<c:forEach items="${adms}" var="adm">
								<option value="${adm.code}" <c:if test="${adm.code eq user.adm.code}">selected</c:if> >${adm.value}[${adm.code}]</option>
							</c:forEach>
						</select>

					</td>
					<td align=right width=20%>
						部门：
					</td>
					<td align=left width=30%>
						<input name="dept" value="${user.dept}">
					</td>
				</tr>
			</table>
			<table width=95%>
				<tr>
					<td align=center>
						<input type="submit" value="提交">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
