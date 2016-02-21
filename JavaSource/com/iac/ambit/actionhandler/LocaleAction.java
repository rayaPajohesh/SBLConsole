package com.iac.ambit.actionhandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.iac.ambit.utils.Constants;

import com.iac.ambit.utils.Config;
//jazimagh 1386/05/17
public class LocaleAction extends Action{

	
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
		        ActionMapping mapping, ActionForm form, HttpServletRequest request,
		        HttpServletResponse response ) throws IOException, ServletException
		    {
		   
		   
		   String path = "success";
		  
		  
		   
		   String language = (String) request.getParameter("language");
		   String country ="";
		   if (language == null || language.equals("")){
			   language = Config.getProperty( "language.Persian" );
			  // language = locale.getLanguage();
		   }
		   String pathCss="css/";
		   String pathImages="images/";
		  
		   
		   if(language.equals(Config.getProperty( "language.Persian" ).toString())){
			   country = Config.getProperty( "country.Iran" );
			  
			   setLocale(request, new Locale(language, country));
			   request.getSession().setAttribute( Constants.JspConstants.PATH_CSS , pathCss +  language + "_" + country + "/" );
			   request.getSession().setAttribute( Constants.JspConstants.PATH_IMAGE , pathImages + language + "_" + country + "/" );
			   request.getSession().setAttribute( Constants.JspConstants.PAGE_DIR  , Constants.JspConstants.RIGHT_DIRECTION );
			   
			  
			  
		   }
		   else{ 
			   country = Config.getProperty( "country.America") ;
			   
			   setLocale(request, new Locale(language, country));
			   request.getSession().setAttribute( Constants.JspConstants.PATH_CSS , pathCss +  language + "_" + country + "/" );
			   request.getSession().setAttribute( Constants.JspConstants.PATH_IMAGE , pathImages + language + "_" + country + "/" );
			   request.getSession().setAttribute( Constants.JspConstants.PAGE_DIR  , Constants.JspConstants.LEFT_DIRECTION );
				
			   
			   			   
		   }
		   		request.getSession().setAttribute("struts_action_language",language);
		   		
		  
		       
		        return mapping.findForward( path );
		    }
		
		
		
	
		
}
//jazimagh 1386/05/17

