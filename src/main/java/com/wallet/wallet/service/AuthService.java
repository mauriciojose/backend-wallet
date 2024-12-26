package com.wallet.wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.wallet.wallet.dto.LoginUserDto;
import com.wallet.wallet.dto.RecoveryJwtTokenDto;
import com.wallet.wallet.security.user_details.UserDetailsImpl;

@Service
public class AuthService {
  
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenService jwtTokenService;

  public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
      new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

    Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
  }
}
