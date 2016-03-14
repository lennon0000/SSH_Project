<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  
		<form  action="system/DicServlet" method="post">
		<input type="hidden" name="m" value="add" >
		<input type="hidden" name="type" value="${param.type}">
		
		<table width="95%" border="1" align="center" cellpadding="1"
			cellspacing="0" bordercolor="#0099cc"
			style="line-height: 1.4; border-collapse: collapse" bgcolor="#ffffcc">
			<tr>
				<td align=right width=20%>
					字典代码：
				</td>
				<td align=left width=30%>
					<input name="code" value="">
				</td>
				<td align=right width=20%>
					字典项名称：
				</td>
				<td align=left width=30%>
					<input name="value" value="" >
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
