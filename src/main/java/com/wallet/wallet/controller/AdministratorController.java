package com.wallet.wallet.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.wallet.dto.TransactionDto;
import com.wallet.wallet.dto.TransactionResponseDto;
import com.wallet.wallet.service.TransactionService;

@RestController
@RequestMapping("/api/admin")
public class AdministratorController {
  private final TransactionService transactionService;

  public AdministratorController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }
  @PostMapping("/transfer/add")
  public ResponseEntity<TransactionResponseDto> transfer(@RequestBody TransactionDto transactionDto) {
    return ResponseEntity.ok(transactionService.transfer(transactionDto));
  }
}
