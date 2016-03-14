<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'jquery.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>
	<script type="text/javascript" src="js/jquery-1.4.2.min.js">
</script>
	<script>
$(function() {
	$.getJSON("system/company!ajax04", function(result) {
	alert(result.name);
	alert(result.id);
	});

});
</script>
	<body>
		<input type="checkbox" id="checkAll">
		<br />
		<br />
		<input type="checkbox" name="ids" value=1>
		<input type="checkbox" name="ids" value=2>
		<input type="checkbox" name="ids" value=3>
		<input type="checkbox" name="ids" value=4>
		<br>
	</body>
</html>
