<%@ page  pageEncoding="UTF-8"%><%@page import="com.sun.xml.internal.txw2.Document"%>

<%@ include file="/common/import.jsp" %>
  
<html>
	 <head>
	<%@ include file="/common/header.jsp" %>
	
	</head>
  <body>
		<table width="100%" border="0" align="center">
			<tr height=27>
				<td width="100%" align="left" class="title" background="images/title_bg.gif">
				 当前位置：  系统管理&gt;数据字典维护  
				 
				</td>
			</tr>
		</table>
		
		<table id="tblData" width="95%" valign="top" border="0" align="center"
			cellpadding="0" cellspacing="0">
			<tr>
				<td align=center>
					<a href="system/dic/add_input.jsp?type=${type}" target="I1" >添加字典项</a>
				</td>
			</tr>
			
			<tr>
				<td height="2" colspan="6" bgcolor="#5B7C2B"></td>
			</tr>
			<tr>
				<td valign="top" bgcolor="DEDEDE">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="1" >
						<tr  class="header" height="27">
							<td >
								字典代码
							</td>
							<td >
								字典项名称
							</td>
							<td colspan="3">
								操作
							</td>
						</tr >
						<c:forEach items="${dics}" var="dic">
							<tr class="data" height="27">
								<td >
									${dic.code}
								</td>
								<td >
									${dic.value}
								</td>
								<td >
									<a href="system/DicServlet?m=updateInput&id=${dic.id}" target=I1>编辑</a>
								</td>
								<td  >
									<a href="system/DicServlet?m=delete&id=${dic.id}" target=I1>删除</a>
								</td>
								<td  >
									<a href="system/DicServlet?type=${dic.code}" >查看</a>
								</td>
								
							</tr>	
						</c:forEach>		
					</table>
				</td>
			</tr>
		</table>
		<table width=95% >
			<tr>
				<td height=50>
				</td>
			</tr>
			<tr>
				<td width=100% height=300>
					<IFRAME   height=100% width=100% style="Z-INDEX: 1; VISIBILITY: inherit; scrollleft: 0"  name="I1" src=""   frameBorder=0 scrolling=auto></IFRAME>
				</td>
			</tr>
		</table>
	</body>
</html>
