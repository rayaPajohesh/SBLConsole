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
import org.apache.struts.validator.DynaValidatorForm;

import proxy.com.iac.ambit.model.ActivityLog;
import proxy.com.iac.ambit.model.Customer;

import proxy.localhost.SBLService.axis.SpringWS.SpringWSSoapBindingStub;

import com.iac.ambit.utils.Constants;
import com.iac.ambit.utils.DateFarsi;
import com.iac.ambit.utils.AmbitUtility;

import com.iac.ambit.utils.ErrorsUtility;
import com.iac.ambit.utils.EscapeInputUtility;
import com.iac.ambit.utils.Tracer;
import com.iac.ambit.utils.Config;

/**
 * 
 * @author Hashir Ahmed
 */
public class CustomerActivitySearchAction extends Action {
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
		String latinFromDate="";
		String latinToDate="";
		try {
			Customer customer = (Customer) request.getSession().getAttribute(
					Constants.CUSTOMER_IN_SESSION);
									
			if (!AmbitUtility.isEmpty(request.getParameter("navigate"))
					&&(request.getParameter("navigate")).length() > Constants.MAX_SECURE_LEN_1) {
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"errors.invalid.data");
				return mapping.findForward("failure");
			}	
			
			String navigate = EscapeInputUtility.escapeInput(request.getParameter("navigate"));		
			
			if (!AmbitUtility.isEmpty(navigate)
					&& Constants.TRUE.equalsIgnoreCase(navigate)) {
				return mapping.findForward(path);
			}
			
			DynaValidatorForm searchForm = (DynaValidatorForm) form;
			EscapeInputUtility.escapeInput(searchForm);
			
			String fromDate = (String) searchForm.get("fromDate");
			String toDate = (String) searchForm.get("toDate");
			String activityType = (String) searchForm
					.get("selectedActivityTypeId");
			String userId = (String) searchForm.get("selectedUserId");
		
			if (AmbitUtility.isEmpty(userId)) {
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"customer.error.customerId.Empty");
				return mapping.findForward("failure");
			}
			else{				
					   if (userId.length() > Constants.MAX_SECURE_LEN_2) {
						ErrorsUtility.AddGlobalErrorToActionMessages(request,
								"errors.invalid.data");
						return mapping.findForward("failure");
				    	}
					   
					 if (!AmbitUtility.isAlphaNumeric(userId)) {
							ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"errors.invalid.data");
					    return mapping.findForward("failure");
	   				   
				     }
				}				
			
			  if (!AmbitUtility.isEmpty(activityType)) {				
					   if (activityType.length() > Constants.MAX_SECURE_LEN_1) {
						ErrorsUtility.AddGlobalErrorToActionMessages(request,
								"errors.invalid.data");
						return mapping.findForward("failure");
				    	}					   		
				}	

			DateFarsi dateFarsi = new DateFarsi();
			if (!AmbitUtility.isEmpty(fromDate)) {
				if (!dateFarsi.isValidRevFormattedFDate(fromDate)) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"global.error.fromDate.invalid");
					return mapping.findForward("failure");
				}
			}
			if (!AmbitUtility.isEmpty(toDate)) {
				if (!dateFarsi.isValidRevFormattedFDate(toDate)) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"global.error.toDate.invalid");
					return mapping.findForward("failure");
				}
			}
			if (!AmbitUtility.isEmpty(fromDate)
					&& !AmbitUtility.isEmpty(toDate)) {
				latinFromDate = dateFarsi.FDateConv(dateFarsi
						.unformatRevFormattedFdate(fromDate));
				latinToDate = dateFarsi.FDateConv(dateFarsi
						.unformatRevFormattedFdate(toDate));
				if (Integer.parseInt(latinFromDate) > Integer.parseInt(latinToDate)) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"global.error.date");
					return mapping.findForward("failure");
				}
			}
			ActivityLog activity = new ActivityLog();
			activity.setUserId(userId);
			activity.setActivityTypeId(activityType);
			activity.setActivityFromDate(latinFromDate);
			activity.setActivityToDate(latinToDate);

			ObjectHolder obj = new ObjectHolder();
			responseCode = getSpringWSSoapBindingStub().searchLogActivity(
					activity, obj);
			Tracer.traceOut(Tracer.Tracing_Level.INFO,
					"CustomerActivitySearchAction",
					"Method : searchLogActivity ", " User : "
							+ customer.getUserId() + " ResponseCode : "
							+ responseCode);
			
			String language = getLocale(request).getLanguage();
			String country = getLocale(request).getCountry();
			String desc = Config.getPropertyFromBundle("UserActivityDescription",language,country);
			desc = AmbitUtility.addAttributes(desc,
					new Object[] { fromDate,toDate});
			getSpringWSSoapBindingStub().logUserActivity(customer.getUserId(),
					desc);
			ActivityLog[] ACTIVITIES = (ActivityLog[]) org.apache.axis.utils.JavaUtils
					.convert(obj.value, ActivityLog[].class);

			request.getSession().setAttribute("ACTIVITIES", ACTIVITIES);
			if (!AmbitUtility.isSuccessResponseCode(request, responseCode, "")) {
				return mapping.findForward("failure");

			}

		} catch (Exception e) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"general.operationfailed");
			Tracer.exception("CustomerActivitySearchAction",
					"Method: CustomerActivitySearch",
					"Exception occured while Search CustomerActivity : ", e);

			path = "failure";

		}

		return mapping.findForward(path);

	}

}
