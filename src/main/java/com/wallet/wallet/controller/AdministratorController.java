package com.wallet.wallet.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdministratorController {
  @PostMapping
  public ResponseEntity<List<String>> createAdmin() {
        return ResponseEntity.ok(new ArrayList<>());
    }
}
