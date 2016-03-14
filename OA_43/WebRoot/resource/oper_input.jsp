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
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/pageStyle.css">
	</head>
	<body>

		<div id="formwrapper">
			<h3>
				增加操作的信息
			</h3>
			<form action="system/resource.action" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="method:addOper" value="">

				<!-- 资源ID -->
				<input type="hidden" name="id" value="<s:property value="id"/>">

				<fieldset>
					<legend>
						操作的基本信息
					</legend>
					<div>
						<label for="operName">
							操作名称
						</label>
						<input type="text" name="operName" id="operName"
							value="${operName }" size="30" />
						<br />
					</div>
					<div>
						<label for="operSn">
							操作标识
						</label>
						<input type="text" name="operSn" id="operSn" value="${operSn }"
							size="30" />
						<br />
					</div>
					<div>
						<label for="methodName">
							方法名
						</label>
						<input type="text" name="methodName" id="methodName"
							value="${methodName }" size="30" />
						<br />
					</div>
					<div>
						<label for="operIndex">
							操作索引
						</label>
						<input type="text" name="operIndex" id="operIndex"
							value="${operIndex }" size="5" />
						(在同一个资源中，请保证操作索引不重复，且小于或等于31)
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

