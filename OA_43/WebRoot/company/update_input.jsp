<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>更改公司信息</title>
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
	<script type="text/javascript" src="js/person_table.js">
</script>
	<script type="text/javascript">
var parentId= <s:property value="id"/>;
</script>
	<body>

		<div id="formwrapper">
			<h3>
				更新公司的信息
			</h3>
			<div style="color: red">
				<s:property value="#delInfor" />
			</div>
			<form action="system/company.action" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="method:update" value="">
				<input type="hidden" name="id" value="<s:property value="id"/>">


				<fieldset>
					<legend>
						公司基本信息

						<input type="button" value="添加下级部门" class="buttom"
							onclick="window.location = 'system/dept!addInput.action?parent.id=<s:property value="id"/>'" />


						<input type="button" value="添加子公司" class="buttom"
							onclick="window.location = 'system/company!addInput.action?parent.id=<s:property value="id"/>'" />


						<input type="button" value="删除公司信息" class="buttom"
							onclick="window.location = 'system/company!del.action?id=<s:property value="id"/>'" />

					</legend>
					<div>
						<label for="name">
							名称
						</label>
						<input type="text" name="name" id="name"
							value="<s:property value="name"/>" size="60" />
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
						<label for="faxNumber">
							传真
						</label>
						<input type="text" name="faxNumber" id="faxNumber"
							value="<s:property value="faxNumber"/>" size="30" />
						<br />
					</div>
					<div>
						<label for="address">
							地址
						</label>
						<input type="text" name="address" id="address"
							value="<s:property value="address"/>" size="60" />
						<br />
					</div>
					<div>
						<label for="postCode">
							邮编
						</label>
						<input type="text" name="postCode" id="postCode"
							value="<s:property value="postCode"/>" size="20" />
						<br />
					</div>
					<div>
						<label for="website">
							网站
						</label>
						<input type="text" name="website" id="website"
							value="<s:property value="website"/>" size="30" />
						<br />
					</div>
					<div>
						<label for="email">
							电子邮件
						</label>
						<input type="text" name="email" id="email"
							value="<s:property value="email"/>" size="30" />
						<br />
					</div>
					<div>
						<label for="trade">
							所属行业
						</label>
						<input type="text" name="trade" id="trade"
							value="<s:property value="trade"/>" size="30" />
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
						<big><div align="center" style="color: red;">
								<s:property value="infor" />
							</div> </big>
						<input name="submit" type="submit" class="buttom" value="保存公司基本信息" />

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

