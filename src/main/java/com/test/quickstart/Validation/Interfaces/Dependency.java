package com.test.quickstart.Validation.Interfaces;
import static java.lang.annotation.ElementType.*;

import com.test.quickstart.Validation.DependencyValidator;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;



@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = DependencyValidator.class)
@Documented

public @interface Dependency {
	String message() default "{com.test.quickstart.Validation.Dependency}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
   
    
}
