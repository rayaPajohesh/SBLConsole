/*
 * Created on Jan 6, 2006
 * 
 */
package com.iac.ambit.utils;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * @author ahashir
 * 
 */
public aspect InputEscapingAspect {
	pointcut actionHandlers(): within (com.iac.ambit.actionhandler.*) 
							   && execution(public ActionForward *.execute(..));

	before():actionHandlers (){
		Object form = thisJoinPoint.getArgs()[1];
		if (form != null && form instanceof DynaValidatorForm) {
			DynaValidatorForm dynaForm = (DynaValidatorForm) form;
			EscapeInputUtility.escapeInput(dynaForm);
		}

	}
}
