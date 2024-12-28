package com.wallet.wallet.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wallet.wallet.filters.UserAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Autowired
  private UserAuthenticationFilter userAuthenticationFilter;

    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
      "/api/auth/login",
      "/api/users"
    };

    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {
      "/teste"
    };

    public static final String [] ENDPOINTS_CUSTOMER = {"/api/client"};

    public static final String [] ENDPOINTS_ADMIN = {"/api/admin", "/api/admin/add"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
      httpSecurity.csrf((csrf) -> csrf.disable());
      httpSecurity.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
      httpSecurity.authorizeHttpRequests(auth -> auth
              .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
              .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated()
              .requestMatchers(ENDPOINTS_ADMIN).hasRole("ADMINISTRATOR")
              .requestMatchers(ENDPOINTS_CUSTOMER).hasRole("CUSTOMER")
              .anyRequest().authenticated()
      )
      .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

      return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
      return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    }

}