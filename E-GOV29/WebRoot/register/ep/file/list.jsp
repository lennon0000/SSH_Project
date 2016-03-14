<%@ page pageEncoding="UTF-8"%><%@page
	import="com.sun.xml.internal.txw2.Document"%>

<%@ include file="/common/import.jsp"%>

<html>
	<head>
		<%@ include file="/common/header.jsp"%>

	</head>
	<body>

		<table id="tblData" width="95%" valign="top" border="0" align="center"
			cellpadding="0" cellspacing="0">

			<tr>
				<td height="2" colspan="6" bgcolor="#5B7C2B"></td>
			</tr>
			<tr>
				<td valign="top" bgcolor="DEDEDE">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="1">
						<tr class="header" height="27">
							<td>
								企业ID
							</td>
							<td>
								文件名称
							</td>
							<td>
								文件大小
							</td>
							<td>
								文件路径
							</td>
							<td colspan="2">
								操作
							</td>
						</tr>
						<c:forEach items="${dics}" var="dic">
							<tr class="data" height="27">
								<td>
									${dic.code}
								</td>
								<td>
									${dic.value}
								</td>
								<td>
									<a href="system/DicServlet?m=delete&id=${dic.id}" target="I1">删除</a>
								</td>
								<td>
									<a href="system/DicServlet?type=${dic.code}">下载</a>
								</td>

							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</table>
		<table width=95%>
			<tr>
				<td height=50>
				</td>
			</tr>
			<tr>
				<td width=100% height=300>
					<form action="register/ep/UploadServlet" method="post"
						enctype="multipart/form-data" id="uploadForm">
						<input type="hidden" name="enterpriseId" value=${param.enterpriseId}>
						<br />
						上传文件:
						<input type="file" name="attachment">
						<br />
						<input type="button" value="上传" onclick="save();">
					</form>
				</td>
			</tr>
		</table>
	</body>
	<script type="text/javascript">

function save() {
	if (!confirm("确实要上传吗？")) {
		return;
	}
	document.getElementById("uploadForm").submit();

}</html>
