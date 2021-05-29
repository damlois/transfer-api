package com.example.transferService.transfer;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Transaction {
	@Id
	public String reference;
	public Long amount;
	@ManyToOne
	public Account fromAccount;
	@ManyToOne
	public Account toAccount;
	public LocalDateTime createdAt;
	
	public String getReference() {
		return reference;
	}
	public Account getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}
	public Account getToAccount() {
		return toAccount;
	}
	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	public Transaction() {
	}
	public Transaction(String reference, Long amount, Account fromAccount, Account toAccount, LocalDateTime createdAt) {
		this.reference = reference;
		this.amount = amount;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.createdAt = createdAt;
	}	
}
