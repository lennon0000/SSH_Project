<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/import.jsp"%>

<html>
	<head>
		<%@ include file="/common/header.jsp"%>
	</head>
	<script type="text/javascript">
function backToLogin(){
	
	window.history.back(-2);
	
}
</script>
	<body>
		<tr >
		</br></br></br></br></br></br></br></br></br></br></br>
		
			<center>
			
				<big>账户IP已绑定，请重新登录</big>
				</br></br></br>
				<input type="button" value="点击返回登录页面" onclick=backToLogin()>
			</center>
		</tr>
	</body>
</html>
