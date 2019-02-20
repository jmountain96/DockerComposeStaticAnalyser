package com.test.quickstart.Validation;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.test.quickstart.Dependencies;
import com.test.quickstart.Validation.Interfaces.Dependency;
public class DependencyValidator implements ConstraintValidator<Dependency, Dependencies> {

	
	public boolean isValid(Dependencies value, ConstraintValidatorContext context) {
		boolean _ret = true;
		String missingDependencies = null;
		if(value.dependents == null)
		{
			return true;
		}
		List<String> services = Arrays.asList(value.target);
		for(String dependency : value.dependents )
		{
			if(services.contains(dependency) == false)
			{
				_ret = false;
				missingDependencies += dependency + " ";
			}
		}
		if(_ret == false)
		{
			context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate( "Missing service dependencies" + missingDependencies ).addConstraintViolation();
		}
		return _ret;
	}

}

