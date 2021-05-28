package com.example.transferService.transfer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="api/v1/transfer")
public class TransferController {
	private final TransactionService transactionService;
		
	@Autowired
	public TransferController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@GetMapping
	public List<Transaction> getAllTransactions() {
		return transactionService.getAllTransactions();	
	}
	
	@GetMapping(path = "{accountNumber}")
	public List<Transaction> getAccountTransactions(@PathVariable("accountNumber") Integer accountNumber) {
		return transactionService.getAccountTransactions(accountNumber);	
	}
}
