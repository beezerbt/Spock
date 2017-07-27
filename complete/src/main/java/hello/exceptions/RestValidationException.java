package hello.exceptions;

import hello.FieldErrorTO;

import java.util.Set;

public class RestValidationException extends RuntimeException {

	private final Set<FieldErrorTO> fieldErrors;

	public <T> RestValidationException(Set<FieldErrorTO> fieldErrors) {
		super("invalid input from client : [" + fieldErrors.toString() + "]");
		this.fieldErrors = fieldErrors;
	}

	public Set<FieldErrorTO> getFieldErrors() {
		return fieldErrors;
	}
}
