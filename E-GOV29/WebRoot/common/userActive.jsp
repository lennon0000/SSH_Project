<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/import.jsp"%>

<html>
	<head>
		<%@ include file="/common/header.jsp"%>
	</head>
	<script type="text/javascript">
function userActive(id){
	alert(id);
	
	//window.history.back(-2);
	
   top.location.href="/E-GOV/system/UserActiveServlet?id="; 
	
}
</script>
	<body>
		<tr >
		</br></br></br></br></br></br></br></br></br></br></br>
		
			<center>
			
				<big>账户未激活</big>
				</br></br></br>
				
				</br>
				<a href="system/UserAcitveServlet?id=${id}">点击激活账户</a>
				
				
			</center>
		</tr>
	</body>
</html>
