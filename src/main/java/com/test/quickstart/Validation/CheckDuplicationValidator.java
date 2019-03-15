package com.test.quickstart.Validation;
import java.io.File;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.test.quickstart.Validation.Interfaces.CheckDuplication;
public class CheckDuplicationValidator implements ConstraintValidator<CheckDuplication, String[]> {
	
	public boolean isValid(String[] value, ConstraintValidatorContext context) {
		if(value == null) 
		{
			return true;
		}
		boolean duplicate=false;
		for (int j=0;j<value.length;j++)
		{
		  for (int k=j+1;k<value.length;k++)
		  {
		    if (k!=j && value[k].equals(value[j])) {
		      duplicate=true;
		      context.buildConstraintViolationWithTemplate( "The following value is a duplicate - " + value[k]).addConstraintViolation();
		    }
		  }
		}
		return !duplicate;
		
	}

}