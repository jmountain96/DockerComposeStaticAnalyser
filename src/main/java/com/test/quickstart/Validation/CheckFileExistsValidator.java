package com.test.quickstart.Validation;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.test.quickstart.Validation.Interfaces.CheckFileExists;
import com.test.quickstart.Validation.Interfaces.ContainsString;
public class CheckFileExistsValidator implements ConstraintValidator<CheckFileExists, String> {
	
	public boolean isValid(String value, ConstraintValidatorContext context) {
		File input = new File(value);
		if (input.exists() == true)
		{
			return true;
		}
		else 
		{
			System.out.println(value);
			return false;
		}
	}

}
