package com.journaldev.spring.form.search;

import java.util.Date;

/**
 * Fields for search form
 * <p>
 */
public class SearchFields {
	
	/**
	 * Used for delete customers
	 * <p>
	 */
	private String[] customers;
	
	/**
	 * Used for modify customers
	 * <p>
	 */
	private String customer;
	
	/**
	 * Used for search form
	 * <p>
	 */
	private String byname;
	
	/**
	 * Used for search form
	 * <p>
	 */
	private int byagehigh;
	
	/**
	 * Used for search form
	 * <p>
	 */
	private int byagelow;
	
	/**
	 * Used for search form
	 * <p>
	 */
	private Date bydatelow;
	
	/**
	 * Used for search form
	 * <p>
	 */
	private Date bydatehigh;
	
	/**
	 * Getter for byname
	 * <p>
	 */
	public String getByname() {
		return byname;
	}
	
	/**
	 * Setter for byname
	 * <p>
	 */
	public void setByname(final String byname) {
		this.byname = byname;
	}
	
	/**
	 * Getter for byagehigh
	 * <p>
	 */
	public int getByagehigh() {
		return byagehigh;
	}
	
	/**
	 * Setter for byagehigh
	 * <p>
	 */
	public void setByagehigh(final int byagehigh) {
		this.byagehigh = byagehigh;
	}
	
	/**
	 * Getter for byagelow
	 * <p>
	 */
	public int getByagelow() {
		return byagelow;
	}
	
	/**
	 * Setter for byagelow
	 * <p>
	 */
	public void setByagelow(final int byagelow) {
		this.byagelow = byagelow;
	}
	
	/**
	 * Getter for bydatelow
	 * <p>
	 */
	public Date getBydatelow() {
		return bydatelow;
	}
	
	/**
	 * Setter for bydatelow
	 * <p>
	 */
	public void setBydatelow(final Date bydatelow) {
		this.bydatelow = bydatelow;
	}
	
	/**
	 * Getter for bydatehigh
	 * <p>
	 */
	public Date getBydatehigh() {
		return bydatehigh;
	}
	
	/**
	 * Setter for bydatehigh
	 * <p>
	 */
	public void setBydatehigh(final Date bydatehigh) {
		this.bydatehigh = bydatehigh;
	}
	
	/**
	 * Getter for customers
	 * <p>
	 */
	public String[] getCustomers() {
		return customers;
	}
	
	/**
	 * Setter for customers
	 * <p>
	 */
	public void setCustomers(final String[] customers) {
		this.customers = customers;
	}
	
	/**
	 * Getter for customer
	 * <p>
	 */
	public String getCustomer() {
		return customer;
	}
	
	/**
	 * Setter for customer
	 * <p>
	 */
	public void setCustomer(final String customer) {
		this.customer = customer;
	}
}
