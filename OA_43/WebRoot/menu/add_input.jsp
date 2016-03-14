<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'deptAddInput.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js"></script>
	<script type="text/javascript" src="js/jquery.jstree.js"></script>
	<link rel="stylesheet" type="text/css"
			href="css/pageStyle.css">
  </head>
  
  <body>

<div id="formwrapper">
	<h3>添加菜单的信息</h3>
	<form action="system/menu.action" method="post" enctype="multipart/form-data">
	<input type="hidden" name="method:add" value="">
	<input type="hidden" name="parent.id" value="<s:property value="id"/>">
	<fieldset>
		<legend>菜单基本信息
		</legend>
		<div>
			<label for="name">名称</label>
			<input type="text" name="name" id="name" value="" size="30"  /> 
			<br />	
		</div>
		<div>
			<label for="href">链接地址</label>
			<input type="text" name="href" id="href" value="" size="60"  /> 
			<br />	
		</div>
		<div>
			<label for="orderNumber">排序号</label>
			<input type="text" name="orderNumber" id="orderNumber" value="" size="15"  /> 
			<br />	
		</div>
		<div>
			<label for="sn">编号</label>
			<input type="text" name="sn" id="sn" value="" size="30"  /> 
			<br />	
		</div>
		<div class="enter">
		    <input name="submit" type="submit" class="buttom" value="提交" />
		    <input name="reset" type="reset" class="buttom" value="重置" />
		</div>		
	</fieldset>
	</form>
</div>
</body>
</html>
