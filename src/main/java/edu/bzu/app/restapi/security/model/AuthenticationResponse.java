package edu.bzu.app.restapi.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
//just to hold the token for the filter
public class AuthenticationResponse {
    private final String jwt;
}
