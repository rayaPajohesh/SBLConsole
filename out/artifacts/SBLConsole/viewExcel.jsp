<%-- set Style for css--%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <style>
       .headLine{
                font-family: "Tahoma";
                font-size: 11px;
                font-weight: normal;
                width: 100%;
        }
        .ExcelFriendlyTable
        {
        		font-family:Tahoma;
        		font-size:11px;
        		padding: 1px;
        		width: 100%;	
        }
        .ExcelHeader
        {
        		font-family: Tahoma;
        		font-weight: bold;
        		color: #000000;
        		background-color: #98A8b6;
        		height: 20px;
        		padding: 4px 4px 4px 4px;
        		vertical-align: top;
        		border: outset;
        		border-width: 1pt;
        }
        .ExcelEvenLine
        {
        		background-color: #E3E1E1;
        		text-indent: 1pt;border: outset;
        		border-width: 1pt;	
        }
        .ExcelOddLine
        {
        		background-color: #FFFFFF;
        		text-indent: 1pt;
        		border: outset;
        		border-width: 1pt;	
        }
        
  
 	</style>    
</head>
						
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ page import="com.iac.ambit.utils.DateFarsi"%>
<%@ page import="proxy.com.iac.ambit.model.Customer"%>

<%-- Set  Parameter --%> 
<% String type=request.getParameter("Type"); %>

<%-- Set the content type header with the JSP directive --%>
<%@ page contentType="application/vnd.ms-excel;charset=UTF-8" pageEncoding="UTF-8" %>
                                                                                                                    
<%-- Set the content disposition header --%>
<% response.setHeader("Content-Disposition", "attachments; filename=\""+type+".xls\""); %>
 
			
<fmt:setLocale value="fa_IR" />
<c:set var="locale" value="${sessionScope.CUSTOMER_IN_SESSION.language}" />
<c:set var="Persian" value="fa" />
<c:set var="English" value="en" />
<c:set var="dateFormat" value="dd/MM/yyyy" scope="page"/>
<c:set var="rangeDateFormat" value="dd-MMM-yy" scope="page"/>
<c:set var="timeFormat" value="hh:mm a" scope="page"/>
<jsp:useBean id="CUSTOMER_IN_SESSION" type="proxy.com.iac.ambit.model.Customer" scope="session"/>

 <%DateFarsi DateFarsi=new DateFarsi();   
 %>

<c:if test="${locale == Persian}">  
  <c:set var="alignment" value="right" scope="request" />  
  <c:set var="rotation" value="rtl" scope="request" />
</c:if>	
<c:if test="${locale == English}">
  <c:set var="alignment" value="left" scope="request" />  
   <c:set var="rotation" value="ltr" scope="request" />  
</c:if>
                                                                                                           

    <table cellpadding="0" cellspacing="0" border="0" align="center" class="headLine" >
        <tr >
        <td align="center" valign="top">
          <h4>
            <%if(type.equalsIgnoreCase("blackListTransReport")){%>
            <bean:message key="blackListTransReport.list"/>
            <%}%>
            <%if(type.equalsIgnoreCase("blackListMonitoring")){%>
            <bean:message key="monitorTransactionsPrint.heading"/>
            <%}%>
            <%if(type.equalsIgnoreCase("blackListManagement")){%>
            <bean:message key="blackList.heading"/>
            <%}%>                 	
          </h4>
        </td>
      </tr>
      <%if(type.equalsIgnoreCase("blackListTransReport")){%>
      <tr>     
        <c:if test="${locale == Persian}">
          <td align="right">
             <b><bean:message key="customer.id" />:</b> &nbsp;           
             <c:out value="${CUSTOMER_IN_SESSION.userId}"/>        
          </td>
       </c:if>	     
    </tr>
	<tr>
		<td align="right" >
		<b><bean:message
			key="blackListTransReport.fromDate" />: </b>&nbsp;&nbsp;	
	         <c:set var="TransactionFromDate" value="${fromDate}" scope="request"/>
              <%
              String TransactionFromDate=(String)request.getAttribute("TransactionFromDate");
              String  excelFromDate=DateFarsi.revformatDateExcel(TransactionFromDate);
              %>
              <%=excelFromDate%>									
			&nbsp;&nbsp;&nbsp;&nbsp;
		<b><bean:message
			key="blackListTransReport.toDate" />: </b>&nbsp;&nbsp;	
	         <c:set var="TransactionToDate" value="${toDate}" scope="request"/>
              <%
              String TransactionToDate=(String)request.getAttribute("TransactionToDate");
              String  excelToDate=DateFarsi.revformatDateExcel(TransactionToDate);
              %>
              <%=excelToDate%>									
			</td>						
		</tr>
	   <tr>
		<td align="right" >
		<b><bean:message
			key="blackListTransReport.fromTime" />: </b>&nbsp;&nbsp;	
	      	 <c:out value="${fromTime}" />								
			&nbsp;&nbsp;&nbsp;&nbsp;
		<b><bean:message
			key="blackListTransReport.toTime" />: </b>&nbsp;&nbsp;	
	           <c:out value="${toTime}" />								
		   </td>						
		</tr>
	    <tr>
		<td align="right" >
		<b><bean:message
			key="blackListTransReport.pan" />: </b>&nbsp;&nbsp;	
	          <c:out value="${pan}" />								
		&nbsp;&nbsp;&nbsp;&nbsp;
		<b><bean:message
			key="blackListTransReport.stan" />: </b>&nbsp;&nbsp;	
	         <c:out value="${stan}" />&nbsp;&nbsp;&nbsp;&nbsp;	
	       <b><bean:message
			key="blackListTransReport.rrn" />: </b>&nbsp;&nbsp;	
	         <c:out value="${rrn}" />&nbsp;&nbsp;&nbsp;&nbsp;  									
		   </td>						
		</tr>      
      <tr><td>&nbsp;&nbsp;</td></tr>
    <%}%>
     <%if(type.equalsIgnoreCase("blackListMonitoring")){%>          
      <tr>     
        <c:if test="${locale == Persian}">
          <td align="right">
             <b><bean:message key="customer.id" />:</b> &nbsp;           
             <c:out value="${CUSTOMER_IN_SESSION.userId}"/>        
          </td>
       </c:if>	     
    </tr>	
      <tr><td>&nbsp;&nbsp;</td></tr>
    <%}%>  
    <%if(type.equalsIgnoreCase("blackListManagement")){%>
      
	<tr>
		<td align="right" >
		<b><bean:message
			key="blackList.fromDate" />: </b>&nbsp;&nbsp;	
	         <c:set var="blackListFromDate" value="${fromDate}" scope="request"/>
              <%
              String blackListFromDate=(String)request.getAttribute("blackListFromDate");
              String  excelFromDate=DateFarsi.revformatDateExcel(blackListFromDate);
              %>
              <%=excelFromDate%>									
			&nbsp;&nbsp;&nbsp;&nbsp;
		<b><bean:message
			key="blackList.toDate" />: </b>&nbsp;&nbsp;	
	         <c:set var="blackListToDate" value="${toDate}" scope="request"/>
              <%
              String blackListToDate=(String)request.getAttribute("blackListToDate");
              String  excelToDate=DateFarsi.revformatDateExcel(blackListToDate);
              %>
              <%=excelToDate%>									
			</td>						
		</tr>
	   
	    <tr>
		<td align="right" >
		<b><bean:message
			key="blackList.pan" />: </b>&nbsp;&nbsp;	
	          <c:out value="${pan}" />								
		&nbsp;&nbsp;&nbsp;&nbsp;
		 									
		   </td>						
		</tr>      
      <tr><td>&nbsp;&nbsp;</td></tr>
    <%}%>
	<tr>
        <td>
          <table dir=<%=(String)request.getAttribute("rotation")%> cellpadding="0" cellspacing="0" border="0" class="ExcelFriendlyTable"  align="center" >
            <tr>
            
            <%if(type.equalsIgnoreCase("blackListTransReport")){%>
              <td width="8%"  class="ExcelHeader" align="center" >
                <bean:message key="blackListTransReport.date"/>
              </td>
              <td width="8%"  class="ExcelHeader" align="center" >
                <bean:message key="blackListTransReport.time"/>
              </td>
              <td width="8%"  class="ExcelHeader" align="center" >
                <bean:message key="blackListTransReport.pan"/>
              </td>
              <td width="8%"  class="ExcelHeader" align="center" >
                <bean:message key="blackListTransReport.transactionType"/>
              </td>
              <td width="10%"  class="ExcelHeader" align="center" >
                <bean:message key="blackListTransReport.stan"/>
              </td>
              <td width="10%"  class="ExcelHeader" align="center" >
                <bean:message key="blackListTransReport.rrn"/>
              </td>            
               <td width="10%"  class="ExcelHeader" align="center" >
                <bean:message key="blackListTransReport.responseCode"/>
              </td>
              <td width="10%"  class="ExcelHeader" align="center" >
                <bean:message key="blackListTransReport.terminalId"/>
              </td>
              <td width="10%"  class="ExcelHeader" align="center" >
                <bean:message key="blackListTransReport.amount"/>
              </td>  
              <%}%>
                                
            <%if(type.equalsIgnoreCase("blackListMonitoring")){%>
              <td width="8%"  class="ExcelHeader" align="center" >
                <bean:message key="monitorTransactions.date"/>
              </td>
              <td width="8%"  class="ExcelHeader" align="center" >
                <bean:message key="monitorTransactions.time"/>
              </td>
              <td width="8%"  class="ExcelHeader" align="center" >
                <bean:message key="monitorTransactions.pan"/>
              </td>
              <td width="8%"  class="ExcelHeader" align="center" >
                <bean:message key="monitorTransactions.transactionType"/>
              </td>
              <td width="10%"  class="ExcelHeader" align="center" >
                <bean:message key="monitorTransactions.stan"/>
              </td>
              <td width="10%"  class="ExcelHeader" align="center" >
                <bean:message key="monitorTransactions.rrn"/>
              </td>            
               <td width="10%"  class="ExcelHeader" align="center" >
                <bean:message key="monitorTransactions.responseCode"/>
              </td>
              <td width="10%"  class="ExcelHeader" align="center" >
                <bean:message key="monitorTransactions.terminalId"/>
              </td>
              <td width="10%"  class="ExcelHeader" align="center" >
                <bean:message key="monitorTransactions.amount"/>
              </td>  
              <%}%>
                            
              <%if(type.equalsIgnoreCase("blackListManagement")){%>
              <td width="8%"  class="ExcelHeader" align="center" >
                <bean:message key="blackList.pan"/>
              </td>
              <td width="8%"  class="ExcelHeader" align="center" >
                <bean:message key="blackList.Name"/>
              </td>
              <td width="8%"  class="ExcelHeader" align="center" >
                <bean:message key="blackList.cardStatus"/>
              </td>
              <td width="8%"  class="ExcelHeader" align="center" >
                <bean:message key="blackList.reason"/>
              </td>
              <td width="10%"  class="ExcelHeader" align="center" >
                <bean:message key="blackList.statusInBlackList"/>
              </td>
              <td width="10%"  class="ExcelHeader" align="center" >
                <bean:message key="blackList.dateIntoBlackList"/>
              </td>            
               <td width="10%"  class="ExcelHeader" align="center" >
                <bean:message key="blackList.timeIntoBlackList"/>
              </td>
              
              <%}%>                         
            </tr>            
 			 <c:set var="evenOdd" value="0"/>
 			 <%if(type.equalsIgnoreCase("blackListTransReport")){%>
            <c:forEach items="${printList}" var="transaction">
              <tr>
                <c:choose>
                  <c:when test="${evenOdd % 2 == 0}">
                   <%-- even rows --%>
                    <td class="ExcelEvenLine" align="center" >
                    <c:choose>
                    <c:when test="${transaction.transactionDate != null && transaction.transactionDate !=''}">
                      <c:out value="${transaction.transactionDate}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    </td>
                     <td class="ExcelEvenLine" align="center" >
                    <c:choose>
                    <c:when test="${transaction.transactionTime != null && transaction.transactionTime !=''}">
                      <c:out value="${transaction.transactionTime}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    </td>
                    <td class="ExcelEvenLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${transaction.pan != null && transaction.pan !='' }">
                      <c:out value="${transaction.pan}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                    <td class="ExcelEvenLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${transaction.transactionCode != null && transaction.transactionCode !='' }">
                      <c:out value="${transaction.transactionCode}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                    <td class="ExcelEvenLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${transaction.stan != null && transaction.stan !='' }">
                      <c:out value="${transaction.stan}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                   <td class="ExcelEvenLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${transaction.retrievalReferenceNumber != null && transaction.retrievalReferenceNumber !='' }">
                      <c:out value="${transaction.retrievalReferenceNumber}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                    <td class="ExcelEvenLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${transaction.responseCode != null && transaction.responseCode !='' }">
                      <c:out value="${transaction.responseCode}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                    <td class="ExcelEvenLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${transaction.terminalId != null && transaction.terminalId !='' }">
                      <c:out value="${transaction.terminalId}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
              		<td class="ExcelEvenLine" align="center" >                 
                    <c:choose>
                    <c:when test="${transaction.amount != null && transaction.amount !='' && transaction.amount !='0.0'}">
                      <c:out value="${transaction.amount}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    </td>                
                  </c:when>
                  <%-- odd rows --%>
                  <c:otherwise>
                  <td class="ExcelOddLine" align="center" >
                    <c:choose>
                    <c:when test="${transaction.transactionDate != null && transaction.transactionDate !=''}">
                      <c:out value="${transaction.transactionDate}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    </td>
                    <td class="ExcelOddLine" align="center" >
                    <c:choose>
                    <c:when test="${transaction.transactionTime != null && transaction.transactionTime !=''}">
                      <c:out value="${transaction.transactionTime}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    </td>
                    <td class="ExcelOddLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${transaction.pan != null && transaction.pan !='' }">
                      <c:out value="${transaction.pan}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                    <td class="ExcelOddLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${transaction.transactionCode != null && transaction.transactionCode !='' }">
                      <c:out value="${transaction.transactionCode}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                    <td class="ExcelOddLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${transaction.stan != null && transaction.stan !='' }">
                      <c:out value="${transaction.stan}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                   <td class="ExcelOddLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${transaction.retrievalReferenceNumber != null && transaction.retrievalReferenceNumber !='' }">
                      <c:out value="${transaction.retrievalReferenceNumber}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                    <td class="ExcelOddLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${transaction.responseCode != null && transaction.responseCode !='' }">
                      <c:out value="${transaction.responseCode}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                    <td class="ExcelOddLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${transaction.terminalId != null && transaction.terminalId !='' }">
                      <c:out value="${transaction.terminalId}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
              		<td class="ExcelOddLine" align="center" >
                    <fmt:formatNumber  value="${transaction.amount}" pattern="###,###"   var="transactionAmount"/>
                    <c:choose>
                    <c:when test="${transactionAmount != null && transactionAmount !='' && transactionAmount !='0.0'}">
                      <c:out value="${transactionAmount}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    </td>            
                   </c:otherwise>
                </c:choose>
              </tr>
              <c:set var="evenOdd" value="${evenOdd + 1}"/>
            </c:forEach>
            <%}%>
            
       <%if(type.equalsIgnoreCase("blackListMonitoring")){%>
            <c:forEach items="${printList}" var="transaction">
              <tr>
                <c:choose>
                  <c:when test="${evenOdd % 2 == 0}">
                   <%-- even rows --%>
                    <td class="ExcelEvenLine" align="center" >
                    <c:choose>
                    <c:when test="${transaction.transactionDate != null && transaction.transactionDate !=''}">
                      <c:out value="${transaction.transactionDate}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    </td>
                     <td class="ExcelEvenLine" align="center" >
                    <c:choose>
                    <c:when test="${transaction.transactionTime != null && transaction.transactionTime !=''}">
                      <c:out value="${transaction.transactionTime}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    </td>
                    <td class="ExcelEvenLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${transaction.pan != null && transaction.pan !='' }">
                      <c:out value="${transaction.pan}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                    <td class="ExcelEvenLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${transaction.transactionCode != null && transaction.transactionCode !='' }">
                      <c:out value="${transaction.transactionCode}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                    <td class="ExcelEvenLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${transaction.stan != null && transaction.stan !='' }">
                      <c:out value="${transaction.stan}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                   <td class="ExcelEvenLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${transaction.retrievalReferenceNumber != null && transaction.retrievalReferenceNumber !='' }">
                      <c:out value="${transaction.retrievalReferenceNumber}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                    <td class="ExcelEvenLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${transaction.responseCode != null && transaction.responseCode !='' }">
                      <c:out value="${transaction.responseCode}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                    <td class="ExcelEvenLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${transaction.terminalId != null && transaction.terminalId !='' }">
                      <c:out value="${transaction.terminalId}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
              		<td class="ExcelEvenLine" align="center" >                   
                    <c:choose>
                    <c:when test="${transaction.amount != null && transaction.amount !='' && transaction.amount !='0.0'}">
                      <c:out value="${transaction.amount}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    </td>                
                  </c:when>
                  <%-- odd rows --%>
                  <c:otherwise>
                  <td class="ExcelOddLine" align="center" >
                    <c:choose>
                    <c:when test="${transaction.transactionDate != null && transaction.transactionDate !=''}">
                      <c:out value="${transaction.transactionDate}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    </td>
                    <td class="ExcelOddLine" align="center" >
                    <c:choose>
                    <c:when test="${transaction.transactionTime != null && transaction.transactionTime !=''}">
                      <c:out value="${transaction.transactionTime}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    </td>
                    <td class="ExcelOddLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${transaction.pan != null && transaction.pan !='' }">
                      <c:out value="${transaction.pan}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                    <td class="ExcelOddLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${transaction.transactionCode != null && transaction.transactionCode !='' }">
                      <c:out value="${transaction.transactionCode}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                    <td class="ExcelOddLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${transaction.stan != null && transaction.stan !='' }">
                      <c:out value="${transaction.stan}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                   <td class="ExcelOddLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${transaction.retrievalReferenceNumber != null && transaction.retrievalReferenceNumber !='' }">
                      <c:out value="${transaction.retrievalReferenceNumber}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                    <td class="ExcelOddLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${transaction.responseCode != null && transaction.responseCode !='' }">
                      <c:out value="${transaction.responseCode}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                    <td class="ExcelOddLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${transaction.terminalId != null && transaction.terminalId !='' }">
                      <c:out value="${transaction.terminalId}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
              		<td class="ExcelOddLine" align="center" >
                   
                    <c:choose>
                    <c:when test="${transaction.amount != null && transaction.amount !='' && transaction.amount !='0.0'}">
                      <c:out value="${transaction.amount}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    </td>            
                   </c:otherwise>
                </c:choose>
              </tr>
              <c:set var="evenOdd" value="${evenOdd + 1}"/>
            </c:forEach>
            <%}%>
 
            <%if(type.equalsIgnoreCase("blackListManagement")){%>
            <c:forEach items="${printList}" var="blackList">
              <tr>
                <c:choose>
                  <c:when test="${evenOdd % 2 == 0}">
                   <%-- even rows --%>
                    <td class="ExcelEvenLine" align="center" >
                    <c:choose>
                    <c:when test="${blackList.pan != null && blackList.pan !=''}">
                    	&nbsp;
                      <c:out value="${blackList.pan}"/>
                      &nbsp;
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    </td>
                     <td class="ExcelEvenLine" align="center" >
                    <c:choose>
                    <c:when test="${blackList.cardInfo.nameAndFamilyName != null && blackList.cardInfo.nameAndFamilyName !=''}">
                      <c:out value="${blackList.cardInfo.nameAndFamilyName}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    </td>
                    <td class="ExcelEvenLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${blackList.cardInfo.cardStatusDesc != null && blackList.cardInfo.cardStatusDesc !='' }">
                      <c:out value="${blackList.cardInfo.cardStatusDesc}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                    <td class="ExcelEvenLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${blackList.blackedReasonId != null && blackList.blackedReasonId !='' }">
                      <c:out value="${blackList.blackedReasonId}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                    <td class="ExcelEvenLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${blackList.blackedActiveFlag != null && blackList.blackedActiveFlag !='' }">
                      <c:out value="${blackList.blackedActiveFlag}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                   <td class="ExcelEvenLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${blackList.blackedDate != null && blackList.blackedDate !='' }">
                     
                      
                      <c:set var="blackedDate" value="${blackList.blackedDate}" scope="request"/>
						<%
		              String blackedDate = (String)request.getAttribute("blackedDate");
		              String  blackedDateFormat = DateFarsi.revformatDateExcel(blackedDate);
		              %>
		              <%=blackedDateFormat%>		
		                      
                      
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                    <td class="ExcelEvenLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${blackList.blackedTime != null && blackList.blackedTime !='' }">
                      <c:out value="${blackList.blackedTime}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                      
                  </c:when>
                  <%-- odd rows --%>
                  <c:otherwise>
                  <td class="ExcelOddLine" align="center" >
                    <c:choose>
                    <c:when test="${blackList.pan != null && blackList.pan !=''}">
                    	&nbsp;
                      <c:out value="${blackList.pan}"/>
                      &nbsp;
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    </td>
                    <td class="ExcelOddLine" align="center" >
                    <c:choose>
                    <c:when test="${blackList.cardInfo.nameAndFamilyName != null && blackList.cardInfo.nameAndFamilyName !=''}">
                      <c:out value="${blackList.cardInfo.nameAndFamilyName}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    </td>
                    <td class="ExcelOddLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${blackList.cardInfo.cardStatusDesc != null && blackList.cardInfo.cardStatusDesc !='' }">
                      <c:out value="${blackList.cardInfo.cardStatusDesc}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                    <td class="ExcelOddLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${blackList.blackedReasonId != null && blackList.blackedReasonId !='' }">
                      <c:out value="${blackList.blackedReasonId}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                    <td class="ExcelOddLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${blackList.blackedActiveFlag != null && blackList.blackedActiveFlag !='' }">
                      <c:out value="${blackList.blackedActiveFlag}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                   <td class="ExcelOddLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${blackList.blackedDate != null && blackList.blackedDate !='' }">
                      
                      
                      <c:set var="blackedDate" value="${blackList.blackedDate}" scope="request"/>
						<%
		              String blackedDateOdd = (String)request.getAttribute("blackedDate");
		              String  blackedDateFormatOdd = DateFarsi.revformatDateExcel(blackedDateOdd);
		              %>
		              <%=blackedDateFormatOdd%>
                      
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                    <td class="ExcelOddLine" align="center" >&nbsp;
                    <c:choose>
                    <c:when test="${blackList.blackedTime != null && blackList.blackedTime !='' }">
                      <c:out value="${blackList.blackedTime}"/>
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="global.nullText"/>
                    </c:otherwise>
					</c:choose>
                    &nbsp;</td>
                          
                   </c:otherwise>
                </c:choose>
              </tr>
              <c:set var="evenOdd" value="${evenOdd + 1}"/>
            </c:forEach>
            <%}%>
            
            
            
          </table>
        </td>
      </tr>

    </table>

       