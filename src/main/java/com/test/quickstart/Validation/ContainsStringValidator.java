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
	private List<String> restart_policy = Arrays.asList("NONE", "ON-FAILURE", "ANY", "UNLESS-STOPPED", "NO", "ALWAYS");
	private List<String> rb_failure_action = Arrays.asList("CONTINUE", "PAUSE");
	private List<String> uc_failure_action = Arrays.asList("CONTINUE", "PAUSE", "ROLLBACK");
	private List<String> configOrder = Arrays.asList("STOP-FIRST", "START-FIRST");
	private List<String> booleanString= Arrays.asList("TRUE","FALSE","YES","NO");
	private List<String> isolation = Arrays.asList("DEFAULT","PROCESS","HYPERV");
	private List<String> healthCheckTest= Arrays.asList("NONE", "CMD", "CMDSHELL");
	private List<String> networkMode = Arrays.asList("BRIDGE", "HOST", "OVERLAY", "MACVLAN", "NONE");
	private List<String> versions = Arrays.asList("1","1.0", "2","2.0", "2.1", "2.2", "2.3", "2.4", "3","3.0", "3.1", "3.2","3.3", "3.4", "3.5", "3.6", "3.7");
	private String pid = "host";
	private List<String> portProtocol = Arrays.asList("TCP", "UDP");
	private List<String> portMode = Arrays.asList("HOST", "INGRESS");
	private List<String> volumeType = Arrays.asList("VOLUME", "BIND","TMPFS");
	private List<String> volumeConsistency = Arrays.asList("CONSISTENT", "CACHED", "DELEGATED");
	private List<String> loggingDriver = Arrays.asList("none", "json-file", "local", "syslog", "journald", "gelf", "fluentd", "awslogs", "splunk", "etwlogs", "gcplogs", "logentries" );
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
				String msg = context.getDefaultConstraintMessageTemplate();
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate( msg + "The following version is invalid " + value + " it must be one of the following " + versions.toString()).addConstraintViolation();
				return false;
			}
		case ENDPOINT_MODE:
			if(endpoint_mode.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else
			{
				String msg = context.getDefaultConstraintMessageTemplate();
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate( msg + "The following end point is invalid " + value + " it must be one of the following " + endpoint_mode.toString()).addConstraintViolation();
				return false;
			}
		case DEPLOY_MODE:
			if(deploy_mode.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else 
			{
				String msg = context.getDefaultConstraintMessageTemplate();
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate( msg + "The following deploy mode is invalid " + value + " it must be one of the following " + deploy_mode.toString()).addConstraintViolation();
				return false;
			}
		case RESTART_POLICY_CONDITION:
			if(restart_policy.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else 
			{
				String msg = context.getDefaultConstraintMessageTemplate();
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate( msg + "The following restart policy is invalid " + value + " it must be one of the following " + restart_policy.toString()).addConstraintViolation();
				return false;
			}
		case RBFAILUREACTION:
			if(rb_failure_action.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else 
			{
				String msg = context.getDefaultConstraintMessageTemplate();
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate( msg + "The following rollback failure option is invalid " + value + " it must be one of the following " + rb_failure_action.toString()).addConstraintViolation();
				return false;
			}
		case UCFAILUREACTION:
			if(uc_failure_action.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else 
			{
				String msg = context.getDefaultConstraintMessageTemplate();
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate( msg + "The following uc failure action is invalid " + value + " it must be one of the following " + uc_failure_action.toString()).addConstraintViolation();
				return false;
			}
		case CONFIGORDER:
			if(configOrder.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else 
			{
				String msg = context.getDefaultConstraintMessageTemplate();
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(msg +  "The following config order is invalid " + value + " it must be one of the following " + configOrder.toString()).addConstraintViolation();
				return false;
			}
		case BOOLEAN:
			if(booleanString.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else 
			{
				String msg = context.getDefaultConstraintMessageTemplate();
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate( msg + "The value is not a boolean " + value + " it must be one of the following " + booleanString.toString()).addConstraintViolation();
				return false;
			}
		case ISOLATION:
			if(isolation.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else 
			{
				String msg = context.getDefaultConstraintMessageTemplate();
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(msg +  "The following isolation is invalid " + value + " it must be one of the following " + isolation.toString()).addConstraintViolation();
				return false;
			}
		case HEALTHCHECK:
			if(healthCheckTest.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else 
			{
				String msg = context.getDefaultConstraintMessageTemplate();
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate( msg + "The following healthcheck test is invalid " + value + " it must be one of the following " + healthCheckTest.toString()).addConstraintViolation();
				return false;
			}
		case NETWORK_MODE:
			if(networkMode.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else 
			{
				String msg = context.getDefaultConstraintMessageTemplate();
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate( msg + "The following network mode is invalid " + value + " it must be one of the following " + networkMode.toString()).addConstraintViolation();
				return false;
			}
		case PID:
			if(value.equals(pid) )
			{
				return true;
			}
			else 
			{
				String msg = context.getDefaultConstraintMessageTemplate();
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate( msg + "The following PID is invalid " + value + " it must be one of the following " + pid.toString()).addConstraintViolation();
				return false;
			}
		case PORT_PROTOCOL:
			if(portProtocol.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else 
			{
				String msg = context.getDefaultConstraintMessageTemplate();
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate( msg + "The following port protocol is invalid " + value + " it must be one of the following " + portProtocol.toString()).addConstraintViolation();
				return false;
			}
		case PORT_MODE:
			if(portMode.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else 
			{
				String msg = context.getDefaultConstraintMessageTemplate();
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate( msg + "The following port mode is invalid " + value + " it must be one of the following " + portMode.toString()).addConstraintViolation();
				return false;
			}
		case VOLUME_TYPE:	
			if(volumeType.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else 
			{
				String msg = context.getDefaultConstraintMessageTemplate();
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate( msg + "The following volume type is invalid " + value + " it must be one of the following " + volumeType.toString()).addConstraintViolation();
				return false;
			}
		case VOLUME_CONSISTENCY:
			if(volumeConsistency.contains(value.toUpperCase()) == true)
			{
				return true;
			}
			else 
			{
				String msg = context.getDefaultConstraintMessageTemplate();
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate( msg + "The following volume consistency is invalid " + value + " it must be one of the following " + volumeConsistency.toString()).addConstraintViolation();
				return false;
			}
		case LOGGING_DRIVER:
			if(loggingDriver.contains(value) == true)
			{
				return true;
			}
			else 
			{
				String msg = context.getDefaultConstraintMessageTemplate();
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate( msg + "The following logging driver is invalid " + value + "it must be one of the following " + loggingDriver.toString()).addConstraintViolation();
				return false;
			}
		default:
			context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate( "Unknown type"  ).addConstraintViolation();
            return false;
		}
		
	}

}

