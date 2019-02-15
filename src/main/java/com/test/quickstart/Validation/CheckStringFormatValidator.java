package com.test.quickstart.Validation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.test.quickstart.Validation.ValidationEnums.*;
import com.test.quickstart.Validation.Interfaces.CheckStringFormat;
public class CheckStringFormatValidator implements ConstraintValidator<CheckStringFormat, String> {
	private CheckStringType type;
	public void initialize(CheckStringFormat constraintAnnotation) {
        this.type = constraintAnnotation.value();
    }
	public boolean isValid(String value, ConstraintValidatorContext context) 
	{
		if(value == null)
		{
			return true;
		}
		switch(this.type)
		{
			case IMAGE: 
				if(value.matches("\\w*(:)\\w*") == true)
					{
						return true;
					}
					else 
					{
						return false;
					}
			case MEMORY:
				if(value.toUpperCase().matches("\\d+(.\\d)?(GB|MB|KB|B|M|G|K)") == true)
				{
					return true;
				}
				else
				{
					return false;
				}
			case INTEGER:
				if(value.toUpperCase().matches("-?\\d+(\\.\\d+)?") == true)
				{
					return true;
				}
				else
				{
					return false;
				}
			default:
				return false;
		}
		
	}

}

