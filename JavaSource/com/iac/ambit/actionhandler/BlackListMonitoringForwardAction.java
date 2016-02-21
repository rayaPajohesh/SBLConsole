package com.iac.ambit.actionhandler;

import java.io.IOException;
import java.util.*;
import java.text.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.holders.ObjectHolder;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import com.iac.ambit.utils.AmbitUtility;
import com.iac.ambit.utils.Constants;
import com.iac.ambit.utils.Config;
import com.iac.ambit.utils.ErrorsUtility;
import com.iac.ambit.utils.Tracer;
import com.iac.ambit.utils.AmountDecoratot;

import proxy.com.iac.ambit.model.BlackList;
import proxy.com.iac.ambit.model.Customer;
import proxy.com.iac.ambit.model.TransactionLog;
import proxy.com.iac.ambit.model.holders.ArrayOf_tns2_TransactionLogHolder;
import proxy.localhost.SBLService.axis.SpringWS.SpringWSSoapBindingStub;
import com.iac.ambit.utils.DateFarsi;
import com.iac.ambit.utils.DateUtils;
import javax.xml.rpc.holders.StringHolder;

public class BlackListMonitoringForwardAction extends Action {

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
			throws IOException, ServletException {
		String path = "success";
		
		
		
		
		String responseCode = "";
		
		String param = "";
		String language = getLocale(request).getLanguage();
		String country = getLocale(request).getCountry();
		Customer customer = (Customer) request.getSession().getAttribute(
				Constants.CUSTOMER_IN_SESSION);

		String monitorIntervalInSec = (String) request.getSession()
				.getAttribute("monitorIntervalInSec");

		if (!AmbitUtility.isEmpty((String) request.getSession().getAttribute(
				"isAlert"))) {
			param = Config
					.getPropertyFromBundle("global.on", language, country);
		} else {
			param = Config.getPropertyFromBundle("global.off", language,
					country);
		}
		request.getSession().setAttribute("PARAM", param);

		request.getSession().setAttribute("LastLogId", null);
		request.getSession().setAttribute("monitorTrans", null);
		try {

			StringHolder maxLogId = new StringHolder();
			responseCode = getSpringWSSoapBindingStub().getMaxLogId(maxLogId);

			Tracer.traceOut(Tracer.Tracing_Level.INFO,
					"BlackListMonitoringForwardAction", "getMaxLogId",
					" User : " + customer.getUserId() + " ResponseCode : "
							+ responseCode);

			if (!AmbitUtility.isSuccessResponseCode(request, responseCode, "")) {

				return mapping.findForward("failure");
			}

			String desc = Config.getPropertyFromBundle(
					"GetTransForMonitoringDescription", language, country);

			String lastLogId = (String) org.apache.axis.utils.JavaUtils
					.convert(maxLogId.value, String.class);
			if (Integer.parseInt(lastLogId) > 0) {
				lastLogId = Integer.toString(Integer.parseInt(lastLogId) - 1);
			}

			ArrayOf_tns2_TransactionLogHolder transactionLog = new ArrayOf_tns2_TransactionLogHolder();
			responseCode = getSpringWSSoapBindingStub().getTransForMonitoring(
					lastLogId, transactionLog);
			Tracer.traceOut(Tracer.Tracing_Level.INFO,
					"BlackListMonitoringForwardAction ",
					"getTransForMonitoring", " User : " + customer.getUserId()
							+ " ResponseCode : " + responseCode);

			getSpringWSSoapBindingStub().logGetTransForMonitoringActivity(
					customer.getUserId(), desc);

			if ((!responseCode
					.equalsIgnoreCase(Constants.RESPONSE_CODE.RECORD_NOT_FOUND))
					&& (!AmbitUtility.isSuccessResponseCode(request,
							responseCode, ""))) {
				return mapping.findForward("failure");
			}

			TransactionLog[] mtResult = (TransactionLog[]) org.apache.axis.utils.JavaUtils
					.convert(transactionLog.value, TransactionLog[].class);
			if (mtResult.length > 0) {
				request.getSession().setAttribute("LastLogId",
						((TransactionLog) mtResult[0]).getLogId());
			} else {
				request.getSession().setAttribute("LastLogId", lastLogId);
			}
			DateFarsi dateFarsi = new DateFarsi();
			AmountDecoratot amountDecorator = new AmountDecoratot();
			Locale locale = getLocale(request);
			MessageResources messages = getResources(request);
			Vector vtServices = (Vector) AmbitUtility.Services();
			Vector vtResponseCodes = (Vector) AmbitUtility.ResponseCodes();
			for (int i = 0; i < mtResult.length; i++) {
				((TransactionLog) mtResult[i]).setTransactionDate(dateFarsi
						.formatFdate(dateFarsi
								.LDateConv(((TransactionLog) mtResult[i])
										.getTransactionDate())));

				((TransactionLog) mtResult[i]).setTransactionTime(dateFarsi
						.formatTime(((TransactionLog) mtResult[i])
								.getTransactionTime()));

				if ((((TransactionLog) mtResult[i]).getResponseCode())
						.equalsIgnoreCase(Constants.TRANSACTION_RESPONSE_CODE.TRANSACTIONALLOWED)) {
					((TransactionLog) mtResult[i])
							.setAppearanceStatus(Constants.NEWALLOWED);
				} else {
					((TransactionLog) mtResult[i])
							.setAppearanceStatus(Constants.NEW);
				}

				((TransactionLog) mtResult[i]).setAmount(amountDecorator
						.decorate(((TransactionLog) mtResult[i]).getAmount()));

				((TransactionLog) mtResult[i]).setTransactionCode(messages
						.getMessage(locale, "global."
								+ AmbitUtility.getDescOfCode(
										((TransactionLog) mtResult[i])
												.getTransactionCode(),
										vtServices)));

				((TransactionLog) mtResult[i]).setResponseCode(messages
						.getMessage(locale, "global."
								+ AmbitUtility.getDescOfCode(
										((TransactionLog) mtResult[i])
												.getResponseCode(),
										vtResponseCodes)));

				request.setAttribute("wasRecordFound", "true");

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
			Tracer.exception("BlackListMonitoringForwardAction ",
					" Black List Monitoring ",
					"Exception occured while monitor transactions  : ", e);
		}

		return mapping.findForward(path);

	}

}
