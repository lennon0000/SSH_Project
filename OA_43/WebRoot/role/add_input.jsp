<%@ taglib prefix="s" uri="/struts-tags"%>
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

		<title>添加角色</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" type="text/css"
			href="js/datatable/css/demo_table.css">
		<link rel="stylesheet" type="text/css"
			href="js/datatable/css/demo_page.css">
		<link rel="stylesheet" type="text/css" href="css/pageStyle.css">
	</head>
	<script type="text/javascript" src="js/jquery-1.4.2.min.js">
</script>
	<script type="text/javascript" src="js/jquery.dataTables.min.js">
</script>
	<script type="text/javascript" src="js/person_table_person.js">
</script>
<body>

<div id="formwrapper">
	<h3>请设置角色信息</h3>
	<form action="system/role.action" method="post">
	<input type="hidden" name="method:add" value="">
	<fieldset>
		<legend>请设置角色信息
		</legend>
		<div>
			<label for="name">角色名称</label>
			<input type="text" name="name" id="name" value="" size="30"  /> 
			<br />	
		</div>
		<div class="enter">
		    <input name="submit" type="submit" class="buttom" value="添加角色" />
		    <input name="reset" type="reset" class="buttom" value="重置" />
		</div>		
	</fieldset>
	</form>
</div>

</body>
</html>

