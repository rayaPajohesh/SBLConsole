<%@ page import="proxy.com.iac.ambit.model.BlackList"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.iac.ambit.utils.Constants"%>
<%@ page import="com.iac.ambit.utils.Config"%>
<%@ page import="java.util.Locale"%>
<%String method = request.getSession().getAttribute("method") + "";
			BlackList black = new BlackList();
			String css = "";
			if (request.getSession().getAttribute("blackListForUpdate") != null) {
				black = (BlackList) request.getSession().getAttribute(
						"blackListForUpdate");

			}
			Locale locale = (Locale) request.getSession().getAttribute(
					"org.apache.struts.action.LOCALE");
			String language = locale.getLanguage();
			String country = locale.getCountry();

			Boolean disabledPan = (Boolean) request.getSession().getAttribute(
					"disabledPan");

			%>
<c:set var="active" value="1" />
<c:set var="inActive" value="2" />
<script type="text/javascript" src="javascripts/calendar.js"></script>
<script type="text/javascript" src="javascripts/lang/calendar-fa.js"></script>
<script type="text/javascript" src="javascripts/utils.js"></script>
<script type="text/javascript" src="javascripts/calendarFarsi.js"></script>


<script language="javascript">



function getRowSelected(id) {
		
		
		document.blackListManagement.selectedPan.value = id;
		
		
	}

	

 </script>

<html:javascript formName="/blackListManagement" method="validateForm"
	dynamicJavascript="true" staticJavascript="false" cdata="false" />

<link
	href="<%= session.getAttribute( Constants.JspConstants.PATH_CSS  )%>calendar.css"
	rel="stylesheet" type="text/css" />
<c:set var="dateFarsiFormat" value="yyyy-MM-dd" scope="page" />



<html:form action="/blackListManagement.do" onsubmit="">
	<html:hidden property="selectedPan" />
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		align="center">
		<tr>
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="WelcomeTableBorder">
				<tr>
					<td class="purpleBarText" width="100%"><bean:message
						key="blackList.heading" /></td>
				</tr>

				

				


				<tr>
					<td>
					<table width="97%" align="center" cellpadding="0" cellspacing="0"
						border="0">
						<tr>
								<td>
								<table width="100%" border="0" cellpadding="2" cellspacing="0"
									class="purpleCustomerMessages">
									<tr>
										<td><bean:message key="blackList.help" /></td>
									</tr>
								</table>
								</td>
							</tr>
							
							<tr>
							
							<td height="10px" ></td>
							</tr>
						<tr>
							<td class="eformsBorder" width="100%">
							<table width="100%" border="0" cellspacing="4" cellpadding="0"
								class="eforms accountSearch" align="left"  >
								
								
								<tr>
									<td>



									<table width="100%" cellpadding="0" class="acountSearch"
										cellspacing="5" border="0" >


										<tr>
											<td class="purpleBarText" width="100%" colspan="4"><%if (Constants.METHOD.ADD.equalsIgnoreCase(method)) {%>
											<bean:message key="blackList.add" /> <%} else if (Constants.METHOD.EDIT.equalsIgnoreCase(method)) {

			%> <bean:message key="blackList.update" /> <%} else if (Constants.METHOD.DELETE.equalsIgnoreCase(method)) {

			%> <bean:message key="blackList.delete" /> <%}%></td>
										</tr>

										<%if (Constants.METHOD.SEARCH.equalsIgnoreCase(method)
					|| Constants.METHOD.CANCEL.equalsIgnoreCase(method)

			) {%>
										<tr>
											<td class="captionBold"><bean:message
												key="blackList.fromDate" />:</td>
											<td><html:text styleId="fromDate" property="fromDate"
												value="<%=(String)request.getSession().getAttribute("farsiFromDate")%>"
												styleClass="inputControls" maxlength="10" /> <img
												src="images/fa_IR/cal.gif" align="middle" id="f_trigger_e"
												style="cursor: pointer;"
												title=<bean:message key="global.toolTip.dateCalendar" />
												onclick="displayDatePicker('fromDate');" /> <bean:message
												key="global.mendatoryField" /></td>

											<td class="captionBold"><bean:message key="blackList.toDate" />:
											</td>

											<td><html:text styleId="toDate" property="toDate"
												value="<%=(String)request.getSession().getAttribute("farsiToDate")%>"
												styleClass="inputControls" maxlength="10" /> <img
												src="images/fa_IR/cal.gif" align="middle" id="t_trigger_e"
												style="cursor: pointer;"
												title=<bean:message key="global.toolTip.dateCalendar" />
												onclick="displayDatePicker('toDate');" /> <bean:message
												key="global.mendatoryField" /></td>
										</tr>
										
										<tr>
										<td  class="captionBold"><bean:message key="blackList.cardStatus" />:</td>
										<td >
										
										<html:select property="statusList"
										styleClass="inputControls">
										
										
										<html:option value="">
										</html:option>
										<html:options collection="activeFlags"
													property="codeActiveFlag"
													labelProperty="codeActiveDescriptionFA" />
											

										</html:select>
										
										</td>
										</tr>

										<%}

			%>




										<%if (Constants.METHOD.ADD.equalsIgnoreCase(method)
					|| Constants.METHOD.CANCEL.equalsIgnoreCase(method)
					|| Constants.METHOD.SEARCH.equalsIgnoreCase(method)

			) {%>
										<tr>
											<td class="captionBold" nowrap="nowrap"><bean:message key="blackList.pan" />:
											</td>
											<td><html:text property="pan"
												styleClass="inputControlsForPan" /> <html:submit
												property="blackList" titleKey="global.searchButton">
												<bean:message key="blackList.searchPan" />

											</html:submit>
											
											<%if (Constants.METHOD.ADD.equalsIgnoreCase(method))
											 { %>
											<bean:message key="global.mendatoryField" />
											
											<%
											 }  if (request.getSession().getAttribute("TOTAL_PARAM") == null) {%>
											 <font style="font: tahoma;font-size: 13;"> <bean:message
														key="global.cardVerification" /> </font>
											 <%
											 
											 }
											 %>
											</td>
											<td colspan="2" nowrap="nowrap">
											<%
											   if (request.getSession().getAttribute("TOTAL_PARAM") != null) {%>
											<font
												style="font: tahoma;font-size: 13;"> 
													
													<%=session.getAttribute("TOTAL_PARAM")%>
													
											</font>
												<%
											 
											 	}
												 %>
												</td>

										</tr>

										<%} else if (Constants.METHOD.EDIT.equalsIgnoreCase(method)
					|| Constants.METHOD.DELETE.equalsIgnoreCase(method))

			{%>


										<tr>
											<td class="captionBold"><bean:message key="blackList.pan" />:
											</td>
											<td nowrap="nowrap" colspan="2">
											
											<%if (disabledPan.booleanValue()) {

												%> 
												<font style="font: tahoma;font-size: 13;"> <%=request.getSession().getAttribute(
												"formPan")%>
												
												&nbsp;&nbsp;&nbsp;
											 <%} else {

												%> 
													<html:text property="pan" styleClass="inputControls" /> <%}

												%>
											
																							
												
													<%=session.getAttribute("TOTAL_PARAM")%>
													
												
											</font>
											
											</td>
											<td  >
											&nbsp;
											</td>

										</tr>


										<%}
			if (Constants.METHOD.EDIT.equalsIgnoreCase(method)
					&& (black.getBlackedActiveFlag()
							.equalsIgnoreCase(Constants.CODE_ACTIVE_FLAG.INACTIVE))) {%>

										<tr>
											<td class="captionBold"><bean:message
												key="blackList.activeInList" />:</td>
											<td><html:checkbox property="activeFlag" /></td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>

										<%}
			if (Constants.METHOD.ADD.equalsIgnoreCase(method)
					|| Constants.METHOD.EDIT.equalsIgnoreCase(method)
					|| Constants.METHOD.DELETE.equalsIgnoreCase(method)) {%>

										<tr>
											<td class="captionBold"><bean:message key="blackList.reason" />:</td>

											<td>
											<c:if test="${sessionScope.blackReasons != null}">
											<html:select property="blackReasons"
												disabled='<%= (Boolean)request.getSession().getAttribute("disabledReason")%>'
												styleClass="inputControls">

												<html:options collection="blackReasons"
													property="blackedReasonId"
													labelProperty="blackedReasonDescriptionFA" />
											</html:select>
											</c:if>
											</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>

										<tr>
											<td class="captionBold"><bean:message key="blackList.explain" />:</td>

											<td><html:textarea property="notes"
												disabled='<%= (Boolean)request.getSession().getAttribute("disabledNotes")%>'
												styleId="notes" styleClass="inputControlsNoborder"
												style="width: 95%" rows="5" /></td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>

										</tr>

										<tr>
											<td align="left" height="25" valign="bottom"><html:submit
												styleClass="buttons" property="blackList">
												<bean:message key="blackList.cancel" />
											</html:submit></td>

											<td align="right" height="25" valign="bottom"><html:submit
												styleClass="buttons" property="blackList">
												<bean:message key="blackList.submit" />
											</html:submit></td>
											<td colspan="2">&nbsp;</td>

										</tr>

										<%}

			%>
										<tr>

											<td colspan="4"  >&nbsp;</td>
										</tr>


										<tr>
											<td colspan="4" align="center" height="25" valign="bottom"><%if (Constants.METHOD.CANCEL.equalsIgnoreCase(method)

			|| Constants.METHOD.SEARCH.equalsIgnoreCase(method)) {%> <logic:match
												name="allowedActions"
												value="<%=Config.getProperty("View")%>">
												<html:submit styleClass="buttons" property="blackList">
													<bean:message key="blackList.search" />
												</html:submit>
											</logic:match> <logic:match name="allowedActions"
												value="<%=Config.getProperty("Add")%>">
												<html:submit styleClass="buttons" property="blackList">
													<bean:message key="blackList.add" />
												</html:submit>
											</logic:match> <logic:match name="allowedActions"
												value="<%=Config.getProperty("Edit")%>">
												<html:submit styleClass="buttons" property="blackList">

													<bean:message key="blackList.update" />
												</html:submit>
											</logic:match> <logic:match name="allowedActions"
												value="<%=Config.getProperty("Delete")%>">
												<html:submit styleClass="buttons" property="blackList">
													<bean:message key="blackList.delete" />
												</html:submit>
											</logic:match> <logic:match name="allowedActions"
												value="<%=Config.getProperty("Limit")%>">
												<html:submit styleClass="buttons" property="blackList">
													<bean:message key="blackList.limits" />
												</html:submit>
											</logic:match> <html:submit styleClass="buttons"
												property="blackList">
												<bean:message key="blackList.transactions" />
											</html:submit> <%}

			%></td>
										</tr>
                                         <tr>
			                               <td     height="4px">
			                               </td>
		                                 </tr>

										<%if (request.getSession().getAttribute("blackList") != null) {

				%>
										<tr>

											<td colspan="4" valign="top" width="100%" align="center"
												class="captionBold acountSearch">
											<table width="100%" border="0" align="left">
												<tr>
													<td class="gridAdjacentHeading"><bean:message
														key="blackList.blacklist" /></td>
												</tr>
												<tr>
													<td colspan="4">

													
													<display:table name="blackList" id="list" scope="session"
														requestURI="blackListManagement.do?gridPagination=true"
														class="grid" pagesize="10" defaultorder="descending"
														defaultsort="1">

														<%BlackList blackList = (BlackList) pageContext
						.getAttribute("list");
				String pan = "";

				if (blackList != null) {

					pan = blackList.getPan();

				}

				%>

														<display:column headerClass="gridHeader" align="center"
															bgcolor="#e0e7cc">

															<input type="radio" name="radioSelect" id="radioSelect"
																onClick="getRowSelected(<%=pan%>);" />

														</display:column>

														<display:column titleKey="blackList.pan" sortable="true"
															headerClass="gridHeader">

															<c:if test="${list.highlight == active}">
																<div class="highlight"><c:out value="${list.pan}" /></div>
															</c:if>
															<c:if test="${list.highlight == inActive}">
																<div><c:out value="${list.pan}" /></div>
															</c:if>


														</display:column>

														<display:column titleKey="blackList.Name" sortable="true"
															headerClass="gridHeader">
															<c:if test="${list.highlight == active}">
																<div class="highlight"><c:out
																	value="${list.cardInfo.nameAndFamilyName}" /></div>
															</c:if>
															<c:if test="${list.highlight == inActive}">
																<div><c:out value="${list.cardInfo.nameAndFamilyName}" /></div>
															</c:if>

														</display:column>
														<display:column titleKey="blackList.cardStatus"
															sortable="true" headerClass="gridHeader">
															<c:if test="${list.highlight == active}">
																<div class="highlight"><c:out
																	value="${list.cardInfo.cardStatusDesc}" /></div>
															</c:if>
															<c:if test="${list.highlight == inActive}">
																<div><c:out value="${list.cardInfo.cardStatusDesc}" /></div>
															</c:if>
														</display:column>

														<display:column titleKey="blackList.reason"
															sortable="true" headerClass="gridHeader">
															<c:if test="${list.highlight == active}">
																<div class="highlight"><c:out
																	value="${list.blackedReasonId}" /></div>
															</c:if>
															<c:if test="${list.highlight == inActive}">
																<div><c:out value="${list.blackedReasonId}" /></div>
															</c:if>
														</display:column>

														<display:column titleKey="blackList.statusInBlackList"
															sortable="true" headerClass="gridHeader">
															<c:if test="${list.highlight == active}">
																<div class="highlight"><c:out
																	value="${list.blackedActiveFlag}" /></div>
															</c:if>
															<c:if test="${list.highlight == inActive}">
																<div><c:out value="${list.blackedActiveFlag}" /></div>
															</c:if>
														</display:column>

														<display:column titleKey="blackList.dateIntoBlackList"
															sortable="true" headerClass="gridHeader">
															<c:if test="${list.highlight == active}">
																<div class="highlight"><c:out
																	value="${list.blackedDate}" /></div>
															</c:if>
															<c:if test="${list.highlight == inActive}">
																<div><c:out value="${list.blackedDate}" /></div>
															</c:if>
														</display:column>

														<display:column titleKey="blackList.timeIntoBlackList"
															sortable="true" headerClass="gridHeader">
															<c:if test="${list.highlight == active}">
																<div class="highlight"><c:out
																	value="${list.blackedTime}" /></div>
															</c:if>
															<c:if test="${list.highlight == inActive}">
																<div><c:out value="${list.blackedTime}" /></div>
															</c:if>
														</display:column>


														<%String pagingBannerNoItemsFound = Config.getPropertyFromBundle(
						"paging.banner.no_items_found", language, Config
								.getProperty("country.Iran"));
				String pagingBannerOneItemFound = Config.getPropertyFromBundle(
						"paging.banner.one_item_found", language, country);
				String pagingBannerAllItemsFound = Config
						.getPropertyFromBundle("paging.banner.all_items_found",
								language, Config.getProperty("country.Iran"));
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
													</display:table>
													</td>
												</tr>
												<tr align="center">
													<td colspan="6">
													<div class="exportlinks"><bean:message
														key="global.helpPrint" /> <a href="print.do"
														TARGET="_blank" STYLE="text-decoration:none" hidefocus> <img
														src="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>printer icon.jpg"
														border="0" /> </a> &nbsp;&nbsp;&nbsp; <a TARGET="_blank"
														hidefocus> <img
														src="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>excel.jpg"
														border="0" onclick="fileDownload()" style="cursor: hand;" />
													</a></div>
													</td>
												</tr>
											</table>
											</td>

										</tr>
										<%}

		%>

									</table>

									</td>
								</tr>
							</table>
							</td>
						</tr>
		
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>
<script>
function fileDownload(){

window.location.href="viewExcel.jsp?Type=blackListManagement";
	
}
</script>


