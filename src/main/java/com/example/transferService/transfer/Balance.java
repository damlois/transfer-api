package com.example.transferService.transfer;

public class Balance {
	public Account account;
	public Long balance;
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	
	public Balance() {
	}
	public Balance(Account account, Long balance) {
		this.account = account;
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "Balance [account=" + account + ", balance=" + balance + "]";
	}
}
