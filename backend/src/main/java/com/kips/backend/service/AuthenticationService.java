package com.kips.backend.service;

import com.kips.backend.service.request.AuthenticationRequest;
import com.kips.backend.service.request.ConfirmationTokenRequest;
import com.kips.backend.service.request.RegisterRequest;
import com.kips.backend.service.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException;

    Boolean enableUser(ConfirmationTokenRequest confirmationTokenRequest);
}
