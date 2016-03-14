<%@ page  pageEncoding="UTF-8"%><%@page import="com.sun.xml.internal.txw2.Document"%>

<%@ include file="/common/import.jsp" %>
  
<html>
	 <head>
	<%@ include file="/common/header.jsp" %>
	
	</head>
	<body>
<table width="100%" border="0" align="center">
			<tr height=27>
				<td width="100%" align="left" class="title"
					background="images/title_bg.gif">
					当前位置： 受理台&gt;受理登记

				</td>
			</tr>
		</table>

		<form action="business/AcceptServlet" method="post">
			<input type="hidden" name="m" value="add" size="3" readonly="readonly">
			
			<table width="60%" border="1" align="center" cellpadding="1"
				cellspacing="0" bordercolor="#0099cc"
				style="line-height: 1.4; border-collapse: collapse"
				bgcolor="#ffffcc">
				<tr>
					<td align=left width=15% class="header" height="27">
						业务类型：
					</td>
					
					<td align=left width=35% >
					<select name="businessType" >请选择业务类型
							<c:forEach items="${businessTypes}" var="businessType">
								<option value="${businessType.code}">${businessType.value}[${businessType.code}]</option>
							</c:forEach>
						</select>
						
					</td>
					<td align=left width=15% class="header" height="27">
						企业名称：
					</td>
					<td align=left width=35%>
						<input name="enterpriseName" value="">
					</td>
				</tr>
				<tr  height="30">
				<td align=center width=100% colspan="4">
					<center><big>----------------------------联系人信息----------------------------</big></center>
				</td>
				</tr>
				<tr>
					<td align=left width=15% class="header" height="27">
						姓名：
					</td>
					<td align=left width=35%>

							<input name="name" value="">

					</td>
					<td align=left width=15% class="header" height="27">
						身份证号：
					</td>
					<td align=left width=35%>
						<input name="idNumber" value="">
					</td>
				</tr>
				<tr>
					<td align=left width=15% class="header" height="27">
						E-mail：
					</td>
					<td align=left width=35%>

						<input name="email" value="">
					</td>
					<td align=left width=15% class="header" height="27">
						联系电话：
					</td>
					<td align=left width=35%>
						<input name="phone" value="">
					</td>
				</tr>
			</table>
			<table width=95%>
				<tr align="center">
					<td align=center>
					<hr/ width="800" align="center">
						&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
						<input type="submit" value="提交" >
					</td>
				</tr>
					
			</table>
			
			<center><div>${infor}</div></center>
			
		</form>
	</body>
</html>
