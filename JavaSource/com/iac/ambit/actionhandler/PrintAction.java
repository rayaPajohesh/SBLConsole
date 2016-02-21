package com.iac.ambit.actionhandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.iac.ambit.utils.Constants;

/**
 *
 * @author Hashir Ahmed
 */
public class PrintAction extends Action
{

	public final Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	private final void writeObject(ObjectOutputStream out) throws IOException {
		throw new IOException("Object cannot be serialized");
	}

	private final void readObject(ObjectInputStream in) throws IOException {
		throw new IOException("Class cannot be Deserialized");
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
    {
       
        String print = (String) request.getSession(  ).getAttribute( "print" );
        String path = "";

        if ( print.equalsIgnoreCase(Constants.BLACK_LIST_TRANS))
        {
        	path = "blackListTransReportPrint";
        }
        
        if ( print.equalsIgnoreCase(Constants.BLACK_LIST_MONITORING))
        {
        	path = "monitorTransactionsPrint";
        }
        
        
        if ( print.equalsIgnoreCase(Constants.BLACK_LIST_MANAGE))
        {
        	path = "blackListPrint";
        }

        return mapping.findForward( path );

    }

}
