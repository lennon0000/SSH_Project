<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test_tree.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="js/jquery.jstree.js"></script>
<script language="javascript">
	//jQuery().ready(function(){
	//	jQuery('#navigation').accordion({
	//		header: '.head',
	//		event: 'click',
	//		fillSpace: true,
	//		animated: 'bounceslide'
	//	});
	//});
	$(function(){
		$("#menuContainer").jstree({
			"plugins" : ["themes","html_data"]
		});
	});
</script>
  </head>
  
  <body>
<div id="menuContainer"  style="height:100%;">
  <ul id="navigation">
    <li> <a class="head">文章管理</a>
      <ul>
        <li><a href="article.action" target="rightFrame">查询文章</a></li> 
        <li><a href="article!addInput.action" target="rightFrame">添加文章</a></li>
        <li><a href="CommentAdminServlet" target="rightFrame">留言管理</a></li>
      </ul>
    </li>
    <li> <a class="head">频道管理</a>
      <ul>
        <li><a href="channel.action" target="rightFrame">查询频道</a></li>
        <li><a href="channel!addInput.action" target="rightFrame">添加频道</a></li>
      </ul>
    </li>
    <li> <a class="head">爬虫管理</a>
      <ul>
        <li><a href="#" target="rightFrame">设定爬虫规则</a></li>
        <li><a href="SpiderServlet" target="rightFrame">爬虫文章管理</a></li>
      </ul>
    </li>
    <li> <a class="head">会员管理</a>
      <ul>
        <li><a href="MemberAdminServlet" target="rightFrame">会员管理</a></li>
      </ul>
    </li>
    <li> <a class="head">系统管理</a>
      <ul>
        <li><a href="#" target="rightFrame">修改管理员密码</a></li>
        <li><a href="#" target="rightFrame">修改数据库配置</a></li>
        <li><a href="#" target="rightFrame">备份网站内容</a></li>
        <li><a href="#" target="rightFrame">导入网站内容</a></li>
      </ul>
    </li>
  </ul>
</div>
  </body>
</html>
