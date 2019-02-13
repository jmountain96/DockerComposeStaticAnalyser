package com.test.quickstart.Validation;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.test.quickstart.Validation.Interfaces.CheckFolderExists;
public class CheckFolderExistsValidator implements ConstraintValidator<CheckFolderExists, String> {

	
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Path path = Paths.get(value);
		if (Files.exists(path, LinkOption.NOFOLLOW_LINKS))
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

}
