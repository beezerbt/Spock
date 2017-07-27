package hello;

import hello.exceptions.RestValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

@Component
public class RestValidator {

    private final Validator validator;

    @Autowired
    public RestValidator(Validator validator) {
        this.validator = validator;
    }

    public <T> void validate(T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        Set<FieldErrorTO> errors = newHashSet();

        if (!violations.isEmpty()) {
            for (ConstraintViolation<T> violation : violations) {
                errors.add(new FieldErrorTO(violation.getPropertyPath().toString(), violation.getMessage()));
            }

            throw new RestValidationException(errors);
        }
    }
}
