package com.iac.ambit.actionhandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iac.ambit.service.EncryptionService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import proxy.com.iac.ambit.model.Customer;
import proxy.localhost.SBLService.axis.SpringWS.SpringWSSoapBindingStub;

import com.iac.ambit.utils.AmbitUtility;
import com.iac.ambit.utils.Constants;
import com.iac.ambit.utils.ErrorsUtility;
import com.iac.ambit.utils.EscapeInputUtility;
import com.iac.ambit.utils.MessagesUtility;
import com.iac.ambit.utils.Tracer;
import com.iac.ambit.utils.Config;

public class ChangePasswordAction extends Action {

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

	private EncryptionService encryptionService;

	private SpringWSSoapBindingStub springWSSoapBindingStub;

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
			throws IOException, ServletException {
		String path = "success";
		String responseCode = "";
		try {
			
			String language = getLocale(request).getLanguage();
			String country = getLocale(request).getCountry();
			
			DynaValidatorForm chngForm = (DynaValidatorForm) form;
			
			 if (!EscapeInputUtility
						.isValideInput((String) chngForm.get( "oldPassword" )) ){
									ErrorsUtility.AddGlobalErrorsToActionErrors(request,
							"errors.invalid", Config.getPropertyFromBundle("changePassword.error.oldPassword",language,country));
					path = "failure";
					return mapping.findForward(path);
				}
			 if (!EscapeInputUtility
						.isValideInput((String) chngForm.get( "newPassword" )) ){
					ErrorsUtility.AddGlobalErrorsToActionErrors(request,
							"errors.invalid", Config.getPropertyFromBundle("changePassword.error.newPassword",language,country));
					path = "failure";
					return mapping.findForward(path);
				}
			 
			 if (!EscapeInputUtility
						.isValideInput((String) chngForm.get( "confirmPassword" )) ){
					ErrorsUtility.AddGlobalErrorsToActionErrors(request,
							"errors.invalid", Config.getPropertyFromBundle("changePassword.error.confirmPassword",language,country));
					path = "failure";
					return mapping.findForward(path);
				}
			 
			

			EscapeInputUtility.escapeInput(chngForm);

			Customer customer = (Customer) request.getSession().getAttribute(
					Constants.CUSTOMER_IN_SESSION);

			String oldPass = chngForm.getString("oldPassword");
			String newPass = chngForm.getString("newPassword");
			String confirmPass = chngForm.getString("confirmPassword");
			
		     if( !AmbitUtility.isValidPasswordPolicy(newPass) ){
		        	ErrorsUtility.AddGlobalErrorsToActionErrors( 
		                    request, "errors.invalid",  Config.getPropertyFromBundle("changePassword.error.newPassword",language,country));
		        		        return mapping.findForward( "failure" );	
		        }
				

			if (!AmbitUtility.isValidPassword(newPass)) {
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"changePassword.newPassword.notAlphaNumeric");
				return mapping.findForward("failure");
			}
			
		

			else if (oldPass.equals(newPass)) {
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"changePassword.error.notOldEqualNew");
				path = "failure";
				return mapping.findForward(path);
			} else if (!newPass.equals(confirmPass)) {
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"changePassword.error.confirmEqualNew");
				path = "failure";
				return mapping.findForward(path);

			}
			oldPass = getEncryptionService().Encrypt(oldPass);
			newPass = getEncryptionService().Encrypt(newPass);

			responseCode = getSpringWSSoapBindingStub().changePassword(
					customer.getUserId(), oldPass, newPass);
			Tracer.traceOut(Tracer.Tracing_Level.INFO, "ChangePasswordAction",
					"ChangePassword", " User : " + customer.getUserId()
							+ " ResponseCode : " + responseCode);
			 language = getLocale(request).getLanguage();
			 country = getLocale(request).getCountry();
			getSpringWSSoapBindingStub().logChangePasswordActivity(
					customer.getUserId(),Config.getPropertyFromBundle("ChangePasswordDescription",language,country));
			if (!AmbitUtility.isSuccessResponseCode(request, responseCode,
					"changePassword.IncorrectOldPassword")) {

				return mapping.findForward("failure");
			}

			MessagesUtility.AddGlobalMessageToActionMessages(request,
					"changePassword.passwordChanged");
			
			
		} catch (Exception e) {
			path = "failure";
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
			"general.operationfailed");
			Tracer.exception("ChangePasswordAction", "ChangePassword",
					"Exception occured while Change Password : ", e);


		}

		return mapping.findForward(path);

	}

}
