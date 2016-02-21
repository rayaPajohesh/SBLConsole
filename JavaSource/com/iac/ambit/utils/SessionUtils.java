package com.iac.ambit.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import proxy.com.iac.ambit.model.Customer;

/**
 * 
 * @author Hashir Ahmed
 */
public class SessionUtils {
	private static Hashtable user = new Hashtable();

	
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
	private SessionUtils() {

	}

	public static List getAllLoggedInCustomers() {
		ArrayList userList = new ArrayList(user.size());
		CustomerSession customerSession;
		Iterator itr = user.values().iterator();
		while(itr.hasNext()){
			customerSession=(CustomerSession)itr.next();	
			userList.add(customerSession.getCustomer());
		}
		return userList;

	}

	public static boolean isCustomerLoggedIn(Customer customer) {
		return user.containsKey(customer.getUserId());

	}

	public static boolean isCustomerLoggedIn(String userId) {
		return user.containsKey(userId);

	}
	
	

	public static synchronized boolean logCustomer(Customer customer,
			HttpSession session) {
		if (!isCustomerLoggedIn(customer)) {
			user.put(customer.getUserId(), new CustomerSession(customer, session));

			return true;

		}

		return false;

	}

	

	/**
	 * Get session object for the customer
	 * 
	 * @return
	 * @param customer
	 */
	public static HttpSession getSessionForCustomer(Customer customer) {
		if (isCustomerLoggedIn(customer)) {
			return ((CustomerSession)user.get(customer.getUserId())).getSession();

		}

		return null;

	}

	public static boolean logOutCustomer(Customer customer) {
		if (isCustomerLoggedIn(customer)) {
			user.remove(customer.getUserId());

			return true;

		}

		return false;

	}

	

	public static Customer getCustomer(String pan) {
		return ((CustomerSession)user.get(pan)).getCustomer();

	}

	
	public static boolean existCustomerSessionId(String sessionId) {
		boolean exist=false;
		
		CustomerSession customerSession;
		Iterator itr = user.values().iterator();
		while(itr.hasNext()){
			customerSession=(CustomerSession)itr.next();	
			if (customerSession.session.getId().equals(sessionId)){
				exist=true;
				break;
			}
		}			
		return exist;
	}
	
	public static HttpSession getNewSession(HttpServletRequest request){
		HttpSession session = null;
		session = request.getSession(true);
		System.out.println("********  old session id :" + session.getId() );
		Object [] sessionValues;
		String sessionName[] = session.getValueNames();
		sessionValues =new Object[sessionName.length];
		for (int i=0;i<sessionName.length;i++){				
			sessionValues[i] = session.getValue(sessionName[i]);
							
		}
		session.invalidate();
		session = request.getSession(true);
		 System.out.println("********  new session id :" + session.getId() );
		 for (int i=0;i<sessionName.length;i++){
			 session.putValue(sessionName[i],sessionValues[i]);
		 }
		return session;
	}
	
	

	/**
	 * This class is merely a Data Structure for holding customer & session
	 * objects
	 * 
	 * @author Hashir Ahmed
	 */
	private static class CustomerSession {
		private Customer customer;

		private HttpSession session;

		CustomerSession(Customer customer, HttpSession session) {
			this.customer = customer;
			this.session = session;

		}

		void setCustomer(Customer customer) {
			this.customer = customer;

		}

		void setSession(HttpSession session) {
			this.session = session;

		}

		Customer getCustomer() {
			return this.customer;

		}

		HttpSession getSession() {
			return this.session;

		}

	}

}
