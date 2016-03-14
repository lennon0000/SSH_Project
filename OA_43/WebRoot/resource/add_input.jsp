<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
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
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/pageStyle.css">
	</head>
	<body>

		<div id="formwrapper">
			<h3>
				添加操作资源的信息
			</h3>
			<form action="system/resource.action" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="method:add" value="">
				<s:if test="parent != null">
					<input type="hidden" name="parent.id"
						value="<s:property value="parent.id"/>">
					<input type="hidden" name="parentSn"
						value="<s:property value="parent.sn"/>">
				</s:if>

				<fieldset>
					<legend>
						操作资源基本信息
					</legend>
					<div>
						<label for="name">
							资源名称
						</label>
						<input type="text" name="name" id="name" value="${name }"
							size="30" />
						<br />
					</div>
					<div>
						<label for="sn">
							唯一标识
						</label>
						<input type="text" name="sn" id="sn" value="${sn }" size="30" />
						(助记标识符)
						<br />
					</div>
					<div>
						<label for="className">
							类名
						</label>
						<input type="text" name="className" id="className"
							value="${className }" size="60" />
						<br />
					</div>
					<div>
						<label for="orderNumber">
							排序号
						</label>
						<input type="text" name="orderNumber" id="orderNumber"
							value="${orderNumber }" size="60" />
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

