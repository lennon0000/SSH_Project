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

		<title>My JSP 'jquery.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>
	<script type="text/javascript" src="js/jquery-1.4.2.min.js">
</script>
	<script type="text/javascript" src="js/jquery.jstree.js"></script>
<script type="text/javascript">
$(function() {
	$("#partyTree").jstree({
		
		plugins:["json_data","themes","ui"],
		themes:{
			theme:"apple"
		},
		json_data:{
			ajax:{
			url:"system/party!tree"
			}
		}
	});
	$("#partyTree").bind("loaded.jstree",function(){
		$("#partyTree").jstree("open_all",-1);
	});
	$("#partyTree").bind("select_node.jstree",function(e,data){
	var partyId = data.rslt.obj.attr("id");
	var partyName = data.rslt.obj.attr("name");
	alert(partyId);
	alert(partyName);
	});
});
</script>
	<body>
		<div id="partyTree" style="height: 100%;">
		</div>
	</body>
</html>
