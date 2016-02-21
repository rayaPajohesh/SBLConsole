package com.iac.ambit.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.struts.action.ActionServlet;
import org.springframework.web.struts.DelegatingActionUtils;

//import org.springframework.web.struts.DelegatingActionUtils;

public class ContextUtils {

	private static ActionServlet servlet;
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
	public static Object getBean(String sBean) {
		return DelegatingActionUtils.findRequiredWebApplicationContext(servlet,
				null).getBean(sBean);

	}

	public static void setBean(ActionServlet actionServlet) {
		servlet = actionServlet;
	}
}
