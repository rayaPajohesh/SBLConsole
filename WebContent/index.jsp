<%@ page import="java.util.Map"%>
<%@ page import="proxy.com.iac.ambit.model.Customer"%>
<%@ page import="com.iac.ambit.utils.Constants"%>
<%@ page import="com.iac.ambit.utils.ContextUtils"%>
<%@ page import="com.iac.ambit.utils.Config"%>
<%@ page import="javax.xml.rpc.holders.BooleanHolder"%>
<%@ page import="proxy.localhost.SBLService.axis.SpringWS.SpringWSSoapBindingStub;"%>



<%    
	

    String startupPage = "";
    if ( session != null ) 
    {   
        Customer customer = (Customer) session.getAttribute(Constants.CUSTOMER_IN_SESSION);       
      
           
           startupPage = "Home";
       
  
        
        
		
		//SpringWSSoapBindingStub proxyService = (SpringWSSoapBindingStub) ContextUtils.
		//	getBean("springWSSoapBindingStub");
		
		//if (Config.getPropertyAsBoolean("ambit.uri.checking")) {
		//	BooleanHolder isPermissionAvailable = new BooleanHolder();
		//	String responseCode = proxyService.isPermissionAvailableToCustomer(customer.getUserId(), startupPage,isPermissionAvailable);
		//	if (!isPermissionAvailable.value)
		//	{
		//		request.getRequestDispatcher("logout.do").forward(request,response);
		//	}
 			
//	}
		
       // ashrafi changed 860618 
        if(startupPage .equalsIgnoreCase("Home")){
          request.getRequestDispatcher("welcomeToIB.do").forward(request,response);
        }else    
        if (startupPage .equalsIgnoreCase( "Account Full Statement")){
          request.getRequestDispatcher("fullAccountStatement.do?first=yes").forward(request,response);
        }else
        if (startupPage .equalsIgnoreCase( "Account Summary")){
          request.getRequestDispatcher("accountSummary.do").forward(request,response);
        }else
        if (startupPage .equalsIgnoreCase( "Mini Statement")){
          request.getRequestDispatcher("miniStatementForward.do").forward(request,response);
        }
        if (startupPage .equalsIgnoreCase( "Sub Card List")){
          request.getRequestDispatcher("subCardList.do").forward(request,response);
        }
        else
        if (startupPage .equalsIgnoreCase( "Add Complaint")){
          request.getRequestDispatcher("addComplaintForward.do?first=yes").forward(request,response);
        }else
        if (startupPage .equalsIgnoreCase( "Complaint List")){
          request.getRequestDispatcher("listComplaintAction.do").forward(request,response);
        }else
        if (startupPage .equalsIgnoreCase( "Request List")){
          request.getRequestDispatcher("listEformAction.do").forward(request,response);
        }else
        if (startupPage .equalsIgnoreCase( "Activity Log")){
          request.getRequestDispatcher("activityLog.do?first=yes").forward(request,response);
        }else
        if (startupPage .equalsIgnoreCase( "Personalize")){
          request.getRequestDispatcher("personalizeForward.do").forward(request,response);
        }else
        if (startupPage .equalsIgnoreCase( "Change Pin2")){
          request.getRequestDispatcher("changePin2.do").forward(request,response);
        }else
        if (startupPage .equalsIgnoreCase( "Cycle Change")){
          request.getRequestDispatcher("cycleChange.do").forward(request,response);
        }else
        if (startupPage .equalsIgnoreCase( "Cycle Amount RemChange")){
          request.getRequestDispatcher("cycleAmountRemChange.do").forward(request,response);
        }else
        if (startupPage .equalsIgnoreCase( "Customer Info")){
          request.getRequestDispatcher("customerInfo.do").forward(request,response);
        }else       
        if (startupPage .equalsIgnoreCase( "Supplementary Card")){
          request.getRequestDispatcher("supCardForward.do?first=yes").forward(request,response);
        }else
        if (startupPage .equalsIgnoreCase( "Change Card Trans Password")){
          request.getRequestDispatcher("changeCardTransPassword.do").forward(request,response);
        }else         
        if (startupPage .equalsIgnoreCase( "Full Statment Request")){
          request.getRequestDispatcher("fullStatmentRequestForward.do").forward(request,response);
        }else        
        if (startupPage .equalsIgnoreCase( "Cheque Book Request")){
          request.getRequestDispatcher("chequeBookRequestForward.do").forward(request,response);
        }else
        if (startupPage .equalsIgnoreCase( "Beneficiary Settings")){
          request.getRequestDispatcher("beneficiaryAddForward.do").forward(request,response);
        }else
        if (startupPage .equalsIgnoreCase( "Transfer")){
          request.getRequestDispatcher("fundsTransferForward.do").forward(request,response);
        }else
        if (startupPage .equalsIgnoreCase( "Debit Limits")){
          request.getRequestDispatcher("setTransactionLimitsForward.do?first=yes").forward(request,response);
        }else
        if (startupPage .equalsIgnoreCase( "Term Deposit")){
          request.getRequestDispatcher("tdEformForward.do").forward(request,response);
        }else
        if (startupPage .equalsIgnoreCase( "Payment Order")){
          request.getRequestDispatcher("payOrderForward.do").forward(request,response);
        }else
        // ashrafi added request parameter first 86/09/11 
        if (startupPage .equalsIgnoreCase( "Standing Instruction")){
          request.getRequestDispatcher("standingOrderForward.do?first=yes").forward(request,response);
        }else
         if (startupPage .equalsIgnoreCase( "Block Card")){
          request.getRequestDispatcher("markCardWarm.do").forward(request,response);
        }else
        if (startupPage .equalsIgnoreCase( "Generate Pin")){
          request.getRequestDispatcher("generatePin.do").forward(request,response);
        }else
        if (startupPage .equalsIgnoreCase( "Cheque Status")){
          request.getRequestDispatcher("chequeStatusSearch.do").forward(request,response);
        }else
        if (startupPage .equalsIgnoreCase( "Stop Cheque Payment")){
          request.getRequestDispatcher("stopChequePayment.do").forward(request,response);
        }else         
        if (startupPage .equalsIgnoreCase( "Credit Card Payments")){
          request.getRequestDispatcher("creditCardPaymentForward.do").forward(request,response);
        }else    
        if (startupPage .equalsIgnoreCase( "Utility Bill Payments")){
          request.getRequestDispatcher("getUtilityBillPaymentType.do?first=yes").forward(request,response);
        }else    
        if (startupPage .equalsIgnoreCase( "Utility Bill Inquiry")){
          request.getRequestDispatcher("utilityBillInquiryForward.do?first=yes").forward(request,response);
        }else  
        if (startupPage .equalsIgnoreCase( "Bill Payment Schedule")){
          request.getRequestDispatcher("billPaymentStandingInstructionForward.do").forward(request,response);
        }else  
        if (startupPage .equalsIgnoreCase( "Contact Update Request")){
          request.getRequestDispatcher("contactUpdateForward.do?first=yes").forward(request,response);
        }else  
        // ashrafi added request parameter first 86/09/13
        if (startupPage .equalsIgnoreCase( "Payment Order")){
          request.getRequestDispatcher("payOrderForward.do?first=yes").forward(request,response);
        }
        else
        if (startupPage .equalsIgnoreCase( "Alerts and Notifications")){
          request.getRequestDispatcher("alert.do").forward(request,response);
        }
      

      }
%>