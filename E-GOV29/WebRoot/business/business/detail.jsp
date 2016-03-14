<%@ page  pageEncoding="UTF-8"%>
<%@ include file="/common/import.jsp" %>
  
<html>
	 <head>
	<%@ include file="/common/header.jsp" %>
	<script type="text/javascript">
		alert("请仔细查看")
		
	</script>
	</head>
  <body>
  <table width="100%" border="0" align="center">
			<tr height=27>
				<td width="100%" align="left" class="title" background="images/title_bg.gif">
				 当前位置：  业务流转单[${business.acceptNo }]
				 
				</td>
			</tr>
		</table>
		
		<table width="95%" border="1" align="center" cellpadding="1"
			cellspacing="0" bordercolor="#0099cc"
			style="line-height: 1.4; border-collapse: collapse" bgcolor="#ffffcc">
		
			<tr>
				<td align=right width=20% class="header" height="27" >
					业务类型：
				</td>
				<td align=left width=30%>
					${business.businessType.value }
				</td>
				<td align=right width=20% class="header" height="27">
					企业名称：
				</td>
				<td align=left width=30%>
					${business.enterpriseName }
				</td>
			</tr>
			<tr>
				<td align=right width=20% class="header" height="27">
					创建时间：
				</td>
				<td align=left width=30%>
					${business.createTime }
				</td>
				<td align=right width=20% class="header" height="27">
					流程状态：
				</td>
				<td align=left width=30%>
					${business.processStatus.value }
				</td>
			</tr>
			
			<tr bgcolor="fffff">
				<td align=center colspan="4" height="27" >
					================联系人信息=====================
				</td>
				
			</tr>
			<tr>
				<td align=right width=20% class="header" height="27">
					姓名：
				</td>
				<td align=left width=30%>
					${business.relation.name }
				</td>
				<td align=right width=20% class="header" height="27">
					身份证号：
				</td>
				<td align=left width=30%>
					${business.relation.idNumber }
				</td>
			</tr>
			<tr>
				<td align=right width=20% class="header" height="27">
					电话号码：
				</td>
				<td align=left width=30%>
					${business.relation.phone }
				</td>
				<td align=right width=20% class="header" height="27">
					电子邮箱：
				</td>
				<td align=left width=30%>
					${business.relation.email }
				</td>
			</tr>
			<tr>
				<td align=center colspan="4" bgcolor="fffff" height="27">
					================业务经办信息=====================
					
					
				</td>
				
			</tr>
			<tr>
				<td align=right width=20% class="header" height="27">
					受理环节：
				</td>
				<td align=left width=80% colspan="3">
					
					<c:set var="processId" value="${business.acceptNo}_A" ></c:set>
					受理人:${business.processes[processId].user.username}<br/>
					受理开始时间:${business.processes[processId].startTime}<br/>
					受理结束时间:${business.processes[processId].endTime}<br/>
				</td>
				
			</tr>
			<tr>
				<td align=right width=20% class="header" height="27">
					录入环节：
				</td>
				<td align=left width=80% colspan="3">
				<c:set var="processId" value="${business.acceptNo}_B" ></c:set>
					录入人:${business.processes[processId].user.username}<br/>
					录入开始时间:${business.processes[processId].startTime}<br/>
					录入结束时间:${business.processes[processId].endTime}<br/>
				</td>
				
			</tr>
			<tr>
				<td align=right width=20% class="header" height="27">
					核查环节：
				</td>
				<td align=left width=80% colspan="3">
				<c:set var="processId" value="${business.acceptNo}_C" ></c:set>
					核查人:${business.processes[processId].user.username}<br/>
					核查开始时间:${business.processes[processId].startTime}<br/>
					核查结束时间:${business.processes[processId].endTime}<br/>
					核查意见：${business.processes[processId].suggestion}
				</td>
				
			</tr>
			<tr>
				<td align=right width=20% class="header" height="27">
					审批环节：
				</td>
				<td align=left width=80% colspan="3">
					<c:set var="processId" value="${business.acceptNo}_D" ></c:set>
					审批人:${business.processes[processId].user.username}<br/>
					审批开始时间:${business.processes[processId].startTime}<br/>
					审批结束时间:${business.processes[processId].endTime}<br/>
					审批意见：${business.processes[processId].suggestion}
				</td>
				
			</tr>
		</table>
		<table width=95%>
			<tr>
				<td align=center>
					<input type="button" value="关闭" onclick="window.returnValue='leadfar';
					window.close();">
				</td>
			</tr>
		</table>
	</body>
</html>
