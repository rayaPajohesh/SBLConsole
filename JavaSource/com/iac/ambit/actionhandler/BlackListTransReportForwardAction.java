package com.iac.ambit.actionhandler;

import java.io.IOException;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;


import com.iac.ambit.utils.AmbitUtility;
import com.iac.ambit.utils.Config;
import com.iac.ambit.utils.Constants;
import com.iac.ambit.utils.DateFarsi;
import com.iac.ambit.utils.DateUtils;
import com.iac.ambit.utils.ErrorsUtility;
import com.iac.ambit.utils.Tracer;

import proxy.com.iac.ambit.model.Customer;
import proxy.com.iac.ambit.model.TransactionLog;
import proxy.com.iac.ambit.model.holders.ArrayOf_tns2_TransactionLogHolder;
import proxy.localhost.SBLService.axis.SpringWS.SpringWSSoapBindingStub;

public class BlackListTransReportForwardAction extends Action {

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
		String latinFromDate = null;
		String latinToDate = null;
		String fromDateFarsi = null;
		String toDateFarsi = null;
		DateFarsi dateFarsi = new DateFarsi();

		try {

			request.getSession().setAttribute("TOTAL_PARAM", null);
			request.getSession().setAttribute("hours", Constants.hours);
			request.getSession().setAttribute("blackListTrans", null);
			request.setAttribute("fromDateFarsi" , null);
			request.setAttribute("toDateFarsi" ,null);
			String pan = (String)request.getAttribute("selectedPan");
			
			String menuParam = request.getParameter("menu")+"";
			
			if (!menuParam.equalsIgnoreCase("yes")) {
			
			latinFromDate = DateUtils
			.dateToString (DateUtils.getYesterDate(30));
			
			
			latinFromDate = dateFarsi.revformatDateExcel(latinFromDate).replaceAll("/","");
			latinToDate = DateUtils.getCurLDate();
			
			fromDateFarsi = dateFarsi.formatedLDateConv(latinFromDate);
			toDateFarsi = dateFarsi.formatedLDateConv(latinToDate);
			
			request.setAttribute("fromDateFarsi" , fromDateFarsi);
			request.setAttribute("toDateFarsi" , toDateFarsi);
			
			Customer customer = (Customer) request.getSession().getAttribute(
					Constants.CUSTOMER_IN_SESSION);
			String language = getLocale(request).getLanguage();
			String country = getLocale(request).getCountry();
			
			// add in 1391/06/12 >>
			ArrayOf_tns2_TransactionLogHolder transactionLog = new ArrayOf_tns2_TransactionLogHolder();
			responseCode = getSpringWSSoapBindingStub().searchBlackListTrans(
					latinFromDate, latinToDate, "", "", pan, "",
					"", transactionLog);
			
			
			Tracer.traceOut(Tracer.Tracing_Level.INFO,
					"BlackListTransReportForwardAction", " Method: execute", " User : "
							+ customer.getUserId() + " ResponseCode : "
							+ responseCode);
			
			String desc = Config.getPropertyFromBundle(
					"BlackListTransReportDescription", language, country);
			
			
			desc = AmbitUtility.addAttributes(desc, new Object[] {
					(String) fromDateFarsi, 
					toDateFarsi, "", "",
					pan, "", "" });
			
			getSpringWSSoapBindingStub().logBlackListTransReportActivity(
					customer.getUserId(), desc);

			if (!AmbitUtility.isSuccessResponseCode(request, responseCode, "")) {
				request.getSession().setAttribute("blackListTrans", null);
				return mapping.findForward("failure");
			}

			TransactionLog[] blackListTrans = (TransactionLog[]) org.apache.axis.utils.JavaUtils
					.convert(transactionLog.value, TransactionLog[].class);

			Locale locale = getLocale(request);
			MessageResources messages = getResources(request);
			Vector vtServices = (Vector) AmbitUtility.Services();
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

			}

			request.getSession().setAttribute("blackListTrans", blackListTrans);
			request.getSession().setAttribute("printList", blackListTrans);
			request.getSession().setAttribute("print",
					Constants.BLACK_LIST_TRANS);
			
			request.getSession().setAttribute("fromDate",
					fromDateFarsi);
			request.getSession().setAttribute("toDate",
					toDateFarsi);
			request.getSession().setAttribute("pan", pan);
			request.getSession().setAttribute("stan", "");
			request.getSession().setAttribute("rrn", "");

			request.getSession().setAttribute("fromTime",
					"");

			request.getSession().setAttribute("toTime",
					"");
			request.setAttribute("blackListTrans", blackListTrans);

			}
			//  <<
			
			
			
		} catch (Exception e) {
			path = "failure";
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"general.operationfailed");
			Tracer
					.exception(
							"BlackListTransReportForwardAction",
							" Black List Transactions Report ",
							"Exception occured while reporting black list transactions  : ",
							e);
		}

		return mapping.findForward(path);

	}

}
