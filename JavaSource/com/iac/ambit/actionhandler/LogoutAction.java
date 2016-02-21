package com.iac.ambit.actionhandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import proxy.com.iac.ambit.model.Customer;

import com.iac.ambit.utils.AmbitUtility;
import com.iac.ambit.utils.Constants;
import com.iac.ambit.utils.ErrorsUtility;
import com.iac.ambit.utils.MessagesUtility;
import com.iac.ambit.utils.SessionUtils;
import com.iac.ambit.utils.Tracer;
import com.iac.ambit.utils.Config;
import proxy.localhost.SBLService.axis.SpringWS.SpringWSSoapBindingStub;

/**
 * 
 * @author Hashir Ahmed
 */
public class LogoutAction extends Action {
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
			throws IOException, ServletException {
		String path = "success";
		Customer customer = null;
		String userId = "";
		String responseCode = "";

		try {
			if (request.getSession()
					.getAttribute(Constants.CUSTOMER_IN_SESSION) != null) {

				customer = (Customer) request.getSession().getAttribute(
						Constants.CUSTOMER_IN_SESSION);
				userId = ((Customer) request.getSession().getAttribute(
						Constants.CUSTOMER_IN_SESSION)).getUserId();

				String customerName = customer.getUserNameFA();

				// force session expiry
				request.getSession().invalidate();

				request.getSession().setAttribute(
						"org.apache.struts.action.LOCALE",
						new Locale(customer.getLanguage().toString(), customer
								.getCountry().toString()));

				if (customer != null) {
					SessionUtils.logOutCustomer(customer);

				}

				if (customer.getIsLogoutMsg() != null) {
					MessagesUtility.AddGlobalMessageToActionMessages(request,
							customer.getIsLogoutMsg());

				} else {
					MessagesUtility.AddGlobalMessageToActionMessages(request,
							"logout.bye", new String[] { " " + customerName });
				}

				// log activity
				String language = getLocale(request).getLanguage();
				String country = getLocale(request).getCountry();
				responseCode = getSpringWSSoapBindingStub().logLogoutActivity(
						userId,Config.getPropertyFromBundle("LogoutDescription",language,country));
				Tracer.traceOut(Tracer.Tracing_Level.INFO,
						"LogoutAction",
						"Method : logLogoutActivity ", " User : "
								+ customer.getUserId() + " ResponseCode : "
								+ responseCode);
				AmbitUtility.isSuccessResponseCode(request, responseCode, "");

			} else {
				int timeOut = request.getSession().getMaxInactiveInterval();
				ErrorsUtility.AddGlobalErrorsToActionErrors(request,
						"session.expired", "" + (timeOut / 60));

			}
		} catch (Exception e) {
			Tracer.traceOut(Tracer.Error_Level, "LogoutAction", "execute",
					"Exception occured while logout Customer : "
							+ customer.getUserId() + "  " + e.toString());
			SessionUtils.logOutCustomer(customer);
		}

		Tracer.traceOut(Tracer.Error_Level, "LogoutAction", "execute",
				"Exception occured while logout Customer : " + userId);

		request.setAttribute("isLogout", "yes");

		return mapping.findForward(path);

	}

}
