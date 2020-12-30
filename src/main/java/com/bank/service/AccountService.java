package com.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.dto.AmountTransaferRequest;
import com.bank.dto.FilterParameterAccountDto;
import com.bank.dto.SelfAmountTransferRequest;
import com.bank.exception.BusinessValidationExcpetion;
import com.bank.model.Account;
import com.bank.repository.AccountRepository;

@Service
public class AccountService {
	
	private static final Logger log = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private KafkaQueueService kafkaQueueService;
	
	public Account createAccount(Account acc) {
		acc= accountRepository.save(acc);
		return acc;
	}
	
	public Account getAccount(String accountId) {
		Optional<Account> opAccount = accountRepository.findByAccountId(accountId);
		if (opAccount.isPresent()) {
			return opAccount.get();
		} else {
			log.info("account not found for account number: {}", accountId);
			throw new BusinessValidationExcpetion("Account Not found for account Id : "+accountId);
		}	
	}



	public Page<Account> getAllAccountDetails(FilterParameterAccountDto filterAccountParameterDto, Pageable pageable) {
		Page<Account> accounts;
		if (filterAccountParameterDto != null) {
			if (filterAccountParameterDto.getMinBalance() != null) {
				accounts = accountRepository.findAllByMinBalance(filterAccountParameterDto.getMinBalance(), pageable);
				return accounts;
			}
		}
		accounts = accountRepository.findAll(pageable);
		return accounts;
	}
	
	
	public void withdrawAmount(SelfAmountTransferRequest selfAmountTransferRequest) {
		withdrawAmountInternal(selfAmountTransferRequest);
		String message = "request successfull for deposit amount" + selfAmountTransferRequest.getAmount()
				+ " from account number " + selfAmountTransferRequest.getAmount();
		kafkaQueueService.sendMessage(message);
	}
	
	private void withdrawAmountInternal(SelfAmountTransferRequest selfAmountTransferRequest) {
		Account account = this.getAccount(selfAmountTransferRequest.getFromAccountId());
		if (account.getBalance() > selfAmountTransferRequest.getAmount()) {
			Double newBalance = account.getBalance() - selfAmountTransferRequest.getAmount();
			account.setBalance(newBalance);
			accountRepository.save(account);
		} else {
			String message = "Account balance is not sufficient, account number: " + account.getAccountId();
			kafkaQueueService.sendMessage(message);
			throw new BusinessValidationExcpetion(message);
		}
	}
	
	
	public void creditAmount(SelfAmountTransferRequest selfAmountTransferRequest) {

		creditAmountInternal(selfAmountTransferRequest);
		String message = "request successfully for credit amount" + selfAmountTransferRequest.getAmount()
				+ " from account number " + selfAmountTransferRequest.getFromAccountId();
		kafkaQueueService.sendMessage(message);
	}

	
	private void creditAmountInternal(SelfAmountTransferRequest selfAmountTransferRequest) {
		Account account = this.getAccount(selfAmountTransferRequest.getFromAccountId());
		Double newBalance = account.getBalance() + selfAmountTransferRequest.getAmount();
		account.setBalance(newBalance);
		accountRepository.save(account);
	}
	
	
	@Transactional
	public void transferAmount(AmountTransaferRequest amountTransferRequest) {

		SelfAmountTransferRequest withdrawRequest = new SelfAmountTransferRequest(amountTransferRequest.getAmount(),
				amountTransferRequest.getFromAccount());
		this.withdrawAmountInternal(withdrawRequest);

		SelfAmountTransferRequest creditRequest = new SelfAmountTransferRequest(amountTransferRequest.getAmount(),
				amountTransferRequest.getToAccount());
		this.creditAmountInternal(creditRequest);
		String message = "trasaction successfully for amount " + amountTransferRequest.getAmount()
				+ " transfer from account number " + amountTransferRequest.getFromAccount() + " to account number "
				+ amountTransferRequest.getToAccount();
		kafkaQueueService.sendMessage(message);
	}
	
	public Page<Account> getAllAccountWhereAgeGreaterThan60AndIfCredited2000BalanceMoreThan5000(Pageable pageable) {
		List<Account> allAccounts =  accountRepository.getAllAccountWhereAgeGreaterThan60AndIfCredited2000BalanceMoreThan5000();
		List<Account> streamFilterAccounts = allAccounts.stream().filter(a->a.getAge()>60 && a.getBalance()>3000).collect(Collectors.toList());
		Page<Account> accWhereAgeGT60AndBalanceGT5000 = new PageImpl<Account>(streamFilterAccounts,pageable,streamFilterAccounts.size()); 
		return accWhereAgeGT60AndBalanceGT5000;
	}
	
}
