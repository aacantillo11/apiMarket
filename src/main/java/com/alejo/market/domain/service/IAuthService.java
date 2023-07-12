package com.alejo.market.domain.service;

import com.alejo.market.domain.dto.AuthenticationRequest;
import com.alejo.market.domain.dto.AuthenticationResponse;
import org.springframework.http.ResponseEntity;


public interface IAuthService {
    ResponseEntity<AuthenticationResponse> createToken(AuthenticationRequest request);
}
