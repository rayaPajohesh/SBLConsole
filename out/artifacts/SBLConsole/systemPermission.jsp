<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page import="com.iac.ambit.utils.Constants"%>




<%@ taglib uri="/WEB-INF/treetag.tld" prefix="tree"%>

<script language="javascript">
			function initiate(){
				document.all.permissionIds.value = "";
			}
			
			function setPermissionIds(obj){
			
					document.all.permissionIds.value = document.all.permissionIds.value + obj.value +  ";";
							
			}
            function alertDeactiviate(module){
                return confirm("<bean:message key='modules.confirm.deactivate' arg0= '" +  module + "'/>" + "<bean:message key='global.questionMark' />");
            }
            function alertActiviate(module){
                return confirm("<bean:message key='modules.confirm.activate' arg0= '" +  module + "'/>" +  "<bean:message key='global.questionMark' />");
            }
        </script>
<c:set var="Active" value="${sessionScope.Active}" />
<c:set var="InActive" value="${sessionScope.InActive}" />
<body onload="initiate();"
	dir="<%= session.getAttribute( Constants.JspConstants.PAGE_DIR  )%>">

<table cellpadding="0" cellspacing="0" border="0"
	class="WelcomeTableBorder">
	<tr>
		<td class="purpleBarText">
		<p><bean:message key="modules.Heading" /></p>
		</td>
	</tr>
	<tr>
		<td>
		<table border="0" cellpadding="5" cellspacing="0" class="wellcome">
			<tr>
				<td class="captionBold"><bean:message key="modules.welcomeMessage" />
				</td>
			</tr>
			<tr>
				<td class="captionBold"><bean:message key="modules.avaliable1" /> <img
					src="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>Active.gif" />
				<bean:message key="modules.avaliable2" /></td>
			</tr>

		</table>
		</td>
	</tr>
	<tr >
	<td>
		<table class="listHeader">
			<tr>
			<td style="width: 98%" class="gridHeader"><bean:message key="modules.Heading" /></td>
			</tr>
		</table>
	</td>
		

	</tr>
	<tr>
		<td><html:form action="permissionManagement.do">
			<html:hidden property="permissionIds" />
			<div
				style="width: 98%;height: 190px;overflow-Y:scroll; overflow-x:hidden ;border-bottom: 1px solid gray;;border-bottom-color: #98a8b6; ">
			<table class="Listbox">

				<c:forEach var="list" items="${permissions}" varStatus="status">

					<TR>
						<TD align="center" bgcolor="#e0e7cc" width="5%"><c:if
							test="${list.permissionStatus == Active}">
							<input type="checkbox" id="status" name="status"
								onclick="setPermissionIds(this);"
								title="<bean:message key="modules.deactivate" />" checked="true"
								value="<c:out value='${list.permissionId}'/>" />
						</c:if> <c:if test="${list.permissionStatus == InActive}">
							<input type="checkbox" id="status" name="status"
								onclick="setPermissionIds(this);"
								title="<bean:message key="modules.activate" />"
								value="<c:out value='${list.permissionId}'/>" />
						</c:if></TD>
						<TD><c:out value="${list.permissionTitleFA}" /></TD>

					</TR>
				</c:forEach>

			</table>
			</div></td>
		<!-- ashrafi:set title to titleKey  1386/05/28 -->
	</tr>

	<tr>

		<td align="center"><html:submit styleClass="loginButtons">
			<bean:message key="global.submitButton" />
		</html:submit></td>
	<tr>
		</html:form>
</table>



</body>
