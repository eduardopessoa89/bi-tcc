package com.business.team.businessSaleProject.core.security.authorization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {

    /**
     * Value of the suffix.
     *
     * @return Suffix.
     */
    String value();
}
