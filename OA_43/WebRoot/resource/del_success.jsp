<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<base href="<%=basePath%>">
	<title></title>
	
	<link rel="stylesheet" type="text/css"
			href="css/pageStyle.css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="js/jquery.jstree.js"></script>
<script type="text/javascript">
$(function(){
	//调用父窗口的refresh()方法，刷新组织机构树
	window.parent.refresh();
});
</script>
</head>
<body>

<!-- 主界面 -->
		<div>
		
<TABLE cellSpacing=0 cellPadding=0 width=540 align=center border=0>
  <TBODY>
  <TR>
    <TD vAlign=top height=270>
      <DIV align=center><BR><IMG height=211 src="common/success.gif" 
      width=329><BR><BR>
      <TABLE cellSpacing=0 cellPadding=0 width="80%" border=0>
        <TBODY>
        <TR>
          <TD><FONT class=p2>&nbsp;&nbsp;&nbsp; </FONT></TD></TR>
        <TR>
          <TD height=8></TD>
        <TR>
          <TD>
            <P>
			<FONT color=#00ff00></FONT>            
      　</P></TD></TR></TBODY></TABLE></DIV></TD></TR>
  <TR>
    <TD height=5></TD>
  <TR>
    <TD align=middle>
      <CENTER>
      <TABLE cellSpacing=0 cellPadding=0 width=480 border=0>
        <TBODY>
        <TR>
          <TD width=6><IMG height=26 src="common/left.gif" 
width=7></TD>
          <TD background="common/bg.gif">
            <DIV align=center><FONT class=p6>
            <A href="javascript:history.go(-1)">返回上一页</A></FONT> </DIV></TD>
          <TD width=7><IMG 
      src="common/right.gif"></TD></TR></TBODY></TABLE></CENTER></TD></TR></TBODY></TABLE>		
		
		</div>

</body>
</html>

