<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page import="com.iac.ambit.utils.Constants"%>
<%@ page import="com.iac.ambit.utils.Config"%>
<%@ page import="com.iac.ambit.utils.AmbitUtility"%>
<%@ page import="proxy.com.iac.ambit.model.Customer"%>
<%@ page import="proxy.com.iac.ambit.model.Group"%>
<%@ page import="java.util.Locale"%>

<%@ taglib uri="/WEB-INF/treetag.tld" prefix="tree"%>
<c:set var="Active" value="${sessionScope.Active}" />
<c:set var="InActive" value="${sessionScope.InActive}" />
<%Customer user = (Customer) request.getSession()
					.getAttribute("user");

			String userStatus = null;
			if (user != null)
				userStatus = user.getUserActive();

			Group[] allGroup = (Group[]) request.getSession().getAttribute(
					"groups");

			int groupCount = 0;
			if (allGroup != null)
				groupCount = allGroup.length;

			Locale locale = (Locale) request.getSession().getAttribute(
					"org.apache.struts.action.LOCALE");
			String language = locale.getLanguage();
			String country = locale.getCountry();

			%>
<script language="javascript">
function setSelectedUserId(obj){
document.all.selectedUserId.value = obj.options[obj.selectedIndex].value;
document.customerManagement.action = '<html:rewrite page="/customerManagement.do?method=edit" />';  
document.customerManagement.submit();
}

			function initiate(){
				document.all.groupIds.value = "";
				var exist = document.all.userId;
				if(exist){
				document.all.userId.focus();
				}
				else{
					exist = document.all.userList;
					if(exist){
						document.all.userList.focus();
					}
				}
				
				
				
			}
			
			

        </script>


<body onload="initiate();"
	dir="<%= session.getAttribute( Constants.JspConstants.PAGE_DIR  )%>">

<script language="Javascript1.1" src="staticJavascript.do"></script>
<html:form action="customerManagement.do?submitKey=true"
	>
	<html:hidden property="method" />
	<html:hidden property="groupIds" />
	<html:hidden property="selectedUserId" />
	<table cellpadding="0" cellspacing="0" border="0"
		class="WelcomeTableBorder">
		<tr>

			<%if (Constants.METHOD.ADD.equalsIgnoreCase(request
					.getParameter("method"))) {%>
			<td class="purpleBarText">
			<p><bean:message key="customer.method.add" /></p>
			</td>
			<%} else if (Constants.METHOD.EDIT.equalsIgnoreCase(request
					.getParameter("method"))) {%>
			<td class="purpleBarText">
			<p><bean:message key="customer.method.edit" /></p>
			</td>
			<%} else if (Constants.METHOD.VIEW.equalsIgnoreCase(request
					.getParameter("method"))) {%>
			<td class="purpleBarText">
			<p><bean:message key="customer.method.view" /></p>
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
					<td>&nbsp;<bean:message key="customer.add.help" /></td>
				 </tr>
				</table>
			</td>
			<%} else if (Constants.METHOD.EDIT.equalsIgnoreCase(request
					.getParameter("method"))) {%>
			<td>
				<table width="100%" border="0" cellpadding="1" cellspacing="4"
						class="purpleCustomerMessages">
				 <tr>
					<td>&nbsp;<bean:message key="customer.edit.help" /></td>
				 </tr>
				</table>
			</td>
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
							.getParameter("method"))) {%>
						<td class="eformsBorder" width="100%"><%} else {%>
						<td width="100%" ><%}

			%>
						<table width="100%" border="0" cellpadding="5" cellspacing="0" class="eforms acountSearch" >
							
							<tr><td height="10px"></td></tr>
							
							<tr>
								<%if (Constants.METHOD.ADD.equalsIgnoreCase(request
					.getParameter("method"))
					|| Constants.METHOD.EDIT.equalsIgnoreCase(request
							.getParameter("method"))) {%>
								<%if (Constants.METHOD.ADD.equalsIgnoreCase(request
						.getParameter("method"))) {%>
								<td class="captionBold"><bean:message key="customer.id" />:</td>
								<td><html:text property="userId" styleClass="inputControls"
									maxlength="50" /><bean:message key="global.mendatoryField" />
								</td>
								<%} else {%>
								<td class="captionBold"><bean:message key="customer" />:</td>
								<c:if test="${sessionScope.users != null}">
								<td><html:select property="userList" styleClass="inputControls"
									onchange="setSelectedUserId(this);">
									<html:option value="" ></html:option>
									<html:options collection="users" property="userId"
										labelProperty="userNameFA" />
								</html:select> <bean:message key="global.mendatoryField" /></td>
								</c:if>
								<%}%>

							</tr>

							<tr>
								<td class="captionBold"><bean:message key="customer.name" />:</td>
								<%if (user != null) {%>
								<td><html:text property="userName" styleClass="inputControls"
									maxlength="50" value="<%=user.getUserNameFA()%>" /> <bean:message
									key="global.mendatoryField" /></td>
								<%} else {%>
								<td><html:text property="userName" styleClass="inputControls"
									maxlength="50" /> <bean:message key="global.mendatoryField" /></td>
								<%}%>
							</tr>
							<% if (Constants.METHOD.ADD.equalsIgnoreCase(request
						.getParameter("method"))) {%>
							<tr>
								<td class="captionBold"><bean:message key="customer.password" />:</td>
								<td><html:password property="userPassword"
									styleClass="inputControls" maxlength="15" /><bean:message key="global.mendatoryField" /></td>
							</tr>
							<tr>
								<td class="captionBold"><bean:message
									key="customer.confirmPassword" />:</td>
								<td><html:password property="confirmPassword"
									styleClass="inputControls" maxlength="15" /><bean:message key="global.mendatoryField" /></td>
							</tr>
							<%}else if(Constants.METHOD.EDIT.equalsIgnoreCase(request
							.getParameter("method"))){%>
							<tr>
								<td class="captionBold"><bean:message key="customer.password" />:</td>
								<td><html:password property="userPassword"
									styleClass="inputControls" maxlength="15" /></td>
							</tr>
							<tr>
								<td class="captionBold"><bean:message
									key="customer.confirmPassword" />:</td>
								<td><html:password property="confirmPassword"
									styleClass="inputControls" maxlength="15" /></td>
							</tr>
							<%}%>
							<tr>
								<td class="captionBold"><bean:message key="customer.status" /></td>
								<td colspan="2"><%if (Constants.CODE_ACTIVE_FLAG.INACTIVE
						.equalsIgnoreCase(userStatus)) {

				%> <input type="checkbox" checked="true" name="status"
									title="<bean:message key="customer.title.deactivate" />" /> <%} else {%>
								<input type="checkbox" name="status"
									title="<bean:message key="customer.title.deactivate" />" /> <%}%></td>
							</tr>

							<%}%>

							<%if (Constants.METHOD.ADD.equalsIgnoreCase(request
					.getParameter("method"))
					|| Constants.METHOD.EDIT.equalsIgnoreCase(request
							.getParameter("method"))) {%>


							<tr>
								<td colspan="2" width="100%">
								<table class="listHeader">
									<tr>
										<td class="listHeader"><bean:message key="group.Heading" /></td>
									</tr>
								</table>
								<div
									style="width: 98%;height: 170px;overflow-Y:scroll; overflow-x:hidden ;border-bottom: 1px solid gray;;border-bottom-color: #98a8b6;">
								<table class="Listbox">
									<%if (Constants.METHOD.ADD.equalsIgnoreCase(request
						.getParameter("method"))) {%>
									<%for (int i = 0; i < groupCount; i++) {

						%>

									<TR>
										<TD align="center" bgcolor="#e0e7cc" width="5%"><input
											type="checkbox" id="<%=i%>" name="checkbox"
											title="<bean:message key="modules.activate" />"
											value="<%=allGroup[i].getGroupId()%>" /></TD>
										<TD><%=allGroup[i].getGroupNameFA()%></TD>

									</TR>

									<%}%>
									<%} else {%>

									<%for (int i = 0; i < groupCount; i++) {

						%>
									<TR>
										<TD align="center" bgcolor="#e0e7cc" width="5%"><%if (allGroup[i].getGroupActive().equalsIgnoreCase(
								Constants.CODE_ACTIVE_FLAG.ACTIVE)) {%> <input type="checkbox"
											id="<%=i%>" name="checkbox"
											title="<bean:message key="modules.deactivate" />"
											checked="true" value="<%=allGroup[i].getGroupId()%>" /> <%} else {%>

										<input type="checkbox" id="<%=i%>" name="checkbox"
											title="<bean:message key="modules.activate" />"
											value="<%=allGroup[i].getGroupId()%>" /> <%}%></TD>
										<TD><%=allGroup[i].getGroupNameFA()%></TD>

									</TR>


									<%}%>
									<%}%>

								</table>
								</div>
								</td>



							</tr>
							<%} else {%>
							<tr>
								<td colspan="2" width="100%"><html:hidden property="groupIds" />
								<span> <display:table name="users" id="list" pagesize="10"
									defaultsort="1"
									requestURI="customerManagement.do?gridPagination=true" class="grid"
									scope="session">
									<display:column titleKey="customer.id" sortable="true"
										property="userId" headerClass="gridHeader" />
									<display:column titleKey="customer.name" sortable="true"
										property="userNameFA" headerClass="gridHeader" />
									<display:column headerClass="gridHeader" sortable="true"
										width="20%" titleKey="customer.active" align="center"
										media="html">

										<c:if test="${list.userActive == Active}">
											<bean:message key="global.active" />

										</c:if>

										<c:if test="${list.userActive == InActive}">
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
							.getParameter("method"))) {%>
								<td align="center" colspan="2"><%if (user != null
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
		                  	  <td     height="5px">
			                  </td>
		                     </tr>
						</table>
						</td>
					</tr>

				</table>
			</table>
			</html:form>
</body>
