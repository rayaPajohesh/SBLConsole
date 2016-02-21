/*
 * Created on Oct 24, 2005
 *
 */
package com.iac.ambit.utils;

/**
 * @author ahashir
 *
 */
public aspect LoggingAspect {
	
    
    // Logging Aspect has precedence over Escaping Aspect
	declare precedence: LoggingAspect,*;
    
    /**
     * Application classes
     */
	 pointcut myClass(): 
		 
		(within(com.iac.ambit.actionhandler.*)) ||
		(within(com.iac.ambit.service.*)) ||		
		(within(proxy.localhost.SBLService.axis.SpringWS.*));
		

    /**
     * The methods of those classes.
     */
    pointcut myMethod(): myClass() && 
        execution(* *(..));

    before(): myMethod() {
    	Tracer.enteringMethod(thisJoinPointStaticPart.getSignature().toString());
    }
    after(): myMethod() {
    	Tracer.exitingMethod(thisJoinPointStaticPart.getSignature().toShortString());
    }
}
