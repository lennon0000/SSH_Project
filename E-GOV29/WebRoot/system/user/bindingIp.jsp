<%@ page  pageEncoding="UTF-8"%><%@page import="com.sun.xml.internal.txw2.Document"%>

<%@ include file="/common/import.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<%@ include file="/common/header.jsp" %>
	<table width="100%" border="0" align="center">
			<tr height=27>
				<td width="100%" align="left" class="title"
					background="images/title_bg.gif">
					当前位置： 个人控制台&gt;绑定IP

				</td>
			</tr>
		</table>
		<base href="<%=basePath%>">

		<title>修改账户IP</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="/css/style.css">
		<script type="text/javascript">
		
		
	
		</script>
	</head>

	<body>

		<form action="Index" method="post">
			<input type="hidden" value="bindingIp" name="m">

			<table width="60%" border="1" align="center" cellpadding="1"
				cellspacing="0" bordercolor="#0099cc"
				style="line-height: 1.4; border-collapse: collapse"
				bgcolor="#ffffcc">
				
				<tr>
					<td align=right width=20%class="header" height="27">
						当前IP：
					</td>
					<td align=left width=18%>
						<input type = "text" name="ip" value="${ip}">
					</td>
					
			</table>
			<table width=95%>
				<tr>
					<td align=center>
						<input type="submit" value="确认绑定">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
