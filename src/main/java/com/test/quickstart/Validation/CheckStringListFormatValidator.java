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
					if(x.matches(".*(:).*") == false)
						{
							String msg = context.getDefaultConstraintMessageTemplate();
							context.disableDefaultConstraintViolation();
						    context.buildConstraintViolationWithTemplate( msg + " The following image is of the wrong format - " + x).addConstraintViolation();
							return false;
						}
				}
				return true;
			case DNS:
				for(String x: value) {
					if(x.matches("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$") == false)
						{
							String msg = context.getDefaultConstraintMessageTemplate();
							context.disableDefaultConstraintViolation();
						    context.buildConstraintViolationWithTemplate( msg + " The following DNS address is of the wrong format - " + x).addConstraintViolation();
							return false;
						}
				}
				return true;
			case EXTRAHOST:
				for(String x: value) {
					String[] x1 = x.split(":");
					if(x1[1].matches("(^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]))$") == false)
							{
								String msg = context.getDefaultConstraintMessageTemplate();
								context.disableDefaultConstraintViolation();
								context.buildConstraintViolationWithTemplate( msg + " The following extrahost is of the wrong format - " + x).addConstraintViolation();
								return false;
							}
				}
				return true;
			case PORT:
				for(String x: value) {
					if(x.matches("(\\d\\-?)+(:)?(\\d\\-?)+") == false)
						{
							String msg = context.getDefaultConstraintMessageTemplate();
							context.disableDefaultConstraintViolation();
							context.buildConstraintViolationWithTemplate( msg + "The following port is of the wrong format - " + x).addConstraintViolation();
							return false;
						}
				}
				return true;
			case ENV:
				for(String x: value) {
					if(x.matches("\\w+(.env)") == false)
						{
							String msg = context.getDefaultConstraintMessageTemplate();
							context.disableDefaultConstraintViolation();
							context.buildConstraintViolationWithTemplate( msg  + " The following env file isn't a valid env file, it should have the file extention .env - " + x).addConstraintViolation();
							return false;
						}
				}
				return true;
			default:
				return false;
		}
		
	}


}
