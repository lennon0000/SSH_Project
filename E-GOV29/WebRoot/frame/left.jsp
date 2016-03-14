<%@ page pageEncoding="UTF-8"%><%@page
	import="com.sun.xml.internal.txw2.Document"%>

<%@ include file="/common/import.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
	<HEAD>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<STYLE>
TD {
	FONT-SIZE: 9pt;
	FONT-FAMILY: verdana, helvetica;
	WHITE-SPACE: nowrap;
	TEXT-DECORATION: none
}

A {
	COLOR: black;
	TEXT-DECORATION: none
}

.specialClass {
	FONT-WEIGHT: bold;
	FONT-SIZE: 12pt;
	COLOR: green;
	FONT-FAMILY: garamond;
	TEXT-DECORATION: underline
}
</STYLE>
		<!-- NO CHANGES PAST THIS LINE -->
		<!-- Code for browser detection -->
		<SCRIPT src="menu/ua.js"></SCRIPT>
		<!-- Infrastructure code for the tree -->
		<SCRIPT src="menu/ftiens4.js"></SCRIPT>
		<!-- Execution of the code that actually builds the specific tree.
     The variable foldersTree creates its structure with calls to
	 gFld, insFld, and insDoc -->
		<SCRIPT>
	// You can find instructions for this file here:
// http://www.treeview.net

// Decide if the names are links or just the icons
USETEXTLINKS = 1  //replace 0 with 1 for hyperlinks

// Decide if the tree is to start all open or just showing the root folders
STARTALLOPEN = 0 //replace 0 with 1 to show the whole tree

ICONPATH = 'menu/images/tree/' //change if the gif's folder is a subfolder, for example: 'images/'

foldersTree = gFld("&nbsp;主菜单", "")
foldersTree.iconSrc = "menu/images/tree/root.gif"
foldersTree.iconSrcClosed = "menu/tree/root.gif"

<c:choose >
<c:when test="${LOGIN_USER.status eq '01'}">
<c:forEach items="${funcs}" var="parent">
					<c:if test="${parent.checked eq 'checked'}">
				
						tree1 = insFld(foldersTree, gFld("&nbsp;${parent.name}", ""))
					</c:if>
					<c:forEach items="${parent.children}" var="child" varStatus="stat">
						<c:if test="${child.checked eq 'checked'}">
							insDoc(tree1, gLnk("rightFrame", "&nbsp;${child.name}","../${child.url}"));
						</c:if>	
					</c:forEach>
</c:forEach>

</c:when>
<c:otherwise>
alert("您的账户未激活，请在个人控制台修改密码以激活账户");
</c:otherwise>
</c:choose >
tree1 = insFld(foldersTree, gFld("&nbsp;个人控制台", ""))
	insDoc(tree1, gLnk("rightFrame", "&nbsp;绑定ip","../Index?m=getIp"))
	insDoc(tree1, gLnk("rightFrame", "&nbsp;修改密码","../system/user/modifyPassword_input.jsp"))
	insDoc(tree1, gLnk("rightFrame", "&nbsp;注销","../Index?m=userCancel"))
	insDoc(tree1, gLnk("rightFrame", "&nbsp;帮助","../document/egov.htm"))
	
</SCRIPT>
		<META http-equiv=Content-Type content="text/html; charset=gbk">
		<META content="MSHTML 6.00.2800.1607" name=GENERATOR>
	</HEAD>
	<BODY leftMargin=0 topMargin=0 background="menu/treeBg_03.gif">
		<DIV
			style="DISPLAY: none; LEFT: 0px; POSITION: absolute; TOP: 0px; HEIGHT: 0px">
			<FONT size=-2><A
				style="FONT-SIZE: 7pt; COLOR: silver; TEXT-DECORATION: none"
				href="http://www.treemenu/" target=_blank></A> </FONT>
		</DIV>
		<TABLE style="MARGIN-TOP: 0px; MARGIN-LEFT: 0px" cellSpacing=0
			cellPadding=0 border=0>


			<TR>
				<TR>
					<TD>
						<b>当前登录用户[${LOGIN_USER.username}]</b>
					</TD>
				</TR>
				<TR>
					<TD background="menu/treeBg_01.gif" height=20></TD>
				</TR>
				<TR>
					<TD background="menu/treeBg_02.gif">
						<DIV id=treeStr style="MARGIN-LEFT: 10px">
							<SCRIPT>initializeDocument()</SCRIPT>
							<NOSCRIPT>
								A tree for site navigation will open here if you enable
								JavaScript in your browser.
							</NOSCRIPT>
						</DIV>
						<DIV align=left>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<A href="javascript:parent.window.close();" target=_parent><STRONG>
									退出</STRONG> </A>
						</DIV>
					</TD>
				</TR>
				<TR>
					<TD>
						<IMG src="menu/treeBg_04.gif">
					</TD>
				</TR>
		</TABLE>
	</BODY>
</HTML>
