package com.test.quickstart.Validation;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.test.quickstart.Validation.ValidationEnums.*;
import com.test.quickstart.Validation.Interfaces.ListContainsString;
public class ListContainsStringValidator implements ConstraintValidator<ListContainsString, String[]> {
	
	private ListContainsStringType type;
	private List<String> capabilities = Arrays.asList("ALL", "AUDIT","CONTROL","AUDIT_READ","AUDIT_WRITE","BLOCK_SUSPEND","CHOWN",
			"DAC_OVERRIDE","DAC_READ_SEARCH","FOWNER,FSETID","IPC_LOCK","IPC_OWNER",
			"KILL","LEASE","LINUX_IMMUTABLE","MAC_ADMIN","MKNOD","NET_ADMIN","NET_BIND_SERVICE",
			"NET_BROADCAST","NET_RAW,SETGID","SETFCAP","SETPCAP","SETUID","SYS_ADMIN","SYS_BOOT",
			"SYS_CHROOT","SYS_MODULE","SYS_NICE","SYS_PACCT","SYS_PTRACE","SYS_RAWIO","SYS_RESOURCE",
			"SYS_TIME","SYS_TTY_CONFIG","SYSLOG,WAKE_ALARM");
	private List<String> constraints = Arrays.asList("node.id","node.hostname", "node.role", "node.labels", "engine.labels");
	public void initialize(ListContainsString constraintAnnotation) {
        this.type = constraintAnnotation.value();
    }
	public boolean isValid(String[] value, ConstraintValidatorContext context) {
		if(value == null)
		{
			return true;
		}
		switch(type) {
		case CAP:
			for(String x: value) {
				if(capabilities.contains(x.toUpperCase())== false)
				{
					String msg = context.getDefaultConstraintMessageTemplate();
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate( msg + "The following capability is invalid " + x + "/n it must be one of the following " + capabilities.toString()).addConstraintViolation();
					return false;
				}
				return true;
		}
		case CONSTRAINTS:
			for(String x: value)
			{
				boolean matches = false;
				for(String n : constraints)
				{
					
					if(x.matches("(" + n + "){1}.*( == ){1}.+") == true || x.matches("(" + n + "){1}.*( != ){1}.+") == true)
					{
						matches = true;
					}
					
				
				}
				if(matches == false)
				{
					String msg = context.getDefaultConstraintMessageTemplate();
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate( msg + "The following constraint is invalid " + x + "/n it must be one of the following " + constraints.toString()).addConstraintViolation();
					return false;
					
				}
			}
			return true;
		case PLACEMENTPREF:
			for(String x: value)
			{
				boolean matches = false;
				for(String n : constraints)
				{
					if(x.contains("spread == " + n)== true)
					{
						matches = true;
					}
				}
				if(matches == false)
				{
					String msg = context.getDefaultConstraintMessageTemplate();
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate(msg +  "The following placement pref is invalid " + x + "/n it must be one of the following " + constraints.toString()).addConstraintViolation();
					return false;
					
				}
			}
			return true;
		default:
			context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate( "Unknown type"  ).addConstraintViolation();
            return false;
		}
		
	}

}