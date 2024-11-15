package com.lds.authentication.security;

import com.lds.authentication.dto.JwtTokenDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    public ResponseEntity<JwtTokenDto> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(null);
    }
}
