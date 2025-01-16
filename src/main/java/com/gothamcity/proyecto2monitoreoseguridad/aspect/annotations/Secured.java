package com.gothamcity.proyecto2monitoreoseguridad.aspect.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Secured {
    String[] roles() default {};
}

