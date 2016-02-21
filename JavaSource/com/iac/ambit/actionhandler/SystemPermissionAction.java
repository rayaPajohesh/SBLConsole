package com.iac.ambit.actionhandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.holders.ObjectHolder;


import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import proxy.com.iac.ambit.model.Customer;
import proxy.com.iac.ambit.model.Permissions;
import proxy.localhost.SBLService.axis.SpringWS.SpringWSSoapBindingStub;

import com.iac.ambit.utils.AmbitUtility;
import com.iac.ambit.utils.ErrorsUtility;
import com.iac.ambit.utils.Tracer;
import com.iac.ambit.utils.Constants;

/**
 * 
 * @author Hashir Ahmed
 */
public class SystemPermissionAction extends Action {
	private SpringWSSoapBindingStub springWSSoapBindingStub;

	// jazimagh : 1386/07/16
	public final Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	private final void writeObject(ObjectOutputStream out) throws IOException {
		throw new IOException("Object cannot be serialized");
	}

	private final void readObject(ObjectInputStream in) throws IOException {
		throw new IOException("Class cannot be Deserialized");
	}

	// jazimagh : 1386/07/16

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
		String responseCode = "";
		try {
			Customer customer = (Customer) request.getSession().getAttribute(
					Constants.CUSTOMER_IN_SESSION);
			if (request.getParameter("submitKey") != null
					&& ((String) request.getParameter("submitKey"))
							.equalsIgnoreCase("first")) {
				ObjectHolder obj = new ObjectHolder();
				responseCode = getSpringWSSoapBindingStub().getAllPermissions(
						"", obj);
				Tracer.traceOut(Tracer.Tracing_Level.INFO,"SystemPermissionAction", "getAllPermissions",
						" User : " +  customer.getUserId()+" ResponseCode : " + responseCode);
				//this method is exception for loging
				//getSpringWSSoapBindingStub().logPermissionActivationActivity(customer.getUserId());
				if (!AmbitUtility.isSuccessResponseCode(request, responseCode,
						"")) {
					return mapping.findForward("failure");
				}
				Permissions[] permissions = (Permissions[]) org.apache.axis.utils.JavaUtils
						.convert(obj.value, Permissions[].class);
				request.getSession().setAttribute("Active",
						Constants.CODE_ACTIVE_FLAG.ACTIVE);
				request.getSession().setAttribute("InActive",
						Constants.CODE_ACTIVE_FLAG.INACTIVE);
				request.getSession().setAttribute("permissions", permissions);
			}
		} catch (Exception e) {
			path = "failure";
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
			"general.operationfailed");
			Tracer.exception("SystemPermissionAction", "getAllPermissions",
					"Exception occured while get All Permissions : ", e);
		}
		return mapping.findForward(path);

	}

}
