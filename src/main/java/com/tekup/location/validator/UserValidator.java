package com.tekup.location.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tekup.location.entities.User;

public class UserValidator implements Validator{

	@Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

	/*@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}*/
    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        /*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }*/
    }

}
