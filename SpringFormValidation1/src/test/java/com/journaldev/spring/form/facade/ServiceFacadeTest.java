package com.journaldev.spring.form.facade;

import static org.junit.Assert.*;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.journaldev.spring.form.login.Login;
import com.journaldev.spring.form.model.Customer;
import com.journaldev.spring.form.model.Employee;
import com.journaldev.spring.form.search.SearchFields;
import com.journaldev.spring.form.service.CustomerService;


/**
 * Core test
 * <p>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "file:src/main/webapp/WEB-INF/spring/datasource-MySQL.xml",
	"file:src/main/webapp/WEB-INF/spring/spring.xml"
})
public class ServiceFacadeTest {
	
    /**
     * connection to Customer and Employee Service
     * <p>
     */
	@Autowired
	private ServiceFacade facade;
	
    /**
     * connection to Customer DAO
     * <p>
     */
	@Autowired
	private CustomerService customers;
	
    /**
     * ServiceFacadeTest LOGGER
     * <p>
     */
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceFacadeTest.class);
	
    /**
     * Test employee
     * <p>
     */
	final Customer customer = new Customer(100, "hola", "hola@hola", 22, Customer.Gender.MALE, new Date(1988-04-04),"3216549870", "test","0000");
	
    /**
     * Test customer
     * <p>
     */
	final Employee employee = new Employee(100, "test", "Developer", "test", "0000");

    /**
     * Test which create and recover a existing employee, SUCCESS
     * <p>
     */
	@Test
	public void testCrearyRecuperarEmployee() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		LOGGER.info("Empezando testCrearyRecuperarEmployee");
		facade.addEmployee(employee);
		final Login test = new Login("test", "0000");
		assertNotNull(facade.getEmployeebyUser(test.getUser()));
	}
	
    /**
     * Test which recover a non-existent Employee, FAIL
     * <p>
     */
	@Test
	public void testRecuperarEmployeeInexistente() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		LOGGER.info("Empezando testRecuperarEmployeeInexistente");
		final Login test = new Login("fail", "0000");
		facade.addEmployee(employee);
		assertNotNull("Recuperar inexistente", facade.getEmployeebyUser(test.getUser()));
	}
	
    /**
     * Test which modifies a existent Employee, SUCCESS
     * <p>
     */
	@Test
	public void testModificarEmployeeExistente() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		LOGGER.info("Empezando testModificarEmployeeExistente");
		final Login test = new Login("test", "0000");
		facade.addEmployee(employee);
		employee.setName("modifyTest");
		final Employee current = facade.getEmployeebyUser(test.getUser()).get(0);
		facade.updateEmployeeTest(employee, current);
		assertTrue(facade.getEmployeebyUser(test.getUser()).get(0).getName().equals(employee.getName()));
		
	}
	
    /**
     * Test which modifies a non-existent Employee, FAIL
     * <p>
     */
	@Test
	public void testModificarEmployeeInexistente() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		LOGGER.info("Empezando testModificarEmployeeInexistente");
		final Login test = new Login("fail", "0000");
		facade.addEmployee(employee);
		employee.setName("modifyTest");
		assertNotNull("Modificar inexistente", facade.getEmployeebyUser(test.getUser()));
		final Employee current = facade.getEmployeebyUser(test.getUser()).get(0);
		facade.updateEmployeeTest(employee, current);

	}
	
    /**
     * Test which recover a related customer, SUCCESS
     * <p>
     */
	@SuppressWarnings("unchecked")
	@Test
	public void testCrearyRecuperarCustomerAsociado() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		LOGGER.info("Empezando testCrearyRecuperarCustomerAsociado");
		final SearchFields sfields = new SearchFields();
		sfields.setCustomer(String.valueOf(customer.getId()));
		facade.addEmployee(employee);
		final Login test = new Login("test", "0000");
		facade.getEmployeeLogin(test);
		facade.createCustomer(customer);
		final Object[] array = facade.getCustomersbyID(1);
		final List<Customer> customerDB = (List<Customer>) array[1];
		final Customer asociado = customerDB.get(0);
		LOGGER.info("Asociado: "+asociado.getName());
		assertNotNull("Customers asociados"+asociado.getName(), asociado);
	}
	
    /**
     * Test which recover a related customer
     * the related employee does not exist, FAIL
     * <p>
     */
	@Test
	public void testRecuperarCustomerAsociadoEmployeeInexistente() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		LOGGER.info("Empezando testRecuperarCustomerAsociadoEmployeeInexistente");
		final SearchFields sfields = new SearchFields();
		sfields.setCustomer(String.valueOf(customer.getId()));
		facade.addEmployee(employee);
		final Login test = new Login("fail", "0000");
		final Employee current = facade.getEmployeeLogin(test);
		assertNotNull("Customer asociado a Employee inexistente", current);
		/*facade.createCustomer(customer);
		final Object[] Array = facade.getCustomersbyID(1);
		final List<Customer> CustomerDB = (List<Customer>) Array[1];
		final Customer asociado = CustomerDB.get(0);*/
	}
	
    /**
     * Test which recover a existent customer, SUCCESS
     * <p>
     */
	@Test
	public void testRecuperarCustomerExistente() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		LOGGER.info("Empezando testRecuperarCustomerExistente");
		facade.addEmployee(employee); //for After
		facade.addCustomer(customer);
		assertNotNull("Recuperar Customer Existente", facade.getCustomerbyUser(customer.getUser()));
	}
	
    /**
     * Test which recover a non-existent customer, FAIL
     * <p>
     */
	@Test
	public void testRecuperarCustomerInexistente() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		LOGGER.info("Empezando testRecuperarCustomerInexistente");
		final SearchFields sfields = new SearchFields();
		sfields.setCustomer(String.valueOf(100));
		facade.addEmployee(employee); // for After
		facade.addCustomer(customer);
		assertNotNull("Recuperar Customer inexistente", facade.getCustomerbyUser("fail"));
	}
	
    /**
     * Test for existent login, SUCCESS
     * <p>
     */
	@Test
	public void testLoginExistente() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		LOGGER.info("Empezando testLoginExistente");
		facade.addEmployee(employee);
		final Login test = new Login("test", "0000");
		facade.getEmployeeLogin(test);
		LOGGER.info("Login test: "+facade.isLogin());
		assertTrue(facade.isLogin());
	}
	
    /**
     * Test for non-existent login, FAIL
     * <p>
     */
	@Test
	public void testLoginInexistente() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		LOGGER.info("Empezando testLoginInexistente");
		facade.addEmployee(employee);
		final Login test = new Login("fail", "0000");
		facade.getEmployeeLogin(test);
		assertTrue(facade.isLogin());
	}
	
    /**
     * Test for existent login, wrong password, FAIL
     * <p>
     */
	@Test
	public void testLoginContraseñaIncorrecta() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		LOGGER.info("Empezando testLoginContraseñaIncorrecta");
		facade.addEmployee(employee);
		final Login test = new Login("test", "fail");
		facade.getEmployeeLogin(test);
		assertTrue("Login contraseña incorrecta", facade.isLogin());
	}
	
    /**
     * Test for search function, by name. SUCCESS
     * <p>
     */
	@Test
	public void testValidarBusqueda() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		
		LOGGER.info("Empezando testValidarBusqueda");
		facade.addEmployee(employee);
		final Login test = new Login("test", "0000");
		facade.getEmployeeLogin(test);
		facade.createCustomer(customer);
		final SearchFields sfields = new SearchFields();
		sfields.setByname("hol");
		final List<Customer> searching = customers.getCustomersbyName(facade.getCurrentEmployee().getId(), sfields.getByname());
		final Customer customer = searching.get(0);
		LOGGER.info("Customer Recuperado "+customer.getName());
		assertNotNull(customer);
	}

	
    /**
     * In order to clean the DB
     * <p>
     */
	@After
	public void paraEjecutarDespues(){
		final SearchFields sfields = new SearchFields();
		String[] customers = new String[1];
		customers[0] = String.valueOf(customer.getId());
		sfields.setCustomers(customers);
		facade.deleteEmployee(employee);
		facade.deleteCustomers(sfields);
	}
}
