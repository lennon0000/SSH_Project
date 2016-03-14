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
		<title>添加或更新公司信息</title>
		<script type="text/javascript" src="js/jquery-1.4.2.min.js">
</script>
		<script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js">
</script>
		<script type="text/javascript" src="js/jquery.jstree.js">
</script>
		<link rel="stylesheet" type="text/css" href="css/pageStyle.css">
	</head>
	<body>

		<div id="formwrapper">
			<h3>
				请设置公司的基本信息
			</h3>
			<form action="system/company" method="post">
				<input type="hidden" name="method:save" value="">
				<input type="hidden" name="id" value="<s:property value="id"/>">
				<fieldset>
					<legend>
						公司基本信息设置（在进行组织机构设置之前，请先设置公司的基本信息）
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
					
						<input name="submit" type="submit" class="buttom" value="保存公司基本信息" />

						<input name="reset" type="reset" class="buttom" value="重置" />
					</div>
				</fieldset>
			</form>
		</div>

	</body>
</html>

