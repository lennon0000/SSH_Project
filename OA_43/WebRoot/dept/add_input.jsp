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

		<title>添加子部门信息</title>

		<script type="text/javascript" src="js/jquery-1.4.2.min.js">
</script>
		<script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js">
</script>
		<script type="text/javascript" src="js/jquery.jstree.js">
</script>
		<script type="text/javascript" src="js/jquery.dataTables.min.js">
</script>

		<link rel="stylesheet" type="text/css" href="css/pageStyle.css">
	</head>
	<body>

		<div id="formwrapper">
			<h3>
				更新部门的信息
			</h3>
			<form action="system/dept.action" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="method:add" value="">
				<input type="hidden" name="id" value="2">
				<input type="hidden" name="parent.id"
					value="<s:property value="parent.id"/>">
				<fieldset>
					<legend>
						部门基本信息
					</legend>
					<div>
						<label for="name">
							名称
						</label>
						<input type="text" name="name" id="name" value="" size="30" />
						<br />
					</div>
					<div>
						<label for="code">
							部门编号
						</label>
						<input type="text" name="code" id="code" value="" size="30" />
						<br />
					</div>
					<div>
						<label for="phoneNumber">
							电话
						</label>
						<input type="text" name="phoneNumber" id="phoneNumber" value=""
							size="30" />
						<br />
					</div>
					<div>
						<label for="description">
							描述
						</label>
						<input type="text" name="description" id="description" value=""
							size="60" />
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

