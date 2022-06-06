package edu.bzu.app.restapi.security;

import edu.bzu.app.restapi.security.util.jwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService service;
    @Autowired
    private jwtUtil util;
//the filter of authorization that is set before the any transaction
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
//the header that contains the token is named "Authorization"
        final String authorizationHeader = request.getHeader ("Authorization");
        String username = null;
        String jwt = null;
        //check if the header exist and the value of it start with bearer
        if (authorizationHeader != null && authorizationHeader.startsWith ("Bearer ")) {
            //get the token, which is Bearer TOKEN so we want only the token
            jwt = authorizationHeader.split (" ")[1];
            //get the username from the token
            username = util.extractUsername (jwt);
        }
        //if the username isn't null, and there is no prior authentication done
        if (username != null && SecurityContextHolder.getContext ().getAuthentication () == null) {
            //get the user from the data source
            UserDetails userDetails = service.loadUserByUsername (username);
            //check if the token is valid
            if (util.validateToken (jwt, userDetails)) {
                //if the token is valid, give him the required security needed,with the password from the data source
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken (
                                userDetails, null, userDetails.getAuthorities ()
                        );
                usernamePasswordAuthenticationToken
                        .setDetails (
                                new WebAuthenticationDetailsSource ().buildDetails (request)
                        );
                //authenticate the user
                SecurityContextHolder.getContext ().setAuthentication (usernamePasswordAuthenticationToken);
            }
        }
        //do this filter
        chain.doFilter (request, response);
    }
}
