package com.journaldev.spring.form.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.journaldev.spring.form.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * DAO for Customer entity
 * <p>
 */
@Repository
public class EmployeeDao {
	 
    /**
     * connection to DB
     * <p>
     */
	@Autowired
	private SessionFactory sessionFactory;
	
	EmployeeDao(){
	    /**
	     * Empty employee constructor
	     * <p>
	     */
	}
 
    /**
     * Get all the employees stored in the DB
     * <p>
     */
	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployees() {
		final Session session = sessionFactory.getCurrentSession();		
		final Query query = session.createQuery("from Employee e order by id desc");
		//Query q = session.createQuery("select NAME from Customer");
		//final List<Employee> employeeList = query.list(); 
		return (List<Employee>) query.list();
	}

    /**
     * Add a employee in the DB
     * <p>
     * @param employee the employee to save
     */
	public void addEmployee(final Employee employee) {
		final Session session = sessionFactory.getCurrentSession();	
		session.save(employee);
	}
	
    /**
     * Get the employee by it's user
     * <p>
     * @param user employee's user
     */
	 @SuppressWarnings("unchecked")
	 public List<Employee> getEmployeeLogin(final String user) {
	 return sessionFactory.getCurrentSession()
	 		 .createQuery("FROM Employee e WHERE user=:user" )
	 		 .setString("user",user)
	 		 .list();
	 }
	 
	 @SuppressWarnings("unchecked")
	 public List<Employee> getEmployeebyId(final int id) {
	 return sessionFactory.getCurrentSession()
	  .createQuery("FROM Employee e WHERE id=:ID")
	  .setInteger("ID", id)
	  .list();
	 }
	 
	 /**
	  * Update the customer data stored in the DB
	  * <p>
	  * @param update employee to update
	  */
	 public void updateEmployee(final Employee update) {
		 sessionFactory.getCurrentSession().saveOrUpdate(update);
	 }
	 
	 public void deleteEmployee(final Employee employee) {
		 sessionFactory.getCurrentSession().delete(employee);
	 }
	 
}
