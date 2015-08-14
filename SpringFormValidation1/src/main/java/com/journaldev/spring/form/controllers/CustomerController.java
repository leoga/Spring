package com.journaldev.spring.form.controllers;
 
import java.beans.PropertyEditorSupport;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.journaldev.spring.form.facade.ServiceFacade;
import com.journaldev.spring.form.login.Login;
import com.journaldev.spring.form.model.Customer;


/**
 * Application controller for Customer entity
 * <p>
 */
@Controller
public class CustomerController {
	
    
    /**
     * Facade which connects with customer and employee services
     * <p>
     */
    @Autowired
    private ServiceFacade facade; 

    /**
     * Controller LOGGER 
     * <p>
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
    
    /**
     * Show aplication's Home Page
     * <p>
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage(final Model model) {
        LOGGER.info("Returning home.jsp page");
        return "home";
    }
    
	/**
	 * Returns a form in order to save a new customer
	 * <p>
	 */
    @RequestMapping(value = "/cust/save", method = RequestMethod.GET)
    public String saveCustomerPage(final Model model) {
        LOGGER.info("Returning custSave.jsp page");
        model.addAttribute("customer", new Customer());
        return "custSave";
    }
    
	/**
	 * Validates the customer and redirect to login page if it's correct
	 * <p>
	 * @param customer the introduced customer collected by the form
	 * @param bindingResult necessary in order to validate the customer
	 * @param model necessary in order to update data from/to jsp page
	 */
	@RequestMapping(value = "/cust/save.do", method = RequestMethod.POST)
    public String saveCustomerAction(
            @Valid final Customer customer,
            final BindingResult bindingResult, final Model model) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        if (bindingResult.hasErrors()) {
        	LOGGER.info("Returning custSave.jsp page");
            return "custSave";
        }else{
        	LOGGER.info("Returning loginCust.jsp page");
        	model.addAttribute("customerLogin", new Login());
        	facade.addCustomer(customer);
        	return "redirect:loginCust";
        }        
    }
  
	/**
	 * Return Login page for customers
	 * <p>
	 * @param model necessary in order to update data from/to jsp page
	 */
    @RequestMapping(value = {"/cust/loginCust", "/loginCust"}, method = RequestMethod.GET)
    public String logPage(final Model model) {
        LOGGER.info("Returning loginCust.jsp page");
        model.addAttribute("customerLogin", new Login());
        return "loginCust";
    }
    
	/**
	 * If login is correct redirects to customer personal page
	 * <p>
	 * @param login the introduced customer user and password 
	 * @param model necessary in order to update data from/to jsp page
	 */
    @RequestMapping(value = {"/loginCust.do","/cust/loginCust.do"}, method = RequestMethod.POST)
    public String getCustomerPost(@ModelAttribute("employeeLogin") final Login login, final Model model, final HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException{

    //LOGGER.info("login: "+login.getUser());
    final Customer user = facade.getCustomerLogin(login);
   	
   	if (null == user) { 
   		// fallo auth
   		return "redirect:loginCust";
   	} else {
   		session.setAttribute("currentUser", user);
   		model.addAttribute("customerDB", user);
   		return "customerPage";
   	}
   	
 }  
    
	/**
	 * Show the customer's personal information
	 * Precondition: Customer need to login
	 * <p>
	 * @param model necessary in order to update data from/to jsp page
	 */
    @RequestMapping(value = "/cust/personalPage")
    public String getPersonalPage(final Model model, final HttpSession session){
        final Customer currentCustomer = this.getCurrentCustomer(session);
        if (null == currentCustomer) {
        	return "redirect:loginEmp";
        }
    	model.addAttribute("customerDB", currentCustomer);
    	return "customerPage";
    }
    
	/**
	 * Allow to update the customer's personal information
	 * It returns a form in order to edit the data
	 * Precondition: Customer need to login
	 * <p>
	 * @param model necessary in order to update data from/to jsp page
	 */
    @RequestMapping(value = "/cust/edit")
    public String editCustomerAction(final Model model, final HttpSession session){
    	LOGGER.info("Returning customerPage-edit.jsp");
        final Customer currentCustomer = this.getCurrentCustomer(session);
        if (null == currentCustomer) {
        	return "redirect:loginEmp";
        }
    	model.addAttribute("customerGET", currentCustomer);
    	return "customerPage-edit";
    }
    
	/**
	 * Validates the changes and if they are correct 
	 * redirects to customer's personal page
	 * <p>
	 * @param customer the introduced changes collected by the form
	 * @param bindingResult necessary in order to validate the customer
	 * @param model necessary in order to update data from/to jsp page
	 */
    @RequestMapping(value = "/cust/edit", method = RequestMethod.POST)
    public String addInfoPost(@ModelAttribute("customerGET") @Valid final Customer customer, final BindingResult bindingResult, final Model model) {
   	 if(bindingResult.hasErrors()){
   		 return "customerPage-edit";
   }
   	final Customer currentCustomer = facade.updateCustomerInfo(customer);
    model.addAttribute("customerDB", currentCustomer);
  	return "customerPage";
  }
 
//NOT CONTROLLERS
    
	/**
	* Return current customer
	*/
    private Customer getCurrentCustomer(final HttpSession ses) {
    	return (Customer) ses.getAttribute("currentUser");
    }
    
	/**
	* Set format "dd/MM/yyyy" to the introduced dates
	* <p>
	*/
	@InitBinder
	public void binderC(final WebDataBinder binder) {
		 binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			 
			/**
			* Default locale
			*/
			Locale locale = Locale.getDefault();
			/**
			* Set format "dd/MM/yyyy" to the introduced dates
			* <p>
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
			* <p>
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



