package api;

import data.DTO.Role;

import javax.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by magnus
 */

@NameBinding
@Retention(RetentionPolicy.RUNTIME)
//Both security for classes (types) and functions (methods)
@Target({ElementType.TYPE, ElementType.METHOD})
//Creating the annotation
public @interface Secured {
    //Array of Roles into the annotation value for defining access
    Role[] value() default {};
}
