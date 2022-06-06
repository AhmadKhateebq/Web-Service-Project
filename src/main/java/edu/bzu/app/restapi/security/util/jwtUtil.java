package edu.bzu.app.restapi.security.util;

import edu.bzu.app.restapi.security.resource.UserResource;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
//also , here is where the jwt magic is done
@Service
public class jwtUtil {

    // the secret key that is going to be used in the signature
    private final String SECRET_KEY = "secret";
    @Autowired
    UserResource resource;
//get the claims
    public String extractUsername(String token) {
        return extractClaim (token, Claims::getSubject);
    }
//get the expiring date
    public Date extractExpiration(String token) {
        return extractClaim (token, Claims::getExpiration);
    }
//get all claims
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims (token);
        return claimsResolver.apply (claims);
    }
//also get all claims but fancy
    public Claims extractAllClaims(String token) {
        return Jwts.parser ().setSigningKey (SECRET_KEY).parseClaimsJws (token).getBody ();
    }
//check if the token is expired
    private Boolean isTokenExpired(String token) {
        return extractExpiration (token).before (new Date ());
    }
//generate a token only using the user details;
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<> ();
        return createToken (claims, userDetails.getUsername ());
    }
//THE MAGIC, the proccess of generating the token
    private String createToken(Map<String, Object> claims, String subject) {

        JwtBuilder jwts = Jwts.builder ();
        //just normal setters
        jwts.setClaims (claims);
        //the subject here is the username
        jwts.setSubject (subject);
        //dates here are related to system time in milliseconds
        jwts.setIssuedAt (new Date (System.currentTimeMillis ()));
        //the time is set 1000(=1second) * 60(=1 minute) * 60 (=1 hour) * 10(=10 hours)
        jwts.setExpiration (new Date (System.currentTimeMillis () + 1000 * 60 * 60 * 10));
        //the algorithm used in sealing the signature is HS512
        jwts.signWith (SignatureAlgorithm.HS512, SECRET_KEY);
        //to return a token not a builder
        return jwts.compact ();
    }
//validating the token with the user
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername (token);
        return (username.equals (userDetails.getUsername ()) && !isTokenExpired (token));
    }
//check the token with the user involved inside the token
    public boolean validateToken(String token) {
        final String username = extractUsername (token);
        return (resource.userNameExists (username) && !isTokenExpired (token));
    }

    public String generateToken(Map<String, Object> claims, String string) {
        return createToken (claims, string);
    }

}