package edu.bzu.app.restapi.security.config;

import edu.bzu.app.restapi.security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity//to enable security in this application
@Configuration//to enable component scan in this class
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//auto wire the JwtRequestFilter by the defined bean
    @Autowired
    JwtRequestFilter jwtRequestFilter;
//override this method to configure the security settings
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf ().disable ()//to enable our custom security settings
                .authorizeRequests ()//to listen to any request needed authorization
                .antMatchers ("/api/**").authenticated ()//that mark any request at url /api/Anything, need to be authenticated
                .anyRequest ().permitAll ().and ()//this so any other request can be done without authentication
                .sessionManagement ()
                .sessionCreationPolicy (SessionCreationPolicy.STATELESS);//this so we can declare this session be stateless, so we don't save any info about the session
        httpSecurity.addFilterBefore (jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);//to add jwt filter before the UsernamePasswordAuthenticationFilter
    }
//create a bean of AuthenticationManager
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean ();
    }

}
