package com.nowickipiotr.springfinalproject.security;

import com.nowickipiotr.springfinalproject.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder bcrypt() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    String encodedpassword = new BCryptPasswordEncoder().encode("password");

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        //in memory authentication
        auth
                .inMemoryAuthentication()
                .withUser("pn")
                .password(encodedpassword)
                .roles("ADMIN")
                .and()
                .passwordEncoder(bcrypt());
        auth
                .inMemoryAuthentication()
                .withUser("rambo")
                .password(encodedpassword)
                .roles("ADMIN")
                .and()
                .passwordEncoder(bcrypt());

        //database authentication
        auth.userDetailsService(userDetailsService).passwordEncoder(bcrypt());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/private").authenticated()
                .antMatchers("/private").hasAnyRole("ADMIN")
                .antMatchers("/wall").authenticated()
                .antMatchers("/wall").hasAnyRole("ADMIN")
                .anyRequest()
                .permitAll()
                .and()
                .formLogin();
    }
}