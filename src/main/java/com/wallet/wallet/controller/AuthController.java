package com.wallet.wallet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.wallet.dto.LoginUserDto;
import com.wallet.wallet.dto.RecoveryJwtTokenDto;
import com.wallet.wallet.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private AuthService authService;
  public AuthController(AuthService authService) {
    this.authService = authService;
  }
  @PostMapping("/login")
  public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody LoginUserDto loginUserDto) {
    RecoveryJwtTokenDto token = authService.authenticateUser(loginUserDto);
    return new ResponseEntity<>(token, HttpStatus.OK);
  }
}
