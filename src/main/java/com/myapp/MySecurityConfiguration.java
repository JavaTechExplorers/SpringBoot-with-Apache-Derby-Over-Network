package com.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

	http.authorizeRequests()
		.antMatchers("/", "/index", "/login", "/templates/**", "/resources/**", "/js/**", "/lib/**").permitAll()
		.anyRequest().authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/").and()
		.logout().logoutUrl("/logout");

	http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth.inMemoryAuthentication().withUser("admin").password("admin").roles("USER");
    }
}