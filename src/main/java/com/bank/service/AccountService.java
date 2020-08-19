package com.bank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dto.CreateAccountDto;
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

//	@Autowired
//	private UserService userService;

	public Account createAccount(CreateAccountDto createAccountDto) {
		log.info("request for create account : {}", createAccountDto);
		Account account = accountMapper.createAccountDtoToAccount(createAccountDto);
		account = accountRepository.save(account);
		log.info("account created succesfully, account id : {}", account.getAccountId());
		return account;
	}

		
	
	
}
