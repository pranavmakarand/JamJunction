package com.example.demo.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.pojo.Role;
import com.example.demo.pojo.User;
import com.example.demo.service.RolesService;
import com.example.demo.service.UserService;

/**
 * <p>
 * A custom Authentication provider example. To create custom
 * AuthenticationProvider, we need to implement the AuthenticationProvider
 * provide the implementation for the authenticate and support method.
 * </p>
 */

@Component
class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserService userService;

	@Autowired
	RolesService roleService;
	
	UserDetails isValidUser(String username, String password) throws IOException {

		User user = userService.findUserByEmail(username);

		List<Role> roles = new ArrayList<>();

		roleService.getAllRoles(user.getId()).forEach(roles::add);
		
		Role role = roles.get(0);
		
		String userPassWord = bCryptPasswordEncoder().encode(password);
		
		System.out.println(userPassWord);
		
		System.out.println(user.getPassword());
		
		if (password.equals(user.getPassword())) {
			
			UserDetails userDetails;
			
			if (role.getRoleName().equals("Admin")) {
				userDetails = org.springframework.security.core.userdetails.User.withUsername(username)
					.password("NOT_DISCLOSED").roles(String.valueOf(role.getRoleName())).build();
			} else {
				userDetails = org.springframework.security.core.userdetails.User.withUsername(username)
					.password("NOT_DISCLOSED").roles(String.valueOf(role.getRoleName())).build();
			}

			return userDetails;

		} else {
			throw new BadCredentialsException("Please enter the correct credentials !!");
		}
	}
	
	public static BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public Authentication authenticate(Authentication authentication) {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		UserDetails userDetails;

		try {

			userDetails = isValidUser(username, password);

			return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());

		} catch (Exception e) {

			throw new UsernameNotFoundException("Incorrect user credentials !!");
		}
	}

	@Override
	public boolean supports(Class<?> authenticationType) {
		return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
	}
}