package com.journaldev.spring.form.facade;

import static org.junit.Assert.*;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Before;
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
/*"file:src/main/webapp/WEB-INF/spring/datasource-MySQL.xml",
"file:src/main/webapp/WEB-INF/spring/spring.xml"*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/datasource-MySQL.xml",
		"classpath:/META-INF/spring.xml"
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
     * Test which create and recover a existing employee
     * <p>
     */
	@Test
	public void testCrearyRecuperarEmployee() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		LOGGER.info("Empezando testCrearyRecuperarEmployee");
		final Login test = new Login("test", "0000");
		assertNotNull(facade.getEmployeebyUser(test.getUser()));
	}
	
    /**
     * Test which recover a non-existent Employee
     * <p>
     */
	@Test
	public void testRecuperarEmployeeInexistente() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		LOGGER.info("Empezando testRecuperarEmployeeInexistente");
		final Login test = new Login("fail", "0000");
		assertNull("Recuperar inexistente", facade.getEmployeebyUser(test.getUser()));
	}
	
    /**
     * Test which modifies a existent Employee
     * <p>
     */
	@Test
	public void testModificarEmployeeExistente() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		LOGGER.info("Empezando testModificarEmployeeExistente");
		final Login test = new Login("test", "0000");
		employee.setName("modifyTest");
		facade.updateEmployee(employee);
		assertTrue(facade.getEmployeebyUser(test.getUser()).get(0).getName().equals(employee.getName()));
	}
	
    /**
     * Test which modifies a non-existent Employee
     * <p>
     */
	@Test
	public void testModificarEmployeeInexistente() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		LOGGER.info("Empezando testModificarEmployeeInexistente");
		final Employee inex = new Employee(99999, "fail", "Developer", "fail", "0000");
		assertNull("Modificar Employee inexistente", facade.updateEmployee(inex));
	}
	
    /**
     * Test which recover a related customer
     * <p>
     */
	@SuppressWarnings("unchecked")
	@Test
	public void testCrearyRecuperarCustomerAsociado() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		LOGGER.info("Empezando testCrearyRecuperarCustomerAsociado");
		final SearchFields sfields = new SearchFields();
		sfields.setCustomer(String.valueOf(customer.getId()));
		final Login test = new Login("test", "0000");
		final Employee currentEmployee = facade.getEmployeeLogin(test);
		facade.createCustomer(customer, currentEmployee.getId());
		final Object[] array = facade.getCustomersbyID(1, currentEmployee);
		final List<Customer> customerDB = (List<Customer>) array[1];
		final Customer asociado = customerDB.get(0);
		LOGGER.info("Asociado: "+asociado.getName());
		assertNotNull("Customers asociados"+asociado.getName(), asociado);
	}
	
    /**
     * Test which recover a related customer
     * the related employee does not exist
     * <p>
     */
	@SuppressWarnings("unchecked")
	@Test
	public void testRecuperarCustomerAsociadoEmployeeInexistente() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		LOGGER.info("Empezando testRecuperarCustomerAsociadoEmployeeInexistente");
		final SearchFields sfields = new SearchFields();
		sfields.setCustomer(String.valueOf(customer.getId()));
		final Employee inex = new Employee(99999, "fail", "Developer", "fail", "0000");
		final Object[] array = facade.getCustomersbyID(1, inex);
		final List<Customer> customerDB = (List<Customer>) array[1];
		assertTrue("Customer asociado a Employee inexistente", customerDB.isEmpty());
	}
	
    /**
     * Test which recover a existent customer
     * <p>
     */
	@Test
	public void testRecuperarCustomerExistente() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		LOGGER.info("Empezando testRecuperarCustomerExistente");
		facade.addCustomer(customer);
		assertNotNull("Recuperar Customer Existente", facade.getCustomerbyUser(customer.getUser()));
	}
	
    /**
     * Test which recover a non-existent customer
     * <p>
     */
	@Test
	public void testRecuperarCustomerInexistente() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		LOGGER.info("Empezando testRecuperarCustomerInexistente");
		final Customer inex = new Customer(100, "inex", "hola@hola", 22, Customer.Gender.MALE, new Date(1988-04-04),"3216549870", "inex","0000");
		assertNull("Recuperar Customer inexistente", facade.getCustomerbyUser(inex.getUser()));
	}
	
    /**
     * Test for existent login
     * <p>
     */
	@Test
	public void testLoginExistente() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		LOGGER.info("Empezando testLoginExistente");
		final Login test = new Login("test", "0000");
		final Employee employeeTest = facade.getEmployeeLogin(test);
		assertTrue(test.getUser().equals(employeeTest.getUser()));
	}
	
    /**
     * Test for non-existent login
     * <p>
     */
	@Test
	public void testLoginInexistente() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		LOGGER.info("Empezando testLoginInexistente");
		final Login test = new Login("fail", "0000");
		final Employee employeeTest = facade.getEmployeeLogin(test);
		assertNull(employeeTest);
	}
	
    /**
     * Test for existent login, wrong password
     * <p>
     */
	@Test
	public void testLoginContraseñaIncorrecta() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		LOGGER.info("Empezando testLoginContraseñaIncorrecta");
		final Login test = new Login("test", "fail");
		final Employee employeeTest = facade.getEmployeeLogin(test);
		assertNull("Login contraseña incorrecta", employeeTest);
	}
	
    /**
     * Test for search function, by name
     * <p>
     */
	@Test
	public void testValidarBusqueda() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		
		LOGGER.info("Empezando testValidarBusqueda");
		final Login test = new Login("test", "0000");
		final Employee currentEmployee = facade.getEmployeeLogin(test);
		facade.createCustomer(customer, currentEmployee.getId());
		final SearchFields sfields = new SearchFields();
		sfields.setByname("hol");
		final List<Customer> searching = customers.getCustomersbyName(currentEmployee.getId(), sfields.getByname());
		final Customer customer = searching.get(0);
		LOGGER.info("Customer Recuperado "+customer.getName());
		assertNotNull("Validar busqueda", customer);
	}

    /**
     * Necessary for almost all the tests
     * <p>
     */
	@Before
	public void paraEjecutarAntes() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		facade.addEmployee(employee);
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
