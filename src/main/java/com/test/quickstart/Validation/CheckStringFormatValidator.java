package com.test.quickstart.Validation;
import java.util.Arrays;
import java.util.List;

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
						String msg = context.getDefaultConstraintMessageTemplate();
						context.disableDefaultConstraintViolation();
						context.buildConstraintViolationWithTemplate( msg + "The following image is of the wrong format - " + value).addConstraintViolation();
						return false;
					}
			case MEMORY:
				if(value.toUpperCase().matches("\\d+(.\\d)?(GB|MB|KB|B|M|G|K)") == true)
				{
					return true;
				}
				else
				{
					String msg = context.getDefaultConstraintMessageTemplate();
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate( msg + " The following memory specification is of the wrong format - " + value).addConstraintViolation();
					return false;
				}
			case INTEGER:
				if(value.toUpperCase().matches("-?\\d+(\\.\\d+)?") == true)
				{
					return true;
				}
				else
				{
					String msg = context.getDefaultConstraintMessageTemplate();
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate( msg + "The following integer is of the wrong format - " + value).addConstraintViolation();
					return false;
				}
			case TIME:
				if(value.toUpperCase().matches("(\\d+(.\\d)?(NS|US|MS|S|M|H))+") == true)
				{
					return true;
				}
				else
				{
					String msg = context.getDefaultConstraintMessageTemplate();
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate( msg + "The following time value is of the wrong format - " + value).addConstraintViolation();
					return false;
				}
			case DNS:
				if(value.matches("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$") == true)
				{
					return true;
				}
				else
				{
					String msg = context.getDefaultConstraintMessageTemplate();
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate( msg + "The following dns address is of the wrong format - " + value).addConstraintViolation();
					return false;
				}
			case DOMAIN:
				if(value.matches("^((?!-))(xn--)?[a-z0-9][a-z0-9-_]{0,61}[a-z0-9]{0,1}\\.(xn--)?([a-z0-9\\-]{1,61}|[a-z0-9-]{1,30}\\.[a-z]{2,})$") == true)
				{
					return true;
				}
				else
				{
					String msg = context.getDefaultConstraintMessageTemplate();
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate( msg + "The following domain is of the wrong format - " + value).addConstraintViolation();
					return false;
				}
			case IPV6:
				if(value.matches("(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))") == true)
				{
					return true;
				}
				else
				{
					String msg = context.getDefaultConstraintMessageTemplate();
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate( msg + "The following IPv6 address is of the wrong format - " + value).addConstraintViolation();
					return false;
				}
			case PORT:
				if(value.matches("(\\d\\-?)+(:)?(\\d\\-?)+") == true)
				{
					return true;
				}
				else 
				{
					String msg = context.getDefaultConstraintMessageTemplate();
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate( msg + " The following port is of the wrong format - " + value).addConstraintViolation();
					return false;
				}
			case MAC:
				if(value.matches("^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$") == true)
				{
					return true;
				}
				else 
				{
					String msg = context.getDefaultConstraintMessageTemplate();
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate( msg + " The following mac address is of the wrong format - " + value).addConstraintViolation();
					return false;
				}
			case UNIX_PERM:
				List<Character> Values = Arrays.asList('0', '1', '2', '4');
				for(int i = 0; i < value.length(); i++ )
				{
					if (Values.contains(value.charAt(i)) == false) 
					{
						String msg = context.getDefaultConstraintMessageTemplate();
						context.disableDefaultConstraintViolation();
						context.buildConstraintViolationWithTemplate( msg + " The following unix permutation is of the wrong format - " + value).addConstraintViolation();
						return false;
					}
					return true;
				}
			case SYSLOG:
				if(value.matches("(tcp|udp){1}(://)\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}(:\\d{1,5})?") == true)
				{
					return true;
				}
				else 
				{
					String msg = context.getDefaultConstraintMessageTemplate();
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate( msg + "The following SYSLOG value is of the wrong format - " + value).addConstraintViolation();
					return false;
				}
			case ENV:
				
				if(value.matches(".+(.env){1}") == false)
					{
					    context.buildConstraintViolationWithTemplate( "The following env file isn't a valid env file, it should have the file extention .env - " + value).addConstraintViolation();
						return false;
					}
			
				return true;
			case SUBNET:
				
					
					if(value.matches("^([0-9]{1,3}\\.){3}[0-9]{1,3}(\\/([0-9]|[1-2][0-9]|3[0-2]))?$") == false)
						{
							String msg = context.getDefaultConstraintMessageTemplate();
							context.disableDefaultConstraintViolation();
							context.buildConstraintViolationWithTemplate( msg + "The following subnet is of the wrong format - " + value).addConstraintViolation();
							return false;
						}
				
				return true;
			default:
				return false;
		}
		
	}

}

