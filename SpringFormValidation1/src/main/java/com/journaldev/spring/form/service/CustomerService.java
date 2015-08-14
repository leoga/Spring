package com.journaldev.spring.form.service;


import java.sql.Timestamp;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.journaldev.spring.form.model.Customer;
import com.journaldev.spring.form.dao.CustomerDao;

/**
 * Service for customer entity
 * <p>
 */
@Service
@Transactional
public class CustomerService {
	
    /**
     * connection to Customer DAO
     * <p>
     */
	@Autowired 
	private CustomerDao customerDAO;
	
    /**
     * Add a customer in the DB
     * <p>
     * @param customer the customer to save
     */
    public void addCustomer(final Customer customer){
    		customerDAO.addCustomer(customer);
    }
    
    /**
     * Get all the customers stored in the DB
     * <p>
     */
	public List<Customer> getAllCustomer() {		
		return customerDAO.getAllCustomers();		
	}
	
    /**
     * Get the customer by it's user
     * <p>
     * @param user customer's user
     */
	public List<Customer> getCustomerLogin(final String user){
		return customerDAO.getCustomerLogin(user);
	}
	
	 /**
	  * Update the customer data stored in the DB
	  * <p>
	  * @param update customer to update
	  */
	public void updateCustomer(final Customer update){
		customerDAO.updateCustomer(update);
	}
	
	 /**
	  * Get the customers by limits, used for pagination
	  * <p>
	  * @param idEmployee the id of the related employee
	  * @param first offset 
	  * @param last number of registers to return
	  */
	public List<Customer> getCustomersbyLimit(final int idEmployee, final int first, final int last){
		return customerDAO.getCustomersbyLimit(idEmployee, first, last);
	}
	
	 /**
	  * Get the customers related by idEmployee
	  * <p>
	  * @param idEmployee the id of the related employee
	  */
	public List<Customer> getCustomersbyID(final int idEmployee){
		return customerDAO.getCustomersbyId(idEmployee);
	}
	
	 /**
	  * Get customer by its id
	  * <p>
	  * @param idCustomer customer's id
	  */
	public List<Customer> getCustomerbyId(final int idCustomer){
		return customerDAO.getCustomerbyId(idCustomer);
	}
	
	 /**
	  * Get a list of customers by its name
	  * <p>
	  * @param idEmployee the id of the related employee
	  * @param byname name used in the query
	  */
	public List<Customer> getCustomersbyName(final int idEmployee, final String byname) {
		return customerDAO.getCustomersbyName(idEmployee, byname);
	}
	
	 /**
	  * Get a list of customers by its age
	  * <p>
	  * @param idEmployee the id of the related employee
	  * @param byagehigh used in the query
	  * @param byagelow  used in the query
	  */
	public List<Customer> getCustomersbyAge(final int idEmployee,final int byagehigh,final int byagelow) {
		return customerDAO.getCustomersbyAge(idEmployee, byagehigh, byagelow);
	}
	
	 /**
	  * Get a list of customers by its date of creation
	  * <p>
	  * @param idEmployee the id of the related employee
	  * @param bydatehigh used in the query
	  * @param bydatelow  used in the query
	  */
	public List<Customer> getCustomersbyDate(final int idEmployee, final Timestamp bydatehigh, final Timestamp bydatelow) {
		return customerDAO.getCustomersbyDate(idEmployee, bydatehigh, bydatelow);
	}

	 /**
	  * Get a list of customers by its age and name
	  * <p>
	  * @param idEmployee the id of the related employee
	  * @param byname used in the query
	  * @param byagehigh used in the query
	  * @param byagelow  used in the query
	  */
	 public List<Customer> getCustomersbyNameAge(final int idEmployee, final String byname, final int byagehigh, final int byagelow) {
		 return customerDAO.getCustomersbyNameAge(idEmployee, byname, byagehigh, byagelow);
	 }
	 
	 /**
	  * Delete customer from DB
	  * <p>
	  * @param customer customer to delete
	  */
	 public void deleteCustomer(final Customer customer) {
		 customerDAO.deleteCustomer(customer);
	 }
	 
	 /**
	  * Create a new customer in the DB
	  * <p>
	  * @param customer customer to store
	  */
	 public void createCustomer(final Customer customer) {
		 customerDAO.createCustomer(customer);
	 }
	 
	 /**
	  * Get a customer list by name and limits in order to paginate
	  * <p>
	  * @param idEmployee 	the id of the related employee
	  * @param byname 		used in the query
	  * @param first 		offset
	  * @param last  		number of registers to return
	  */
	 public List<Customer> getCustomersbyNameLimit(final int idEmployee, final String byname, final int first, final int last) {
		 return customerDAO.getCustomersbyNameLimit(idEmployee, byname, first, last);
	 }
	 
	 /**
	  * Call to the same function in CustomerDao
	  */
	 public List<Customer> getCustomersbyAgeLimit(final int idEmployee, final int byagehigh, final int byagelow, final int first, final int last) {
		 return customerDAO.getCustomersbyAgeLimit(idEmployee, byagehigh, byagelow, first, last);
	 }
	 
	 /**
	  * Call to the same function in CustomerDao
	  */
	 public List<Customer> getCustomersbyDateLimit(final int idEmployee, final Timestamp bydatehigh, final Timestamp bydatelow, final int first, final int last) {
		 return customerDAO.getCustomersbyDateLimit(idEmployee, bydatehigh, bydatelow, first, last);
	 }
	 
	 /**
	  * Call to the same function in CustomerDao
	  */
	 public List<Customer> getCustomersbyNameAgeLimit(final int idEmployee, final String byname, final int byagehigh, final int byagelow, final int first, final int last) {
		 return customerDAO.getCustomersbyNameAgeLimit(idEmployee, byname, byagehigh, byagelow, first, last);
	 }
		 
}