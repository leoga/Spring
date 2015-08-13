package com.journaldev.spring.form.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.journaldev.spring.form.dao.EmployeeDao;
import com.journaldev.spring.form.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for Employee entity
 * <p>
 */
@Service
@Transactional
public class EmployeeService {

    /**
     * Connection to Employee DAO
     * <p>
     */
	@Autowired 
	private EmployeeDao employeeDao;

	/**
	 * Call the same function in EmployeeDao
	 * <p>
	 */
	public List<Employee> getAllEmployees() {		
		return employeeDao.getAllEmployees();		
	}
	
	/**
	 * Call the same function in EmployeeDao
	 * <p>
	 */
    public void addEmployee(final Employee employee){
		employeeDao.addEmployee(employee);
		
    }
    
	/**
	 * Call the same function in EmployeeDao
	 * <p>
	 */
	public List<Employee> getEmployeeLogin(final String user){
		return employeeDao.getEmployeeLogin(user);
	}
	/**
	 * Call the same function in EmployeeDao
	 * <p>
	 */
	public void updateEmployee(final Employee update){
		employeeDao.updateEmployee(update);
	}
	
	public void deleteEmployee(final Employee employee) {
		employeeDao.deleteEmployee(employee);
	}
	
	public List<Employee> getEmployeebyId(int id){
		return employeeDao.getEmployeebyId(id);
	}
	
}
