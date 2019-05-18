package com.nowickipiotr.springfinalproject.security;

import com.nowickipiotr.springfinalproject.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder bcrypt() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    //password used for inMemoryAuthentication
    String encodedpassword = new BCryptPasswordEncoder().encode("password");

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        //in memory authentication
//        auth
//                .inMemoryAuthentication()
//                .withUser("pn")
//                .password(encodedpassword)
//                .roles("ADMIN")
//                .and()
//                .passwordEncoder(bcrypt());
//        auth
//                .inMemoryAuthentication()
//                .withUser("rambo")
//                .password(encodedpassword)
//                .roles("ADMIN")
//                .and()
//                .passwordEncoder(bcrypt());

        //database authentication
        auth.userDetailsService(userDetailsService).passwordEncoder(bcrypt());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/wall").hasAnyAuthority("ADMIN")
                .antMatchers("/addPost").hasAnyAuthority("ADMIN")
                .antMatchers("/newPost").hasAnyAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .anyRequest()
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/login.jsp")
                .loginProcessingUrl("/appLogin")
                .usernameParameter("app_username")
                .passwordParameter("app_password")
                .defaultSuccessUrl("/wall")
                .and()
                .logout()
                .logoutUrl("/appLogout")
                .logoutSuccessUrl("/login.jsp");
    }
}