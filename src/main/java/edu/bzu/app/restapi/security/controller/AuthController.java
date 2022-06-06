package edu.bzu.app.restapi.security.controller;

import edu.bzu.app.restapi.security.controller.model.AuthUser;
import edu.bzu.app.restapi.security.controller.model.TokenHolder;
import edu.bzu.app.restapi.security.model.AuthenticationRequest;
import edu.bzu.app.restapi.security.model.AuthenticationResponse;
import edu.bzu.app.restapi.security.resource.UserResource;
import edu.bzu.app.restapi.security.service.MyUserDetailsService;
import edu.bzu.app.restapi.security.util.jwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
//where the magic is done
@RestController//mark it as a controller
public class AuthController {
    //auto wiring to pre-defined beans
    @Autowired
    MyUserDetailsService userDetailsService;
    @Autowired
    jwtUtil util;
    @Autowired
    UserResource resource;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService service;


    //the login method, we get the request from the body with username and password
    @PostMapping("authenticate")
    public ResponseEntity<?> auth(@RequestBody AuthenticationRequest request) throws Exception {
        System.out.println (request);//just for debugging
        try {
            //trying to authenticate the user , if not throw an exception
            authenticationManager.authenticate (
                    new UsernamePasswordAuthenticationToken (request.getUsername (), request.getPassword ())
            );
        } catch (BadCredentialsException e) {
            throw new Exception ("Incorrect Login");
        }
        //load the user from the resource, in this project i should probably store the users in database
        //but i stored them locally because i don't really have time :P
        final UserDetails details = service.loadUserByUsername (request.getUsername ());
        //generate the JWT token based on the user details and return it
        final String jwt = util.generateToken (details);
        return ResponseEntity.ok (new AuthenticationResponse (jwt));
    }
    //all the rest are just test methods, to get the token, validate it and get the claims in the token
    @GetMapping("/getToken")
    ResponseEntity<String> getToken(@RequestBody AuthUser authUser){
        if (resource.isUserValid (authUser.getUsername (), authUser.getPassword ()))
            return ResponseEntity.ok (util.generateToken (authUser.toUser ()));
        else
            return ResponseEntity.ok("null");
    }
    @GetMapping("/validate")
    boolean isValid(@RequestBody TokenHolder authUser){
        return util.validateToken (authUser.getToken (),authUser.toUser ());
    }
    @GetMapping("/isValid")
    ResponseEntity<Boolean> isValid(@RequestBody String token){
        try {
            util.validateToken (token);
            return ResponseEntity.ok (util.validateToken (token));
        }catch (SignatureException | MalformedJwtException | ExpiredJwtException signatureException) {
            System.out.println (signatureException.getMessage ());
            return ResponseEntity.ok (false);
        }
    }
    @GetMapping("/claimsTime")
    Map<String, String> getClaimsTime(@RequestHeader Map<String,String> headers){
        String token = headers.get ("authorization").split (" ")[1];
        Claims claim = util.extractAllClaims (token);
        SimpleDateFormat formatter= new SimpleDateFormat ("yyyy-MM-dd 'at' HH:mm:ss z");
        Date exp =  (claim.getExpiration ());
        Date isAt =  (claim.getIssuedAt ());
        return Map.of (
                "sub", claim.getSubject (),
                "exp",formatter.format (exp),
                "iat",formatter.format (isAt)
        );

    }
    @GetMapping("/claims")
   Claims getClaims(@RequestHeader Map<String,String> headers){
        String token = headers.get ("authorization").split (" ")[1];
        return util.extractAllClaims (token);
    }
    @GetMapping("")
    UserDetails getUser(){
        return userDetailsService.loadUserByUsername ("user");
    }


}

