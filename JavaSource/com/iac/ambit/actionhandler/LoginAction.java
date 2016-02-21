package com.iac.ambit.actionhandler;

import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.xml.rpc.holders.ObjectHolder;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import proxy.localhost.SBLService.axis.SpringWS.SpringWSSoapBindingStub;

import proxy.com.iac.ambit.model.Customer;

import com.iac.ambit.service.EncryptionService;
import com.iac.ambit.utils.SessionUtils;
import com.iac.ambit.utils.AmbitUtility;
import com.iac.ambit.utils.Config;
import com.iac.ambit.utils.Constants;
import com.iac.ambit.utils.Tracer;
import com.iac.ambit.utils.ErrorsUtility;
import com.iac.ambit.utils.EscapeInputUtility;
import org.apache.axis.utils.JavaUtils;

public class LoginAction extends Action {

	private SpringWSSoapBindingStub springWSSoapBindingStub;

	private EncryptionService encryptionService;

	public final Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	private final void writeObject(ObjectOutputStream out) throws IOException {
		throw new IOException("Object cannot be serialized");
	}

	private final void readObject(ObjectInputStream in) throws IOException {
		throw new IOException("Class cannot be Deserialized");
	}

	public SpringWSSoapBindingStub getSpringWSSoapBindingStub() {
		return springWSSoapBindingStub;
	}

	public void setSpringWSSoapBindingStub(
			SpringWSSoapBindingStub springWSSoapBindingStub) {
		this.springWSSoapBindingStub = springWSSoapBindingStub;
	}

	public EncryptionService getEncryptionService() {
		return encryptionService;
	}

	public void setEncryptionService(EncryptionService encryptionService) {
		this.encryptionService = encryptionService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String path = "success";
		String customerId = "";
		String customerPassword = "";

		
		String language = "";
		String country = "";
		String responseCode = "";

		boolean hasError = false;

		Customer customer = null;

		DynaValidatorForm loginForm = null;

		try {
			
			loginForm = (DynaValidatorForm) form;

			// Escape delimiter & single qoute
			EscapeInputUtility.escapeInput(loginForm);
			language = getLocale(request).getLanguage();
			country = getLocale(request).getCountry();
			customerId = (String) loginForm.get("customerId");
			customerPassword = (String) loginForm.get("customerPassword");
			String captchaInForm = (String) loginForm.get("captcha");
			String refererIP = request.getRemoteAddr();
			
			
			HttpSession session = SessionUtils.getNewSession(request);
			
			//sarmadiRad 1392/05/09
			if (!AmbitUtility.isAlphanumeric(customerId,50,6) || !AmbitUtility.isValidPasswordPolicy(customerPassword)) {
				ErrorsUtility.AddGlobalErrorToActionMessages(request, 
						"login.error.Invalid");
				hasError = true;
			}
			
			
			String captchaSession = (String) request.getSession().getAttribute(
					"customerCaptcha");
			if (captchaSession == null) {

				int timeOut = request.getSession().getMaxInactiveInterval();
				ErrorsUtility.AddGlobalErrorsToActionErrors(request,
						"session.expired", "" + (timeOut / 60));
				Tracer.traceOut(Tracer.Tracing_Level, "AdminLoginAction",
						"execute", " invalid Captcha userName :: "
								+ AmbitUtility.nvl(customerId)
								+ " ,captcha taken from screen :: " + captchaInForm
								+ " ,captcha taken from session ::"
								+ captchaSession + " refererIP : " + refererIP);
				path = "failure";

				return mapping.findForward(path);
			} else if (!AmbitUtility.isAlphanumeric(captchaInForm, 5,5)) {
				Tracer.traceOut(Tracer.Tracing_Level, "AdminLoginAction",
						"execute", " invalid Captcha userName :: "
								+ AmbitUtility.nvl(customerId)
								+ " ,captcha taken from screen :: " + captchaInForm
								+ " ,captcha taken from session ::"
								+ captchaSession + " refererIP : " + refererIP);
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"login.error.InvalidCaptcha");

				path = "failure";
				return mapping.findForward(path);
			} else {

				if (!AmbitUtility.nvl(captchaSession).equalsIgnoreCase(
						captchaInForm)) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"login.error.InvalidCaptcha");
					Tracer.traceOut(Tracer.Tracing_Level, "AdminLoginAction",
							"execute", " invalid Captcha userName :: "
									+ AmbitUtility.nvl(customerId)
									+ " ,captcha taken from screen :: "
									+ captchaInForm
									+ " ,captcha taken from session ::"
									+ captchaSession + " refererIP : " + refererIP);
					path = "failure";
					return mapping.findForward(path);
				} else {
					Tracer.traceOut(Tracer.Tracing_Level, "AdminLoginAction",
							"execute", "userName :: " + AmbitUtility.nvl(customerId)
									+ " ,captcha taken from screen :: "
									+ captchaInForm
									+ " ,captcha taken from session ::"
									+ captchaSession + " refererIP : " + refererIP);
					request.getSession().removeAttribute("customerCaptcha");
				}
			}
			//end
			if (hasError) {
				return mapping.findForward("failure");
			}

			if (SessionUtils.isCustomerLoggedIn(customerId)) {
				// check referesh button browser
				if (request.getSession().getAttribute(
						Constants.CUSTOMER_IN_SESSION) != null) {
					return mapping.findForward("success");
				} else {

					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"login.error.alreadLoggedIn");

					return mapping.findForward("failure");
				}
			}

			customerPassword = getEncryptionService().Encrypt(customerPassword);

			ObjectHolder obj = new ObjectHolder();
			responseCode = getSpringWSSoapBindingStub().customerAuthenticate(
					customerId, customerPassword, obj);

			if (!AmbitUtility.isSuccessResponseCode(request, responseCode,
					"login.error.Invalid")) {
				return mapping.findForward("failure");
			}
			customer = (Customer) JavaUtils.convert(obj, Customer.class);
			customer.setLanguage(language);
			customer.setCountry(country);

			 getSpringWSSoapBindingStub().logLoginActivity(
					customerId,Config.getPropertyFromBundle("LoginDescription",language,country));
			

			ObjectHolder sysPermissionIdList = new ObjectHolder();
			responseCode = getSpringWSSoapBindingStub()
					.getCustomerSysPermissionsIds(customerId,
							sysPermissionIdList);
			if (!AmbitUtility.isSuccessResponseCode(request, responseCode, "")) {

				return mapping.findForward("failure");
			}
			List menu = (List) JavaUtils.convert(sysPermissionIdList.value,
					List.class);
			if (menu.size() == 0) {
				ErrorsUtility.AddGlobalErrorToActionMessages(request,

				"customer.error.noPermissionAvailable");
				return mapping.findForward("failure");
			}

			session.setAttribute("MENU_LIST", menu);

			SessionUtils.logCustomer(customer, request.getSession());
			session.setAttribute(Constants.CUSTOMER_IN_SESSION, customer);
			responseCode = getSpringWSSoapBindingStub().addLoginInfo(
					customerId, true);
			Tracer.traceOut(Tracer.Tracing_Level.INFO,
					"LoginAction",
					"Method : addLoginInfo ", " User : "
							+ customer.getUserId() + " ResponseCode : "
							+ responseCode);
			if (!AmbitUtility.isSuccessResponseCode(request, responseCode, "")) {
				return mapping.findForward("failure");
			}

			String monitorIntervalInSec = Config
					.getProperty("MONITORING_INTERVAL_IN_SEC");
			String isAlert = Config.getProperty("IS_ALERT_ON");
			String alertTimeInSec = Config.getProperty("ALERT_TIME_IN_SEC");
			request.getSession().setAttribute("monitorIntervalInSec",
					monitorIntervalInSec);

			if (isAlert.equalsIgnoreCase(Constants.CHECKBOX_SELECTED)) {
				request.getSession().setAttribute("isAlert",
						"checked=" + isAlert);
			} else {
				request.getSession().setAttribute("isAlert", "");
			}
			request.getSession().setAttribute("alertTimeInSec", alertTimeInSec);
			
			String userName = Config.getPropertyFromBundle(
					"global.UserName", language, country);
			request.getSession().setAttribute("LABEL_PARAMETER",
					userName);
			request.getSession().setAttribute(
					"VALUE_PARAMETER",customer.getUserNameFA());
				 

		} catch (Exception e) {
			path = "failure";
			Tracer.exception("LoginAction", "execute",
					"Exception occured while Authenticating Customer : "
							+ customerId, e);

			loginForm.reset(mapping, request);
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"general.operationfailed");

		}
		return mapping.findForward(path);
	}

}
