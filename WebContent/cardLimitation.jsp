<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="proxy.com.iac.ambit.model.BlackList"%>
<%@ page import="com.iac.ambit.utils.Config"%>
<%@ page import="com.iac.ambit.utils.AmbitUtility"%>
<%@ page import="com.iac.ambit.utils.Constants"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<script type="text/javascript" src="javascripts/javascript.js"></script>

<script language="javascript">

  function cancel(){ 
      
      document.cardLimitationForm.method.value = "cancel";     
  }
  
 function initiate(){ 
    	 document.all.chkShetabiTransaction.disabled = true;
         document.all.terminalNumber.disabled = true;
         document.all.chkATM.disabled = true;
         document.all.chkPOS.disabled = true;
         document.all.chkInternet.disabled = true;
         document.all.chkTelephone.disabled = true;
         document.all.chkMobile.disabled = true;
         document.all.chkPinPad.disabled = true;
         document.all.chkWithdraw.disabled = true;
         document.all.chkFundTransfer.disabled = true;
         document.all.chkBalance.disabled = true;
         document.all.chkPurchase.disabled = true;
         document.all.chkMiniStatement.disabled = true;
         document.all.chkValidation.disabled = true;
         document.all.chkBillPayment.disabled = true;
         document.all.chkChangePin.disabled = true;
         document.all.chkChangePin2.disabled = true;
         document.all.notes.disabled = true;
         document.all.cancelButton.disabled = true;
         document.all.submit.disabled = true;
         document.all.terminalNumber.style.border = '1px solid'
         document.all.terminalNumber.style.borderColor = 'silver';
    }
    
function toggleOnOff(alpha)
{
    	 document.all.chkShetabiTransaction.disabled = false;
         document.all.terminalNumber.disabled = false;
         document.all.chkATM.disabled = false;
         document.all.chkPOS.disabled = false;
         document.all.chkInternet.disabled = false;
         document.all.chkTelephone.disabled = false;
         document.all.chkMobile.disabled = false;
         document.all.chkPinPad.disabled = false;
         document.all.chkWithdraw.disabled = false;
         document.all.chkFundTransfer.disabled = false;
         document.all.chkBalance.disabled = false;
         document.all.chkPurchase.disabled = false;
         document.all.chkMiniStatement.disabled = false;
         document.all.chkValidation.disabled = false;
         document.all.chkBillPayment.disabled = false;
         document.all.chkChangePin.disabled = false;
         document.all.chkChangePin2.disabled = false;
         document.all.notes.disabled = false;
         document.all.submit.disabled = false;
         document.all.cancelButton.disabled = false;
         document.all.terminalNumber.style.border = '1px solid'
         document.all.terminalNumber.style.borderColor = '#0A0756';
        
} 
</script>
<html:javascript formName="cardLimitationForm" method="validateForm"
	dynamicJavascript="true" staticJavascript="true" cdata="false" />
	
<body onload="initiate();">
<script language="Javascript1.1" src="staticJavascript.do"></script>
<% 
		 BlackList bl = (BlackList)request.getAttribute("BLACKLIST");		
%>

<table width="100%" border="0" cellspacing="5" cellpadding="0"
	class="WelcomeTableBorder">
	<tr>
        <td  width="100%" height="5px">
        </td>
   </tr>
	<tr>
		<td class="purpleBarText"><bean:message
			key="cardLimitation.topBoxHeading" /></td>
	</tr>
	
	
	
<html:form action="cardLimitationAction.do"  onsubmit="return validateForm(this);">
<tr>
		<td>
		<table width="100%" border="0" cellpadding="3" cellspacing="3" class="captionBold" align="right"
			>
			<tr >
					
				<td align="right"><bean:message key="cardLimitation.cardNo" /> :&nbsp;<%=AmbitUtility.nvl(((String)session.getAttribute("cardNO")))%> </td>
				<td align="right"><bean:message key="cardLimitation.nameAndFamily" /> :&nbsp;<%=AmbitUtility.nvl(((String)session.getAttribute("nameAndFamilyName")))%></td>
				<td align="right"><bean:message key="cardLimitation.cardStatus" /> :&nbsp;<%=AmbitUtility.nvl(((String)session.getAttribute("cardStatus")))%></td>
			</tr>
			<tr>
				<td colspan="3" align="center" >
				&nbsp;&nbsp;
					</td>									 								
			</tr>
			
			<tr  >
				<td colspan="3" align="center" > 
				<logic:match name="allowedActions"  value="<%=Config.getProperty("Edit")%>">
				<html:button property="change" styleClass="submitButtons" onclick="toggleOnOff(this);">		
															<bean:message
																key="global.editButton" />
				</html:button>&nbsp;&nbsp;
				</logic:match>
				<html:cancel styleClass="submitButtons">
				<bean:message key="global.backButton" />
				</html:cancel>
				
				</td>									 								
			</tr>
			
		</table>
		</td>
	</tr>
	<tr>
	<html:hidden
						property="method" value="" />	
        <html:hidden property="cardNo" value="<%=AmbitUtility.nvl(((String)session.getAttribute("cardNO")))%>" />	
		<td class="eformsBorder" >
		<table width="100%" border="0" cellpadding="3" cellspacing="3" class="eforms acountSearch" align="right"
			>
			<tr >
				<td  class="caption" hidefocus> 1- <bean:message key="cardLimitation.shetabiTransaction" /> 
				<%if(AmbitUtility.nvl(bl.getAllowedShetab()).equalsIgnoreCase(Constants.CODE_ACTIVE_FLAG.ACTIVE)){%>								
				<input type="checkbox"  name="chkShetabiTransaction" checked="true"  />		
				<%} else {%>
				<input type="checkbox"  name="chkShetabiTransaction"   />
				<%}%>
				</td>																				
			</tr>
			
		</table>
		</td>
	</tr>
	
	<tr>
		<td class="eformsBorder" >
		<table width="100%" border="0" cellpadding="3" cellspacing="3" class="eforms acountSearch" align="right"
			>
			<tr >
				<td colspan="6" class="caption" > 2- <bean:message key="cardLimitation.terminalTransaction" /></td>
			</tr>
			<tr>
				<td class="captionBold" > <bean:message key="cardLimitation.ATM" />
				<input type="checkbox" name="chkATM" <%=AmbitUtility.nvl(((String)request.getAttribute("ATM")))%> value="<%=Config.getProperty("ATM")%>"  />	
				</td>
																				
				<td class="captionBold" > <bean:message key="cardLimitation.POS" />
				<input type="checkbox" name="chkPOS"  <%=AmbitUtility.nvl(((String)request.getAttribute("POS")))%> value="<%=Config.getProperty("POS")%>"    />	
				</td>
				
												
				<td class="captionBold" > <bean:message key="cardLimitation.internet" />
				<input type="checkbox" name="chkInternet" <%=AmbitUtility.nvl(((String)request.getAttribute("Internet")))%> value="<%=Config.getProperty("Internet")%>"    />	
				</td>
				
				
				<td class="captionBold" > <bean:message key="cardLimitation.telephone" />
				<input type="checkbox" name="chkTelephone" <%=AmbitUtility.nvl(((String)request.getAttribute("Telephone")))%> value="<%=Config.getProperty("Telephone")%>"    />	
				</td>
				
												
				<td class="captionBold" > <bean:message key="cardLimitation.mobile" />
				<input type="checkbox" name="chkMobile" <%=AmbitUtility.nvl(((String)request.getAttribute("mobile")))%> value="<%=Config.getProperty("mobile")%>"    />	
				</td>
				
				<td class="captionBold" > <bean:message key="cardLimitation.pinpad" />
				<input type="checkbox" name="chkPinPad" <%=AmbitUtility.nvl(((String)request.getAttribute("PinPad")))%> value="<%=Config.getProperty("PinPad")%>"    />	
				</td>
			</tr>
			
		</table>
		</td>
	</tr>
	
	<tr>
		<td class="eformsBorder" >
		<table width="100%" border="0" cellpadding="3" cellspacing="3" class="eforms acountSearch" align="right">
			
			<tr >
				<td class="caption" > 3- <bean:message key="cardLimitation.notTransactionOnTerminal" /> : 
				<html:text property="terminalNumber" maxlength="8"  styleClass="lessWidthInputControls" value="<%=AmbitUtility.nvl(bl.getAllowedTerminals())%>" onkeyup="validateNumber(this)" /></td>
												 								
			</tr>
			
		</table>
		</td>
	</tr>
	
	<tr>
		<td class="eformsBorder" >
		<table width="100%" border="0" cellpadding="3" cellspacing="3" class="eforms acountSearch" align="right">
			
			<tr >
				<td class="caption" colspan="5" > 4- <bean:message key="cardLimitation.services" /> </td>								 								
			</tr>
			<tr >
				<td  class="captionBold" >  <bean:message key="cardLimitation.withdraw" />
				<input type="checkbox" name="chkWithdraw" <%=AmbitUtility.nvl(((String)request.getAttribute("withdraw")))%> value="<%=Config.getProperty("withdraw")%>"    />	
				</td>
				
				<td  class="captionBold" >  <bean:message key="cardLimitation.fundTransfer" />
				<input type="checkbox" name="chkFundTransfer" <%=AmbitUtility.nvl(((String)request.getAttribute("fundtransfer")))%> value="<%=Config.getProperty("fundtransfer")%>"    />	
				</td>
				
				<td  class="captionBold" >  <bean:message key="cardLimitation.balance" />
				<input type="checkbox" name="chkBalance" <%=AmbitUtility.nvl(((String)request.getAttribute("balance")))%> value="<%=Config.getProperty("balance")%>"    />	
				</td>
				
					
				<td  class="captionBold" >  <bean:message key="cardLimitation.miniStatement" />
				<input type="checkbox" name="chkMiniStatement" <%=AmbitUtility.nvl(((String)request.getAttribute("miniStatment")))%> value="<%=Config.getProperty("miniStatment")%>"    />	
				</td>
				
					
				<td  class="captionBold" >  <bean:message key="cardLimitation.purchase" />
				<input type="checkbox" name="chkPurchase" <%=AmbitUtility.nvl(((String)request.getAttribute("purchase")))%> value="<%=Config.getProperty("purchase")%>"    />	
				</td>									 								 								
			</tr>
			
			<tr >
				<td  class="captionBold" >  <bean:message key="cardLimitation.validation" />
				<input type="checkbox" name="chkValidation" <%=AmbitUtility.nvl(((String)request.getAttribute("validation")))%> value="<%=Config.getProperty("validation")%>"    />	
				</td>	
				 							
				<td  class="captionBold" >  <bean:message key="cardLimitation.billPayment" />
				<input type="checkbox" name="chkBillPayment" <%=AmbitUtility.nvl(((String)request.getAttribute("billPayment")))%> value="<%=Config.getProperty("billPayment")%>"    />	
				</td>
				
				<td  class="captionBold" >  <bean:message key="cardLimitation.changePin" />
				<input type="checkbox" name="chkChangePin" <%=AmbitUtility.nvl(((String)request.getAttribute("changePin")))%> value="<%=Config.getProperty("changePin")%>"    />	
				</td>	
				
				<td  class="captionBold" >  <bean:message key="cardLimitation.changePin2" />
				<input type="checkbox" name="chkChangePin2" <%=AmbitUtility.nvl(((String)request.getAttribute("changepin2")))%> value="<%=Config.getProperty("changepin2")%>"    />	
				</td>								 
													 								
			</tr>
			
		</table>
		</td>
	</tr>
	
	<tr>
		<td class="eformsBorder">
		<table width="100%" border="0" cellpadding="3" cellspacing="3" class="eforms acountSearch" align="right">
			<tr >
				<td class="caption" > <bean:message key="cardLimitation.explain" /> : </td>									 								
			</tr>
			<tr >
			<td class="captionBold" ><html:textarea  property="notes" value="<%=AmbitUtility.nvl(bl.getComments())%>" styleId="notes" styleClass="inputControlsNoborder" style="width: 95%" rows="5"  />
              </td>
            </tr>
              
		</table>
		</td>
	</tr>
	
	<tr>
		<td>
		<table width="100%" border="0" cellpadding="3" cellspacing="3" class="captionBold" align="right">
			<tr >
				<td align="center" > 
				<logic:match name="allowedActions"  value="<%=Config.getProperty("Edit")%>">
				<html:submit property="submit" styleClass="submitButtons">		
															<bean:message
																key="global.updateButton" />
				</html:submit>&nbsp;&nbsp;
				<html:submit onclick="cancel();"  property="cancelButton" 
								styleClass="submitButtons" >
								<bean:message key="global.CancelButton" />
				</html:submit>
				</logic:match>
							
				</td>									 								
			</tr>
			
              
		</table>
		</td>
	</tr>

</html:form>

</table>

</body>