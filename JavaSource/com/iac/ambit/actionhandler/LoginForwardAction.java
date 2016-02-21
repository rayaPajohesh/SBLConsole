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

import com.iac.ambit.utils.Config;

import com.iac.ambit.utils.ErrorsUtility;
import com.iac.ambit.utils.SessionUtils;


public class LoginForwardAction extends Action {

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
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String path = "success";
		// Jazimagh 1390/07/10 check duplicate session Id in multiple TAB
		// Browser
		if (SessionUtils.existCustomerSessionId(request.getSession().getId())) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"login.error.duplicateSession");

			return mapping.findForward(path);
		}
		 Locale locale = (Locale) request.getSession().getAttribute("org.apache.struts.action.LOCALE");
		 String language = locale.getLanguage();
		
		 if(language.equalsIgnoreCase(Config.getProperty( "language.English" ))){
			 return mapping.findForward("locale");
		 }
		

		return mapping.findForward(path);

	}

}
