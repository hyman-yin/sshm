package hyman.study.ssh.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyValidator implements ConstraintValidator {

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		
		return false;
	}

}