<%@ page  pageEncoding="UTF-8"%><%@page import="com.sun.xml.internal.txw2.Document"%>

<%@ include file="/common/import.jsp" %>
  
<html>
	 <head>
	<%@ include file="/common/header.jsp" %>
	<script type="text/javascript">
	function finish(){
		if(confirm("确定要审批通过？")){
			document.getElementById("m").value="finish";
			document.getElementById("f").submit();
			
		}
		
	}
	function back(){
		if(confirm("确定要审批驳回？")){
			document.getElementById("m").value="back";
			document.getElementById("f").submit();
			
		}
		
	}
	
	</script>
	</head>
	<body>

		<form action="register/CheckServlet" method="post" id="f">
			<input type="hidden" name="m" value="" id="m">
			
			<input type="hidden" name="acceptNo" value="${business.acceptNo}">
			<table width="60%" border="1" align="center" cellpadding="1"
				cellspacing="0" bordercolor="#0099cc"
				style="line-height: 1.4; border-collapse: collapse"
				bgcolor="#ffffcc">
				<tr>
					<td align=left width=15% class="header" height="27">
						受理号：
					</td>
					
					<td align=left width=35% >
						<div>${business.acceptNo}</div>
					</td>
				</tr>
				<tr>	
					<td align=left width=15% class="header" height="27">
						企业名称：
					</td>
					<td align=left width=35%>
						<div>${business.enterpriseName}</div>
					</td>
				<tr>
					<td align=left width=15% class="header" height="27" >
						审核意见：
					</td>
					<td align=left width=35%>

							<textarea name="suggestion" value="" rows="5" cols="40">经审查，符合标准，审批通过</textarea>

					</td>
				</tr>
			</table>
			<table width=95%>
				<tr align="center">
					<td align=center>
					<hr/ width="800" align="center">
						&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
						<input type="button" value="审核通过" onclick="finish()"><input type="button" value="审核驳回" onclick="back()">
					</td>
				</tr>
					
			</table>
			
			<center><div>${infor}</div></center>
			
		</form>
	</body>
</html>
