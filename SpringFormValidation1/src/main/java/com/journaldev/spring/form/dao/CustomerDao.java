package com.journaldev.spring.form.dao;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.journaldev.spring.form.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * DAO for Customer entity
 * <p>
 */
@Repository
public class CustomerDao {
	
    /**
     * connection to DB
     * <p>
     */
	@Autowired
	private SessionFactory sessionFactory;
		 
    /**
     * Get all the customers stored in the DB
     * <p>
     */
	@SuppressWarnings("unchecked")
	public List<Customer> getAllCustomers() {
		final Session session = sessionFactory.getCurrentSession();		
		final Query query = session.createQuery("from Customer c order by id desc");
		//Query q = session.createQuery("select NAME from Customer");
		//final List<Customer> customerList = query.list(); 
	    return (List<Customer>) query.list();			
	}
	
    /**
     * Add a customer in the DB
     * <p>
     * @param customer the customer to save
     */
	public void addCustomer(final Customer customer) {
		final Session session = sessionFactory.getCurrentSession();	
		session.save(customer);
	}
	
    /**
     * Get the customer by it's user
     * <p>
     * @param user customer's user
     */
	 @SuppressWarnings("unchecked")
	 public List<Customer> getCustomerLogin(final String user) {
	 return sessionFactory.getCurrentSession()
	 		 .createQuery("FROM Customer c WHERE user=:user" )
	 		 .setString("user",user)
	 		 .list();
	 }
	 
	 /**
	  * Update the customer data stored in the DB
	  * <p>
	  * @param update customer to update
	  */
	 public void updateCustomer(final Customer update) {
		 sessionFactory.getCurrentSession().saveOrUpdate(update);
	 }
	 
	 /**
	  * Get the customers by limits, used for pagination
	  * <p>
	  * @param idEmployee the id of the related employee
	  * @param first offset 
	  * @param last number of registers to return
	  */	 
	 @SuppressWarnings("unchecked")
	 public List<Customer> getCustomersbyLimit(final int idEmployee,final int first,final int last) {
		 return sessionFactory.getCurrentSession()
			.createQuery("FROM Customer c WHERE idemployee=:ID")
			.setInteger("ID", idEmployee)
			.setFirstResult(first)
			.setMaxResults(last)
			.list();
	 }
	 
	 /**
	  * Get the customers related by idEmployee
	  * <p>
	  * @param idEmployee the id of the related employee
	  */
	 @SuppressWarnings("unchecked")
	 public List<Customer> getCustomersbyID(final int idEmployee) {
	 return sessionFactory.getCurrentSession()
	  .createQuery("FROM Customer c WHERE idemployee=:ID")
	  .setInteger("ID", idEmployee)
	  .list();
	 }
	 
	 /**
	  * Get a customer by its Id
	  * <p>
	  * @param idCustomer customer's id
	  */
	 @SuppressWarnings("unchecked")
	 public List<Customer> getCustomersbyIDcustomer(final int idCustomer) {
	 return sessionFactory.getCurrentSession()
	  .createQuery("FROM Customer c WHERE id=:ID")
	  .setInteger("ID", idCustomer)
	  .list();
	 }
	 
	 /**
	  * Get a list of customers by its name
	  * <p>
	  * @param idEmployee the id of the related employee
	  * @param byname name used in the query
	  */
	 @SuppressWarnings("unchecked")
	 public List<Customer> getCustomersbyName(final int idEmployee, final String byname) {
	 return sessionFactory.getCurrentSession()
	  .createQuery("FROM Customer c WHERE idemployee=:ID AND name LIKE :N")
	  .setInteger("ID", idEmployee)
	  .setString("N", "%"+byname+"%")
	  .list();
	 }
	 
	 /**
	  * Get a list of customers by its age
	  * <p>
	  * @param idEmployee the id of the related employee
	  * @param byagehigh used in the query
	  * @param byagelow  used in the query
	  */
	 @SuppressWarnings("unchecked")
	 public List<Customer> getCustomersbyAge(final int idEmployee, final int byagehigh, final int byagelow) {
	// System.out.printf("ID: %s High: %s Low: %s",idEmployee, byagehigh, byagelow);
	 return sessionFactory.getCurrentSession()
	  .createQuery("FROM Customer c WHERE idemployee=:id AND age BETWEEN :L AND :H")
	  .setParameter("id", idEmployee)
	  .setParameter("L", byagelow)
	  .setParameter("H", byagehigh)
	  .list();
	 }
	 
	 /**
	  * Get a list of customers by its date of creation
	  * <p>
	  * @param idEmployee the id of the related employee
	  * @param bydatehigh used in the query
	  * @param bydatelow  used in the query
	  */
	 @SuppressWarnings("unchecked")
	 public List<Customer> getCustomersbyDate(final int idEmployee, final Timestamp bydatehigh, final Timestamp bydatelow) {
	// System.out.printf("ID: %s High: %s Low: %s",idEmployee, byagehigh, byagelow);
	 return sessionFactory.getCurrentSession()
	  .createQuery("FROM Customer c WHERE idemployee=:id AND currentdate BETWEEN :L AND :H")
	  .setParameter("id", idEmployee)
	  .setParameter("L", bydatelow)
	  .setParameter("H", bydatehigh)
	  .list();
	 }
	 
	 /**
	  * Get a list of customers by its age and name
	  * <p>
	  * @param idEmployee the id of the related employee
	  * @param byname used in the query
	  * @param byagehigh used in the query
	  * @param byagelow  used in the query
	  */
	 @SuppressWarnings("unchecked")
	 public List<Customer> getCustomersbyNameAge(final int idEmployee, final String byname, final int byagehigh, final int byagelow) {
	 return sessionFactory.getCurrentSession()
	  .createQuery("FROM Customer c WHERE idemployee=:ID AND age BETWEEN :L AND :H AND name LIKE :N")
	  .setInteger("ID", idEmployee)
	  .setInteger("H", byagehigh)
	  .setInteger("L", byagelow)
	  .setString("N", "%"+byname+"%")
	  .list();
	 }
	 
	 /**
	  * Delete customer from DB
	  * <p>
	  * @param customer customer to delete
	  */
	 public void deleteCustomer(final Customer customer) {
		 sessionFactory.getCurrentSession().delete(customer);
	 }
	 
	 /**
	  * Create a new customer in the DB
	  * <p>
	  * @param customer customer to store
	  */
	 public void createCustomer(final Customer customer) {
	 sessionFactory.getCurrentSession().save(customer);
	 }
	 
	 /**
	  * Get a customer list by name and limits in order to paginate
	  * <p>
	  * @param idEmployee 	the id of the related employee
	  * @param byname 		used in the query
	  * @param first 		offset
	  * @param last  		number of registers to return
	  */
	 @SuppressWarnings("unchecked")
	 public List<Customer> getCustomersbyNameLimit(final int idEmployee, final String byname, final int first, final int last) {
	 return sessionFactory.getCurrentSession()
	  .createQuery("FROM Customer c WHERE idemployee=:ID AND name LIKE :N")
	  .setInteger("ID", idEmployee)
	  .setString("N", "%"+byname+"%")
	  .setFirstResult(first)
	  .setMaxResults(last)
	  .list();
	 }
	 
	 /**
	  * Get a customer list by age and limits in order to paginate
	  * <p>
	  * @param idEmployee 	the id of the related employee
	  * @param byagehigh	used in the query
	  * @param byagelow		used in the query
	  * @param first 		offset
	  * @param last  		number of registers to return
	  */
	 @SuppressWarnings("unchecked")
	 public List<Customer> getCustomersbyAgeLimit(final int idEmployee, final int byagehigh, final int byagelow, final int first, final int last) {
	// System.out.printf("ID: %s High: %s Low: %s",idEmployee, byagehigh, byagelow);
	 return sessionFactory.getCurrentSession()
	  .createQuery("FROM Customer c WHERE idemployee=:id AND age BETWEEN :L AND :H")
	  .setParameter("id", idEmployee)
	  .setParameter("L", byagelow)
	  .setParameter("H", byagehigh)
	  .setFirstResult(first)
	  .setMaxResults(last)
	  .list();
	 }
	 
	 /**
	  * Get a customer list by date of creation and limits in order to paginate
	  * <p>
	  * @param idEmployee 	the id of the related employee
	  * @param bydatehigh	used in the query
	  * @param bydatelow		used in the query
	  * @param first 		offset
	  * @param last  		number of registers to return
	  */
	 @SuppressWarnings("unchecked")
	 public List<Customer> getCustomersbyDateLimit(final int idEmployee, final Timestamp bydatehigh, final Timestamp bydatelow, final int first, final int last) {
	// System.out.printf("ID: %s High: %s Low: %s",idEmployee, byagehigh, byagelow);
	 return sessionFactory.getCurrentSession()
	  .createQuery("FROM Customer c WHERE idemployee=:id AND currentdate BETWEEN :L AND :H")
	  .setParameter("id", idEmployee)
	  .setParameter("L", bydatelow)
	  .setParameter("H", bydatehigh)
	  .setFirstResult(first)
	  .setMaxResults(last)
	  .list();
	 }
	 
	 /**
	  * Get a customer list by name, age and limits in order to paginate
	  * <p>
	  * @param idEmployee 	the id of the related employee
	  * @param byname 		used in the query
	  * @param byagehigh	used in the query
	  * @param byagelow		used in the query
	  * @param first 		offset
	  * @param last  		number of registers to return
	  */
	 @SuppressWarnings("unchecked")
	 public List<Customer> getCustomersbyNameAgeLimit(final int idEmployee,final String byname,final int byagehigh, final int byagelow, final int first, final int last) {
	 return sessionFactory.getCurrentSession()
	  .createQuery("FROM Customer c WHERE idemployee=:ID AND age BETWEEN :L AND :H AND name LIKE :N")
	  .setInteger("ID", idEmployee)
	  .setInteger("H", byagehigh)
	  .setInteger("L", byagelow)
	  .setString("N", "%"+byname+"%")
	  .setFirstResult(first)
	  .setMaxResults(last)
	  .list();
	 }
	
}