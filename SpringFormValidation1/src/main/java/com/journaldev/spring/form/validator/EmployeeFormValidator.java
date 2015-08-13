package com.journaldev.spring.form.validator;
 
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
 
import com.journaldev.spring.form.model.Employee;

/**
 * Custom employee validator
 * <p>
 */
public class EmployeeFormValidator implements Validator {
 
	/**
	 * which objects can be validated by this validator
	 * <p>
	 */
    @Override
    public boolean supports(final Class<?> paramClass) {
        return Employee.class.equals(paramClass);
    }
    
	/**
	 * Validation function
	 * <p>
	 */
    @Override
    public void validate(final Object obj, final Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
         
        final Employee emp = (Employee) obj;
        if(emp.getId() <=0){
            errors.rejectValue("id", "negativeValue", new Object[]{"'id'"}, "id can't be negative");
        }
         
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "role.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user", "user.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
    }
}