package com.iac.ambit.actionhandler;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorActionForm;
import org.apache.struts.validator.DynaValidatorForm;

import proxy.com.iac.ambit.model.Customer;
import proxy.com.iac.ambit.model.TransactionLog;
import proxy.com.iac.ambit.model.holders.ArrayOf_tns2_TransactionLogHolder;
import proxy.com.iac.ambit.model.holders.CardInfoHolder;
import java.util.Vector;

import com.iac.ambit.utils.AmbitUtility;
import com.iac.ambit.utils.Config;
import com.iac.ambit.utils.Constants;
import com.iac.ambit.utils.DateFarsi;
import com.iac.ambit.utils.DateUtils;
import com.iac.ambit.utils.ErrorsUtility;
import com.iac.ambit.utils.EscapeInputUtility;
import com.iac.ambit.utils.Tracer;
import proxy.com.iac.ambit.model.CardInfo;
import proxy.localhost.SBLService.axis.SpringWS.SpringWSSoapBindingStub;

public class BlackListTransReportAction extends LookupDispatchAction {

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

		String path = "success";

		String method = "";
		String parameter = request.getParameter("blackListTransReport");
		
		
		if (!AmbitUtility.isEmpty(request.getParameter("blackListTransReport"))
				&&(request.getParameter("blackListTransReport")).length() > Constants.MAX_SECURE_LEN_1) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"errors.invalid.data");
			return mapping.findForward("failure");
		}
		
		 parameter = (String) EscapeInputUtility.escapeInput(parameter);
		 
		try {
			method = getLookupMapName(request, parameter, mapping);
		} catch (Exception e) {
			method = "";
		}
		if (Constants.METHOD.SEARCH_PAN.equalsIgnoreCase(method))
			path = searchPan(mapping, form, request, response);
		else
			path = submit(mapping, form, request, response);
		return mapping.findForward(path);
	}

	public String searchPan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		Customer customer = (Customer) request.getSession().getAttribute(
				Constants.CUSTOMER_IN_SESSION);
		String language = getLocale(request).getLanguage();
		String country = getLocale(request).getCountry();
		String responseCode = "";
		String path = "searchPan";
		request.getSession().setAttribute("TOTAL_PARAM", "");

		try {

			DynaValidatorActionForm blackListForm = (DynaValidatorActionForm) form;
			
			EscapeInputUtility.escapeInput(blackListForm);

			String pan = blackListForm.getString("pan");
			request.setAttribute("selectedPan", pan);
			if (AmbitUtility.isEmpty(pan)) {
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"blackList.error.pan.Empty");
				request.getSession().setAttribute("blackListTrans", null);
				return "failure";

			}

			if (!AmbitUtility.isEmpty(pan)) {
				if (!((pan.substring(0, 6)).equalsIgnoreCase(Config
						.getProperty("ACQUIRER_BANK_IMD")))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"blackListTransReport.error.pan.invalid");
					request.getSession().setAttribute("blackListTrans", null);
					return "failure";
				}
				
				if (!AmbitUtility.isNumeric(pan, 19)) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"errors.invalid.data");
					return ("failure");
				}
			}
			
			
			
			pan = AmbitUtility.addTrailing(pan, 19, "0");
			String desc = Config.getPropertyFromBundle(
					"SearchPanInformationDescription", language, country);
			desc = AmbitUtility.addAttributes(desc, new Object[] { pan });
			CardInfoHolder cardInfoHolder = new CardInfoHolder();
			CardInfo cardInfo = new CardInfo();
			responseCode = getSpringWSSoapBindingStub().searchPanInformation(
					pan, cardInfoHolder);
			cardInfo = (CardInfo) org.apache.axis.utils.JavaUtils.convert(
					cardInfoHolder, CardInfo.class);
			Tracer.traceOut(Tracer.Tracing_Level.INFO,
					"BlackListTransReportAction", " Method : searchPan",
					" User : " + customer.getUserId() + " ResponseCode : "
							+ responseCode);
			getSpringWSSoapBindingStub().logSearchPanInformationActivity(
					customer.getUserId(), desc);
			if (!AmbitUtility.isSuccessResponseCode(request, responseCode, "")) {
				request.getSession().setAttribute("blackListTrans", null);
				return "failure";
			}

			String totalParam = "";

			totalParam =  AmbitUtility
							.nvl((String) cardInfo.getNameAndFamilyName())
					+ "  "
					
					+ AmbitUtility.nvl((String) cardInfo.getCardStatusDesc());

			request.getSession().setAttribute("TOTAL_PARAM", totalParam);

		} catch (Exception e) {

			path = "failure";
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"general.operationfailed");
			Tracer.exception("BlackListTransReportAction",
					" Method : searchPan",
					"Exception occured while search pan : ", e);
		}

		return (path);

	}

	public String submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String path = "success";
		String responseCode = "";
		String fromDateFarsi = null;
		String toDateFarsi = null;
		String tempFromDateFarsi = null;
		String tempToDateFarsi = null;
		String latinFromDate = null;
		String latinToDate = null;
		Date fromDate = new Date();
		Date toDate = new Date();
		String language = "";
		String country = "";
		String rrn = "";
		String stan = "";
		String pan = "";
		String fromTime = "";
		String toTime = "";

		Customer customer = (Customer) request.getSession().getAttribute(
				Constants.CUSTOMER_IN_SESSION);

		try {

			request.getSession().setAttribute("TOTAL_PARAM", null);
			language = getLocale(request).getLanguage();
			country = getLocale(request).getCountry();
			DynaValidatorForm blackListTransReportForm = (DynaValidatorForm) form;
			
			
			
			latinFromDate = blackListTransReportForm.getString("fromDate");
			latinToDate = blackListTransReportForm.getString("toDate");
			fromTime = blackListTransReportForm.getString("timeListFrom");
			toTime = blackListTransReportForm.getString("timeListTo");
			rrn = blackListTransReportForm.getString("RRN");
			stan = blackListTransReportForm.getString("stan");
			pan = blackListTransReportForm.getString("pan");

			EscapeInputUtility.escapeInput(blackListTransReportForm);
			
			if (!AmbitUtility.isEmpty(fromTime)) {
				if (!AmbitUtility.isNumeric(fromTime,2)) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"errors.invalid.data");
					return ("failure");
				}
			}
			
			
			if (!AmbitUtility.isEmpty(toTime)) {
				if (!AmbitUtility.isNumeric(toTime,2)) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"errors.invalid.data");
					return ("failure");
				}
			}
			
			if (!AmbitUtility.isEmpty(rrn)) {
				if (!AmbitUtility.isNumeric(rrn,12)) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"errors.invalid.data");
					return ("failure");
				}
			}
			
			if (!AmbitUtility.isEmpty(stan)) {
				if (!AmbitUtility.isNumeric(stan,6)) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"errors.invalid.data");
					return ("failure");
				}
			}
			
			
			DateFarsi dateFarsi = new DateFarsi();
			if (isCancelled(request)) {

				request.setAttribute("returnPath", "blackListTransReport");

				request.setAttribute("selectedPan", pan);
				return "cancel";
			}
			// check grid pagination
			String gridPagination = request.getParameter("gridPagination");
			
			if (!AmbitUtility.isEmpty(request.getParameter("gridPagination"))
					&&(request.getParameter("gridPagination")).length() > Constants.MAX_SECURE_LEN_1) {
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"errors.invalid.data");
				return ("failure");
			}
			
			gridPagination = (String) EscapeInputUtility.escapeInput(gridPagination);
			
			
			if (gridPagination != null
					&& request.getSession().getAttribute("blackListTrans") != null) {

				return path;
			}
			if (AmbitUtility.isEmpty(blackListTransReportForm.get("fromDate"))
					|| AmbitUtility.isEmpty(blackListTransReportForm
							.get("toDate"))) {
				if (AmbitUtility.isEmpty(blackListTransReportForm
						.get("fromDate"))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"blackListTransReport.error.fromdate.absent");
				}
				if (AmbitUtility
						.isEmpty(blackListTransReportForm.get("toDate"))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"blackListTransReport.error.todate.absent");
				}
				request.getSession().setAttribute("blackListTrans", null);
				request.setAttribute("selectedPan", pan);
				request.setAttribute("fromDateFarsi",
						blackListTransReportForm.get("fromDate"));
				request.setAttribute("toDateFarsi",
						blackListTransReportForm.get("toDate"));
				return "failure";
			}

			if (!dateFarsi
					.isValidRevFormattedFDate((String) blackListTransReportForm
							.get("fromDate"))
					|| !dateFarsi
							.isValidRevFormattedFDate((String) blackListTransReportForm
									.get("toDate"))) {

				if (!dateFarsi
						.isValidRevFormattedFDate((String) blackListTransReportForm
								.get("fromDate"))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"blackListTransReport.error.fromDate.invalid");
					request.setAttribute("fromDateFarsi",
							blackListTransReportForm.get("fromDate"));
					request.setAttribute("toDateFarsi",
							blackListTransReportForm.get("toDate"));
					

				}
				if (!dateFarsi
						.isValidRevFormattedFDate((String) blackListTransReportForm
								.get("toDate"))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"blackListTransReport.error.toDate.invalid");
					request.setAttribute("fromDateFarsi",
							blackListTransReportForm.get("fromDate"));
					request.setAttribute("toDateFarsi",
							blackListTransReportForm.get("toDate"));
					

				}
				request.getSession().setAttribute("blackListTrans", null);
				request.setAttribute("selectedPan", pan);
				request.setAttribute("fromDateFarsi",
						blackListTransReportForm.get("fromDate"));
				request.setAttribute("toDateFarsi",
						blackListTransReportForm.get("toDate"));
				
				return "failure";
			}

			else {
				fromDateFarsi = dateFarsi
						.unformatRevFormattedFdate((String) blackListTransReportForm
								.get("fromDate"));

				toDateFarsi = dateFarsi
						.unformatRevFormattedFdate((String) blackListTransReportForm
								.get("toDate"));

				fromDate = DateUtils.stringToDate(dateFarsi
						.formatFdate(fromDateFarsi));
				toDate = DateUtils.stringToDate(dateFarsi
						.formatFdate(toDateFarsi));
				blackListTransReportForm.set("fromDate", dateFarsi
						.formatFdate(fromDateFarsi));
				blackListTransReportForm.set("toDate", dateFarsi
						.formatFdate(toDateFarsi));

				if (fromDateFarsi.compareTo(toDateFarsi) > 0) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"blackListTransReport.fromDateMoreThan");
					request.getSession().setAttribute("blackListTrans", null);
					request.setAttribute("selectedPan", pan);
					return "failure";
				}

				// Check whether from date is less than an year or not
				if (!DateUtils.isLessThanYears(fromDate, toDate, 1)) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"blackListTransReport.isMoreThanOneYear");
					request.getSession().setAttribute("blackListTrans", null);
					request.setAttribute("selectedPan", pan);
					request.setAttribute("fromDateFarsi",
							blackListTransReportForm.get("fromDate"));
					request.setAttribute("toDateFarsi",
							blackListTransReportForm.get("toDate"));
					return "failure";

				}
			}

			// to check if toTime is less than fromTime
			if (!AmbitUtility.isEmpty(fromTime)
					&& !AmbitUtility.isEmpty(toTime)) {
				if (Integer.parseInt(fromTime) - Integer.parseInt(toTime) > 0) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"blackListTransReport.fromTimeMoreThan");
					request.getSession().setAttribute("blackListTrans", null);
					request.setAttribute("selectedPan", pan);
					request.setAttribute("fromDateFarsi",
							blackListTransReportForm.get("fromDate"));
					request.setAttribute("toDateFarsi",
							blackListTransReportForm.get("toDate"));
					return "failure";
				}
			}
			tempFromDateFarsi = dateFarsi.FDateConv(fromDateFarsi);
			latinFromDate = tempFromDateFarsi;
			tempToDateFarsi = dateFarsi.FDateConv(toDateFarsi);
			latinToDate = tempToDateFarsi;

			if (!AmbitUtility.isEmpty(pan)) {
				if (!((pan.substring(0, 6)).equalsIgnoreCase(Config
						.getProperty("ACQUIRER_BANK_IMD")))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"blackListTransReport.error.pan.invalid");
					request.getSession().setAttribute("blackListTrans", null);
					request.setAttribute("selectedPan", pan);
					request.setAttribute("fromDateFarsi",
							blackListTransReportForm.get("fromDate"));
					request.setAttribute("toDateFarsi",
							blackListTransReportForm.get("toDate"));
					return "failure";

				}
				pan = AmbitUtility.addTrailing(pan, 19, "0");
			}

			String desc = Config.getPropertyFromBundle(
					"BlackListTransReportDescription", language, country);
			desc = AmbitUtility.addAttributes(desc, new Object[] {
					(String) blackListTransReportForm.get("fromDate"),
					(String) blackListTransReportForm.get("toDate"), fromTime,
					toTime, pan, stan, rrn });
			ArrayOf_tns2_TransactionLogHolder transactionLog = new ArrayOf_tns2_TransactionLogHolder();
			responseCode = getSpringWSSoapBindingStub().searchBlackListTrans(
					latinFromDate, latinToDate, fromTime, toTime, pan, stan,
					rrn, transactionLog);
			Tracer.traceOut(Tracer.Tracing_Level.INFO,
					"BlackListTransReportAction", " Method: submit", " User : "
							+ customer.getUserId() + " ResponseCode : "
							+ responseCode);
			getSpringWSSoapBindingStub().logBlackListTransReportActivity(
					customer.getUserId(), desc);

			if (!AmbitUtility.isSuccessResponseCode(request, responseCode, "")) {
				request.getSession().setAttribute("blackListTrans", null);
				request.setAttribute("selectedPan", pan);
				request.setAttribute("fromDateFarsi",
						blackListTransReportForm.get("fromDate"));
				request.setAttribute("toDateFarsi",
						blackListTransReportForm.get("toDate"));
				return "failure";
			}

			TransactionLog[] blackListTrans = (TransactionLog[]) org.apache.axis.utils.JavaUtils
					.convert(transactionLog.value, TransactionLog[].class);

			Locale locale = getLocale(request);
			MessageResources messages = getResources(request);
			Vector vtServices = (Vector) AmbitUtility.Services();
			Vector vtResponseCodes = (Vector) AmbitUtility.ResponseCodes();
			for (int i = 0; i < blackListTrans.length; i++) {
				((TransactionLog) blackListTrans[i])
						.setTransactionDate(dateFarsi.formatFdate(dateFarsi
								.LDateConv(((TransactionLog) blackListTrans[i])
										.getTransactionDate())));

				((TransactionLog) blackListTrans[i])
						.setTransactionTime(dateFarsi
								.formatTime(((TransactionLog) blackListTrans[i])
										.getTransactionTime()));
				((TransactionLog) blackListTrans[i])
						.setTransactionCode(messages
								.getMessage(
										locale,
										"global."
												+ AmbitUtility
														.getDescOfCode(
																((TransactionLog) blackListTrans[i])
																		.getTransactionCode(),
																vtServices)));
				((TransactionLog) blackListTrans[i]).setResponseCode(messages
						.getMessage(locale, "global."
								+ AmbitUtility.getDescOfCode(
										((TransactionLog) blackListTrans[i])
												.getResponseCode(),
										vtResponseCodes)));

			}

			request.getSession().setAttribute("blackListTrans", blackListTrans);
			request.setAttribute("blackListTrans", blackListTrans);

			request.getSession().setAttribute("fromDate",
					blackListTransReportForm.get("fromDate"));
			request.getSession().setAttribute("toDate",
					blackListTransReportForm.get("toDate"));
			request.setAttribute("fromDateFarsi",
					blackListTransReportForm.get("fromDate"));
			request.setAttribute("toDateFarsi",
					blackListTransReportForm.get("toDate"));
			
			request.getSession().setAttribute("pan", pan);
			request.getSession().setAttribute("stan", stan);
			request.getSession().setAttribute("rrn", rrn);

			request.getSession().setAttribute("fromTime",
					blackListTransReportForm.get("timeListFrom"));

			request.getSession().setAttribute("toTime",
					blackListTransReportForm.get("timeListTo"));

			request.getSession().setAttribute("printList", blackListTrans);
			request.getSession().setAttribute("print",
					Constants.BLACK_LIST_TRANS);

			request.setAttribute("selectedPan", pan);

		} catch (Exception e) {
			path = "failure";
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"general.operationfailed");
			Tracer
					.exception(
							"BlackListTransReportAction",
							" Method: submit",
							"Exception occured while reporting  black list transactions  : ",
							e);
		}

		return path;
	}

	protected Map getKeyMethodMap() {
		Map map = new HashMap();

		map.put("blackList.searchPan", "searchPan");
		map.put("global.submitButton", "submit");

		return map;

	}
}
