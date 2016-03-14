<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

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

		<title>更新部门信息</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" type="text/css"
			href="js/datatable/css/demo_table.css">
		<link rel="stylesheet" type="text/css"
			href="js/datatable/css/demo_page.css">
		<style type="text/css">
body {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #666666;
	background: #fff;
	text-align: center;
}

* {
	margin: 0;
	padding: 0;
}

a {
	color: #1E7ACE;
	text-decoration: none;
}

a:hover {
	color: #000;
	text-decoration: underline;
}

h3 {
	font-size: 14px;
	font-weight: bold;
}

pre,p {
	color: #1E7ACE;
	margin: 4px;
}

input,select,textarea {
	padding: 1px;
	margin: 2px;
	font-size: 12px;
}

.buttom {
	padding: 1px 10px;
	font-size: 12px;
	border: 1px #1E7ACE solid;
	background: #D0F0FF;
}

#formwrapper {
	width: 95%;
	margin: 15px auto;
	padding: 20px;
	text-align: left;
}

fieldset {
	padding: 10px;
	margin-top: 5px;
	border: 1px solid #1E7ACE;
	background: #fff;
}

fieldset legend {
	color: #1E7ACE;
	font-weight: bold;
	background: #fff;
}

fieldset label {
	float: left;
	width: 60px;
	text-align: right;
	padding: 4px;
	margin: 1px;
}

fieldset div {
	clear: left;
	margin-bottom: 2px;
}

.enter {
	text-align: center;
}

.clear {
	clear: both;
}
-->
</style>
	</head>
	<script type="text/javascript" src="js/jquery-1.4.2.min.js">
</script>
	<script type="text/javascript" src="js/jquery.dataTables.min.js">
</script>
	<script type="text/javascript" src="js/person_table.js">
</script>
	<body>
		<div id="formwrapper">
			<h3>
				更新部门的信息
			</h3>
			<div style="color: red">
				<s:property value="#delInfor" />
			</div>
			<form action="system/dept.action" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="method:update" value="">
				<input type="hidden" name="id" value="<s:property value="id"/>">
				<input type="hidden" name="parent.id"
					value="<s:property value="parent.id"/>">
				<fieldset>
					<legend>
						部门基本信息
						<input type="button" value="添加下级部门" class="buttom"
							onclick="window.location = 'system/dept!addInput.action?parent.id=<s:property value="id"/>'" />
						<input type="button" value="添加岗位" class="buttom"
							onclick="window.location = 'system/position!addInput.action?parent.id=<s:property value="id"/>'" />
						<input type="button" value="添加人员" class="buttom"
							onclick="window.location = 'system/person!addInput.action?parent.id=<s:property value="id"/>'" />
						<input type="button" value="删除本部门" class="buttom"
							onclick="window.location = 'system/dept!del.action?id=<s:property value="id"/>'" />
					</legend>
					<div>
						<label for="name">
							名称
						</label>
						<input type="text" name="name" id="name"
							value="<s:property value="name"/>" size="30" />
						<br />
					</div>
					<div>
						<label for="code">
							部门编号
						</label>
						<input type="text" name="code" id="code"
							value="<s:property value="code"/>" size="30" />
						<br />
					</div>
					<div>
						<label for="phoneNumber">
							电话
						</label>
						<input type="text" name="phoneNumber" id="phoneNumber"
							value="<s:property value="phoneNumber"/>" size="30" />
						<br />
					</div>
					<div>
						<label for="description">
							描述
						</label>
						<input type="text" name="description" id="description"
							value="<s:property value="description"/>" size="60" />
						<br />
					</div>
					<div class="enter">
						<input name="submit" type="submit" class="buttom" value="提交" />
						<input name="reset" type="reset" class="buttom" value="重置" />
					</div>
				</fieldset>
			</form>
		</div>
		<div>
			<table id="personList" class="display">
				<thead>
					<tr>
						<th>
							ID
						</th>
						<th>
							姓名
						</th>
						<th>
							性别
						</th>
						<th>
							电话
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#persons">
						<tr>
							<td>
								<s:property value="id" />
							</td>
							<td>
								<s:property value="name" />
							</td>
							<td>
								<s:property value="sex" />
							</td>
							<td>
								<s:property value="phoneNumber" />
							</td>

						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</body>
</html>
