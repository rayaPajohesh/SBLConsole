package com.iac.ambit.actionhandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import proxy.com.iac.ambit.model.Customer;

import com.iac.ambit.utils.AmbitUtility;
import com.iac.ambit.utils.Constants;
import com.iac.ambit.utils.ErrorsUtility;
import com.iac.ambit.utils.MessagesUtility;

import com.iac.ambit.utils.Tracer;
import com.iac.ambit.utils.Config;

import proxy.localhost.SBLService.axis.SpringWS.SpringWSSoapBindingStub;

/**
 * 
 * @author Hashir Ahmed
 */
public class PermissionsManagementAction extends Action

{
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
		Customer customer = (Customer) request.getSession().getAttribute(
				Constants.CUSTOMER_IN_SESSION);
		try {
			DynaValidatorActionForm systemModuleForm = (DynaValidatorActionForm) form;

			String permissionIds = systemModuleForm.getString("permissionIds");
			String[] result = StringUtils.split(permissionIds,
					Constants.semicolonSign);

			responseCode = getSpringWSSoapBindingStub().activationPermission(
					result);
			Tracer.traceOut(Tracer.Tracing_Level.INFO,"PermissionManagementAction", "activationPermission",
					" User : " +  customer.getUserId()+" ResponseCode : " + responseCode);
			
			getSpringWSSoapBindingStub()
			.logPermissionActivationActivity(customer.getUserId(),Config.getProperty("PermissionActivationDescription"));
			if (!AmbitUtility.isSuccessResponseCode(request, responseCode,"")) {
				return mapping.findForward("failure");
			}
			MessagesUtility.AddGlobalMessageToActionMessages(request,
					"modules.update.Successfully");

			
			if (!AmbitUtility.isSuccessResponseCode(request, responseCode,"")) {
				return mapping.findForward("failure");
			}

		} catch (Exception e) {
			path = "failure";
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
			"general.operationfailed");
			Tracer.exception("PermissionManagementAction", "activationPermission",
					"Exception occured while activation Permission : ", e);

			
		}

		return mapping.findForward(path);

	}

}
