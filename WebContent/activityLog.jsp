<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.iac.ambit.utils.Config"%>
<%@ page import="com.iac.ambit.utils.DateFarsiDecorator"%>
<%@ page import="com.iac.ambit.utils.Constants"%>
<%@ page import="java.util.Locale"%>
 <%
 	Locale locale = (Locale)request.getSession().getAttribute("org.apache.struts.action.LOCALE");
 	String language=locale.getLanguage();
	String country = locale.getCountry();
 %>
<link
	href="<%= session.getAttribute( Constants.JspConstants.PATH_CSS  )%>calendar.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="javascripts/calendar.js"></script>
<script type="text/javascript" src="javascripts/lang/calendar-fa.js"></script>

<script type="text/javascript" src="javascripts/utils.js"></script>
<script type="text/javascript" src="javascripts/calendarFarsi.js"></script>

<html:javascript formName="/activitySearchAction" method="validateForm"
	dynamicJavascript="true" staticJavascript="false" cdata="false" />
<script language="Javascript1.1" src="staticJavascript.do"></script>
<script language="javascript">

function setSelectedUserId(obj){
document.all.selectedUserId.value = obj.options[obj.selectedIndex].value;
}

function setSelectedActivityTypeId(obj){
document.all.selectedActivityTypeId.value = obj.options[obj.selectedIndex].value;
}
</script>

<body dir="<%=session.getAttribute( Constants.JspConstants.PAGE_DIR  )%>">

<table width="97%" border="0" cellspacing="0" cellpadding="0"
	align="center">
	<html:form action="activitySearchAction.do" 
		onsubmit="return validateForm(this);">
		<html:hidden property="selectedActivityTypeId" />
		<html:hidden property="selectedUserId" />
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="WelcomeTableBorder">

				<tr>
					<td width="100%" height="10px"></td>
				</tr>
				<tr>
					<td class="purpleBarText" width="100%"><bean:message
						key="activityLog.topBoxHeading" /></td>
				</tr>
				
				<tr>
				<td>
				   <table width="100%" border="0" cellpadding="2" cellspacing="0"
						class="purpleCustomerMessages">
				       <tr>
					      <td><bean:message key="activityLog.help" /></td>
				        </tr>
				   </table>
			    </td>				
				</tr>
				<tr>
			       <td     height="10px">
			      </td>
		       </tr>
				<tr>
					<td class="eformsBorder">
					<table width="100%" align="center" border="0" cellpadding="5"
						class="eforms acountSearch" cellspacing="0">
						<tr><td height="10px"></td></tr>
						<tr>

							<td class="captionBold"><bean:message key="activityLog.activityType" />:</td>

							<td><html:select property="allActivity"
								styleClass="inputControls"
								onchange="setSelectedActivityTypeId(this);">
								<html:option value="" />
								<html:options collection="activityTypes" property="permissionId"
									labelProperty="permissionTitleFA" />
							</html:select></td>

						</tr>
						<tr>
						<td class="captionBold"><bean:message key="customer.name" />:</td>

							<td><html:select property="allUser" 
								styleClass="inputControls" 
								onchange="setSelectedUserId(this);">
								<html:option value=""  />
								<html:options collection="userList" property="userId"
									labelProperty="userNameFA" />
							</html:select><bean:message key="global.mendatoryField" /></td>
						</tr>
						<tr>
							<td class="captionBold"><bean:message key="global.fromDate" />:</td>
							<td><html:text styleId="fromDate" property="fromDate"
								styleClass="inputControls" maxlength="10" /> <img
								src="<%=  session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>cal.gif"
								align="middle" id="f_trigger_e" style="cursor: pointer;"
								title=<bean:message key="global.toolTip.dateCalendar" />
								onclick="displayDatePicker('fromDate');" /></td>
						</tr>
						
						<tr>
							<td class="captionBold"><bean:message key="global.toDate" />:</td>
							<td><html:text styleId="toDate" property="toDate"
								styleClass="inputControls" maxlength="10" /> <img
								src="<%=  session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>cal.gif"
								align="middle" id="t_trigger_e" style="cursor: pointer;"
								title=<bean:message key="global.toolTip.dateCalendar" />
								onclick="displayDatePicker('toDate');" /></td>
						</tr>
						
						<tr >
							<td colspan="2" align="center"><html:submit
								styleClass="submitButtons">
								<bean:message key="global.submitButton" />
							</html:submit> </td>
						</tr>
						<tr>
			             <td     height="8px">
			             </td>
		              </tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td  colspan="4" valign="top" width="100%" align="center"
				class="captionBold acountSearch">
				<c:if test="${sessionScope.ACTIVITIES != null}">
					<table width="100%" border="0" align="left">
						<tr>
							<td  class="gridAdjacentHeading"><bean:message
								key="activityLog.heading" /></td>
						</tr>
						<tr>
							<!-- ashrafi:set title to titleKey  1386/05/28 -->
							<td align="left" valign="top" width="98%"><span> <display:table
								name="ACTIVITIES" id="list" pagesize="10" requestURI="activitySearchAction.do?navigate=true"
								class="grid" defaultorder="descending" defaultsort="2"
								scope="session">
								<display:column property="activityDate" titleKey="global.date"
									style="width: 15%" sortable="true" headerClass="gridHeader"
									decorator="com.iac.ambit.utils.DateFarsiDecorator" />
								<display:column property="activityTime" titleKey="global.time"
									style="width: 10%" sortable="true" headerClass="gridHeader"
									decorator="com.iac.ambit.utils.TimeDecorator" />
								<display:column property="activityTypeNameFa"
									titleKey="activityLog.activityType" sortable="true" headerClass="gridHeader" />
								<display:column property="activityDescriptionFa" 
									titleKey="global.description" sortable="true" headerClass="gridHeader" />
								

								<%String pagingBannerNoItemsFound = Config.getPropertyFromBundle(
					"paging.banner.no_items_found", language, country);
			String pagingBannerOneItemFound = Config.getPropertyFromBundle(
					"paging.banner.one_item_found", language, country);
			String pagingBannerAllItemsFound = Config.getPropertyFromBundle(
					"paging.banner.all_items_found", language, country);
			String pagingBannerSomeItemsFound = Config.getPropertyFromBundle(
					"paging.banner.some_items_found", language, country);
			String pagingBannerFull = Config.getPropertyFromBundle(
					"paging.banner.full", language,country);
			String pagingBannerFirst = Config.getPropertyFromBundle(
					"paging.banner.first", language, country);
			String pagingBannerLast = Config.getPropertyFromBundle(
					"paging.banner.last", language, country);
			String pagingBannerItemName = Config.getPropertyFromBundle(
					"paging.banner.item_name",language, country);
			String pagingBannerItemsName = Config.getPropertyFromBundle(
					"paging.banner.items_name",language, country);

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
							<!-- ashrafi:set title to titleKey  1386/05/28 -->
						</tr>
						
					</table>
				</c:if>
				
			</td>
		</tr>
	</html:form>
</table>
</body>

