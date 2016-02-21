package com.iac.ambit.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.iac.ambit.utils.Constants;

import cryptix.util.core.Hex;

public class EncryptionServiceImpl implements EncryptionService {

	public final Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}	
	private final void writeObject(ObjectOutputStream out) throws IOException{
		throw new IOException("Object cannot be serialized");
	}	
	private final void readObject(ObjectInputStream in) throws IOException{
		throw new IOException ("Class cannot be Deserialized");
	}	
	private String sKey = Constants.INTERNAL_Key;
	public String Encrypt(String sData){
		 byte[] encryptedData ;
	        String sEncryptedData = "";
	        try {
	        	 sData = toHexStringAddPadding(sData);

	            Cipher algorithm = Cipher.getInstance("DES/ECB/NoPadding");
	            Key key = new SecretKeySpec( (new DESKeySpec(Hex.fromString(sKey))).getKey(), "DES" );
	            algorithm.init(Cipher.ENCRYPT_MODE, key);
	            byte[] temparr = Hex.fromString(sData.substring(0, sData.length()));
	            encryptedData = algorithm.doFinal(temparr, 0, temparr.length);
	            sEncryptedData =  Hex.toString(encryptedData);
	            //Tracer.traceOut(Tracer.Tracing_Level,MODULE_NAME,sMethod,"Encrypted Data:"+sEncryptedData);

	        } catch (Exception e) {
	            System.out.println("Error Occured :" + e);
	            e.printStackTrace();
	        }

	        return sEncryptedData;
	}
	
	public String Decrypt(String sData){
	    byte[] decryptedData ;
        String sDecryptedData = "";
        try {

            Cipher algorithm = Cipher.getInstance("DES/ECB/NoPadding");
            Key key = new SecretKeySpec( (new DESKeySpec(Hex.fromString(sKey))).getKey(), "DES" );
            algorithm.init(Cipher.DECRYPT_MODE, key);

            decryptedData = algorithm.doFinal(Hex.fromString(sData));
            sDecryptedData = Hex.toString(decryptedData);

            //Tracer.traceOut(Tracer.Tracing_Level,MODULE_NAME,sMethod,"Decrypted Data:"+sDecryptedData);

        } catch (Exception e) {
            System.out.println("Error Occured :" + e);
            //throw new ContactCenterException(MODULE_NAME,sMethod,e.getMessage());
            e.printStackTrace();
        }


        //Tracer.traceOut(Tracer.Tracing_Level,MODULE_NAME,sMethod,"End");
        return sDecryptedData;
	}
	
	  public static String toHexStringAddPadding(String input) 
	    {
	      String hexString = "";
	      int integerTemp;
	      for (int i=0; i<input.length(); i++) 
	      {
	          integerTemp = (int)input.charAt(i);
	          hexString += Integer.toHexString(integerTemp);
	      }
	      
	      int padingRequired = hexString.length() % 16;
	      if (padingRequired != 0) {
	        padingRequired = 16 - padingRequired;      
	        for (int y=0; y<padingRequired; y++) 
	        {
	            hexString += "0";
	        }
	      }
	      
	      return hexString;
	    }	  
	
	
}
