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
			case TIME:
				if(value.matches("(\\d+(.\\d)?(NS|US|MS|S|M|H))+") == true)
				{
					return true;
				}
				else
				{
					return false;
				}
			case DNS:
				if(value.matches("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$") == true)
				{
					return true;
				}
				else
				{
					return false;
				}
			case DOMAIN:
				if(value.matches("^((?!-))(xn--)?[a-z0-9][a-z0-9-_]{0,61}[a-z0-9]{0,1}\\.(xn--)?([a-z0-9\\-]{1,61}|[a-z0-9-]{1,30}\\.[a-z]{2,})$") == true)
				{
					return true;
				}
				else
				{
					return false;
				}
			case IPV6:
				if(value.matches("(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))") == true)
				{
					return true;
				}
				else
				{
					return false;
				}
			case PORT:
				if(value.matches("(\\d\\-?)+(:)?(\\d\\-?)+") == true)
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

