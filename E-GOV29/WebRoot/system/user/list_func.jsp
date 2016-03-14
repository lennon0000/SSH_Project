<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'add_input.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="../css/style.css">
		<script type="text/javascript">
function checkParent(p,ccount) {
	var pid = p.id;
	//alert(pid);
	for ( var i = 0; i < ccount; i++) {
		document.getElementById(pid + "_" + i).checked = p.checked;
	}
}
function checkChild(c,ccount) {
	var cid = c.id;
	//alert(pid);
	var pid = cid.substring(0, 6);
	var flag = false;
	for ( var i = 0; i < ccount; i++) {
		if (document.getElementById(pid + "_" + i).checked) {
			flag = true;
			break;
		}
	}
	document.getElementById(pid).checked = flag;
}
</script>
	</head>

	<body>

		<form action="system/UserServlet" method="post">
			<input type="hidden" name="m" value="addPermission">
			<input type="hidden" name="userId" value="${param.id}">
			<input type="hidden" name="m" value="add">

			<table width="60%" border="1" align="center" cellpadding="1"
				cellspacing="0" bordercolor="#0099cc"
				style="line-height: 1.4; border-collapse: collapse"
				bgcolor="#ffffcc">
				<tr bgcolor="DEDEDE" height="27" class="header">
					<td align="center" width=35%>
						功能
					</td>
					<td align="center" width=65%>
						模块
					</td>
				</tr>
				<c:forEach items="${funcs}" var="parent">
					<tr class="data" height="27">
						<td align=left width=35%>
							<input type="checkbox" value="${parent.id}" name="funcIds"
								${parent.checked} id="p_${parent.id}"
								onclick="checkParent(this,${fn:length(parent.children)})">
							${parent.name}
						</td>
						<td align=left width=65%>
							<c:forEach items="${parent.children}" var="child"
								varStatus="stat">
								<input type="checkbox" value="${child.id}" name="funcIds"
									${child.checked} id="p_${parent.id}_${stat.index}"
									onclick="checkChild(this,${fn:length(parent.children)})">
							${child.name}
							</c:forEach>
						</td>
					</tr>
				</c:forEach>


			</table>
			<table width=95%>
				<tr>
					<td align=center>
						<input type="submit" value="提交">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
