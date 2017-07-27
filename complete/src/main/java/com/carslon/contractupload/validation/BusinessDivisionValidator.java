package com.carslon.contractupload.validation;

import com.carslon.contractupload.model.BusinessDivisions;
import com.carslon.contractupload.validation.annotation.ValidBusinessDivision;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class BusinessDivisionValidator implements ConstraintValidator<ValidBusinessDivision, Set<String>> {

    @Autowired
    private BusinessDivisions businessDivisions;

    @Override
    public void initialize(ValidBusinessDivision validBusinessDivision) {

    }

   /* @Override
    public boolean isValid(String businessDivisionCode, ConstraintValidatorContext constraintValidatorContext) {
        return businessDivisions.exists(businessDivisionCode);
    } */

    @Override
    public boolean isValid(Set<String> values, ConstraintValidatorContext context) {
        int violationsCount = 0;
        for (String value: values) {
            //TODO::KS::currently do not know how to get the count from the
            //context so will just put a counter
            if (!businessDivisions.exists(value)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Invalid business division")
                        .addNode(value)
                        .addConstraintViolation();
                ++violationsCount;
            }
        }
        if(violationsCount > 0) {
            return false;
        } else {
            return true;
        }
    }
}
