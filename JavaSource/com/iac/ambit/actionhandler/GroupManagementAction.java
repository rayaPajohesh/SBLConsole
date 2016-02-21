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
import com.iac.ambit.utils.MessagesUtility;
import com.iac.ambit.utils.AmbitUtility;
import com.iac.ambit.utils.Constants;
import com.iac.ambit.utils.ErrorsUtility;
import com.iac.ambit.utils.Tracer;
import com.iac.ambit.utils.Config;
import proxy.com.iac.ambit.model.Customer;
import proxy.com.iac.ambit.model.Permissions;

import proxy.com.iac.ambit.model.Group;
import proxy.localhost.SBLService.axis.SpringWS.SpringWSSoapBindingStub;

public class GroupManagementAction extends DispatchAction {
	private SpringWSSoapBindingStub springWSSoapBindingStub;

	public SpringWSSoapBindingStub getSpringWSSoapBindingStub() {
		return springWSSoapBindingStub;
	}

	public void setSpringWSSoapBindingStub(
			SpringWSSoapBindingStub springWSSoapBindingStub) {
		this.springWSSoapBindingStub = springWSSoapBindingStub;
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

	private String getAllPermission(HttpServletRequest request)
			throws Exception {
		Customer customer = (Customer) request.getSession().getAttribute(
				Constants.CUSTOMER_IN_SESSION);
		ObjectHolder obj = new ObjectHolder();
		String responseCode = getSpringWSSoapBindingStub().getAllPermissions(
				Constants.CODE_ACTIVE_FLAG.ACTIVE, obj);
		Tracer.traceOut(Tracer.Tracing_Level.INFO, "GroupManagementAction",
				"Method : getAllPermission", " User : " + customer.getUserId()
						+ " ResponseCode : " + responseCode);
		request.getSession().setAttribute("Active",
				Constants.CODE_ACTIVE_FLAG.ACTIVE);
		request.getSession().setAttribute("InActive",
				Constants.CODE_ACTIVE_FLAG.INACTIVE);
		Permissions[] menu = (Permissions[]) JavaUtils.convert(obj.value,
				Permissions[].class);

		request.getSession().setAttribute("allPermission", menu);
		return responseCode;
	}

	private String getAllGroup(HttpServletRequest request) throws Exception {
		Customer customer = (Customer) request.getSession().getAttribute(
				Constants.CUSTOMER_IN_SESSION);
		ObjectHolder obj = new ObjectHolder();
		String responseCode = getSpringWSSoapBindingStub()
				.getAllGroups("", obj);
		Tracer.traceOut(Tracer.Tracing_Level.INFO, "GroupManagementAction",
				"Method : getAllGroup ", " User : " + customer.getUserId()
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

	private boolean isValidInputs(HttpServletRequest request, Group group,Permissions[] permissions) {
		boolean result = true;
		String language = getLocale(request).getLanguage();
		String country = getLocale(request).getCountry();
	
		/*if (AmbitUtility.isEmpty(group.getGroupId())) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"group.error.groupId.Empty");
			result = false;
		}*/
		

		 if (!AmbitUtility.isAlphanumeric(group.getGroupId(), 50, 6)) {
			ErrorsUtility.AddGlobalErrorsToActionErrors(request,
					"errors.invalid",  "" + Config.getPropertyFromBundle("group.id",language,country));
			result = false;
		}
		 
		 if (!AmbitUtility.isAlphaFarsiNumeric(group.getGroupNameFA(),50,6)) {
				ErrorsUtility.AddGlobalErrorsToActionErrors(request,
						"errors.invalid", "" + Config.getPropertyFromBundle("group.name",language,country));		
				
				result = false;
			}
			
		
			
		
		
		 /*if (AmbitUtility.isEmpty(group.getGroupNameFA())) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"group.error.groupName.Empty");
			result = false;
		}*/
		
		/*if (!AmbitUtility.isAlphaNumeric(group.getGroupId())) {
			ErrorsUtility.AddGlobalErrorsToActionErrors(request,
					"errors.mask.alphanumeric", "" + Config.getPropertyFromBundle("group.id",language,country));
						
			result = false;
		}*/
		 /*if (!AmbitUtility.isAlphaFarsiNumeric(group.getGroupNameFA())) {
			ErrorsUtility.AddGlobalErrorsToActionErrors(request,
					"errors.mask.string", "" + Config.getPropertyFromBundle("group.name",language,country));		
			
			result = false;
		}*/
		 
		if (!AmbitUtility.isAlphaFarsiNumeric(group.getGroupDescriptionFA())) {
			ErrorsUtility.AddGlobalErrorsToActionErrors(request,
					"errors.invalid", "" + Config.getPropertyFromBundle("group.description",language,country));		
			
			result = false;
		}
		 /*if (group.getGroupId().length() > 50) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"group.error.groupId.notAllowLength");
			result = false;
		}
		if (group.getGroupNameFA().length() > 50) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"group.error.groupNameFA.notAllowLength");
			result = false;
		}*/
		 
		if (group.getGroupDescriptionFA().length() > 255) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"group.error.groupDescriptionFA.notAllowLength");
			result = false;
		}
		
		if(permissions == null){
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"group.error.permissionNotSelected");
			result = false;
		}
		return result;
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String responseCode = "";
		Customer customer = null;
		Permissions[] permissions = null;
		Group group;
		
		try {
			
			DynaValidatorForm groupForm = (DynaValidatorForm) form;
			EscapeInputUtility.escapeInput(groupForm);
			
			if (!AmbitUtility.isEmpty(request.getParameter("submitKey"))
					&&(request.getParameter("submitKey")).length() > Constants.MAX_SECURE_LEN_1) {
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"errors.invalid.data");
				return mapping.findForward("add");
			}					
			String submitKey = EscapeInputUtility.escapeInput(request.getParameter("submitKey"));
			
		
		
			if (Constants.TRUE.equalsIgnoreCase(submitKey)) {
				customer = (Customer) request.getSession().getAttribute(
						Constants.CUSTOMER_IN_SESSION);
				
				String groupId = (String) groupForm.get("groupId");
				String groupNmae = (String) groupForm.get("groupName");
				String groupDescription = (String) groupForm
						.get("groupDescription");
				String status = (String) groupForm.get("status");
				
				if (((String) groupForm.get("status")).length() != 0) {
					if (((String) groupForm.get("status")).length() > Constants.MAX_SECURE_LEN_1) {
						ErrorsUtility.AddGlobalErrorToActionMessages(request,
								"errors.invalid.data");
						return mapping.findForward("add");
					     }
					}
				

				String permissionIds[] = request.getParameterValues("checkbox");
				groupId=groupId.trim();
				groupNmae =AmbitUtility.charVal(groupNmae);
				groupDescription=AmbitUtility.charVal(groupDescription);
				if (permissionIds != null) {
					Permissions[] allPermission = (Permissions[])request.getSession().getAttribute("allPermission");
					if(allPermission == null){
						ErrorsUtility.AddGlobalErrorToActionMessages(request,
								"permission.error.notExistPermissionInformation");
						return mapping.findForward("add");
						
					}
					permissions = new Permissions[permissionIds.length];
					for (int i = 0; i < permissionIds.length; i++) {
						
						if (permissionIds[i].length() > Constants.MAX_SECURE_LEN_2) {
							ErrorsUtility.AddGlobalErrorToActionMessages(request,
									"errors.invalid.data");
							return mapping.findForward("add");
						     }
						
						//find selected Permission id in array allGroup
						for(int j=0;j<allPermission.length;j++){
							if(allPermission[j].getPermissionId() == Integer.parseInt(permissionIds[i]))
								permissions[i] = allPermission[j];
						}
						
					}

				}

				group = new Group();

				if (Constants.CHECKBOX_SELECTED.equalsIgnoreCase(status))
					group.setGroupActive(Constants.CODE_ACTIVE_FLAG.INACTIVE);
				else
					group.setGroupActive(Constants.CODE_ACTIVE_FLAG.ACTIVE);
				group.setGroupDescriptionFA(groupDescription);
				group.setGroupName("");
				group.setGroupNameFA(groupNmae);
				group.setGroupDescription("");
				group.setGroupId(groupId);
				
				if (!isValidInputs(request, group,permissions)) {

					return mapping.findForward("add");
				}

				responseCode = getSpringWSSoapBindingStub().addGroup(group,
						permissions);
				Tracer.traceOut(Tracer.Tracing_Level.INFO,
						"GroupManagementAction", " Method : Add", " User : "
								+ customer.getUserId() + " ResponseCode : "
								+ responseCode);
				String language = getLocale(request).getLanguage();
				String country = getLocale(request).getCountry();
				String desc = Config.getPropertyFromBundle("AddGroupDescription",language,country);
				desc = AmbitUtility.addAttributes(desc, new Object[] {groupNmae});
				getSpringWSSoapBindingStub()
						.logAddGroupActivity(customer.getUserId(),desc);
				if (!AmbitUtility.isSuccessResponseCode(request, responseCode,
						"")) {
					return mapping.findForward("add");
				}

				MessagesUtility.AddGlobalMessageToActionMessages(request,
						"group.info.addGroup.Successfully");

			} else {
				request.getSession().removeAttribute("group");
				responseCode = getAllPermission(request);

				AmbitUtility.isSuccessResponseCode(request, responseCode, "");
			}

		} catch (Exception e) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
			"general.operationfailed");
			Tracer.exception("GroupManagementAction", "Add",
					"Exception occured while Add Group : ", e);
		}
		return mapping.findForward("add");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String responseCode = "";
		Customer customer = null;
		Permissions[] permissions = null;
		Group group;
		
		try {
			
			if (!AmbitUtility.isEmpty(request.getParameter("submitKey"))
					&&(request.getParameter("submitKey")).length() > Constants.MAX_SECURE_LEN_1) {
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"errors.invalid.data");
				return mapping.findForward("edit");
			}					
			String submitKey = EscapeInputUtility.escapeInput(request.getParameter("submitKey"));
		

		
			customer = (Customer) request.getSession().getAttribute(
					Constants.CUSTOMER_IN_SESSION);
			DynaValidatorForm groupForm = (DynaValidatorForm) form;
			String selectedGroupId = (String) groupForm.get("selectedGroupId");
			
			if (!AmbitUtility.isEmpty(selectedGroupId)) {
				   if (selectedGroupId.length() > Constants.MAX_SECURE_LEN_2) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"errors.invalid.data");
					return mapping.findForward("edit");
			    	}
				   
				   if (!AmbitUtility.isAlphaNumeric(selectedGroupId)) {
					   ErrorsUtility.AddGlobalErrorToActionMessages(request,
								"errors.invalid.data");
						return mapping.findForward("edit");
					}   
				   
				   
			}
			
			
			
			String groupName = (String) groupForm.get("groupName");
			String groupDescription = (String) groupForm
					.get("groupDescription");
			String status = (String) groupForm.get("status");
			
			if (((String) groupForm.get("status")).length() != 0) {
				if (((String) groupForm.get("status")).length() > Constants.MAX_SECURE_LEN_1) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"errors.invalid.data");
					return mapping.findForward("edit");
				     }
				}
			
			String permissionIds[] = request.getParameterValues("checkbox");
			;
			groupName =AmbitUtility.charVal(groupName);
			groupDescription = AmbitUtility.charVal(groupDescription);
			// save information
			
			if ("true".equalsIgnoreCase(submitKey)) {

				if (permissionIds != null) {

					permissions = new Permissions[permissionIds.length];
					for (int i = 0; i < permissionIds.length; i++) {
						
						if (permissionIds[i].length() > Constants.MAX_SECURE_LEN_2) {
							ErrorsUtility.AddGlobalErrorToActionMessages(request,
									"errors.invalid.data");
							return mapping.findForward("edit");
						     }
						
						Permissions val = new Permissions();
						val.setPermissionId(Integer.parseInt(permissionIds[i]));
						permissions[i] = val;
					}

				}

				group = new Group();

				if (Constants.CHECKBOX_SELECTED.equalsIgnoreCase(status))
					group.setGroupActive(Constants.CODE_ACTIVE_FLAG.INACTIVE);
				else
					group.setGroupActive(Constants.CODE_ACTIVE_FLAG.ACTIVE);
				group.setGroupDescriptionFA(groupDescription);
				group.setGroupName("");
				group.setGroupNameFA(groupName);
				group.setGroupDescription("");
				group.setGroupId(selectedGroupId);
				if (!isValidInputs(request, group,permissions)) {

					return mapping.findForward("edit");
				}

				responseCode = getSpringWSSoapBindingStub().updateGroup(group,
						permissions);
				Tracer.traceOut(Tracer.Tracing_Level.INFO,
						"GroupManagementAction", " Method : Edit", " User : "
								+ customer.getUserId() + " ResponseCode : "
								+ responseCode);
				String language = getLocale(request).getLanguage();
				String country = getLocale(request).getCountry();
				String desc = Config.getPropertyFromBundle("UpdateGroupDescription",language,country);
				desc = AmbitUtility.addAttributes(desc, new Object[] {groupName});
				getSpringWSSoapBindingStub()
						.logUpdateGroupActivity(customer.getUserId(),desc);
				if (!AmbitUtility.isSuccessResponseCode(request, responseCode,
						"")) {
					return mapping.findForward("edit");
				}
				MessagesUtility.AddGlobalMessageToActionMessages(request,
						"group.info.updateGroup.Successfully");
				// refresh list Permission
				search(selectedGroupId, request);

				return mapping.findForward("edit");

			} else {
				// select group from combo
				if (!AmbitUtility.isEmpty(selectedGroupId)) {
					request.getSession().removeAttribute("group");
					search(selectedGroupId, request);

					return mapping.findForward("edit");

				}

				else {
					// first load page
					request.getSession().removeAttribute("group");
					request.getSession().removeAttribute("allPermission");
					responseCode = getAllGroup(request);

					if (!AmbitUtility.isSuccessResponseCode(request,
							responseCode, "")) {
						return mapping.findForward("edit");
					}

				}

			}

		} catch (Exception e) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
			"general.operationfailed");
			Tracer.exception("groupManagementAction", " Method: Edit "
					+  "User Id : " + customer.getUserId(), e);
		}
		return mapping.findForward("edit");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Customer customer = (Customer) request.getSession().getAttribute(
				Constants.CUSTOMER_IN_SESSION);
		try{
			
		// check grid pagination
			if (!AmbitUtility.isEmpty(request.getParameter("gridPagination"))
					&&(request.getParameter("gridPagination")).length() > Constants.MAX_SECURE_LEN_1) {
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"errors.invalid.data");
				return mapping.findForward("view");
			}
			
			String gridPagination =EscapeInputUtility.escapeInput( request.getParameter("gridPagination"));
	
		if ( gridPagination != null && request.getSession().getAttribute("groups") !=null) {
			return mapping.findForward("view");
		}
		

		String responseCode = getAllGroup(request);
		Tracer.traceOut(Tracer.Tracing_Level.INFO, "GroupManagementAction",
				" Method: View ", " User : " + customer.getUserId()
						+ " ResponseCode : " + responseCode);
		String language = getLocale(request).getLanguage();
		String country = getLocale(request).getCountry();
		getSpringWSSoapBindingStub()
		.logViewGroupActivity(customer.getUserId(),Config.getPropertyFromBundle("ViewGroupDescription",language,country));
		AmbitUtility.isSuccessResponseCode(request, responseCode, "");
		}
		catch (Exception e) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
			"general.operationfailed");
			Tracer.exception("groupManagementAction", " Method : View ",
					"Exception occured while View Group : ", e);
		}
		return mapping.findForward("view");
	}

	private void search(String selectedGroupId, HttpServletRequest request)
			throws Exception {
		Customer customer = null;
		String responseCode = "";
		try {
			customer = (Customer) request.getSession().getAttribute(
					Constants.CUSTOMER_IN_SESSION);

			ObjectHolder objGroup = new ObjectHolder();
			ObjectHolder objPermission = new ObjectHolder();
			responseCode = getSpringWSSoapBindingStub().getGroupDetail(
					selectedGroupId, objGroup, objPermission);
			Tracer.traceOut(Tracer.Tracing_Level.INFO, "GroupManagementAction",
					" Method : Search ", " User : " + customer.getUserId()
							+ " ResponseCode : " + responseCode);
			if (!AmbitUtility.isSuccessResponseCode(request, responseCode, "")) {
				return;
			}
			Permissions[] groupPermission = (Permissions[]) JavaUtils.convert(
					objPermission.value, Permissions[].class);

			request.getSession().setAttribute("Active",
					Constants.CODE_ACTIVE_FLAG.ACTIVE);
			request.getSession().setAttribute("InActive",
					Constants.CODE_ACTIVE_FLAG.INACTIVE);
			Group group = (Group) org.apache.axis.utils.JavaUtils.convert(
					objGroup.value, Group.class);

			request.getSession().setAttribute("group", group);

			// set status allPermission by groupPermission

			responseCode = getAllPermission(request);
			if (!AmbitUtility.isSuccessResponseCode(request, responseCode, "")) {
				return;
			}
			Permissions[] allPermission = (Permissions[]) request.getSession()
					.getAttribute("allPermission");
			if (groupPermission != null) {

				boolean find = false;
				for (int i = 0; i < allPermission.length; i++) {
					for (int k = 0; k < groupPermission.length; k++) {
						if (allPermission[i].getPermissionId() == groupPermission[k]
								.getPermissionId()) {
							find = true;
						}
					}
					if (!find) {
						allPermission[i]
								.setPermissionStatus(Constants.CODE_ACTIVE_FLAG.INACTIVE);
						find = false;
					}
					find = false;
				}
			} else {
				for (int i = 0; i < allPermission.length; i++) {
					allPermission[i]
							.setPermissionStatus(Constants.CODE_ACTIVE_FLAG.INACTIVE);
				}
			}

			request.getSession().setAttribute("allPermission", allPermission);

			// refresh combo group
			responseCode = getAllGroup(request);
			if (!AmbitUtility.isSuccessResponseCode(request, responseCode, "")) {
				return;
			}

		} catch (Exception e) {

			Tracer.exception("groupManagementAction", " Method : " + "Search",
					"User Id : " + customer.getUserId(), e);
		}

		;
	}
	

}
