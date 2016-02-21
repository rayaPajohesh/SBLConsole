<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="com.iac.ambit.utils.Constants"%>
<%@ page import="com.iac.ambit.utils.Config"%>
 
 
   
  <html:javascript formName="/changePasswordAction" method="validateForm" dynamicJavascript="true" staticJavascript="false" cdata="false"/>
  <script language="Javascript1.1" src="staticJavascript.do"></script>
<body dir="<%=session.getAttribute( Constants.JspConstants.PAGE_DIR  )%>">
    <html:form action="changePasswordAction.do" onsubmit="return validateForm(this);"   focus="oldPassword">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="WelcomeTableBorder">
              <tr>
                <td class="purpleBarText" width="100%">
                  <bean:message key="changePassword"/>
                </td>
              </tr>
              <tr>
              <td>
				<table width="100%" border="0" cellpadding="2" cellspacing="4"
						class="purpleCustomerMessages">
				 <tr>
					<td>&nbsp;<bean:message key="changePassword.help" /></td>
				 </tr>
				</table>
			  </td>
			 </tr>
             <tr><td height="10px"></td></tr>
              <tr>
                <td>
                  <table border="0" align="center" cellpadding="0" cellspacing="0" width="97%">
			       <tr>
			        <td class="eformsBorder" width="100%">
			                 <table width="100%" border="0" cellspacing="4" cellpadding="0" class="eforms accountSearch" align="left">
                    <tr>
                      <td >
                       
                          
                       
                        <table width="100%" cellpadding="0" class="acountSearch" cellspacing="5" border="0">

                         <tr><td height="3px"></td></tr>
                         <tr>
                            
                                                     
                           
                            <td  class="captionBold" >
                              <bean:message key="changePassword.oldPassword"/>
                            </td>
                            
                            
                            <td >
                              <html:password property="oldPassword" styleClass="passwordControls" redisplay="false" maxlength="15"/><bean:message key="global.mendatoryField"/>
                            </td>
                           
                                                      
                          </tr>
                          <tr> 
                            <td  class="captionBold" >
                              <bean:message key="changePassword.newPassword"/>
                            </td>
                                  
                           
                            <td >
                              <html:password property="newPassword" styleClass="passwordControls" redisplay="false" maxlength="15"/><bean:message key="global.mendatoryField"/>
                            </td>
                           
                            
                            
                          </tr>
                          <tr>
                            <td  class="captionBold">
                              <bean:message key="changePassword.confirmPassword"/>
                            </td>
                            <!-- ashrafi:set locale  1386/06/4 -->
                           
                            <td >
                              <html:password property="confirmPassword" styleClass="passwordControls" redisplay="false" maxlength="15"/><bean:message key="global.mendatoryField"/>
                            </td>
                            
                          </tr>
                          <tr>
                          
                            <td   align="center"  colspan="2">
                              <html:submit styleClass="submitButtons">
                                <bean:message key="global.submitButton"/>
                              </html:submit>
                              <html:reset styleClass="submitButtons" onclick="javascript:document.all.oldPassword.focus();">
                                <bean:message key="global.resetButton"/>
                              </html:reset>
                            </td>
                           
                          </tr> 
                        
                        </table>
                      </td>
                    </tr>
                      <tr>
			               <td     height="5px">
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
</body>