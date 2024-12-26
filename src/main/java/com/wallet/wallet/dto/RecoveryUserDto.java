package com.wallet.wallet.dto;

import java.util.List;

import com.wallet.wallet.model.Role;

public record RecoveryUserDto(
  Long id,
  String email,
  List<Role> roles

) {
}
