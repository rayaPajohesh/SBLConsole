package com.iac.ambit.actionhandler;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.iac.ambit.utils.AmbitUtility;
import com.iac.ambit.utils.Config;
import com.iac.ambit.utils.Constants;
import com.iac.ambit.utils.ErrorsUtility;
import com.iac.ambit.utils.Tracer;

import proxy.com.iac.ambit.model.BlackedReason;
import proxy.com.iac.ambit.model.CodeActiveFlag;
import proxy.com.iac.ambit.model.Customer;
import proxy.com.iac.ambit.model.holders.ArrayOf_tns2_BlackedReasonHolder;
import proxy.com.iac.ambit.model.holders.ArrayOf_tns2_CodeActiveFlagHolder;
import proxy.localhost.SBLService.axis.SpringWS.SpringWSSoapBindingStub;

public class BlackListManagementForwardAction extends Action {

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
		try {

			request.getSession().setAttribute("disabledPan", Boolean.FALSE);
			request.getSession().setAttribute("disabledNotes", Boolean.FALSE);
			request.getSession().setAttribute("disabledReason", Boolean.FALSE);
			request.getSession().setAttribute("method", "search");
			
			
			

			// remove data from sessions
			request.getSession().setAttribute("blackList", null);
			request.getSession().setAttribute("TOTAL_PARAM", null);
			request.getSession().setAttribute("latinFromDate", null);
			request.getSession().setAttribute("latinToDate", null);
			request.getSession().setAttribute("farsiFromDate", null);
			request.getSession().setAttribute("farsiToDate", null);
			request.getSession().setAttribute("fromDate", null);
			request.getSession().setAttribute("toDate", null);
			request.getSession().setAttribute("pan", null);
			request.getSession().setAttribute("printList", null);

			Customer customer = (Customer) request.getSession().getAttribute(
					Constants.CUSTOMER_IN_SESSION);
			String pagePermission = Config.getProperty("BlackListManagement");
			String allowedActions = AmbitUtility.getAllowedActions(
					pagePermission, customer.getAccess());

			request.getSession().setAttribute("allowedActions", allowedActions);
			
			//all activeFlag >>
			ArrayOf_tns2_CodeActiveFlagHolder holder = new ArrayOf_tns2_CodeActiveFlagHolder();
			responseCode = getSpringWSSoapBindingStub().getAllFlags(holder);
			Tracer.traceOut(Tracer.Tracing_Level.INFO, "BlackListManagementForwardAction",
					"getAllFlags", " User : " + customer.getUserId()
							+ " ResponseCode : " + responseCode);
			
			CodeActiveFlag[] activeFlags = (CodeActiveFlag[]) org.apache.axis.utils.JavaUtils
			.convert(holder.value, CodeActiveFlag[].class);
			
			request.getSession().setAttribute("activeFlags", activeFlags);
			//<<
			
			
			
			// all blackReasons >>
			ArrayOf_tns2_BlackedReasonHolder obj = new ArrayOf_tns2_BlackedReasonHolder();

			responseCode = getSpringWSSoapBindingStub().searchBlackReason(obj);
			Tracer.traceOut(Tracer.Tracing_Level.INFO, "BlackListManagementForwardAction",
					"searchBlackReason", " User : " + customer.getUserId()
							+ " ResponseCode : " + responseCode);
			BlackedReason[] blackReasons = (BlackedReason[]) org.apache.axis.utils.JavaUtils
					.convert(obj.value, BlackedReason[].class);

			request.getSession().setAttribute("blackReasons", blackReasons);

			// all blackReasons <<

			if (!AmbitUtility.isSuccessResponseCode(request, responseCode, "")) {

				return mapping.findForward(path);
			}

		} catch (Exception e) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"general.operationfailed");
			Tracer.exception("BlackListManagementForwardAction", "searchBlackReason",
					"Exception occured : ", e);

			

		}
		return mapping.findForward(path);

	}

}
