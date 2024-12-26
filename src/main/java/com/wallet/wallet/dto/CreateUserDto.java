package com.wallet.wallet.dto;

import com.wallet.wallet.model.enums.RoleName;

public record CreateUserDto(
  String name,
  String email,
  String password,
  RoleName role
) {
}