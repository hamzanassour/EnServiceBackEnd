package com.example.enserviceback.config;

import com.example.enserviceback.utils.Constants;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;


@KeycloakConfiguration
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {


    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new NullAuthenticatedSessionStrategy ();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(keycloakAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.csrf ().disable (); // post will not work without disabling csrf protection
        http.authorizeRequests ().antMatchers ("/h2-console/**").permitAll (); // allow access to h2 console
        http.headers ().frameOptions ().disable ();
        http.authorizeRequests ()
                .antMatchers("/api/v0/admin/**").hasAuthority(Constants.ADMIN_ROLE)
                .antMatchers("/announcements/**").hasAuthority(Constants.ADMIN_ROLE);
    }
}