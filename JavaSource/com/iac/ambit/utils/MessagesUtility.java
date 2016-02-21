package com.iac.ambit.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.StringBuffer;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import javax.servlet.http.HttpServletRequest;
import javax.crypto.Cipher;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;


import com.iac.ambit.utils.AmbitUtility;
import java.security.Key;
import java.util.Vector;
import java.util.ArrayList;
import java.util.List;



import com.iac.ambit.utils.Config;

import cryptix.util.core.Hex;

public class MessagesUtility {
	public static String ACTION_MESSAGES_KEY = Globals.MESSAGE_KEY;

	public final Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	private final void writeObject(ObjectOutputStream out) throws IOException {
		throw new IOException("Object cannot be serialized");
	}

	private final void readObject(ObjectInputStream in) throws IOException {
		throw new IOException("Class cannot be Deserialized");
	}

	private MessagesUtility() {

	}

	/**
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param name
	 *            of field to which error is associated
	 * @param key
	 *            to error message in applicationresources.properties
	 */
	public static void AddErrorToActionMessages(HttpServletRequest request,
			String fieldName, String msgResource) {
		ActionMessages aErrs = (ActionMessages) request
				.getAttribute(ACTION_MESSAGES_KEY);

		if (aErrs == null) {
			aErrs = new ActionMessages();

		}

		aErrs.add(fieldName, new ActionMessage(msgResource));
		request.setAttribute(ACTION_MESSAGES_KEY, aErrs);
		request.getSession().setAttribute(ACTION_MESSAGES_KEY, aErrs);
	}

	public static ActionMessages AddErrorToActionMessages_Return(
			HttpServletRequest request, String fieldName, String msgResource) {
		ActionMessages aErrs = (ActionMessages) request
				.getAttribute(ACTION_MESSAGES_KEY);

		if (aErrs == null) {
			aErrs = new ActionMessages();

		}

		aErrs.add(fieldName, new ActionMessage(msgResource));
		request.setAttribute(ACTION_MESSAGES_KEY, aErrs);
		request.getSession().setAttribute(ACTION_MESSAGES_KEY, aErrs);
		return aErrs;

	}

	public static ActionMessages ReturnActionMessages(HttpServletRequest request) {
		return (ActionMessages) request.getAttribute(ACTION_MESSAGES_KEY);

	}

	/**
	 * Add a message to global messages with replacements as an array of String
	 * 
	 * @param HTTPServlestRequest
	 * @param key
	 *            to error msg in ApplicationResource.properties
	 */
	public static void AddGlobalMessageToActionMessages(
			HttpServletRequest request, String msgResource, String[] value) {
		ActionMessages aErrs = (ActionMessages) request
				.getAttribute(ACTION_MESSAGES_KEY);

		if (aErrs == null) {
			aErrs = new org.apache.struts.action.ActionMessages();

		}

		aErrs.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(msgResource,
				value));
		request.setAttribute(ACTION_MESSAGES_KEY, aErrs);
		request.getSession().setAttribute(ACTION_MESSAGES_KEY, aErrs);
	}

	/**
	 * Add a message to global messages
	 * 
	 * @param msgResource
	 * @param request
	 */
	public static void AddGlobalMessageToActionMessages(
			HttpServletRequest request, String msgResource) {
		ActionMessages aErrs = (ActionMessages) request
				.getAttribute(ACTION_MESSAGES_KEY);

		if (aErrs == null) {
			aErrs = new org.apache.struts.action.ActionMessages();

		}

		aErrs
				.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						msgResource));
		request.setAttribute(ACTION_MESSAGES_KEY, aErrs);
		request.getSession().setAttribute(ACTION_MESSAGES_KEY, aErrs);
	}

	public static boolean hasErrors(HttpServletRequest request) {
		ActionMessages aErrs = (ActionMessages) request
				.getAttribute(ACTION_MESSAGES_KEY);

		if ((aErrs == null) || aErrs.isEmpty()) {
			return false;

		} else {
			return true;

		}

	}

	public static Vector MessagePattern(String MessageId) {

		Vector vtMessagePattern = new Vector();
		int fieldNo = Integer.parseInt(MessageConfig.getProperty(MessageId
				+ "." + "FieldNo"));
		for (int i = 0; i < fieldNo; i++) {
			vtMessagePattern.add(i, new FieldInfo(MessageConfig
					.getProperty(MessageId + "." + Integer.toString(i) + "."
							+ "Name"), MessageConfig.getProperty(MessageId
					+ "." + Integer.toString(i) + "." + "Len"), MessageConfig
					.getProperty(MessageId + "." + Integer.toString(i) + "."
							+ "Position"), MessageConfig.getProperty(MessageId
					+ "." + Integer.toString(i) + "." + "Type"), MessageConfig
					.getProperty(MessageId + "." + Integer.toString(i) + "."
							+ "Status"), MessageConfig.getProperty(MessageId
					+ "." + Integer.toString(i) + "." + "LenType"),
					MessageConfig.getProperty(MessageId + "."
							+ Integer.toString(i) + "." + "MetaLenSize"),
					MessageConfig.getProperty(MessageId + "."
							+ Integer.toString(i) + "." + "MetaLenType")));
		}
		return vtMessagePattern;

	}


	public static List CardproGroupPattern() {

		List CardproGroupList = new ArrayList();
		int typeNo = Integer.parseInt(Config.getProperty("CardproGroup"
				+ "." + "TypeNo"));
			
		IdDescInfo  idDescInfo=new IdDescInfo();
		
		
		
		for (int i = 0; i < typeNo; i++) {
			  idDescInfo=new IdDescInfo();
			idDescInfo.setId(Config
					.getProperty("CardproGroup" + "." + Integer.toString(i) + "."
							+ "TypeId"));
							
			idDescInfo.setDesc(Config.getProperty("CardproGroup"
					+ "." + Integer.toString(i) + "." + "TypeName"));
		
			CardproGroupList.add(i,idDescInfo);
		
		}
		return CardproGroupList;

	}

	//
	
	
	//ashrafi added 

	
	public static class IdDescInfo {
		private String Id;

		private String Desc;

		public IdDescInfo() {
		}

		public IdDescInfo(String idVal, String descVal) {
			Id = idVal;
			Desc = descVal;
		

		}

		public String getId() {
			return Id;
		}

		public void setId(String value) {
			Id = value;
		}

		public String getDesc() {
			return Desc;
		}

		public void setDesc(String value) {
			Desc = value;
		}

	}

	
	
	//
	
	
	
	
	public static class FieldInfo {
		private String name;

		private String len;

		private String position;

		private String type;

		private String status;

		private String lenType;

		private String metaLenSize;

		private String metaLenType;

		private String valueOfField;

		public FieldInfo() {
		}

		public FieldInfo(String nameVal, String lenVal, String positionVal,
				String typeVal, String statusVal, String lenTypeVal,
				String metaLenSizeVal, String metaLenTypeVal) {
			name = nameVal;
			len = lenVal;
			position = positionVal;
			type = typeVal;
			status = statusVal;
			lenType = lenTypeVal;
			metaLenSize = metaLenSizeVal;
			metaLenType = metaLenTypeVal;

		}

		public String getName() {
			return name;
		}

		public void setName(String value) {
			name = value;
		}

		public String getLen() {
			return len;
		}

		public void setLen(String value) {
			len = value;
		}

		public String getPosition() {
			return position;
		}

		public void setPosition(String value) {
			position = value;
		}

		public String getType() {
			return type;
		}

		public void setType(String value) {
			type = value;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String value) {
			status = value;
		}

		public String getLenType() {
			return lenType;
		}

		public void setLenType(String value) {
			lenType = value;
		}

		public String getMetaLenSize() {
			return metaLenSize;
		}

		public void setMetaLenSize(String value) {
			metaLenSize = value;
		}

		public String getMetaLenType() {
			return metaLenType;
		}

		public void setMetaLenType(String value) {
			metaLenType = value;
		}

		public String getValueOfField() {
			return valueOfField;
		}

		public void setValueOfField(String value) {
			valueOfField = value;
		}
	}

	public static char[] asciiToHex(char buffer[], int length) throws Exception {
		try {
			char hexBuffer[] = new char[length];
			int i = 0;
			/* check it */
			if (length > 16)
				throw (new AmbitException("MessagesUtility",
						"asciiToHex(char buffer[], int length)",
						"length more than 16"));

			for (i = 0; i < length; i++) {

				if (buffer[i] > 0x39) {

					if (buffer[i] >= 0x41 && buffer[i] <= 0x46) {
						if (buffer[i] == 'A')
							hexBuffer[i] = (char) (10);
						else if (buffer[i] == 'B')
							hexBuffer[i] = (char) (11);
						else if (buffer[i] == 'C')
							hexBuffer[i] = (char) (12);
						else if (buffer[i] == 'D')
							hexBuffer[i] = (char) (13);
						else if (buffer[i] == 'E')
							hexBuffer[i] = (char) (14);
						else if (buffer[i] == 'F')
							hexBuffer[i] = (char) (15);

					}

				} else if (buffer[i] >= 0x30 && buffer[i] <= 0x39) {

					hexBuffer[i] = (char) ((int) buffer[i] - 0x30);

				} else {
					throw (new AmbitException("MessagesUtility",
							"asciiToHex(char buffer[], int length)",
							"problem in buffer amount"));
				}
			}
			return hexBuffer;
		} catch (Exception e) {
			throw e;
		}
	}

	public static char[] hexToAscii(char buffer[], int length) throws Exception

	{

		try {
			char asciiBuffer[] = new char[length];
			int i;

			/* check it */
			if (length > 16)
				throw (new AmbitException("MessagesUtility",
						"hexToAscii(char buffer[], int length)",
						"length more than 16"));

			for (i = 0; i < length; i++) {

				if (buffer[i] >= 0x00 && buffer[i] <= 0x09) {
					asciiBuffer[i] = (char) (buffer[i] + 0x30);
				} else if (buffer[i] >= 0x0a && buffer[i] <= 0x0f) {
					asciiBuffer[i] = (char) ('A' + buffer[i] - 10);

				} else {
					throw (new AmbitException("MessagesUtility",
							"hexToAscii(char buffer[], int length)",
							"problem in buffer amount"));

				}

			}
			return asciiBuffer;
		}

		catch (Exception e) {

			throw e;

		}

	}

	public static class PINManager {
		private static final int BlockSize = 16;

		private static final int MaxPINLength = 12;

		private static final int MinPINLength = 4;

	//	private static final int MAX_STRING_LENGTH = 256;

		public static char[] createAnsiPINBlock(char PINArray[],
				char PANArray[]) throws Exception {
			try {
		//		int blockLen = BlockSize;
				char[] PINPartArry = new char[BlockSize];
				char[] PANPartArry = new char[BlockSize];

				char[] AnsiPINBlock = new char[BlockSize];

				for (int i = 0; i < BlockSize; i++) {
					PINPartArry[i] = '0';
				}

				for (int i = 0; i < BlockSize; i++) {
					PANPartArry[i] = '0';
				}
				if (PANArray.length < 13) {
					throw (new AmbitException(
							"MessagesUtility",
							"createAnsiPINBlock(char PINArray[],char PANArray[], char AnsiPINBlock[])",
							"length less than 13"));
				}

				for (int i = 0; i < BlockSize; i++) {
					AnsiPINBlock[i] = '0';
				}

				for (int i = 0; i < 4; i++) {
					PANPartArry[i] = '0';
				}

				int PANLen = (int) PANArray.length;

				int j = 15;
				for (int i = PANLen - 2; i > PANLen - 14; i--) {
					PANPartArry[j] = PANArray[i];
					j--;
				}

				int PINLen = PINArray.length;
				if ((PINLen < MinPINLength) || (PINLen > MaxPINLength)) {
					throw (new AmbitException(
							"MessagesUtility",
							"createAnsiPINBlock(char PINArray[],char PANArray[], char AnsiPINBlock[])",
							"PINLen less than MinPINLength or PINLen more than MaxPINLength"));
				}

				for (int i = 0; i < 16; i++) {
					if (i == 0) {
						PINPartArry[i] = '0';
					} else if (i == 1) {
						PINPartArry[i] = 'x';
					} else
						PINPartArry[i] = 'F';
				}

				if (PINLen <= 9) {
					PINPartArry[1] = (char) (PINLen + 0x30);
				}

				else {

					PINPartArry[1] = (char) ('A' + PINLen - 10);

				}
				for (int i = 0; i < PINLen; i++) {
					PINPartArry[i + 2] = PINArray[i];
				}

				int iSize = BlockSize;
				char[] hexPINPartArry = asciiToHex(PINPartArry, iSize);

				iSize = BlockSize;
				char[] hexPANPartArry = asciiToHex(PANPartArry, iSize);

				for (int i = 0; i < BlockSize; i++) {

					AnsiPINBlock[i] = (char) (hexPINPartArry[i] ^ hexPANPartArry[i]);

					// AnsiPINBlock [i]
				}

				iSize = BlockSize;
				char[] asciiAnsiPINBlock = hexToAscii(AnsiPINBlock, iSize);

				return asciiAnsiPINBlock;

			} catch (Exception e) {
				throw e;
			}
		}

	}

	public static String AddLeadingZeros(String Value, int Len) {
		String format = "";
		for (int i = 0; i < Len - Value.length(); i++)
			format = format + "0";
		return format + Value;

	}

	public static String AddFformating(String Value, int Len) {
		String format = "";
		for (int i = 0; i < Len - Value.length(); i++)
			format = format + "F";
		return format + Value;

	}

	public static String getSpace(int Len) {
		String format = "";
		for (int i = 0; i < Len; i++)
			format = format + " ";
		return format;
	}

	public static String getZero(int Len) {
		String format = "";
		for (int i = 0; i < Len; i++)
			format = format + "0";
		return format;
	}
	
	public static String getSpeciedCharcters(String SpeciedCharcters, int Len) {
		String format = "";
		for (int i = 0; i < Len; i++)
			format = format + SpeciedCharcters;
		return format;
	}
	
	public static String AddTrailingSpace(String Value, int Len) {
		String format = "";
		for (int i = 0; i < Len - Value.length(); i++)
			format = format + " ";
		return Value + format;

	}

	public static byte[] AsciiToBinary(String Value) throws Exception {
		try {
			if (!Value.matches("^[0-9a-fA-F]+$")) {
				byte ByteValue[] = {};
				return ByteValue;
			}
			if (Value.length() % 2 != 0) {
				Value = AddLeadingZeros(Value, Value.length() + 1);
			}
			String[] ArrayValue = new String[Value.length() / 2];
			byte[] ByteValue = new byte[Value.length() / 2];
			int iCounter;
			int BCD;

			ArrayValue[0] = Value.substring(0, 2);

			for (iCounter = 2; iCounter <= Value.length() / 2; iCounter++) {
				ArrayValue[iCounter - 1] = AmbitUtility.strMid(Value,
						(iCounter * 2) - 2, 2);
			}
			for (iCounter = 0; iCounter <= ArrayValue.length - 1; iCounter++) {
				BCD = Integer.parseInt(ArrayValue[iCounter], 16);
				ByteValue[iCounter] = (byte) (BCD);

				// ByteValue[iCounter] = Byte.valueOf(ArrayValue[iCounter], 16);
			}

			return ByteValue;

		} catch (Exception e) {

			throw e;
		}

	}

	// The fuction use for convert Track2Data
	public static byte[] ConvertToBCDForZfields(String Value) throws Exception {
		try {

			Value = Value.replace("=", "D");
			Value = Value.replace(";", "");
			Value = Value.replace("?", "");

			if (Value.length() % 2 != 0) {
				Value = Value + "F";
			}
			if (Value.length() % 2 != 0) {
				Value = AddLeadingZeros(Value, Value.length() + 1);
			}
			String[] ArrayValue = new String[Value.length() / 2];
			byte[] ByteValue = new byte[Value.length() / 2];
			int iCounter;
			int BCD;

			ArrayValue[0] = Value.substring(0, 2);

			for (iCounter = 2; iCounter <= Value.length() / 2; iCounter++) {
				ArrayValue[iCounter - 1] = AmbitUtility.strMid(Value,
						(iCounter * 2) - 2, 2);
			}
			for (iCounter = 0; iCounter <= ArrayValue.length - 1; iCounter++) {
				BCD = Integer.parseInt(ArrayValue[iCounter], 16);
				ByteValue[iCounter] = (byte) (BCD);

				// ByteValue[iCounter] = Byte.valueOf(ArrayValue[iCounter], 16);
			}

			return ByteValue;

		} catch (Exception e) {
			throw e;
		}

	}

	// this function convert String of 0&1 to String of base 16
	public static String ConvertBinaryToHex(String Value) throws Exception {
		try {
			String sHexValue = "";
			if (!Value.matches("^[0-1]+$")) {
				throw (new AmbitException("MessagesUtility",
						"ConvertBinaryToHex(String Value)", "invalid input"));
			}

			if (Value.length() > 128) {
				throw (new AmbitException("MessagesUtility",
						"ConvertBinaryToHex(String Value)",
						"length more than 128"));
			}

			while (Value.length() % 4 != 0) {
				Value = AddLeadingZeros(Value, Value.length() + 1);
			}

			String[] ArrayValue = new String[Value.length() / 4];
		//	byte[] ByteValue = new byte[Value.length() / 4];
			int iCounter;
		//	int BCD;

			ArrayValue[0] = Value.substring(0, 4);

			for (iCounter = 2; iCounter <= Value.length() / 4; iCounter++) {
				ArrayValue[iCounter - 1] = AmbitUtility.strMid(Value,
						(4 * (iCounter - 1)), 4);
			}

			int lTempValue;
			for (iCounter = 1; iCounter <= Value.length() / 4; iCounter++) {
				lTempValue = Integer.parseInt(ArrayValue[iCounter - 1], 2);

				sHexValue = sHexValue
						+ Integer.toHexString(lTempValue).toUpperCase();

			}
			return sHexValue;

		} catch (Exception e) {

			throw e;
		}

	}

	// this function convert String of base 16 to String of base 2
	public static String ConvertHexToBinary(String Value) throws Exception {
		try {
			String sBinaryValue = "";
			if (!Value.matches("^[0-9a-fA-F]+$")) {
				throw (new AmbitException("MessagesUtility",
						"ConvertHexToBinary(String Value)", "invalid input"));
			}
			String SValue;

			if (Value.length() > 16) {
				throw (new AmbitException("MessagesUtility",
						"ConvertHexToBinary(String Value)",
						"length more than 16"));
			}
			String[] ArrayValue = new String[Value.length()];
			int iCounter;
			for (iCounter = 1; iCounter <= Value.length(); iCounter++) {
				ArrayValue[iCounter - 1] = AmbitUtility.strMid(Value,
						(1 * (iCounter - 1)), 1);
			}
			int lTempValue;

			for (iCounter = 1; iCounter <= Value.length(); iCounter++) {
				lTempValue = Integer.parseInt(ArrayValue[iCounter - 1], 16);
				SValue = Integer.toBinaryString(lTempValue);

				if (SValue.length() % 4 != 0) {
					while (SValue.length() % 4 != 0) {
						SValue = AddLeadingZeros(SValue, SValue.length() + 1);
					}
				}
				sBinaryValue = sBinaryValue + SValue;

			}

			return sBinaryValue;
		}

		catch (Exception e) {
			throw e;

		}
	}

	public static String ConvertToNumeric(byte Value[]) throws Exception {
		try {
			String sNumericValue = "";
			int iTempvalue;
			String sTempvalue;

			for (int iCounter = 1; iCounter <= Value.length; iCounter++) {
				iTempvalue = Integer.parseInt(Byte
						.toString(Value[iCounter - 1]));
				sTempvalue = Integer.toHexString(iTempvalue);

				if (sTempvalue.length() % 2 != 0) {
					sTempvalue = AddLeadingZeros(sTempvalue, 2);
				}
				sNumericValue = sNumericValue + sTempvalue;
			}

			return sNumericValue;
		}

		catch (Exception e) {
			throw e;

		}
	}

	// start:ashrafi added 871120
	public static String genMac(byte ucByteStream[],

	int iLength, String sKey) throws Exception {
		try {
			String sMac = "";
		//	DESEncryption des = new DESEncryption();

			byte[] buffer = new byte[30 * 1024];
			byte[] bkey = new byte[10];

			if (sKey.length() != 16) {
				throw (new AmbitException(
						"MessagesUtility",
						"genMac(byte ucByteStream[],int iLength, String sKey )",
						"length not equal to 16"));
			}

			bkey = AsciiToBinary(sKey);

			int iPos = 0;

			byte[] cipher = new byte[8];
			byte[] result = new byte[8];
			for (int i = 0; i < iLength; i++) {
				buffer[i] = ucByteStream[i];
			}

			for (int i = 0; i < 8; i++) {
				result[i] = 0;
			}

			while (iPos < iLength) {

				for (int i = 0; i < 8; i++) {
					result[i] = (byte) (result[i] ^ buffer[iPos + i]);

				}

				Cipher algorithm = Cipher.getInstance("DES/ECB/NoPadding");
				Key key = new SecretKeySpec((new DESKeySpec(bkey)).getKey(),
						"DES");
				algorithm.init(Cipher.ENCRYPT_MODE, key);
				cipher = algorithm.doFinal(result);

				for (int i = 0; i < 8; i++) {
					result[i] = cipher[i];
				}

				iPos = iPos + 8;

			}

			sMac = Hex.toString(result);

			return sMac;
		}

		catch (Exception e) {
			throw e;

		}
	}

	// end:ashrafi added 871120

	public static byte[] EncodeToAscii(String sValue) throws Exception {
		byte[] by8859 = sValue.getBytes("Cp1256");
		String tempValue = new String(by8859, "ISO8859_1");
		byte[] bValue = new byte[tempValue.length()];
		for (int y = 0; y < tempValue.length(); y++) {
			bValue[y] = (byte) ((int) tempValue.charAt(y));
		}
		return bValue;
		// return sValue.getBytes("us-ascii");
	}

	public static byte[] ConcatByteStreams(byte first[], byte second[])
			throws Exception {

		byte[] concatArray;

		if (first == null) {
			concatArray = new byte[second.length];
			System.arraycopy(second, 0, concatArray, 0, second.length);
		}

		else {

			concatArray = new byte[first.length + second.length];
			System.arraycopy(first, 0, concatArray, 0, first.length);

			System.arraycopy(second, 0, concatArray, first.length,
					second.length);
		}
		return concatArray;
	}

	/*public static synchronized String getParameters(
			SysParametersService sysParametersService, String key)
			throws Exception {
		try {
			String parameterValue = "";
			parameterValue = sysParametersService.getParameterValue(key);

			return parameterValue;
		} catch (Exception e) {
			throw e;
		}
	}*/

	


	public static byte[] GetFormatedValue(FieldInfo msg) throws Exception {
		byte[] bReturnValue = {};
		String sMetaLength;
		String sValue;
		byte[] bTempValue = {};
		byte[] bMetaLength = {};
		StringBuffer bufferValue = new StringBuffer("");
		try {
			if (!msg.getLenType().equalsIgnoreCase("FIX")) {
				if (!AmbitUtility.nvl(msg.getValueOfField()).equalsIgnoreCase(
						"")) {
					if (msg.getType().equalsIgnoreCase("N")) {
						bTempValue = EncodeToAscii(msg.getValueOfField());

					} else if (msg.getType().equalsIgnoreCase("AN")) {
						bTempValue = EncodeToAscii(msg.getValueOfField());

					} else if (msg.getType().equalsIgnoreCase("ANS")) {
						bTempValue = EncodeToAscii(msg.getValueOfField());

					}
				}
				sMetaLength = AddLeadingZeros(Integer.toString(AmbitUtility
						.nvl(msg.getValueOfField()).length()), Integer
						.parseInt(msg.getMetaLenSize()));
				bMetaLength = EncodeToAscii(sMetaLength);
				bReturnValue = ConcatByteStreams(bMetaLength, bTempValue);

			}

			else {
				if (msg.getType().equalsIgnoreCase("N")) {
					sValue = AddLeadingZeros(AmbitUtility.nvl(msg
							.getValueOfField()), Integer.parseInt(msg.getLen()));
					bTempValue = EncodeToAscii(sValue);
					bufferValue.insert(0, "");
				} else if (msg.getType().equalsIgnoreCase("AN")) {
					sValue = AddLeadingZeros(AmbitUtility.nvl(msg
							.getValueOfField()), Integer.parseInt(msg.getLen()));
					bTempValue = EncodeToAscii(sValue);
				} else if (msg.getType().equalsIgnoreCase("ANS")) {
					// bufferValue.insert(0, msg.getValueOfField());
					sValue = AddTrailingSpace(AmbitUtility.nvl(msg
							.getValueOfField()), Integer.parseInt(msg.getLen()));
					bTempValue = EncodeToAscii(sValue);
				}

				bReturnValue = bTempValue;

			}

			return bReturnValue;

		} catch (Exception e) {

			throw e;

		}
	}

	public static String evaluateError(String errorCode) throws Exception {
		String errorDesc = "";

		if (errorCode
				.equalsIgnoreCase(Config.getProperty("ERROR_CODE_SUCCESS"))) {
			errorDesc = "paymentGateway.Successfully";
		} else if ((errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_TRANSACTION_CANCELED_01")))
				|| (errorCode.equalsIgnoreCase(Config
						.getProperty("ERROR_CODE_TRANSACTION_CANCELED_02")))
				|| (errorCode.equalsIgnoreCase(Config
						.getProperty("ERROR_CODE_TRANSACTION_CANCELED_03")))
				|| (errorCode.equalsIgnoreCase(Config
						.getProperty("ERROR_CODE_TRANSACTION_CANCELED_04")))
				|| (errorCode.equalsIgnoreCase(Config
						.getProperty("ERROR_CODE_TRANSACTION_CANCELED_05")))
				|| (errorCode.equalsIgnoreCase(Config
						.getProperty("ERROR_CODE_TRANSACTION_CANCELED_06")))
				|| (errorCode.equalsIgnoreCase(Config
						.getProperty("ERROR_CODE_TRANSACTION_CANCELED_07")))
				|| (errorCode.equalsIgnoreCase(Config
						.getProperty("ERROR_CODE_TRANSACTION_CANCELED_08")))
				|| (errorCode.equalsIgnoreCase(Config
						.getProperty("ERROR_CODE_TRANSACTION_CANCELED_09")))) {
			errorDesc = "paymentGateway.error.TransactionCanceled";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_PREVIOUSLY_REVERSED"))) {
			errorDesc = "paymentGateway.error.PreviouslyReversed";
		} else if ((errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_INVALID_ACQUIRER_01")))
				|| (errorCode.equalsIgnoreCase(Config
						.getProperty("ERROR_CODE_INVALID_ACQUIRER_02")))) {
			errorDesc = "paymentGateway.error.InvalidAcquirer";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_INTERNAL_ERROR"))) {
			errorDesc = "paymentGateway.error.InternalError";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_SYSTEM_BUSY"))) {
			errorDesc = "paymentGateway.error.SystemBusy";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_PARTIAL_DISPENSE"))) {
			errorDesc = "paymentGateway.error.PartialDispense";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_INVALID_TRANSACTION"))) {
			errorDesc = "paymentGateway.error.InvalidTransaction";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_INVALID_AMOUNT"))) {
			errorDesc = "paymentGateway.error.InvalidAmount";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_INVALID_PAN"))) {
			errorDesc = "paymentGateway.error.InvalidPan";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_UNKNOWN_ISSUER"))) {
			errorDesc = "paymentGateway.error.UnknownIssuer";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_TRANSACTION_REPEATED"))) {
			errorDesc = "paymentGateway.error.TransactionRepeated";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_INVALID_RESPONSE_CODE"))) {
			errorDesc = "paymentGateway.error.InvalidResponseCode";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_NO_ACTION_DONE"))) {
			errorDesc = "paymentGateway.error.NoActionDone";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_SYSTEM_MALFUNCTION"))) {
			errorDesc = "paymentGateway.error.SystemMalfunction";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_RECORD_NOT_FOUND"))) {
			errorDesc = "paymentGateway.error.RecordNotFound";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_INVALID_MESSAGE_TEMPLATE"))) {
			errorDesc = "paymentGateway.error.InvalidMessageTemplate";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_COMPLETED_PARTIALY"))) {
			errorDesc = "paymentGateway.error.CompletedPartialy";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_CARD_EXPIRED"))) {
			errorDesc = "paymentGateway.error.CardExpired";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_NOT_APPROVAL"))) {
			errorDesc = "paymentGateway.error.NotApproval";
		} else if ((errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_CARD_LIMITED_01")))
				|| (errorCode.equalsIgnoreCase(Config
						.getProperty("ERROR_CODE_CARD_LIMITED_02")))) {
			errorDesc = "paymentGateway.error.CardLimited";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_TOO_MUCH_INVALID_PIN"))) {
			errorDesc = "paymentGateway.error.TooMuchInvalidPin";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_TOO_MUCH_INVALID_PIN"))) {
			errorDesc = "paymentGateway.error.TooMuchInvalidPin";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_TRANSACTION_NOT_DISTINGUISHED"))) {
			errorDesc = "paymentGateway.error.TransactionNotDistinguished";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_CARD_LOST_OR_WARM"))) {
			errorDesc = "paymentGateway.error.CardLostOrWarm";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_CARD_STOLEN_OR_HOT"))) {
			errorDesc = "paymentGateway.error.CardStolenOrHot";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_INSUFFICIENT_SUPPLY"))) {
			errorDesc = "paymentGateway.error.InsufficientAmount";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_INVALID_PIN"))) {
			errorDesc = "paymentGateway.error.InvalidPin";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_INVALID_CARD"))) {
			errorDesc = "paymentGateway.error.InvalidCard";
		} else if ((errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_TRANSACTION_NOT_ALLOWED_01")))
				|| (errorCode.equalsIgnoreCase(Config
						.getProperty("ERROR_CODE_TRANSACTION_NOT_ALLOWED_02")))) {
			errorDesc = "paymentGateway.error.TransactionNotAllowed";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_AMOUNT_MORE_THAN_LIMIT"))) {
			errorDesc = "paymentGateway.error.AmountMoreThanLimit";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_ACCOUNT_IS_INACTIVE"))) {
			errorDesc = "paymentGateway.error.AccountIsInactive";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_CARD_CAPTURED"))) {
			errorDesc = "paymentGateway.error.CardCaptured";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_DELAY_IN_ANSWER"))) {
			errorDesc = "paymentGateway.error.DelayInAnswer";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_CARD_IS_INACTIVE"))) {
			errorDesc = "paymentGateway.error.CardIsInactive";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_ACCOUNT_NOT_DEFINED"))) {
			errorDesc = "paymentGateway.error.AccountNotDefined";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_ERROR_IN_TRANSACTION_PROCESSING"))) {
			errorDesc = "paymentGateway.error.ErrorInTransactionProcessing";
		} else if ((errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_NO_ANSWER_01")))
				|| (errorCode.equalsIgnoreCase(Config
						.getProperty("ERROR_CODE_NO_ANSWER_02")))) {
			errorDesc = "paymentGateway.error.NoAnswer";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_INVALID_ORIGINATOR_NO"))) {
			errorDesc = "paymentGateway.error.InvalidOriginatorNo";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_CUTOFF_PROCESSING"))) {
			errorDesc = "paymentGateway.error.CutoffProcessing";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_INVALID_ISSUER"))) {
			errorDesc = "paymentGateway.error.InvalidIssuer";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_UNCOMPLETED_TRANSACTION"))) {
			errorDesc = "paymentGateway.error.UncompletedTransaction";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_DUPLICATE_TRANSACTION"))) {
			errorDesc = "paymentGateway.error.DuplicateTransaction";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_MAC_CONFLICT"))) {
			errorDesc = "paymentGateway.error.ConflictInMAC";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_BILL_PAYED_PREVIOUSLY"))) {
			errorDesc = "paymentGateway.error.BillPayedPreviously";
		} else if (errorCode.equalsIgnoreCase(Config
				.getProperty("ERROR_CODE_MOBILE_TOPUP_CHARGE_FINISHED"))) {
			errorDesc = "paymentGateway.error.MobileTopUpChargeFinished";
		} else {
			errorDesc = "paymentGateway.error.ErrorInResponse";
		}

		return errorDesc;
	}



}