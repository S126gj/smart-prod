package com.smart.prod.common.annotation;

import com.smart.prod.common.annotation.handler.PhoneValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * 手机号验证
 * @Author: Guoji Shen
 * @Date: 2023/7/4 15:47
 */
@Constraint(validatedBy = PhoneValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Phone {

    String message() default "手机号输入有误，请重新输入!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}