package com.journaldev.spring.form.validator;
 
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Custom phone validator
 * <p>
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {
	
	/**
	 * Phone Validator
	 * <p>
	 */
    @Override
    public void initialize(final Phone paramA) {
    	/**
    	 * Phone Validator
    	 * <p>
    	 */
    }
    
    /**
     * Validation function
     * <p>
     */
    @Override
    public boolean isValid(final String phoneNo, final ConstraintValidatorContext ctx) {
        if(phoneNo == null){
            return false;
        }
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}")){
        	return true;
        }
        //validating phone number with -, . or spaces
        else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")){
        	return true;
        }
        //validating phone number with extension length from 3 to 5
        else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")){ 
        	return true;
    	}
        //validating phone number where area code is in braces ()
        else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")){
    		return true;
		}
        //return false if nothing matches the input
        else{ 
			return false;
		}
 
    }
}