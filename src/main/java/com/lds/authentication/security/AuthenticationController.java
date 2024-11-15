package com.lds.authentication.security;

import com.lds.authentication.domain.UserModel;
import com.lds.authentication.dto.JwtTokenDto;
import com.lds.authentication.port.service.authentication.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    public AuthenticationController(AuthenticationService authenticationService, JwtService jwtService, UserDetailsService userDetailsService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtTokenDto> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        String email = authenticationRequest.getEmail();
        String password = authenticationRequest.getPassword();

        UserModel authenticatedUser = authenticationService.authenticate(email, password);

        if (authenticatedUser == null) {
            throw new BadCredentialsException("Email ou senha invalidos");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        if (userDetails == null) {
            throw new UsernameNotFoundException("Email ou senha invalidos");
        }

        final String jwtToken = jwtService.generateToken(
                userDetails,
                authenticatedUser.getFullName(),
                authenticatedUser.getRole(),
                authenticatedUser.getEmail());

        if (jwtToken == null || jwtToken.isEmpty()) {
            throw new InternalError("Jwt invalido");
        }
        System.out.println("Jwt criado: " + jwtToken);
        final JwtTokenDto tokenDto = new JwtTokenDto(jwtToken);

        return ResponseEntity.ok(tokenDto);

    }
}
