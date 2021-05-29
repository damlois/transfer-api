package com.example.transferService.transfer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {
	private final TransactionRepository transactionRepository;
	private final AccountRepository accountRepository;
	private final BalanceRepository balanceRepository;

	@Autowired
	public TransactionService(
			TransactionRepository transactionRepository, 
			AccountRepository accountRepository,
			BalanceRepository balanceRepository) {
		this.transactionRepository = transactionRepository;
		this.accountRepository = accountRepository;
		this.balanceRepository = balanceRepository;
	}
	
	public List<Transaction> getAllTransactions() {
		return transactionRepository.findAll();
	}
	
	public Transaction getTransaction(String referenceNumber) {
		Optional<Transaction> transaction = transactionRepository.getTransaction(referenceNumber);
		return transaction.get();
	}

	public List<Transaction> getAccountTransactions(Integer accountNumber) {
		checkAccountExists(accountNumber);
		Optional<List<Transaction>> transactions =  transactionRepository.getAccountTransactions(accountNumber);
		return transactions.get();
	}

	@Transactional
	public TransferResource transferMoney(TransferPayload transferPayload) {
		Account fromAccount = getAccount(transferPayload.fromAccount);
		Account toAccount = getAccount(transferPayload.toAccount);
		
		List<Transaction> possibleDuplicateTransactions = (transactionRepository.getDuplicateTransactions(
				fromAccount, toAccount, transferPayload.amount)).get();
		
		int size = possibleDuplicateTransactions.size();
		if(size > 0) {
			Long duration = ChronoUnit.SECONDS.between(possibleDuplicateTransactions.get(size - 1).createdAt, LocalDateTime.now());
			
			if(duration < 30) {
				throw new IllegalStateException(
						"Possible duplicate transaction!");
			}
		}
		
		checkAccountExists(transferPayload.toAccount);
		
		Balance senderBalance = (balanceRepository.getBalance(transferPayload.fromAccount)).get();
		Balance receiverBalance = (balanceRepository.getBalance(transferPayload.toAccount)).get();
		
		if(transferPayload.amount <= 0) {
			throw new IllegalStateException(
					"Invalid amount. Amount must be greater than 0");
		}
		
		if(senderBalance.balance < transferPayload.amount) {
			throw new IllegalStateException(
					"Transfer successful. Insufficient Balance");
		}
		
		senderBalance.setBalance(senderBalance.balance - transferPayload.amount);
		receiverBalance.setBalance(receiverBalance.balance + transferPayload.amount);
		
		Transaction transaction = new Transaction(
				generateReferenceNumber(),
				transferPayload.amount,
				fromAccount,
				toAccount,
				LocalDateTime.now()
		);
		
		transactionRepository.save(transaction);
		
		return new TransferResource("Transaction Successful", transaction);
	}
	
	private void checkAccountExists(Integer accountNumber) {
		boolean accountExists = accountRepository.existsById(accountNumber);
		if(!accountExists) {
			throw new IllegalStateException(
					"Account " + accountNumber + " does not exist");
		}
	}
	
	private Account getAccount(Integer accountNumber) {
		return accountRepository.findById(accountNumber)
		.orElseThrow(() -> new IllegalStateException(
				"Account " + accountNumber + " does not exist"));
	}
	
	private String generateReferenceNumber() {
		String refNumber = "";
		try {
		SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
		String randomNum = Integer.valueOf(prng.nextInt()).toString();
		MessageDigest sha = MessageDigest.getInstance("SHA-1");
	      byte[] result =  sha.digest(randomNum.getBytes());
	      refNumber = hexEncode(result);
	      
		}
		catch (NoSuchAlgorithmException ex){
			throw new IllegalStateException(
					"An error occured. Please try Again.");
		}
		
		return refNumber;
	}
	
	static private String hexEncode(byte[] input){
	    StringBuilder result = new StringBuilder();
	    char[] digits = {'0', '1', '2', '3', '4','5','6','7','8','9','a','b','c','d','e','f'};
	    for (int idx = 0; idx < input.length; ++idx) {
	      byte b = input[idx];
	      result.append(digits[ (b&0xf0) >> 4 ]);
	      result.append(digits[ b&0x0f]);
	    }
	    return result.toString();
	}
}
