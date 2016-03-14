<%@ page pageEncoding="UTF-8"%><%@page
	import="com.sun.xml.internal.txw2.Document"%>

<%@ include file="/common/import.jsp"%>

<html>
	<head>
		<%@ include file="/common/header.jsp"%>
		<script type="text/javascript">
		function look(acceptNo){
			window.showModalDialog("<%=basePath%>business/AcceptServlet?m=look&acceptNo="+acceptNo,"","dialogWidth=800px;dialogHeight=480px")
		}
		
		</script>
	</head>
	<body>
		<table width="100%" border="0" align="center">
			<tr height=27>
				<td width="100%" align="left" class="title"
					background="images/title_bg.gif">
					当前位置： 受理台&gt;受理信息
				</td>
			</tr>
		</table>

		<table id="tblData" width="95%" valign="top" border="0" align="center"
			cellpadding="0" cellspacing="0">
			<tr>
				<form action="business/AcceptServlet" id="searchForm">
					受理号：
					<input type="hidden" name="pageNo" value="1" id="pageNo">
					<input type="text" name="acceptNo" value="${acceptNo}">
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
								受理号
							</td>
							<td>
								企业名称
							</td>
							<td>
								业务类型
							</td>
							<td>
								所在工商机关
							</td>
							<td>
								流程状态
							</td>
							<td dcolspan="2">
								创建时间
							</td>


							<td colspan="4">
								操作
							</td>
						</tr>
						<c:forEach items="${businesses}" var="business">
							<tr class="data" height="27">
								<td>
									${business.acceptNo}
								</td>
								<td>
									${business.enterpriseName}
								</td>
								<td>
									[${business.businessType.code}] ${business.businessType.value}
								</td>
								<td>
									[${business.adm.code}]${business.adm.value}
								</td>
								<td>
									[${business.processStatus.code}]${business.processStatus.value}
								</td>
								<td>
									${business.createTime}
								</td>



								<td>
									<c:if test="${business.processStatus.code eq '01'}">
										<a
											href="business/AcceptServlet?m=finish&acceptNo=${business.acceptNo}"
											target=I1>完成</a>
									</c:if>
								</td>

								<td>
									<c:if test="${business.processStatus.code eq '00'}">
										<a
											href="business/AcceptServlet?m=lock&acceptNo=${business.acceptNo}"
											target=I1>进入</a>
									</c:if>
								</td>
								<td>
									<c:if test="${business.processStatus.code eq '01'}">
										<a
											href="business/AcceptServlet?m=release&acceptNo=${business.acceptNo}"
											target=I1>释放</a>
									</c:if>
								</td>
								<td>
									<a
									href="javascript:look(${business.acceptNo})">查看</a>
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
