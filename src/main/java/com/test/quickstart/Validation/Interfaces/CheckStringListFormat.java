package com.test.quickstart.Validation.Interfaces;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


import com.test.quickstart.Validation.CheckStringListFormatValidator;
import com.test.quickstart.Validation.ValidationEnums.CheckStringListType;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CheckStringListFormatValidator.class)
@Documented

public @interface CheckStringListFormat {
	String message() default "{com.test.quickstart.Validation.CheckStringListFormat}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
   
    CheckStringListType value();
}
