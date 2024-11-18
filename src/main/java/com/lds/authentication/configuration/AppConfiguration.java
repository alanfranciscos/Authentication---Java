package com.lds.authentication.configuration;

import com.lds.authentication.dao.UserDaoImpl;
import com.lds.authentication.port.dao.user.UserDao;
import com.lds.authentication.port.service.authentication.AuthenticationService;
import com.lds.authentication.port.service.user.UserService;
import com.lds.authentication.security.JwtService;
import com.lds.authentication.security.JwtServiceImpl;
import com.lds.authentication.service.authentication.AuthenticationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Connection;

@Configuration
public class AppConfiguration {

    @Bean
    public UserDao getUserDao(final Connection connection) {
        return new UserDaoImpl(connection);
    }

    @Bean
    public AuthenticationService authenticationService(final UserService userService, final PasswordEncoder passwordEncoder) {
        return new AuthenticationServiceImpl(userService, passwordEncoder);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtService jwtService() {
        return new JwtServiceImpl();
    }
}
