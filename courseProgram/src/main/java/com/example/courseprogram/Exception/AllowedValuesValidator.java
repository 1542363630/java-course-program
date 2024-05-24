package com.example.courseprogram.Exception;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AllowedValuesValidator implements ConstraintValidator<AllowedValues,String>{

    private Set<String> allowedValuesSet;

    @Override
    public void initialize(AllowedValues constraintAnnotation) {
        allowedValuesSet = new HashSet<>(Arrays.asList(constraintAnnotation.allowedValues()));
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null ) {
            return true; // value为null说明可以为空
        }
        return allowedValuesSet.contains(value); // 如果value不在允许的值集合中，则验证失败，否则验证成功
    }
}
