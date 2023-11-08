package com.example.demo.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.service.UserService;

@Configuration
@EnableWebSecurity
public class SecureSystem extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;

	@Autowired
	CustomAuthenticationProvider customProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/", "/shop/**", "/register")
			.permitAll()
			.antMatchers("/admin/**")
			.hasRole("Admin")
			.anyRequest()
			.authenticated()
		.and()
			.formLogin()
			.loginPage("/login")
			.permitAll()
			.usernameParameter("email")
			.passwordParameter("password")
			.successHandler(authenticationSuccessHandler())
			.failureUrl("/login?error=true")
		.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID");
		
		http.headers().frameOptions().disable();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder authentication) throws Exception {
		authentication.authenticationProvider(customProvider);
	}
	
  @Bean
	AuthenticationSuccessHandler authenticationSuccessHandler() {
    return new CustomAuthenticationSuccessHandler();
  }
}
