<%@ page pageEncoding="UTF-8"%><%@page
	import="com.sun.xml.internal.txw2.Document"%>

<%@ include file="/common/import.jsp"%>

<html>
	<head>
		<%@ include file="/common/header.jsp"%>
		<title>基本信息</title>

		<SCRIPT LANGUAGE="JavaScript">

	window.dhx_globalImgPath = "register/ep/codebase/imgs/";

</SCRIPT>

		<script src="register/ep/codebase/dhtmlxcommon.js">
</script>
		<script src="register/ep/codebase/dhtmlxcalendar.js">
</script>

		<SCRIPT type="text/javascript">


function win_load() {
   mDCal = new dhtmlxCalendarObject('foundDate',false, {
			isMonthEditable: true,
			isYearEditable: true
		});
		mDCal.setSkin("vista");
		mDCal.setYearsRange(1949, 2020);
	    mDCal.draw();
}


</SCRIPT>
	</head>
	<body leftmargin=0 topmargin=0 onload="win_load();">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			align=center border=0>

			<tr>
				<td height=30>
				</td>
			</tr>
			<tr>
				<td valign=top>
					<form id="qy_info" name="qy_info"
						action="register/ep/EnterpriseServlet?m=addEn" method="post">
						<input type="hidden" name="acceptNo" value=${acceptNo}>
						<input type="hidden" name="enterpriseId" value="${enterprise.id}">
						<input type="hidden" name="Id" value=${Id}>
						<table width="95%" border="1" align="center" cellpadding="1"
							cellspacing="0" bordercolor="#0099cc"
							style="line-height: 1.4; border-collapse: collapse"
							bgcolor="#ffffcc">
							<tr>
								<td align=right width=20% class="header" height="27">
									注册号：
								</td>
								<td align=left width=30%>
									<input name="registerNo" value="${enterprise.registerNo}"
										id="registerNo">
								</td>
								<td align=right width=20% class="header" height="27">
									企业类型：
								</td>
								<td align=left width=30%>
									<select name="enterpriseType" id="enterpriseType">
										<c:forEach items="${enterpriseTypes}" var="enterpriseType">
											<option value="${enterpriseType.code}">
												${enterpriseType.value}[${enterpriseType.code}]
											</option>
										</c:forEach>
									</select>

								</td>
							</tr>
							<tr>
								<td align=right class="header" height="27">
									企业名称：
								</td>
								<td align=left colspan=3>
									<input name="name" id="name" value="${enterprise.name}"
										size=100>
								</td>
							</tr>
							<tr>
								<td align=right width=20% class="header" height="27">
									成立日期：
								</td>
								<td align=left width=30%>
									<input type="text" name="foundDate" value="${enterprise.foundDate}"
										id="foundDate">
								</td>
								<td align=right width=20% class="header" height="27">
									经营期限：
								</td>
								<td align=left width=30%>
									<select name="operateLimited" id="operateLimited">
										<option value=1>
											&nbsp;&nbsp;&nbsp;&nbsp;5年
										</option>
										<option value=2>
											&nbsp;&nbsp;&nbsp;&nbsp;10年
										</option>
										<option value=3>
											&nbsp;&nbsp;&nbsp;&nbsp;20年
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<td align=right width=20% class="header" height="27">
									注册资本：
								</td>
								<td align=left width=30%>
									<input name="capital" value="${enterprise.capital}"
										id="capital">
									(元)
								</td>
								<td align=right width=20% class="header" height="27">
									核准日期：
								</td>
								<td align=left width=30%>
									<input name="approveDate" value="" id="approveDate">
								</td>
							</tr>

							<tr>
								<td align=right class="header" height="27">
									住 所：
								</td>
								<td align=left colspan=3>
									<input name="address" id="address"
										value="${enterprise.address}" size=100>
								</td>
							</tr>
							<tr>
								<td align=right class="header" height="27">
									经营范围：
								</td>
								<td align=left colspan=3>
									<textarea cols=50 rows=4 name="operateRange" id="operateRange">${enterprise.operateRange}</textarea>
								</td>
							</tr>
							<tr>
								<td align=right class="header" height="27">
									经营地址：
								</td>
								<td align=left colspan=3>
									<input name="operateAddress" id="operateAddress"
										value="${enterprise.operateAddress}" size=100>
								</td>
							</tr>
							<tr>
								<td align=right class="header" height="27">
									主要产品：
								</td>
								<td align=left colspan=3>
									<input name="products" id="products"
										value="${enterprise.products}" size=100>
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
	<script language=javascript src="../common/script/common1.js">
</script>
	<script language=javascript>

function save() {
	if (!confirm("确实要保存并转入下一步吗？")) {
		return;
	}
	
	//parent.setId(${enterpriseId});
	//parent.setId(${enterprise.id});
	
	document.getElementById("qy_info").submit();
	parent.showBar("a3");

}
</script>
</html>
