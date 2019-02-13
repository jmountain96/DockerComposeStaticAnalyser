package com.test.quickstart;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class YamlParser {
	 public static void main(String[] args) throws YamlException, FileNotFoundException {
	    	//YamlReader reader = new YamlReader(new FileReader("test.yaml"));
	        //TopLevel input = reader.read(TopLevel.class);
	       // System.out.println(input.getCommand());
	        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
	        TopLevel level = new TopLevel();
	        try {
	            level = mapper.readValue(new File("test.yaml"), TopLevel.class);
	            System.out.println(ReflectionToStringBuilder.toString(level,ToStringStyle.MULTI_LINE_STYLE));
	            
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	        Validator validator = factory.getValidator();
	        Set<ConstraintViolation<TopLevel>> constraintViolations = validator.validate(level);
	       for (ConstraintViolation<TopLevel> violation : constraintViolations) {
	            System.out.println(violation.getMessage());
	        }
	    }
	 
	 public void setConstructors() 
	 {
		 
	 }
	 
	 
}
