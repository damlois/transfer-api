package com.example.transferService.transfer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
	@Query("SELECT b FROM Balance b WHERE b.accountNumber = ?1")
	Optional<Balance> getBalance(Integer accountNumber);
}
