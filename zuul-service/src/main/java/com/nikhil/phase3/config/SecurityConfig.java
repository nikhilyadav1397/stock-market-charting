package com.nikhil.phase3.config;

import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private Environment env;
	
	public SecurityConfig(Environment env) {
		this.env = env;
	}

	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable();
//		httpSecurity.headers().frameOptions().disable();
//		httpSecurity.authorizeRequests()
//        .antMatchers(env.getProperty("registration.url.path")).permitAll()
//        .antMatchers(env.getProperty("login.url.path")).permitAll()
//        .anyRequest().authenticated()
//		.and().addFilter(new AuthorizationFilter(authenticationManager(), env));
		
		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}
