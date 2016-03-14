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

		<title>人员管理</title>

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
<script type="text/javascript">
var parentId = 0;
</script>
	<body>
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

