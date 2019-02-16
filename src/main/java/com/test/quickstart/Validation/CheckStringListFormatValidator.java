package com.test.quickstart.Validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.test.quickstart.Validation.ValidationEnums.*;
import com.test.quickstart.Validation.Interfaces.CheckStringListFormat;

public class CheckStringListFormatValidator implements ConstraintValidator<CheckStringListFormat, String[]> {
	private CheckStringListType type;
	
	public void initialize(CheckStringListFormat constraintAnnotation) {
        this.type = constraintAnnotation.value();
    }
	public boolean isValid(String[] value, ConstraintValidatorContext context) 
	{
		if(value == null) 
		{
			return true;
		}
		switch(this.type)
		{
			case IMAGE: 
				for(String x: value) {
					if(x.matches("\\w*(:)\\w*") == false)
						{
							return false;
						}
				}
				return true;
			case DNS:
				for(String x: value) {
					if(x.matches("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$") == false)
						{
							return false;
						}
				}
				return true;
			case EXTRAHOST:
				for(String x: value) {
					if(x.matches("//w+(:)(^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$") == false)
							{
								return false;
							}
				}
				return true;
			default:
				return false;
		}
		
	}


}
