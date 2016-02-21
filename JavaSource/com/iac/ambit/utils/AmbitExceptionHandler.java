package com.iac.ambit.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.iac.ambit.utils.Tracer;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Hashir Ahmed
 */
public class AmbitExceptionHandler extends ExceptionHandler
{
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
    public ActionForward execute ( 
        Exception ex, ExceptionConfig ae, ActionMapping mapping, ActionForm formInstance,
        HttpServletRequest request, HttpServletResponse response )
        throws ServletException
    {
        
        Tracer.exception( "AmbitExceptionHandler", "execute", ex );

        if ( request.getSession().getAttribute( Constants.CUSTOMER_IN_SESSION ) != null ) {        
        	return mapping.findForward( "exerror" );
        }
        else {
        	return mapping.findForward( "publicError" );
        }

    }

}
