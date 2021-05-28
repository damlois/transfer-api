package com.example.transferService.transfer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
	@Query("SELECT t FROM Transaction t WHERE t.account.accountNumber = ?1")
	Optional<List<Transaction>> getAccountTransactions(Integer accountNumber);
	
	@Query("SELECT t FROM Transaction t WHERE t.reference = ?1")
	Optional<Transaction> getTransaction(String referenceNumber);
}
