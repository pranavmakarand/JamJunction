package com.example.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;

@Component
public class UserValidator implements Validator {
	
	@Autowired
	UserService userService;
	
	public String userType;
	
	@Override
	public boolean supports(Class<?> clazz) {	
		
		return User.class.isAssignableFrom(clazz); //user class and all its subclasses
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error-email", "Email cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error-password", "Password cannot be empty");
		
		if (userType == "NEW") {
			if (validateDuplicateUser((User)target)) {
				errors.rejectValue("email", "error-email", "Email already already exists");
			}
		}
	}
	
	public Boolean validateDuplicateUser(User user) {
		
		User userFound = userService.findUserByEmail(user.getEmail());
		
		if (userFound != null) {
			return true;
		}
		
		return false;
	}
}
