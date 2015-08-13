package com.journaldev.spring.form.facade;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.journaldev.spring.form.login.Login;
import com.journaldev.spring.form.model.Customer;
import com.journaldev.spring.form.model.Employee;
import com.journaldev.spring.form.search.SearchFields;
import com.journaldev.spring.form.service.CustomerService;
import com.journaldev.spring.form.service.EmployeeService;

/**
 * Facade which connects with customer 
 * and employee services
 * <p>
 */
@Service
public class ServiceFacade {
	
    /**
     * connection to CustomerService
     * <p>
     */
	@Autowired
    private CustomerService customers;
    /**
     * connection to EmployeeService
     * <p>
     */
	@Autowired
	private EmployeeService employees;
    /**
     * Necessary in order to modify customers
     * <p>
     */
	private Customer modifyCustomer;
    /**
     * Necessary for Employee session
     * <p>
     */
	private Employee currentEmployee;
    /**
     * Necessary for Customer session
     * <p>
     */
	private Customer currentCustomer;

    /**
     * Used for searchfields
     * <p>
     */
    private Timestamp[] dates;
    /**
     * True if login success, false if fails
     * <p>
     */
    private boolean login;
    /**
     * ServiceFacade LOGGER
     * <p>
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceFacade.class);
    
    /**
     * Getter for login
     * <p>
     */
    public boolean isLogin() {
		return login;
	}
    
    /**
     * Getter for currentEmployee
     * <p>
     */
    public Employee getCurrentEmployee() {
		return currentEmployee;
	}
    
    /**
     * Getter for currentCustomer
     * <p>
     */
    public Customer getCurrentCustomer() {
		return currentCustomer;
	}
    
    /**
     * List the results by sfields
     * <p>
     * @param modDel search fields for the query
     * @param model necessary in order to update data from/to the jsp page
     * @param start necessary for pagination
     */
	public void search(final SearchFields modDel, final Model model, final int start){
		SearchFields sfields= modDel;
	    if(sfields!=null){
	      	 if(sfields.getByname()!=null && sfields.getByagehigh()==0 && sfields.getByagelow()==0 && sfields.getBydatehigh()==null && sfields.getBydatelow()==null){
	      		 final List<Customer> searching = customers.getCustomersbyName(currentEmployee.getId(), sfields.getByname());
	      		 paginationSearch(model, searching, start, sfields, 2, 1);
	      	 }
	      	 else if(sfields.getByagehigh()!=0 && sfields.getByagelow()!=0 && sfields.getByname()==null && sfields.getBydatehigh()==null && sfields.getBydatelow()==null) {
	      		 final List<Customer> searching = customers.getCustomersbyAge(currentEmployee.getId(), sfields.getByagehigh(), sfields.getByagelow());
	      		 paginationSearch(model, searching, start, sfields, 2, 2);
	      	 }
	      	 else if(sfields.getBydatehigh()!=null && sfields.getBydatelow()!=null && sfields.getByname()==null && sfields.getByagehigh()==0 && sfields.getByagelow()==0){
	      		dates= timestampConverter(sfields.getBydatehigh(), sfields.getBydatelow());
	      		final Timestamp timehigh = dates[0];
	      		final Timestamp timelow = dates[1];
	      		model.addAttribute("timehigh", timehigh);
	      		model.addAttribute("timelow", timelow);
	      		final List<Customer> searching = customers.getCustomersbyDate(currentEmployee.getId(), timehigh, timelow);
	      		paginationSearch(model, searching, start, sfields, 2, 3);
	      	 }
	      	 else {
	      		 final List<Customer> searching = customers.getCustomersbyNameAge(currentEmployee.getId(), sfields.getByname(), sfields.getByagehigh(), sfields.getByagelow());
	      		 paginationSearch(model, searching, start,  sfields, 2, 4);
	      	 }
	       }
	    //return model;
	}
	
	
    /**
     * Delete the selected customer from DB
     * <p>
     * @param sfields search fields for the query
     */
	public void deleteCustomers(final SearchFields sfields){
		final String[] selectedCustomers = sfields.getCustomers();
		for(int j=0;j<selectedCustomers.length;j++) {
		   	final List<Customer> customersl = customers.getCustomersbyIDcustomer(Integer.parseInt(selectedCustomers[j]));
		   	LOGGER.info("Customer with ID "+Integer.parseInt(selectedCustomers[j])+" selected");
		if(customersl.isEmpty()) {
			LOGGER.info("ERROR. Impossible search of customer's ID \n");		
	   	}else{
	   		customers.deleteCustomer(customersl.get(0));		
	   	}
	   	}
	}
	
    /**
     * Delete employee from DB
     * <p>
     * @param employee employee to delete
     */
	public void deleteEmployee(final Employee employee){
		employees.deleteEmployee(employee);
	}
	
    /**
     * Modify the customer's data from DB
     * <p>
     * @param form data collected by the form
     */
	public void updateCustomer(final Customer form){
		
	   	modifyCustomer.setUser(form.getUser());
	   	modifyCustomer.setName(form.getName());
	   	modifyCustomer.setAge(form.getAge());
	   	modifyCustomer.setBirthday(form.getBirthday());
	   	modifyCustomer.setEmail(form.getEmail());
	   	modifyCustomer.setPhone(form.getPhone());
	    customers.updateCustomer(modifyCustomer);
	}
	
    /**
     * Modify the current customer data
     * <p>
     * @param form data collected by the form
     */
	public void updateCustomerInfo(final Customer form){
		
		currentCustomer.setUser(form.getUser());
		currentCustomer.setName(form.getName());
		currentCustomer.setAge(form.getAge());
		currentCustomer.setBirthday(form.getBirthday());
		currentCustomer.setEmail(form.getEmail());
		currentCustomer.setPhone(form.getPhone());
	    customers.updateCustomer(currentCustomer);
	}
	
    /**
     * modify the current employee data
     * <p>
     * @param form data collected by the form
     */
	public void updateEmployee(final Employee form){
		
	   	currentEmployee.setUser(form.getUser());
	   	currentEmployee.setName(form.getName());
	   	currentEmployee.setRole(form.getRole());
	   	employees.updateEmployee(currentEmployee);
	   	
	}
	
    /**
     * modify the current employee data, used for test
     * <p>
     * @param form data collected by the form
     * @param form current current test employee
     */
	public void updateEmployeeTest(final Employee form, final Employee current){
		LOGGER.info("Form: "+form);
		LOGGER.info("Current: "+current);
		current.setId(form.getId());
		current.setUser(form.getUser());
		current.setPassword(form.getPassword());
		current.setName(form.getName());
		current.setRole(form.getRole());
		employees.updateEmployee(current);
	}
	
    /**
     * Create a customer
     * <p>
     * @param customer data collected by the form
     */
	public void createCustomer(final Customer customer) 
			throws NoSuchAlgorithmException, UnsupportedEncodingException{
		final Login log = new Login();
        customer.setPassword(log.returnedHash("0000"));
        customer.setCurrentdate(currentDate());
        customer.setIdemployee(currentEmployee.getId());
        customers.createCustomer(customer);
	}
	
    /**
     * Creates a new employee
     * <p>
     * @param employee data collected by the form
     */
	public void addEmployee(final Employee employee) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		final Login log = new Login();          
	    final String password = employee.getPassword();
	    employee.setPassword(log.returnedHash(password)); 
	    employees.addEmployee(employee);
	}
	
    /**
     * Used for test, add an employee to DB
     * <p>
     * @param employee employee to add
     
	public void addEmployeeTest(final Employee employee) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		LOGGER.info(employee.getPassword());
		final Login log = new Login();
		final String password = employee.getPassword();
		employee.setPassword(log.returnedHash(password));
		LOGGER.info(employee.getPassword());
		employees.addEmployee(employee);
	}*/
	
    /**
     * Creates a new Customer
     * <p>
     * @param customer data collected by the form
     */
	public void addCustomer(final Customer customer) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		final Login log = new Login();
			customer.setCurrentdate(currentDate());
			final String password = customer.getPassword();
			customer.setPassword(log.returnedHash(password));
			customers.addCustomer(customer);
	}
	
    /**
     * Paginate the customers related with current employee
     * <p>
     * @param start search necessary in order to paginate
     */
	public Object[] getCustomersbyID(final int start){
		int maxElements = 2;
		int pages;
		LOGGER.info("Current Employee: "+currentEmployee.getName());	
		final List<Customer> customersById = customers.getCustomersbyID(currentEmployee.getId());
    	final int num = customersById.size();
    	final int offset = (start-1)*maxElements;
    	if(num%maxElements == 0){
    		pages= num/maxElements;
    	}else{
    		pages=num/maxElements+1;
    	}
    	final List<Customer> customerDB = customers.getCustomersbyLimit(currentEmployee.getId(), offset, maxElements);

    	
    	Object[] array = new Object[2];
    	array[0] = pages;
    	array[1] = customerDB;
    	
    	return  array;
	}
	
    /**
     * Get the selected customer from DB
     * <p>
     * @param sfields search fields for the query
     */
	public Customer getCustomerDB(final SearchFields sfields){
			
		Customer customerDB;
		final List<Customer> customersl= customers.getCustomersbyIDcustomer(Integer.parseInt(sfields.getCustomer()));
    	if(customersl.isEmpty()){	
    		LOGGER.info("ERROR. Impossible search of customer's ID \n");	
    		return null;
    	}else{
    		customerDB = customersl.get(0);
    		modifyCustomer = customerDB;
    		LOGGER.info("Customer with ID "+Integer.parseInt(sfields.getCustomer())+" selected");
    		return customerDB;
    	}
    		
	}
	
    /**
     * If employee login success login = true else false
     * <p>
     * @param clogin the introduced employee login
     */
	public Employee getEmployeeLogin(final Login clogin) 
			throws NoSuchAlgorithmException, UnsupportedEncodingException{
		List<Employee> employeeLog = employees.getEmployeeLogin(clogin.getUser());
		LOGGER.info(employeeLog.toString());
	   	if(employeeLog.isEmpty()){
	   		LOGGER.info("Fallo al hacer login");
	   		login=false;
	   		return null;
	   	}else{
	   		currentEmployee=employeeLog.get(0);
	   	}
	   	final String savedfailpass = clogin.getPassword();
	   	clogin.setPassword(clogin.returnedHash(clogin.getPassword()));
	   	if(clogin.getPassword().equals(currentEmployee.getPassword())){ 
	   		login = true;
	   		LOGGER.info("Login True");
	   		return currentEmployee;
	   	} else {
	   		clogin.setPassword(savedfailpass);
	   		login = false;
	   		LOGGER.info("Login False");
	   		return null;
	   	}
	   		//return currentEmployee;
	}
	
    /**
     * Returns employee by user
     * <p>
     * @param user employee's user
     */
	public List<Employee> getEmployeebyUser(final String user){
		final List<Employee> employee = employees.getEmployeeLogin(user);
		if(employee.isEmpty()){
			return null;
		}else{
			return employee;
		}
	}
	
    /**
     * Returns customer by user
     * <p>
     * @param user customer's user
     */
	public List<Customer> getCustomerbyUser(final String user){
		final List<Customer> customer = customers.getCustomerLogin(user);
		if(customer.isEmpty()){
			return null;
		}else{
			return customer;
		}
	}
	
    /**
     * If customer login success login = true else false
     * <p>
     * @param clogin the introduced customer login
     * @param model necessary in order to update data from/to the jsp page
     */
	public Customer getCustomerLogin(final Login clogin) 
			throws NoSuchAlgorithmException, UnsupportedEncodingException{
		List<Customer> customerLog = customers.getCustomerLogin(clogin.getUser());
	   	if(customerLog.isEmpty()){
	   		LOGGER.info("Fallo al hacer login");
	   		login=false;
	   		return null; 		
	   	}else{
	   		currentCustomer=customerLog.get(0);
	   	}
	   	final String savedfailpass = clogin.getPassword();
	   	clogin.setPassword(clogin.returnedHash(clogin.getPassword()));
	   	if(clogin.getPassword().equals(currentCustomer.getPassword())){
	   		login = true;
	   		return currentCustomer;
	   	} else {
	   		clogin.setPassword(savedfailpass);
	   		login = false;
	   		return null;
	   	}

	}
	
    /**
     * Return a timestamp vector, timehigh is setted to 23.59 h
     * <p>
     * @param timehigh for search fields 
     * @param timelow for search fields
     */
	public Timestamp[] timestampConverter(final Date timehigh, final Date timelow) { 
	  	 final Calendar high = Calendar.getInstance();
	  	 final Calendar low = Calendar.getInstance();
	  	 
	  	 high.setTime(timehigh);
	  	 low.setTime(timelow);
	  	 high.set(Calendar.MILLISECOND,0);
	  	 low.set(Calendar.MILLISECOND,0);
	  	 Timestamp[] dates = new Timestamp[2];
	  	 dates[0]= new Timestamp (high.getTimeInMillis()+86399999);
	  	 dates[1]= new Timestamp (low.getTimeInMillis());
	  	 return dates;
	   }
	
    /**
     * returns a timestamp with the current date
     * <p>
     */
	public Timestamp currentDate() {
	  	 final Date today = new Date();
	  	 return new Timestamp(today.getTime());
	   }
	
    /**
     * Necessary for search pagination 
     * <p>
     * @param model necessary in order to update data from/to the jsp page
     * @param searching result to be listed
     * @param start necessary for pagination
     * @param sfields search parameters
     * @param numregist number of registers to be listed by page
     * @param type necessary for switch function
     */
	public void paginationSearch(final Model model, final List<Customer> searching, final int start, 
								 final SearchFields sfields, final int numregist, final int type){
	  	 final int num = searching.size();
	  	 int pages;
	  	 if(num%numregist == 0){
	  		 pages= num/numregist;
	  	 }else{
	  		 pages=num/numregist+1;
	  	 }
	  	 int ncustomers;
	  	 if(searching.isEmpty()){
	  		 ncustomers = searching.size();
	  	 }else{	 
	  		 ncustomers=0;  		 
	  	 }
	  	 model.addAttribute("start", start);
	  	 model.addAttribute("pages", pages);
	  	 model.addAttribute("ncustomers",ncustomers);
	  	 if(!searching.isEmpty()){
	  		 switch(type){
	  		 case 1:  final List<Customer> listing = customers.getCustomersbyNameLimit(currentEmployee.getId(), sfields.getByname(), numregist*(start-1), numregist);
	  		 		  model.addAttribute("listing",listing); break;
	  		 case 2: final List<Customer> listing2 = customers.getCustomersbyAgeLimit(currentEmployee.getId(), sfields.getByagehigh(), sfields.getByagelow(), numregist*(start-1), numregist);
	  		 		 model.addAttribute("listing",listing2); break;
	  		 case 3: final Timestamp timehigh = dates[0];
	  		 		 final Timestamp timelow = dates[1];
	  			 	 final List<Customer> listing3 = customers.getCustomersbyDateLimit(currentEmployee.getId(),timehigh, timelow, numregist*(start-1), numregist);
	   		 		 model.addAttribute("listing",listing3); break;
	  		 case 4: final List<Customer> listing4 = customers.getCustomersbyNameAgeLimit(currentEmployee.getId(), sfields.getByname(), sfields.getByagehigh(), sfields.getByagelow(), numregist*(start-1), numregist);
	  		 		 model.addAttribute("listing",listing4); break;	 
	  		 }
	  	  }
	   }

}
