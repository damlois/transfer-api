package com.example.transferService.transfer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="api/v1/transapp")
public class TransferController {
	private final TransactionService transactionService;
	private final BalanceService balanceService;
		
	@Autowired
	public TransferController(TransactionService transactionService, BalanceService balanceService) {
		this.transactionService = transactionService;
		this.balanceService = balanceService;
	}

	@GetMapping(path = "transactions")
	public List<Transaction> getAllTransactions() {
		return transactionService.getAllTransactions();	
	}
	
	@GetMapping(path = "transaction/{referenceNumber}")
	public Transaction getTransaction(@PathVariable("referenceNumber") String referenceNumber) {
		return transactionService.getTransaction(referenceNumber);	
	}
	
	@GetMapping(path = "transactions/{accountNumber}")
	public List<Transaction> getAccountTransactions(@PathVariable("accountNumber") Integer accountNumber) {
		return transactionService.getAccountTransactions(accountNumber);	
	}
	
	@GetMapping(path = "balance/{accountNumber}")
	public Balance getAccountBalance(@PathVariable("accountNumber") Integer accountNumber) {
		return balanceService.getAccountBalance(accountNumber);
	}
	
	@PostMapping(path = "transfer")
	public TransferResource transferMoney(@RequestBody TransferPayload transferPayload) {
		return transactionService.transferMoney(transferPayload);
	}
}
