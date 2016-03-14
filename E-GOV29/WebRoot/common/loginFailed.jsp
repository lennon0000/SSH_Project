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
			
				<big>用户名或密码错误，请重新输入</big>
				</br></br></br>
				<input type="button" value="重新登录" onclick=backToLogin()>
			</center>
		</tr>
	</body>
</html>
