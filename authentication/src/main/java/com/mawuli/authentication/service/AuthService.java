package com.mawuli.authentication.service;

import com.mawuli.authentication.dto.AuthRequest;
import com.mawuli.authentication.dto.AuthenticationResponse;
import com.mawuli.authentication.dto.UserDTO;

public interface AuthService {
    AuthenticationResponse authenticate(AuthRequest authRequestDTO);
    AuthenticationResponse signup(UserDTO userDTO);
}
