package com.example.demo.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {
		boolean isAdmin = false;

		for (GrantedAuthority role : authentication.getAuthorities()) {
			if (role.getAuthority().equals("ROLE_Admin")) {
				isAdmin = true;
				break;
			}
		}
		
		if (isAdmin) {
			response.sendRedirect("/adminIndexPage");
		} else {
			response.sendRedirect("/");
		}
	}
}
