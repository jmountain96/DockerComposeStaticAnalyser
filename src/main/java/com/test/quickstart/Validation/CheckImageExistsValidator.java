package com.test.quickstart.Validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


import com.test.quickstart.Validation.Interfaces.CheckImageExists;
public class CheckImageExistsValidator implements ConstraintValidator<CheckImageExists, String> {
	
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null) 
		{
			return true;
		}
		
		return true;
		
	}

}