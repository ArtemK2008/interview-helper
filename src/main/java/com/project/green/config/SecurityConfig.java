//package com.project.green.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.project.green.security.UserRole;
//
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("ADMIN").password("{noop}ADMIN").roles("ADMIN");
//    }
//
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http.csrf().disable().authorizeRequests()
//        .antMatchers("/login-form", "/registration").permitAll()
//        .antMatchers("/**")
//        .hasAnyRole(UserRole.USER.name(), UserRole.ADMIN.name()).and()
//        .formLogin()
//        .loginPage("/login-form").loginProcessingUrl("/login")
//        .defaultSuccessUrl("/personal-area").and().logout().logoutUrl("/logout")
//        .logoutSuccessUrl("/login-form").deleteCookies();
//  }
//
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//}
