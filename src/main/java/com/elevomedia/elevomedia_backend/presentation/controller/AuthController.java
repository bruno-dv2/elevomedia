package com.elevomedia.elevomedia_backend.presentation.controller;

import com.elevomedia.elevomedia_backend.application.service.AuthService;
import com.elevomedia.elevomedia_backend.presentation.dto.request.AuthRequestDTO;
import com.elevomedia.elevomedia_backend.presentation.dto.response.AuthResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @RequestBody @Valid AuthRequestDTO dto) {

        String token = authService.login(dto.email(), dto.senha());
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }
}
