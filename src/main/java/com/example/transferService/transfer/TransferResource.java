package com.example.transferService.transfer;

public class TransferResource {
	public String message;
	public Transaction transaction;
		
	public TransferResource() {
	}

	public TransferResource(String message, Transaction transaction) {
		this.message = message;
		this.transaction = transaction;
	}
	
	
}
