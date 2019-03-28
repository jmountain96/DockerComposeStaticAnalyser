package com.test.quickstart.Validation;
import java.io.File;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


import com.test.quickstart.Validation.Interfaces.CheckFileListExists;
public class CheckFileListExistsValidator implements ConstraintValidator<CheckFileListExists, String[]> {
	
	public boolean isValid(String[] value, ConstraintValidatorContext context) {
		if(value == null) 
		{
			return true;
		}
		for(String x : value)
		{
			File input = new File(x);
			if (input.exists() == false)
			{
				context.buildConstraintViolationWithTemplate( "The following file cannot be found - " + x).addConstraintViolation();
				return false;
			}
		}
		return true;
		
	}

}