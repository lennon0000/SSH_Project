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

		<title>人员信息更新</title>

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
				更新人员的信息
			</h3>
			<form action="system/person.action" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="method:update" value="">
				<input type="hidden" name="id" value="<s:property value="id"/>">
				<input type="hidden" name="parent.id"
					value="<s:property value="parent.id"/>">
				<fieldset>
					<legend>
						人员基本信息
					</legend>
					<div>
						<label for="name">
							姓名
						</label>
						<input type="text" name="name" id="name"
							value="<s:property value="name"/>" size="30" />
						<br />
					</div>
					<div>
						<label for="code">
							员工编号
						</label>
						<input type="text" name="code" id="code"
							value="<s:property value="code"/>" size="30" />
						<br />
					</div>
					<div>
						<label for="sex">
							性别
						</label>
						<input type="text" name="sex" id="sex"
							value="<s:property value="sex"/>" size="30" />
						<br />
					</div>
					<div>
						<label for="phoneNumber">
							手机
						</label>
						<input type="text" name="phoneNumber" id="phoneNumber"
							value="<s:property value="phoneNumber"/>" size="30" />
						<br />
					</div>
					<div>
						<label for="qq">
							QQ号
						</label>
						<input type="text" name="qq" id="qq"
							value="<s:property value="qq"/>" size="30" />
						<br />
					</div>
					<div>
						<label for="msn">
							MSN
						</label>
						<input type="text" name="msn" id="msn"
							value="<s:property value="msn"/>" size="30" />
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
						<label for="duty">
							主要负责
						</label>
						<input type="text" name="duty" id="duty"
							value="<s:property value="duty"/>" size="60" />
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

	</body>
</html>

