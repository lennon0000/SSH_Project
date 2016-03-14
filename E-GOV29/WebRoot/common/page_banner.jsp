<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
function go(pageNo) {
	document.getElementById("pageNo").value = pageNo;
	document.getElementById("searchForm").submit();
}
</script>

<table width=95%>
	<tr>
		<td>
			共${pm.rowCount}条记录&nbsp;&nbsp; 共${pm.pageCount}页
			&nbsp;&nbsp;第${pm.pageNo}页&nbsp;&nbsp&nbsp;&nbsp

			<c:choose>
				<c:when test="${pm.pageNo>1}">
					<a href="javascript:go(1)">第一页</a>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${pm.pageNo>1}">
					<a href="javascript:go(${pm.pageNo-1})">上一页</a>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${pm.pageNo<pm.pageCount}">
				<a href="javascript:go(${pm.pageNo+1})">下一页</a>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${pm.pageNo<pm.pageCount}">
					<a href="javascript:go(${pm.pageCount})">最后一页</a>
				</c:when>
			</c:choose>

			<input id="pno" type="text" size="2" value="${pm.pageNo}">
			页
			<input type="button" value="GO!"
				onclick="go(document.getElementById('pno').value)">
		</td>
	</tr>
</table>
