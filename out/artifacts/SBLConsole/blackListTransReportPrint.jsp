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
			key="blackListTransReportPrint.heading" /></td>
	</tr>
	<tr>
			<td  width="40%">&nbsp;</td>
		
      <td align="right"  dir="rtl"  ><b><bean:message key="blackListTransReport.fromDate" />:
		</b> &nbsp;&nbsp; <c:out value="${fromDate}" />   &nbsp;&nbsp;&nbsp;&nbsp; <b><bean:message
			key="blackListTransReport.toDate" />: </b> &nbsp;&nbsp; <c:out
			value="${toDate}" /></td>
		<td class="direction" align="right"  dir="rtl"  ><b><bean:message
			key="global.userId" /></b> &nbsp; <c:out
			value="${CUSTOMER_IN_SESSION.userId}" /></td>	
	</tr>

	<tr>
			<td  width="40%">&nbsp;</td>
		<td align="right" dir="rtl" ><b><bean:message key="blackListTransReport.fromTime" />:
		</b> &nbsp;&nbsp;&nbsp; <c:out value="${fromTime}" />&nbsp;&nbsp;&nbsp;&nbsp; <b><bean:message
			key="blackListTransReport.toTime" />: </b> &nbsp;&nbsp; <c:out
			value="${toTime}" /></td>
			<td align="right" dir="rtl"><b><bean:message key="blackListTransReport.pan" />:
		</b> &nbsp;&nbsp;&nbsp; <c:out value="${pan}" /></td>	
			
	</tr>
	<tr>
		<td  width="40%">&nbsp;</td>
		<td align="right" dir="rtl"> <b><bean:message
			key="blackListTransReport.stan" />: </b> &nbsp;&nbsp; <c:out
			value="${stan}" /></td>
		<td align="right"  dir="rtl"><b><bean:message key="blackListTransReport.rrn" />:
		</b> &nbsp;&nbsp;&nbsp; <c:out value="${rrn}" /></td>
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
				<bean:message key="blackListTransReport.date" /></td>
				<td class="gridHeader printerFriendlyCell" width="74px" align="left">
				<bean:message key="blackListTransReport.time" /></td>
				<td class="gridHeader printerFriendlyCell" width="100px"
					align="left"><bean:message key="blackListTransReport.pan" /></td>
				<td class="gridHeader printerFriendlyCell" width="100px"
					align="left"><bean:message
					key="blackListTransReport.transactionType" /></td>
				<td class="gridHeader printerFriendlyCell" width="100px"
					align="left"><bean:message key="blackListTransReport.stan" /></td>
				<td class="gridHeader printerFriendlyCell" width="100px"
					align="left"><bean:message key="blackListTransReport.rrn" /></td>
				<td class="gridHeader printerFriendlyCell" width="100px"
					align="left"><bean:message key="blackListTransReport.responseCode" /></td>
				<td class="gridHeader printerFriendlyCell" width="80px" align="left">
				<bean:message key="blackListTransReport.terminalId" /></td>
				<td class="gridHeader printerFriendlyCell" width="80px" align="left">
				<bean:message key="blackListTransReport.amount" /></td>
			</tr>
			<c:set var="evenOdd" value="0" />
			<c:forEach items="${printList}" var="transaction">

				<tr style="font-size:9px" valign="center">
					<c:choose>
						<c:when test="${evenOdd % 2 == 0}">
							<td class="gridEvenLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${transaction.transactionDate != null && transaction.transactionDate !=''}">
								<c:out value="${transaction.transactionDate}" />
							</c:if> <c:if
								test="${transaction.transactionDate == null || transaction.transactionDate ==''}">
                    	&nbsp;
                    </c:if></td>
							<td class="gridEvenLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${transaction.transactionTime != null && transaction.transactionTime !=''}">
								<c:out value="${transaction.transactionTime}" />
							</c:if> <c:if
								test="${transaction.transactionTime == null || transaction.transactionTime ==''}">
                    	             &nbsp;
                                       </c:if></td>
							<td class="gridEvenLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${transaction.pan != null && transaction.pan !=''}">
								<c:out value="${transaction.pan}" />&nbsp;
                    </c:if> <c:if
								test="${transaction.pan == null || transaction.pan ==''}">
                    	&nbsp;
                    </c:if></td>
							<td class="gridEvenLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${transaction.transactionCode != null && transaction.transactionCode !=''}">
								<c:out value="${transaction.transactionCode}" />
							</c:if> <c:if
								test="${transaction.transactionCode == null || transaction.transactionCode ==''}">
                    	&nbsp;
                    </c:if></td>
							<td class="gridEvenLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${transaction.stan != null && transaction.stan !=''}">
								<c:out value="${transaction.stan}" />
							</c:if> <c:if
								test="${transaction.stan == null || transaction.stan ==''}">
                    	&nbsp;
                    </c:if></td>
							<td class="gridEvenLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${transaction.retrievalReferenceNumber != null && transaction.retrievalReferenceNumber !=''}">
								<c:out value="${transaction.retrievalReferenceNumber}" />
							</c:if> <c:if
								test="${transaction.retrievalReferenceNumber == null || transaction.retrievalReferenceNumber ==''}">
                    	&nbsp;
                      </c:if></td>
							<td class="gridEvenLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${transaction.responseCode != null && transaction.responseCode !=''}">
								<c:out value="${transaction.responseCode}" />
							</c:if> <c:if
								test="${transaction.responseCode == null || transaction.responseCode ==''}">
                    	&nbsp;
                      </c:if></td>
							<td class="gridEvenLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${transaction.terminalId != null && transaction.terminalId !=''}">
								<c:out value="${transaction.terminalId}" />
							</c:if> <c:if
								test="${transaction.terminalId == null || transaction.terminalId ==''}">
                    	&nbsp;
                      </c:if></td>


							<td
								class="gridEvenLine printerFriendlyCell printerFriendlyNumberic"
								align=<%=(String)request.getAttribute("alignment")%>><fmt:formatNumber
								pattern="###,###" value="${transaction.amount}"
								var="transactionAmount" /> <c:if
								test="${transactionAmount != null && transactionAmount !=''}">
								<c:out value="${transactionAmount}" />
							</c:if> <c:if
								test="${transactionAmount == null || transactionAmount ==''}">
                    	             &nbsp;
                                    </c:if></td>
						</c:when>
						<c:otherwise>
							<td class="gridOddLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${transaction.transactionDate != null && transaction.transactionDate !=''}">
								<c:out value="${transaction.transactionDate}" />
							</c:if> <c:if
								test="${transaction.transactionDate == null || transaction.transactionDate ==''}">
                                  	&nbsp;
                                    </c:if></td>
							<td class="gridOddLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${transaction.transactionTime != null && transaction.transactionTime !=''}">
								<c:out value="${transaction.transactionTime}" />
							</c:if> <c:if
								test="${transaction.transactionTime == null || transaction.transactionTime ==''}">
                    	             &nbsp;
                                       </c:if></td>
							<td class="gridOddLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${transaction.pan != null && transaction.pan !=''}">
								<c:out value="${transaction.pan}" />&nbsp;
                                    </c:if> <c:if
								test="${transaction.pan == null || transaction.pan ==''}">
                    	           &nbsp;
                                   </c:if></td>
							<td class="gridOddLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${transaction.transactionCode != null && transaction.transactionCode !=''}">
								<c:out value="${transaction.transactionCode}" />
							</c:if> <c:if
								test="${transaction.transactionCode == null || transaction.transactionCode ==''}">
                    	            &nbsp;
                                 </c:if></td>
							<td class="gridOddLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${transaction.stan != null && transaction.stan !=''}">
								<c:out value="${transaction.stan}" />
							</c:if> <c:if
								test="${transaction.stan == null || transaction.stan ==''}">
                    	         &nbsp;
                                </c:if></td>
							<td class="gridOddLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${transaction.retrievalReferenceNumber != null && transaction.retrievalReferenceNumber !=''}">
								<c:out value="${transaction.retrievalReferenceNumber}" />
							</c:if> <c:if
								test="${transaction.retrievalReferenceNumber == null || transaction.retrievalReferenceNumber ==''}">
                    	      &nbsp;
                               </c:if></td>
							<td class="gridOddLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${transaction.responseCode != null && transaction.responseCode !=''}">
								<c:out value="${transaction.responseCode}" />
							</c:if> <c:if
								test="${transaction.responseCode == null || transaction.responseCode ==''}">
                    	     &nbsp;
                             </c:if></td>
							<td class="gridOddLine printerFriendlyCell"
								align=<%=(String)request.getAttribute("alignment")%>><c:if
								test="${transaction.terminalId != null && transaction.terminalId !=''}">
								<c:out value="${transaction.terminalId}" />
							</c:if> <c:if
								test="${transaction.terminalId == null || transaction.terminalId ==''}">
                    	       &nbsp;
                                     </c:if></td>
							<td
								class="gridOddLine printerFriendlyCell printerFriendlyNumberic"
								align=<%=(String)request.getAttribute("alignment")%>><fmt:formatNumber
								pattern="###,###" value="${transaction.amount}"
								var="transactionAmount" /> <c:if
								test="${transactionAmount != null && transactionAmount !=''}">
								<c:out value="${transactionAmount}" />
							</c:if> <c:if
								test="${transactionAmount == null || transactionAmount ==''}">
                    	             &nbsp;
                                    </c:if></td>
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
