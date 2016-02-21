package com.iac.ambit.actionhandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.axis.utils.JavaUtils;
import javax.xml.rpc.holders.ObjectHolder;



import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.validator.DynaValidatorForm;

import com.iac.ambit.utils.EscapeInputUtility;
import com.iac.ambit.service.EncryptionService;
import com.iac.ambit.utils.MessagesUtility;
import com.iac.ambit.utils.AmbitUtility;
import com.iac.ambit.utils.Constants;
import com.iac.ambit.utils.ErrorsUtility;
import com.iac.ambit.utils.Tracer;
import com.iac.ambit.utils.Config;
import proxy.com.iac.ambit.model.Customer;

//import proxy.com.iac.ambit.model.UserSubnetwork;
import proxy.com.iac.ambit.model.Group;
import proxy.localhost.SBLService.axis.SpringWS.SpringWSSoapBindingStub;

public class CustomerManagementAction extends DispatchAction {
	private SpringWSSoapBindingStub springWSSoapBindingStub;

	private EncryptionService encryptionService;

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

	/*
	 * Author : jazimagh 1386/07/16 (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */

	public final Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	private final void writeObject(ObjectOutputStream out) throws IOException {
		throw new IOException("Object cannot be serialized");
	}

	private final void readObject(ObjectInputStream in) throws IOException {
		throw new IOException("Class cannot be Deserialized");
	}

	private String getAllCustomer(HttpServletRequest request) throws Exception {

		Customer customer = (Customer) request.getSession().getAttribute(
				Constants.CUSTOMER_IN_SESSION);

		ObjectHolder obj = new ObjectHolder();
		String responseCode = getSpringWSSoapBindingStub().getAllCustomers("",
				obj);
		Tracer.traceOut(Tracer.Tracing_Level.INFO, "CustomerManagementAction",
				"Method : getAllCustomer", " User : " + customer.getUserId()
						+ " ResponseCode : " + responseCode);
		
		request.getSession().setAttribute("Active",
				Constants.CODE_ACTIVE_FLAG.ACTIVE);
		request.getSession().setAttribute("InActive",
				Constants.CODE_ACTIVE_FLAG.INACTIVE);
		Customer[] user = (Customer[]) JavaUtils.convert(obj.value,
				Customer[].class);
		request.getSession().setAttribute("users", user);
		return responseCode;
	}

	private String getAllGroups(HttpServletRequest request) throws Exception {
		Customer customer = (Customer) request.getSession().getAttribute(
				Constants.CUSTOMER_IN_SESSION);
		ObjectHolder obj = new ObjectHolder();
		String responseCode = getSpringWSSoapBindingStub().getAllGroups(
				Constants.CODE_ACTIVE_FLAG.ACTIVE, obj);
		Tracer.traceOut(Tracer.Tracing_Level.INFO, "CustomerManagementAction",
				"Method : getAllGroups", "User :" + customer.getUserId()
						+ " ResponseCode : " + responseCode);

		request.getSession().setAttribute("Active",
				Constants.CODE_ACTIVE_FLAG.ACTIVE);
		request.getSession().setAttribute("InActive",
				Constants.CODE_ACTIVE_FLAG.INACTIVE);
		Group[] group = (Group[]) org.apache.axis.utils.JavaUtils.convert(
				obj.value, Group[].class);
		request.getSession().setAttribute("groups", group);
		return responseCode;

	}

	/*
	 * private boolean isValidInputs(HttpServletRequest request, Customer user,
	 * UserSubnetwork userSubnetwork, Group[] groups, String confirmPassword) {
	 */
	private boolean isValidInputs(HttpServletRequest request, Customer user,
			Group[] groups, String confirmPassword,String method) {
		boolean result = true;
	 
		String language = getLocale(request).getLanguage();
		String country = getLocale(request).getCountry();

		 if (!AmbitUtility.isAlphanumeric(user.getUserId(), 50, 6)) {
			ErrorsUtility.AddGlobalErrorsToActionErrors(request,
					"errors.invalid",  "" + Config.getPropertyFromBundle("customer.title",language,country));
			result = false;
		}

	  if (!AmbitUtility.isAlphaFarsiNumeric(user.getUserNameFA(),50,6)) {
			ErrorsUtility.AddGlobalErrorsToActionErrors(request,
					"errors.invalid", "" + Config.getPropertyFromBundle("customer.name",language,country));		
			
			result = false;
		}
		
	  if(Constants.METHOD.ADD.equalsIgnoreCase(method)|| !AmbitUtility.isEmpty(user.getUserPassword()) || !AmbitUtility.isEmpty(confirmPassword)){
          if (!AmbitUtility.isValidPasswordPolicy(user.getUserPassword())) {
			ErrorsUtility.AddGlobalErrorsToActionErrors(request,
					"errors.invalid", "" + Config.getPropertyFromBundle("customer.password",language,country));		
			result = false;
		}

			if (!user.getUserPassword()
							.equalsIgnoreCase(confirmPassword)) {
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"customer.error.confirmEqualPassword");
				result = false;
			}
	  }

		if (groups == null) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"customer.error.groupNotSelected");
			result = false;
		}

		return result;
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String responseCode = "";
		Customer customer = null;
		Customer user = null;
		Group[] groups = null;
		 
		// UserSubnetwork userSubnetwork = null;
		
		try {
			String language = getLocale(request).getLanguage();
			String country = getLocale(request).getCountry();
			
			DynaValidatorForm customerForm = (DynaValidatorForm) form;

			if (!AmbitUtility.isEmpty(request.getParameter("submitKey"))
					&&(request.getParameter("submitKey")).length() > Constants.MAX_SECURE_LEN_1) {
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"errors.invalid.data");
				return mapping.findForward("add");
			}					
			String submitKey = EscapeInputUtility.escapeInput(request.getParameter("submitKey"));
		
			customer = (Customer) request.getSession().getAttribute(
					Constants.CUSTOMER_IN_SESSION);
			
			if (Constants.TRUE.equalsIgnoreCase(submitKey)) {


				if (!EscapeInputUtility.isValideInput((String) customerForm.get("userPassword"))) {
					ErrorsUtility.AddGlobalErrorsToActionErrors(request,
							"errors.invalid",  "" + Config.getPropertyFromBundle("customer.password",language,country));
					return mapping.findForward("add");
				}
			
				if (!EscapeInputUtility.isValideInput((String) customerForm.get("confirmPassword"))) {
					ErrorsUtility.AddGlobalErrorsToActionErrors(request,
							"errors.invalid",  "" + Config.getPropertyFromBundle("customer.confirmPassword",language,country));
					return mapping.findForward("add");
				}

			EscapeInputUtility.escapeInput(customerForm);
			String userId = (String) customerForm.get("userId");
			String userName = (String) customerForm.get("userName");
			String userPassword = (String) customerForm.get("userPassword");
			String confirmPassword = (String) customerForm
					.get("confirmPassword");

			String status = (String) customerForm.get("status");


			if (((String) customerForm.get("status")).length() != 0) {
				if (((String) customerForm.get("status")).length() > Constants.MAX_SECURE_LEN_1) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"errors.invalid.data");
					return mapping.findForward("add");
				     }
				}
		
			String groupIds[] = request.getParameterValues("checkbox");

			userId=userId.trim();
		
			userName=AmbitUtility.charVal(userName);						
				if (groupIds != null) {
					Group[] allGroup = (Group[])request.getSession().getAttribute("groups");
					if(allGroup == null){
						ErrorsUtility.AddGlobalErrorToActionMessages(request,
								"group.error.notExistGroupInformation");
						return mapping.findForward("add");
						
					}
					groups = new Group[groupIds.length];
					for (int i = 0; i < groupIds.length; i++) {
																	
						if (groupIds[i].length() > Constants.MAX_SECURE_LEN_2) {
							ErrorsUtility.AddGlobalErrorToActionMessages(request,
									"errors.invalid.data");
							return mapping.findForward("add");
						     }
						//find selected group id in array allGroup
						for(int j=0;j<allGroup.length;j++){
							if(allGroup[j].getGroupId().equalsIgnoreCase(groupIds[i]))
								groups[i] = allGroup[j];
						}
					}

				}

				user = new Customer();

				if (Constants.CHECKBOX_SELECTED.equalsIgnoreCase(status))
					user.setUserActive(Constants.CODE_ACTIVE_FLAG.INACTIVE);
				else
					user.setUserActive(Constants.CODE_ACTIVE_FLAG.ACTIVE);
			
				user.setUserId(userId);
				user.setUserName("");
				user.setUserNameFA(userName);
				user.setUserPassword(userPassword);

		
				
				if ( !isValidInputs(request, user, groups, confirmPassword,Constants.METHOD.ADD)) {

					return mapping.findForward("add");
				}
				userPassword = getEncryptionService().Encrypt(userPassword);
				user.setUserPassword(userPassword);
				responseCode = getSpringWSSoapBindingStub().addUser(user,
						groups);
				Tracer.traceOut(Tracer.Tracing_Level.INFO,
						"GroupManagementAction", " Method : Add", " User : "
								+ customer.getUserId() + " ResponseCode : "
								+ responseCode);
		
				String desc = Config.getPropertyFromBundle("AddUserDescription",language,country);
				desc = AmbitUtility
						.addAttributes(desc, new Object[] { userId });
				getSpringWSSoapBindingStub().logAddUserActivity(
						customer.getUserId(), desc);
				if (!AmbitUtility.isSuccessResponseCode(request, responseCode,
						"")) {
					return mapping.findForward("add");
				}
				MessagesUtility.AddGlobalMessageToActionMessages(request,
						"customer.info.addCustomer.Successfully");

			} else if (Constants.FALSE.equalsIgnoreCase(submitKey)) {
				
				if (!AmbitUtility.isSuccessResponseCode(request, responseCode,
						"")) {
					return mapping.findForward("add");
				}
			} else {
				// load first page
				request.getSession().removeAttribute("user");
				request.getSession().removeAttribute("userSubnetwork");
				request.getSession().removeAttribute("users");
				responseCode = getAllGroups(request);
				if (!AmbitUtility.isSuccessResponseCode(request, responseCode,
						"")) {
					return mapping.findForward("add");
				}

			}

		} catch (Exception e) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"general.operationfailed");
			Tracer.exception("CustomerManagementAction", "Method :Add",
					"Exception occured while Define New Customer : ", e);

		}
		return mapping.findForward("add");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		String language = getLocale(request).getLanguage();
		String country = getLocale(request).getCountry();
		
		if (!AmbitUtility.isEmpty(request.getParameter("submitKey"))
				&&(request.getParameter("submitKey")).length() > Constants.MAX_SECURE_LEN_1) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"errors.invalid.data");
			return mapping.findForward("edit");
		}					
		String submitKey = EscapeInputUtility.escapeInput(request.getParameter("submitKey"));
		
		
		String responseCode = "";

		// UserSubnetwork userSubnetwork = null;
		Group[] groups = null;
		Customer user;
		try {
			DynaValidatorForm customerForm = (DynaValidatorForm) form;
			
				
			String selectedUserId = (String) customerForm.get("selectedUserId");			
			
			if (!AmbitUtility.isEmpty(selectedUserId)) {
				   if (selectedUserId.length() > Constants.MAX_SECURE_LEN_2) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"errors.invalid.data");
					return mapping.findForward("edit");
			    	}
				   
				 if (!AmbitUtility.isAlphaNumeric(selectedUserId)) {
						ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"errors.invalid.data");
				    return mapping.findForward("edit");
   				   
			     }
			}
			if (Constants.TRUE.equalsIgnoreCase(submitKey)) {

				
				if (!EscapeInputUtility.isValideInput((String) customerForm.get("userPassword"))) {
					ErrorsUtility.AddGlobalErrorsToActionErrors(request,
							"errors.invalid",  "" + Config.getPropertyFromBundle("customer.password",language,country));
					return mapping.findForward("edit");
				}
			
				if (!EscapeInputUtility.isValideInput((String) customerForm.get("confirmPassword"))) {
					ErrorsUtility.AddGlobalErrorsToActionErrors(request,
							"errors.invalid",  "" + Config.getPropertyFromBundle("customer.confirmPassword",language,country));
					return mapping.findForward("edit");
				}
				
				EscapeInputUtility.escapeInput(customerForm);
			
			String userName = (String) customerForm.get("userName");
			String userPassword = (String) customerForm.get("userPassword");
			String confirmPassword = (String) customerForm
					.get("confirmPassword");
			
			String status = (String) customerForm.get("status");
			
			if (((String) customerForm.get("status")).length() != 0) {
				if (((String) customerForm.get("status")).length() > Constants.MAX_SECURE_LEN_1) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"errors.invalid.data");
					return mapping.findForward("edit");
				     }
				}
			
			
			String groupIds[] = request.getParameterValues("checkbox");
			userName=AmbitUtility.charVal(userName);

				Customer customer = (Customer) request.getSession()
						.getAttribute(Constants.CUSTOMER_IN_SESSION);

				

				if (groupIds != null) {
					Group[] allGroup = (Group[])request.getSession().getAttribute("groups");
					if(allGroup == null){
						ErrorsUtility.AddGlobalErrorToActionMessages(request,
								"group.error.notExistGroupInformation");
						return mapping.findForward("add");
						
					}
					groups = new Group[groupIds.length];
					for (int i = 0; i < groupIds.length; i++) {
																	
						if (groupIds[i].length() > Constants.MAX_SECURE_LEN_2) {
							ErrorsUtility.AddGlobalErrorToActionMessages(request,
									"errors.invalid.data");
							return mapping.findForward("add");
						     }
						//find selected group id in array allGroup
						for(int j=0;j<allGroup.length;j++){
							if(allGroup[j].getGroupId().equalsIgnoreCase(groupIds[i]))
								groups[i] = allGroup[j];
						}
					}

				}


				user = new Customer();

				if (Constants.CHECKBOX_SELECTED.equalsIgnoreCase(status))
					user.setUserActive(Constants.CODE_ACTIVE_FLAG.INACTIVE);
				else
					user.setUserActive(Constants.CODE_ACTIVE_FLAG.ACTIVE);
			
				user.setUserId(selectedUserId);
				user.setUserNameFA(userName);
				user.setUserName("");
				user.setUserPassword(userPassword);

				if (!isValidInputs(request, user, groups, confirmPassword,Constants.METHOD.EDIT)) {

					return mapping.findForward("edit");
				}
				userPassword = getEncryptionService().Encrypt(userPassword);
				user.setUserPassword(userPassword);
				responseCode = getSpringWSSoapBindingStub().updateUser(user,
						groups);
				Tracer.traceOut(Tracer.Tracing_Level.INFO,
						"CustomerManagementAction", " Method : Edit",
						" User : " + customer.getUserId() + " ResponseCode : "
								+ responseCode);
			
				String desc = Config.getPropertyFromBundle("UpdateUserDescription",language,country);
				desc = AmbitUtility.addAttributes(desc,
						new Object[] { selectedUserId });
				getSpringWSSoapBindingStub().logUpdateUserActivity(
						customer.getUserId(), desc);
				if (!AmbitUtility.isSuccessResponseCode(request, responseCode,
						"")) {
					return mapping.findForward("edit");
				}
				MessagesUtility.AddGlobalMessageToActionMessages(request,
						"customer.info.updateCustomer.Successfully");
				// refresh Group List
				searchUser(selectedUserId, request);

				return mapping.findForward("edit");

			}
			// search terminal Name
			else if (Constants.FALSE.equalsIgnoreCase(submitKey)) {
				
				if (!AmbitUtility.isSuccessResponseCode(request, responseCode,
						"")) {

					return mapping.findForward("edit");
				}

			} else {
				// select user from combo
				if (!AmbitUtility.isEmpty(selectedUserId)) {
					request.getSession().removeAttribute("group");
					request.getSession().removeAttribute("user");
					
					searchUser(selectedUserId, request);

					return mapping.findForward("edit");

				}

				else {
					// first load page

					request.getSession().removeAttribute("groups");					
					request.getSession().removeAttribute("user");					
					responseCode = getAllCustomer(request);
					if (!AmbitUtility.isSuccessResponseCode(request,
							responseCode, "")) {
						return mapping.findForward("edit");
					}

				}
			}

		} catch (Exception e) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"general.operationfailed");
			Tracer.exception("customerManagementAction", "Method: Edit",
					"Exception occured while Edit Customer : ", e);
		}
		return mapping.findForward("edit");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			// check grid pagination
		

			if (!AmbitUtility.isEmpty(request.getParameter("gridPagination"))
					&&(request.getParameter("gridPagination")).length() > Constants.MAX_SECURE_LEN_1) {
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"errors.invalid.data");
				return mapping.findForward("view");
			}
			
			String gridPagination =EscapeInputUtility.escapeInput( request.getParameter("gridPagination"));
			
			if (gridPagination != null
					&& request.getSession().getAttribute("users") != null)
				return mapping.findForward("view");
			getAllCustomer(request);

			Customer customer = (Customer) request.getSession().getAttribute(
					Constants.CUSTOMER_IN_SESSION);
			String language = getLocale(request).getLanguage();
			String country = getLocale(request).getCountry();
			getSpringWSSoapBindingStub().logViewUserActivity(
					customer.getUserId(),
					Config.getPropertyFromBundle("ViewUserDescription",language,country));
			
		} catch (Exception e) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"general.operationfailed");
			Tracer.exception("customerManagementAction", " Method : View ",
					"Exception occured while View Customer : ", e);
		}
		return mapping.findForward("view");
	}

	/*
	 * public String searchTerminalName(String terminalId, HttpServletRequest
	 * request) throws Exception { String responseCode = ""; Customer customer =
	 * null; try { customer = (Customer) request.getSession().getAttribute(
	 * Constants.CUSTOMER_IN_SESSION); ObjectHolder obj = new ObjectHolder();
	 * responseCode = getSpringWSSoapBindingStub().getTerminalDetail(
	 * terminalId, obj); Tracer.traceOut(Tracer.Tracing_Level.INFO,
	 * "GroupManagementAction", " Method : searchTerminalName", " User : " +
	 * customer.getUserId() + " ResponseCode : " + responseCode); UserSubnetwork
	 * userSubnetwork = (UserSubnetwork) org.apache.axis.utils.JavaUtils
	 * .convert(obj.value, UserSubnetwork.class);
	 * 
	 * request.getSession().setAttribute("userSubnetwork", userSubnetwork); }
	 * catch (Exception e) {
	 * 
	 * Tracer.exception("customerManagementAction", " Method : " +
	 * "searchTerminalName", "User Id : " + customer.getUserId(), e); }
	 * 
	 * return responseCode; }
	 */

	public void searchUser(String selectedUserId, HttpServletRequest request)
			throws Exception {
		Customer customer = null;
		String responseCode = "";
		try {
			customer = (Customer) request.getSession().getAttribute(
					Constants.CUSTOMER_IN_SESSION);

			ObjectHolder objUser = new ObjectHolder();
			
			ObjectHolder objAllGroups = new ObjectHolder();
			/*
			 * responseCode = getSpringWSSoapBindingStub().getUserDetail(
			 * selectedUserId, objUser, objUserSubnetwork, objAllGroups);
			 */
			responseCode = getSpringWSSoapBindingStub().getUserDetail(
					selectedUserId, objUser, objAllGroups);
			Tracer.traceOut(Tracer.Tracing_Level.INFO,
					"CustomerManagementAction", " Method : searchUser",
					" User : " + customer.getUserId() + " ResponseCode : "
							+ responseCode);
			if (!AmbitUtility.isSuccessResponseCode(request, responseCode, "")) {
				return;
			}
			Group[] groups = (Group[]) JavaUtils.convert(objAllGroups.value,
					Group[].class);

			request.getSession().setAttribute("Active",
					Constants.CODE_ACTIVE_FLAG.ACTIVE);
			request.getSession().setAttribute("InActive",
					Constants.CODE_ACTIVE_FLAG.INACTIVE);
			Customer user = (Customer) org.apache.axis.utils.JavaUtils.convert(
					objUser.value, Customer.class);
			/*
			 * UserSubnetwork userSubnetwork = (UserSubnetwork)
			 * org.apache.axis.utils.JavaUtils .convert(objUserSubnetwork.value,
			 * UserSubnetwork.class);
			 */

			request.getSession().setAttribute("user", user);

			// request.getSession().setAttribute("userSubnetwork",
			// userSubnetwork);

			// set status allPermission by groupPermission

			responseCode = getAllGroups(request);
			if (!AmbitUtility.isSuccessResponseCode(request, responseCode, "")) {
				return;
			}
			Group[] allGroups = (Group[]) request.getSession().getAttribute(
					"groups");
			if (groups != null) {

				boolean find = false;
				for (int i = 0; i < allGroups.length; i++) {
					for (int k = 0; k < groups.length; k++) {
						if (allGroups[i].getGroupId().equalsIgnoreCase(
								groups[k].getGroupId())) {
							find = true;
						}
					}
					if (!find) {
						allGroups[i]
								.setGroupActive(Constants.CODE_ACTIVE_FLAG.INACTIVE);
						find = false;
					}
					find = false;
				}
			} else {
				for (int i = 0; i < allGroups.length; i++) {
					allGroups[i]
							.setGroupActive(Constants.CODE_ACTIVE_FLAG.INACTIVE);
				}
			}
			// refresh combo user
			responseCode = getAllCustomer(request);
			if (!AmbitUtility.isSuccessResponseCode(request, responseCode, "")) {
				return;
			}
			request.getSession().setAttribute("groups", allGroups);
		} catch (Exception e) {

			Tracer.exception("customerManagementAction", "Method :"
					+ "searchUser", "User Id : " + customer.getUserId(), e);
		}


	}


}
