package edu.bzu.app.restapi.security.controller.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
//extends the first class so it can hold the token too
public
class TokenHolder extends AuthUser {
    String token;
}
