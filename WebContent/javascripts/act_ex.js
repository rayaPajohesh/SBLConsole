function openWin(win_width, win_height)
{
	var newwin = window.open('', '', 'copyhistory=0, directories=0, location=0, \
								menubar=0, scrollbars=yes, status=0, toolbar=0, \
								width=' + win_width + ', height=' + win_height + ', resizable=yes');
								newwin.moveTo(0, 0);
}

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
        if (url == 'Account Mini Statement') {
          document.navigationForm.navigationFormTitle.value = 'Account Mini Statement';  
          document.navigationForm.action = '<html:rewrite page="/miniAccountStatement.do" />';  
          document.navigationForm.submit();  
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
        
        if (url == 'Cheque Status') {
          document.navigationForm.navigationFormTitle.value = 'Cheque Status';
          document.navigationForm.action = '<html:rewrite page="/chequeStatus.do" />';
          document.navigationForm.submit();
        }else
         if (url == 'Stop Cheque') {
          document.navigationForm.navigationFormTitle.value = 'Stop Cheque';
          document.navigationForm.action = '<html:rewrite page="/stopChequePayment.do" />';  
          document.navigationForm.submit();
        }else    
        if (url == 'Complaints') {
/*          document.navigationForm.navigationFormTitle.value = 'Complaints';
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
        if (url == 'Submit Request') {
          document.navigationForm.navigationFormTitle.value = 'Submit Request';
          document.navigationForm.action = '<html:rewrite page="/submitRequest.do" />';  
          document.navigationForm.submit();
        }else
        if (url == 'Activity Log') {
          document.navigationForm.navigationFormTitle.value = 'Activity Log';
          document.navigationForm.action = '<html:rewrite page="/activityLog.do?first=yes" />';
          document.navigationForm.submit();
        }else
        if (url == 'Profile') {
/*          document.navigationForm.navigationFormTitle.value = 'Profile';
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
        if (url == 'Feed Back') {
          document.navigationForm.navigationFormTitle.value = 'Feed Back';
          document.navigationForm.action = '<html:rewrite page="/feedBackForward.do" />';  
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
        if (url == 'Credit Card Payment') {
          document.navigationForm.navigationFormTitle.value = 'Credit Card Payment';
          document.navigationForm.action = '<html:rewrite page="/CreditCardPayForward.do?first=yes" />';
          document.navigationForm.submit();
        }else 
        if (url == 'Change Card Trans Password') {
          document.navigationForm.navigationFormTitle.value = 'Change Card Trans Password';
          document.navigationForm.action = '<html:rewrite page="/changeCardTransPassword.do" />';  
          document.navigationForm.submit();
        }else         
        if (url == 'Full Statment Request') {
          document.navigationForm.navigationFormTitle.value = 'Full Statment Request';
          document.navigationForm.action = '<html:rewrite page="/FullStatmentRequestForward.do?first=yes" />';  
          document.navigationForm.submit();
        }else             
        if (url == 'Cheque Book Request') {
          document.navigationForm.navigationFormTitle.value = 'Cheque Book Request';
          document.navigationForm.action = '<html:rewrite page="/ChequeBookRequestForward.do?first=yes" />';  
          document.navigationForm.submit();
        }else    
        if (url == 'Utility Bill Payment') {
          document.navigationForm.navigationFormTitle.value = 'Utility Bill Payment';
          document.navigationForm.action = '<html:rewrite page="/BillPayForward.do?first=yes" />';  
          document.navigationForm.submit();
        }else    
        if (url == 'Funds Transfer') {
          document.navigationForm.navigationFormTitle.value = 'Funds Transfer';
          document.navigationForm.action = '<html:rewrite page="/FundsTransferForward.do?first=yes" />';  
          document.navigationForm.submit();
        }else    
        if (url == 'ATM Card Request') {
          document.navigationForm.navigationFormTitle.value = 'ATM Card Request';
          document.navigationForm.action = '<html:rewrite page="/ATMCardRequestForward.do?first=yes" />';  
          document.navigationForm.submit();
        }else    
        if (url == 'Stop Cheque Request') {
          document.navigationForm.navigationFormTitle.value = 'Stop Cheque Request';
          document.navigationForm.action = '<html:rewrite page="/StopChequeForward.do?first=yes" />';
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
          document.navigationForm.action = '<html:rewrite page="/tdSummaryAction.do" />';
          document.navigationForm.submit();
        }else
        if (url == 'Fixed Deposit') {
          document.navigationForm.navigationFormTitle.value = 'Fixed Deposit';
          document.navigationForm.action = '<html:rewrite page="/fixedDepositForward.do?first=yes" />';
          document.navigationForm.submit();
        }else
        if (url == 'Outward Fund Transfer') {
          document.navigationForm.navigationFormTitle.value = 'Outward Fund Transfer';
          document.navigationForm.action = '<html:rewrite page="/outwardTransferForward.do" />';
          document.navigationForm.submit();
        }else
        if (url == 'Standing Order') {
          document.navigationForm.navigationFormTitle.value = 'Standing Order';
          document.navigationForm.action = '<html:rewrite page="/standingOrderForward.do?first=yes" />';
          document.navigationForm.submit();
        }
        
        
  }

