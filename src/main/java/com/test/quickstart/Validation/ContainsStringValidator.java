package com.test.quickstart.Validation;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.test.quickstart.Validation.ValidationEnums.*;
import com.test.quickstart.Validation.Interfaces.ContainsString;
public class ContainsStringValidator implements ConstraintValidator<ContainsString, String> {
	
	private ContainsStringType type;
	private List<String> endpoint_mode = Arrays.asList("VIP", "DNSRR");
	private List<String> deploy_mode= Arrays.asList("GLOBAL", "REPLICATED");
	private List<String> restart_policy = Arrays.asList("NONE", "ON-FALIURE", "ANY");
	private List<String> rb_failure_action = Arrays.asList("CONTINUE", "PAUSE");
	private List<String> uc_failure_action = Arrays.asList("CONTINUE", "PAUSE", "ROLLBACK");
	private List<String> configOrder = Arrays.asList("STOP-FIRST", "START-FIRST");
	private List<String> booleanString= Arrays.asList("TRUE","FALSE","YES","NO");
	private List<String> isolation = Arrays.asList("DEFAULT","PROCESS","HYPERV");
	private List<String> healthCheckTest= Arrays.asList("NONE", "CMD", "CMDSHELL");
	private List<String> networkMode = Arrays.asList("bridge", "host", "overlay", "macvlan", "none");
	private List<String> versions = Arrays.asList("1.0", "2.0", "2.1", "2.2", "2.3", "2.4", "3.0", "3.1", "3.2", "3.3", "3.4", "3.5", "3.6", "3.7");
	private String pid = "host";
	private List<String> portProtocol = Arrays.asList("tcp", "udp");
	private List<String> portMode = Arrays.asList("host", "ingress");
	public void initialize(ContainsString constraintAnnotation) {
        this.type = constraintAnnotation.value();
    }
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null)
		{
			return true;
		}
		switch(type) {
		case VERSION:
			if(versions.contains(value) == true)
			{
				return true;
			}
			else 
			{
				return false;
			}
		case ENDPOINT_MODE:
			if(endpoint_mode.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else
			{
				return false;
			}
		case DEPLOY_MODE:
			if(deploy_mode.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else 
			{
				return false;
			}
		case RESTART_POLICY_CONDITION:
			if(restart_policy.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else 
			{
				return false;
			}
		case RBFAILUREACTION:
			if(rb_failure_action.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else 
			{
				return false;
			}
		case UCFAILUREACTION:
			if(uc_failure_action.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else 
			{
				return false;
			}
		case CONFIGORDER:
			if(configOrder.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else 
			{
				return false;
			}
		case BOOLEAN:
			if(booleanString.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else 
			{
				return false;
			}
		case ISOLATION:
			if(isolation.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else 
			{
				return false;
			}
		case HEALTHCHECK:
			if(healthCheckTest.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else 
			{
				return false;
			}
		case NETWORK_MODE:
			if(networkMode.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else 
			{
				return false;
			}
		case PID:
			if(value == pid )
			{
				return true;
			}
			else 
			{
				return false;
			}
		case PORT_PROTOCOL:
			if(portProtocol.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else 
			{
				return false;
			}
		case PORT_MODE:
			if(portMode.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else 
			{
				return false;
			}
			
		default:
			context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate( "Unknown type"  ).addConstraintViolation();
            return false;
		}
		
	}

}

