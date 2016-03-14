<%@ page pageEncoding="UTF-8"%><%@page
	import="com.sun.xml.internal.txw2.Document"%>

<%@ include file="/common/import.jsp"%>

<html>

	<head>
		<title>E-GOV工商业务综合管理系统</title>
		<script type="text/javascript">
<c:if test="${error ne null and error ne ''}">
		alert("${error}");
	</c:if>	
	if(window!=top){
		top.location=window.location;
	}
	</script>
	</head>
	<body bgcolor="#999999">
		<table height="100%" border="0" cellpadding="0" cellspacing="0"
			width="100%" align="center">
			<tr>
				<td>
					<div align="center">
						<table width="595" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td>
									<img src="images/img_1.jpg" width="595" height="81">
								</td>
							</tr>
						</table>
						<table width="595" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td>
									<img src="images/img_2.jpg" width="595" height="77">
								</td>
							</tr>
						</table>
						<table width="595" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td>
									<img src="images/img_3.jpg" width="595" height="104">
								</td>
							</tr>
						</table>
						<form action="Index?m=login" method="post">
							<table width="595" border="0" align="center" cellpadding="0"
								cellspacing="0">

								<tr>
									<td width="19" background="images/img_4.jpg">
										&nbsp;
									</td>
									<td width="68" background="images/img_4.jpg">
										<img src="images/img_5.jpg" width="61" height="78">
									</td>
									<td width="88" background="images/img_4.jpg">
										<Select name="admcode" size="1"
											STYLE="width: 12em">
											<c:forEach items="${adms}" var="adm">

												<option value="${adm.code}"
													<c:if test="${adm.code eq seletedAdmcode}">selected</c:if>>
													${adm.value}[${adm.code}]

												</option>
											</c:forEach>

										</select>
									</td>
									<td width="68" background="images/img_4.jpg">
										<img src="images/img_6.jpg" width="62" height="78">
									</td>
									<td width="85" background="images/img_4.jpg">
										<input name="username" type="text" size="10" value="admin">
									</td>
									<td width="68" background="images/img_4.jpg">
										<img src="images/img_7.jpg" width="63" height="78">
									</td>
									<td width="91" background="images/img_4.jpg">
										<input name="password" type="password" size="10" value="admin">
									</td>
									<td width="109" background="images/img_4.jpg">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">

											<tr>
												<td>
													<input name="imageField" type="image"
														src="images/img_button1.jpg" width="42" height="18"
														border="0">
												</td>

											</tr>
										</table>
									</td>
								</tr>




							</table>
						</form>
					</div>
				</td>
			</tr>
		</table>





	</body>
</html>