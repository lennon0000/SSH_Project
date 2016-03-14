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

		<title>岗位信息更新</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" type="text/css"
			href="js/datatable/css/demo_table.css">
		<link rel="stylesheet" type="text/css"
			href="js/datatable/css/demo_page.css">
		<link rel="stylesheet" type="text/css"
			href="css/pageStyle.css">
	</head>
	<script type="text/javascript" src="js/jquery-1.4.2.min.js">
</script>
	<script type="text/javascript" src="js/jquery.dataTables.min.js">
</script>
	<script type="text/javascript" src="js/person_table.js">
</script>
<script type="text/javascript">
var parentId = <s:property value="id"/>;
</script>
	<body>

		<div id="formwrapper">
			<h3>
				更新岗位的信息
			</h3>
			<div style="color: red">
				<s:property value="#delInfor" />
			</div>
			<form action="system/position.action" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="method:update" value="">
				<input type="hidden" name="id" value="<s:property value="id"/>">
				<input type="hidden" name="parent.id"
					value="<s:property value="parent.id"/>">
				<fieldset>
					<legend>
						岗位基本信息
						<input type="button" value="添加人员" class="buttom"
							onclick="window.location = 'system/person!addInput.action?parent.id=<s:property value="id"/>'" />
						<input type="button" value="删除本岗位" class="buttom"
							onclick="window.location = 'system/position!del.action?id=<s:property value="id"/>'" />
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
		<!-- 显示party下面的人员信息 -->
		<div>
			<table id="personList" class="display" width="100%" border="0"
				cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
				<thead>
					<tr>
						<th width="10" height="20" bgcolor="d3eaef" class="STYLE6">
							<div align="center">
								<span class="STYLE10">ID</span>
							</div>
						</th>
						<th width="60" height="20" bgcolor="d3eaef" class="STYLE6">
							<div align="center">
								<span class="STYLE10">姓名</span>
							</div>
						</th>
						<th width="25" height="20" bgcolor="d3eaef" class="STYLE6">
							<div align="center">
								<span class="STYLE10">性别</span>
							</div>
						</th>
						<th width="50" height="20" bgcolor="d3eaef" class="STYLE6">
							<div align="center">
								<span class="STYLE10">电话</span>
							</div>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
			<td colspan="4"></td>
		</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>

