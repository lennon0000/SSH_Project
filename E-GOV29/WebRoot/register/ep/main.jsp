<%@ page pageEncoding="UTF-8"%><%@page
	import="com.sun.xml.internal.txw2.Document"%>

<%@ include file="/common/import.jsp"%>

<html>
	<head>
		<%@ include file="/common/header.jsp"%>
		<link rel="STYLESHEET" type="text/css"
			href="<%=basePath%>register/ep/tabbar/dhtmlxtabbar.css">
		<script src="<%=basePath%>register/ep/tabbar/dhtmlxcommon.js">
</script>
		<script src="<%=basePath%>register/ep/tabbar/dhtmlxtabbar.js">
</script>
		<script type="text/javascript">
function showBar(id) {
	tabbar.enableTab(id);
	tabbar.setTabActive(id);
}
	var enterpriseId = ${param.enterpriseId};
	function setId(id) {
	ehterpriseId=id;
}
	function getId(){
		return enterpriseId;
}
</script>
	</head>
	<body>
		<div id="a_tabbar" style="width: 100%; height: 100%;" />

		<script type="text/javascript">
tabbar = new dhtmlXTabBar("a_tabbar", "top");
tabbar.setSkin('dhx_skyblue');
tabbar.setImagePath("register/ep/tabbar/imgs/");
tabbar.addTab("a1", "基础信息", "100px");
tabbar.addTab("a2", "负责人信息", "100px");
tabbar.addTab("a3", "股东信息", "100px");
tabbar.addTab("a4", "董事会成员", "100px");
tabbar.addTab("a5", "文档资料", "100px");
tabbar.setHrefMode("iframes-on-demand");
tabbar
		.setContentHref("a1",
				"<%=basePath%>register/ep/EnterpriseServlet?m=listEn&acceptNo=${acceptNo}");
tabbar.setContentHref("a2", "<%=basePath%>register/ep/in_charge.jsp");
tabbar
		.setContentHref("a3",
				"<%=basePath%>register/ep/EnterpriseServlet?m=listSh&acceptNo=${acceptNo}");
tabbar.setContentHref("a4", "<%=basePath%>register/ep/directorate.jsp");
tabbar.setContentHref("a5", "<%=basePath%>register/ep/file/list.jsp");
tabbar.setTabActive("a1");

//if(${param.enterpriseId}==0){
		tabbar.disableTab("a2");
		//tabbar.disableTab("a3");
		tabbar.disableTab("a4");
		//tabbar.disableTab("a5");
//}
			
		</script>
	</body>
</html>
