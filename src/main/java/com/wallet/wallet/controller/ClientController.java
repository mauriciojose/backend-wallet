package com.wallet.wallet.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/client")
public class ClientController {
@PostMapping
  public ResponseEntity<List<String>> createUser() {
        return ResponseEntity.ok(new ArrayList<>());
    }
}
