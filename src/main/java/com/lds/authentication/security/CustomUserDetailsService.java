package com.lds.authentication.security;

import com.lds.authentication.domain.UserModel;
import com.lds.authentication.port.service.user.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (email == null || email.isEmpty()) {
            throw new RuntimeException("Email nulo ou vazio");
        }
        UserModel userModel = userService.findByEmail(email);
        if (userModel == null) {
            throw new UsernameNotFoundException("Email nao encontrado" + email);
        }

        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority(userModel.getRole().name())
        );


        return new User(userModel.getEmail(), userModel.getPassword(), authorities);
    }

}