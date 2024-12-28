package com.wallet.wallet.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="transactions")
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long value;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "payer_id", referencedColumnName = "id")
  private User payer;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "payee_id", referencedColumnName = "id")
  private User payee;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getValue() {
    return value;
  }

  public void setValue(Long value) {
    this.value = value;
  }

  public User getPayer() {
    return payer;
  }

  public void setPayer(User payer) {
    this.payer = payer;
  }

  public User getPayee() {
    return payee;
  }

  public void setPayee(User payee) {
    this.payee = payee;
  }
}
