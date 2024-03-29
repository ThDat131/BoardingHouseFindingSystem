package com.md.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.text.SimpleDateFormat;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
        "com.md.controllers",
        "com.md.repository",
        "com.md.service",
        "com.md.validator",
        "com.md.components"
})
@Order(2)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin().loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password");

        http.formLogin().defaultSuccessUrl("/", true)
                .failureUrl("/login?error");

        http.logout().logoutSuccessUrl("/login");
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/").access("hasAnyRole('ROLE_1', 'ROLE_2')")
//                        .antMatchers("/**/tentants").access("hasAnyRole('ROLE_1', 'ROLE_2')")
//                        .antMatchers("/**/landlords").access("hasAnyRole('ROLE_1', 'ROLE_2')")
//                        .antMatchers("/**/users").access("hasAnyRole('ROLE_1', 'ROLE_2')");
                .antMatchers("/**").access("hasAnyRole('ROLE_1', 'ROLE_2')")
                .antMatchers("/api/activate/**").access("hasAnyRole('ROLE_1', 'ROLE_2')");

        http.exceptionHandling()
                .accessDeniedPage("/loginnn");
        http.csrf().disable();
    }

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary
                 = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", this.env.getProperty("cloudinary.cloud_name"),
                "api_key", this.env.getProperty("cloudinary.api_id"),
                "api_secret", this.env.getProperty("cloudinary.api_secret"),
                "secure", true));
        return cloudinary;
    }
    @Bean
    public SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }
}
