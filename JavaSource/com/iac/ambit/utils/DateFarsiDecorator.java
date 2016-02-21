package com.iac.ambit.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.displaytag.decorator.ColumnDecorator;

import org.displaytag.exception.DecoratorException;

import com.iac.ambit.utils.DateFarsi;

public class DateFarsiDecorator implements ColumnDecorator {
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
	public DateFarsiDecorator() {

	}

	// jazimagh:1386/05/22
	public String decorate(Object expectedDateTime) throws DecoratorException {
		if (expectedDateTime != null)

		{
			DateFarsi fDate = new DateFarsi();
		
			return fDate.formatedLDateConv(expectedDateTime.toString());
			
		}

		return "";

	}

	// jazimagh:1386/05/22

}
