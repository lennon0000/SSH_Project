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

		<title>修改账户密码</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="/css/style.css">
		<script type="text/javascript">
		
		if(${error ne null}){
			alert("${error}");
		}
		function form_submit(){
			var pwd1=document.getElementById("password3").value;
			var pwd2=document.getElementById("password2").value;
			if((trim(pwd1).length<10) || (pwd1!=pwd2)){
				alert("密码两遍输入不一致");
				document.getElementById("password2").focus();
				return false;
			}
		}
		
	
		</script>
	</head>

	<body>
</tr></tr></tr></tr></tr></tr></tr></tr>
		<form action="Index" method="post" onsubmit="return form_submit()";>
			<input type="hidden" value="modifyPassword" name="m">

			<table width="60%" border="1" align="center" cellpadding="1"
				cellspacing="0" bordercolor="#0099cc"
				style="line-height: 1.4; border-collapse: collapse"
				bgcolor="#ffffcc">
				
				<tr>
					<td align=right width=20% class="header">
						请输入初始密码：
					</td>
					<td align=left width=18%>
						<input type = "password" name="password1" id="password1" value="">
					</td>
				<tr>
					<td align=right width=20% class="header">
						请输入新密码：
					</td>
					<td align=left width=18%>
						<input type = "password" name="password2" value="" id="password2">
					</td>
				</tr>
				<tr>
					<td align=right width=20% class="header">
						请再次输入新密码：
					</td>
					<td align=left width=18%>
						<input type = "password"  name="password3" value="" id="password3">
					</td>
				</tr>
			</table>
			<table width=95%>
				<tr>
					<td align=center>
						<input type="submit" value="确认修改">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
