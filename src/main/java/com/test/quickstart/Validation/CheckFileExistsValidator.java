package com.test.quickstart.Validation;
import java.io.File;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.test.quickstart.Validation.Interfaces.CheckFileExists;

public class CheckFileExistsValidator implements ConstraintValidator<CheckFileExists, String> {
	private String prefix;
	public void initialize(CheckFileExists constraintAnnotation) {
        this.prefix = constraintAnnotation.value();
    }
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null || value.equals("true"))
		{
			return true;
		}
		File input = new File(this.prefix + value);
		if (input.exists() == true)
		{
			return true;
		}
		else 
		{
			context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate( "The following file cannot be found " + prefix + value).addConstraintViolation();
			return false;
		}
	}

}
