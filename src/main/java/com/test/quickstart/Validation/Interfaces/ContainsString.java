package com.test.quickstart.Validation.Interfaces;
import static java.lang.annotation.ElementType.*;

import com.test.quickstart.Validation.ContainsStringValidator;
import com.test.quickstart.Validation.ValidationEnums.ContainsStringType;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;



@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ContainsStringValidator.class)
@Documented

public @interface ContainsString {
	String message() default "{com.test.quickstart.Validation.ContainsString}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    ContainsStringType value();
    
}

