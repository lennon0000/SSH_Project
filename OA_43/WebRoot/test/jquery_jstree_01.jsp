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
	$("#menuContainer").jstree({
		
		plugins:["html_data","themes"],
		themes:{
			theme:"apple"
		}
	});
});
</script>
	<body>
		<div id="menuContainer" style="height: 100%;">
			<ul id="navigation">
				<li>
					<a href="#">个人办公</a>
					<ul>
						<li>
							<a href="#">桌面</a>
							<ul>
								<li>
									<a href="#">我的桌面</a>
								</li>
								<li>
									<a href="#">桌面配置</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#">任务计划</a>
							<ul>
								<li>
									<a href="#">创建计划</a>
								</li>
								<li>
									<a href="#">查询计划</a>
								</li>
								<li>
									<a href="#">计划分类</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#">日程安排</a>
							<ul>
								<li>
									<a href="#">创建日程</a>
								</li>
								<li>
									<a href="#">查询日程</a>
								</li>
								<li>
									<a href="#">日程分类</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#">日志管理</a>
							<ul>
								<li>
									<a href="#">创建日志</a>
								</li>
								<li>
									<a href="#">我的日志</a>
								</li>
								<li>
									<a href="#">共享日志</a>
								</li>
								<li>
									<a href="#">日志分类</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#">工作报告</a>
							<ul>
								<li>
									<a href="#">编写报告</a>
								</li>
								<li>
									<a href="#">查询报告</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#">电子邮件</a>
							<ul>
								<li>
									<a href="#">收件箱</a>
								</li>
								<li>
									<a href="#">发件箱</a>
								</li>
								<li>
									<a href="#">参数配置</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#">消息管理</a>
							<ul>
								<li>
									<a href="#">发送消息</a>
								</li>
								<li>
									<a href="#">已接收消息</a>
								</li>
								<li>
									<a href="#">已发送消息</a>
								</li>
								<li>
									<a href="#">已删除消息</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#">手机短信</a>
							<ul>
								<li>
									<a href="#">发送手机短信</a>
								</li>
								<li>
									<a href="#">已接收短信</a>
								</li>
								<li>
									<a href="#">已发送短信</a>
								</li>
								<li>
									<a href="#">短信网关配置</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#">通信录</a>
							<ul>
								<li>
									<a href="#">私人通信录</a>
								</li>
								<li>
									<a href="#">公共通信录</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#">个人设置</a>
							<ul>
								<li>
									<a href="#">个人信息</a>
								</li>
								<li>
									<a href="#">修改密码</a>
								</li>
								<li>
									<a href="#">工作状态</a>
								</li>
								<li>
									<a href="#">在线状态</a>
								</li>
							</ul>
						</li>
					</ul>
				</li>
				<li>
					<a href="#">工作流</a>
					<ul>
						<li>
							<a href="#">表单定义</a>
							<ul>
								<li>
									<a href="#">创建新的表单</a>
								</li>
								<li>
									<a href="#">查询已有表单</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#">流程定义</a>
							<ul>
								<li>
									<a href="#">创建新的流程定义</a>
								</li>
								<li>
									<a href="#">查询已有流程定义</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#">任务管理</a>
							<ul>
								<li>
									<a href="#">新建任务</a>
								</li>
								<li>
									<a href="#">我的任务</a>
								</li>
								<li>
									<a href="#">待办任务</a>
								</li>
								<li>
									<a href="#">已办任务</a>
								</li>
							</ul>
						</li>
					</ul>
				</li>
				<li>
					<a href="#">系统管理</a>
					<ul>
						<li>
							<a href="#">组织机构管理</a>
							<ul>
								<li>
									<a href="system/company!list">单位/公司信息设置</a>
								</li>
								<li>
									<a href="system/party!list">部门/岗位设置</a>
								</li>
								<li>
									<a href="#">人员管理</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#">权限管理</a>
							<ul>
								<li>
									<a href="#">用户管理</a>
								</li>
								<li>
									<a href="#">角色管理</a>
								</li>
								<li>
									<a href="#">菜单管理</a>
								</li>
								<li>
									<a href="#">资源管理</a>
								</li>
								<li>
									<a href="#">用户授权</a>
								</li>
								<li>
									<a href="#">角色授权</a>
								</li>
								<li>
									<a href="#">部门授权</a>
								</li>
								<li>
									<a href="#">岗位授权</a>
								</li>
							</ul>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</body>
</html>
