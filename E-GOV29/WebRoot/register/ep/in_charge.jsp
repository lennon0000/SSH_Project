<%@ page pageEncoding="UTF-8"%><%@page
	import="com.sun.xml.internal.txw2.Document"%>

<%@ include file="/common/import.jsp"%>

<html>
	<head>
		<%@ include file="/common/header.jsp"%>
		<title>负责人信息</title>
	</head>
  
  <body leftmargin=0 topmargin=0>
		
			<table width="100%" border="0" cellpadding="0" cellspacing="0" align=center border=0>
				
				<tr>
					<td height=30>
					</td>
				</tr>
				<tr>
					<td valign=top>
						<form id="qy_info" name="qy_info" action="" method="post">
						<table width="95%" border="1" align="center" cellpadding="1" cellspacing="0" bordercolor="#0099cc"  style="line-height: 1.4; border-collapse: collapse" bgcolor="#ffffcc">
							<tr>
								<td align=right width=20% class="header" height="27">
									证件号码：
								</td>
								<td  align=left  width=30%>
									<input name="cardNo" value="" id="cardNo">
								</td>
								<td align=right  width=20% class="header" height="27">
									姓名：
								</td>
								<td align=left  width=30%>
									<input name="name" value="" id="name">
								</td>
							</tr>
							
							<tr>
								<td align=right width=20% class="header" height="27">
									性别：
								</td>
								<td  align=left  width=30%>
									<input name="sex" value="" id="sex">
								</td>
								<td align=right  width=20% class="header" height="27">
									出生日期：
								</td>
								<td  align=left  width=30%>
									<input name="birthDate" value="" id="birthDate">
								</td>
							</tr>
							<tr>
								<td align=right width=20% class="header" height="27">
									联系电话：
								</td>
								<td  align=left  width=30%>
									<input name="phone" value="" id="phone">
								</td>
								<td align=right  width=20% class="header" height="27">
									文化程度：
								</td>
								<td  align=left  width=30%>
									<input name="degree" value="" id="degree">
								</td>
							</tr>
							<tr>
								<td align=right class="header" height="27" >
									工作简历：
								</td>
								<td  align=left colspan=3>
									<textarea cols=50 rows=20 name="resume" id="resume" >
									</textarea>
								</td>
							</tr>		
							
						</table>
						</form>
					</td>
				</tr>

				<tr>
					<td height=20>
					</td>
				</tr>
				<tr>
					<td align="center" width="100%">
						<input type="button" name="save" onClick="save()" value="保存&转入下一步">
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
			

			</table>

			
	</body>
	<script language=javascript src="../common/script/common1.js"></script>
	<script language=javascript>
		function save(){
			if(!confirm("确实要保存并转入下一步吗？")){
				return;
			}
			qy_info.submit();
		}
	</script>
</html>
