<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="javax.xml.rpc.holders.StringHolder"%>
<%@ page import="com.iac.ambit.utils.AmbitUtility"%>
<%@ page import="proxy.com.iac.ambit.model.Customer"%>
<%@ page import="com.iac.ambit.utils.DateUtils,com.iac.ambit.utils.DateFarsi,com.iac.ambit.utils.Constants"%>
<%@ page import="java.util.List,java.util.Date"%>
<%@ page import="com.iac.ambit.utils.ContextUtils"%>
<%@ page import="proxy.localhost.SBLService.axis.SpringWS.SpringWSSoapBindingStub;"%>
    <%  
    DateFarsi dateFarsi = (DateFarsi) ContextUtils.getBean("DateFarsi");
    Customer customer = (Customer)session.getAttribute(Constants.CUSTOMER_IN_SESSION);
    %>
    
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" dir="<%= session.getAttribute( Constants.JspConstants.PAGE_DIR  )%>">
  <tr align="center"  >
    <td valign="top" style="padding-top: 5px;" >
     <table width="690" border="0" cellspacing="0" cellpadding="0">
         <tr> 
          <td height="15px"  valign="top" align="center">

            </td>
        </tr>
        <tr>
	      <td colspan="3" height="40"   align="center" background="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>Welcome-Header2.jpg">
	      <b ><font style="font: Tahoma;font-size: 13;color: #2A3561;">	<bean:message key="welcome.Message"/></font></b>
	      
	      </td>
        </tr>
           
        <tr>
       	<td height="287" colspan="3" class="welcomeImage" align="center">
		        <table cellpadding="0" cellspacing="0" align="right" border="0"  dir="rtl">
		         <tr>
		        	<td class="wellcomeHeading" align="center"  >
		          <%  
		              Date dt = new Date();
		              
		              if(customer.getGMTOffset()!=null && !customer.getGMTOffset().equals("")) {
		                dt.setTime((dt.getTime()-(300*60*1000) - 
		                  (Integer.parseInt(customer.getGMTOffset())*60*1000)));
		              }
		            %>
		          	<bean:message key="<%=DateUtils.getGreetingMoment((dt))%>"/>
		          	
		          	<span class="wellcomeNameHeading" >
		          		<%=  (customer.getUserNameFA()==null?"":customer.getUserNameFA())%>
		          	</span>
		       	 	</td>
				 </tr>
				      <%  
		      			SpringWSSoapBindingStub proxyService = (SpringWSSoapBindingStub)ContextUtils.
		      				getBean("springWSSoapBindingStub");
		      			//jazimagh: 1386/05/14
		      						      				
		      			String dtSuccess = null;
		      			String dtFailure = null;
		      			StringHolder lastLoginDate = new StringHolder();
		      			String ResponseCode = proxyService.getLastLoginDate(customer.getUserId(), true,lastLoginDate);
		      			String date = lastLoginDate.value;
		      			
		      			if (!AmbitUtility.isEmpty(date)){
		      			//	dtSuccess = date.toString();
		      				dtSuccess =dateFarsi.formatTime(date.substring(8,14)) + "	" + dateFarsi.formatFdate(dateFarsi.LDateConv(date.substring(0,8)));
		      				
		      			}	                
		                
		                request.setAttribute("dtSuccess",dtSuccess);
		                ResponseCode = proxyService.getLastLoginDate(customer.getUserId(), false,lastLoginDate);
		                date = lastLoginDate.value;
		                
		                if (!AmbitUtility.isEmpty(date)){
		      			//	dtFailure = date.toString();
		      			
		      				dtFailure = dateFarsi.formatTime(date.substring(8,14)) + " " + dateFarsi.formatFdate(dateFarsi.LDateConv(date.substring(0,8)));
		      				
		      			}	
		                		         
		                request.setAttribute("dtFailure",dtFailure);
		                //jazimagh: 1386/05/14
				      %>
				      <%  
				        if (dtSuccess!=null) {
				      %>
				      <tr><td>&nbsp;</td></tr>
				      <tr>
				      	<!-- jazimagh: 1386/05/14 -->
				        <td align="center" class="wellcomeLoginInfo" valign="top">
				          <b>
				            <bean:message key="welcome.lastSuccessfulLogin"/>
				          </b>					          
							<%= request.getAttribute("dtSuccess")%>
				        </td>
				        <!-- jazimagh: 1386/05/14 -->
				      </tr>
				      <tr><td>&nbsp;</td></tr>
				      <%  
				                }
				              %>
				      <%  
				                if (dtFailure!=null) {
				              %>
				      <tr>
					    <!-- jazimagh: 1386/05/14 -->
				        <td align="right" class="wellcomeLoginInfo" >
				          <b>
				            <bean:message key="welcome.lastFailureLogin"/>
				          </b>
							<%= request.getAttribute("dtFailure")%>
				        </td>
				        <!-- jazimagh: 1386/05/14 -->
				      </tr>
				      <%  
				      }
		              %>
				      <tr><td>&nbsp;</td></tr>
		         </table>
			</td>
        </tr>
      
       
      </table></td>
  </tr>
 
</table>
