package com.lds.authentication.service.authentication;

import com.lds.authentication.domain.UserModel;
import com.lds.authentication.port.service.authentication.AuthenticationService;
import com.lds.authentication.port.service.user.UserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserModel authenticate(String email, String password) {
        final UserModel userModel = userService.findByEmail(email);
        if (userModel == null) {
            throw new UsernameNotFoundException("Credenciais invalidas");
        }

        if (!passwordEncoder.matches(password, userModel.getPassword())) {
            throw new BadCredentialsException("Credenciais invalidas");
        }

        return userModel;

    }
}
