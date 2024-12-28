package com.wallet.wallet.dto;

import java.math.BigDecimal;

public record TransactionResponseDto(
  BigDecimal balance,
  Long payee_id
) {
}


