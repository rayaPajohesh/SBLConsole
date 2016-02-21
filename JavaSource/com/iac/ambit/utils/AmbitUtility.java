package com.iac.ambit.utils;

import java.io.File;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.text.DecimalFormat;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import org.apache.commons.mail.EmailException;

import org.apache.commons.mail.EmailAttachment;

import org.apache.commons.mail.HtmlEmail;




import proxy.com.iac.ambit.model.Customer;
import proxy.com.iac.ambit.model.Permissions;

import com.iac.ambit.utils.Config;




import org.apache.axis.MessageContext;
import org.apache.axis.transport.http.HTTPConstants;

import com.iac.ambit.utils.Constants;

public class AmbitUtility {
	public static String FARSI_CHARS = ":«¬»Å ÀÃçÕŒœ–—“é”‘’÷ÿŸ⁄€›ﬁﬂê·„‰ÊƒÂÌ∆¡…√";

	public static String NUMBERS = "0123456789";

	public static String FARSI_NOT_JOIN_CHARS = "«¬œ–—“éÊ";
	
	public static String Alpha_Numeric_Farsi="[«¬»Å ÀÃçÕŒœ–—“é”‘’÷ÿŸ⁄€›ﬁòê·„‰ÊƒÂÌ∆¡…√ a-zA-Z0-9]*";
	// jazimagh : 1386/07/16
	public final Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	private final void writeObject(ObjectOutputStream out) throws IOException {
		throw new IOException("Object cannot be serialized");
	}

	private final void readObject(ObjectInputStream in) throws IOException {
		throw new IOException("Class cannot be Deserialized");
	}

	// jazimagh : 1386/07/16
	public AmbitUtility() {

	}

	/**
	 * input: posArray: array of indices totalPos: Bitmap length
	 * 
	 * output: return: bitmap (eg: 0010101100010)
	 */
	public static String createBitmap(Vector posArray, int totalPos) {
		if ((posArray == null) || (posArray.size() == 0) || (totalPos <= 0)) {
			return "";

		}

		StringBuffer sb = new StringBuffer("");

		for (int i = 0; i < totalPos; i++) {
			if (isKeyInVector(posArray, i)) {
				sb.append("1");

			} else {
				sb.append("0");

			}

		}

		return sb.toString();

	}

	public static String completeChequeNumber(String chequeNumber) {
		StringBuffer completeChqueNumber = new StringBuffer();

		if (chequeNumber.length() < 11) {
			int length = 11 - chequeNumber.length();

			for (int i = 0; i < length; i++) {
				completeChqueNumber.append("0");

			}

		}

		completeChqueNumber.append(chequeNumber);

		return completeChqueNumber.toString();

	}

	public static boolean isKeyInVector(Vector v, int key) {
		for (int i = 0; i < v.size(); i++)
			if (((Integer) v.get(i)).intValue() == key) {
				return true;

			}

		return false;

	}

	public static String createPasswordFormatFromBitmap(String password,
			String bitmap) {
		String returnValue = "";
		int bitmapSize = bitmap.length();

		for (int i = 0; (i < bitmapSize & i < password.length()); i++) {
			if (bitmap.charAt(i) == '1')
				returnValue = returnValue + password.charAt(i);
		}
		return returnValue;
	}

	public static String[] createPasswordFormatTemp(String password) {
		String bitmap = new String("010101000000000");

		String[] returnArr = new String[2];

		returnArr[0] = bitmap;
		returnArr[1] = (password.charAt(1) + "") + (password.charAt(3) + "")
				+ (password.charAt(5) + "");

		return returnArr;
	}

	public static boolean arePasswordsEqual(String oldPassword, String password) {
		String oldBitmap = oldPassword.substring(0, 15);
		boolean flag = true;
		int oldIndex = 1;

		for (int i = 0; ((i < oldBitmap.length()) && flag); i++) {
			if ((oldBitmap.charAt(i) + "").equals("1" + "")) {
				if (!(oldPassword.charAt(oldBitmap.length() + oldIndex) + "")
						.equals(password.charAt(i) + "")) {
					flag = false;

				}

				oldIndex++;

			}

		}

		return flag;

	}

	/**
	 * This function creates a new thread to send the email.
	 * 
	 * @param throwable
	 * @param exceptionType
	 */
	private static void sendAlert(String message, String emailAddresses,
			String subject) {
		// Sends Email in a new thread
		Thread thread = new Thread(new MailSender(message, emailAddresses,
				subject));
		thread.start();

	}

	private static void sendMultiPartEMailThread(String message,
			String emailAddress, String subject, EmailAttachment attachment,
			boolean isMultiPartMail) {
		MailSender mailSender = new MailSender(message, emailAddress, subject,
				attachment, isMultiPartMail);
		Thread thread = new Thread(mailSender);
		thread.start();
	}

	/**
	 * This function sends an email to customer notifying him of the
	 * registration
	 */
	public static void alertCustomerOfRegistration(String name,
			String emailAddress) {
		if (Config.getProperty("email.customer.registrataion.send")
				.equalsIgnoreCase("true")) {
			String subject = Config
					.getProperty("email.customer.registrataion.subject");
			String message = "Dear "
					+ name
					+ ",\n"
					+ Config
							.getProperty("email.customer.registrataion.message");

			sendAlert(message, emailAddress, subject);
		}

	}

	// jazimagh : 1386/07/15 change input parameters
	public static void sendMultiPartEmail(String name, String message,
			String subject, String emailaddress, EmailAttachment attachment) {

		sendMultiPartEMailThread(message, emailaddress, subject, attachment,
				true);
	}

	

	public static void alertCustomerOfChangePIN(String name,
			String emailAddress, String password) {
		String subject = Config.getProperty("email.customer.pinChange.subject");
		String url = Config.getProperty("ChangePIN.URL") + password;

		String message = "Dear "
				+ ((name == null || name.equals("null")) ? "Customer" : name)
				+ ",\n"
				+ Config.getProperty("email.customer.pinChange.message") + "\n"
				+ url;

		sendAlert(message, emailAddress, subject);
	}

	public static void alertCustomerOfNewTPIN(String name, String emailAddress,
			String pin, String expiryDate, String language, String country)
			throws UnsupportedEncodingException {
		// jazimagh : 1386/07/15

		String message = Config.getPropertyFromBundle(
				"email.customer.generateTPin.subject", language, country)
				+ " : "
				+ pin
				+ "\n"
				+ Config.getPropertyFromBundle(
						"email.customer.generateTPin.expireDate", language,
						country) + " : " + expiryDate;
		byte[] by8859 = message.getBytes("Cp1256");
		String tempmessage = new String(by8859, "ISO8859_1");
		message = tempmessage;
		String subject = Config.getPropertyFromBundle(
				"email.customer.generateTPin.subject", language, country);
		sendAlert(message, emailAddress, subject);
	}

	// Jazimagh : 13880305
	public static void alertCustomerOfNewPurchase(String emailAddress,
			String merchantName, String purchaseAmount,
			String purchaseDateTime, String purchasePan, String purchaseRRN,
			String purchaseSTAN, String reservationNum, String language,
			String country) throws UnsupportedEncodingException {
		 
			String bodyHtml = "<head>" 
				+ " <style>"
	      + ".headLine{"
	               +" font-family: 'Tahoma';"
	               +" font-size: 11px;"
	               +" font-weight: normal;"
	             
	        +"}"
	        
	            
	 	+"</style>"    
	 	+"</head>" +
			
			"<table width='100%' ' border='1' dir='rtl' class='headLine' >"
				+ "<tr id='header' >"
				+ "<td id='header' colspan=2 align='center'  bgcolor='#A0D27B' bordercolor='#800000'>"
				+ Config.getPropertyFromBundle("email.customer.bankName", language,
						country) + "</td></tr>";

		String message = bodyHtml
				+ "<tr>"
				+ "<td  align='left' bgcolor='#C8EBFD' width='50%' >"
				+ Config.getPropertyFromBundle(
						"email.customer.purchase.merchantName", language,
						country)
				+ " : "
				+ "</td>"
				+ "<td  align='right' width='50%' >"
				+ merchantName
				+ "</td></tr>"
				+ "<tr>"
				+ "<td  align='left' bgcolor='#C8EBFD'>"
				+ Config.getPropertyFromBundle(
						"email.customer.purchase.purchaseAmount", language,
						country)
				+ " : "
				+ "</td>"
				+ "<td  align='right' >"
				+ purchaseAmount

				+ ((language.equals(Config.getProperty("language.Persian"))) ? Config
						.getPropertyFromBundle("global.currencyPersian",
								language, country)
						: "")
				+ "</td></tr>"
				+ "<tr>"
				+ "<td  align='left' bgcolor='#C8EBFD'>"
				+ Config.getPropertyFromBundle(
						"email.customer.purchase.purchaseDateTime", language,
						country)
				+ " : "
				+ "</td>"
				+ "<td  align='right' >"
				+ purchaseDateTime
				+ "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td  align='left' bgcolor='#C8EBFD'>"
				+ Config.getPropertyFromBundle(
						"email.customer.purchase.purchasePan", language,
						country)
				+ " : "
				+ "</td>"
				+ "<td  align='right' >"
				+ purchasePan
				+ "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td  align='left' bgcolor='#C8EBFD'>"
				+ Config.getPropertyFromBundle(
						"email.customer.purchase.purchaseRRN", language,
						country)
				+ " : "
				+ "</td>"
				+ "<td  align='right' >"
				+ purchaseRRN
				+ "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td  align='left' bgcolor='#C8EBFD'>"
				+ Config.getPropertyFromBundle(
						"email.customer.purchase.purchaseSTAN", language,
						country)
				+ " : "
				+ "</td>"
				+ "<td  align='right' >"
				+ purchaseSTAN
				+ "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td  align='left' bgcolor='#C8EBFD'>"
				+ Config.getPropertyFromBundle(
						"email.customer.purchase.reservationNum", language,
						country) + " : " + "</td>" + "<td  align='right' >"
				+ reservationNum + "</td>" + "</tr>" + "</table>";

		/*
		 * byte[] by8859 = message.getBytes("Cp1256"); String tempmessage = new
		 * String(by8859, "ISO8859_1"); message = tempmessage;
		 */
		String subject = Config.getPropertyFromBundle(
				"email.customer.purchase.subject", language, country);
		sendAlert(message, emailAddress, subject);
	}

	

	
///
	

	

	
	//***
	
	//
	
	
	

	private static class MailSender implements Runnable {
		private String emailAddresses;

		private String message;

		private String subject;

		private EmailAttachment attachment;

		private boolean isMultiPartMail = false;

		//private MultiPartEmail multiPartEmail;

		protected MailSender(String message, String emailAddresses,
				String subject) {
			this.message = message;
			this.emailAddresses = emailAddresses;
			this.subject = subject;

		}

		protected MailSender(String message, String emailAddress,
				String subject, EmailAttachment attachment,
				boolean isMultiPartMail) {
			this.message = message;
			this.emailAddresses = emailAddress;
			this.subject = subject;
			this.attachment = attachment;
			this.isMultiPartMail = isMultiPartMail;

		}

		public void run() {

			try {
				
				HtmlEmail mail = new HtmlEmail();
				mail.setCharset("UTF-8");
				if (isMultiPartMail) {
					mail.attach(attachment);
				}

				mail.setHostName(Config.getProperty("smtp.server"));
				mail.setSmtpPort(Integer.parseInt(Config
						.getProperty("smtp.server.port")));

				if (Config.getProperty("smtp.server.authenticate")
						.equalsIgnoreCase("true")) {
					mail.setAuthentication(Config
							.getProperty("smtp.server.username"), Config
							.getProperty("smtp.server.password"));

				}

				mail.setBounceAddress(Config
						.getProperty("smtp.server.username"));
				String[] emails = StringUtils.split(this.emailAddresses, ",");

				for (int i = 0; i < emails.length; i++) {
					mail.addTo(emails[i]);

				}

				mail.setFrom(Config.getProperty("ambit.email.address"), Config
						.getProperty("ambit.email.name"));
				mail.setSubject(this.subject);
				mail.setMsg(this.message);
				mail.send();

				
			} catch (EmailException e) {
				Tracer.traceOut(Tracer.Error_Level, "AmbitUtility",
						"Send Mail", e.toString());
				if (isMultiPartMail) {
					// this.multiPartEmail = (MultiPartEmail) mail;

					String path = attachment.getPath();
					File file = new File(path);
					System.out.println("File : " + path + " deleted ===>> "
							+ file.delete());

				}
				Tracer.exception("MailSender", "run", e);

			}

		}

	}

	/**
	 * 
	 * This Function helps to create a Selection list for Stop Cheque Reasons.
	 * 
	 * 
	 * @return reasonList
	 * @param configProperty
	 */
	public static List createStopChequeReasonList(String configProperty,
			String language, String country) {
		Vector reasonList = new Vector();
		// jazimagh : 1386/05/30
		String[] reasons = IntegrationUtils
				.convertMsgToArrayNoLastDelimiter(Config.getPropertyFromBundle(
						configProperty, language, country), "|");
		// jazimagh : 1386/05/30

		int i = 0;
		while (reasons != null && reasons.length > i)
			reasonList.add(reasons[i++]);

		return reasonList;
	}

	// jazimagh : 1386/05/30
	public static void setStopChequeReasonList(HttpServletRequest request,
			String configProperty, String requestProperty) {
		Customer customer = (Customer) request.getSession().getAttribute(
				Constants.CUSTOMER_IN_SESSION);
		String language = customer.getLanguage();
		String country = null;
		if (language.equals(Config.getProperty("language.Persian"))) {
			country = Config.getProperty("country.Iran");
		} else {
			country = Config.getProperty("country.America");
		}

		// Config.init(realPath + "\\default.properties",msg);
		request.setAttribute(requestProperty, createStopChequeReasonList(
				configProperty, language, country));
	}

	// jazimagh : 1386/05/30
	public static String addAttributes(String target, Object[] attributes) {
		String result = "";

		for (int i = 0; i < attributes.length; i++) {
			result = target.substring(0, target.indexOf("{"));
			result += (String) attributes[i];
			result += target
					.substring(target.indexOf("}") + 1, target.length());
			target = result;
		}

		return target;
	}

	public static String createPasswordFormat(String[] sArr, Vector rndPos) {
		if ((sArr == null) || (rndPos == null) || (rndPos.size() == 0)) {
			return "";

		}

		Collections.sort((List) rndPos);

		StringBuffer sb = new StringBuffer("");
		boolean flag = false;
		int count = 0;

		for (int i = (sArr.length - 1); i >= 0; i--) {
			if ((sArr[i] != null) && !sArr[i].equals("")) {
				flag = true;
				sb.insert(0, sArr[i]);

			} else {
				if (flag) {
					sb.insert(0, "@");

				} else {
					count++;

				}

			}

		}

		int tempSZ = rndPos.size();

		if (count != 0) {
			for (int j = (rndPos.size() - 1); j >= (tempSZ - count); j--) {
				rndPos.remove(j);

			}

		}

		// sb.insert( 0, ( createBitmap( rndPos, 15 ) +
		// IntegrationUtils.getDelimiter( ) ) );

		return sb.toString();

	}

	// jazimagh : 1385/05/30
	public static Vector ActivityType() {

		// ashrafi changed vtActivityType 860631
		// in version 1
		// AddComplaint,ChequeStatusInquiry,StopChequeInquiry,FillEForm,DebitLimit,MarkCardWarm
		// is commented
		String language = Config.getProperty("language.Persian");
		String country = Config.getProperty("country.Iran");
		Vector vtActivityType = new Vector();
		vtActivityType.add(0, new CodeDesc("9999", Config
				.getPropertyFromBundle("activityType.All", language, country)));
		vtActivityType.add(1, new CodeDesc("112", Config.getPropertyFromBundle(
				"activityType.FundsTransfer", language, country)));
		vtActivityType.add(2, new CodeDesc("113", Config.getPropertyFromBundle(
				"activityType.AddComplaint", language, country)));
		vtActivityType.add(3, new CodeDesc("114", Config.getPropertyFromBundle(
				"activityType.FundsTransferThirdParty", language, country)));
		vtActivityType.add(4, new CodeDesc("115", Config.getPropertyFromBundle(
				"activityType.ChangePIN", language, country)));
		vtActivityType.add(5, new CodeDesc("116", Config.getPropertyFromBundle(
				"activityType.ChangePassword", language, country)));
		// vtActivityType.add(4, new CodeDesc("",
		// Config.getPropertyFromBundle("activityType.changeCardTransPassword",Config.getProperty("language.Persian"))));
		vtActivityType.add(6, new CodeDesc("117", Config.getPropertyFromBundle(
				"activityType.AccountFullStatement", language, country)));
		vtActivityType.add(7, new CodeDesc("118", Config.getPropertyFromBundle(
				"activityType.AccountMiniStatement", language, country)));
		// vtActivityType.add(8, new CodeDesc("119",
		// Config.getPropertyFromBundle("activityType.ChequeStatusInquiry",Config.getProperty("language.Persian"))));
		vtActivityType.add(8, new CodeDesc("120", Config.getPropertyFromBundle(
				"activityType.StopChequeInquiry", language, country)));
		vtActivityType.add(9, new CodeDesc("121", Config.getPropertyFromBundle(
				"activityType.AccountSummary", language, country)));
		vtActivityType.add(10, new CodeDesc("122", Config
				.getPropertyFromBundle("activityType.FillEForm", language,
						country)));
		vtActivityType.add(11, new CodeDesc("123", Config
				.getPropertyFromBundle("activityType.DebitLimit", language,
						country)));
		vtActivityType.add(12, new CodeDesc("124", Config
				.getPropertyFromBundle("activityType.MarkCardWarm", language,
						country)));
		vtActivityType.add(13, new CodeDesc("125", Config
				.getPropertyFromBundle("activityType.BeneficiarySettings",
						language, country)));
		vtActivityType.add(14,
				new CodeDesc("126", Config.getPropertyFromBundle(
						"activityType.Login", language, country)));
		vtActivityType.add(15, new CodeDesc("127",
				Config.getPropertyFromBundle("activityType.Logout", language,
						country)));
		vtActivityType.add(16, new CodeDesc("128", Config
				.getPropertyFromBundle("activityType.AlertAndNotifications",
						language, country)));

		return vtActivityType;

	}

	public static List ComplaintType() {

		String language = Config.getProperty("language.Persian");
		String country = Config.getProperty("country.Iran");

		Vector vtComplaintType = new Vector();
		vtComplaintType.add(0, new CodeDesc("1", Config.getPropertyFromBundle(
				"complaintType.CreditCardProblem", language, country)));
		vtComplaintType.add(1, new CodeDesc("2", Config.getPropertyFromBundle(
				"complaintType.ATMCardProblem", language, country)));
		vtComplaintType.add(2, new CodeDesc("3", Config.getPropertyFromBundle(
				"complaintType.AccountBalanceProblem", language, country)));
		vtComplaintType.add(3, new CodeDesc("4", Config.getPropertyFromBundle(
				"complaintType.GeneralProblem", language, country)));
		return vtComplaintType;

	}

	public static Vector ComplaintStatus() {

		String language = Config.getProperty("language.Persian");
		String country = Config.getProperty("country.Iran");

		Vector vtActivityType = new Vector();
		vtActivityType.add(0, new CodeDesc("1", Config.getPropertyFromBundle(
				"COMPLAINT_DESC_STATUS_INITIATED", language, country)));
		vtActivityType.add(1, new CodeDesc("2", Config.getPropertyFromBundle(
				"COMPLAINT_DESC_STATUS_CLOSED", language, country)));
		return vtActivityType;

	}

	

	public static Vector EformStatus() {
		String language = Config.getProperty("language.Persian");
		String country = Config.getProperty("country.Iran");

		Vector vtActivityType = new Vector();
		vtActivityType.add(0, new CodeDesc("1", Config.getPropertyFromBundle(
				"eformStatus.Initiated", language, country)));
		vtActivityType.add(1, new CodeDesc("2", Config.getPropertyFromBundle(
				"eformStatus.Resolved", language, country)));
		vtActivityType.add(2, new CodeDesc("3", Config.getPropertyFromBundle(
				"eformStatus.Rejected", language, country)));
		vtActivityType.add(3, new CodeDesc("4", Config.getPropertyFromBundle(
				"eformStatus.InProcess", language, country)));
		return vtActivityType;

	}

	public static Vector AccountTermType(String language, String country,
			String bankName) {

		Vector vtAccountTermType = new Vector();
		if (Config.getProperty("ActiveBank").equalsIgnoreCase(
				Config.getProperty("BankName.Sarmayeh"))) {
			vtAccountTermType.add(0, new CodeDesc("accountTerm.supporter",
					Config.getPropertyFromBundle("accountTerm.supporter",
							language, country)));
			vtAccountTermType.add(1, new CodeDesc("accountTerm.shortTerm",
					Config.getPropertyFromBundle("accountTerm.shortTerm",
							language, country)));
			vtAccountTermType.add(2, new CodeDesc(
					"accountTerm.specialShortTermSixMNT", Config
							.getPropertyFromBundle(
									"accountTerm.specialShortTermSixMNT",
									language, country)));
			vtAccountTermType.add(3, new CodeDesc(
					"accountTerm.specialShortTermNineMNT", Config
							.getPropertyFromBundle(
									"accountTerm.specialShortTermNineMNT",
									language, country)));
			vtAccountTermType.add(4, new CodeDesc(
					"accountTerm.longTermOneYear", Config
							.getPropertyFromBundle(
									"accountTerm.longTermOneYear", language,
									country)));
			vtAccountTermType.add(5, new CodeDesc(
					"accountTerm.longTermTwoYear", Config
							.getPropertyFromBundle(
									"accountTerm.longTermTwoYear", language,
									country)));
			vtAccountTermType.add(6, new CodeDesc(
					"accountTerm.longTermThreeYear", Config
							.getPropertyFromBundle(
									"accountTerm.longTermThreeYear", language,
									country)));
			vtAccountTermType.add(7, new CodeDesc(
					"accountTerm.longTermFourYear", Config
							.getPropertyFromBundle(
									"accountTerm.longTermFourYear", language,
									country)));
			vtAccountTermType.add(8, new CodeDesc(
					"accountTerm.longTermFiveYear", Config
							.getPropertyFromBundle(
									"accountTerm.longTermFiveYear", language,
									country)));
		} else if (Config.getProperty("ActiveBank").equalsIgnoreCase(
				Config.getProperty("BankName.Ghavamin"))) {
			vtAccountTermType.add(0, new CodeDesc("accountTerm.shortTerm",
					Config.getPropertyFromBundle("accountTerm.shortTerm",
							language, country)));
			vtAccountTermType.add(1, new CodeDesc(
					"accountTerm.specialShortTermSixMNT", Config
							.getPropertyFromBundle(
									"accountTerm.specialShortTermSixMNT",
									language, country)));
			vtAccountTermType.add(2, new CodeDesc(
					"accountTerm.longTermOneYear", Config
							.getPropertyFromBundle(
									"accountTerm.longTermOneYear", language,
									country)));
			vtAccountTermType.add(3, new CodeDesc(
					"accountTerm.longTermTwoYear", Config
							.getPropertyFromBundle(
									"accountTerm.longTermTwoYear", language,
									country)));
			vtAccountTermType.add(4, new CodeDesc(
					"accountTerm.longTermThreeYear", Config
							.getPropertyFromBundle(
									"accountTerm.longTermThreeYear", language,
									country)));
			vtAccountTermType.add(5, new CodeDesc(
					"accountTerm.longTermFourYear", Config
							.getPropertyFromBundle(
									"accountTerm.longTermFourYear", language,
									country)));
			vtAccountTermType.add(6, new CodeDesc(
					"accountTerm.longTermFiveYear", Config
							.getPropertyFromBundle(
									"accountTerm.longTermFiveYear", language,
									country)));
		}
		return vtAccountTermType;

	}

	public static Vector ChannelName(String language, String country) {

		Vector vtChannelName = new Vector();
		vtChannelName.add(0, new CodeDesc("1", Config.getPropertyFromBundle(
				"channelName.IB", language, country)));
		vtChannelName.add(1, new CodeDesc("2", Config.getPropertyFromBundle(
				"channelName.ATM", language, country)));
		return vtChannelName;

	}

	public static class CodeDesc {
		private String code;

		private String desc;

		public CodeDesc() {
		}

		public String getCode() {
			return code;
		}

		public CodeDesc(String codeVal, String descVal) {
			code = codeVal;
			desc = descVal;
		}

		public void setCode(String value) {
			code = value;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String value) {
			desc = value;
		}
	}

	public static class FLCodeDesc {
		private String code;

		private String fdesc;
		
		private String desc;

		public FLCodeDesc() {
		}

		public String getCode() {
			return code;
		}

		public FLCodeDesc(String codeVal, String fdescVal,String descVal) {
			code = codeVal;
			fdesc = fdescVal;
			desc = descVal;
			
		}

		public void setCode(String value) {
			code = value;
		}

		public String getFdesc() {
			return fdesc;
		}

		public void setFdesc(String value) {
			fdesc = value;
		}
		
		
		public String getDesc() {
			return desc;
		}

		public void setDesc(String value) {
			desc = value;
		}
	}
	
	
	
	public static String leadingZero(String data, int len) {
		// jazimagh : 1386/08/15
		String format = "";
		for (int i = 0; i < len; i++)
			format = format + "0";
		DecimalFormat myFormat = new DecimalFormat(format);
		if (data.length() == 0)
			return format;
		return myFormat.format(new Double(data));

	}

	public static Vector PersonalizePage() {

		String language = Config.getProperty("language.Persian");
		String country = Config.getProperty("country.Iran");

		// ashrafi changed vtPersonalizePage 86/01/02
		// in version 1
		// BlockCard,ChequeStatus,StopChequePayment,StandingInstruction,DebitLimits,RequestList,ChequeBookRequest,SupplementaryCard,BillPaymentSchedule,TermDeposit,PaymentOrder,ContactUpdateRequest
		// ,CreditCardPayments,AddComplaint,AlertsAndNotifications,CustomerInfo
		Vector vtPersonalizePage = new Vector();
		vtPersonalizePage.add(0, new CodeDesc(Config
				.getProperty("personaliz.Page.Home"), Config
				.getPropertyFromBundle("global.personaliz.Page.Home", language,
						country)));
		vtPersonalizePage.add(1, new CodeDesc(Config
				.getProperty("personaliz.Page.AccountSummary"), Config
				.getPropertyFromBundle("global.personaliz.Page.AccountSummary",
						language, country)));
		vtPersonalizePage.add(2, new CodeDesc(Config
				.getProperty("personaliz.Page.BlockCard"), Config
				.getPropertyFromBundle("global.personaliz.Page.BlockCard",
						language, country)));
		// vtPersonalizePage.add(3, new
		// CodeDesc(Config.getProperty("personaliz.Page.ChequeStatus"),
		// Config.getPropertyFromBundle("global.personaliz.Page.ChequeStatus",language,
		// country)));
		vtPersonalizePage.add(3, new CodeDesc(Config
				.getProperty("personaliz.Page.StopChequePayment"), Config
				.getPropertyFromBundle(
						"global.personaliz.Page.StopChequePayment", language,
						country)));
		vtPersonalizePage.add(4, new CodeDesc(Config
				.getProperty("personaliz.Page.MiniStatement"), Config
				.getPropertyFromBundle("global.personaliz.Page.MiniStatement",
						language, country)));
		vtPersonalizePage.add(5, new CodeDesc(Config
				.getProperty("personaliz.Page.Transfer"), Config
				.getPropertyFromBundle("global.personaliz.Page.Transfer",
						language, country)));
		vtPersonalizePage.add(6, new CodeDesc(Config
				.getProperty("personaliz.Page.StandingInstruction"), Config
				.getPropertyFromBundle(
						"global.personaliz.Page.StandingInstruction", language,
						country)));
		vtPersonalizePage.add(7, new CodeDesc(Config
				.getProperty("personaliz.Page.BeneficiarySettings"), Config
				.getPropertyFromBundle(
						"global.personaliz.Page.BeneficiarySettings", language,
						country)));
		vtPersonalizePage.add(8, new CodeDesc(Config
				.getProperty("personaliz.Page.DebitLimits"), Config
				.getPropertyFromBundle("global.personaliz.Page.DebitLimits",
						language, country)));
		vtPersonalizePage.add(9, new CodeDesc(Config
				.getProperty("personaliz.Page.RequestList"), Config
				.getPropertyFromBundle("global.personaliz.Page.RequestList",
						language, country)));
		vtPersonalizePage.add(10, new CodeDesc(Config
				.getProperty("personaliz.Page.FullStatmentRequest"), Config
				.getPropertyFromBundle(
						"global.personaliz.Page.FullStatmentRequest", language,
						country)));
		vtPersonalizePage.add(11, new CodeDesc(Config
				.getProperty("personaliz.Page.ChequeBookRequest"), Config
				.getPropertyFromBundle(
						"global.personaliz.Page.ChequeBookRequest", language,
						country)));
		vtPersonalizePage.add(12, new CodeDesc(Config
				.getProperty("personaliz.Page.SupplementaryCard"), Config
				.getPropertyFromBundle(
						"global.personaliz.Page.SupplementaryCard", language,
						country)));
		// //vtPersonalizePage.add(13, new
		// CodeDesc(Config.getProperty("personaliz.Page.BillPaymentSchedule"),
		// Config.getPropertyFromBundle("global.personaliz.Page.BillPaymentSchedule",Config.getProperty("language.Persian"))));
		// vtPersonalizePage.add(13, new
		// CodeDesc(Config.getProperty("personaliz.Page.TermDeposit"),
		// Config.getPropertyFromBundle("global.personaliz.Page.TermDeposit",Config.getProperty("language.Persian"))));
		vtPersonalizePage.add(13, new CodeDesc(Config
				.getProperty("personaliz.Page.PaymentOrder"), Config
				.getPropertyFromBundle("global.personaliz.Page.PaymentOrder",
						language, country)));
		vtPersonalizePage.add(14, new CodeDesc(Config
				.getProperty("personaliz.Page.ContactUpdateRequest"), Config
				.getPropertyFromBundle(
						"global.personaliz.Page.ContactUpdateRequest",
						language, country)));
		vtPersonalizePage.add(15, new CodeDesc(Config
				.getProperty("personaliz.Page.UtilityBillPayments"), Config
				.getPropertyFromBundle(
						"global.personaliz.Page.UtilityBillPayments", language,
						country)));
		// vtPersonalizePage.add(19, new
		// CodeDesc(Config.getProperty("personaliz.Page.UtilityBillInquiry"),
		// Config.getPropertyFromBundle("global.personaliz.Page.UtilityBillInquiry",language,
		// country)));
		// /vtPersonalizePage.add(18, new
		// CodeDesc(Config.getProperty("personaliz.Page.CreditCardPayments"),
		// Config.getPropertyFromBundle("global.personaliz.Page.CreditCardPayments",language,
		// country)));
		vtPersonalizePage.add(16, new CodeDesc(Config
				.getProperty("personaliz.Page.ActivityLog"), Config
				.getPropertyFromBundle("global.personaliz.Page.ActivityLog",
						language, country)));
		vtPersonalizePage.add(17, new CodeDesc(Config
				.getProperty("personaliz.Page.AddComplaint"), Config
				.getPropertyFromBundle("global.personaliz.Page.AddComplaint",
						language, country)));
		vtPersonalizePage.add(18, new CodeDesc(Config
				.getProperty("personaliz.Page.AlertsAndNotifications"), Config
				.getPropertyFromBundle(
						"global.personaliz.Page.AlertsAndNotifications",
						language, country)));
		vtPersonalizePage.add(19, new CodeDesc(Config
				.getProperty("personaliz.Page.Personalize"), Config
				.getPropertyFromBundle("global.personaliz.Page.Personalize",
						language, country)));
		vtPersonalizePage.add(20, new CodeDesc(Config
				.getProperty("personaliz.Page.CustomerInfo"), Config
				.getPropertyFromBundle("global.personaliz.Page.CustomerInfo",
						language, country)));
		vtPersonalizePage.add(21, new CodeDesc(Config
				.getProperty("personaliz.Page.ChangePassword"), Config
				.getPropertyFromBundle("global.personaliz.Page.ChangePassword",
						language, country)));
		vtPersonalizePage.add(22, new CodeDesc(Config
				.getProperty("personaliz.Page.GeneratePin"), Config
				.getPropertyFromBundle("global.personaliz.Page.GeneratePin",
						language, country)));
		// ashrafi changed vtPersonalizePage 86/01/02

		return vtPersonalizePage;

	}

	public static Vector FieldNotification() {

		String language = Config.getProperty("language.Persian");
		String country = Config.getProperty("country.Iran");

		Vector vtFieldNotification = new Vector();
		// /vtFieldNotification.add(0, new
		// CodeDesc(Config.getProperty("ibFieldSignature.OnceADay"),Config.getPropertyFromBundle("ibFieldSignature.OnceADay",language,
		// country)));
		// vtFieldNotification.add(1, new
		// CodeDesc(Config.getProperty("ibFieldSignature.OnceAWeek"),
		// Config.getPropertyFromBundle("ibFieldSignature.OnceAWeek",language,
		// country)));
		// vtFieldNotification.add(2, new
		// CodeDesc(Config.getProperty("ibFieldSignature.OnceAMonth"),
		// Config.getPropertyFromBundle("ibFieldSignature.OnceAMonth",language,
		// country)));
		vtFieldNotification.add(0, new CodeDesc("day", Config
				.getPropertyFromBundle("ibFieldSignature.OnceADay", language,
						country)));
		vtFieldNotification.add(1, new CodeDesc("week", Config
				.getPropertyFromBundle("ibFieldSignature.OnceAWeek", language,
						country)));
		vtFieldNotification.add(2, new CodeDesc("month", Config
				.getPropertyFromBundle("ibFieldSignature.OnceAMonth", language,
						country)));
		return vtFieldNotification;

	}

	// ashrafi create RRN class 860621
	public static class RRN {
		// private static String[] rrn1;
		private static String rrn;

		public static void setRRN(String rrnNumber) {
			rrn = rrnNumber;

		}

		public static String getRRN() {
			return rrn;

		}
	}

	public static boolean isEmpty(Object str) {

		if (str == null || ((String) str).length() == 0) {
			return true;

		}
		if ((String) str.toString().trim() == null) {

			return true;

		}

		else {

			return false;
		}

	}

	public static String nvl(String val) {
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	public static String charVal(String str) {
		if (str == null) {
			return "";
		}

		String val = trimConvert(str);
		// val = val.replaceAll("[^" + FARSI_CHARS + "]", " ");
		// val = removeWithSpaces(val);
		// val = ReplaceSpecialCharacter(val);
		return val;
	}

	public static String charVal2(String str) {
		if (str == null) {
			return "";
		}

		String val = trimConvert(str);
		val = removeWithSpaces(val);
		return val;
	}

	public static String charVal3(String str) {
		String val = removeWithSpaces(str);

		if (!(val.matches("[1234567890]*"))) {
			val = "";
		}
		return val;
	}

	public static String removeWithSpaces(String str) {
		if (str == null) {
			return "";
		}

		String val = str.trim();
		int len;

		do {
			len = val.length();
			val = val.replaceAll("\\s\\s", " ");
		} while (len > val.length());

		val = val.replaceAll("\\s", "ù"); // "" is not empty, it contains
		// nulSpace
		for (int i = 0; i < FARSI_NOT_JOIN_CHARS.length(); i++) {
			String ch = strMid(FARSI_NOT_JOIN_CHARS, i, 1);
			val = val.replaceAll(ch + "ù", ch);
		}
		return val;
	}
	
	public static String trimConvert(String str) {
		try {
			str = str.trim();

			String c = "Ì";
			String c1 = "" + (char) 1740;
			str = str.replaceAll("&#1740;", c);
			str = str.replaceAll(c1, c);
			return str;
		} catch (Exception e) {
			return str;
		}

	}

	public static String strMid(String str, int pos) {
		if (str == null) {
			return null;
		}

		if (pos < 0 || pos >= str.length()) {
			return "";
		}
		return str.substring(pos, str.length());
	}

	public static String strMid(String str, int pos, int len) {
		if (str == null) {
			return null;
		}

		if (pos < 0 || pos >= str.length() || len <= 0) {
			return "";
		}

		if (str.length() <= (pos + len)) {
			return strMid(str, pos);
		} else {
			return str.substring(pos, pos + len);
		}
	}

	

	public static String formatRecordNumber(int iRecordNumber, int iRequiredSize) {
		String strRecordNumber = new Integer(iRecordNumber).toString();

		while (strRecordNumber.length() < iRequiredSize) {
			strRecordNumber = "0" + strRecordNumber;

		}

		return strRecordNumber;

	}

	/**
	 * Returns the current time
	 */
	public static String getCurrentTime() {
		Calendar calendar = Calendar.getInstance();

		String timeValue = ""
				+ formatRecordNumber(calendar.get(Calendar.HOUR_OF_DAY), 2)
				+ formatRecordNumber(calendar.get(Calendar.MINUTE), 2)
				+ formatRecordNumber(calendar.get(Calendar.SECOND), 2);

		return timeValue;

	}

	/**
	 * Returns the current Date
	 */
	public static String getCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		String dateValue = calendar.get(Calendar.YEAR) + ""
				+ formatRecordNumber((calendar.get(Calendar.MONTH) + 1), 2)
				+ ""
				+ formatRecordNumber(calendar.get(Calendar.DAY_OF_MONTH), 2);

		return dateValue;

	}

	public String getHostAddress() {
		String hostAddress = "";
		try {

			hostAddress = InetAddress.getLocalHost().getHostAddress();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} finally {
			//nothing
			
		}
		return hostAddress;
	}


	public static int genShetabMod10Digit(String data) {
		int pos;
		int factor;
		int n;
		int sum;
		int prduc;
		int firstf, endf, perviousf;
		int modsum;
		
		if (data.length() == 0 || !DateFarsi.isNumeric(data, 19)) {
			return -1;

		}

		pos = data.length();

		factor = 2;
		sum = 0;
		prduc = 0;
		modsum = 0;
		
		while (pos > 0) {
			n = Integer.parseInt(data.substring(pos - 1, pos));
			prduc = n * factor;
			firstf = Integer.parseInt(Integer.toString(prduc).substring(0, 1));
			if (Integer.toString(prduc).length() == 0
					|| Integer.toString(prduc).length() == 1)
				endf = 0;
			else
				endf = Integer.parseInt("0"
						+ Integer.toString(prduc).substring(1, 2));

			if (pos > 1) {
				perviousf = Integer.parseInt(data.substring(pos - 2, pos - 1));
			} else {
				perviousf = 0;
			}
			sum = sum + firstf + endf + perviousf;
			pos = pos - 2;
		}
		modsum = sum % 10;
		if (modsum == 0) {
			return 0;
		}
		return (((sum / 10) + 1) * 10) - sum;
	}

	public static Vector BankImds() {

		Vector vtBankImds = new Vector();
		String prefix = "bankImds";
		int bankImdsCount = Integer.parseInt(Config.getProperty(prefix + "."
				+ "Count"));
		for (int i = 0; i < bankImdsCount; i++) {
			vtBankImds.add(i, new ImdsInfo(Config.getProperty(prefix + "."
					+ Integer.toString(i) + "." + "Id"), Config
					.getProperty(prefix + "." + Integer.toString(i) + "."
							+ "Name")));
		}
		return vtBankImds;

	}

	// added by roohi 89/06/21
	public static Vector Bank19DigitsImds() {

		Vector vtBank19DigitsImds = new Vector();
		String prefix = "bank19DigitsImds";
		int bank19DigitsImdsCount = Integer.parseInt(Config.getProperty(prefix
				+ "." + "Count"));
		for (int i = 0; i < bank19DigitsImdsCount; i++) {
			vtBank19DigitsImds.add(i, new ImdsInfo(Config.getProperty(prefix
					+ "." + Integer.toString(i) + "." + "Id"), Config
					.getProperty(prefix + "." + Integer.toString(i) + "."
							+ "Name")));
		}
		return vtBank19DigitsImds;
	}
	
	public static Vector getNameAndIdConfig(String prefix) {

		Vector vector = new Vector();
		int Count = Integer.parseInt(Config.getProperty(prefix + "."
				+ "Count"));
		for (int i = 0; i < Count; i++) {
			vector.add(i, new ImdsInfo(Config.getProperty(prefix + "."
					+ Integer.toString(i) + "." + "Id"), Config
					.getProperty(prefix + "." + Integer.toString(i) + "."
							+ "Name")));
		}
		return vector;
	}
	
	
	
	

	public static class ImdsInfo {
		private String id;

		private String name;

		public ImdsInfo() {
		}

		public ImdsInfo(String idVal, String nameVal) {
			id = idVal;
			name = nameVal;
		}

		public String getId() {
			return id;
		}

		public void setId(String value) {
			id = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String value) {
			name = value;
		}
	}
	public static String  getRemoteAddress(HttpServletRequest request,MessageContext msgContext){
		String remoteIP="";
		if(request !=null){
			remoteIP = request.getRemoteAddr();
		}
		if(msgContext !=null){
			request = (HttpServletRequest)msgContext.getProperty((HTTPConstants.MC_HTTP_SERVLETREQUEST));
			remoteIP = request.getRemoteAddr();
		}
		
		return remoteIP;
	}

	public static boolean isSuccessResponseCode(HttpServletRequest request,String responseCode,String message){
		boolean isSuccess=false;
		if (Constants.RESPONSE_CODE.SUCCEED.equalsIgnoreCase(responseCode)) {
			
			isSuccess=true;
		}
		else if(Constants.RESPONSE_CODE.ERROR_IN_PARAMETER.equalsIgnoreCase(responseCode)){
			if(AmbitUtility.isEmpty(message))
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
			"global.errorInParameter");
			else
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
				message);
			
		}
		else if (Constants.RESPONSE_CODE.INTERNAL_ERROR.equalsIgnoreCase(responseCode)) {
			if(AmbitUtility.isEmpty(message))
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
					"global.internalError");
			else
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
				message);

			
		}
		else if(Constants.RESPONSE_CODE.DUPLICATE_RECORD.equalsIgnoreCase(responseCode)){
			
			if(AmbitUtility.isEmpty(message))
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
			"global.duplicateRecord");
			else
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
				message);
			
		}
		else if(Constants.RESPONSE_CODE.RECORD_NOT_FOUND.equalsIgnoreCase(responseCode)){
			if(AmbitUtility.isEmpty(message))
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
			"global.noRecordFound");
			else
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
						message);
			
		}
		else if(Constants.RESPONSE_CODE.MAX_RECORD_EXCEEDED.equalsIgnoreCase(responseCode)){
			if(AmbitUtility.isEmpty(message))
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
			"global.isMoreThanMax");
			else
				ErrorsUtility.AddGlobalErrorToActionMessages(request,
						message);
			
		}
		else if(Constants.RESPONSE_CODE.SERVICE_PROVIDER_NOT_AVAILABLE.equalsIgnoreCase(responseCode)){
			
			ErrorsUtility.AddGlobalErrorToActionMessages(request,
			"global.WebServiceNotAvailble");
			
			
		}
		return isSuccess;
	}
	public static boolean isValidPassword(String str) {
	    if (str.matches("[a-zA-Z0-9]*")) {
	      return true;
	    }
	    else {
	      return false;
	    }
	  }
	
	public static void setLocalePaths(HttpServletRequest request){
	//	 Locale locale = (Locale) request.getSession().getAttribute("org.apache.struts.action.LOCALE");
	//	 String language = locale.getLanguage();
		String language = Config.getProperty( "language.Persian" );
		 String country = Config.getProperty( "country.Iran" );
		String pathCss="css/";
		   String pathImages="images/";
		 /*  if(isAdminContextPath(request)){
			   pathCss="../css/";
			   pathImages="../images/";
			   
		   }*/
		//   if(language.equals(Config.getProperty( "language.Persian" ).toString())){
			  
				  // request.getSession().setAttribute("org.apache.struts.action.LOCALE", new Locale(language, country));
				   
				   request.getSession().setAttribute( Constants.JspConstants.PATH_CSS , pathCss +  language + "_" + country + "/" );
				   request.getSession().setAttribute( Constants.JspConstants.PATH_IMAGE , pathImages + language + "_" + country + "/" );
				   request.getSession().setAttribute( Constants.JspConstants.PAGE_DIR  , Constants.JspConstants.RIGHT_DIRECTION );
				  // request.getSession().setAttribute("org.apache.struts.action.LOCALE", new Locale(language, country));
		/*   }
		   else{
			   country = Config.getProperty( "country.America") ;
			//   request.getSession().setAttribute("org.apache.struts.action.LOCALE", new Locale(language, country));
			  // setLocale(request, new Locale(language, country));
			   request.getSession().setAttribute( Constants.JspConstants.PATH_CSS , pathCss +  language + "_" + country + "/" );
			   request.getSession().setAttribute( Constants.JspConstants.PATH_IMAGE , pathImages + language + "_" + country + "/" );
			   request.getSession().setAttribute( Constants.JspConstants.PAGE_DIR  , Constants.JspConstants.LEFT_DIRECTION );
				
		   }*/
		
	}
	public static boolean isAdminContextPath(HttpServletRequest request){
		boolean result=false;
		String adminContextPath="/adminConsole";
		String subUri ="";
		String uri = request.getRequestURI();
		if (uri.length()>18){
		 subUri=uri.substring(0,19);
		}
		

       String path = request.getContextPath();

		String targetURI = uri.substring(path.length(), uri.length());
		String target = targetURI.toLowerCase();	
		if (target.startsWith(adminContextPath.toLowerCase())) {
			result=true;
		}	
		return result;
	}
	public static boolean userHasAccess(String permissionPage,
			String permissionActionType,HashMap access) {
		boolean hasAccess = false;
		Set set = access.entrySet();
		Iterator i = set.iterator();
		Permissions permission = new Permissions();
		while (i.hasNext()) {
			Map.Entry element = (Map.Entry) i.next();
			permission = (Permissions) element.getValue();
			if (permission.getPermissionParentId() == Integer
					.parseInt(permissionPage) && permission.getPermissionActionTypeId().equalsIgnoreCase(permissionActionType)){
				hasAccess=true;
				break;
			}
		}
		return hasAccess;
	}

	public static String getAllowedActions(String permissionPage,HashMap access ) {
		String allowedActions = "";
		Set set = access.entrySet();
		Iterator i = set.iterator();
		Permissions permission = new Permissions();

		while (i.hasNext()) {
			Map.Entry element = (Map.Entry) i.next();
			permission = (Permissions) element.getValue();

			if (permission.getPermissionParentId() == Integer
					.parseInt(permissionPage)
					&& permission.getPermissionActionTypeId() != "")
				
			allowedActions += permission.getPermissionActionTypeId();
		}

		return allowedActions;
	}
	public static String addTrailing(String data, int len, String trailingChar) {
		int dataLength = data.length();

		for (int i = dataLength; i < len; i++)
			data = data + trailingChar;
		return data;
	}
	
	public static Vector Services() {

		Vector vtServices = new Vector();
		String prefix = "Services";
		int servicesCount = Integer.parseInt(Config.getProperty(prefix + "."
				+ "Count"));
		for (int i = 0; i < servicesCount; i++) {
			vtServices.add(i, new CodeDesc(Config.getProperty(prefix + "."
					+ Integer.toString(i) + "." + "Id"), Config
					.getProperty(prefix + "." + Integer.toString(i) + "."
							+ "Name")));
		}
		return vtServices;

	}
	
	public static Vector ResponseCodes() {

		Vector vtResponseCodes = new Vector();
		String prefix = "ResponseCodes";
		int responseCodesCount = Integer.parseInt(Config.getProperty(prefix + "."
				+ "Count"));
		for (int i = 0; i < responseCodesCount; i++) {
			vtResponseCodes.add(i, new CodeDesc(Config.getProperty(prefix + "."
					+ Integer.toString(i) + "." + "Id"), Config
					.getProperty(prefix + "." + Integer.toString(i) + "."
							+ "Name")));
		}
		return vtResponseCodes;

	}
	
	public static String getDescOfCode(String code,Vector vtCodeDesc) {

		int vectorPointer = 0;
		String desc = "";
		if (!StringUtils.isEmpty(code)) {

				vectorPointer = 0;
				AmbitUtility.CodeDesc codeDescInf = null;
				boolean Valid = false;
				for (; vectorPointer < vtCodeDesc.size(); vectorPointer++) {
					codeDescInf = (AmbitUtility.CodeDesc) vtCodeDesc
							.elementAt(vectorPointer);
					if (code.equalsIgnoreCase(codeDescInf.getCode())) {
						Valid = true;
						vectorPointer++;
						break;
					}
				}

				if (Valid == true) {
					desc = codeDescInf.getDesc();
				}
		}
		return desc;
	}
	public static boolean isAlphaNumeric(String str) {
		if (str.matches("[ a-zA-Z0-9]*")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isAlphanumeric(String str, int maxLen,int minLength) {
		if (str == null || (str.length() == 0 || str.length() > maxLen || str.length() < minLength)) {
			return false;
		} else {
			return str.matches("[a-zA-Z0-9]*");
		}
	}
	
	public static boolean isValidPasswordPolicy(String pwd){
		String pwdPattern = "^(?=.*[A-Z])(?=.*\\d)(.{8,15})$";
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(pwdPattern);
		matcher = pattern.matcher(pwd);
		return matcher.matches();
	}
	
	
	public static boolean isAlphaFarsiNumeric(String str) {
		
		
		if (str.matches(Alpha_Numeric_Farsi)  ) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isAlphaFarsiNumeric(String str, int maxLen,int minLength) {
		if (str == null || (str.length() == 0 || str.length() > maxLen || str.length() < minLength)) {
			return false;
		} else {
			return str.matches(Alpha_Numeric_Farsi);
		}
	}
	
	public static boolean isNumeric(String str, int maxLen) {
		if (str == null || (str.length() == 0 || str.length() > maxLen)) {
			return false;
		} else {
			return str.matches("[0-9]*");
		}
	}
	

	
}
