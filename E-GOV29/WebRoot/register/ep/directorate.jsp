<%@ page pageEncoding="UTF-8"%><%@page
	import="com.sun.xml.internal.txw2.Document"%>

<%@ include file="/common/import.jsp"%>

<html>
	<head>
		<%@ include file="/common/header.jsp"%>
		<title>公司董事会</title>
	</head>
   
  <body leftmargin=0 topmargin=0>

<table width="100%" border="0" cellpadding="0" cellspacing="0"
	align=center border=0>
	
	<tr>
		<td height=30>本功能与股东信息的编码类似，请同学们自己完成</td>
	</tr>
	<tr>
		<td height=10 align=center>
			<input type=button name="add" value="新增董事" onclick="addRow();">
		</td>
	</tr>
	<tr>
		<td valign=top>
		<form id="qy_info" name="qy_info" action="" method="post">
			<table width="95%" border="1" align="center" cellpadding="1" id="tbl_gd" name="tbl_gd" cellspacing="0" bordercolor="#0099cc" style="line-height: 1.4; border-collapse: collapse" bgcolor="#ffffcc">
			<tr>
				<td align=center width=30%  class="header" height="27">姓名</td>
				<td align=center width=30% class="header" height="27">证照号码</td>
				<td align=center width=20% class="header" height="27">产生方式</td>
				<td align=center width=20% class="header" height="27">职务</td>

			</tr>
		
			<tr>
				<td align=left%>
										<input name="gdmc" id="xm" value="">
									</td>
				<td align=left%>
										<input name="zzhm" id="zzhm" value="">
									</td>
				<td align=left%>
										<select name="csfs" id="csfs" ><option value="选举">选举</option><option value="委派">委派</option></select>
									</td>
				<td align=left%>
										<select name="csfs" id="csfs" ><option value="董事长">董事长</option><option value="董事">董事</option><option value="监事">监事</option></select>
									</td>

			</tr>
			
		</table>
		</form>
		</td>
	</tr>

	<tr>
		<td height=20></td>
	</tr>
	<tr>
		<td align="center" width="100%">
		<input type="button" name="save"
			onClick="save()" value="保存&转入下一步"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
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
		function addRow(){

				//添加一行
				
				var newTr = tbl_gd.insertRow();
				
				//添加两列
				
				var newTd0 = newTr.insertCell();
				
				var newTd1 = newTr.insertCell();
				
				var newTd2 = newTr.insertCell();
				
				var newTd3 = newTr.insertCell();
				
				
				//设置列内容和属性
				
				newTd0.innerHTML = '<input name="xm" id="xm" value="">'; 
				newTd1.innerHTML = '<input name="zzhm" id="zzhm" value="">';
				newTd2.innerHTML = '<select name="csfs" id="csfs" ><option value="选举">选举</option><option value="委派">委派</option></select>';
				newTd3.innerHTML = '<select name="csfs" id="csfs" ><option value="董事长">董事长</option><option value="董事">董事</option><option value="监事">监事</option></select>';
		}
		
	</script>
</html>
