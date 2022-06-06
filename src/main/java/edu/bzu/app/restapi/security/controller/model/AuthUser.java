package edu.bzu.app.restapi.security.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
//create a class to hold the username and password
public
class AuthUser {
    private String username;
    private String password;

    public User toUser() {
        return new User (username, password, new ArrayList<> ());
    }
}
