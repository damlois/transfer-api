package com.example.transferService.transfer;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Balance {
	@Id
	public Integer accountNumber;
	public Long balance;
	
	public Integer getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	
	public Balance() {
	}
	public Balance(Integer accountNumber, Long balance) {
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "Balance [account=" + accountNumber + ", balance=" + balance + "]";
	}
}
