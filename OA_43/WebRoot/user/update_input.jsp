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

		<title>更新用户</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

	<script type="text/javascript" src="js/jquery-1.4.2.min.js">
</script>
	<script type="text/javascript" src="js/jquery.dataTables.min.js">
</script>
	<script type="text/javascript" src="js/person_table_person.js">
</script>
<link rel="stylesheet" type="text/css" href="css/pageStyle.css">
<body>

<div id="formwrapper">
	<h3>请设置人员的登录帐号</h3>
	<form action="system/user.action" method="post">
	<input type="hidden" name="method:update" value="">
	<input type="hidden" name="id" value="<s:property value="id"/>">
	<input type="hidden" name="person.id" value="<s:property value="person.id"/>">
	<fieldset>
		<legend>请设置人员的登录帐号
		</legend>
		<div>
			<label for="userName">登录帐号</label>
			<input type="text" name="userName" id="userName" value="<s:property value="userName"/>" size="30"  /> 
			<br />	
		</div>
		<div>
			<label for="password">登录密码</label>
			<input type="text" name="password" id="password" value="<s:property value="password"/>" size="30"  /> 
			<br />	
		</div>
		<div>
			<label for="roles">分配角色</label>
			 <select name="roleIds" multiple="multiple" >
			
			 <s:iterator  value="#roles" >
			
			 <option value="<s:property value="id"/>" <s:property value="status"/>><s:property value="name"/></option>
			 
			 </s:iterator>
			 
			 </select>
			<br />	
		</div>
<s:debug></s:debug>
		<div class="enter">
		    <input name="submit" type="submit" class="buttom" value="更新登录帐号" />
		    <input name="reset" type="reset" class="buttom" value="重置" />
		</div>		
	</fieldset>
	</form>
</div>

</body>
</html>

