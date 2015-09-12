package com.prueba.promexico.examenandroid.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by egm on 16/07/15.
 */

@Target(value={ElementType.FIELD} )
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityFieldAnnotation {

    String name();

}
