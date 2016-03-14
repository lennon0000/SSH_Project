<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/import.jsp"%>

<html>
	<head>
		<%@ include file="/common/header.jsp"%>
	</head>
	<script type="text/javascript">
function parentRefesh() {
	parent.location.replace(parent.location.href);
	parent.location.reload;
}
</script>
	<body>
		<center>
			数据提交成功！
			</br>
			<input type="button" value="刷新" onclick=parentRefesh()>
		</center>
	</body>
</html>
