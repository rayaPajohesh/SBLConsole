<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.iac.ambit.utils.Constants"%>
<%@ page import="com.iac.ambit.utils.Config"%>
<%@ page import="com.iac.ambit.utils.AmbitUtility"%>
<!-- added by hashemi 88/04/30  -->
<script type="text/javascript">
	function setSelectedUserId(obj){
		document.all.selectedUserId.value = obj.options[obj.selectedIndex].value;
}
</script>

<link
	href="<%= session.getAttribute( Constants.JspConstants.PATH_CSS  )%>calendar.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="javascripts/calendar.js"></script>
<script type="text/javascript" src="javascripts/lang/calendar-fa.js"></script>

<script type="text/javascript" src="javascripts/utils.js"></script>
<script type="text/javascript" src="javascripts/calendarFarsi.js"></script>

<html:javascript formName="/aggregateActivitySearch"
	method="validateForm" dynamicJavascript="true" staticJavascript="true"
	cdata="false" />


<body dir="<%=session.getAttribute( Constants.JspConstants.PAGE_DIR  )%>">

<table width="97%" border="0" cellspacing="0" cellpadding="0" align="center">
<html:form action="aggregateActivitySearch.do"
					onsubmit="return validateForm(this)" >
					<html:hidden property="selectedUserId" />

	<tr>
		<td valign="top" >
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="WelcomeTableBorder">
			<tr>
					<td class="purpleBarText"><bean:message
						key="aggregateActivityLog.heading" /></td>

				</tr>
		
			 <tr>
			   <td>
				   <table width="100%" border="0" cellpadding="2" cellspacing="0"
						class="purpleCustomerMessages">
				       <tr>
					      <td><bean:message key="aggregateActivityLog.help" /></td>
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
							<td class="captionBold"><bean:message key="customer.name" />:</td>

							<td><html:select property="allUser" styleClass="inputControls"
								onchange="setSelectedUserId(this);">
								<html:option value="" />
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

						<tr>
							<td colspan="2" align="center"><html:submit
								styleClass="submitButtons">
								<bean:message key="global.submitButton" />
							</html:submit></td>
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
					<td height="10px" width="100%"></td>
				</tr>
				
			
			
				
				<tr>
					<td height="10px" width="100%"></td>
				</tr>
	<tr>
	
		<td class="acountSearch captionBold" align="center" valign="top"><c:if
			test="${sessionScope.ACTIVITIES_IN_REQUEST != null}">
			
			<div style="width: 100%; height: 200px;overflow-Y:scroll; overflow-x:hidden ;border-bottom: 1px solid gray;;border-bottom-color: #98a8b6;">
			<table width="100%" align="center" border="0" cellpadding="0"
				cellspacing="0">
				
				
				
				<tr>
					<td width="100%" align="center" valign="top" colspan="2"><display:table
						name="ACTIVITIES_IN_REQUEST" id="list"
						requestURI="aggregateActivitySearch.do?navigate=true" class="grid"
						defaultorder="descending" defaultsort="1" scope="session">
						
						<display:column property="activityTypeNameFa"
							titleKey="activityLog.activityType" sortable="true"
							headerClass="gridHeader" />
						<display:column property="aggregate"
							titleKey="aggregateActivityLog.total" sortable="true"
							headerClass="gridHeader" />
						<display:footer>
							<tr class="gridFooter">
								<td><bean:message
						key="aggregateActivityLog.allTotal" /></td>
								<td><%=AmbitUtility.nvl((String)request.getSession().getAttribute("AGGREGATE_TOTAL")) %></td>
							</tr>
						</display:footer>
					</display:table></td>
				</tr>
			</table>
			</div>
			
		</c:if></td>
		
	</tr>
	
	
	</html:form>
</table>
</body>