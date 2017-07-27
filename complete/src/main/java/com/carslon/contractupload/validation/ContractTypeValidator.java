package com.carslon.contractupload.validation;

import com.carslon.contractupload.model.ContractTypes;
import com.carslon.contractupload.validation.annotation.ValidContractType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContractTypeValidator implements ConstraintValidator<ValidContractType, String> {

    @Autowired
    private ContractTypes contractTypes;

    @Override
    public void initialize(ValidContractType constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return contractTypes.exists(value);
    }
}
