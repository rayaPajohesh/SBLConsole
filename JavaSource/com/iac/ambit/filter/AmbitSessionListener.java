package com.iac.ambit.filter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;



import proxy.com.iac.ambit.model.Customer;
import com.iac.ambit.utils.Constants;
import com.iac.ambit.utils.SessionUtils;
import com.iac.ambit.utils.Tracer;
import com.iac.ambit.utils.AmbitUtility;

public class AmbitSessionListener implements javax.servlet.http.HttpSessionListener
{
    private HttpSession session = null;
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
    public void sessionCreated ( HttpSessionEvent event )
    {

        //session = event.getSession(  );
    }

    public void sessionDestroyed ( HttpSessionEvent event )
    {
        session = event.getSession(  );

        if ( session.getAttribute( Constants.CUSTOMER_IN_SESSION ) != null )
        {
            Customer customer = (Customer) session.getAttribute( Constants.CUSTOMER_IN_SESSION );

            SessionUtils.logOutCustomer( customer );
            Tracer.traceOut(Tracer.Error_Level, "AmbitSessionListener", "execute",
					"TimeOut Customer : "
							+ customer.getUserId() );
            Tracer.traceOut(Tracer.Error_Level, "AmbitSessionListener", "execute",
					"Date Time : "
							+ AmbitUtility.getCurrentDate() + "	" + AmbitUtility.getCurrentTime() );            

        }
       

    }

}
