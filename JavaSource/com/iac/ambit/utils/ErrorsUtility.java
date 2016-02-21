package com.iac.ambit.utils;

/**
 * This Class contains utiltiy funtions for handling server side errors.
 * 
 * @author hmirza
 */

// import com.iac.ambit.manager.ResponseManager;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;



import javax.servlet.http.HttpServletRequest;

public class ErrorsUtility {
	private static final ErrorsUtility errorsUtility = new ErrorsUtility();
//	jazimagh : 1386/07/16 
	public final Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}	
	private final void writeObject(ObjectOutputStream out) throws IOException{
		throw new IOException("Object cannot be serialized");
	}	
	private final void readObject(ObjectInputStream in) throws IOException{
		throw new IOException ("Class cannot be Deserialized");
	}	
//	jazimagh : 1386/07/16 	
	private ErrorsUtility() {

	}

	public static ErrorsUtility getInstance() {
		return errorsUtility;

	}

	/**
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param name
	 *            of field to which error is associated
	 * @param key
	 *            to error message in applicationresources.properties
	 */
	public static void AddErrorToActionErrors(HttpServletRequest request,
			String fieldName, String msgResource) {
		ActionErrors aErrs = (ActionErrors) request
				.getAttribute(Globals.ERROR_KEY);

		if (aErrs == null) {
			aErrs = new ActionErrors();

		} else {

		}

		aErrs.add(fieldName, new ActionMessage(msgResource));
		request.setAttribute(Globals.ERROR_KEY, aErrs);
		request.getSession().setAttribute(Globals.ERROR_KEY, aErrs);
	}

	/**
	 * Get response key to be looked up in Resource Bundle for specified error
	 * code
	 * 
	 * @param errorCode
	 * @param request
	 */
	public static void AddGlobalMessageToActionMessages(
			HttpServletRequest request, Integer errorCode) {
		/*if (!errorCode.toString().equals("") && !errorCode.toString()
				.equals(RDVIntegrationChannel.ERR_Code_Success)) {
			ActionMessages aMessages = (ActionMessages) request
					.getAttribute(Globals.ERROR_KEY);

			if (aMessages == null) {
				aMessages = new org.apache.struts.action.ActionMessages();

			}

			GenericService genericService =null; //= (GenericService) ContextUtils
					//.getBean("genericService");
			String key = genericService.getResponse(errorCode);

			if ((key != null) && !key.equals("")) {
				aMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						key));

			} else {
				aMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"general.operationfailed"));

			}

			request.setAttribute(Globals.ERROR_KEY, aMessages);
		}*/
	}

	/**
	 * Get response key to be looked up in Resource Bundle for specified error
	 * code
	 * 
	 * @param errorCode
	 * @param request
	 */

	public static void AddGlobalMessageToActionMessages(
			HttpServletRequest request, StringBuffer errorCode) {
		/*if (!errorCode.toString().equals("") && !errorCode.toString()
				.equals(RDVIntegrationChannel.ERR_Code_Success)) {
			ActionMessages aMessages = (ActionMessages) request
					.getAttribute(Globals.ERROR_KEY);

			if (aMessages == null) {
				aMessages = new org.apache.struts.action.ActionMessages();

			}

			GenericService genericService = (GenericService) ContextUtils
					.getBean("genericService");
			String key = genericService.getResponse(errorCode);

			if ((key != null) && !key.equals("")) {
				aMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						key));

			} else {
				aMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"general.operationfailed"));

			}

			request.setAttribute(Globals.ERROR_KEY, aMessages);
		}*/

	}

	public static ActionErrors AddErrorToActionErrors_Return(
			HttpServletRequest request, String fieldName, String msgResource) {
		ActionErrors aErrs = (ActionErrors) request
				.getAttribute(Globals.ERROR_KEY);

		if (aErrs == null) {
			aErrs = new ActionErrors();

		} else {

		}

		aErrs.add(fieldName, new ActionMessage(msgResource));
		request.setAttribute(Globals.ERROR_KEY, aErrs);
		request.getSession().setAttribute(Globals.ERROR_KEY, aErrs);
		return aErrs;

	}

	public static ActionErrors ReturnActionErrors(HttpServletRequest request) {
		return (ActionErrors) request.getAttribute(Globals.ERROR_KEY);

	}

	/**
	 * 
	 * @param HTTPServlestRequest
	 * @param key
	 *            to error msg in ApplicationResource.properties
	 */
	public static void AddGlobalErrorToActionErrors(HttpServletRequest request,
			String msgResource) {
		
		
		ActionErrors aErrs = (ActionErrors) request.getAttribute(Globals.ERROR_KEY);

		if (aErrs == null) {
			aErrs = new org.apache.struts.action.ActionErrors();

		} else {

		}

		aErrs.add(Globals.ERROR_KEY, new ActionMessage(msgResource));
		request.setAttribute(Globals.ERROR_KEY, aErrs);
		request.getSession().setAttribute(Globals.ERROR_KEY, aErrs);

	}
	
	public static void AddGlobalErrorToActionMessages(HttpServletRequest request,
			String msgResource) {
		
		
		ActionMessages aErrs = (ActionMessages) request.getAttribute(Globals.ERROR_KEY);

		if (aErrs == null) {
			aErrs = new ActionMessages( );

		} else {

		}

		aErrs.add( Globals.ERROR_KEY, new ActionMessage(msgResource));
		request.setAttribute(Globals.ERROR_KEY, aErrs);
		request.getSession().setAttribute(Globals.ERROR_KEY, aErrs);

	}

	
	

	public static boolean hasErrors(HttpServletRequest request) {
		ActionErrors aErrs = (ActionErrors) request
				.getAttribute(Globals.ERROR_KEY);

		if ((aErrs == null) || aErrs.isEmpty()) {
			return false;

		} else {
			return true;

		}

	}

	/**
	 * Add a message to global messages with replacements as an array of String
	 * 
	 * @param HTTPServlestRequest
	 * @param key
	 *            to error msg in ApplicationResource.properties
	 */
	public static void AddGlobalErrorsToActionErrors(
			HttpServletRequest request, String msgResource, String value) {
		//jazimagh : 1386/09/21 change input type String[] to String for value
		ActionMessages aErrs = (ActionMessages) request
				.getAttribute(Globals.ERROR_KEY);

		if (aErrs == null) {
			aErrs = new org.apache.struts.action.ActionMessages();

		}

		aErrs.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(msgResource,
				value));
		request.setAttribute(Globals.ERROR_KEY, aErrs);
		request.getSession().setAttribute(Globals.ERROR_KEY, aErrs);

	}

}
