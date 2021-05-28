package com.example.transferService.transfer;

public class TransferPayload {
	public Integer fromAccount;
	public Integer toAccount;
	public Long amount;
	
	public Integer getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(Integer fromAccount) {
		this.fromAccount = fromAccount;
	}
	public Integer getToAccount() {
		return toAccount;
	}
	public void setToAccount(Integer toAccount) {
		this.toAccount = toAccount;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	public TransferPayload() {
	}
	
	public TransferPayload(Integer fromAccount, Integer toAccount, Long amount) {
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
	}
}
