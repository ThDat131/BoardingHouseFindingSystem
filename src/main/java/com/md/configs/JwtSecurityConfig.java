package com.md.configs;

import com.md.filters.CustomAccessDeniedHandler;
import com.md.filters.JwtAuthenticationTokenFilter;
import com.md.filters.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
        "com.md.controllers",
        "com.md.repository",
        "com.md.service",
        "com.md.validator",
        "com.md.components"})
@Order(1)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
        return jwtAuthenticationTokenFilter;
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
@Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.addAllowedOrigin("*");
    configuration.addAllowedMethod("*");
    configuration.addAllowedHeader("*");

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()
//                .usernameParameter("username")
//                .passwordParameter("password");
//
//        http.formLogin().defaultSuccessUrl("/")
//                .failureUrl("/login?error");
//
//        http.logout().logoutSuccessUrl("/login");
//
//        http.exceptionHandling()
//                .accessDeniedPage("/login?accessDenied");

//        http.authorizeRequests().antMatchers("/").permitAll()
//            .antMatchers("/api/**")
//            .access("hasRole('ROLE_ADMIN')");
//        http.csrf().disable();

        // Disable crsf cho đường dẫn /rest/**
        http.csrf().ignoringAntMatchers("/api/**");
        http.authorizeRequests().antMatchers("/api/login/").permitAll();
        http.authorizeRequests().antMatchers("/api/provinces/").permitAll();
        http.authorizeRequests().antMatchers("/api/province/**").permitAll();
        http.authorizeRequests().antMatchers("/api/district/**").permitAll();
        http.authorizeRequests().antMatchers("/api/landlord-user/").permitAll();
        http.authorizeRequests().antMatchers("/api/tentant-user/").permitAll();
        http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/room/**").access("hasAnyRole('ROLE_0', 'ROLE_1', 'ROLE_2')")
                .antMatchers(HttpMethod.DELETE, "/api/room/**").access("hasAnyRole('ROLE_0', 'ROLE_1', 'ROLE_2')")
                .antMatchers(HttpMethod.GET, "/api/rooms/").access("hasAnyRole('ROLE_0', 'ROLE_1', 'ROLE_2')")
                .antMatchers("/api/follow/**").access("hasRole('ROLE_-1')")
          /*      .antMatchers(HttpMethod.GET, "/api/**").access("hasRole('ROLE_-1') or hasRole('ROLE_0') or hasRole('ROLE_1') or hasRole('ROLE_2')")
                .antMatchers(HttpMethod.POST, "/api/**").access("hasRole('ROLE_0') or hasRole('ROLE_1')")
                .antMatchers(HttpMethod.DELETE, "/api/**").access("hasRole('ROLE_0') or hasRole('ROLE_1')")*/
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler())
                .and()
                .cors().configurationSource(corsConfigurationSource());
    }
}
