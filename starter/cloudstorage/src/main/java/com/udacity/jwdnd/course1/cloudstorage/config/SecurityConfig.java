package com.udacity.jwdnd.course1.cloudstorage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	AuthenticationService authenticationService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/css/**", "/js/**", "/signup", "/login", "/h2-console/**").permitAll()
				.anyRequest().authenticated()
				.and().formLogin().loginPage("/login").defaultSuccessUrl("/home", true)
				.and().csrf().disable();

		// required for H2 console to work
//		http.headers().frameOptions().disable();
	}
}
