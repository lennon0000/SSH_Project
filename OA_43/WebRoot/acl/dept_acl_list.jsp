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

		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>OA办公自动化平台</title>
		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow: hidden;
}
-->
</style>
		<script type="text/javascript" src="js/jquery-1.4.2.min.js">
</script>
		<script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js">
</script>
		<script type="text/javascript" src="js/jquery.jstree.js">
</script>
		<script type="text/javascript">

$(function() {

	//计算出当前页面的高度
	var clientHeight;
	if ($.browser.msie) { //如果是IE浏览器，使用下面的方法获取页面高度
		clientHeight = document.body.clientHeight;
	} else { //其它浏览器，使用下面的方法获取页面高度
		clientHeight = self.innerHeight;
	}

	//将菜单的DIV设置为当前页面的高度（一个绝对值）
	$("#partyTree").height(clientHeight);

	//设置菜单DIV的宽度
	$("#partyTree").width(220);
	//设置菜单的左右滚动条自动显示
	$("#partyTree").css("overflow", "auto");
});

$(function() {

	$("#partyTree").jstree( {

		plugins : [ "json_data", "themes", "ui" ],
		themes : {
			theme : "classic"
		},
		json_data : {
			ajax : {
				url : "system/acl!deptTree"
			}
		}
	});
	$("#partyTree").bind("loaded.jstree", //当组织机构树加载完毕，触发本事件被执行
			function(event, data) {
				//默认打开所有的节点
			$("#partyTree").jstree("open_all", -1);
		});
	$("#partyTree").bind("select_node.jstree", function(e, data) {
		//得到<li>标签上面定义的partyId属性的值
			var partyId = data.rslt.obj.attr("id");
			//得到<li>标签上面定义的partyType属性的值
			var partyType = data.rslt.obj.attr("objectType");
			$("#rightFrame")
					.attr(
							"src",
							"system/acl!allMenuResource.action?principalId="+partyId+"&principalType="+partyType);
		});
});
function refresh() {
	//调用refresh方法，刷新整棵组织机构树
	$("#partyTree").jstree("refresh", "#partyTree");
}
</script>
	</head>

	<body>
		<table width="100%" height="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td width="147" id="leftMenu" valign="top">
					<div id="partyTree">
					</div>
				</td>
				<td width="5" bgcolor="#add2da">
					&nbsp;
				</td>
				<td valign="top">
					<iframe height="100%" width="100%" frameborder="0" frameborder="0"
						src="party/right.jsp" name="rightFrame" id="rightFrame"
						title="rightFrame"></iframe>
				</td>

			</tr>
		</table>
	</body>
</html>