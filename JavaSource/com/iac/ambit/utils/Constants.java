package com.iac.ambit.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Constants {

	public final Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	private final void writeObject(ObjectOutputStream out) throws IOException {
		throw new IOException("Object cannot be serialized");
	}

	private final void readObject(ObjectInputStream in) throws IOException {
		throw new IOException("Class cannot be Deserialized");
	}

	private Constants() {

	}

	public static String farsiYears[] = { "", "87", "88", "89", "90", "91",
			"92", "93", "94", "95", "96", "97", "98", "99" };

	public static String englishYears[] = { "", "2008", "2009", "2010", "2011",
			"2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019",
			"2020" };

	public static String months[] = { "", "1", "2", "3", "4", "5", "6", "7",
			"8", "9", "10", "11", "12" };
	
	public static String hours[] = { "", "1", "2", "3", "4", "5", "6", "7",
		"8", "9", "10", "11", "12"  , "13", "14","15","16","17","18",
		"19","20","21",	"22", "23"};

	public static final String DATA_SEPARATOR_SPLIT = "=";

	public static final String TEMP_FILE_PATH = "tempFile";

	public static String SYSTEM_PERMISSION_URI_MAP = "SYSTEM_PERMISSION_URI_MAP";

	public static String ACCOUNT_SUMMARY = "ACCOUNT_SUMMARY";

	public static String zeroValue = "0";

	public static String equalSign = "=";

	public static String pipeSign = "|";

	public static String semicolonSign = ";";

	public static String commaSign = ",";

	public static String caretSign = "^";

	public static String dashSign = "-";

	public static String underLineSign = "_";

	public static String dollarSign = "$";

	public static String starSign = "*";

	public static String slashSign = "/";

	public static String TRUE = "true";

	public static String FALSE = "false";
	
	public static String GRID = "grid";
	
	public static String AUTOMATIC = "automatic";
	
	public static String CUSTOMER_IN_SESSION = "CUSTOMER_IN_SESSION";

	public static String CHECKBOX_SELECTED = "on";

	public static String CHECKBOX_NONE_SELECTED = "off";
	
	public static String LOGIN_PAGE = "loginPage";

	public static String LOGIN_HANDLER = "loginHandler";

	public static String LOGOUT_HANDLER = "logoutHandler";

	public static String LOCALE_HANDLER = "localeHandler";

	public static String INSECURE_PAGES = "insecurePages";

	public static String SECURE_FILETYPE = "secureFileType";

	public static String ADMIN_IN_SESSION = "ADMIN_IN_SESSION";

	public static String INTERNAL_Key = "0E329232EA6D0D73";
	
	public static String TERMINALS = "Terminals";
	
	public static String SERVICES = "Services";
	
	public static String NEW = "new";
	
	public static String OLD = "old";
	
	public static String NEWALLOWED = "newAllowed";
	
	public static String MENU_XML_FILE_IN_APPLICATION = "MENU_XML_FILE_IN_APPLICATION";
	
	public static   final  String   BLACK_LIST_TRANS = "BLACK_LIST_TRANS";
	
	public static   final  String   BLACK_LIST_MONITORING = "BLACK_LIST_MONITORING";
	
	public static   final  String   BLACK_LIST_MANAGE = "BLACK_LIST_MANAGE";
	
	public static int MAX_SECURE_LEN_1 = 30;
	
	public static int MAX_SECURE_LEN_2= 50;
	
	// jazimagh 1389/05/18
	public static class DataSource {
		public static final String DATABASE_LIVE = "DATABASE_LIVE";

		public static final String DATABASE_ARCHIVE = "DATABASE_ARCHIVE";

	}

	public static class JspConstants {
		public static String RIGHT_DIRECTION = "rtl";

		public static String LEFT_DIRECTION = "ltr";

		public static final String PAGE_DIR = "pageDir";

		// CSS File
		public static final String PATH_CSS = "pathCss";

		// IMAGE File
		public static final String PATH_IMAGE = "pathImage";

		public static final String TAG_LEFT_ALIGN = "left";

		public static final String TAG_RIGHT_ALIGN = "right";

		public static final String TAG_CENTER_ALIGN = "center";

		public static final String TAG_JUSTIFY_ALIGN = "justify";
		
		
	}

	public class CODE_ACTIVE_FLAG {

		public static final String ACTIVE = "1";

		public static final String INACTIVE = "2";

	}

	public class METHOD {
		public static final String ADD = "add";

		public static final String EDIT = "edit";
		
		public static final String DELETE = "delete";

		public static final String VIEW = "view";

		public static final String SEARCH = "search";
		
		public static final String CANCEL = "operationCancel";
		
		public static final String OPERATION_Ok = "operationOk";
		
		public static final String LIMITS = "LIMITS";
		
		public static final String TRANSACTIONS = "transactions";
		
		public static final String SEARCH_PAN = "searchPan";
		
		public static final String CONFIRM_REFRESH_INTERVAL_IN_SEC = "confirmRefreshIntervalInSec";
		
		public static final String CONFIRM_ALERT_INFO = "confirmAlertInfo";
		
		public static final String REFRESH = "refresh";
		
		

	}

	public static class RESPONSE_CODE {
		public static String SUCCEED = "00";

		public static String ERROR_IN_PARAMETER = "01";

		public static String DUPLICATE_RECORD = "02";

		public static String INTERNAL_ERROR = "03";

		public static String RECORD_NOT_FOUND = "04";
		
		public static String MAX_RECORD_EXCEEDED= "05";
		
		public static String SERVICE_PROVIDER_NOT_AVAILABLE= "06";
	}
	
	
	public static class SERVICE_USER_PASS {
		
		public static String USER = "Merchant";

		public static String PASS = "test";
	}
	
	public static class TRANSACTION_RESPONSE_CODE {
		public static String TRANSACTIONALLOWED= "000";
	}

	public static class MenuItemFixed {
		public static final String FIRST_PAGE = "3";
		// other items are 998,997,...
	}

	public static int ATM_NETWORK = 1;

}
