package com.test.quickstart.Validation;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.test.quickstart.Validation.Interfaces.CheckFileExists;
import com.test.quickstart.Validation.Interfaces.CheckStringFormat;
import com.test.quickstart.Validation.Interfaces.ContainsString;
import com.test.quickstart.Validation.ValidationEnums.CheckStringType;
public class CheckFileExistsValidator implements ConstraintValidator<CheckFileExists, String> {
	private String prefix;
	public void initialize(CheckFileExists constraintAnnotation) {
        this.prefix = constraintAnnotation.value();
    }
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null || value == "true")
		{
			return true;
		}
		File input = new File(this.prefix + value);
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
