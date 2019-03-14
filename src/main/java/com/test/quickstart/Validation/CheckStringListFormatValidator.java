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
						    context.buildConstraintViolationWithTemplate( "The following image is of the wrong format - " + x).addConstraintViolation();
							return false;
						}
				}
				return true;
			case DNS:
				for(String x: value) {
					if(x.matches("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$") == false)
						{
						    context.buildConstraintViolationWithTemplate( "The following DNS address is of the wrong format - " + x).addConstraintViolation();
							return false;
						}
				}
				return true;
			case EXTRAHOST:
				for(String x: value) {
					if(x.matches("//w+(:)(^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$") == false)
							{
								context.buildConstraintViolationWithTemplate( "The following extrahost is of the wrong format - " + x).addConstraintViolation();
								return false;
							}
				}
				return true;
			case SUBNET:
				for(String x: value) {
					if(x.substring(x.lastIndexOf("=")).matches("^([0-9]{1,3}\\.){3}[0-9]{1,3}(\\/([0-9]|[1-2][0-9]|3[0-2]))?$") == false || x.startsWith("subnet: ") == false)
						{
							context.buildConstraintViolationWithTemplate( "The following subnet is of the wrong format - " + x).addConstraintViolation();
							return false;
						}
				}
				return true;
			case PORT:
				for(String x: value) {
					if(x.matches("(\\d\\-?)+(:)?(\\d\\-?)+") == false)
						{
							context.buildConstraintViolationWithTemplate( "The following port is of the wrong format - " + x).addConstraintViolation();
							return false;
						}
				}
				return true;
			case ENV:
				for(String x: value) {
					if(x.matches("\\w+(.env)") == false)
						{
							context.buildConstraintViolationWithTemplate( "The following env file isn't a valid env file, it should have the file extention .env - " + x).addConstraintViolation();
							return false;
						}
				}
				return true;
			default:
				return false;
		}
		
	}


}
