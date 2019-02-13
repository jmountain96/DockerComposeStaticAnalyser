package com.test.quickstart.Validation;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.test.quickstart.Validation.ValidationEnums.*;
import com.test.quickstart.Validation.Interfaces.ContainsString;
public class ContainsStringValidator implements ConstraintValidator<ContainsString, String> {
	
	private ContainsStringType type;
	private List<String> Versions = Arrays.asList("1.0", "2.0", "2.1", "2.2", "2.3", "2.4", "3.0", "3.1", "3.2", "3.3", "3.4", "3.5", "3.6", "3.7");
	public void initialize(ContainsString constraintAnnotation) {
        this.type = constraintAnnotation.value();
    }
	public boolean isValid(String value, ConstraintValidatorContext context) {
		switch(type) {
		case VERSION:
			if(Versions.contains(value) == true)
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

