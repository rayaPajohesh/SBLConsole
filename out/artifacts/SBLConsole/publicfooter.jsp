<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.iac.ambit.utils.Constants"%>
<!-- ashrafi added 'publicfooter.jsp' in 'fa' locale    86/09/19 -->

<table width="880px" align="left"  cellpadding="0" cellspacing="0" border="0" height="15px">
 <tr>
    <td background="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>Footer_1.jpg" class="footerCopyRight" width="20px"></td>
  	<td background="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>Footer_1.jpg" class="footerCopyRight" align="center" width="325px">
  	<bean:message key="publicFooter.message.enCopyright"/>
  	
  	</td>
  	
 	<td background="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>Footer_1.jpg"  class="footerCopyRight" align="center" > | </td>
 	<td background="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>Footer_1.jpg"  class="footerCopyRight" align="center"   width="40px">  </td>
  	<td background="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>Footer_1.jpg"  class="footerCopyRight"  align="center" width="400px" >
  	<bean:message key="publicFooter.message.copyrightSarmaye"/>
  	</td>
  	<td><img src="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>Footer_2.jpg" ></td>
  	<td class="footerCopyRight"  align="right"></td>
  	<td class="footerCopyRight"  width="120px"></td>
  </tr>
</table>