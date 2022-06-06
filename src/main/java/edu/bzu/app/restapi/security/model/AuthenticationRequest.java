package edu.bzu.app.restapi.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// as above , just classes to hold the username and the password in the body
public class AuthenticationRequest {
    private String username;
    private String password;
}