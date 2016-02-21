package com.iac.ambit.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.displaytag.decorator.ColumnDecorator;
import org.displaytag.exception.DecoratorException;

public class DateSeperatorDecorator implements ColumnDecorator {

	public final Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}	
	private final void writeObject(ObjectOutputStream out) throws IOException{
		throw new IOException("Object cannot be serialized");
	}	
	private final void readObject(ObjectInputStream in) throws IOException{
		throw new IOException ("Class cannot be Deserialized");
	}	
 	
	public DateSeperatorDecorator() {

	}

	
	public String decorate(Object dateTime) throws DecoratorException {
		if (dateTime != null)

		{ 
		DateFarsi fDate = new DateFarsi();	
		String dateTimeSt = dateTime.toString();	
		String date = dateTimeSt.substring(0,8);
		
			
		
			return fDate.formatedLDateConv(date);
			
		}

		return "";

	}

	

}



