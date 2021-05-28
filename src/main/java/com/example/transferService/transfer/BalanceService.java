package com.example.transferService.transfer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {
	private final BalanceRepository balanceRepository;
	private final AccountRepository accountRepository;
	
	@Autowired
	public BalanceService(BalanceRepository balanceRepository, AccountRepository accountRepository) {
		this.balanceRepository = balanceRepository;
		this.accountRepository = accountRepository;
	}

	public Balance getAccountBalance(Integer accountNumber) {
		boolean accountExists = accountRepository.existsById(accountNumber);
		if(!accountExists) {
			throw new IllegalStateException(
					"Account " + accountNumber + " does not exist");
		}
		Optional<Balance> balance =  balanceRepository.getBalance(accountNumber);
		return balance.get();
	}
}
