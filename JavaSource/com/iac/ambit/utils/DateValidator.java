package com.iac.ambit.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.Globals;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import org.apache.struts.validator.DynaValidatorForm;
import org.apache.struts.validator.Resources;
/**
 * This class contains the Date comparison validation that is used in the 
 * validator-rules.xml file. It was made due to some limitations in validWhen 
 * function.
 *
 * @since Struts 1.2
 */
 
public class DateValidator 
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
    /**
     * Returns true if <code>obj</code> is null or a String.
     */
    private static boolean isString(Object obj) {
        return (obj == null) ? true : String.class.isInstance(obj);
    }

    /**
     * Checks if the field matches the boolean expression specified in 
     * <code>testaction</code> parameter.
     *
     * @param bean The bean validation is being performed on.
     * 
     * @param va The <code>ValidatorAction</code> that is currently being 
     *      performed.
     * 
     * @param field The <code>Field</code> object associated with the current
     *      field being validated.
     * 
     * @param errors The <code>ActionMessages</code> object to add errors to if any
     *      validation errors occur.
     * 
     * @param request Current request object.
     * 
     * @return <code>true</code> if meets stated requirements, 
     *      <code>false</code> otherwise.
     */
    public static boolean validateValidWhen(
        Object bean,
        ValidatorAction va,
        Field field,
        ActionMessages errors,
        Validator validator,
        HttpServletRequest request) {
        
        ActionMapping maping = (ActionMapping)request.getAttribute(Globals.MAPPING_KEY);
        String formName = maping.getName();
        Object form = request.getAttribute(formName);
        DynaValidatorForm dynaVdForm = (DynaValidatorForm)form;
        
        String value = null;
        boolean valid = false;
        
        if (isString(bean)) {
            value = (String) bean;
        } else {
            value = ValidatorUtils.getValueAsString(bean, field.getProperty());
        }
        
        String comparableValue = (String)dynaVdForm.get( field.getVarValue("comparableDateControl") );
        
        if (value!=null && comparableValue!=null && !value.equals("") && !comparableValue.equals(""))
        {
          Date originalDate = new Date(DateUtils.stringToDatetime(value, DateUtils.DATE_FORMAT).getTime());
          Date comparableDate = new Date(DateUtils.stringToDatetime(comparableValue, DateUtils.DATE_FORMAT).getTime());        
          
          String test = field.getVarValue("testAction");
          if (test == null) {
              test = "<";
          }        
  
          try {
          
            if (test.equals("<"))
              valid = originalDate.before(comparableDate);
            
            if (test.equals(">"))
              valid = originalDate.after(comparableDate);          
            
            if (test.equals("="))
              valid = originalDate.equals(comparableDate);
            
            if (test.equals("<="))
              valid = (!originalDate.after(comparableDate));
  
            if (test.equals(">="))
              valid = (!originalDate.before(comparableDate));
              
          } catch (Exception ex) {
              ex.printStackTrace();
              
              errors.add(
                  field.getKey(),
                  Resources.getActionMessage(request, va, field));
                  
              return false;
          }
          
          if (!valid) {
              errors.add(
                  field.getKey(),
                  Resources.getActionMessage(request, va, field));
                  
              return false;
          }
        }
        else
        {
          return true;
        }
        return valid;
    }
    
}
