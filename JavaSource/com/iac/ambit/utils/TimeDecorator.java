package com.iac.ambit.utils;

import org.displaytag.decorator.ColumnDecorator;

import org.displaytag.exception.DecoratorException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;


/**
 *
 * @author Hashir Ahmed
 */
public class TimeDecorator implements ColumnDecorator
{
    private SimpleDateFormat timeFormatter;
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
    public TimeDecorator (  )
    {
        timeFormatter = new SimpleDateFormat( Config.getProperty( "timeDecorator.timeFormat" ) );

    } 

    public String decorate ( Object expectedDateTime )
        throws DecoratorException
    {
        if ( expectedDateTime != null )
        {
        	DateFarsi fDate = new DateFarsi();
            return fDate.formatTime( expectedDateTime.toString() );

        }

        return "";

    }

}
