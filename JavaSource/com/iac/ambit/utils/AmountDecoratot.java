package com.iac.ambit.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.displaytag.decorator.ColumnDecorator;


/**
 *
 * @author Hashir Ahmed
 */
public class AmountDecoratot implements ColumnDecorator
{
    private NumberFormat numberFormatter;
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
    public AmountDecoratot (  )
    {
        this.numberFormatter = new DecimalFormat(  );
        //roohi:1388/01/22
        this.numberFormatter.setMinimumFractionDigits(Integer.parseInt( Config.getProperty("amount.WithoutFractionDidits")));
        this.numberFormatter.setMaximumFractionDigits( Integer.parseInt( Config.getProperty("amount.WithoutFractionDidits")) );
     }

    public String decorate ( Object columnValue ) 
    {
       if ( !AmbitUtility.isEmpty(columnValue) )
        {
        	
        	if ( Double.parseDouble(columnValue.toString()) == 
        		Double.parseDouble(Config.getProperty("DUMMY_AC_BALANCE")) )
        	{
        		return "";
        	}
            return this.numberFormatter.format( Double.parseDouble(columnValue.toString()) );
        }

        return "";
    }
    public String decorate ( double columnValue ) 
    {
         	if ( columnValue == 
        		Double.parseDouble(Config.getProperty("DUMMY_AC_BALANCE")) )
        	{
        		return "";
        	}else{
            return this.numberFormatter.format( columnValue );}
  
    }
//  roohi added 890503
    public String decorateZeroShowing ( Object columnValue ) 
    {
       if ( !AmbitUtility.isEmpty(columnValue) )
        {
        	
        	if ( Double.parseDouble(columnValue.toString()) == 
        		Double.parseDouble(Config.getProperty("DUMMY_AC_BALANCE")) )
        	{
        		return "0";
        	}
            return this.numberFormatter.format( Double.parseDouble(columnValue.toString()) );
        }

        return "";
    }
}
