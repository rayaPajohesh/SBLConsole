package com.iac.ambit.utils;
   
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.dbcp.BasicDataSource;




// this class used in Application-Context
public class SecureDriverManagerDataSource extends BasicDataSource  {
//	jazimagh : 1386/07/16
	private final void writeObject(ObjectOutputStream out) throws IOException{
		throw new IOException("Object cannot be serialized");
	}	
	private final void readObject(ObjectInputStream in) throws IOException{
		throw new IOException ("Class cannot be Deserialized");
	}
//	jazimagh : 1386/07/16 
	
	//private  DESEncryptionService encryptionService;
	public SecureDriverManagerDataSource() {
	}

	public synchronized String getPassword() {
		return super.getPassword();

	}

	public synchronized void setPassword(String password) {
	/*	encryptionService = new DESEncryptionService();
		
		String noneEncrypted = this.encryptionService.DecryptWithDesKey(Constants.INTERNAL_Key,password);
		
		super.setPassword(noneEncrypted);
		encryptionService = null;*/
	}



}
