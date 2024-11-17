package com.mawuli.authentication.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mawuli.authentication.dto.AuthRequest;
import com.mawuli.authentication.dto.AuthenticationResponse;
import com.mawuli.authentication.dto.UserDTO;
import com.mawuli.authentication.enums.Role;
import com.mawuli.authentication.exception.UserException;
import com.mawuli.authentication.model.User;
import com.mawuli.authentication.repository.UserRepository;
import com.mawuli.authentication.service.AuthService;
import com.mawuli.authentication.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final ObjectMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse authenticate(AuthRequest authRequestDTO) {
        //authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDTO.getEmail(),
                        authRequestDTO.getPassword()
                )
        );

        User user = userRepository.findUsersByEmail(authRequestDTO.getEmail())
                .orElseThrow();
        String accessToken = jwtService.generateToken(user.getEmail());
        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .build();
    }

    @Override
    public AuthenticationResponse signup(UserDTO userDTO) {
        userRepository.findUsersByEmail(userDTO.getEmail())
                .ifPresent(user -> {
                    throw new UserException("User with the email already exists");
                });

        // Convert DTO to User entity and set additional properties
        User newUser = mapper.convertValue(userDTO, User.class);
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        newUser.setRole(Role.ADMIN);

        userRepository.save(newUser);

        String accessToken = jwtService.generateToken(newUser.getEmail());

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .build();
    }


}

