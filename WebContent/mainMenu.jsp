<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="com.iac.ambit.utils.Constants"%>
<script src="javascripts\AC_RunActiveContent.js" language="javascript"></script>



<SCRIPT LANGUAGE=JavaScript>

var CoolListReady=0;
var CoolListLoaded=false;
var CoolListError=false;

var myTimer=setInterval("LoaderCheck()",600);
var sXMLList;
var waitLoadFlag = false;
    
//****************************************************************************
//								LoaderCheck                                   
//****************************************************************************
function LoaderCheck(){	

	try{
		CoolListReady=window.document.CoolList.GetVariable("CoolListReady")
		if (CoolListReady=="1")
		{
			window.document.CoolList.SetVariable("structure", sXMLList);
			clearInterval(myTimer);
		}
	}
	catch(e) {
		CoolListError=true;
	}


}
//****************************************************************************
//						    End of LoaderCheck                                   
//****************************************************************************



//****************************************************************************
//						    CoolList Methods                                   
//****************************************************************************

// loadXml

function loadXml(){
        <% 
            String navigationFormTitle = request.getParameter("navigationFormTitle");
            if(navigationFormTitle != null && !navigationFormTitle.equalsIgnoreCase(""))
            {
                session.setAttribute("COOL_LIST_ACTIVE_TITLE",navigationFormTitle);
            }
           String xml = (String)session.getAttribute(Constants.MENU_XML_FILE_IN_APPLICATION); 
           String title =(String) session.getAttribute("COOL_LIST_ACTIVE_TITLE");
           xml = xml.replaceAll("activeTitle=''", "activeTitle='" + title + "'");          
        %>
        var XMLStr="<%=xml%>";

	sXMLList = XMLStr;
}


//****************************************************************************
//						    End of CoolList Methods                                   
//****************************************************************************



    function openPage(url){
    
        if(url == 'Home') {
          document.navigationForm.navigationFormTitle.value = 'Home';      
          document.navigationForm.action = '<html:rewrite page="/welcome.do" />';  
          document.navigationForm.submit();  
        }else
        if (url == 'Accounts') {
         /* document.navigationForm.navigationFormTitle.value = 'Accounts';  
          document.navigationForm.action = '<html:rewrite page="/accountSummaryForward.do" />';  
          document.navigationForm.submit();  */
        }else
        if (url == 'Account Full Statement') {
          document.navigationForm.navigationFormTitle.value = 'Account Full Statement';
          document.navigationForm.action = '<html:rewrite page="/fullAccountStatement.do?first=yes" />';
          document.navigationForm.submit();
        }else
        if (url == 'Account Summary') {
          document.navigationForm.navigationFormTitle.value = 'Account Summary';
          document.navigationForm.action = '<html:rewrite page="/accountSummary.do" />';  
          document.navigationForm.submit();
        }else
        if (url == 'Balance') {
          document.navigationForm.navigationFormTitle.value = 'Balance';
          document.navigationForm.action = '<html:rewrite page="/getBalance.do" />';  
          document.navigationForm.submit();
        }else
          if (url == 'Sub Card List') {
          document.navigationForm.navigationFormTitle.value = 'Sub Card List';
          document.navigationForm.action = '<html:rewrite page="/subCardList.do" />';  
          document.navigationForm.submit();
        }else
        if (url == 'Complaints') {
        /*document.navigationForm.navigationFormTitle.value = 'Complaints';
          document.navigationForm.action = '<html:rewrite page="/listComplaintAction.do" />';  
          document.navigationForm.submit();*/
        }else
        if (url == 'Add Complaint') {
          document.navigationForm.navigationFormTitle.value = 'Add Complaint';
          document.navigationForm.action = '<html:rewrite page="/addComplaintForward.do?first=yes" />';  
          document.navigationForm.submit();
        }else
        if (url == 'Complaint List') {
          document.navigationForm.navigationFormTitle.value = 'Complaint List';
          document.navigationForm.action = '<html:rewrite page="/listComplaintAction.do" />';  
          document.navigationForm.submit();
        }else
        if (url == 'Request Forms') {
/*          document.navigationForm.navigationFormTitle.value = 'Request Forms';
          document.navigationForm.action = '<html:rewrite page="/listEformAction.do" />';  
          document.navigationForm.submit();*/
        }else
        if (url == 'Request List') {
          document.navigationForm.navigationFormTitle.value = 'Request List';
          document.navigationForm.action = '<html:rewrite page="/listEformAction.do" />';  
          document.navigationForm.submit();
        }else
        if (url == 'Activity Log') {
          document.navigationForm.navigationFormTitle.value = 'Activity Log';
          document.navigationForm.action = '<html:rewrite page="/activityLog.do?first=yes" />';
          document.navigationForm.submit();
        }else
        if (url == 'Profile') {
         /*document.navigationForm.navigationFormTitle.value = 'Profile';
          document.navigationForm.action = '<html:rewrite page="/personalizeForward.do" />';  
          document.navigationForm.submit();*/
        }else
        if (url == 'Personalize') {
          document.navigationForm.navigationFormTitle.value = 'Personalize';
          document.navigationForm.action = '<html:rewrite page="/personalizeForward.do" />';  
          document.navigationForm.submit();
        }else    
        if (url == 'Change Pin2') {
          document.navigationForm.navigationFormTitle.value = 'Change Pin2';
          document.navigationForm.action = '<html:rewrite page="/changePin2.do" />';  
          document.navigationForm.submit();
        }else
        if (url == 'Cycle Change') {
          document.navigationForm.navigationFormTitle.value = 'Cycle Change';
          document.navigationForm.action = '<html:rewrite page="/cycleChange.do" />';  
          document.navigationForm.submit();
        }else
        if (url == 'Cycle Amount RemChange') {
          document.navigationForm.navigationFormTitle.value = 'Cycle Amount RemChange';
          document.navigationForm.action = '<html:rewrite page="/cycleAmountRemChange.do" />';  
          document.navigationForm.submit();
        }else
        if (url == 'Negin IB Forward') {
          document.navigationForm.navigationFormTitle.value = 'Negin IB Forward';
          document.navigationForm.action = '<html:rewrite page="/neginIB.do" />';  
          document.navigationForm.submit();
        }else
        if (url == 'Change PIN') {
          document.navigationForm.navigationFormTitle.value = 'Change PIN';
          document.navigationForm.action = '<html:rewrite page="/changePIN.do" />';  
          document.navigationForm.submit();
        }else            
        if (url == 'Customer Info') {
          document.navigationForm.navigationFormTitle.value = 'Customer Info';
          document.navigationForm.action = '<html:rewrite page="/customerInfo.do" />';  
          document.navigationForm.submit();
        }else
        if (url == 'Alerts and Notifications') {
          document.navigationForm.navigationFormTitle.value = 'Alerts and Notifications';
          document.navigationForm.action = '<html:rewrite page="/alert.do" />';  
          document.navigationForm.submit();
        }else    
        if (url == 'Logout') {
          document.navigationForm.navigationFormTitle.value = 'Logout';
          document.navigationForm.action = '<html:rewrite page="/logout.do" />';  
          document.navigationForm.submit();
        }else    
        if (url == 'Supplementary Card') {
          document.navigationForm.navigationFormTitle.value = 'Supplementary Card';
          document.navigationForm.action = '<html:rewrite page="/supCardForward.do?first=yes" />';
          document.navigationForm.submit();
        }else
        if (url == 'Change Card Trans Password') {
          document.navigationForm.navigationFormTitle.value = 'Change Card Trans Password';
          document.navigationForm.action = '<html:rewrite page="/changeCardTransPassword.do" />';  
          document.navigationForm.submit();
        }else         
        if (url == 'Full Statment Request') {
          document.navigationForm.navigationFormTitle.value = 'Full Statement Request';
          document.navigationForm.action = '<html:rewrite page="/FullStatmentRequestForward.do?first=yes" />';  
          document.navigationForm.submit();
        }else            
        if (url == 'Cheque Book Request') {
          document.navigationForm.navigationFormTitle.value = 'Cheque Book Request';
          document.navigationForm.action = '<html:rewrite page="/ChequeBookRequestForward.do?first=yes" />';  
          document.navigationForm.submit();
        }else    
        if (url == 'Beneficiary Settings') {
          document.navigationForm.navigationFormTitle.value = 'Beneficiary Settings';
          document.navigationForm.action = '<html:rewrite page="/BeneficiaryAddForward.do" />';  
          document.navigationForm.submit();
        }else    
        if (url == 'Transfer') {
          document.navigationForm.navigationFormTitle.value = 'Funds Transfer';
          document.navigationForm.action = '<html:rewrite page="/FundsTransferForward.do?first=yes" />';  
          document.navigationForm.submit();
        }else    
         if (url == 'Sub Card Charge') {
          document.navigationForm.navigationFormTitle.value = 'Sub Card Charge';
          document.navigationForm.action = '<html:rewrite page="/SubCardChargeForward.do?first=yes" />';  
          document.navigationForm.submit();
        }else      
        if (url == 'Debit Limits') {
          document.navigationForm.navigationFormTitle.value = 'Debit Limits';
          document.navigationForm.action = '<html:rewrite page="/setTransactionLimitsForward.do?first=yes" />';
          document.navigationForm.submit();
        }else
        if (url == 'CASA Accounts') {
          
          document.navigationForm.navigationFormTitle.value = 'CASA Accounts';
          document.navigationForm.action = '<html:rewrite page="/casaSummaryAction.do" />';
          document.navigationForm.submit();
        }else
        if (url == 'LOANS') {
          document.navigationForm.navigationFormTitle.value = 'LOANS';
          document.navigationForm.action = '<html:rewrite page="/loanSummaryAction.do" />';
          document.navigationForm.submit();
        }else
        if (url == 'Trade Finance') {
          document.navigationForm.navigationFormTitle.value = 'Trade Finance';
          document.navigationForm.action = '<html:rewrite page="/corporateSummaryAction.do" />';
          document.navigationForm.submit();
        }else
        if (url == 'Term Deposit') {
          document.navigationForm.navigationFormTitle.value = 'Term Deposit';
          document.navigationForm.action = '<html:rewrite page="/tdEformForward.do" />';
          document.navigationForm.submit();
        }else
         // ashrafi added request parameter first 86/09/13
        if (url == 'Payment Order') {
          document.navigationForm.navigationFormTitle.value = 'Payment Order';
          document.navigationForm.action = '<html:rewrite page="/payOrderForward.do?first=yes" />';
          document.navigationForm.submit();
        }else
         // ashrafi added request parameter first 86/09/11 
        if (url == 'Standing Instruction') {
          document.navigationForm.navigationFormTitle.value = 'Standing Instruction';
          document.navigationForm.action = '<html:rewrite page="/standingOrderForward.do?first=yes" />';
          document.navigationForm.submit();
        }
        else
        if (url == 'Block Card') {
          document.navigationForm.navigationFormTitle.value = 'Block Card';
          document.navigationForm.action = '<html:rewrite page="/markCardWarm.do" />';
          document.navigationForm.submit();
        }
        else
        if (url == 'Generate Pin') {
          document.navigationForm.navigationFormTitle.value = 'Generate Pin';
          document.navigationForm.action = '<html:rewrite page="/generatePin.do" />';
          document.navigationForm.submit();
        }
      
       }
</script>
<table align="left" cellpadding="0" cellspacing="0" border="0" width="180px"  height="400px">
  <tr >
    <td valign="top" >
		<script src="javascripts/coolistLoad.js"></script>

<script language="javascript">
	if (AC_FL_RunContent == 0) {	
		alert("This page requires AC_RunActiveContent.js. In Flash, run \"Apply Active Content Update\" in the Commands menu to copy AC_RunActiveContent.js to the HTML output folder.");
	} else {
	loadXml();
	if (( CoolListReady > 0)){
		AC_FL_RunContent(
			'codebase', 'https://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,0,0',
			'width', '180',
			'height', '493',
			'src', 'CoolList',
			'quality', 'high',
			'pluginspage', 'https://www.macromedia.com/go/getflashplayer',
			'align', 'middle',
			'play', 'true',
			'loop', 'true',
			'scale', 'showall',
			'wmode', 'window',
			'devicefont', 'false',
			'id', 'CoolList',
			'bgcolor', '#e2e7eb',
			'name', 'CoolList',
			'menu', 'true',
			'allowScriptAccess','samedomain',
			'movie', 'Flash/CoolList.swf',
			'salign', ''
			);
		}
	}
</script>
    </td>
  </tr>
</table>
<form method="post" name="navigationForm" action="<html:rewrite page="/welcomeToInvestBank.do" />">
  <input type="HIDDEN" name="navigationFormTitle" id="navigationFormTitle" value="none"/>
</form>
	