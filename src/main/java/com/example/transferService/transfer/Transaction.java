package com.example.transferService.transfer;

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
	public Account account;
	
	public String getReference() {
		return reference;
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
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	public Transaction() {
	}
	public Transaction(String reference, Long amount, Account account) {
		this.reference = reference;
		this.amount = amount;
		this.account = account;
	}
	@Override
	public String toString() {
		return "Transaction [reference=" + reference + ", amount=" + amount + ", account=" + account + "]";
	}
	
}
