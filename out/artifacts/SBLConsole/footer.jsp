<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.iac.ambit.utils.ContextUtils,com.iac.ambit.utils.DateFarsi,com.iac.ambit.utils.DateUtils"%>
<%@ page import="com.iac.ambit.utils.Constants"%>


<c:set var="locale" value="${sessionScope.struts_action_language}" />
<c:set var="Persian" value="fa" />
<c:set var="English" value="en" />
<%DateFarsi dateFarsi = (DateFarsi) ContextUtils.getBean("DateFarsi");

			%>

<c:set var="locale" value="${sessionScope.struts_action_language}" />
<c:set var="Persian" value="fa" />
<c:set var="English" value="en" />
<table width="100%" align="right" cellpadding="0" cellspacing="0"
	border="0" height="10px">
	<tr>
		<td width="182px" bgcolor="#e2e7eb"></td>
  <td
			background="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>Footer_1.jpg"
			class="footerCopyRight footerTopLineCell" align="center"
			width="40px" ></td>
	      
		<td
			background="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>Footer_1.jpg"
			class="footerCopyRight footerTopLineCell" align="center"
			width="150px" style="font-weight: bold;font-size:10px"><bean:message
			key="global.CurrentDateAndTime" />:</td>
		<td
			background="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>Footer_1.jpg"
			class="footerCopyRight footerTopLineCell" align="center"  width="60px"
			style="font-weight: bold"><%=DateUtils.getCurrentTime()%></td>
		
			<td
				background="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>Footer_1.jpg"
				class="footerCopyRight footerTopLineCell" align="right"
				style="font-weight: bold" width="100px"><%=dateFarsi.formatFdate(dateFarsi.CurFdate())%>

			</td>
		
		
		
			<td
				background="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>Footer_1.jpg"
				class="footerCopyRight footerTopLineCell" align="right"
				style="font-weight: bold" width="100px">&nbsp;</td>
		
		
			<td
				background="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>Footer_1.jpg"
				class="footerCopyRight footerTopLineCell" align="right"
				style="font-weight: bold"    >&nbsp;</td>
		
		
		<td background="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>Footer_1.jpg" class="footerCopyRight footerTopLineCell"  align="center">
  	
  </td>
  	<td width="100px"   background="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>Footer_1.jpg"  class="footerCopyRight footerTopLineCell" align="center"></td>
  	
  
 
	
			<td  width="130px"  class="footerCopyRight footerTopLineCell"><img
				src="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>Top_link_Bar.jpg"></td>
		
		

		
	
	</tr>
</table>

