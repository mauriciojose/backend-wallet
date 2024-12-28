package com.wallet.wallet.dto;

public record TransactionDto(
  String value,
  Long payee_id
) {
}

