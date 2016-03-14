<%@ page pageEncoding="UTF-8"%><%@page
	import="com.sun.xml.internal.txw2.Document"%>

<%@ include file="/common/import.jsp"%>

<html>
	<head>
		<%@ include file="/common/header.jsp"%>
	</head>
	<body>
		<table width="100%" border="0" align="center">
			<tr height=27>
				<td width="100%" align="left" class="title"
					background="images/title_bg.gif">
					当前位置： 系统管理&gt;用户管理

				</td>
			</tr>
		</table>

		<table id="tblData" width="95%" valign="top" border="0" align="center"
			cellpadding="0" cellspacing="0">
			<tr>
				<td align=center>
					<a href="system/UserServlet?m=addInput" target="I1">添加字典项</a>
				</td>
			</tr>
			<tr>
				<form action="system/UserServlet" id="searchForm">
					<select name="admcode">

						<option value="${adm.code}">
							请选择所属工商部门
						</option>
						<c:forEach items="${adms}" var="adm">
							<option value="${adm.code}"
								<c:if test="${adm.code eq param.admcode}">selected</c:if>>
								${adm.value}[${adm.code}]
							</option>
						</c:forEach>
					</select>
					<input type="hidden" name="pageNo" value="1" id="pageNo">
					<input type="text" name="username" value="${param.username}">
					<input type="button" value="檢索" onclick="javascript:go(1)">
				</form>
			</tr>

			<tr>
				<td height="2" colspan="6" bgcolor="#5B7C2B"></td>
			</tr>
			<tr>
				<td valign="top" bgcolor="DEDEDE">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="1">
						<tr class="header" height="27">
							<td>
								用户名
							</td>
							<td>
								真实姓名
							</td>
							<td>
								所属工商部门
							</td>
							<td>
								所属部门
							</td>
							<td>
								状态
							</td>

							<td colspan="3">
								操作
							</td>
						</tr>
						<c:forEach items="${users}" var="user">
							<tr class="data" height="27">
								<td>
									${user.username}
								</td>
								<td>
									${user.name}
								</td>
								<td>
									${user.adm.value}
								</td>
								<td>
									${user.dept}
								</td>
								<td>
									<c:choose>
										<c:when test="${user.status eq '00'}">[${user.status}]未激活</c:when>
										<c:when test="${user.status eq '01'}">[${user.status}]正常</c:when>
										<c:when test="${user.status eq '02'}">[${user.status}]停用</c:when>
										<c:otherwise>未知</c:otherwise>
									</c:choose>
								</td>


								<td>
									<a href="system/UserServlet?m=updateInput&id=${user.id}"
										target=I1>编辑</a>
								</td>
								<td>
									<c:choose>
										<c:when test="${user.status eq '00'}">
											<a href="system/UserServlet?m=enable&id=${user.id}" target=I1>启用</a>
										</c:when>
										<c:otherwise>
											<a href="system/UserServlet?m=disable&id=${user.id}"
												target=I1>禁用</a>
										</c:otherwise>
									</c:choose>
								</td>
								<td>
									<a href="system/UserServlet?m=listFunc&id=${user.id}" target=I1>授权</a>
								</td>

							</tr>
						</c:forEach>

					</table>
				</td>
			</tr>

		</table>
		<%@include file="/common/page_banner.jsp"%>


		<table width=95%>
			<tr>
				<td height=50>
				</td>
			</tr>
			<tr>
				<td width=100% height=300>
					<IFRAME height=100% width=100%
						style="Z-INDEX: 1; VISIBILITY: inherit; scrollleft: 0" name="I1"
						src="" frameBorder=0 scrolling=auto></IFRAME>
				</td>
			</tr>
		</table>
	</body>
</html>
