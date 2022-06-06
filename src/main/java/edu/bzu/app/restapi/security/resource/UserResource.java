package edu.bzu.app.restapi.security.resource;

import edu.bzu.app.restapi.security.controller.model.AuthUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//this service should be a database jpa repository but i didn't have time for it
public class UserResource {
    private final List<AuthUser> users = List.of (
            new AuthUser ("user","user"),
            new AuthUser ("admin","admin"),
            new AuthUser ("USER","USER"),
            new AuthUser ("ADMIN","ADMIN")
    );
    public boolean userNameExists(String user){
        for (AuthUser authUser : users) {
            if (authUser.getUsername ().equals (user))
                return true;
        }
        return false;
    }
    private int getUserInt(String user){
        for (int i = 0; i < users.size (); i++) {
            if(users.get (i).getUsername ().equals (user))
                return i;
        }
        return -1;
    }
    public AuthUser getUser(String user){
        for (int i = 0; i < users.size (); i++) {
            if(users.get (i).getUsername ().equals (user))
               users.get (i);
        }
        return null;
    }
    public boolean isUserValid(String user,String password){
       int i = getUserInt (user);
       if(i != -1){
           return password.equals (users.get (i).getPassword ());
       }
        return false;
    }
}
