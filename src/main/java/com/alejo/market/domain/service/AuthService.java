package com.alejo.market.domain.service;

import com.alejo.market.domain.dto.AuthenticationRequest;
import com.alejo.market.domain.dto.AuthenticationResponse;
import com.alejo.market.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService{
    /*Inyectamos el AuthenticationManager que tiene Spring*/
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ApiUserDetailsService apiUserDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public ResponseEntity<AuthenticationResponse> createToken(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = apiUserDetailsService.loadUserByUsername(request.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);

            return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
