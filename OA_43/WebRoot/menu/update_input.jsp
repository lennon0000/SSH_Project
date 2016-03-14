<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css"
			href="css/pageStyle.css">
  </head>
  
  <body>

<div id="formwrapper">
	<h3>更新菜单的信息</h3>
	<div style="color: red">
				<s:property value="#delInfor" />
			</div>
	<form action="system/menu.action" method="post" enctype="multipart/form-data">
	<input type="hidden" name="method:update" value="">
	<input type="hidden" name="id" value="<s:property value="id"/>">
	<fieldset>
		<legend>菜单基本信息
		<input type="button" value="添加下级菜单" onclick="window.location = 'system/menu!addInput.action?id=<s:property value="id"/>'"/>
		<input type="button" value="删除菜单信息" onclick="window.location = 'system/menu!del.action?id=<s:property value="id"/>'"/>
		</legend>
		<div>
			<label for="name">名称</label>
			<input type="text" name="name" id="name" value="<s:property value="name"/>" size="30"  /> 
			<br />	
		</div>
		<div>
			<label for="href">链接地址</label>
			<input type="text" name="href" id="href" value="<s:property value="href"/>" size="60"  /> 
			<br />	
		</div>
		<div>
			<label for="orderNumber">排序号</label>
			<input type="text" name="orderNumber" id="orderNumber" value="<s:property value="orderNumber"/>" size="15"  /> 
			<br />	
		</div>
		<div>
			<label for="sn">编号</label>
			<input type="text" name="sn" id="sn" value="<s:property value="sn"/>" size="30"  /> 
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
