package edu.bzu.app.restapi.security.service;

import edu.bzu.app.restapi.security.controller.model.AuthUser;
import edu.bzu.app.restapi.security.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
//a service that is used in the filter to get the user from the resource/database
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserResource resource;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser = resource.getUser (username);
        return new User (authUser.getUsername (),authUser.getPassword (),new ArrayList<> ());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance ();
    }

}
