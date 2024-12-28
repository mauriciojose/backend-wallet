package com.wallet.wallet.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.wallet.wallet.dto.TransactionDto;
import com.wallet.wallet.dto.TransactionResponseDto;
import com.wallet.wallet.model.User;
import com.wallet.wallet.repository.UserRepository;

@Service
public class TransactionService {
  private final UserRepository userRepository;

  public TransactionService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public TransactionResponseDto transfer(TransactionDto transactionDto) {
    User payee = userRepository.findById(transactionDto.payee_id()).orElse(null);
    if (payee == null) {
      throw new IllegalArgumentException("User not exists");
    }
    User user = getCurrentUser();
    if (payee.getId() == user.getId()) {
      throw new IllegalArgumentException("You can't make a transfer to yourself");
    }
    payee.addBalance(new BigDecimal(transactionDto.value()));

    payee = userRepository.save(payee);

    TransactionResponseDto transactionResponseDto = new TransactionResponseDto(payee.getBalance(), payee.getId());
    
    return transactionResponseDto;
  }

  private User getCurrentUser() {
    return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }
}
