package com.iac.ambit.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;


import javax.servlet.ServletException;
import javax.xml.rpc.holders.ObjectHolder;
import com.iac.ambit.utils.Constants;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.springframework.web.struts.DelegatingActionUtils;


import proxy.localhost.SBLService.axis.SpringWS.SpringWSSoapBindingStub;

//import com.iac.ambit.dbutil.DBConnection;
//import com.iac.ambit.integration.PhoenixMessageSource;







public class AmbitInitializationPlugin implements PlugIn {
	
	String configFilePath;
	String dbConfigFilePath;
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
	public AmbitInitializationPlugin() {

	}

	public void destroy() {
		//DBConnection.destroy();
		//ClientClass.destroy();
	//	RDVMessageSource.closeConnection();

	}

	/**
	 * Initialize all resources needed by ambit
	 * 
	 * @throws javax.servlet.ServletException
	 * @param config
	 * @param servlet
	 */
	public void init(ActionServlet servlet, ModuleConfig config)
			throws ServletException {
		try {
			Tracer.enteringMethod("AmbitInitializationPlugin", "init");
			
			// This is to store reference to action servlet. So application context can be accessed.
			ContextUtils.setBean(servlet);
			SpringWSSoapBindingStub proxyService = (SpringWSSoapBindingStub) DelegatingActionUtils
			.findRequiredWebApplicationContext(servlet, null).getBean(
					"springWSSoapBindingStub");
			
		
			
			
				Config.setResourceBundle(servlet);
				
				if (Config.getPropertyAsBoolean("ambit.uri.checking")) {
					ObjectHolder obj = new ObjectHolder();
					String responseCode = proxyService.loadSysPermissionURIs(obj);
					if(Constants.RESPONSE_CODE.SUCCEED.equalsIgnoreCase(responseCode)){
					Map result = (Map)org.apache.axis.utils.JavaUtils.convert(obj.value,Map.class);
					servlet.getServletContext().setAttribute(
							
							Constants.SYSTEM_PERMISSION_URI_MAP, result);
					}
					else{
						Tracer.traceOut(Tracer.Tracing_Level.ERROR,
								"AmbitInitializationPlugin", "init",
								" User : null"  + " ResponseCode : "
										+ responseCode);
					}
					
					
				}
		} catch (Exception ex) {
			
			Tracer.exception("AmbitInitializationPlugin", "init()", ex);
			//throw new AmbitException(getClass().getName(), "init", ex);

		}
		
		Tracer.exitingMethod("AmbitInitializationPlugin", "init");

	}

}
