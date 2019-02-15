package com.test.quickstart.Validation;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.test.quickstart.Validation.ValidationEnums.*;
import com.test.quickstart.Validation.Interfaces.ContainsString;
public class ContainsStringValidator implements ConstraintValidator<ContainsString, String> {
	
	private ContainsStringType type;
	private List<String> endpoint_mode = Arrays.asList("VIP", "DNSRR");
	private List<String> deploy_mode= Arrays.asList("GLOBAL", "REPLICATED");
	private List<String> restart_policy = Arrays.asList("NONE", "ON-FALIURE", "ANY");
	private List<String> versions = Arrays.asList("1.0", "2.0", "2.1", "2.2", "2.3", "2.4", "3.0", "3.1", "3.2", "3.3", "3.4", "3.5", "3.6", "3.7");
	public void initialize(ContainsString constraintAnnotation) {
        this.type = constraintAnnotation.value();
    }
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null)
		{
			return true;
		}
		switch(type) {
		case VERSION:
			if(versions.contains(value) == true)
			{
				return true;
			}
			else 
			{
				return false;
			}
		case ENDPOINT_MODE:
			if(endpoint_mode.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else
			{
				return false;
			}
		case DEPLOY_MODE:
			if(deploy_mode.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else 
			{
				return false;
			}
		
		default:
			context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate( "Unknown type"  ).addConstraintViolation();
            return false;
		}
		
	}

}

