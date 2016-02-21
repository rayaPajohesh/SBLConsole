<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page import="com.iac.ambit.utils.Constants"%>
<%@ page import="com.iac.ambit.utils.Config"%>
<%@ page import="proxy.com.iac.ambit.model.Group"%>
<%@ page import="proxy.com.iac.ambit.model.Permissions"%>
<%@ page import="com.iac.ambit.utils.ErrorsUtility"%>
<%@ taglib uri="/WEB-INF/treetag.tld" prefix="tree"%>
<%@ page import="java.util.Locale"%>



<%Group group = (Group) request.getSession().getAttribute("group");

			Permissions[] allPermission = (Permissions[]) request.getSession()
					.getAttribute("allPermission");
			;
			int permissionCount = 0;
			if (allPermission != null) {
				permissionCount = allPermission.length;

			}

			String groupStatus = null;
			if (group != null)
				groupStatus = group.getGroupActive();
			Locale locale = (Locale) request.getSession().getAttribute(
					"org.apache.struts.action.LOCALE");
			String language = locale.getLanguage();
			String country = locale.getCountry();

			%>
<script language="javascript">



function setSelectedGroupId(obj){

document.all.selectedGroupId.value = obj.options[obj.selectedIndex].value;
document.groupManagement.action = '<html:rewrite page="/groupManagement.do?method=edit" />';  
document.groupManagement.submit();

}
			function initiate(){
				document.all.permissionIds.value = "";
				if(document.all.groupId)
				document.all.groupId.focus();
				
			}
			
		
			
        </script>



<script language="Javascript1.1" src="staticJavascript.do"></script>
<body onload="initiate();"
	dir="<%= session.getAttribute( Constants.JspConstants.PAGE_DIR  )%>">

<script language="Javascript1.1" src="staticJavascript.do"></script>
<html:form action="groupManagement.do?submitKey=true"
	>
	<html:hidden property="method" />
	<html:hidden property="permissionIds" />
	<html:hidden property="selectedGroupId" />

	<table cellpadding="0" cellspacing="0" border="0"
		class="WelcomeTableBorder">
		<tr>

			<%if (Constants.METHOD.ADD.equalsIgnoreCase(request
					.getParameter("method"))) {%>
			<td class="purpleBarText">
			<p><bean:message key="group.method.add" /></p>
			
			</td>
			<%} else if (Constants.METHOD.EDIT.equalsIgnoreCase(request
					.getParameter("method"))
					|| Constants.METHOD.SEARCH.equalsIgnoreCase(request
							.getParameter("method"))) {%>
			<td class="purpleBarText">
			<p><bean:message key="group.method.edit" /></p>
			</td>
			<%} else if (Constants.METHOD.VIEW.equalsIgnoreCase(request
					.getParameter("method"))) {%>
			<td class="purpleBarText">
			<p><bean:message key="group.method.view" /></p>
			</td>
			<%}

			%>
		</tr> 
		<tr>
			<%if (Constants.METHOD.ADD.equalsIgnoreCase(request
					.getParameter("method"))) {%>
			<td>
				<table width="100%" border="0" cellpadding="1" cellspacing="4"
						class="purpleCustomerMessages">
					<tr>
						<td>&nbsp;<bean:message key="group.add.help" /></td>
					</tr>					
				</table>
			</td>
			
			<%} else if (Constants.METHOD.EDIT.equalsIgnoreCase(request
					.getParameter("method"))
					|| Constants.METHOD.SEARCH.equalsIgnoreCase(request
							.getParameter("method"))) {%>
			<td>
				<table width="100%" border="0" cellpadding="1" cellspacing="4"
						class="purpleCustomerMessages">
					<tr>
						<td>&nbsp;<bean:message key="group.edit.help" /></td>
					</tr>
				</table>
			<%} %>

			
		</tr>
		<tr>
			<td     height="10px">
			</td>
		</tr>
		<tr>
			<td><%if (Constants.METHOD.ADD.equalsIgnoreCase(request
					.getParameter("method"))
					|| Constants.METHOD.EDIT.equalsIgnoreCase(request
							.getParameter("method"))
					|| Constants.METHOD.SEARCH.equalsIgnoreCase(request
							.getParameter("method"))) {%>
			<table border="0" align="center" cellpadding="0" cellspacing="0"
				width="97%">
				<%} else {%>
				<table align="center" cellpadding="0" cellspacing="0" width="97%">
					<%}

			%>
					
					<tr>
						<%if (Constants.METHOD.ADD.equalsIgnoreCase(request
					.getParameter("method"))
					|| Constants.METHOD.EDIT.equalsIgnoreCase(request
							.getParameter("method"))
					|| Constants.METHOD.SEARCH.equalsIgnoreCase(request
							.getParameter("method"))) {%>
						<td class="eformsBorder" width="100%"><%} else {%>
						<td width="100%"><%}

			%>
						<table width="100%" border="0" cellpadding="5" cellspacing="0" class="eforms acountSearch" >
							<tr>
										<td height="10px" ></td>
									</tr>
							<tr>
								<%if (Constants.METHOD.ADD.equalsIgnoreCase(request
					.getParameter("method"))
					|| Constants.METHOD.EDIT.equalsIgnoreCase(request
							.getParameter("method"))
					|| Constants.METHOD.SEARCH.equalsIgnoreCase(request
							.getParameter("method"))) {%>
								<%if (Constants.METHOD.ADD.equalsIgnoreCase(request
						.getParameter("method"))) {%>
								<td class="captionBold"><bean:message key="group.id" />:</td>
								<td><html:text property="groupId" styleClass="inputControls"
									maxlength="50" /><bean:message key="global.mendatoryField" />
								</td>
								<%} else {%>
								<td class="captionBold"><bean:message key="group" />:</td>
								<c:if test="${sessionScope.groups != null}">

									<td><html:select property="groupList"
										styleClass="inputControls"
										onchange="setSelectedGroupId(this);">
										<html:option value="" />
										<html:options collection="groups" property="groupId"
											labelProperty="groupNameFA" />
									</html:select> <bean:message key="global.mendatoryField" /></td>
								</c:if>


								<%}%>

							</tr>

							<tr>
								<td class="captionBold"><bean:message key="group.name" />:</td>

								<%if (group != null) {%>
								<td><html:text property="groupName" styleClass="inputControls"
									maxlength="50" value="<%=group.getGroupNameFA()%>" /> <bean:message
									key="global.mendatoryField" /></td>
								<%} else {%>
								<td><html:text property="groupName" styleClass="inputControls"
									maxlength="50" /> <bean:message key="global.mendatoryField" /></td>
								<%}%>

							</tr>
							<tr>
								<td class="captionBold"><bean:message key="group.description" />:</td>
								<%if (group != null) {%>
								<td><html:text property="groupDescription"
									value="<%=group.getGroupDescriptionFA()%>"
									styleClass="inputControls" maxlength="255" /></td>
								<%} else {%>
								<td><html:text property="groupDescription"
									styleClass="inputControls" maxlength="255" /></td>
								<%}%>
							</tr>
							<tr>
								<td class="captionBold"><bean:message key="global.inactive" /></td>
								<td colspan="2"><%if (Constants.CODE_ACTIVE_FLAG.INACTIVE
						.equalsIgnoreCase(groupStatus)) {%> <input type="checkbox"
									checked="true" name="status"
									title="<bean:message key="group.title.deactivate" />" /> <%} else {%>
								<input type="checkbox" name="status"
									title="<bean:message key="group.title.deactivate" />" /> <%}%></td>
							</tr>

							<%}%>


							<%if (Constants.METHOD.ADD.equalsIgnoreCase(request
					.getParameter("method"))
					|| Constants.METHOD.EDIT.equalsIgnoreCase(request
							.getParameter("method"))
					|| Constants.METHOD.SEARCH.equalsIgnoreCase(request
							.getParameter("method"))) {%>


							<tr>
								<td colspan="2" width="100%">
								<table class="listHeader">
									<tr>
										<td class="listHeader"><bean:message key="modules.Heading" /></td>
									</tr>
								</table>
								<div
									style="width: 98%; height: 170px;overflow-Y:scroll; overflow-x:hidden ;border-bottom: 1px solid gray;;border-bottom-color: #98a8b6;">
								<table class="Listbox">
									<%if (Constants.METHOD.ADD.equalsIgnoreCase(request
						.getParameter("method"))) {

					%>
									<%for (int i = 0; i < permissionCount; i++) {

						%>

									<TR>

										<TD align="center" bgcolor="#e0e7cc" width="5%"><input
											type="checkbox" id="<%=i%>" name="checkbox"
											title="<bean:message key="modules.activate" />"
											value="<%=allPermission[i].getPermissionId()%>" /></TD>
										<TD><%=allPermission[i].getPermissionTitleFA()%></TD>
									</TR>
									<%}%>

									<%} else {%>
									<%for (int i = 0; i < permissionCount; i++) {

						%>
									<TR>
										<TD align="center" bgcolor="#e0e7cc" width="5%"><%if (allPermission[i].getPermissionStatus()
								.equalsIgnoreCase(
										Constants.CODE_ACTIVE_FLAG.ACTIVE)) {%> <input type="checkbox"
											id="<%=i%>" name="checkbox"
											title="<bean:message key="modules.deactivate" />"
											checked="true"
											value="<%=allPermission[i].getPermissionId()%>" /> <%} else {%>

										<input type="checkbox" id="<%=i%>" name="checkbox"
											title="<bean:message key="modules.activate" />"
											value="<%=allPermission[i].getPermissionId()%>" /> <%}%></TD>
										<TD><%=allPermission[i].getPermissionTitleFA()%></TD>

									</TR>


									<%}%>
									<%}%>



								</table>
								</div>
								</td>



							</tr>
							<%} else {%>
							
				
							
							
							<tr>
							
								
								<td colspan="2" width="100%"  ><html:hidden
									property="permissionIds" /> <span> <display:table
									name="groups" id="list" pagesize="10" defaultsort="1"
									requestURI="groupManagement.do?gridPagination=true" class="grid"
									scope="session">
									<display:column titleKey="group.id" sortable="true"
										property="groupId" headerClass="gridHeader" />
									<display:column titleKey="group.name" sortable="true"
										property="groupNameFA" headerClass="gridHeader" />
									<display:column headerClass="gridHeader" width="15%"
										sortable="true" titleKey="group.active" align="center"
										media="html">

										<c:if test="${list.groupActive == Active}">
											<bean:message key="global.active" />

										</c:if>

										<c:if test="${list.groupActive == InActive}">
											<bean:message key="global.inactive" />
										</c:if>


									</display:column>

									<%String pagingBannerNoItemsFound = Config.getPropertyFromBundle(
						"paging.banner.no_items_found", language, country);
				String pagingBannerOneItemFound = Config.getPropertyFromBundle(
						"paging.banner.one_item_found", language, country);
				String pagingBannerAllItemsFound = Config
						.getPropertyFromBundle("paging.banner.all_items_found",
								language, country);
				String pagingBannerSomeItemsFound = Config
						.getPropertyFromBundle(
								"paging.banner.some_items_found", language,
								country);
				String pagingBannerFull = Config.getPropertyFromBundle(
						"paging.banner.full", language, country);
				String pagingBannerFirst = Config.getPropertyFromBundle(
						"paging.banner.first", language, country);
				String pagingBannerLast = Config.getPropertyFromBundle(
						"paging.banner.last", language, country);
				String pagingBannerItemName = Config.getPropertyFromBundle(
						"paging.banner.item_name", language, country);
				String pagingBannerItemsName = Config.getPropertyFromBundle(
						"paging.banner.items_name", language, country);

				%>
									<display:setProperty name="paging.banner.no_items_found"
										value="<%=pagingBannerNoItemsFound%>" />
									<display:setProperty name="paging.banner.one_item_found"
										value="<%=pagingBannerOneItemFound%>" />
									<display:setProperty name="paging.banner.all_items_found"
										value="<%=pagingBannerAllItemsFound%>" />
									<display:setProperty name="paging.banner.some_items_found"
										value="<%=pagingBannerSomeItemsFound%>" />
									<display:setProperty name="paging.banner.full"
										value="<%=pagingBannerFull%>" />
									<display:setProperty name="paging.banner.first"
										value="<%=pagingBannerFirst%>" />
									<display:setProperty name="paging.banner.last"
										value="<%=pagingBannerLast%>" />
									<display:setProperty name="paging.banner.item_name"
										value="<%=pagingBannerItemName%>" />
									<display:setProperty name="paging.banner.items_name"
										value="<%=pagingBannerItemsName%>" />

								</display:table> </span></td>



							</tr>
							<%}%>
							<tr>
								<!--ashrafi:align right changed to center 860524/!-->
								<%if (Constants.METHOD.ADD.equalsIgnoreCase(request
					.getParameter("method"))
					|| Constants.METHOD.EDIT.equalsIgnoreCase(request
							.getParameter("method"))
					|| Constants.METHOD.SEARCH.equalsIgnoreCase(request
							.getParameter("method"))) {%>
								<td align="center" colspan="2"><%if (group != null
						|| Constants.METHOD.ADD.equalsIgnoreCase(request
								.getParameter("method"))) {%> <html:submit
									styleClass="submitButtons">
									<bean:message key="global.submitButton" />
								</html:submit> <%} else {%> <html:submit disabled="true"
									styleClass="submitButtons">
									<bean:message key="global.submitButton" />
								</html:submit> <%}%></td>
								<%}%>
							</tr>
							<tr>
										<td height="5px" ></td>
							</tr>
						</table>
						</td>
					</tr>

				</table>







			</table>
			</html:form>
</body>
