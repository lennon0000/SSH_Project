
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/import.jsp"%>


<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="textml; charset=gb2312">
		<title>基础信息</title>
		<link href="register/ep/common/CSS/styles.css" rel="stylesheet"
			type="text/css">
	</head>

	<body leftmargin=0 topmargin=0 >

		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			align=center border=0>

			<tr>
				<td height=30></td>
			</tr>
			<tr>
				<td height=10 align=center>
					<input type=button name="add" value="新增股东" onclick="addRow();">
					&nbsp;&nbsp;
					<input type=button name="del" value="减少股东" onclick="delRow();">
				</td>
			</tr>
			<tr>
				<td valign=top>
					<form id="form1" name="form1"
						action="register/ep/EnterpriseServlet" method="post">
						<input type="hidden" name="m" value="addStockHolders">
						<input type="hidden" name="stockHolderMsg" id="stockHolderMsg">
						<input type="hidden" name="acceptNo" value=${acceptNo}>
						<table width="95%" border="1" align="center" cellpadding="1"
							id="tbl_gd" name="tbl_gd" cellspacing="0" bordercolor="#0099cc"
							style="line-height: 1.4; border-collapse: collapse"
							bgcolor="#ffffcc">
							<tr>
								<td align=center width=30% class="header" height="27">
									股东名称
								</td>
								<td align=center width=30% class="header" height="27">
									证照号码
								</td>
								<td align=center width=20% class="header" height="27">
									出资额
								</td>
								<td align=center width=20% class="header" height="27">
									出资比例(%)
								</td>

							</tr>
							<c:forEach items="${stockHolderList}" var="sh" varStatus="s">
								<tr>

									<td align=left%>
										<input name="name${(s.index)+1}" id="name${(s.index)+1}" value="${sh.name}">
									</td>
									<td align=left%>
										<input name="cardNo${(s.index)+1}" id="cardNo${(s.index)+1}" value="${sh.cardNo}">
									</td>
									<td align=left%>
										<input name="moneyInvested${(s.index)+1}" id="moneyInvested${(s.index)+1}" value="${sh.moneyInvested}">
									</td>
									<td align=left%>
										<input name="percentage${(s.index)+1}" id="percentage${(s.index)+1}" value="${sh.percentage}">
									</td>

								</tr>
							</c:forEach>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td height=20></td>
			</tr>
			<tr>
				<td align="center" width="100%">
					<input type="button" name="save" onClick="save();" value="保存&转入下一步">
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
	var stockHolderMsg = getInfo();
	document.getElementById("stockHolderMsg").value = stockHolderMsg

	document.getElementById("form1").submit();
}
function addRow() {

	//添加一行
	var tbl_gd = document.getElementById("tbl_gd");
	var index = tbl_gd.rows.length;
	var newTr = tbl_gd.insertRow(index);

	//添加两列

	var newTd0 = newTr.insertCell(0);

	var newTd1 = newTr.insertCell(1);

	var newTd2 = newTr.insertCell(2);

	var newTd3 = newTr.insertCell(3);

	//设置列内容和属性

	newTd0.innerHTML = "  <input name=\"name" + index + "\" id=\"name" + index
			+ "\" value=\"\">  ";

	newTd1.innerHTML = "<input name=\"cardNo" + index + "\" id=\"cardNo"
			+ index + "\" value=\"\">";
	newTd2.innerHTML = "<input name=\"moneyInvested" + index
			+ "\" id=\"moneyInvested" + index + "\" value=\"\">";
	newTd3.innerHTML = "<input name=\"percentage" + index
			+ "\" id=\"percentage" + index + "\" value=\"\">";
}

function delRow() {
	var tbl_gd = document.getElementById("tbl_gd");
	var index = tbl_gd.rows.length;
	if (tbl_gd.rows.length > 2) {
		tbl_gd.deleteRow(index - 1);
	}
}
function getInfo() {

	var infoStr = "";

	//alert(document.getElementById("name2").value);
	//alert("sadsad");
	//alert(tbl_gd.rows.length);
	for ( var i = 1; i <= tbl_gd.rows.length - 1; i++) {

		infoStr += document.all.item("name" + i).value + "::";
		infoStr += document.all.item("cardNo" + i).value + "::";
		infoStr += document.all.item("moneyInvested" + i).value + "::";
		infoStr += document.all.item("percentage" + i).value + ";;";
	}
	return infoStr;

	//alert(infoStr);
}
</script>
	</html>