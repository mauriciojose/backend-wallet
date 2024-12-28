package com.wallet.wallet.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String email;
  private String password;

  private BigDecimal balance = BigDecimal.ZERO;

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JoinTable(name="users_roles",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name="role_id"))
  private List<Role> roles;


  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void addBalance(BigDecimal amount) {
    if (amount.compareTo(BigDecimal.ZERO) < 0) {
        throw new IllegalArgumentException("Amount must be positive");
    }
    this.balance = this.balance.add(amount);
  }

  public void subtractBalance(BigDecimal amount) {
    if (amount.compareTo(BigDecimal.ZERO) < 0) {
        throw new IllegalArgumentException("Amount must be positive");
    }
    if (this.balance.compareTo(amount) < 0) {
        throw new IllegalArgumentException("Insufficient balance");
    }
    this.balance = this.balance.subtract(amount);
  }
}