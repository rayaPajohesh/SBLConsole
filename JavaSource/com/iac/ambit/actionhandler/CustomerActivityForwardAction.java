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
import com.iac.ambit.utils.Constants;
import com.iac.ambit.utils.AmbitUtility;
import com.iac.ambit.utils.ErrorsUtility;
import com.iac.ambit.utils.Tracer;

/**
 * 
 * @author Hashir Ahmed
 */
public class CustomerActivityForwardAction extends Action {
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
			ObjectHolder obj = new ObjectHolder();
			responseCode = getSpringWSSoapBindingStub().getAllActivity(
					 obj);
			Tracer.traceOut(Tracer.Tracing_Level.INFO,
					"CustomerActivityForwardAction", "Method : getAllPermissions ", " User : "
							+ customer.getUserId() + " ResponseCode : "
							+ responseCode);
			if (!AmbitUtility.isSuccessResponseCode(request, responseCode, "")) {
				return mapping.findForward(path);
			}
			Permissions[] allActivity = (Permissions[])  org.apache.axis.utils.JavaUtils.convert(
					obj.value, Permissions[].class);
			request.getSession().setAttribute("activityTypes",allActivity);
			responseCode = getSpringWSSoapBindingStub().getAllCustomers("", obj);
			Tracer.traceOut(Tracer.Tracing_Level.INFO,
					"CustomerActivityForwardAction", "Method : getAllCustomers ", " User : "
							+ customer.getUserId() + " ResponseCode : "
							+ responseCode);
			if (!AmbitUtility.isSuccessResponseCode(request, responseCode, "")) {
				return mapping.findForward(path);
			}
			Customer[] allUser = (Customer[])  org.apache.axis.utils.JavaUtils.convert(
					obj.value, Customer[].class);
			request.getSession().setAttribute("userList",allUser);
				request.getSession().removeAttribute("ACTIVITIES");
				
			

		} catch (Exception e) {
			Tracer.exception("CustomerActivityForwardAction", "CustomerActivityForward",
					"Exception occured while Search Customer Activity  : ", e);
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"general.operationfailed");

			

		}

		return mapping.findForward(path);

	}

}
