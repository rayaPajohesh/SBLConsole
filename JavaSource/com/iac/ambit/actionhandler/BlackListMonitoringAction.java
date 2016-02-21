package com.iac.ambit.actionhandler;


import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorActionForm;

import com.iac.ambit.utils.EscapeInputUtility;
import com.iac.ambit.utils.MessagesUtility;
import com.iac.ambit.utils.AmbitUtility;
import com.iac.ambit.utils.AmountDecoratot;
import com.iac.ambit.utils.Config;
import com.iac.ambit.utils.Constants;
import com.iac.ambit.utils.DateFarsi;

import com.iac.ambit.utils.ErrorsUtility;
import com.iac.ambit.utils.Tracer;
import proxy.com.iac.ambit.model.Customer;
import proxy.com.iac.ambit.model.TransactionLog;
import proxy.com.iac.ambit.model.holders.ArrayOf_tns2_TransactionLogHolder;
import proxy.localhost.SBLService.axis.SpringWS.SpringWSSoapBindingStub;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlackListMonitoringAction extends LookupDispatchAction {

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
		String path = "";
		String method = "";

		if (!AmbitUtility.isEmpty(request.getParameter("monitorTransactions"))
				&&(request.getParameter("monitorTransactions")).length() > Constants.MAX_SECURE_LEN_1) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"errors.invalid.data");
			return mapping.findForward("failure");		
			}
		
		String parameter =EscapeInputUtility.escapeInput( request.getParameter("monitorTransactions"));
		
		try {
			method = getLookupMapName(request, parameter, mapping);
		} catch (Exception e) {
			// grid navigation
			method = Constants.METHOD.REFRESH;
		}
		if (Constants.METHOD.CONFIRM_REFRESH_INTERVAL_IN_SEC
				.equalsIgnoreCase(method))
			path = confirmRefreshIntervalInSec(mapping, form, request, response);
		else if (Constants.METHOD.CONFIRM_ALERT_INFO.equalsIgnoreCase(method))
			path = confirmAlertInfo(mapping, form, request, response);
		else if (Constants.METHOD.REFRESH.equalsIgnoreCase(method))
			path = refresh(mapping, form, request, response);

		return mapping.findForward(path);
	}

	public String refresh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Customer customer = (Customer) request.getSession().getAttribute(
				Constants.CUSTOMER_IN_SESSION);

		String path = "refresh";
		String responseCode = "";
		

		TransactionLog[] monitorTrans = null;
		try {
		
			if (!AmbitUtility.isEmpty(request.getParameter("gridPagination"))
					&&(request.getParameter("gridPagination")).length() > Constants.MAX_SECURE_LEN_1) {
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"errors.invalid.data");
				path="failure";
				return path;
				}
			
			String gridPagination =EscapeInputUtility.escapeInput( request.getParameter("gridPagination"));
					
			if (gridPagination != null
					&& request.getSession().getAttribute("monitorTrans") != null) {

				return path;
			}

			String lastLogId = (String) request.getSession().getAttribute(
					"LastLogId");

			if (!AmbitUtility.isEmpty(request.getParameter("refreshType"))
					&&(request.getParameter("refreshType")).length() > Constants.MAX_SECURE_LEN_1) {
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"errors.invalid.data");
				path="failure";
				return path;
				}
			
			String refreshType =EscapeInputUtility.escapeInput( request.getParameter("refreshType"));
			
			ArrayOf_tns2_TransactionLogHolder transactionLog = new ArrayOf_tns2_TransactionLogHolder();
			int newListSize = 0;
		
			responseCode = getSpringWSSoapBindingStub().getTransForMonitoring(
					lastLogId, transactionLog);

			if (!AmbitUtility.nvl(refreshType).equalsIgnoreCase(
					Constants.AUTOMATIC)) {

				Tracer.traceOut(Tracer.Tracing_Level.INFO,
						"BlackListMonitoringAction ", " Method : refresh ",
						" User : " + customer.getUserId() + " ResponseCode : "
								+ responseCode);
		

				String language = getLocale(request).getLanguage();
				String country = getLocale(request).getCountry();
				String desc = Config.getPropertyFromBundle(
						"GetTransForMonitoringDescription", language, country);
				
				
				 getSpringWSSoapBindingStub()
				  .logGetTransForMonitoringActivity( customer.getUserId(),
				  desc);
				
			}
			if ((!responseCode
					.equalsIgnoreCase(Constants.RESPONSE_CODE.RECORD_NOT_FOUND))
					&& (!AmbitUtility.isSuccessResponseCode(request,
							responseCode, ""))) {
				return "failure";
			}

			monitorTrans = (TransactionLog[]) org.apache.axis.utils.JavaUtils
					.convert(transactionLog.value, TransactionLog[].class);

			newListSize = monitorTrans.length;

			if (newListSize > 0) {
				request.getSession().setAttribute("LastLogId",
						((TransactionLog) monitorTrans[0]).getLogId());
			}
			// }

			TransactionLog[] oldList = (TransactionLog[]) request.getSession()
					.getAttribute("monitorTrans");

			int oldListSize;

			int definedCountOfRecord = Integer.parseInt(Config
					.getProperty("definedCountOfRecord"));

			if (newListSize > definedCountOfRecord) {

				oldListSize = 0;
			} else {
				int remainCountOfRecord = Math.abs(newListSize
						- definedCountOfRecord);
				if (oldList.length < remainCountOfRecord) {
					oldListSize = oldList.length;
				} else {
					oldListSize = remainCountOfRecord;
				}

			}

			TransactionLog[] mtResult = new TransactionLog[newListSize
					+ oldListSize];

			DateFarsi dateFarsi = new DateFarsi();
			AmountDecoratot amountDecorator = new AmountDecoratot();
			Locale locale = getLocale(request);
			MessageResources messages = getResources(request);
			Vector vtServices = (Vector) AmbitUtility.Services();
			Vector vtResponseCodes = (Vector) AmbitUtility.ResponseCodes();
			for (int i = 0; i < newListSize; i++) {
				((TransactionLog) monitorTrans[i]).setTransactionDate(dateFarsi
						.formatFdate(dateFarsi
								.LDateConv(((TransactionLog) monitorTrans[i])
										.getTransactionDate())));

				((TransactionLog) monitorTrans[i]).setTransactionTime(dateFarsi
						.formatTime(((TransactionLog) monitorTrans[i])
								.getTransactionTime()));
				
				if ((((TransactionLog) monitorTrans[i]).getResponseCode())
						.equalsIgnoreCase(Constants.TRANSACTION_RESPONSE_CODE.TRANSACTIONALLOWED)) {
					((TransactionLog) monitorTrans[i])
							.setAppearanceStatus(Constants.NEWALLOWED);
				}else{
					((TransactionLog) monitorTrans[i])
					.setAppearanceStatus(Constants.NEW);	
				}

				((TransactionLog) monitorTrans[i]).setAmount(amountDecorator
						.decorate(((TransactionLog) monitorTrans[i])
								.getAmount()));

				((TransactionLog) monitorTrans[i]).setTransactionCode(messages
						.getMessage(locale, "global."
								+ AmbitUtility.getDescOfCode(
										((TransactionLog) monitorTrans[i])
												.getTransactionCode(),
										vtServices)));

				((TransactionLog) monitorTrans[i]).setResponseCode(messages
						.getMessage(locale, "global."
								+ AmbitUtility.getDescOfCode(
										((TransactionLog) monitorTrans[i])
												.getResponseCode(),
										vtResponseCodes)));

				mtResult[i] = monitorTrans[i];

				request.setAttribute("wasRecordFound", "true");
			}
			for (int j = 0; j < oldListSize; j++) {
				mtResult[newListSize + j] = oldList[j];
				((TransactionLog) mtResult[newListSize + j])
						.setAppearanceStatus(Constants.OLD);
			}

			request.getSession().setAttribute("monitorTrans", mtResult);
			request.setAttribute("monitorTrans", mtResult);


			request.getSession().setAttribute("printList", mtResult);
			request.getSession().setAttribute("print",
					Constants.BLACK_LIST_MONITORING);

		} catch (Exception e) {
			path = "failure";
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"general.operationfailed");
			Tracer.exception("BlackListMonitoringAction", " Method : refresh ",
					"Exception occured  while monitor transactions  :", e);
		}

		return (path);
	}

	public String confirmRefreshIntervalInSec(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String path = "confirmRefreshIntervalInSec";
		Customer customer = (Customer) request.getSession().getAttribute(
				Constants.CUSTOMER_IN_SESSION);
		
		String previousMonitorIntervalInSec;
		String lastMonitorIntervalInSec;
		try {

			DynaValidatorActionForm monitorTransForm = (DynaValidatorActionForm) form;
			
			EscapeInputUtility.escapeInput(monitorTransForm);

			previousMonitorIntervalInSec = (String) request.getSession()
					.getAttribute("monitorIntervalInSec");

			String refreshIntervalInSec = monitorTransForm
					.getString("refreshIntervalInSec");
			
			 if (!AmbitUtility.isNumeric(refreshIntervalInSec, 3)){
				 ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"errors.invalid.data");
					
				 return "failure";
				}
			
			
			if (!AmbitUtility.isEmpty(refreshIntervalInSec)) {

				/*if (Double.parseDouble(refreshIntervalInSec) == Double
						.parseDouble(previousMonitorIntervalInSec)) {
					ErrorsUtility
							.AddGlobalErrorToActionMessages(
									request,
									"monitorTransactions.error.monitorIntervalInSec.IsNotEqualPreviousMonitorIntervalInSec");
					return "failure";

				}*/

				if (Double.parseDouble(refreshIntervalInSec) < 5) {
					ErrorsUtility
							.AddGlobalErrorToActionMessages(request,
									"monitorTransactions.error.monitorIntervalInSec.IsNotLessThanfiveSeconds");
					return "failure";

				}

				if (Double.parseDouble(refreshIntervalInSec) > request
						.getSession().getMaxInactiveInterval()) {
					ErrorsUtility
							.AddGlobalErrorsToActionErrors(
									request,
									"monitorTransactions.error.monitorIntervalInSec.IsNotMoreThanSessionTimeout",
									Integer.toString(request.getSession()
											.getMaxInactiveInterval()));

					return "failure";

				}

				request.getSession().setAttribute("monitorIntervalInSec",
						refreshIntervalInSec);

				lastMonitorIntervalInSec = (String) request.getSession()
						.getAttribute("monitorIntervalInSec");
				String language = getLocale(request).getLanguage();
				String country = getLocale(request).getCountry();
				String desc = Config.getPropertyFromBundle(
						"ChangeRefreshIntervalInSecDescription", language,
						country);
				desc = AmbitUtility.addAttributes(desc,
						new Object[] { previousMonitorIntervalInSec,
								lastMonitorIntervalInSec });
				Tracer.traceOut(Tracer.Tracing_Level.INFO,
						"BlackListMonitoringAction ",
						" Method : confirmRefreshIntervalInSec ", " User : "
								+ customer.getUserId());
				getSpringWSSoapBindingStub()
						.logChangeRefreshIntervalInSecActivity(
								customer.getUserId(), desc);

			} else {
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"monitorTransactions.error.monitorIntervalInSec.Empty");
				return "failure";

			}

		} catch (Exception e) {
			path = "failure";
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"general.operationfailed");
			Tracer.exception("BlackListMonitoringAction",
					" Method : confirmRefreshIntervalInSec ",
					"Exception occured while set monitor Interval In Second :",
					e);
		}

		return (path);

	}

	public String confirmAlertInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String path = "confirmAlertInfo";
		String param = "";
		
		String previousAlertTimeInSec = "";
		String lastAlertTimeInSec = "";
		String previousIsAlertON;
		String lastIsAlertON;
		String isAlert;
		String desc = "";
		String soundOfTheAlertDesc = "";
		String alertTimeInSecDesc = "";
		Customer customer = (Customer) request.getSession().getAttribute(
				Constants.CUSTOMER_IN_SESSION);
		try {
			String language = getLocale(request).getLanguage();
			String country = getLocale(request).getCountry();
			DynaValidatorActionForm monitorTransForm = (DynaValidatorActionForm) form;
			
			EscapeInputUtility.escapeInput(monitorTransForm);

			previousAlertTimeInSec = (String) request.getSession()
					.getAttribute("alertTimeInSec");

			if (!AmbitUtility.isEmpty((String) request.getSession()
					.getAttribute("isAlert"))) {
				previousIsAlertON = Constants.CHECKBOX_SELECTED;
			} else {
				previousIsAlertON = Constants.CHECKBOX_NONE_SELECTED;
			}

			String alertTimeInSec = monitorTransForm
					.getString("alertTimeInSec");
			isAlert = monitorTransForm.getString("isAlertName");
			
			

			if (!AmbitUtility.isEmpty(alertTimeInSec)) {

				if (Double.parseDouble(alertTimeInSec) < 1) {
					ErrorsUtility
							.AddGlobalErrorToActionMessages(request,
									"monitorTransactions.error.alertTimeInSec.IsNotLessThanOneSeconds");
					return "failure";

				}
				
				 if (!AmbitUtility.isNumeric(alertTimeInSec, 3)){
					 ErrorsUtility.AddGlobalErrorToActionMessages(request,
								"errors.invalid.data");
						
					 return "failure";
					}
				 
				 if (!AmbitUtility.isEmpty(isAlert)) {	
				 if (((String) monitorTransForm.getString("isAlertName")).length() > Constants.MAX_SECURE_LEN_1) {
						ErrorsUtility.AddGlobalErrorToActionMessages(request,
								"errors.invalid.data");
						 return "failure";
				}
				 }
				
				if (Double.parseDouble(alertTimeInSec) > request.getSession()
						.getMaxInactiveInterval()) {
					ErrorsUtility
							.AddGlobalErrorsToActionErrors(
									request,
									"monitorTransactions.error.alertTimeInSec.IsNotMoreThanSessionTimeout",
									Integer.toString(request.getSession()
											.getMaxInactiveInterval()));

					return "failure";

				}

		/*		if (Double.parseDouble(alertTimeInSec) == Double
						.parseDouble(previousAlertTimeInSec)) {
					ErrorsUtility
							.AddGlobalErrorToActionMessages(request,
									"monitorTransactions.error.alertTimeInSec.IsNotEqualpreviousAlertTimeInSec");
					return "failure";

				}*/

				request.getSession().setAttribute("alertTimeInSec",
						alertTimeInSec);
				lastAlertTimeInSec = (String) request.getSession()
						.getAttribute("alertTimeInSec");

			}

			if (!AmbitUtility.isEmpty(isAlert)) {
				param = Config.getPropertyFromBundle("global.on", language,
						country);
				request.getSession().setAttribute("isAlert",
						"checked=" + isAlert);
				lastIsAlertON = Constants.CHECKBOX_SELECTED;

			} else {
				param = Config.getPropertyFromBundle("global.off", language,
						country);
				request.getSession().setAttribute("isAlert", "");
				lastIsAlertON = Constants.CHECKBOX_NONE_SELECTED;
			}
			request.getSession().setAttribute("PARAM", param);

			if ((!AmbitUtility.isEmpty(alertTimeInSec))
					|| (!previousIsAlertON.equalsIgnoreCase(lastIsAlertON))) {

				if (!previousIsAlertON.equalsIgnoreCase(lastIsAlertON)) {
					soundOfTheAlertDesc = Config.getPropertyFromBundle(
							"ChangeSoundOfTheAlertDescription", language,
							country);
					soundOfTheAlertDesc = AmbitUtility.addAttributes(
							soundOfTheAlertDesc, new Object[] {
									previousIsAlertON, lastIsAlertON });
				}
				if (!AmbitUtility.isEmpty(alertTimeInSec)) {

					alertTimeInSecDesc = Config.getPropertyFromBundle(
							"ChangeAlertTimeInSecDescription", language,
							country);
					alertTimeInSecDesc = AmbitUtility.addAttributes(
							alertTimeInSecDesc,
							new Object[] { previousAlertTimeInSec,
									lastAlertTimeInSec });
				}
				desc = soundOfTheAlertDesc + MessagesUtility.getSpace(1)
						+ alertTimeInSecDesc;
				Tracer.traceOut(Tracer.Tracing_Level.INFO,
						"BlackListMonitoringAction ",
						" Method : confirmAlertInfo ", " User : "
								+ customer.getUserId());

				getSpringWSSoapBindingStub().logChangeAlertInfoActivity(
						customer.getUserId(), desc);

			}

		} catch (Exception e) {
			path = "failure";
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"general.operationfailed");
			Tracer.exception("BlackListMonitoringAction",
					" Method : confirmAlertInfo ",
					"Exception occured while set confirm Alert Information :",
					e);
		}
		return (path);
	}

	protected Map getKeyMethodMap() {
		Map map = new HashMap();
		map.put("monitorTransactions.confirmRefreshIntervalInSec",
				"confirmRefreshIntervalInSec");
		map.put("monitorTransactions.confirmAlertInfo", "confirmAlertInfo");
		map.put("monitorTransactions.manuallyRefresh", "refresh");
		return map;

	}

}
