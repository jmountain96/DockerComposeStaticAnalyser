package com.test.quickstart.Validation.Interfaces;
import static java.lang.annotation.ElementType.*;
import com.test.quickstart.Validation.ValidationEnums.CheckStringType;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;



import com.test.quickstart.Validation.CheckStringFormatValidator;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CheckStringFormatValidator.class)
@Documented

public @interface CheckStringFormat {
	String message() default "{com.test.quickstart.Validation.CheckStringFormat}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
   
    CheckStringType value();
}