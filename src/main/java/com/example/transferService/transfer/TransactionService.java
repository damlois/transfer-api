package com.example.transferService.transfer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
	private final TransactionRepository transactionRepository;
	private final AccountRepository accountRepository;

	@Autowired
	public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
		this.transactionRepository = transactionRepository;
		this.accountRepository = accountRepository;
	}
	
	public List<Transaction> getAllTransactions() {
		return transactionRepository.findAll();
	}

	public List<Transaction> getAccountTransactions(Integer accountNumber) {
		boolean accountExists = accountRepository.existsById(accountNumber);
		if(!accountExists) {
			throw new IllegalStateException(
					"Account " + accountNumber + " does not exist");
		}
		Optional<List<Transaction>> transactions =  transactionRepository.getAccountTransactions(accountNumber);
		return transactions.get();
	}
}
