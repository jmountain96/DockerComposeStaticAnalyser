package com.test.quickstart.Validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.test.quickstart.Dependencies;
import com.test.quickstart.Validation.Interfaces.CheckUsed;
public class CheckUsedValidator implements ConstraintValidator<CheckUsed, Dependencies> {

	
	public boolean isValid(Dependencies value, ConstraintValidatorContext context) {
		boolean _ret = true;
		boolean found = false;
		String notUsed = "";
		if(value.dependents == null)
		{
			return true;
		}
		for(String x : value.dependents)
		{
			for(String y : value.target)
			{
				if(x.equals(y))
				{
					found = true;
				}
			}
			if(found == false)
			{
				_ret = false;
				notUsed += x + " ";
			}
		}
		if(_ret == false)
		{
			String msg = context.getDefaultConstraintMessageTemplate();
			context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate( msg + "The following are not in use by any service " + notUsed ).addConstraintViolation();
		}
		return _ret;
	}

}

