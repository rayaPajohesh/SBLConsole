<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.iac.ambit.utils.Config"%>
<%@ page import="com.iac.ambit.utils.Constants"%>
<%@ page import="com.iac.ambit.utils.SessionUtils"%>

<script type="text/javascript" src="javascripts/javascript.js"></script>

<body dir="<%=session.getAttribute( Constants.JspConstants.PAGE_DIR  )%>" onload="javascript:document.all.customerId.focus();">

<script language="Javascript1.1" src="staticJavascript.do"></script>
<script type="text/javascript" src="javascripts/vkboards.js"></script>

<html:javascript formName="loginForm" method="validateForm"
	dynamicJavascript="true" staticJavascript="false" cdata="false" />

<script><!--

   var opened = false, vkb = null, text = null, insertionS = 0, insertionE = 0;

   var userstr = navigator.userAgent.toLowerCase();
   var isgecko = (userstr.indexOf('gecko') != -1) && (userstr.indexOf('applewebkit') == -1);


  //--></script>

<table class="bodyBG" border="0" cellspacing="0" cellpadding="0" 
	height="100%" width="100%" style="background-color:#F3F3F3">
	<tr>
		<td valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="100%">
			<tr>
				<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					height="1%" style="background-repeat: repeat-x">
					<tr>
						<td width="1%"><img
							src="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>User_Login_Heading.jpg"></td>
						<td
							background="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>User_Login_Heading_rep_2.jpg">
						&nbsp;</td>

					</tr>
					<%if (!SessionUtils.existCustomerSessionId(request.getSession()
					.getId())) {

			%>
				<!--	<tr>
						<td class="HeaderText"><a href="#"
							style="text-decoration : none; font-weight: bold"
							onclick="javascript: document.location.replace('<c:url value="/localeAction.do?language=fa" />');">
						<bean:message key="global.Persian" /></a> |<a href="#"
							style="text-decoration : none;font-weight: bold"
							onclick="javascript: document.location.replace('<c:url value="/localeAction.do?language=en" />');">
						<bean:message key="global.English" /></a></td>

					</tr>-->
					<%}%>
				</table>
				</td>
			</tr>
			<tr>

				<td class="loginDetailsCell" colspan="2">
				<table width="100%" border="0" cellpadding="5" cellspacing="0">
					<logic:present name="org.apache.struts.action.ERROR">
					
						<tr>
							<td align="center">
							<table cellpadding="0" cellspacing="0" border="0"
								class="errorTable">
								<tr>
									<td class="errorTopCell" />
								</tr>
								<tr>
									<td class="errorMidCell"><img
										src="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>erroricon.gif"
										border="0" /> <html:errors /></td>
								</tr>
								<tr>
									<td class="errorBottomCell" />
								</tr>
							</table>
							</td>
						</tr>
						
					</logic:present>
					<logic:present name="org.apache.struts.action.ACTION_MESSAGE">
						
						<tr>
							<td align="center">
							<table cellpadding="0" cellspacing="0" border="0"
								class="errorTable">
								<tr>
									<td class="errorTopCell" />
								</tr>
								<tr>
									<td class="errorMidCell" align="center"><html:messages
										id="message" message="true" header="messages.header"
										footer="messages.footer">
										<bean:write name="message" filter="false" />
									</html:messages></td>
								</tr>
								<tr>
									<td class="errorBottomCell" />
								</tr>
							</table>
							</td>
						</tr>
						
					</logic:present>



				</table>
				</td>
			</tr>
			<tr>

				<td colspan="2" valign="top" class="loginBG"><html:form
					action="loginAction.do" styleId="loginFormJ">


					<table width="100%" border="0" cellspacing="0" cellpadding="0" >

						<tr>


							<td>

							<table align="left" width="100%" border="0" cellspacing="0"
								cellpadding="5"  >



								<tr>

									<td class="loginBodyBoldText"  ><bean:message
										key="login.customerId" />&nbsp;:</td>
									<td   ><input type="text" name="customerId" id="customerId" 
										styleClass="inputControls"
										maxlength="50" /><bean:message key="global.mendatoryField" /></td>

								</tr>
								<tr></tr>
								<tr>

									<td class="loginBodyBoldText"><bean:message
										key="login.customerPassword" />&nbsp;:</td>
									<td ><input type="password" name="customerPassword" 
										id="customerPassword" 
										styleClass="inputControls" maxlength="15"  /><bean:message
										key="global.mendatoryField"   /></td>

								</tr>


<tr >
			                      <td  class="loginBodyBoldText"  >
			                        <bean:message key="login.captcha"/>&nbsp;:
			                      </td>
			                      
			                      <td  ><input maxlength="5" styleClass="inputControls" 
										
										type="text"
										id="captcha" 
										 name="captcha" /><bean:message  key="global.mendatoryField" />
										
										</td>
			                    </tr>
			                    <tr >
			                    	<td></td>
									<td nowrap="nowrap">
									

									<table border="0">
										<tr align="center">
										
											<td>

											<div id="divCaptcha"><img id="captchaImage"
												name="captchaImage" class="captchaImage"
												src='<%=Config.getProperty( "CAPTCHA_PATH" ).toString()%>' /></div>
											</td>
											<td><img
												src="images/fa_IR/Refresh icon.png"
												style="cursor: pointer" onclick="refreshCaptcha();"
												title="<bean:message key="global.refresh"/>" /></td>
											
										</tr>
									</table>

									</td>
			                    </tr>

							

								<tr style="padding-top: 5px">
									<!--roohi:alignment changed from right to center  860513/!-->

									<td dir="rtl" colspan="5" align="center"><html:submit
										styleClass="loginButtons">
										<bean:message key="login.submit" />
									</html:submit> &nbsp; <html:reset styleClass="loginButtons"
										onclick="javascript:document.all.customerId.focus();">
										<bean:message key="login.reset" />
									</html:reset> &nbsp; <!--roohi:alignment changed from right to center  860513/!-->
									</td>

								</tr>
								<tr>


								</tr>

							</table>
							<html:hidden property="language" value="" /> </html:form></td>
							
						</tr>
					</table></td>

			</tr>
		</table>
		</td>
	</tr>
</table>
</body>

