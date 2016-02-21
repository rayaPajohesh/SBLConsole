<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page import="com.iac.ambit.utils.AmbitUtility"%>
<%@ page import="proxy.com.iac.ambit.model.Customer"%>
<%@ page import="com.iac.ambit.utils.Constants"%>
<link href="css/fa-IR/default.css" rel="stylesheet"
	type="text/css" />
<%@ include file="printHeader.jsp"%>

<%int gridRows;

			gridRows = 10;

			Customer cust = (Customer) request.getSession().getAttribute(
					"CUSTOMER_IN_SESSION");

%>
<jsp:useBean id="CUSTOMER_IN_SESSION"
	type="proxy.com.iac.ambit.model.Customer" scope="session" />

<c:set var="locale" value="${sessionScope.CUSTOMER_IN_SESSION.language}" />
<c:set var="Persian" value="fa" />
<c:set var="English" value="en" />
<c:set var="dateFormat" value="dd/MM/yyyy" scope="page" />
<c:set var="rangeDateFormat" value="dd-MMM-yy" scope="page" />
<c:set var="timeFormat" value="hh:mm a" scope="page" />
<fmt:setLocale value="fa-IR" />
<c:set var="alignment" value="right" scope="request" />
<c:set var="rotation" value="rtl" scope="request" />
<body>
<table cellpadding="0" cellspacing="5" border="0" width="100%"
	class="acountSearch">
	
	<tr align="center">
		<td height="148" width="100%" align="center" colspan="3"><img
			src="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE)%>printHeader.gif" /></td>
	</tr>
	<tr>
		<td align="center" class="printHeadingText" colspan="3"  ><bean:message
			key="blackList.heading" /></td>
	</tr>
	<tr>
			<td  width="40%">&nbsp;</td>
		
      <td align="right"  dir="rtl"  ><b><bean:message key="blackList.fromDate" />:
		</b> &nbsp;&nbsp; <c:out value="${fromDate}" />   &nbsp;&nbsp;&nbsp;&nbsp; <b><bean:message
			key="blackList.toDate" />: </b> &nbsp;&nbsp; <c:out
			value="${toDate}" /></td>
		<td class="direction" align="right"  dir="rtl"  ><b><bean:message
			key="blackList.pan" />:</b> &nbsp; <c:out
			value="${pan}" /></td>	
	</tr>

	
	


	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td colspan="3">
		<table dir=<%=(String)request.getAttribute("rotation")%>
			cellpadding="4px 4px 4px 4px" cellspacing="0" border="0"
			class="printerFriendlyTable" width="100%">
			<tr style="font-size:9px">
				<td class="gridHeader printerFriendlyCell" width="75px" align="left">
				<bean:message key="blackList.pan" /></td>
				
				<td class="gridHeader printerFriendlyCell" width="74px" align="left">
				<bean:message key="blackList.Name" /></td>
				
				<td class="gridHeader printerFriendlyCell" width="100px"
					align="left"><bean:message key="blackList.cardStatus" />
					</td>
					
				<td class="gridHeader printerFriendlyCell" width="100px"
					align="left"><bean:message
					key="blackList.reason" />
					</td>
				<td class="gridHeader printerFriendlyCell" width="100px"
					align="left"><bean:message key="blackList.statusInBlackList" />
					</td>
				<td class="gridHeader printerFriendlyCell" width="100px"
					align="left"><bean:message key="blackList.dateIntoBlackList" />
					</td>
				<td class="gridHeader printerFriendlyCell" width="100px"
					align="left"><bean:message key="blackList.timeIntoBlackList" />
					</td>
				
			</tr>
			<c:set var="evenOdd" value="0" />
			<c:forEach items="${printList}" var="blackList">

				<tr style="font-size:9px" valign="center">
					<c:choose>
						<c:when test="${evenOdd % 2 == 0}">
							<td class="gridEvenLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${blackList.pan != null && blackList.pan !=''}">
								<c:out value="${blackList.pan}" />
							</c:if> <c:if
								test="${blackList.pan == null || blackList.pan ==''}">
                    	&nbsp;
                    </c:if></td>
							<td class="gridEvenLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${blackList.cardInfo.nameAndFamilyName != null && blackList.cardInfo.nameAndFamilyName !=''}">
								<c:out value="${blackList.cardInfo.nameAndFamilyName}" />
							</c:if> <c:if
								test="${blackList.cardInfo.nameAndFamilyName == null || blackList.cardInfo.nameAndFamilyName ==''}">
                    	             &nbsp;
                                       </c:if></td>
							<td class="gridEvenLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${blackList.cardInfo.cardStatusDesc != null && blackList.cardInfo.cardStatusDesc !=''}">
								<c:out value="${blackList.cardInfo.cardStatusDesc}" />&nbsp;
                    </c:if> <c:if
								test="${blackList.cardInfo.cardStatusDesc == null || blackList.cardInfo.cardStatusDesc ==''}">
                    	&nbsp;
                    </c:if></td>
							<td class="gridEvenLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${blackList.blackedReasonId != null && blackList.blackedReasonId !=''}">
								<c:out value="${blackList.blackedReasonId}" />
							</c:if> <c:if
								test="${blackList.blackedReasonId == null || blackList.blackedReasonId ==''}">
                    	&nbsp;
                    </c:if></td>
							<td class="gridEvenLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${blackList.blackedActiveFlag != null && blackList.blackedActiveFlag !=''}">
								<c:out value="${blackList.blackedActiveFlag}" />
							</c:if> <c:if
								test="${blackList.blackedActiveFlag == null || blackList.blackedActiveFlag ==''}">
                    	&nbsp;
                    </c:if></td>
							<td class="gridEvenLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${blackList.blackedDate != null && blackList.blackedDate !=''}">
								<c:out value="${blackList.blackedDate}" />
							</c:if> <c:if
								test="${blackList.blackedDate == null || blackList.blackedDate ==''}">
                    	&nbsp;
                      </c:if></td>
							<td class="gridEvenLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${blackList.blackedTime != null && blackList.blackedTime !=''}">
								<c:out value="${blackList.blackedTime}" />
							</c:if> <c:if
								test="${blackList.blackedTime == null || blackList.blackedTime ==''}">
                    	&nbsp;
                      </c:if></td>
							


							
						</c:when>
						<c:otherwise>
							<td class="gridOddLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${blackList.pan != null && blackList.pan !=''}">
								<c:out value="${blackList.pan}" />
							</c:if> <c:if
								test="${blackList.pan == null || blackList.pan ==''}">
                    	&nbsp;
                    </c:if></td>
							<td class="gridOddLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${blackList.cardInfo.nameAndFamilyName != null && blackList.cardInfo.nameAndFamilyName !=''}">
								<c:out value="${blackList.cardInfo.nameAndFamilyName}" />
							</c:if> <c:if
								test="${blackList.cardInfo.nameAndFamilyName == null || blackList.cardInfo.nameAndFamilyName ==''}">
                    	             &nbsp;
                                       </c:if></td>
							<td class="gridOddLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${blackList.cardInfo.cardStatusDesc != null && blackList.cardInfo.cardStatusDesc !=''}">
								<c:out value="${blackList.cardInfo.cardStatusDesc}" />&nbsp;
                    </c:if> <c:if
								test="${blackList.cardInfo.cardStatusDesc == null || blackList.cardInfo.cardStatusDesc ==''}">
                    	&nbsp;
                    </c:if></td>
							<td class="gridOddLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${blackList.blackedReasonId != null && blackList.blackedReasonId !=''}">
								<c:out value="${blackList.blackedReasonId}" />
							</c:if> <c:if
								test="${blackList.blackedReasonId == null || blackList.blackedReasonId ==''}">
                    	&nbsp;
                    </c:if></td>
							<td class="gridOddLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${blackList.blackedActiveFlag != null && blackList.blackedActiveFlag !=''}">
								<c:out value="${blackList.blackedActiveFlag}" />
							</c:if> <c:if
								test="${blackList.blackedActiveFlag == null || blackList.blackedActiveFlag ==''}">
                    	&nbsp;
                    </c:if></td>
							<td class="gridOddLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${blackList.blackedDate != null && blackList.blackedDate !=''}">
								<c:out value="${blackList.blackedDate}" />
							</c:if> <c:if
								test="${blackList.blackedDate == null || blackList.blackedDate ==''}">
                    	&nbsp;
                      </c:if></td>
							<td class="gridOddLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${blackList.blackedTime != null && blackList.blackedTime !=''}">
								<c:out value="${blackList.blackedTime}" />
							</c:if> <c:if
								test="${blackList.blackedTime == null || blackList.blackedTime ==''}">
                    	&nbsp;
                      </c:if>
                      </td>
						</c:otherwise>
					</c:choose>
				</tr>
				<c:set var="evenOdd" value="${evenOdd + 1}" />
			</c:forEach>
		</table>

</td>
</tr>
</table>
</body>
