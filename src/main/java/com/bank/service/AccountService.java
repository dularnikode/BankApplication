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
import com.bank.dto.CreateAccountDto;
import com.bank.dto.FilterParameterAccountDto;
import com.bank.dto.SelfAmountTransferRequest;
import com.bank.mapper.AccountMapper;
import com.bank.model.Account;
import com.bank.repository.AccountRepository;

@Service
public class AccountService {
	
	private static final Logger log = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AccountMapper accountMapper;
	
	@Autowired
	private KafkaQueueService kafkaQueueService;
	
	public Account createAccount(CreateAccountDto createAccountDto) {
		log.info("request for create account : {}", createAccountDto);
		Account account = accountMapper.createAccountDtoToAccount(createAccountDto);
		account = accountRepository.save(account);
		log.info("account created succesfully, account id : {}", account.getAccountId());
		return account;
	}
	
	public Account getAccount(String accountId) {
		log.info("request for get account details of account number: {}", accountId);
		Optional<Account> opAccount = accountRepository.findByAccountId(accountId);
		if (opAccount.isPresent()) {
			log.info("get account details of account id: {} successfully :: account details  : {}", accountId,
					opAccount.get());
			return opAccount.get();
		} else {
			log.info("account not found for account number: {}", accountId);
			return null;
		}
		
	}



	public Page<Account> getAllAccountDetails(FilterParameterAccountDto filterAccountParameterDto, Pageable pageable) {
		log.info("request for get all account details");
		Page<Account> accounts;
		if (filterAccountParameterDto != null) {
			if (filterAccountParameterDto.getMinBalance() != null) {
				accounts = accountRepository.findAllByMinBalance(filterAccountParameterDto.getMinBalance(), pageable);
				log.info("successfully get all account details");
				return accounts;
			}
		}
		accounts = accountRepository.findAll(pageable);
		log.info("successfully get all account details");
		return accounts;
	}
	
	
	public void withdrawAmount(SelfAmountTransferRequest selfAmountTransferRequest) {
		log.info("request iniate for deposite amount {} from account number {}", selfAmountTransferRequest.getAmount(),
				selfAmountTransferRequest.getFromAccountId());
		withdrawAmountInternal(selfAmountTransferRequest);
		String message = "request successfull for deposit amount" + selfAmountTransferRequest.getAmount()
				+ " from account number " + selfAmountTransferRequest.getAmount();
		log.info(message);
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
			log.info(message);
			kafkaQueueService.sendMessage(message);
		}
	}
	
	
	public void creditAmount(SelfAmountTransferRequest selfAmountTransferRequest) {
		log.info("request iniate for credit amount {} from account number {}", selfAmountTransferRequest.getAmount(),
				selfAmountTransferRequest.getFromAccountId());
		creditAmountInternal(selfAmountTransferRequest);
		String message = "request successfully for credit amount" + selfAmountTransferRequest.getAmount()
				+ " from account number " + selfAmountTransferRequest.getFromAccountId();
		log.info(message);
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

		log.info("transaction request for amount {} transfer from account number {} to account number {}",
				amountTransferRequest.getAmount(), amountTransferRequest.getFromAccount(),
				amountTransferRequest.getToAccount());

		SelfAmountTransferRequest withdrawRequest = new SelfAmountTransferRequest(amountTransferRequest.getAmount(),
				amountTransferRequest.getFromAccount());
		this.withdrawAmountInternal(withdrawRequest);

		SelfAmountTransferRequest creditRequest = new SelfAmountTransferRequest(amountTransferRequest.getAmount(),
				amountTransferRequest.getToAccount());
		this.creditAmountInternal(creditRequest);
		String message = "trasaction successfully for amount " + amountTransferRequest.getAmount()
				+ " transfer from account number " + amountTransferRequest.getFromAccount() + " to account number "
				+ amountTransferRequest.getToAccount();
		log.info(message);
		kafkaQueueService.sendMessage(message);
	}
	
	public Page<Account> getAllAccountWhereAgeGreaterThan60AndIfCredited2000BalanceMoreThan5000(Pageable pageable) {
		log.info("request for get all transaction where age of account holder is greater than 60, and if 2000 credited"
				+ " than available balance become more than 5000");
		List<Account> allAccounts =  accountRepository.getAllAccountWhereAgeGreaterThan60AndIfCredited2000BalanceMoreThan5000();
		List<Account> streamFilterAccounts = allAccounts.stream().filter(a->a.getAge()>60 && a.getBalance()>3000).collect(Collectors.toList());
		Page<Account> accWhereAgeGT60AndBalanceGT5000 = new PageImpl<Account>(streamFilterAccounts,pageable,streamFilterAccounts.size()); 
		return accWhereAgeGT60AndBalanceGT5000;
	}
	


}
