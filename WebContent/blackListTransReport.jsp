<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.iac.ambit.utils.Config"%>
<%@ page import="com.iac.ambit.utils.Constants"%>
<%@ page import="java.util.Locale"%>

<%String selectedPan = (String) request.getAttribute("selectedPan");
			String pan = "";
			if (selectedPan != null)
				pan = selectedPan;
			Locale locale = (Locale) request.getSession().getAttribute(
					"org.apache.struts.action.LOCALE");
			String language = locale.getLanguage();
			String country = locale.getCountry();
			
String fromDateFarsi = (String) request.getAttribute("fromDateFarsi");
			String fromDate = "";			
			if (fromDateFarsi != null)
				fromDate = fromDateFarsi;
				
String toDateFarsi = (String) request.getAttribute("toDateFarsi");
			String toDate = "";			
			if (toDateFarsi != null)
				toDate = toDateFarsi;				

			%>
<link
	href="<%= session.getAttribute( Constants.JspConstants.PATH_CSS  )%>calendar.css"
	rel="stylesheet" type="text/css" />

<c:set var="dateFarsiFormat" value="yyyy-MM-dd" scope="page" />
<c:set var="locale" value="${sessionScope.CUSTOMER_IN_SESSION.language}" />
<c:set var="Persian" value="fa" />
<c:set var="English" value="en" />
<script type="text/javascript" src="javascripts/calendar.js"></script>
<script type="text/javascript" src="javascripts/lang/calendar-fa.js"></script>
<script type="text/javascript" src="ja?ascripts/utils.js"></script>
<script type="text/javascript" src="javascripts/calendarFarsi.js"></script>

<html:javascript formName="/blackListTransReportAction"
	method="validateForm" dynamicJavascript="true" staticJavascript="false"
	cdata="false" />




<script language="Javascript1.1" src="staticJavascript.do"></script>

<table width="97%" border="0" cellspacing="0" cellpadding="0"
	align="center">
	<html:form action="/blackListTransReportAction.do"
		onreset="resetDates();" onsubmit="return validateForm(this);">

		
		
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="WelcomeTableBorder">

				<tr>
					<td width="100%" height="10px"></td>
				</tr>
				<tr>
					<td class="purpleBarText" width="100%"><bean:message
						key="blackListTransReport.title" /></td>
				</tr>
				<tr>
					<td height="10px" width="100%"></td>
				</tr>
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="2" cellspacing="0"
						class="purpleCustomerMessages">
						<tr>
							<td><bean:message key="blackListTransReport.help" /></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="10px"></td>
				</tr>
				<tr>
					<td class="eformsBorder">
					<table width="100%" align="center" border="0" cellpadding="5"
						class="eforms acountSearch" cellspacing="0">
						<tr>
							<td height="10px" colspan="4"></td>
						</tr>

						<tr>
							<td align="left" class="captionBold"><bean:message
								key="blackListTransReport.fromDate" />:</td>

							<td align="right"><html:text styleId="fromDate" value="<%=fromDate%>"
								property="fromDate" styleClass="inputControls" maxlength="10" />
							<img src="images/fa_IR/cal.gif" align="middle" id="f_trigger_e"
								style="cursor: pointer;"
								title=<bean:message key="global.toolTip.dateCalendar" />
								onclick="displayDatePicker('fromDate');" /> <bean:message
								key="global.mendatoryField" /></td>


							<td align="left" class="captionBold"><bean:message
								key="blackListTransReport.toDate" />:</td>

							<td align="right"><html:text styleId="toDate" property="toDate" value="<%=toDate%>"
								styleClass="inputControls" maxlength="10" /> <img
								src="images/fa_IR/cal.gif" align="middle" id="t_trigger_e"
								style="cursor: pointer;"
								title=<bean:message key="global.toolTip.dateCalendar" />
								onclick="displayDatePicker('toDate');" /> <bean:message
								key="global.mendatoryField" /></td>


						</tr>

						<tr>
							<td align="left" class="captionBold"><bean:message
								key="blackListTransReport.fromTime" />:</td>

							<td align="right"><html:select property="timeListFrom"
								styleClass="inputControls">
								<html:options name="hours" />

							</html:select></td>


							<td align="left" class="captionBold"><bean:message
								key="blackListTransReport.toTime" />:</td>
							<td align="right"><html:select property="timeListTo"
								styleClass="inputControls">
								<html:options name="hours" />

							</html:select></td>
						</tr>
						<tr>
							<td align="left" class="captionBold"><bean:message
								key="blackListTransReport.pan" />:</td>
							<td colspan="3" align="right"><html:text property="pan"
								styleClass="inputControlsForPan" value="<%=pan%>" maxlength="19" /> <html:submit
								property="blackListTransReport" titleKey="global.searchButton">
								<bean:message key="blackList.searchPan" />

							</html:submit><font style="font: tahoma;font-size: 13;"> <c:choose>
								<c:when test="${(sessionScope.TOTAL_PARAM == null)}">
									<font style="font: tahoma;font-size: 13;"> <bean:message
										key="global.cardVerification" /> </font>
								</c:when>
								<c:otherwise>
									<%=session.getAttribute("TOTAL_PARAM")%>
								</c:otherwise>
							</c:choose> </font></td>
						</tr>
						<tr>
							<td align="left" class="captionBold"><bean:message
								key="blackListTransReport.stan" />:</td>

							<td align="right"><html:text property="stan"
								styleClass="inputControls" maxlength="6" /></td>
							<td align="left" class="captionBold"><bean:message
								key="blackListTransReport.rrn" />:</td>

							<td align="right"><html:text property="RRN"
								styleClass="inputControls" maxlength="12" /></td>

						</tr>
						<tr align="center">
							<td colspan="4" align="center"><html:submit
								styleClass="submitButtons">
								<bean:message key="global.submitButton" />
							</html:submit> <%if (pan != "") {

			%> <html:cancel styleClass="submitButtons">
								<bean:message key="global.backButton" />
							</html:cancel> <%}%> <html:reset styleClass="submitButtons"
								onclick="resetDates()">
								<bean:message key="global.resetButton" />
							</html:reset></td>
						</tr>
						<tr>
							<td height="10px" colspan="4"></td>
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
			<td colspan="4" valign="top" width="100%" align="center"
				class="captionBold acountSearch"><c:if
				test="${sessionScope.blackListTrans != null}">
				<table width="100%" border="0" align="left">
					<tr>
						<td class="gridAdjacentHeading"><bean:message
							key="blackListTransReport.list" /></td>
					</tr>
					<tr>

						<td align="left" valign="top" width="98%"><span> <display:table
							name="blackListTrans" id="list" pagesize="10"
							requestURI="blackListTransReportAction.do?gridPagination=true"
							class="grid" defaultorder="descending" defaultsort="2"
							scope="session">
							<display:column property="transactionDate"
								titleKey="blackListTransReport.date" style="width: 10%"
								sortable="true" headerClass="gridHeader" />


							<display:column property="transactionTime"
								titleKey="blackListTransReport.time" style="width: 10%"
								sortable="true" headerClass="gridHeader" />


							<display:column property="pan"
								titleKey="blackListTransReport.pan" style="width:10%"
								sortable="true" headerClass="gridHeader" />

							<display:column property="transactionCode"
								titleKey="blackListTransReport.transactionType"
								style="width:10%" sortable="true" headerClass="gridHeader" />

							<display:column property="stan"
								titleKey="blackListTransReport.stan" style="width: 10%"
								sortable="true" headerClass="gridHeader" />


							<display:column property="retrievalReferenceNumber"
								style="width: 10%" titleKey="blackListTransReport.rrn"
								sortable="true" headerClass="gridHeader" />

							<display:column property="responseCode" style="width: 10%"
								titleKey="blackListTransReport.responseCode" sortable="true"
								headerClass="gridHeader" />

							<display:column property="terminalId" style="width: 10%"
								titleKey="blackListTransReport.terminalId" sortable="true"
								headerClass="gridHeader" />


							<display:column property="amount" style="width: 10%"
								titleKey="blackListTransReport.amount" sortable="true"
								headerClass="gridHeader"
								decorator="com.iac.ambit.utils.AmountDecoratot" />


				
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
				<tr align="center">
					<td colspan="6">
					<div class="exportlinks"><a
						href="print.do" TARGET="_blank" STYLE="text-decoration:none"
						hidefocus> <img
						src="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>printer icon.jpg"
						border="0" /> </a> &nbsp;&nbsp;&nbsp; <a TARGET="_blank"
						hidefocus> <img
						src="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>excel.jpg"
						border="0" onclick="fileDownload()" style="cursor: hand;" /> </a>
					</div>
					</td>
				</tr>
			</c:if></td>
		</tr>
	</html:form>
</table>
<script>
function fileDownload(){

window.location.href="viewExcel.jsp?Type=blackListTransReport";
	
}
</script>
