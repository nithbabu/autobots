package com.cars.autobots.authentication.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class UserConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/garage").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/garage").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/garage/editVehicle").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/garage/editVehicle").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/garage/deleteVehicle").hasRole("ADMIN")
			.antMatchers("/vehicles").permitAll()
			.antMatchers(HttpMethod.POST, "/compare-vehicles").permitAll()
			.antMatchers(HttpMethod.GET, "/compare-vehicles").permitAll()
			.and().csrf().disable()
			.formLogin();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(dataSource);
//			.withUser(
//					User.withUsername("admin")
//					.password("admin")
//					.roles("ADMIN")
//			)
//			.withUser(
//					User.withUsername("user")
//					.password("user")
//					.roles("USER")
//			);
	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
////		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/welcome").hasAnyRole("USER", "ADMIN")
////				.antMatchers("/getEmployees").hasAnyRole("USER", "ADMIN").antMatchers("/addNewEmployee")
////				.hasAnyRole("ADMIN").anyRequest().authenticated()
////				.and().formLogin().loginPage("/login").permitAll()
////				.and().logout().permitAll();
//		http.authorizeRequests()
//			.anyRequest().authenticated()
//			.and().formLogin()
//			.loginPage("/login")
//			.permitAll();
//
////		http.csrf().disable();
//	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
//		authenticationMgr.inMemoryAuthentication().withUser("admin").password("admin").authorities("ROLE_USER").and()
//				.withUser("javainuse").password("javainuse").authorities("ROLE_USER", "ROLE_ADMIN");
//		authenticationMgr.inMemoryAuthentication()
//						 .withUser("admin")
//						 .password("admin")
//						 .roles("ADMIN")
//						 .and()
//						 .withUser("user")
//						 .password("user")
//						 .roles("USER");
	}
	
	@Bean
	public PasswordEncoder getpasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
