package com.iac.ambit.filter;

import proxy.com.iac.ambit.model.Customer;

import com.iac.ambit.utils.Config;
import com.iac.ambit.utils.Constants;
import com.iac.ambit.utils.ContextUtils;
import com.iac.ambit.utils.Tracer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.HashSet;

import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.holders.BooleanHolder;


import proxy.localhost.SBLService.axis.SpringWS.SpringWSSoapBindingStub;

public class LoginFilter implements Filter {
	private FilterConfig _filterConfig = null;

	private HashSet insecurePages = null;

	private HashSet secureFileType = null;


	private String loginHandler = null;

	
	private String logoutHandler = null;

	private String localeHandler = null;
	

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
	public void init(FilterConfig filterConfig) throws ServletException {
		_filterConfig = filterConfig;

				
		this.loginHandler = _filterConfig
				.getInitParameter(Constants.LOGIN_HANDLER);
		
		this.logoutHandler = _filterConfig
				.getInitParameter(Constants.LOGOUT_HANDLER);
		this.localeHandler = _filterConfig
				.getInitParameter(Constants.LOCALE_HANDLER);
		
		

		String strInsecurePages = _filterConfig
				.getInitParameter(Constants.INSECURE_PAGES);
		String strSecureFileType = _filterConfig
				.getInitParameter(Constants.SECURE_FILETYPE);

		StringTokenizer tokenizer = new StringTokenizer(strInsecurePages, ",",
				false);
		this.insecurePages = new HashSet(tokenizer.countTokens());

		while (tokenizer.hasMoreTokens()) {
			this.insecurePages.add(tokenizer.nextToken());

		}

		tokenizer = new StringTokenizer(strSecureFileType, ",", false);
		this.secureFileType = new HashSet(tokenizer.countTokens());

		while (tokenizer.hasMoreTokens()) {
			this.secureFileType.add(tokenizer.nextToken());

		}

	}

	public void destroy() {
		this._filterConfig = null;
		this.secureFileType = null;
		this.insecurePages = null;
		

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = null;
		HttpServletRequest req = null;
		RequestDispatcher rd = null;

		if (request instanceof HttpServletRequest) {
			req = (HttpServletRequest) request;
			res = (HttpServletResponse) response;
			if (req.getMethod().equalsIgnoreCase("TRACE") ||
					req.getMethod().equalsIgnoreCase("PUT") || 
					req.getMethod().equalsIgnoreCase("OPTIONS")
					 || req.getMethod().equalsIgnoreCase("DELETE")) {
				
				Tracer.traceOut(Tracer.Tracing_Level,
						"LoginFilter",
						"LoginFilter",
						"req.getMethod() : " + req.getMethod());
				
			rd = req.getRequestDispatcher(logoutHandler);
			rd.forward(req, res);

			return;
			}
			req.setCharacterEncoding("UTF-8");	
			
			String uri = req.getRequestURI();
			
			

	       String path = req.getContextPath();

			String targetURI = uri.substring(path.length(), uri.length());
			String target = targetURI.toLowerCase();					
			
			String fileType = null;
			int index = target.indexOf(".");
			if (index >= 0) {
				fileType = target.substring(index);

			}			
			///Jazimagh : 1388/10/21
		     res.addHeader(
							"Cache-Control",
							"no-chache, no-store, must-revalidate, max-age=0, proxy-revalidate, no-transform, pre-check=0, post-check=0, private");
		     
		     String sessionid = req.getSession().getId();
				//sarmadi added 1391/12/22
			
				res.addHeader("SET-COOKIE", "JSESSIONID=" + sessionid + ";path=/"+"; secure; HttpOnly");
		    

					req.getSession().setAttribute(
							"org.apache.struts.action.LOCALE",
							new Locale(Config.getProperty("language.Persian")
									.toString(), Config.getProperty(
									"country.Iran").toString()));
				
					  
					com.iac.ambit.utils.AmbitUtility.setLocalePaths(req);
			if ((req.getSession().getAttribute(Constants.CUSTOMER_IN_SESSION) == null)) {

	
				if (this.secureFileType.contains(fileType)
						&& !this.insecurePages.contains(targetURI)) {

					// Check to see if the path is of admin module
					
						if ((req.getSession().getAttribute(
								Constants.CUSTOMER_IN_SESSION) == null)
								&& !targetURI.equalsIgnoreCase(loginHandler)) {
							
							
							
							if (targetURI.equalsIgnoreCase(localeHandler)) {
								rd = req.getRequestDispatcher(localeHandler);
							} else {
								rd = req.getRequestDispatcher(logoutHandler);
							}
							
							rd.forward(req, res);

							return;

						} else {
							if (Config
									.getPropertyAsBoolean("ambit.uri.checking")) {
								if (!this.hasPermission(req)) {
									 rd = req
											.getRequestDispatcher(logoutHandler);

									rd.forward(req, res);

									return;
								}
								
							}
						}

					
				}

			}

		}

		chain.doFilter(request, response);

	}

    private boolean hasPermission(HttpServletRequest request)
    {
		
    	Customer customer = (Customer)request.getSession().getAttribute("CUSTOMER_IN_SESSION");
		
	/*	SysPermissionService sysPemService = (SysPermissionService) ContextUtils.
			getBean("sysPermissionService");*/



        String uri = request.getRequestURI(  );
        String path = request.getContextPath(  );

        String targetURI = uri.substring( path.length(  ), uri.length(  ) );
        
        targetURI = targetURI.substring(targetURI.lastIndexOf('/'));
		boolean flag = true;
		String responseCode="";
		Map uriMap = (Map)request.getSession().getServletContext().getAttribute(Constants.SYSTEM_PERMISSION_URI_MAP);
		if( uriMap==null){
			
			return flag;
		}
		if ( uriMap.containsKey(targetURI.substring(1) ) ) {
			SpringWSSoapBindingStub proxyService = (SpringWSSoapBindingStub) ContextUtils.
			getBean("springWSSoapBindingStub");
			
				BooleanHolder obj = new BooleanHolder();
				try{
				responseCode = proxyService.isPermissionAvailableToCustomer(customer.getUserId(),(String)uriMap.get(targetURI.substring(1)),obj);
				if(Constants.RESPONSE_CODE.SERVICE_PROVIDER_NOT_AVAILABLE.equalsIgnoreCase(responseCode))
					flag=false;
				else
				flag = obj.value;
				}
				catch (Exception e){
			
				Tracer.exception("WebServiceMethods","LoginFilter",
						"isPermissionAvailableToCustomer", e);
				e.printStackTrace();
				}
			
    	}
		
		return flag;
    }
}
