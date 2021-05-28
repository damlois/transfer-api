package com.example.transferService.transfer;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransferConfig {
	@Bean
	CommandLineRunner commandLineRunner(
			TransactionRepository transactionRepo, 
			AccountRepository accountRepo,
			BalanceRepository balanceRepo
			) {
		return args -> {
			Account acct1 = new Account ("Tunji", "Awe", 309854769);
					
			Account acct2 = new Account ("Lois", "Ade", 674389065);
			
			accountRepo.saveAll(List.of(acct1, acct2));
			
			Balance bal1 = new Balance (309854769, 3000L);
			Balance bal2 = new Balance (674389065, 1000L);
			
			balanceRepo.saveAll(List.of(bal1, bal2));
			
			Transaction transact1 = new Transaction(
					"23XsEtrsasf",
					78700000L,
					acct1
			);	
			
			Transaction transact2 = new Transaction(
					"Jggsd564E",
					176000L,
					acct2
			);	
			
			transactionRepo.saveAll(List.of(transact1, transact2));
		};
	}
}
