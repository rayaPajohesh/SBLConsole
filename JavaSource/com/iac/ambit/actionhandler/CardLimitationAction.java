package com.iac.ambit.actionhandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.Vector;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.holders.ObjectHolder;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.struts.validator.DynaValidatorActionForm;

import com.iac.ambit.utils.EscapeInputUtility;
import com.iac.ambit.utils.MessagesUtility;
import com.iac.ambit.utils.AmbitUtility;
import com.iac.ambit.utils.Constants;
import com.iac.ambit.utils.ErrorsUtility;
import com.iac.ambit.utils.Tracer;
import com.iac.ambit.utils.Config;

import proxy.com.iac.ambit.model.BlackList;

import proxy.com.iac.ambit.model.Customer;

import proxy.localhost.SBLService.axis.SpringWS.SpringWSSoapBindingStub;

public class CardLimitationAction extends Action {

	public final Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	private final void writeObject(ObjectOutputStream out) throws IOException {
		throw new IOException("Object cannot be serialized");
	}

	private final void readObject(ObjectInputStream in) throws IOException {
		throw new IOException("Class cannot be Deserialized");
	}

	private SpringWSSoapBindingStub springWSSoapBindingStub;

	public SpringWSSoapBindingStub getSpringWSSoapBindingStub() {
		return springWSSoapBindingStub;
	}

	public void setSpringWSSoapBindingStub(
			SpringWSSoapBindingStub springWSSoapBindingStub) {
		this.springWSSoapBindingStub = springWSSoapBindingStub;
	}

	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String responseCode = "";
		String path = "success";
		String terminals = Constants.TERMINALS;
		String services = Constants.SERVICES;
		String chkShetabiTransaction = "";
		String chkATM = "";
		String chkPOS = "";
		String chkInternet = "";
		String chkTelephone = "";
		String chkMobile = "";
		String chkPinPad = "";
		String chkWithdraw = "";
		String chkFundTransfer = "";
		String chkBalance = "";
		String chkMiniStatement = "";
		String chkPurchase = "";
		String chkValidation = "";
		String chkChangePin = "";
		String chkChangePin2 = "";
		String chkBillPayment = "";
		String terminalType = "";
		String tarns = "";
		String cardNo = "";
		String method = "";
		String terminalNumber ="";

		try {

			DynaValidatorActionForm cardLimitationForm = (DynaValidatorActionForm) form;
			
			EscapeInputUtility.escapeInput(cardLimitationForm);
			BlackList blUpdate = new BlackList();

			method = cardLimitationForm.getString("method");
			cardNo = cardLimitationForm.getString("cardNo");
			
			
			
			
			if (isCancelled(request)) {

				request.setAttribute("returnPath", "cardLimitation");
				request.setAttribute("selectedPan", cardNo);
				return mapping.findForward("cancel");
			}
			
			if (method.equalsIgnoreCase("cancel")) {
				path = loadLimitation(request, terminals, services, cardNo);
				return mapping.findForward(path);

			}

			else {
				chkShetabiTransaction = cardLimitationForm
						.getString("chkShetabiTransaction");
				
				if (!AmbitUtility.isEmpty(chkShetabiTransaction)&&
						 (!AmbitUtility.isAlphanumeric(chkShetabiTransaction, 3,2))) {
							ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"errors.invalid.data");
							mapping.findForward("failure");
						}
				
				if (chkShetabiTransaction
						.equalsIgnoreCase(Constants.CHECKBOX_SELECTED)) {
					blUpdate
							.setAllowedShetab(Constants.CODE_ACTIVE_FLAG.ACTIVE);
				} 
				/*else {
					blUpdate
							.setAllowedShetab(Constants.CODE_ACTIVE_FLAG.INACTIVE);
				}*/

				chkATM = cardLimitationForm.getString("chkATM");
				chkPOS = cardLimitationForm.getString("chkPOS");
				chkInternet = cardLimitationForm.getString("chkInternet");
				chkTelephone = cardLimitationForm.getString("chkTelephone");
				chkMobile = cardLimitationForm.getString("chkMobile");
				chkPinPad = cardLimitationForm.getString("chkPinPad");
				
				
				// validation >>
				if (!AmbitUtility.isEmpty(chkATM)&&
				 (!AmbitUtility.isNumeric(chkATM, 3))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"errors.invalid.data");
					mapping.findForward("failure");
				}
				if (!AmbitUtility.isEmpty(chkPOS)&&
				 (!AmbitUtility.isNumeric(chkPOS, 3))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"errors.invalid.data");
					mapping.findForward("failure");
				}
				if (!AmbitUtility.isEmpty(chkInternet)&&
				(!AmbitUtility.isNumeric(chkInternet, 3))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"errors.invalid.data");
					mapping.findForward("failure");
				}
				if (!AmbitUtility.isEmpty(chkTelephone)&&
				 (!AmbitUtility.isNumeric(chkTelephone, 3))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"errors.invalid.data");
					mapping.findForward("failure");
				}
				if (!AmbitUtility.isEmpty(chkMobile)&&
				(!AmbitUtility.isNumeric(chkMobile, 3))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"errors.invalid.data");
					mapping.findForward("failure");
				}
				if (!AmbitUtility.isEmpty(chkPinPad)&&
				(!AmbitUtility.isNumeric(chkPinPad, 3))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"errors.invalid.data");
					mapping.findForward("failure");
				}
				
				// validation <<
				

				if (!StringUtils.isEmpty(chkATM)) {
					terminalType += chkATM + Constants.semicolonSign;
				}
				if (!StringUtils.isEmpty(chkPOS)) {
					terminalType += chkPOS + Constants.semicolonSign;
				}
				if (!StringUtils.isEmpty(chkInternet)) {
					terminalType += chkInternet + Constants.semicolonSign;
				}
				if (!StringUtils.isEmpty(chkTelephone)) {
					terminalType += chkTelephone + Constants.semicolonSign;
				}
				if (!StringUtils.isEmpty(chkMobile)) {
					terminalType += chkMobile + Constants.semicolonSign;
				}

				if (!StringUtils.isEmpty(chkPinPad)) {
					terminalType += chkPinPad + Constants.semicolonSign;
				}

				blUpdate.setAllowedTerminalTypes(terminalType);

				chkWithdraw = cardLimitationForm.getString("chkWithdraw");
				chkFundTransfer = cardLimitationForm
						.getString("chkFundTransfer");
				chkBalance = cardLimitationForm.getString("chkBalance");
				chkMiniStatement = cardLimitationForm
						.getString("chkMiniStatement");
				chkPurchase = cardLimitationForm.getString("chkPurchase");
				chkValidation = cardLimitationForm.getString("chkValidation");
				chkBillPayment = cardLimitationForm.getString("chkBillPayment");
				chkChangePin = cardLimitationForm.getString("chkChangePin");
				chkChangePin2 = cardLimitationForm.getString("chkChangePin2");
				
				// validation >>
				if (!AmbitUtility.isEmpty(chkWithdraw)&&
				 (!AmbitUtility.isNumeric(chkWithdraw, 5))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"errors.invalid.data");
					mapping.findForward("failure");
				}
				if (!AmbitUtility.isEmpty(chkFundTransfer)&&
				 (!AmbitUtility.isNumeric(chkFundTransfer, 5))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"errors.invalid.data");
					mapping.findForward("failure");
				}
				if (!AmbitUtility.isEmpty(chkBalance)&&
				(!AmbitUtility.isNumeric(chkBalance, 5))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"errors.invalid.data");
					mapping.findForward("failure");
				}
				if (!AmbitUtility.isEmpty(chkMiniStatement)&&
				(!AmbitUtility.isNumeric(chkMiniStatement, 5))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"errors.invalid.data");
					mapping.findForward("failure");
				}
				if (!AmbitUtility.isEmpty(chkPurchase)&&
				(!AmbitUtility.isNumeric(chkPurchase, 5))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"errors.invalid.data");
					mapping.findForward("failure");
				}
				if (!AmbitUtility.isEmpty(chkValidation)&&
				 (!AmbitUtility.isNumeric(chkValidation, 5))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"errors.invalid.data");
					mapping.findForward("failure");
				}
				if (!AmbitUtility.isEmpty(chkBillPayment)&&
				 (!AmbitUtility.isNumeric(chkBillPayment, 5))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"errors.invalid.data");
					mapping.findForward("failure");
				}
				if (!AmbitUtility.isEmpty(chkChangePin)&&
				(!AmbitUtility.isNumeric(chkChangePin, 5))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"errors.invalid.data");
					mapping.findForward("failure");
				}
				if (!AmbitUtility.isEmpty(chkChangePin2)&&
				(!AmbitUtility.isNumeric(chkChangePin2, 5))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"errors.invalid.data");
					mapping.findForward("failure");
				}
				
				// validation <<
				

				if (!StringUtils.isEmpty(chkWithdraw)) {
					tarns += chkWithdraw + Constants.semicolonSign;
				}
				if (!StringUtils.isEmpty(chkFundTransfer)) {
					tarns += Config.getProperty("fundtransferVerification") + Constants.semicolonSign 
					+ chkFundTransfer + Constants.semicolonSign
					+ Config.getProperty("fundtransferFromAccount") + Constants.semicolonSign 
					+ Config.getProperty("fundtransferToAccount") + Constants.semicolonSign ;
				}
				if (!StringUtils.isEmpty(chkBalance)) {
					tarns += chkBalance + Constants.semicolonSign;
				}
				if (!StringUtils.isEmpty(chkMiniStatement)) {
					tarns += chkMiniStatement + Constants.semicolonSign;
				}

				if (!StringUtils.isEmpty(chkPurchase)) {
					tarns += chkPurchase + Constants.semicolonSign;
				}
				if (!StringUtils.isEmpty(chkValidation)) {
					tarns += chkValidation + Constants.semicolonSign;
				}
				if (!StringUtils.isEmpty(chkBillPayment)) {
					tarns += Config.getProperty("utilityBillPaymentVerification") + Constants.semicolonSign 
					+ chkBillPayment + Constants.semicolonSign;
				}
				if (!StringUtils.isEmpty(chkChangePin)) {
					tarns += chkChangePin + Constants.semicolonSign;
				}
				if (!StringUtils.isEmpty(chkChangePin2)) {
					tarns += chkChangePin2 + Constants.semicolonSign;
				}
				
				terminalNumber =cardLimitationForm.getString(
				"terminalNumber").toString();
				
				
				if (!AmbitUtility.isEmpty(terminalNumber)&&
						(!AmbitUtility.isNumeric(terminalNumber, 10))) {
							ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"errors.invalid.data");
							mapping.findForward("failure");
						}
				
				if((StringUtils.isEmpty(terminalType) || StringUtils.isEmpty(tarns) || StringUtils.isEmpty(terminalNumber) ) &&
						!(StringUtils.isEmpty(terminalType) && StringUtils.isEmpty(tarns) && StringUtils.isEmpty(terminalNumber)))	
				{
				if(StringUtils.isEmpty(terminalType))
				{
					String messageErorr=  "cardLimitation.erorr.atleastTerminals";
					failureMessage(mapping , request , messageErorr);
				}
				
				if(StringUtils.isEmpty(tarns))
				{
					String messageErorr=  "cardLimitation.erorr.atleastService";
					failureMessage(mapping , request , messageErorr);
					
				}
				if(StringUtils.isEmpty(terminalNumber))
				{
					String messageErorr=  "cardLimitation.erorr.enterTerminalNumber";
					failureMessage(mapping , request , messageErorr);
				}
				path = loadLimitation(request, terminals, services, cardNo);
				
				
				return mapping.findForward("failure");
				}
				
				blUpdate.setAllowedTrans(tarns);
				blUpdate.setAllowedTerminals(cardLimitationForm.getString(
						"terminalNumber").toString());
				blUpdate.setComments(cardLimitationForm.getString("notes")
						.toString());
				cardNo = AmbitUtility.addTrailing(cardNo,19,"0");
				blUpdate.setPan(cardNo);
				
				

				responseCode = getSpringWSSoapBindingStub()
						.updateBlackListForLimitationCard(blUpdate);

				Customer customer = (Customer) request.getSession()
						.getAttribute(Constants.CUSTOMER_IN_SESSION);
				Tracer.traceOut(Tracer.Tracing_Level.INFO,
						"CardLimitationAction",
						"updateBlackListForLimitationCard", " User : "
								+ customer.getUserId() + " ResponseCode : "
								+
								responseCode);
				
				String language = getLocale(request).getLanguage();
				String country = getLocale(request).getCountry();
				String desc = Config.getPropertyFromBundle("ChangeLimitDescription",language,country);
				desc = AmbitUtility.addAttributes(desc,
						new Object[] { cardNo });
				
				getSpringWSSoapBindingStub().logUpdateBlackListForLimitationCardActivity(
						customer.getUserId(),desc);

				if (!AmbitUtility.isSuccessResponseCode(request, responseCode,
						"")) {
					String messageErorr=  "general.operationfailed";
					
					 failureMessage(mapping, request,messageErorr);
					 return mapping.findForward("failure");
				}

				path = loadLimitation(request, terminals, services, cardNo);
				if (path.equalsIgnoreCase("success")) {
					MessagesUtility
							.AddGlobalMessageToActionMessages(request,
									"global.Success",
									new String[] { "cardLimitation" });
				}

			}

		} catch (Exception e) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"general.operationfailed");
			Tracer
					.exception(
							"CardLimitationAction",
							"updateBlackListForLimitationCard",
							"Exception occured while updateBlackListForLimitationCard : ",
							e);

			path = "failure";

		}

		return mapping.findForward(path);

	}

	private void failureMessage(ActionMapping mapping, HttpServletRequest request , String message) {
		request.getSession().setAttribute("blackList", null);
		BlackList blEmpty = new BlackList();
		request.setAttribute("BLACKLIST", blEmpty);
		ErrorsUtility.AddGlobalErrorToActionMessages(request,
				message);
		
	}
	

	private String loadLimitation(HttpServletRequest request, String terminals,
			String services, String cardNo) throws RemoteException {
		String responseCode;
		String path;
		String allowedTerminalType;
		String allowedTrans;

		ObjectHolder obj = new ObjectHolder();
		responseCode = getSpringWSSoapBindingStub().searchPanInBlackList(
				cardNo, obj);

		Customer customer = (Customer) request.getSession().getAttribute(
				Constants.CUSTOMER_IN_SESSION);
		Tracer.traceOut(Tracer.Tracing_Level.INFO, "CardLimitationAction",
				"searchPanInBlackList", " User : " + customer.getUserId()
						+ " ResponseCode : " + responseCode);

		if (!AmbitUtility.isSuccessResponseCode(request, responseCode, "")) {
			request.getSession().setAttribute("blackList", null);
			BlackList blEmpty = new BlackList();
			request.setAttribute("BLACKLIST", blEmpty);
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"general.operationfailed");
			path = "failure";

		} else {
			BlackList bl = (BlackList) org.apache.axis.utils.JavaUtils.convert(
					obj.value, BlackList.class);
			allowedTerminalType = bl.getAllowedTerminalTypes();
			allowedTrans = bl.getAllowedTrans();

			extract(request, allowedTerminalType, terminals);
			extract(request, allowedTrans, services);

			request.setAttribute("BLACKLIST", bl);

			path = "success";

		}
		return path;
	}

	private void extract(HttpServletRequest request, String note,
			String titleConfig) {
		String data[] = null;
		String temp = "";

		int vectorPointer = 0;
		if (!StringUtils.isEmpty(note)) {

			data = ((String) note).split(";");

			for (int i = 0; i < data.length; i++) {

				temp = data[i].toString();
				Vector vt = (Vector) AmbitUtility
						.getNameAndIdConfig(titleConfig);
				vectorPointer = 0;
				AmbitUtility.ImdsInfo imdInf = null;
				boolean Valid = false;
				for (; vectorPointer < vt.size(); vectorPointer++) {
					imdInf = (AmbitUtility.ImdsInfo) vt
							.elementAt(vectorPointer);
					if (temp.equalsIgnoreCase(imdInf.getId())) {

						Valid = true;
						vectorPointer++;
						break;
					}
				}

				if (Valid == true) {
					String name = imdInf.getName();
					// request.setAttribute(name,Constants.CODE_ACTIVE_FLAG.ACTIVE);
					request.setAttribute(name, "checked=" + "" + imdInf.getId()
							+ "");

				}
			}

		}
	}

}
