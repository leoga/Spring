package com.journaldev.spring.form.controllers;
 

import java.beans.PropertyEditorSupport;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.journaldev.spring.form.login.Login;
import com.journaldev.spring.form.model.Employee;
import com.journaldev.spring.form.model.Customer;
import com.journaldev.spring.form.search.SearchFields;
import com.journaldev.spring.form.facade.ServiceFacade;
 

/**
 * Application controller for Employee entity
 * <p>
 */
@Controller
public class EmployeeController {
    
    /**
     * Facade which connects with customer and employee services
     * <p>
     */
    @Autowired
    private ServiceFacade facade;
    /**
     * Used in order to show data of the current logged employee
     * <p>
     */
    private Employee currentEmployee;
    /**
     * Used in order recover pages in "mycustomers" and "Search"
     * <p>
     */
    int pagelist, pagesearchlist;
    /**
     * Used in order to show data of the current search
     * <p>
     */
    private SearchFields sfields;
    /**
     * Used in order display search results when we change of page
     * <p>
     */
    boolean getpost = false;
    /**
     * Controller LOGGER 
     * <p>
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
	
	/**
	 * Returns a form in order to save a new employee
	 * <p>
	 */
    @RequestMapping(value = "/emp/save", method = RequestMethod.GET)
    public String saveEmployeePage(final Model model) {
    	LOGGER.info("Returning empSave.jsp page");
    	model.addAttribute("employee", new Employee());
        return "empSave";
    }
    
	/**
	 * Validates the employee and redirect to login page if it's correct
	 * <p>
	 * @param employee the introduced employee collected by the form
	 * @param bindingResult necessary in order to validate the employee
	 * @param model necessary in order to update data from/to jsp page
	 */
    @RequestMapping(value = "/emp/save.do", method = RequestMethod.POST)
    public String saveEmployeeAction(
    		@ModelAttribute("employee") @Validated final Employee employee,
            final BindingResult bindingResult, final Model model) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (bindingResult.hasErrors()) {
        	LOGGER.info("Returning empSave.jsp page");
            return "empSave";
        }else{
        	LOGGER.info("Returning loginEmp.jsp page");
	    	model.addAttribute("employeeLogin", new Login());            
	        facade.addEmployee(employee);
	    	return "redirect:loginEmp";
        } 
    }
	
	/**
	 * Return Login page for employees
	 * <p>
	 * @param model necessary in order to update data from/to jsp page
	 */
    @RequestMapping(value = {"/emp/loginEmp", "/loginEmp"}, method = RequestMethod.GET)
    public String logPage(final Model model) {
    	LOGGER.info("Returning loginEmp.jsp page");
        model.addAttribute("employeeLogin", new Login());
        return "loginEmp";
    }
    
	/**
	 * If login is correct redirects to employee personal page
	 * <p>
	 * @param login the introduced employee user and password 
	 * @param model necessary in order to update data from/to jsp page
	 */
    @RequestMapping(value = "/loginEmp.do", method = RequestMethod.POST)
    public String getEmployeePost(@ModelAttribute("employeeLogin") final Login login, final Model model) throws NoSuchAlgorithmException, UnsupportedEncodingException{
    //LOGGER.info("login: "+login.getUser());
   	facade.getEmployeeLogin(login);
   	if(facade.isLogin()){
   		currentEmployee = facade.getCurrentEmployee();
   		model.addAttribute("employeeDB", currentEmployee);
   		return "employeePage";
   	}else{
   		return "redirect:loginEmp";
   	}
    }
    
	/**
	 * Show the employee's personal information
	 * Precondition: Employee need to login
	 * <p>
	 * @param model necessary in order to update data from/to jsp page
	 */
    @RequestMapping(value = "/emp/personalPage")
    public String getPersonalPage(final Model model){
    	model.addAttribute("employeeDB", currentEmployee);
    	return "employeePage";
    }
    
	/**
	 * Allow to update the employee's personal information
	 * It returns a form in order to edit the data
	 * Precondition: Employee need to login
	 * <p>
	 * @param model necessary in order to update data from/to jsp page
	 */
    @RequestMapping(value = "/emp/edit")
    public String editEmployeeAction(final Model model){
    	LOGGER.info("Returning customerPage-edit.jsp");
    	model.addAttribute("employeeGET", currentEmployee);
    	return "employeePage-edit";
    }
    
	/**
	 * Validates the changes and if they are correct 
	 * redirects to employee's personal page, need login
	 * <p>
	 * @param employee the introduced changes collected by the form
	 * @param bindingResult necessary in order to validate the employee
	 * @param model necessary in order to update data from/to jsp page
	 */
    @RequestMapping(value = "/emp/edit", method = RequestMethod.POST)
    public String addInfoPost(@ModelAttribute("employeeGET") @Valid final Employee employee, final BindingResult bindingresult, final Model model) {
   	 if(bindingresult.hasErrors()){
   		 return "employeePage-edit";
   	}
   	facade.updateEmployee(employee);
   	model.addAttribute("employeeDB", currentEmployee);
  	return "employeePage";
    }
    
	/**
	 * Return a form in order to add a related customer (with currentEmployee) to the DB
	 * need login
	 * <p>
	 * @param model necessary in order to update data from/to jsp page
	 */
    @RequestMapping(value = "add-customer")
    public String addCustomerGet(final Model model) {
    model.addAttribute("customer", new Customer());
    return "add-customer";
    }
    
	/**
	 * Validates the customer and redirects to employee personal page
	 * need employee login
	 * <p>
	 * @param customer the customer data collected by the form
	 * @param bindingResult necessary in order to validate the customer
	 * @param model necessary in order to update data from/to jsp page
	 */
    @RequestMapping(value = "add-customer", method = RequestMethod.POST)
    public String addCustomerPost(final Model model, 
    		@Valid final Customer customer, final BindingResult bindingresult) throws NoSuchAlgorithmException, UnsupportedEncodingException{
    if(bindingresult.hasErrors()){
       		 return "add-customer";
       }    	
    	LOGGER.info("Returning employeePage.jsp page");
        facade.createCustomer(customer);
        model.addAttribute("employeeDB", currentEmployee);
        return "employeePage";
    }
    
	/**
	 * Return a form in order to modify a related customer (with currentEmployee) in the DB
	 * need employee login
	 * <p>
	 * @param searchById an string with the ID of the customer we choose to modify
	 * @param model necessary in order to update data from/to jsp page
	 */
    @RequestMapping(value = "/modify")
    public String modifyCustomerAction(@ModelAttribute("ArrayID") final SearchFields searchById, final Model model){
    	Customer customerDB;
    	LOGGER.info("Returning modifyCustomer.jsp");
    	customerDB = facade.getCustomerDB(searchById);
    	model.addAttribute("customerGET", customerDB);
    	return "modifyCustomer";
    }
    
	/**
	 * Validates the customer and redirects to "mycustomers" page if it's correct
	 * need employee login
	 * <p>
	 * @param customer the customer data collected by the form
	 * @param bindingResult necessary in order to validate the customer	 
	 * @param model necessary in order to update data from/to jsp page
	 */
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String addInfoPost(@ModelAttribute("customerGET") @Valid final Customer customer, final BindingResult bindingresult, final Model model) {
   	 if(bindingresult.hasErrors()){
   		 return "modifyCustomer";
   }
   	facade.updateCustomer(customer);
  	return "redirect:mycustomers?page="+pagelist;
  }
    
	/**
	 * Delete the customer we choose, need employee login
	 * <p>
	 * @param searchById an string with the ID of the customer we choose to delete	 
	 * @param model necessary in order to update data from/to jsp page
	 */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deletePost(@ModelAttribute("ArrayID") final SearchFields searchById, final Model model) {
    	facade.deleteCustomers(searchById);
	   	return "redirect:mycustomers?page="+pagelist;
    }
    
	/**
	 * List the customers related with the currentEmployee, need employee login
	 * <p>
	 * @param start value needed in order to paginate the result
	 * @param model necessary in order to update data from/to jsp page
	 */
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "mycustomers", method = RequestMethod.GET)
    public String showMyCustomers(@RequestParam(value="page") final int start, final Model model){
    	LOGGER.info("Returning mycustomers.jsp page");
    	Object[] array= facade.getCustomersbyID(start);
    	final int pages = (int) array[0];
    	final List<Customer> customerDB = (List<Customer>) array[1];
    	model.addAttribute("customerDB", customerDB);
    	model.addAttribute("start", start);
    	model.addAttribute("pages", pages);
    	model.addAttribute("ArrayID", new SearchFields());
    	model.addAttribute("currentemployee", currentEmployee);
    	pagelist= start;
    	return "mycustomers";
    }
    
	/**
	 * Return a form in order to make a search related
	 * customers by date, name o age, if we modify or delete
	 * customers "Searchfields" doesn't change
	 * <p>
	 * @param start value needed in order to paginate the result
	 * @param model necessary in order to update data from/to jsp page
	 */
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String getSearch(@RequestParam(value="page") final int start, final Model model) {
    	if(getpost){
    		facade.search(sfields, model, start);
    	   	model.addAttribute("currentemployee", currentEmployee);
    	   	model.addAttribute("Searchfields", sfields);
    	    pagesearchlist= start;
    	    return "search";
    	}
	   	model.addAttribute("currentemployee", currentEmployee);
	   	model.addAttribute("Searchfields", new SearchFields());
	   	return "search";
    }
    
	/**
	 * List related customers by date, name, or age
	 * <p>
	 * @param modDel search parameters collected by the form
	 * @param model necessary in order to update data from/to jsp page
	 */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String doSearch(@RequestParam(value="page") final int start, 
    		@ModelAttribute("Searchfields") final SearchFields modDel, final Model model) {	
    model.addAttribute("currentemployee", currentEmployee);
    if(modDel!=null){
    	sfields=modDel;
    }
    facade.search(modDel, model, start);
    pagesearchlist= start;
    getpost=true;
    return "search";
    }
    
	/**
	 * Return a form in order to modify a customer listed by "Search" function
	 * <p>
	 * @param searchById an string with the ID of the customer we choose to modify
	 * @param model necessary in order to update data from/to jsp page
	 */
    @RequestMapping(value = "/modifys")
    public String modifyCustomerSearchAction(@ModelAttribute("ArrayID") final SearchFields searchById, final Model model){
    	LOGGER.info("Returning modifyCustomer.jsp");
    	final Customer customerDB = facade.getCustomerDB(searchById);
    	model.addAttribute("customerGET", customerDB);
    	return "modifyCustomer";
    }
    
	/**
	 * Validates the customer and redirects to "search" page if it's correct
	 * <p>
	 * @param customer the customer data collected by the form
	 * @param bindingResult necessary in order to validate the customer
	 * @param model necessary in order to update data from/to jsp page
	 */
    @RequestMapping(value = "/modifys", method = RequestMethod.POST)
    public String modifySearchPost(@ModelAttribute("customerGET") @Valid final Customer customer, 
    								final BindingResult bindingResult, final Model model) {
   	 if(bindingResult.hasErrors()){
   		 return "modifyCustomer";
   }
   	model.addAttribute("currentemployee", currentEmployee);
   	facade.updateCustomer(customer);
  	return "redirect:search?page="+pagesearchlist;
  }
    
	/**
	 * Delete the customer we choose in the "search" list
	 * <p>
	 * @param searchById an string with the ID of the customer we choose to delete
	 * @param model necessary in order to update data from/to jsp page
	 */
    @RequestMapping(value = "/deletes", method = RequestMethod.POST)
    public String deleteSearchPost(@ModelAttribute("ArrayID") final SearchFields searchById, final Model model) {
    	facade.deleteCustomers(searchById);
	   	return "redirect:search?page="+pagesearchlist;
    }
    

    
//NOT CONTROLLERS
/**
* Set format "dd/MM/yyyy" to the introduced dates
*/
@InitBinder
public void binder(final WebDataBinder binder) {
	binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
		
		/**
		* Default locale
		*/
		Locale locale = Locale.getDefault();
		
			/**
			* Set format "dd/MM/yyyy" to the introduced dates
			*/
   		    public void setAsText(final String value) {
   		            try {
   						setValue(new SimpleDateFormat("yyyy-MM-dd", locale).parse(value));
   					} catch (ParseException e) {
   						//e.printStackTrace();
   						LOGGER.info(e.getMessage());
   						setValue(null);
   					}
   		    
   		    }
	   		/**
	   		* Set format "dd/MM/yyyy" to the introduced dates
	   		*/
   		    public String getAsText() {
   		    	if (getValue()==null){
   		    		return "";
   		    	}
   		    	else{
   		    		return new SimpleDateFormat("dd/MM/yyyy", locale).format((Date) getValue());	
   		    	}
   		    }        

   		});
    }
    
}

