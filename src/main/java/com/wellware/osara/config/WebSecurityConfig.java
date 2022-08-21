package com.wellware.osara.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authProvider());
//    }

//    public void configure(HttpSecurity http) throws Exception{
//        http.csrf().disable()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//
//                .formLogin()
//                .loginPage("/login-register").defaultSuccessUrl("/profile").permitAll()
//                .and()
//                .logout().permitAll();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and().httpBasic();
    }

    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**")
                .and().ignoring().antMatchers("/js/**")
                .and().ignoring().antMatchers("/fonts/**")
                .and().ignoring().antMatchers("/img/**")
                .and().ignoring().antMatchers("/signup/**");
    }
}
