package com.example.courseprogram.Exception;

import com.example.courseprogram.model.DTO.DataRequest;
import com.example.courseprogram.utils.JsonUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;


public class MapValuesValidator implements ConstraintValidator<ValidMapValues, DataRequest> {

    String[] groups;
    Class[] classList;

    @Override
    public void initialize(ValidMapValues constraintAnnotation) {
        groups = constraintAnnotation.groups();
        classList = constraintAnnotation.classList();
    }

    @Override
    public boolean isValid(DataRequest dataRequest, ConstraintValidatorContext context) {
        Map<String,Object> map=dataRequest.getData();
        for(int i=0;i<groups.length;i++){
            String key=groups[i];
            Class nowClass=classList[i];
            JsonUtil.parse(key,nowClass);
        }
        return true;
    }
}
