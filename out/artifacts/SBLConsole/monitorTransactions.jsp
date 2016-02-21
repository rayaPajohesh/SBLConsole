<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.iac.ambit.utils.Config"%>
<%@ page import="com.iac.ambit.utils.Constants"%>
<%@ page import="com.iac.ambit.utils.AmbitUtility"%>
<%@ page import="proxy.com.iac.ambit.model.TransactionLog"%>
<%@ page import="java.util.Locale"%>

<%Locale locale = (Locale) request.getSession().getAttribute(
					"org.apache.struts.action.LOCALE");
			String language = locale.getLanguage();
			String country = locale.getCountry();
			String ua;
			boolean isFirefox;
%>



<script>

var countdown;
var countdown_number;
var countingStopPoint;

function countdown_init() {

    countdown_number = <%=session.getAttribute("monitorIntervalInSec")%>;
  
    countdown_trigger();
}

function countdown_trigger() {

    if(countdown_number > 0) {
        countdown_number--;
        document.getElementById('countdown_text').innerHTML = countdown_number;
        
        if(countdown_number > 0) {
            countdown = setTimeout('countdown_trigger()', 1000);
        }else{

         document.monitorTransForm.action = '<html:rewrite page="/blackListMonitoring.do?refreshType=automatic" />';  
        document.monitorTransForm.submit();
        }
      
         <% ua = request.getHeader("User-Agent");
			 isFirefox = (ua != null && ua.indexOf("Firefox/") != -1);
		
        if (isFirefox) {

				%>
      document.getElementById('spanSound').innerHTML = '<embed hidden="true" id="embed" src="notify.wav" type="application/x-mplayer2" loop="false" autostart="true" playcount="<%= request.getSession().getAttribute("alertTimeInSec")%>"   />';
      <% } %> 
      
    }
}

function countdown_clear() {

    clearTimeout(countdown);   
}

function stopStartReplace(){

if(document.getElementById('stopstart').value == '<bean:message key="monitorTransactions.start" />'){
document.getElementById('stopstart').value='<bean:message key="monitorTransactions.stop" />';
countdown_number=countingStopPoint;
countdown_trigger();

}else
{
countingStopPoint = countdown_number;
document.getElementById('stopstart').value='<bean:message key="monitorTransactions.start" />';

countdown_clear();

}
}
 
</script>

<html:javascript formName="monitorTransForm"
	method="validateForm" dynamicJavascript="true" staticJavascript="true"
	cdata="false" />


<c:set var="New" value="new" />
<c:set var="Old" value="old" />
<c:set var="NewAllowed" value="newAllowed" />

<body onload="countdown_init()">
<link
	href="<%= session.getAttribute( Constants.JspConstants.PATH_CSS  )%>calendar.css"
	rel="stylesheet" type="text/css" />

<c:set var="dateFarsiFormat" value="yyyy-MM-dd" scope="page" />
<c:set var="locale" value="${sessionScope.CUSTOMER_IN_SESSION.language}" />
<c:set var="Persian" value="fa" />
<c:set var="English" value="en" />
<script type="text/javascript" src="javascripts/calendar.js"></script>
<script type="text/javascript" src="javascripts/lang/calendar-fa.js"></script>
<script type="text/javascript" src="javascripts/utils.js"></script>
<script type="text/javascript" src="javascripts/calendarFarsi.js"></script>

<table width="97%" border="0" cellspacing="2" cellpadding="2"
	align="center">

	<html:form action="blackListMonitoring.do"  
		onsubmit="return validateForm(this);">

		<tr>
			<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="WelcomeTableBorder">

				<tr>
					<td height="12px"></td>
				</tr>
				<tr>
					<td class="purpleBarText"><bean:message
						key="monitorTransactions.title" /></td>
				</tr>




				<tr>
					<td colspan="4" valign="top" width="100%" align="center"
						class="captionBold acountSearch">
					<table width="100%" border="0" align="left">
						<tr>
							<td class="gridAdjacentHeading"><bean:message
								key="monitorTransactions.list" /></td>
						</tr>
						<tr>

							<td align="left" valign="top" width="98%"><span> <display:table
								name="monitorTrans" id="list" pagesize="10"
								requestURI="blackListMonitoring.do?gridPagination=true"
								class="grid"  scope="session">
								<display:column titleKey="monitorTransactions.date"
									style="width: 10%"  headerClass="gridHeader">
									<c:if test="${list.appearanceStatus == New}">
										<div class="newHighlight"><c:out
											value="${list.transactionDate}" /></div>
									</c:if>
									<c:if test="${list.appearanceStatus == Old}">
										<div class="oldHighlight"><c:out
											value="${list.transactionDate}" /></div>
									</c:if>
									<c:if test="${list.appearanceStatus == NewAllowed}">
										<div class="newAllowedHighlight"><c:out
											value="${list.transactionDate}" /></div>
									</c:if>
								</display:column>


								<display:column titleKey="monitorTransactions.time"
									style="width: 10%"  headerClass="gridHeader">
									<c:if test="${list.appearanceStatus == New}">
										<div class="newHighlight"><c:out
											value="${list.transactionTime}" /></div>
									</c:if>
									<c:if test="${list.appearanceStatus == Old}">
										<div class="oldHighlight"><c:out
											value="${list.transactionTime}" /></div>
									</c:if>
									<c:if test="${list.appearanceStatus == NewAllowed}">
										<div class="newAllowedHighlight"><c:out
											value="${list.transactionTime}" /></div>
									</c:if>
									
								</display:column>


								<display:column titleKey="monitorTransactions.pan"
									style="width:10%"  headerClass="gridHeader">
									<c:if test="${list.appearanceStatus == New}">
										<div class="newHighlight"><c:out value="${list.pan}" /></div>
									</c:if>
									<c:if test="${list.appearanceStatus == Old}">
										<div class="oldHighlight"><c:out value="${list.pan}" /></div>
									</c:if>
									<c:if test="${list.appearanceStatus == NewAllowed}">
										<div class="newAllowedHighlight"><c:out
											value="${list.pan}" /></div>
									</c:if>							
								</display:column>

								<display:column titleKey="monitorTransactions.transactionType"
									style="width:10%"  headerClass="gridHeader">
									<c:if test="${list.appearanceStatus == New}">
										<div class="newHighlight"><c:out
											value="${list.transactionCode}" /></div>
									</c:if>
									<c:if test="${list.appearanceStatus == Old}">
										<div class="oldHighlight"><c:out
											value="${list.transactionCode}" /></div>
									</c:if>
									<c:if test="${list.appearanceStatus == NewAllowed}">
										<div class="newAllowedHighlight"><c:out
											value="${list.transactionCode}" /></div>
									</c:if>									
								</display:column>


								<display:column titleKey="monitorTransactions.stan"
									style="width: 10%"  headerClass="gridHeader">
									<c:if test="${list.appearanceStatus == New}">
										<div class="newHighlight"><c:out value="${list.stan}" /></div>
									</c:if>
									<c:if test="${list.appearanceStatus == Old}">
										<div class="oldHighlight"><c:out value="${list.stan}" /></div>
									</c:if>
									<c:if test="${list.appearanceStatus == NewAllowed}">
										<div class="newAllowedHighlight"><c:out
											value="${list.stan}" /></div>
									</c:if>								
								</display:column>

								<display:column style="width: 10%"
									titleKey="monitorTransactions.rrn" 
									headerClass="gridHeader">
									<c:if test="${list.appearanceStatus == New}">
										<div class="newHighlight"><c:out
											value="${list.retrievalReferenceNumber}" /></div>
									</c:if>
									<c:if test="${list.appearanceStatus == Old}">
										<div class="oldHighlight"><c:out
											value="${list.retrievalReferenceNumber}" /></div>
									</c:if>
									<c:if test="${list.appearanceStatus == NewAllowed}">
										<div class="newAllowedHighlight"><c:out
											value="${list.retrievalReferenceNumber}" /></div>
									</c:if>									
								</display:column>

								<display:column style="width: 10%"
									titleKey="monitorTransactions.responseCode" 
									headerClass="gridHeader">
									<c:if test="${list.appearanceStatus == New}">
										<div class="newHighlight"><c:out value="${list.responseCode}" />
										</div>
									</c:if>
									<c:if test="${list.appearanceStatus == Old}">
										<div class="oldHighlight"><c:out value="${list.responseCode}" />
										</div>
									</c:if>
									<c:if test="${list.appearanceStatus == NewAllowed}">
										<div class="newAllowedHighlight"><c:out
											value="${list.responseCode}" /></div>
									</c:if>									
								</display:column>

								<display:column style="width: 10%"
									titleKey="monitorTransactions.terminalId" 
									headerClass="gridHeader">
									<c:if test="${list.appearanceStatus == New}">
										<div class="newHighlight"><c:out value="${list.terminalId}" />
										</div>
									</c:if>
									<c:if test="${list.appearanceStatus == Old}">
										<div class="oldHighlight"><c:out value="${list.terminalId}" />
										</div>
									</c:if>
									<c:if test="${list.appearanceStatus == NewAllowed}">
										<div class="newAllowedHighlight"><c:out
											value="${list.terminalId}" /></div>
									</c:if>								
								</display:column>


								<display:column style="width: 10%"
									titleKey="monitorTransactions.amount" 
									headerClass="gridHeader">
									<c:if test="${list.appearanceStatus == New}">
										<div class="newHighlight"><c:out value="${list.amount}" /></div>
									</c:if>
									<c:if test="${list.appearanceStatus == Old}">
										<div class="oldHighlight"><c:out value="${list.amount}" /></div>
									</c:if>
									<c:if test="${list.appearanceStatus == NewAllowed}">
										<div class="newAllowedHighlight"><c:out
											value="${list.amount}" /></div>
									</c:if>									
								</display:column>

								<%String pagingBannerNoItemsFound = Config.getPropertyFromBundle(
					"paging.banner.no_items_found", language, country);
			String pagingBannerOneItemFound = Config.getPropertyFromBundle(
					"paging.banner.one_item_found", language, country);
			String pagingBannerAllItemsFound = Config.getPropertyFromBundle(
					"paging.banner.all_items_found", language, country);
			String pagingBannerSomeItemsFound = Config.getPropertyFromBundle(
					"paging.banner.some_items_found", language, country);
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

					</table>
					</td>
				</tr>

				<tr>
					<td>
					<table width="100%" border="0" cellpadding="2" cellspacing="0"
						class="purpleCustomerMessages">
						<tr>
							<td><bean:message key="monitorTransactions.help" /></td>
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
						dir="rtl" class="eforms acountSearch" cellspacing="0">
						<tr>
							<td height="10px" colspan="4"></td>
						</tr>
						<tr>

							<td width="45%" align="right" class="captionBold"><bean:message
								key="monitorTransactions.remainingAmount" />&nbsp;:</td>
							<td width="25%">
							<div id="countdown_text" class="counter" align="center"></div>
							</td>
							<td><%TransactionLog[] monitorTrans = (TransactionLog[]) request
					.getSession().getAttribute("monitorTrans");

			if (!AmbitUtility.isEmpty((String) request.getSession()
					.getAttribute("isAlert"))
					&& (monitorTrans != null)
					&& (request.getAttribute("wasRecordFound") == "true")) {%> <%ua = request.getHeader("User-Agent");
				isFirefox = (ua != null && ua.indexOf("Firefox/") != -1);

				if (isFirefox) {

					%> <object id="beep" type="application/x-mplayer2" height="0"
								width="0">
								<param name="fileName" value="notify.wav">
								<param name="autostart" value="true">
								<param name="playcount"
									value="<%= request.getSession().getAttribute("alertTimeInSec")%>">
								<param name="loop" value="false">
								<param name="hidden" value="true">
							</object> <%}%> <span id="spanSound"> <embed hidden="true"
								id="embed" src="notify.wav" type="application/x-mplayer2"
								loop="false" autostart="true"
								playcount="<%= request.getSession().getAttribute("alertTimeInSec")%>" /></span>

							<%}%></td>
						</tr>
						<tr>
							<td style="font: tahoma;font-size: 13;" align="right" colspan="4">&nbsp;&nbsp;<bean:message
								key="monitorTransactions.RefreshIntervalInSecDescription1" /><%=request.getSession().getAttribute(
									"monitorIntervalInSec")%>
							<bean:message
								key="monitorTransactions.RefreshIntervalInSecDescription2" /></td>
						</tr>

						<tr>

							<td class="captionBold" colspan="3" align="center"><bean:message
								key="monitorTransactions.monitorIntervalInSec" />&nbsp;: <html:text
								styleId="refreshIntervalInSec" property="refreshIntervalInSec"
								styleClass="inputControls" maxlength="8" />&nbsp;<bean:message
								key="monitorTransactions.Second" /></td>
							<td align="right" width="40%"><html:submit styleClass="buttons"
								property="monitorTransactions">
								<bean:message
									key="monitorTransactions.confirmRefreshIntervalInSec" />
							</html:submit></td>

						</tr>
						<tr>

							<td style="font: tahoma;font-size: 13;" align="right" colspan="4">&nbsp;&nbsp;<bean:message
								key="monitorTransactions.AlertInfoDescription1" /><%=request.getSession().getAttribute("PARAM")%>
							<bean:message key="monitorTransactions.AlertInfoDescription2" />
							<bean:message key="monitorTransactions.AlertInfoDescription3" /><%=request.getSession().getAttribute("alertTimeInSec")%>
							<bean:message key="monitorTransactions.AlertInfoDescription4" />

							</td>
						</tr>

						<tr>

							<td class="captionBold" align="right" colspan="3"><bean:message
								key="monitorTransactions.isAlert" /> <input type="checkbox"
								name="isAlertName"
								<%=AmbitUtility.nvl(((String)request.getSession().getAttribute("isAlert")))%> />
							<bean:message key="monitorTransactions.alertTimeInSec" />:&nbsp;<html:text
								styleId="alertTimeInSec" property="alertTimeInSec"
								styleClass="inputControls" maxlength="8" />&nbsp;<bean:message
								key="monitorTransactions.Second" /></td>
							<td><html:submit styleClass="buttons" style="width:120"
								property="monitorTransactions">
								<bean:message key="monitorTransactions.confirmAlertInfo" />
							</html:submit></td>


						</tr>
						<tr>
							<td align="left" colspan="2"><html:submit style="width:100"
								styleClass="buttons" property="monitorTransactions">
								<bean:message key="monitorTransactions.manuallyRefresh" />
							</html:submit> <INPUT id="stopstart" class="buttons"
								style="width:140" type="button"
								value="<bean:message key="monitorTransactions.stop" />"
								onclick="stopStartReplace();" /></td>

						</tr>
						<tr>
							<td height="10px"></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr align="center">
			<td colspan="6">
			<div class="exportlinks"><c:if
				test="${requestScope.monitorTrans != null}">
				<a href="print.do" TARGET="_blank" STYLE="text-decoration:none"
					hidefocus> <img
					src="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>printer icon.jpg"
					border="0" /> </a> &nbsp;&nbsp;&nbsp; <a TARGET="_blank" hidefocus>
				<img
					src="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>excel.jpg"
					border="0" onclick="fileDownload()" style="cursor: hand;" /> </a>
			</c:if></div>
			</td>
		</tr>

	</html:form>
</table>
<script>
function fileDownload(){

window.location.href="viewExcel.jsp?Type=blackListMonitoring";
	
}


</script>
</body>
