package com.lds.authentication.port.service.authentication;


import com.lds.authentication.domain.UserModel;

public interface AuthenticationService {
    UserModel authenticate(final String email, final String password);

}