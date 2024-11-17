package com.mawuli.authentication.controller;


import com.mawuli.authentication.dto.AuthRequest;
import com.mawuli.authentication.dto.ResponseHandler;
import com.mawuli.authentication.dto.UserDTO;
import com.mawuli.authentication.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/v1/auth/signup")
    public ResponseEntity<Object> signup(@Valid @RequestBody UserDTO userDTO) {
        return ResponseHandler.successResponse(HttpStatus.CREATED, authService.signup(userDTO));
    }

    @PostMapping("/v1/auth/authenticate")
    public ResponseEntity<Object> authenticate(@Valid @RequestBody AuthRequest authRequestDTO) {
        return ResponseHandler.successResponse(HttpStatus.OK, authService.authenticate(authRequestDTO));
    }
}
