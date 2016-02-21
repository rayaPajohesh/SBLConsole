package com.iac.ambit.actionhandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.apache.struts.validator.DynaValidatorActionForm;

import com.iac.ambit.utils.EscapeInputUtility;
import com.iac.ambit.utils.AmbitUtility;
import com.iac.ambit.utils.Config;
import com.iac.ambit.utils.Constants;
import com.iac.ambit.utils.DateFarsi;
import com.iac.ambit.utils.DateUtils;
import com.iac.ambit.utils.ErrorsUtility;
import com.iac.ambit.utils.MessagesUtility;
import com.iac.ambit.utils.Tracer;

import proxy.com.iac.ambit.model.BlackList;
import proxy.com.iac.ambit.model.CardInfo;
import proxy.com.iac.ambit.model.Customer;

import proxy.com.iac.ambit.model.holders.CardInfoHolder;
import proxy.localhost.SBLService.axis.SpringWS.SpringWSSoapBindingStub;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.holders.ObjectHolder;
import org.apache.commons.lang.StringUtils;

public class BlackListManagementAction extends LookupDispatchAction {

	private SpringWSSoapBindingStub springWSSoapBindingStub;

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
		String  path = "searchSuccess";
		String method = "";

				
		String parameter = request.getParameter("blackList");
		
		if (!AmbitUtility.isEmpty(request.getParameter("blackList"))
				&&(request.getParameter("blackList")).length() > Constants.MAX_SECURE_LEN_1) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"errors.invalid.data");
			return mapping.findForward("failure");
		}
		
		 parameter = (String) EscapeInputUtility.escapeInput(parameter);
				
		String returnPath = (String)request.getAttribute("returnPath");
		
		Customer customer = (Customer) request.getSession().getAttribute(
				Constants.CUSTOMER_IN_SESSION);
		String pagePermission = Config.getProperty("BlackListManagement");

		try {
			method = getLookupMapName(request, parameter, mapping);
		} catch (Exception e) {
			// grid navigation
			method = Constants.METHOD.SEARCH;
		}
		if(parameter != null){
		if (Constants.METHOD.SEARCH.equalsIgnoreCase(method)
				&& AmbitUtility.userHasAccess(pagePermission, Config
						.getProperty("View"), customer.getAccess()))
			path = search(mapping, form, request, response);
		else if (Constants.METHOD.ADD.equalsIgnoreCase(method)
				&& AmbitUtility.userHasAccess(pagePermission, Config
						.getProperty("Add"), customer.getAccess()))
			path = add(mapping, form, request, response);
		else if (Constants.METHOD.EDIT.equalsIgnoreCase(method)
				&& AmbitUtility.userHasAccess(pagePermission, Config
						.getProperty("Edit"), customer.getAccess()))
			path = edit(mapping, form, request, response);
		else if (Constants.METHOD.DELETE.equalsIgnoreCase(method)
				&& AmbitUtility.userHasAccess(pagePermission, Config
						.getProperty("Delete"), customer.getAccess()))
			path = delete(mapping, form, request, response);
		else if (Constants.METHOD.SEARCH_PAN.equalsIgnoreCase(method))
			path = searchPan(mapping, form, request, response);
		else if (Constants.METHOD.OPERATION_Ok.equalsIgnoreCase(method))
			path = operationOk(mapping, form, request, response);
		else if (Constants.METHOD.CANCEL.equalsIgnoreCase(method))
			path = operationCancel(mapping, form, request, response);
		else if (Constants.METHOD.LIMITS.equalsIgnoreCase(method)
				&& AmbitUtility.userHasAccess(pagePermission, Config
						.getProperty("Limit"), customer.getAccess()))
			path = limits(mapping, form, request, response);

		else if (Constants.METHOD.TRANSACTIONS.equalsIgnoreCase(method))
			path = transactions(mapping, form, request, response);
		}
		else if(returnPath != null){
			
			String allowedActions = AmbitUtility.getAllowedActions(
					pagePermission, customer.getAccess());
			String activeFlag = "";
			request.getSession().setAttribute("allowedActions", allowedActions);
			if("cardLimitation".equalsIgnoreCase(returnPath)){
				method = Constants.METHOD.SEARCH;
				String selectedPan = "";
				
				String fromDate = (String)request.getSession().getAttribute("latinFromDate");
				String toDate = (String)request.getSession().getAttribute("latinToDate");
				if(fromDate == "" && toDate == ""){
					selectedPan = (String)request.getAttribute("selectedPan");
				}
				refresh(selectedPan, fromDate, toDate,activeFlag, request);
				path = "searchSuccess";
			}
			if("blackListTransReport".equalsIgnoreCase(returnPath)){
				String selectedPan = "";
				String fromDate = (String)request.getSession().getAttribute("latinFromDate");
				String toDate = (String)request.getSession().getAttribute("latinToDate");
				if(fromDate == "" && toDate == ""){
					selectedPan = (String)request.getAttribute("selectedPan");
				}
				refresh(selectedPan, fromDate, toDate, activeFlag ,request);
				path = "searchSuccess";
			}
			
		}
		return mapping.findForward(path);
	}

	public String search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String path = "searchSuccess";
		String responseCode = "";

		request.getSession().setAttribute("method", "search");
		request.getSession().setAttribute("TOTAL_PARAM", null);
		

		request.getSession().setAttribute("disabledPan", Boolean.FALSE);
		request.getSession().setAttribute("disabledNotes", Boolean.FALSE);
		request.getSession().setAttribute("disabledReason", Boolean.FALSE);

		String fromDateFarsi = null;
		String toDateFarsi = null;
		String tempFromDateFarsi = null;
		String tempToDateFarsi = null;
		String latinFromDate = null;
		String latinToDate = null;
		String activeFlag = null;
		Date fromDate = new Date();
		Date toDate = new Date();
		
		

		try {

			DynaValidatorActionForm blackListForm = (DynaValidatorActionForm) form;
			
			EscapeInputUtility.escapeInput(blackListForm);
			
			Customer customer = (Customer) request.getSession().getAttribute(
					Constants.CUSTOMER_IN_SESSION);
//			 check grid pagination
			String gridPagination = request.getParameter("gridPagination");
			
			if (!AmbitUtility.isEmpty(request.getParameter("gridPagination"))
					&&(request.getParameter("gridPagination")).length() > Constants.MAX_SECURE_LEN_1) {
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"errors.invalid.data");
				return ("failure");
			}
			
			gridPagination = (String) EscapeInputUtility.escapeInput(gridPagination);
			
			if (gridPagination != null && request.getSession().getAttribute("blackList") != null) {

				return path;
			}

			String pan = blackListForm.getString("pan");

			DateFarsi dateFarsi = new DateFarsi();

			if (AmbitUtility.isEmpty(blackListForm.get("fromDate"))
					|| AmbitUtility.isEmpty(blackListForm.get("toDate"))) {
				if (AmbitUtility.isEmpty(blackListForm.get("fromDate"))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"blackList.error.fromdate.absent");
				}
				if (AmbitUtility.isEmpty(blackListForm.get("toDate"))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"blackList.error.todate.absent");
				}
				return ("failure");
			}

			if (!dateFarsi.isValidRevFormattedFDate((String) blackListForm
					.get("fromDate"))
					|| !dateFarsi
							.isValidRevFormattedFDate((String) blackListForm
									.get("toDate"))) {

				if (!dateFarsi.isValidRevFormattedFDate((String) blackListForm
						.get("fromDate"))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"blackList.error.fromDate.invalid");

				}
				if (!dateFarsi.isValidRevFormattedFDate((String) blackListForm
						.get("toDate"))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"blackList.error.toDate.invalid");

				}
				return ("failure");

			}

			else {
				fromDateFarsi = dateFarsi
						.unformatRevFormattedFdate((String) blackListForm
								.get("fromDate"));

				toDateFarsi = dateFarsi
						.unformatRevFormattedFdate((String) blackListForm
								.get("toDate"));

				fromDate = DateUtils.stringToDate(dateFarsi
						.formatFdate(fromDateFarsi));
				toDate = DateUtils.stringToDate(dateFarsi
						.formatFdate(toDateFarsi));
				blackListForm.set("fromDate", dateFarsi
						.formatFdate(fromDateFarsi));
				blackListForm.set("toDate", dateFarsi.formatFdate(toDateFarsi));

				if (fromDateFarsi.compareTo(toDateFarsi) > 0) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"blackList.fromDateMoreThan");
					return ("failure");
				}

				// Check whether from date is less than a year or not
				if (!DateUtils.isLessThanYears(fromDate, toDate, 1)) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"blackList.isMoreThanOneYear");
					request.getSession().setAttribute("blackList", null);
					return ("failure");

				}
			}

			tempFromDateFarsi = dateFarsi.FDateConv(fromDateFarsi);
			latinFromDate = tempFromDateFarsi;
			tempToDateFarsi = dateFarsi.FDateConv(toDateFarsi);
			latinToDate = tempToDateFarsi;

			if (!AmbitUtility.isEmpty(pan)) {
				if (!((pan.substring(0, 6)).equalsIgnoreCase(Config
						.getProperty("ACQUIRER_BANK_IMD")))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"blackList.error.pan.invalid");
					request.getSession().setAttribute("blackList", null);
					return ("failure");

				}
				if (!AmbitUtility.isNumeric(pan, 19)) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"errors.invalid.data");
					return ("failure");
				}
				pan = AmbitUtility.addTrailing(pan,19,"0");
			} 
			
			activeFlag = blackListForm.getString("statusList");
			
			
			if (!AmbitUtility.isEmpty(activeFlag)) {
				if (!AmbitUtility.isNumeric(activeFlag,1)) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"errors.invalid.data");
					return ("failure");
				}
			}

			ObjectHolder list = new ObjectHolder();
			responseCode = getSpringWSSoapBindingStub().searchBlackList(
					latinFromDate, latinToDate, pan, activeFlag, list);
			
			Tracer.traceOut(Tracer.Tracing_Level.INFO, "BlackListManagementAction",
					"search", " User : " + customer.getUserId()
							+ " ResponseCode : " + responseCode);
			
			String language = getLocale(request).getLanguage();
			String country = getLocale(request).getCountry();
			String desc = Config.getPropertyFromBundle("SearchBlackListDescription",language,country);
			desc = AmbitUtility.addAttributes(desc,
					new Object[] { pan,(String) blackListForm
					.get("fromDate"),(String) blackListForm
					.get("toDate") });
			getSpringWSSoapBindingStub().logSearchBlackListActivity(
					customer.getUserId(),desc);
			
			BlackList[] blackList = (BlackList[]) org.apache.axis.utils.JavaUtils
					.convert(list.value, BlackList[].class);

			if (!AmbitUtility.isSuccessResponseCode(request, responseCode, "")) {
				request.getSession().setAttribute("blackList", null);
				return ("failure");
			}

			for (int i = 0; i < blackList.length; i++) {
				((BlackList) blackList[i]).setBlackedDate(dateFarsi
						.formatFdate(dateFarsi
								.LDateConv(((BlackList) blackList[i])
										.getBlackedDate())));

				((BlackList) blackList[i])
						.setBlackedTime(dateFarsi
								.formatTime(((BlackList) blackList[i])
										.getBlackedTime()));

			}

			request.getSession().setAttribute("printList", blackList);
			request.getSession().setAttribute("blackList", blackList);
			request.getSession().setAttribute("latinFromDate", latinFromDate);
			request.getSession().setAttribute("latinToDate", latinToDate);
			request.getSession().setAttribute("farsiFromDate",
					(String) blackListForm.get("fromDate"));
			request.getSession().setAttribute("farsiToDate",
					(String) blackListForm.get("toDate"));
			request.getSession().setAttribute("print",
					Constants.BLACK_LIST_MANAGE);

			// set in session for print
			request.getSession().setAttribute("fromDate",
					blackListForm.get("fromDate"));
			request.getSession().setAttribute("toDate",
					blackListForm.get("toDate"));
			request.getSession().setAttribute("pan", blackListForm.get("pan"));

		} catch (Exception e) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"general.operationfailed");
			Tracer.exception("BlackListManagementAction", "search",
					"Exception occured while search in blackList : ", e);
			path = "failure";

		}

		return (path);

	}

	public String add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		request.getSession().setAttribute("blackList", null);
		request.getSession().setAttribute("TOTAL_PARAM", null);
		DynaValidatorActionForm blackListForm = (DynaValidatorActionForm) form;
		
		EscapeInputUtility.escapeInput(blackListForm);
		
		String path = "add";

		blackListForm.set("pan", "");

		request.getSession().setAttribute("method", "add");

		request.getSession().setAttribute("disabledPan", Boolean.FALSE);
		request.getSession().setAttribute("disabledNotes", Boolean.FALSE);
		request.getSession().setAttribute("disabledReason", Boolean.FALSE);

		return (path);

	}

	public String edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		request.getSession().setAttribute("blackList", null);
		request.getSession().setAttribute("method", "edit");
		String path = "edit";
		String responseCode = "";
		
		

		request.getSession().setAttribute("disabledPan", Boolean.TRUE);
		request.getSession().setAttribute("disabledNotes", Boolean.FALSE);
		request.getSession().setAttribute("disabledReason", Boolean.FALSE);

		try {

			DynaValidatorActionForm blackListForm = (DynaValidatorActionForm) form;
			
			EscapeInputUtility.escapeInput(blackListForm);

			String selectedPan = blackListForm.getString("selectedPan");

			if (AmbitUtility.isEmpty(selectedPan)) {
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"blackList.panSelectedIsEmpty");
												
				request.getSession().setAttribute("method", "search");

				return ("failure");
				
				
			}
			
			
			if (!AmbitUtility.isNumeric(selectedPan, 19)) {
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
				"errors.invalid.data");
				return ("failure");
			}

			ObjectHolder obj = new ObjectHolder();

			responseCode = getSpringWSSoapBindingStub().searchPanInBlackList(
					selectedPan, obj);

			if (!AmbitUtility.isSuccessResponseCode(request, responseCode, "")) {

				return ("failure");
			}

			BlackList bl = (BlackList) org.apache.axis.utils.JavaUtils.convert(
					obj.value, BlackList.class);

			blackListForm.set("selectedPan", "");

			blackListForm.set("blackReasons", bl.getBlackedReasonId());
			
			if (!AmbitUtility.isEmpty(blackListForm.getString("notes"))) {
				if (!EscapeInputUtility.isValideInput(blackListForm.getString("notes"))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"errors.invalid.data");
							return ("failure");	
				}
				}

			blackListForm.set("notes", bl.getComments());

			blackListForm.set("pan", bl.getPan());
			
			

			blackListForm.set("activeFlag", bl.getBlackedActiveFlag());

			request.getSession().setAttribute("blackListForUpdate", bl);
			request.getSession().setAttribute("formPan", bl.getPan());
			
			String totalParam =  AmbitUtility
							.nvl((String) bl.getCardInfo().getNameAndFamilyName())
					+ "  "
					+ AmbitUtility.nvl((String) bl.getCardInfo().getCardStatusDesc());
			
			
			request.getSession().setAttribute("TOTAL_PARAM",
					totalParam);

		} catch (Exception e) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"general.operationfailed");
			Tracer.exception("BlackListManagementAction", "edit",
					"Exception occured while edit : ", e);
			path = "failure";

		}

		return (path);

	}

	public String delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		
		request.getSession().setAttribute("method", "delete");
		String responseCode = "";
		String path = "edit";
		
		

		request.getSession().setAttribute("disabledPan", Boolean.TRUE);
		request.getSession().setAttribute("disabledNotes", Boolean.TRUE);
		request.getSession().setAttribute("disabledReason", Boolean.TRUE);

		try {

			DynaValidatorActionForm blackListForm = (DynaValidatorActionForm) form;
			
			EscapeInputUtility.escapeInput(blackListForm);

			String selectedPan = blackListForm.getString("selectedPan");

			if (AmbitUtility.isEmpty(selectedPan)) {
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"blackList.panSelectedIsEmpty");

				request.getSession().setAttribute("method", "search");
				return ("failure");
			}

			if (!AmbitUtility.isNumeric(selectedPan, 19)) {
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
				"errors.invalid.data");
				return ("failure");
			}
			
			
			ObjectHolder obj = new ObjectHolder();

			responseCode = getSpringWSSoapBindingStub().searchPanInBlackList(
					selectedPan, obj);

			if (!AmbitUtility.isSuccessResponseCode(request, responseCode, "")) {

				return ("failure");
			}

			BlackList bl = (BlackList) org.apache.axis.utils.JavaUtils.convert(
					obj.value, BlackList.class);
			// if its removed befor
			if (bl.getBlackedActiveFlag().equalsIgnoreCase(
					Constants.CODE_ACTIVE_FLAG.INACTIVE)) {
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"blackList.IsRemovedBefor");

				request.getSession().setAttribute("method", "search");
				return ("failure");

			}

			blackListForm.set("selectedPan", "");

			blackListForm.set("blackReasons", bl.getBlackedReasonId());
			
			// 92/9/10
			if (!AmbitUtility.isEmpty(blackListForm.getString("notes"))) {
				if (!EscapeInputUtility.isValideInput(blackListForm.getString("notes"))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"errors.invalid.data");
							return ("failure");	
				}
				}

			blackListForm.set("notes", bl.getComments());

			blackListForm.set("pan", bl.getPan());

			blackListForm.set("activeFlag", bl.getBlackedActiveFlag());
			
			request.getSession().setAttribute("formPan", bl.getPan());
			
			request.getSession().setAttribute("panForDelete", selectedPan);

			String totalParam =  AmbitUtility
							.nvl((String) bl.getCardInfo().getNameAndFamilyName())
					+ "  "
					+ AmbitUtility.nvl((String) bl.getCardInfo().getCardStatusDesc());
			
			
			request.getSession().setAttribute("TOTAL_PARAM",
					totalParam);
			
			request.getSession().setAttribute("blackList", null);

		} catch (Exception e) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"general.operationfailed");
			Tracer.exception("BlackListManagementAction", "edit",
					"Exception occured while delete : ", e);
			path = "failure";

		}

		return (path);

	}

	public String operationOk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		String method = request.getSession().getAttribute("method") + "";
		String path = "searchSuccess";
		DynaValidatorActionForm blackListForm = (DynaValidatorActionForm) form;
		
		EscapeInputUtility.escapeInput(blackListForm);
		String responseCode = "";
		Customer customer = (Customer) request.getSession().getAttribute(
				Constants.CUSTOMER_IN_SESSION);
		String language = getLocale(request).getLanguage();
		String country = getLocale(request).getCountry();
		try {

			if (Constants.METHOD.ADD.equalsIgnoreCase(method)) {

				BlackList bl = new BlackList();
				String pan = blackListForm.getString("pan");

				if (AmbitUtility.isEmpty(pan)) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"blackList.error.pan.Empty");
					return ("failure");
				}
				
				if (!AmbitUtility.isEmpty(pan)) {
					if (!((pan.substring(0, 6)).equalsIgnoreCase(Config
							.getProperty("ACQUIRER_BANK_IMD")))) {
						ErrorsUtility.AddGlobalErrorToActionMessages(request,
								"blackList.error.pan.invalid");
						request.getSession().setAttribute("blackList", null);
						return ("failure");
					}
					
					if (!AmbitUtility.isNumeric(pan, 19)) {
						ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"errors.invalid.data");
						return ("failure");
					}
					
					pan = AmbitUtility.addTrailing(pan,19,"0");	
				}
				
				
				CardInfoHolder cardInfoHolder = new CardInfoHolder();
				
				responseCode = getSpringWSSoapBindingStub().searchPanInformation(
						pan, cardInfoHolder);

				
				
				if (Constants.RESPONSE_CODE.RECORD_NOT_FOUND.equalsIgnoreCase(responseCode)){
					
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"blackList.error.pan.invalid");
					return ("failure");
				}

				else if (!AmbitUtility.isSuccessResponseCode(request, responseCode, "")) {
						
						return ("failure");
					}
				
				
				
				
				bl.setPan(pan);
				
				String blackReason = blackListForm.getString("blackReasons");
				
				if (!AmbitUtility.isEmpty(blackReason)) {
					if (!AmbitUtility.isNumeric(blackReason,1)) {
						ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"errors.invalid.data");
						return ("failure");
					}
				}

				bl.setBlackedReasonId(blackReason);
				
				
				
				bl.setComments(blackListForm.getString("notes"));
				bl.setBlackedActiveFlag(Constants.CODE_ACTIVE_FLAG.ACTIVE);
				bl.setBlackedDate(AmbitUtility.getCurrentDate());
				bl.setBlackedTime(AmbitUtility.getCurrentTime());
				
				responseCode = getSpringWSSoapBindingStub().addBlackList(bl);
				
				Tracer.traceOut(Tracer.Tracing_Level.INFO, "BlackListManagementAction",
						"AddOK", " User : " + customer.getUserId()
								+ " ResponseCode : " + responseCode);
				
				String desc = Config.getPropertyFromBundle("AddInBlackListDescription",language,country);
				desc = AmbitUtility.addAttributes(desc,
						new Object[] { pan });
				getSpringWSSoapBindingStub().logAddInBlackListActivity(
						customer.getUserId(),desc);
				
				
				

				 if (!AmbitUtility.isSuccessResponseCode  (request, responseCode,
						"")) {

					return ("failure");
				}
				
				String fromDate = AmbitUtility.getCurrentDate();
				String toDate = fromDate;
				request.getSession().setAttribute("latinFromDate", fromDate);
				request.getSession().setAttribute("latinToDate", toDate);
				request.setAttribute("selectedPan", pan);
				refresh("", fromDate, toDate,"", request);
				DateFarsi dateFarsi = new DateFarsi();
				fromDate = dateFarsi.formatFdate(dateFarsi.CurFdate());
				toDate = fromDate;
				request.getSession().setAttribute("farsiFromDate",fromDate);
				request.getSession().setAttribute("farsiToDate",toDate);
				
				MessagesUtility.AddGlobalMessageToActionMessages(request,
						"blackList.addIsOk");

			}

			if (Constants.METHOD.EDIT.equalsIgnoreCase(method)) {
				String fromDate = (String) request.getSession().getAttribute(
						"latinFromDate");
				String toDate = (String) request.getSession().getAttribute(
						"latinToDate");

				BlackList bl = (BlackList) request.getSession().getAttribute(
						"blackListForUpdate");
				
				String blackReason = blackListForm.getString("blackReasons");
				
				if (!AmbitUtility.isEmpty(blackReason)) {
					if (!AmbitUtility.isNumeric(blackReason,1)) {
						ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"errors.invalid.data");
						return ("failure");
					}
				}

				bl.setBlackedReasonId(blackReason);
				
				
				bl.setComments(blackListForm.getString("notes"));
				bl.getCardInfo().setPan(bl.getPan());
				
				
				String extraDesc = "";

				if (blackListForm.getString("activeFlag").equalsIgnoreCase(
						Constants.CHECKBOX_SELECTED)) {
					bl.setBlackedActiveFlag(Constants.CODE_ACTIVE_FLAG.ACTIVE);
					extraDesc =  " " + Config.getPropertyFromBundle("EditBlackListExtraDescription",language,country);
				}

				responseCode = getSpringWSSoapBindingStub().updateBlackList(bl);
				
				Tracer.traceOut(Tracer.Tracing_Level.INFO, "BlackListManagementAction",
						"EditOK", " User : " + customer.getUserId()
								+ " ResponseCode : " + responseCode);
				String desc =  Config.getPropertyFromBundle("EditBlackListDescription",language,country);
				desc = AmbitUtility.addAttributes(desc,
						new Object[] { bl.getPan() });
				getSpringWSSoapBindingStub().logEditBlackListActivity(
						customer.getUserId(),desc+extraDesc);

				if (!AmbitUtility.isSuccessResponseCode(request, responseCode,
						"")) {

					return ("failure");
				}
				request.setAttribute("selectedPan", bl.getPan());
				refresh("", fromDate, toDate,"", request);
				MessagesUtility.AddGlobalMessageToActionMessages(request,
						"blackList.updateIsOk");

			}

			if (Constants.METHOD.DELETE.equalsIgnoreCase(method)) {
				String fromDate = (String) request.getSession().getAttribute(
						"latinFromDate");
				String toDate = (String) request.getSession().getAttribute(
						"latinToDate");
				String pan = (String) request.getSession().getAttribute(
						"panForDelete");

				responseCode = getSpringWSSoapBindingStub()
						.inactivatePanInBlackList(pan);
				
				Tracer.traceOut(Tracer.Tracing_Level.INFO, "BlackListManagementAction",
						"DeleteOK", " User : " + customer.getUserId()
								+ " ResponseCode : " + responseCode);
				String desc = Config.getPropertyFromBundle("DeleteBlackListDescription",language,country);
				desc = AmbitUtility.addAttributes(desc,
						new Object[] { pan });
				getSpringWSSoapBindingStub().logDeleteFromBlackListActivity(
						customer.getUserId(),desc);

				if (!AmbitUtility.isSuccessResponseCode(request, responseCode,
						"")) {

					return ("failure");
				}
				request.setAttribute("selectedPan", pan);
				refresh("", fromDate, toDate, "" ,request);
				MessagesUtility.AddGlobalMessageToActionMessages(request,
						"blackList.deleteIsOk");

			}

		} catch (Exception e) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"general.operationfailed");
			
			Tracer.exception("BlackListManagementAction", "OperationOK",
					"Exception occured while OperationOK : ", e);

			path = "failure";

		}

		return (path);

	}

	public String operationCancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		request.getSession().setAttribute("method", "operationCancel");
		String path = "cancelOperation";

		try {

			DynaValidatorActionForm blackListForm = (DynaValidatorActionForm) form;
			
			EscapeInputUtility.escapeInput(blackListForm);

			blackListForm.set("pan", "");
			request.getSession().setAttribute("TOTAL_PARAM", null);
			request.getSession().setAttribute("blackList", null);
			String latinFromDate = "";
			String latinToDate = "";
			String activeFlag = "";
			String pan = "";

			DateFarsi dateFarsi = new DateFarsi();

			String responseCode = "";

			latinFromDate = (String) request.getSession().getAttribute(
					"latinFromDate");
			latinToDate = (String) request.getSession().getAttribute(
					"latinToDate");
			pan = (String) request.getSession().getAttribute("pan");

			if ((!AmbitUtility.isEmpty(latinFromDate))
					&& !(AmbitUtility.isEmpty(latinToDate))
					|| (!AmbitUtility.isEmpty(pan))) {

				// search to fill the grid with last parameters
				ObjectHolder list = new ObjectHolder();
				responseCode = getSpringWSSoapBindingStub().searchBlackList(
						latinFromDate, latinToDate, pan, activeFlag ,list);
				BlackList[] blackList = (BlackList[]) org.apache.axis.utils.JavaUtils
						.convert(list.value, BlackList[].class);

				if (!AmbitUtility.isSuccessResponseCode(request, responseCode,
						"")) {

					return ("failure");
				}

				for (int i = 0; i < blackList.length; i++) {
					((BlackList) blackList[i]).setBlackedDate(dateFarsi
							.formatFdate(dateFarsi
									.LDateConv(((BlackList) blackList[i])
											.getBlackedDate())));

					((BlackList) blackList[i]).setBlackedTime(dateFarsi
							.formatTime(((BlackList) blackList[i])
									.getBlackedTime()));

				}

				request.getSession().setAttribute("print",
						Constants.BLACK_LIST_MANAGE);
				request.getSession().setAttribute("printList", blackList);
				request.getSession().setAttribute("blackList", blackList);

			}
		} catch (Exception e) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"general.operationfailed");
			Tracer.exception("BlackListManagementAction", "operationCancel",
					"Exception occured while operationCancel : ", e);
			path = "failure";

		}

		return (path);

	}

	public String searchPan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		Customer customer = (Customer) request.getSession().getAttribute(
				Constants.CUSTOMER_IN_SESSION);
		String language = getLocale(request).getLanguage();
		String country = getLocale(request).getCountry();
		String responseCode = "";
		String path = "searchPan";

		request.getSession().setAttribute("TOTAL_PARAM", null);

		try {

			DynaValidatorActionForm blackListForm = (DynaValidatorActionForm) form;
			
			EscapeInputUtility.escapeInput(blackListForm);

			String pan = blackListForm.getString("pan");

			if (AmbitUtility.isEmpty(pan)) {
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"blackList.error.pan.Empty");
				request.getSession().setAttribute("blackList", null);
				return ("failure");

			}

			if (!AmbitUtility.isEmpty(pan)) {
				if (!((pan.substring(0, 6)).equalsIgnoreCase(Config
						.getProperty("ACQUIRER_BANK_IMD")))) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
							"blackList.error.pan.invalid");
					request.getSession().setAttribute("blackList", null);
					return ("failure");
				}
				
				if (!AmbitUtility.isNumeric(pan, 19)) {
					ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"errors.invalid.data");
					return ("failure");
				}
				
				pan = AmbitUtility.addTrailing(pan,19,"0");	
			}

			CardInfoHolder cardInfoHolder = new CardInfoHolder();
			CardInfo cardInfo = new CardInfo();
			responseCode = getSpringWSSoapBindingStub().searchPanInformation(
					pan, cardInfoHolder);
			
			
			Tracer.traceOut(Tracer.Tracing_Level.INFO, "BlackListManagementAction",
					"SearchPan", " User : " + customer.getUserId()
							+ " ResponseCode : " + responseCode);
			String desc =  Config.getPropertyFromBundle("SearchPanInformationDescription",language,country);
			desc = AmbitUtility.addAttributes(desc, new Object[] {pan});
			
			 getSpringWSSoapBindingStub().logSearchPanInformationActivity
			 (customer.getUserId(),desc);
			 
			 if (!AmbitUtility.isSuccessResponseCode(request, responseCode, "")) {
					request.getSession().setAttribute("blackList", null);
					return ("failure");
				}

			cardInfo = (CardInfo) org.apache.axis.utils.JavaUtils.convert(
					cardInfoHolder, CardInfo.class);

			

			String totalParam = "";

			totalParam =  AmbitUtility
							.nvl((String) cardInfo.getNameAndFamilyName())
					+ "  "
					+ AmbitUtility.nvl((String) cardInfo.getCardStatusDesc());

			request.getSession().setAttribute("TOTAL_PARAM", totalParam);

			
		} catch (Exception e) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"general.operationfailed");
			
			Tracer.exception("BlackListManagementAction", "SearchPan",
					"Exception occured while Search Pan : ", e);

			path = "failure";

		}

		return (path);

	}

	private void refresh(String pan, String fromDate, String toDate,String activeFlag,
			HttpServletRequest request)

	{

		String responseCode = "";

		request.getSession().setAttribute("TOTAL_PARAM", null);
		request.getSession().setAttribute("method", "search");
		String selectedPan = (String) request.getAttribute("selectedPan");
		try {
			Customer customer = (Customer) request.getSession().getAttribute(
					Constants.CUSTOMER_IN_SESSION);
			DateFarsi dateFarsi = new DateFarsi();
			ObjectHolder list = new ObjectHolder();
			responseCode = getSpringWSSoapBindingStub().searchBlackList(
					fromDate, toDate, pan,activeFlag ,list);
			Tracer.traceOut(Tracer.Tracing_Level.INFO,
					"BlackListManagementAction", "Refresh", " User : "
							+ customer.getUserId() + " ResponseCode : "
							+ responseCode);
			BlackList[] blackList = (BlackList[]) org.apache.axis.utils.JavaUtils
					.convert(list.value, BlackList[].class);

			for (int i = 0; i < blackList.length; i++) {
				if (blackList[i].getPan().equalsIgnoreCase(selectedPan)) {
					blackList[i]
							.setHighlight(Constants.CODE_ACTIVE_FLAG.ACTIVE);
				}

				((BlackList) blackList[i]).setBlackedDate(dateFarsi
						.formatFdate(dateFarsi
								.LDateConv(((BlackList) blackList[i])
										.getBlackedDate())));

				((BlackList) blackList[i])
						.setBlackedTime(dateFarsi
								.formatTime(((BlackList) blackList[i])
										.getBlackedTime()));

			}

			request.getSession().setAttribute("blackList", blackList);
			request.getSession().setAttribute("printList", blackList);

			request.getSession().setAttribute("print",
								Constants.BLACK_LIST_MANAGE);

		} catch (Exception e) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"general.operationfailed");
			Tracer.exception("BlackListManagementAction", "refresh",
					"Exception occured while refresh : ", e);

		}

	}

	public String limits(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String responseCode = "";
		String path = "limits";
		String allowedTerminalType = "";
		String allowedTrans = "";
		String terminals = Constants.TERMINALS;
		String services = Constants.SERVICES;

		try {

			DynaValidatorActionForm blackListForm = (DynaValidatorActionForm) form;
			
			EscapeInputUtility.escapeInput(blackListForm);
			
			CardInfoHolder cardInfoHolder = new CardInfoHolder();
			CardInfo cardInfo = new CardInfo();
			String selectedPan = blackListForm.getString("selectedPan");

			if (AmbitUtility.isEmpty(selectedPan)) {
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"blackList.panSelectedIsEmpty");

				request.getSession().setAttribute("method", "search");

				return ("failure");
			}
			
			if (!AmbitUtility.isNumeric(selectedPan, 19)) {
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
				"errors.invalid.data");
				return ("failure");
			}

			request.getSession().setAttribute("nameAndFamilyName", "");
			request.getSession().setAttribute("cardStatus", "");
			request.getSession().setAttribute("cardNO", "");

			responseCode = getSpringWSSoapBindingStub().searchPanInformation(
					selectedPan, cardInfoHolder);

			if (!AmbitUtility.isSuccessResponseCode(request, responseCode, "")) {
				request.getSession().setAttribute("blackList", null);
				return ("failure");
			}

			cardInfo = (CardInfo) org.apache.axis.utils.JavaUtils.convert(
					cardInfoHolder, CardInfo.class);

			request.getSession().setAttribute("nameAndFamilyName",
					(String) cardInfo.getNameAndFamilyName());
			request.getSession().setAttribute("cardStatus",
					(String) cardInfo.getCardStatusDesc());
			request.getSession().setAttribute("cardNO", selectedPan);

			ObjectHolder obj = new ObjectHolder();
			responseCode = getSpringWSSoapBindingStub().searchPanInBlackList(
					selectedPan, obj);

			if (!AmbitUtility.isSuccessResponseCode(request, responseCode, "")) {
				request.getSession().setAttribute("blackList", null);
				return ("failure");
			}

			BlackList bl = (BlackList) org.apache.axis.utils.JavaUtils.convert(
					obj.value, BlackList.class);

			allowedTerminalType = bl.getAllowedTerminalTypes();
			allowedTrans = bl.getAllowedTrans();

			extract(request, allowedTerminalType, terminals);
			extract(request, allowedTrans, services);

			request.setAttribute("BLACKLIST", bl);

			Customer customer = (Customer) request.getSession().getAttribute(
					Constants.CUSTOMER_IN_SESSION);
			String pagePermission = Config.getProperty("ChangeLimit");
			String allowedActions = AmbitUtility.getAllowedActions(
					pagePermission, customer.getAccess());

			request.getSession().setAttribute("allowedActions", allowedActions);

		} catch (Exception e) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"general.operationfailed");
			
			Tracer.exception("BlackListManagementAction", "limits",
					"Exception occured while limits : ", e);

			path = "failure";

		}

		return (path);

	}

	private void extract(HttpServletRequest request, String note,
			String titleConfig) {
		String data[] = null;
		String temp = "";

		int vectorPointer = 0;
		if (!StringUtils.isEmpty(note)) {

			data = ((String) note).split(";");

			for (int i = 0; i < data.length; i++) {

				temp = data[i].toString();
				Vector vt = (Vector) AmbitUtility
						.getNameAndIdConfig(titleConfig);
				vectorPointer = 0;
				AmbitUtility.ImdsInfo imdInf = null;
				boolean Valid = false;
				for (; vectorPointer < vt.size(); vectorPointer++) {
					imdInf = (AmbitUtility.ImdsInfo) vt
							.elementAt(vectorPointer);
					if (temp.equalsIgnoreCase(imdInf.getId())) {

						Valid = true;
						vectorPointer++;
						break;
					}
				}

				if (Valid == true) {
					String name = imdInf.getName();
					// request.setAttribute(name,Constants.CODE_ACTIVE_FLAG.ACTIVE);
					request.setAttribute(name, "checked=" + "" + imdInf.getId()
							+ "");

				}
			}

		}
	}

	public String transactions(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String path = "transactions";

		try {
			DynaValidatorActionForm blackListForm = (DynaValidatorActionForm) form;
			
			EscapeInputUtility.escapeInput(blackListForm);

			String selectedPan = blackListForm.getString("selectedPan");

			request.getSession().setAttribute("hours", Constants.hours);

			if (AmbitUtility.isEmpty(selectedPan)) {
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
						"blackList.panSelectedIsEmpty");

				request.getSession().setAttribute("method", "search");

				return ("failure");
			}
			

			
			

			request.setAttribute("selectedPan", selectedPan);

		} catch (Exception e) {
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"general.operationfailed");
			Tracer.exception("BlackListManagementAction", "transactions",
					"Exception occured while transactions : ", e);
			path = "failure";

		}

		return (path);

	}

	protected Map getKeyMethodMap() {
		Map map = new HashMap();

		map.put("blackList.search", "search");
		map.put("blackList.add", "add");
		map.put("blackList.update", "edit");
		map.put("blackList.delete", "delete");
		map.put("blackList.cancel", "operationCancel");
		map.put("blackList.submit", "operationOk");
		map.put("blackList.limits", "limits");
		map.put("blackList.transactions", "transactions");
		map.put("blackList.searchPan", "searchPan");

		return map;

	}

}
